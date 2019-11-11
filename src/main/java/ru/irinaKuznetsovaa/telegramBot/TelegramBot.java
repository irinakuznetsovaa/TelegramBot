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
    public static final String TOKEN=System.getenv("VARIABLE_NAME");
    public TelegramBot(DefaultBotOptions botOptions) {
        super(botOptions, "IJustABot");
        register(new Command());
    }


    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegram = new TelegramBotsApi();
        try {
            DefaultBotOptions botOptions = ApiContext.getInstance(DefaultBotOptions.class);
          botOptions.setProxyHost("209.90.63.108");
            botOptions.setProxyPort(80);
    botOptions.setProxyType(DefaultBotOptions.ProxyType.HTTP);
            telegram.registerBot(new TelegramBot(botOptions));
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }


    }


    @Override
    public void processNonCommandUpdate(Update update) {
        Message inMessage = update.getMessage();
        SendMessage outMessage = new SendMessage();
        if (inMessage != null && inMessage.hasText()) {
            try {

                setButtons(outMessage);
                switch (inMessage.getText()) {
                    case "help":
                        outMessage.setChatId(inMessage.getChatId());
                        outMessage.setText("„ем € могу помочь ?");
                        break;
                    case "information":
                        outMessage.setChatId(inMessage.getChatId());
                        outMessage.setText("TelegramBot ,который копирует ваше сообщение ");
                        break;
                    case " нопки":
                        execute(sendInlineKeyBoardMessage(update.getMessage().getChatId()));
                        break;
                    case "What is your name?":
                        outMessage.setChatId(inMessage.getChatId());
                        outMessage.setText("My name a JustABot!");
                        break;
                    default:
                        outMessage.setChatId(inMessage.getChatId());
                        outMessage.setText(inMessage.getText());
                        break;
                }
                execute(outMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        } else if (update.hasCallbackQuery()) {
            String callbackId = update.getCallbackQuery().getId();
            AnswerCallbackQuery answerCallbackQuery = new AnswerCallbackQuery()
                    .setCallbackQueryId(callbackId)
                    .setText("Ok")
                    .setShowAlert(true);
            try {
                execute(answerCallbackQuery);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
    public static void setButtons(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        keyboardFirstRow.add(new KeyboardButton("information"));
        keyboardFirstRow.add(new KeyboardButton("help"));
        keyboardSecondRow.add(new KeyboardButton("What is your name?"));
        keyboardRowList.add(keyboardSecondRow);
        keyboardRowList.add(keyboardFirstRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);

    }
    public static SendMessage sendInlineKeyBoardMessage(Long chatId) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();

        keyboardButtonsRow1.add(new InlineKeyboardButton()
                .setText("notifications\n")
                .setCallbackData("\"notifications\n\" is very technical"));
        keyboardButtonsRow1.add(new InlineKeyboardButton()
                .setText("weatherSPb")
                .setUrl("https://www.gismeteo.ru/weather-sankt-peterburg-4079/"));
        rowList.add(keyboardButtonsRow1);

        inlineKeyboardMarkup.setKeyboard(rowList);
        SendMessage message = new SendMessage()
                .setChatId(chatId)
                .setText("InlineKeyBoard")
                .setReplyMarkup(inlineKeyboardMarkup);
        return message;

    }
    @Override
    public String getBotToken() {
        return TOKEN;
    }
}
