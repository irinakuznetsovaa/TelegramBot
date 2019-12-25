package ru.irinaKuznetsovaa.telegramBot;

import java.util.Date;

public class Transaction {
    private String status;
    private Assets assets;
    private Integer count;
    private Date data;

    public Transaction(String status, Assets assets, Integer count, Date data) {
        this.status = status;
        this.assets = assets;
        this.count = count;
        this.data = data;
    }

    public String getStatus(){ return status;}
    public Integer getCount(){return count;}
    public Date getData(){return data;}
    public Assets getAssets(){return assets;};

}
