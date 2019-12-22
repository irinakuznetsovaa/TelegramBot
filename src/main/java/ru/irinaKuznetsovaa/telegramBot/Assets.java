package ru.irinaKuznetsovaa.telegramBot;

public class Assets {
    private String name;
    private String ticker;

    public Assets(String name1,String ticker1){
        name=name1;
        ticker=ticker1;
    }

    public String getName(){
        return name;
    }
    public String getTicker(){
        return ticker;
    }

}
