package ru.irinaKuznetsovaa.telegramBot;

public class Transaction {
    private String status;
    private Portfolio portfolio;

    public Transaction(String status1, Portfolio portfolio1) {
        status = status1;
        portfolio = portfolio1;
    }
    public String getStatus(){
        return status;
    }

}
