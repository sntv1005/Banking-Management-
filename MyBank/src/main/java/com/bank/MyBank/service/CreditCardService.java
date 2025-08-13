package com.bank.MyBank.service;

import com.bank.MyBank.entity.Account;
import com.bank.MyBank.entity.CreditCard;
import com.bank.MyBank.entity.Customer;
import com.bank.MyBank.exception.BadRequestException;
import com.bank.MyBank.exception.NotFoundException;
import com.bank.MyBank.repository.AccountRepo;
import com.bank.MyBank.repository.CreditCardRepo;
import com.bank.MyBank.repository.CustomerRepo;
import com.bank.MyBank.request.CreditCardChargeRequest;
import com.bank.MyBank.request.CreditCardIssueRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CreditCardService {

    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private CreditCardRepo creditCardRepo;
    @Autowired
    private AccountRepo accountRepo;

    public boolean eligible(Customer c){
        // Simple rule: eligible if monthly income >= 30000 OR any account balance >= 50000
        boolean incomeOk = (c.getMonthlyIncome() != null) && c.getMonthlyIncome() >= 30000;
        boolean balanceOk = c.getAccounts().stream().anyMatch(a -> a.getBalance().doubleValue() >= 50000);
        return incomeOk || balanceOk;
    }

    public CreditCard issue(CreditCardIssueRequest req){
        Customer c = customerRepo.findById(req.customerId)
                .orElseThrow(() -> new NotFoundException("Customer not found"));
        if (!eligible(c)) throw new BadRequestException("Customer not eligible for a credit card");

        CreditCard card = new CreditCard();
        card.setCustomer(c);
        card.setCardNumber("CARD-" + UUID.randomUUID().toString().substring(0,12).replace("-", "").toUpperCase());
        // credit limit: 2x monthly income (min 50k, max 3L) OR 1x highest balance
        double incomeLimit = (c.getMonthlyIncome() == null ? 0 : c.getMonthlyIncome()) * 2;
        double highestBal = c.getAccounts().stream().map(Account::getBalance)
                .map(BigDecimal::doubleValue).collect(Collectors.toList()).stream().mapToDouble(d->d).max().orElse(0);
        double limit = Math.max(Math.max(50000, incomeLimit), highestBal);
        limit = Math.min(limit, 300000);
        card.setCreditLimit(BigDecimal.valueOf(limit));
        return creditCardRepo.save(card);
    }

    public CreditCard get(Long id){
        return creditCardRepo.findById(id).orElseThrow(() -> new NotFoundException("Card not found: " + id));
    }

    public CreditCard chargeOrPay(CreditCardChargeRequest req){
        CreditCard card = get(req.cardId);
        BigDecimal amt = BigDecimal.valueOf(req.amount);

        if (req.payment) {
            // repayment reduces balance
            card.setCurrentBalance(card.getCurrentBalance().subtract(amt));
            if (card.getCurrentBalance().doubleValue() < 0) card.setCurrentBalance(BigDecimal.ZERO);
        } else {
            // new spend increases balance; must be <= limit
            if (card.getCurrentBalance().add(amt).compareTo(card.getCreditLimit()) > 0)
                throw new BadRequestException("Exceeds credit limit");
            card.setCurrentBalance(card.getCurrentBalance().add(amt));
        }
        return creditCardRepo.save(card);
    }

    public String cardStatementCsv(CreditCard card){
        // For simplicity, we just output a single line with current balance and limit
        StringBuilder sb = new StringBuilder();
        sb.append("CardNumber,CreditLimit,CurrentBalance,APR\n");
        sb.append(card.getCardNumber()).append(",")
                .append(card.getCreditLimit()).append(",")
                .append(card.getCurrentBalance()).append(",")
                .append(card.getAprPercent()).append("\n");
        return sb.toString();
    }


}
