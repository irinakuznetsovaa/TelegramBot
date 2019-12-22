package ru.irinaKuznetsovaa.telegramBot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.ApiContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.util.ArrayList;
import java.util.List;


    public class TelegramBot extends TelegramLongPollingCommandBot {

        public static final String USERNAME = "@IJistABot";
        public static final String TOKEN = System.getenv("VARIABLE_NAME");

        private long chat_id;

        public TelegramBot(DefaultBotOptions botOptions) {
            super(botOptions, USERNAME);

        }

        public static void main(String[] args) {
            ApiContextInitializer.init();
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
            try {
                DefaultBotOptions botOptions = ApiContext.getInstance(DefaultBotOptions.class);
                botOptions.setProxyHost("178.128.50.214");
                botOptions.setProxyPort(8080);
                botOptions.setProxyType(DefaultBotOptions.ProxyType.HTTP);
                telegramBotsApi.registerBot(new TelegramBot(botOptions));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }


        @Override
        public void processNonCommandUpdate(Update update) {
            update.getUpdateId();

            SendMessage sendMessage = new SendMessage().setChatId(update.getMessage().getChatId());
            chat_id = update.getMessage().getChatId();
            sendMessage.setText(input(update.getMessage().getText()));

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

        public String input(String msg){
            if(msg.contains("Hi") || msg.contains("Hello") || msg.contains("Привет")){
                return "Привет фанатам! Я спротивный бот КХЛ. Если любишь хоккей, погнали!!!";
            }
            return "Не понял!";
        }

        @Override
        public String getBotToken() {
            return TOKEN;
        }
    }
