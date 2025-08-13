package com.bank.MyBank.controller;

import com.bank.MyBank.entity.Account;
import com.bank.MyBank.entity.CreditCard;
import com.bank.MyBank.repository.AccountRepo;
import com.bank.MyBank.request.StatementFilter;
import com.bank.MyBank.service.CreditCardService;
import com.bank.MyBank.service.StatementService;
import com.bank.MyBank.util.CsvExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/statement")
public class StatementController {

    @Autowired
    private  StatementService statements;
    @Autowired
    private  AccountRepo accounts;
    @Autowired
    private  CreditCardService cards;

    @GetMapping("/account/{accountId}")
    public ResponseEntity<byte[]> accountCsv(@PathVariable Long accountId, StatementFilter filter){
        Account a = accounts.findById(accountId).orElseThrow();
        var txns = statements.accountStatement(accountId, filter);
        String csv = CsvExporter.txnsToCsv(txns);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=account-"+a.getAccountNumber()+"-statement.csv");
        return new ResponseEntity<>(csv.getBytes(), headers, HttpStatus.OK);
    }

    @GetMapping("/card/{cardId}")
    public ResponseEntity<byte[]> cardCsv(@PathVariable Long cardId){
        CreditCard card = cards.get(cardId);
        String csv = cards.cardStatementCsv(card);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=card-"+card.getCardNumber()+"-statement.csv");
        return new ResponseEntity<>(csv.getBytes(), headers, HttpStatus.OK);
    }
}
