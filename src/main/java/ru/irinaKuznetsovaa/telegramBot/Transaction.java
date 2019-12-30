package ru.irinaKuznetsovaa.telegramBot;

import java.util.Date;

public class Transaction {
    private String status;
    private Assets assets;
    private Integer count;
    private Date data;
    private Double price;


    public Transaction(String status, Assets assets, Integer count,Double price) {
        this.status = status;
        this.assets = assets;
        this.count = count;
        this.data = new Date();
        this.price=price;
    }

    public String getStatus(){ return status;}
    public Integer getCount(){return count;}
    public Assets getAssets(){return assets;}
    public Double getPrice(){return price;}
    public Date getData() {
        return data;
    }
}
