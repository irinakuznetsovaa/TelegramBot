import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class sendInlineKeyBoardMessage {
    private static Long chatId;


    public sendInlineKeyBoardMessage(Long chatId) {
        this.chatId=chatId;
    }

    public SendMessage sendInlineKeyBoardMessage1() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>>rowList = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();

        keyboardButtonsRow1.add((new InlineKeyboardButton().setText("notifications\n").setCallbackData("\"notifications\n\" is very technical")));
        keyboardButtonsRow1.add(new InlineKeyboardButton().setText("weatherSPb").setUrl("https://www.gismeteo.ru/weather-sankt-peterburg-4079/"));
        rowList.add(keyboardButtonsRow1);

        inlineKeyboardMarkup.setKeyboard(rowList);
        SendMessage message = new SendMessage().setChatId(chatId).setText("InlineKeyBoard").setReplyMarkup(inlineKeyboardMarkup);
        return message;

    }
}
