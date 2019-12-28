package ru.irinaKuznetsovaa.telegramBot;

import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.math.BigDecimal;
import java.util.*;


public class TelegramBot<userID> extends TelegramLongPollingCommandBot {

        private static final String USERNAME = "@IJistABot";
        private static final String TOKEN = System.getenv("VARIABLE_NAME");
        private Integer UserID;
        List<Assets> assetsList=new ArrayList<Assets>();
        List<Transaction> transactionList=new ArrayList<Transaction>();
        private String name;
        private String tiker;
        private Double currentPrice;
        private Integer count;
        private String status;
        private Double freeMoney;
        private Portfolio portfolio;
        public StateManager stateManager=new StateManager();
        private StateOfCurrentOperation state=new StateOfCurrentOperation();

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
                if((stateManager.getStateOfCurrentOperationID().isEmpty()==true)
                        ||((stateManager.getStateOfCurrentOperationID().containsKey(UserID)==false))
                        ||(stateManager.getStateOfCurrentOperationID().get(UserID).equals(state.getBuyAssetsOk()))
                        ||(stateManager.getStateOfCurrentOperationID().get(UserID).equals(state.getSellAssetsOk()))
                        ||(stateManager.getStateOfCurrentOperationID().get(UserID).equals(state.getNew()))
                        ||(stateManager.getStateOfCurrentOperationID().get(UserID).equals(state.getFreeMoney()))){
                    if(stateManager.getStateOfCurrentOperationID().isEmpty()==true){
                        freeMoney=Double.valueOf(inMessage.getText());
                        outMessage.setChatId(inMessage.getChatId());
                        outMessage.setText(stateManager.StateManager(UserID,inMessage.getText()));
                        execute(outMessage);

                    }else{
                switch (inMessage.getText()) {
                    case "Купить акции":
                        outMessage.setChatId(inMessage.getChatId());
                        outMessage.setText(stateManager.StateManager(UserID,inMessage.getText()));
                        execute(outMessage);
                        break;
                    case "Продать акции":
                        outMessage.setChatId(inMessage.getChatId());
                        outMessage.setText(stateManager.StateManager(UserID,inMessage.getText()));
                        execute(outMessage);
                        break;

                    case "Посмотреть список всех транзакций":
                        outMessage.setChatId(inMessage.getChatId());
                        stateManager.StateManager(UserID,inMessage.getText());
                        outMessage.setText("Все транзакции:");
                        execute(outMessage);
                        for(int i=0;i<transactionList.size();i++){
                            outMessage.setText(transactionList.get(i).getStatus()+":" +
                                    "\nДата :"+transactionList.get(i).getData()+
                                    "\nИндекс акции :"+transactionList.get(i).getAssets().getTicker()+
                                    "\nНаименование акции :"+transactionList.get(i).getAssets().getName()+
                                    "\nТекущая цена : "+transactionList.get(i).getAssets().getCurrentPrice()+
                                    "\nКоличество : "+transactionList.get(i).getCount());
                            execute(outMessage);
                        }

                        break;
                    case "Состояние портфеля":
                        outMessage.setText("Состояние портфеля");
                        execute(outMessage);
                        portfolio=new Portfolio(assetsList,freeMoney);
                        for(int i=0;i<assetsList.size();i++) {
                            outMessage.setText("Индекс акции:"+portfolio.getListAssets().get(i).getTicker()+
                                    "\nНаименование акции: "+portfolio.getListAssets().get(i).getName()+
                                    "\nТекущая цена: "+portfolio.getListAssets().get(i).getCurrentPrice());
                            execute(outMessage);
                        }
                        outMessage.setText("Свободные деньги:"+portfolio.getFreeMoney());
                        execute(outMessage);
                        break;
                        default:
                }}}else if(stateManager.getStateOfCurrentOperationID().get(UserID).equals(state.getBuyAssetsIndex())){
                    status="Куплено";
                    tiker=inMessage.getText();
                outMessage.setChatId(inMessage.getChatId());
                outMessage.setText(stateManager.StateManager(UserID,inMessage.getText()));
                execute(outMessage);
                }else if(stateManager.getStateOfCurrentOperationID().get(UserID).equals(state.getBuyAssetsName())){
                    name=inMessage.getText();
                    outMessage.setChatId(inMessage.getChatId());
                    outMessage.setText(stateManager.StateManager(UserID,inMessage.getText()));
                    execute(outMessage);
                }else if(stateManager.getStateOfCurrentOperationID().get(UserID).equals(state.getBuyAssetsCount())){
                    count= Integer.valueOf(inMessage.getText());
                    outMessage.setChatId(inMessage.getChatId());
                    outMessage.setText(stateManager.StateManager(UserID,inMessage.getText()));
                    execute(outMessage);
                }else if(stateManager.getStateOfCurrentOperationID().get(UserID).equals(state.getBuyAssetsCurrentPrice())){
                    currentPrice=Double.parseDouble(inMessage.getText());
                    freeMoney=freeMoney - (Double.parseDouble(inMessage.getText())*count);
                    Assets assets=new Assets(name,tiker,currentPrice);
                    assetsList.add(assets);
                    Date date=new Date();
                    Transaction transaction=new Transaction(status,assets,count, date);
                    transactionList.add(transaction);
                    outMessage.setChatId(inMessage.getChatId());
                    outMessage.setText(stateManager.StateManager(UserID,inMessage.getText()));
                    execute(outMessage);
                }else if(stateManager.getStateOfCurrentOperationID().get(UserID).equals(state.getSellAssetsIndex())){
                    status="Продано";
                    tiker=inMessage.getText();
                    outMessage.setChatId(inMessage.getChatId());
                    outMessage.setText(stateManager.StateManager(UserID,inMessage.getText()));
                    execute(outMessage);
                }else if(stateManager.getStateOfCurrentOperationID().get(UserID).equals(state.getSellAssetsName())){
                    name=inMessage.getText();
                    outMessage.setChatId(inMessage.getChatId());
                    outMessage.setText(stateManager.StateManager(UserID,inMessage.getText()));
                    execute(outMessage);
                }else if(stateManager.getStateOfCurrentOperationID().get(UserID).equals(state.getSellAssetsCount())){
                    count= Integer.valueOf(inMessage.getText());
                    outMessage.setChatId(inMessage.getChatId());
                    outMessage.setText(stateManager.StateManager(UserID,inMessage.getText()));
                    execute(outMessage);
                }else if(stateManager.getStateOfCurrentOperationID().get(UserID).equals(state.getSellAssetsCurrentPrice())){
                    currentPrice=Double.parseDouble(inMessage.getText());
                    freeMoney=freeMoney+(currentPrice*count);
                    Assets assets=new Assets(name,tiker,currentPrice);
                    Date date=new Date();
                    Transaction transaction=new Transaction(status,assets,count, date);
                    transactionList.add(transaction);
                    outMessage.setChatId(inMessage.getChatId());
                    outMessage.setText(stateManager.StateManager(UserID,inMessage.getText()));
                    execute(outMessage);}


            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}
