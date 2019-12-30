package ru.irinaKuznetsovaa.telegramBot;

import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramBot<userID> extends TelegramLongPollingCommandBot {

        private static final String USERNAME = "@IJistABot";
        private static final String TOKEN = System.getenv("VARIABLE_NAME");
        public StateManager stateManager=new StateManager();
        private Integer UserID;

    public TelegramBot(DefaultBotOptions botOptions) {
            super(botOptions, USERNAME);
        register(new CommandStart());
        }

        @Override
        public String getBotToken(){
            return TOKEN;
        }


    @Override
    public void processNonCommandUpdate(Update update) {
        long chat_id = update.getMessage().getChatId();
        Message inMessage = update.getMessage();
        SendMessage outMessage = new SendMessage().setChatId(chat_id);
        Integer UserID=inMessage.getFrom().getId();

        if (inMessage != null && inMessage.hasText()) {
            try {
                outMessage.setChatId(inMessage.getChatId());
                outMessage.setText(stateManager.TransactionStateManager(UserID,inMessage.getText()));
                execute(outMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}
