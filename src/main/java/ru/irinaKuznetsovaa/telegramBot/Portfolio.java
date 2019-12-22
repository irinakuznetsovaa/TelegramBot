package ru.irinaKuznetsovaa.telegramBot;

import java.util.List;

public class Portfolio {
    private Assets listAssets;
    private int count;
    private float cost;


    public Portfolio(Assets listAssets1,int count1,float cost1){
       listAssets=listAssets1;
        count=count1;
        cost=cost1;
    }

    public int getCount() {
        return count;
    }
    public float getCost(){
        return cost;
    }
}
