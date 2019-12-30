package ru.irinaKuznetsovaa.telegramBot;

import java.util.ArrayList;
import java.util.List;

public class Portfolio {
    private List<Assets> listAssets;
    private List<Transaction>listTransaction;
    private Double freeMoney;


    public Portfolio(List<Assets> listAssets,List<Transaction>listTransaction,Double freeMoney){
       this.listAssets=listAssets;
       this.freeMoney=freeMoney;
       this.listTransaction=listTransaction;
    }

    public Double getFreeMoney(){
        return freeMoney;
    }

    public List<Assets> getListAssets() {
        return listAssets;
    }

    public List<Transaction> getListTransaction(){
        return listTransaction;
    }
}
