import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.ApiContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;


public class TelegramBot extends TelegramLongPollingBot {

    public TelegramBot(DefaultBotOptions botOptions) {
        super(botOptions);
    }

    public static void main(String[] args){
        ApiContextInitializer.init();
        TelegramBotsApi telegram=new TelegramBotsApi();
        try{
            DefaultBotOptions botOptions= ApiContext.getInstance(DefaultBotOptions.class);
            botOptions.setProxyHost("200.89.178.83");
            botOptions.setProxyPort(80);
            botOptions.setProxyType(DefaultBotOptions.ProxyType.HTTP);
            telegram.registerBot(new TelegramBot(botOptions));
        }catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }


}

    @Override
    public void onUpdateReceived(Update update) {

        Message inMessage = update.getMessage();
        SendMessage outMessage = new SendMessage();
            if (inMessage!=null && inMessage.hasText()) {
                try {
                    setButtons setButtons = new setButtons(outMessage);
                switch (inMessage.getText()) {
                    case "/help":
                        outMessage.setChatId(inMessage.getChatId());
                        outMessage.setText("Чем я могу помочь ?");
                        break;
                    case "/information":
                        outMessage.setChatId(inMessage.getChatId());
                        outMessage.setText("TelegramBot ,который копирует ваше сообщение ");
                        break;
                    case "/start":
                        outMessage.setChatId(inMessage.getChatId());
                        outMessage.setText("Команды: \n" +
                                "/help - помощь \n" +
                                "/information - сведения о TelegramBot\n" +
                                "/start - начало, список команд\n");
                        break;
                    case "Кнопки":
                        sendInlineKeyBoardMessage sendInlineKeyBoardMessage = new sendInlineKeyBoardMessage(update.getMessage().getChatId());
                        execute(sendInlineKeyBoardMessage.sendInlineKeyBoardMessage1());
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
        }else if(update.hasCallbackQuery()){
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

    @Override
    public String getBotUsername() {
        return "@IJustABot";
    }

    @Override
    public String getBotToken() {
        return "965079337:AAGtZ64lq1sqD1xaQk_QofKGzCTY4121Gn4";
    }
}
