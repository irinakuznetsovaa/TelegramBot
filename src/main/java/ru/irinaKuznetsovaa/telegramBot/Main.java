package ru.irinaKuznetsovaa.telegramBot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Main {
    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            DefaultBotOptions botOptions = ApiContext.getInstance(DefaultBotOptions.class);
            botOptions.setProxyHost("163.172.147.94");
            botOptions.setProxyPort(8811);
            botOptions.setProxyType(DefaultBotOptions.ProxyType.HTTP);
            telegramBotsApi.registerBot(new TelegramBot(botOptions));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
