package ru.irinaKuznetsovaa.telegramBot;

public class Assets {

    private String name;
    private String ticker;
    private float currentPrice;


    public Assets(String name,String ticker,float currentPrice){
        this.name=name;
        this.ticker=ticker;
        this.currentPrice=currentPrice;
    }

    public String getName(){
        return name;
    }
    public String getTicker(){
        return ticker;
    }
    public float getCurrentPrice(){return currentPrice;}


}
