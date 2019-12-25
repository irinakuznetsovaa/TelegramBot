package ru.irinaKuznetsovaa.telegramBot;

import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.*;


public class TelegramBot<userID> extends TelegramLongPollingCommandBot {

        private static final String USERNAME = "@IJistABot";
        private static final String TOKEN = System.getenv("VARIABLE_NAME");
        Map<Integer,SendMessage> sentMessageUserId= new HashMap<Integer, SendMessage>();
        private SendMessage fMessage = new SendMessage().setText("");
        private Integer UserID;
        List<Assets> assetsList=new ArrayList<Assets>();
        List<Transaction> transactionList=new ArrayList<Transaction>();
        private String name;
        private String tiker;
        private float currentPrice;
        private Integer count;
        private String status;
        private float freeMoney=12500;
        private Portfolio portfolio;


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
        UserID = new User().getId();
        sentMessageUserId.put(UserID,fMessage);
        if (inMessage != null && inMessage.hasText()) {
            try {
                if(sentMessageUserId.get(UserID).getText()==""){
                switch (inMessage.getText()) {
                    case "������ �����":
                        outMessage.setChatId(inMessage.getChatId());
                        sentMessageUserId.replace(UserID,fMessage.setText("������� ������ ������ ������� ������ ����������"));
                        outMessage.setText(sentMessageUserId.get(UserID).getText());
                        execute(outMessage);

                        break;
                    case "������� �����":
                        outMessage.setChatId(inMessage.getChatId());
                        sentMessageUserId.replace(UserID,fMessage.setText("������� ������ ������ ������� ������ �������"));
                        outMessage.setText(sentMessageUserId.get(UserID).getText());
                        execute(outMessage);
                        break;

                    case "���������� ������ ���� ����������":
                        outMessage.setChatId(inMessage.getChatId());
                        sentMessageUserId.replace(UserID,fMessage.setText(""));
                        outMessage.setText("��� ����������:");
                        execute(outMessage);
                        for(int i=0;i<transactionList.size();i++){
                            outMessage.setText(transactionList.get(i).getStatus()+":" +
                                    "\n���� :"+transactionList.get(i).getData()+
                                    "\n������ ����� :"+transactionList.get(i).getAssets().getTicker()+
                                    "\n������������ ����� :"+transactionList.get(i).getAssets().getName()+
                                    "\n������� ���� : "+transactionList.get(i).getAssets().getCurrentPrice()+
                                    "\n���������� : "+transactionList.get(i).getCount());
                            execute(outMessage);
                        }

                        break;
                    case "��������� ��������":
                        outMessage.setChatId(inMessage.getChatId());
                        sentMessageUserId.replace(UserID,fMessage.setText(""));
                        outMessage.setText("��������� ��������:");
                        execute(outMessage);
                        portfolio=new Portfolio(assetsList,freeMoney);

                        for(int i=0;i<assetsList.size();i++) {
                            outMessage.setText("������ �����:"+portfolio.getListAssets().get(i).getTicker()+
                                    "\n������������ �����: "+portfolio.getListAssets().get(i).getName()+
                            "\n������� ����: "+portfolio.getListAssets().get(i).getCurrentPrice());
                            execute(outMessage);
                        }
                        outMessage.setText("��������� ������:"+portfolio.getFreeMoney());
                        execute(outMessage);
                        break;
                        default:
                }}else if(sentMessageUserId.get(UserID).getText()=="������� ������ ������ ������� ������ ����������"){
                    status="�������";
                    tiker=inMessage.getText();
                outMessage.setChatId(inMessage.getChatId());
                sentMessageUserId.replace(UserID,fMessage.setText("������� ������������ ������ ������ ������� ������ ���������� :"));
                outMessage.setText(sentMessageUserId.get(UserID).getText());
                execute(outMessage);
                }else if(sentMessageUserId.get(UserID).getText()=="������� ������������ ������ ������ ������� ������ ���������� :"){
                    name=inMessage.getText();
                    outMessage.setChatId(inMessage.getChatId());
                    sentMessageUserId.replace(UserID,fMessage.setText("������� ���������� ������������� ����� :"));
                    outMessage.setText(sentMessageUserId.get(UserID).getText());
                    execute(outMessage);
                }else if(sentMessageUserId.get(UserID).getText()=="������� ���������� ������������� ����� :"){
                    count= Integer.valueOf(inMessage.getText());
                    outMessage.setChatId(inMessage.getChatId());
                    sentMessageUserId.replace(UserID,fMessage.setText("������� ������� ���� �� ��. ���. :"));
                    outMessage.setText(sentMessageUserId.get(UserID).getText());
                    execute(outMessage);
                }else if(sentMessageUserId.get(UserID).getText()=="������� ������� ���� �� ��. ���. :"){
                    currentPrice=Float.parseFloat(inMessage.getText());
                    freeMoney=freeMoney-(currentPrice*count);
                    Assets assets=new Assets(name,tiker,currentPrice);
                    assetsList.add(assets);
                    Date date=new Date();
                    Transaction transaction=new Transaction(status,assets,count, date);
                    transactionList.add(transaction);
                    outMessage.setChatId(inMessage.getChatId());
                    sentMessageUserId.replace(UserID,fMessage.setText(""));
                    outMessage.setText("������ �� ������������ ����� ���������");
                    execute(outMessage);
                }else if(sentMessageUserId.get(UserID).getText()=="������� ������ ������ ������� ������ �������"){
                    status="�������";
                    tiker=inMessage.getText();
                    outMessage.setChatId(inMessage.getChatId());
                    sentMessageUserId.replace(UserID,fMessage.setText("������� ������������ ������ ������ ������� ������ ������� :"));
                    outMessage.setText(sentMessageUserId.get(UserID).getText());
                    execute(outMessage);
                }else if(sentMessageUserId.get(UserID).getText()=="������� ������������ ������ ������ ������� ������ ������� :"){
                    name=inMessage.getText();
                    outMessage.setChatId(inMessage.getChatId());
                    sentMessageUserId.replace(UserID,fMessage.setText("������� ���������� ����������� ����� :"));
                    outMessage.setText(sentMessageUserId.get(UserID).getText());
                    execute(outMessage);
                }else if(sentMessageUserId.get(UserID).getText()=="������� ���������� ����������� ����� :"){
                    count= Integer.valueOf(inMessage.getText());
                    outMessage.setChatId(inMessage.getChatId());
                    sentMessageUserId.replace(UserID,fMessage.setText("������� ������� ���� ����������� ����� �� ��. ���. :"));
                    outMessage.setText(sentMessageUserId.get(UserID).getText());
                    execute(outMessage);
                }else if(sentMessageUserId.get(UserID).getText()=="������� ������� ���� ����������� ����� �� ��. ���. :"){
                    currentPrice=Float.parseFloat(inMessage.getText());
                    freeMoney=freeMoney+(currentPrice*count);
                    Assets assets=new Assets(name,tiker,currentPrice);
                    Date date=new Date();
                    Transaction transaction=new Transaction(status,assets,count, date);
                    transactionList.add(transaction);
                    outMessage.setChatId(inMessage.getChatId());
                    sentMessageUserId.replace(UserID,fMessage.setText(""));
                    outMessage.setText("������ �� ������� ����� ���������");
                    execute(outMessage);}

            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}
