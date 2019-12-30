package ru.irinaKuznetsovaa.telegramBot;


public class Assets {

    private String name;
    private String ticker;
    private Double currentPrice;

    public Assets(String name, String ticker, Double currentPrice) {
        this.name = name;
        this.ticker = ticker;
        this.currentPrice = currentPrice;
    }

    public String getName() {
        return name;
    }

    public String getTicker() {
        return ticker;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

}