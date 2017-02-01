package com.example.tiuadmin.simplysafeconusmerapp.Models;

/**
 * Created by tiuadmin on 01/02/17.
 */

public class MerchantTransactionModel {
    String id;
    String TransactionMessage;
    String TransacitonDate;
    String TransactionTIme;
    public MerchantTransactionModel(String id, String transactionMessage, String transacitonDate, String transactionTIme) {
        this.id = id;
        this.TransactionMessage = transactionMessage;
        TransacitonDate = transacitonDate;
        TransactionTIme = transactionTIme;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTransactionMessage() {
        return TransactionMessage;
    }

    public void setTransactionMessage(String transactionMessage) {
        TransactionMessage = transactionMessage;
    }

    public String getTransacitonDate() {
        return TransacitonDate;
    }

    public void setTransacitonDate(String transacitonDate) {
        TransacitonDate = transacitonDate;
    }

    public String getTransactionTIme() {
        return TransactionTIme;
    }

    public void setTransactionTIme(String transactionTIme) {
        TransactionTIme = transactionTIme;
    }




}
