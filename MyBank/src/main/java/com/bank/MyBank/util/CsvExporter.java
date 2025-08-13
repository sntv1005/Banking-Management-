package com.bank.MyBank.util;



import com.bank.MyBank.entity.Txn;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class CsvExporter {
    public static String txnsToCsv(List<Txn> txns){
        StringBuilder sb = new StringBuilder();
        sb.append("TxnId,DateTime,Type,Amount,Description\n");
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        for (Txn t : txns) {
            sb.append(t.getId()).append(",")
                    .append(t.getCreatedAt().format(fmt)).append(",")
                    .append(t.getType()).append(",")
                    .append(t.getAmount()).append(",")
                    .append(t.getDescription() == null ? "" : t.getDescription().replace(",", " ")).append("\n");
        }
        return sb.toString();
    }
}