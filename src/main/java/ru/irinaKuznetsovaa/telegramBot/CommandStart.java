package ru.irinaKuznetsovaa.telegramBot;

import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class CommandStart extends BotCommand {
    public CommandStart() {
        super("/start", " ");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments) {
        String userName = chat.getUserName();
        if (userName == null || userName.isEmpty()) {
            userName = user.getFirstName() + " " + user.getLastName();
        }
        StringBuilder messageTextBuilder = new StringBuilder("Здравствуйте ! Это бот-инвестиционный портфель,что вы хотите сделать?\n" +
                "Купить акции\n" +
                "Продать акции\n" +
                "Посмотреть список всех транзакций\n" +
                "Состояние портфеля");

        if (arguments != null && arguments.length > 0) {

            messageTextBuilder.append("\n");
            messageTextBuilder.append(String.join(" ", arguments));
        }
        SendMessage answer = new SendMessage();
        answer.setChatId(chat.getId().toString());
        answer.setText(messageTextBuilder.toString());

        try {
            absSender.execute(answer);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    }


