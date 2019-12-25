package ru.irinaKuznetsovaa.telegramBot;

import java.util.List;

public class Portfolio {
    private List<Assets> listAssets;
    private float freeMoney;

    public Portfolio(List<Assets> listAssets,float freeMoney){
       this.listAssets=listAssets;
       this.freeMoney=freeMoney;
    }

    public float getFreeMoney(){
        return freeMoney;
    }

    public List<Assets> getListAssets() {
        return listAssets;
    }



}
