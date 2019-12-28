package ru.irinaKuznetsovaa.telegramBot;

import java.math.BigDecimal;
import java.util.List;

public class Portfolio {
    private List<Assets> listAssets;
    private Double freeMoney;

    public Portfolio(List<Assets> listAssets,Double freeMoney){
       this.listAssets=listAssets;
       this.freeMoney=freeMoney;
    }

    public Double getFreeMoney(){
        return freeMoney;
    }

    public List<Assets> getListAssets() {
        return listAssets;
    }

}
