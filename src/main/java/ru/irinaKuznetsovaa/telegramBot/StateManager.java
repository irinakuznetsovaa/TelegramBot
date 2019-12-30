package ru.irinaKuznetsovaa.telegramBot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StateManager {
    private Map<Integer,StateOfCurrentOperation>StateOfCurrentOperationID=new HashMap<>();
    private Portfolio portfolio;
    private StatusOfCurrentOperation state=new StatusOfCurrentOperation();
    private Map<Integer,Portfolio>PortfolioID=new HashMap<>();


    public String TransactionStateManager(Integer userID, String StateOfCurrentOperation) {
       StatusOfCurrentOperation statusOfCurrentOperation = new StatusOfCurrentOperation();
       if (StateOfCurrentOperationID.containsKey(userID) == false) {
           StateOfCurrentOperation stateOfCurrentOperation = new StateOfCurrentOperation(statusOfCurrentOperation.FREE_MONEY);
           StateOfCurrentOperationID.put(userID, stateOfCurrentOperation);
       }

       if(StateOfCurrentOperation.equals(statusOfCurrentOperation.SEE_TRANSACTION)){
           return  SeeTransaction(userID);
       }else if(StateOfCurrentOperation.equals(statusOfCurrentOperation.STATE_PORTFOLIO)){
           return SeeStatePortfolio(userID);
       }else {
           StateOfCurrentOperationID.get(userID).setStatusTransaction(StateOfCurrentOperation);
           if(StateOfCurrentOperationID.get(userID).getStatus().equals(statusOfCurrentOperation.BUY_ASSETS_OK)) {
               if(PortfolioID.containsKey(userID)==false) {
                   Assets assets=new Assets(StateOfCurrentOperationID.get(userID).getName(),
                           StateOfCurrentOperationID.get(userID).getTiker(),
                           StateOfCurrentOperationID.get(userID).getCurrentPrice());
                   List<Assets> assetsList=new ArrayList<Assets>();
                   assetsList.add(assets);
                   List<Transaction> transactionList=new ArrayList<Transaction>();
                   Transaction transaction=new Transaction(StateOfCurrentOperationID.get(userID).getStatus(),assets,
                           StateOfCurrentOperationID.get(userID).getCount()
                           ,StateOfCurrentOperationID.get(userID).getCurrentPrice());
                   transactionList.add(transaction);
                   PortfolioID.put(userID, portfolio = new Portfolio(assetsList,transactionList,StateOfCurrentOperationID.get(userID).getFreeMoney()));
               }else{
                   Assets assets=new Assets(StateOfCurrentOperationID.get(userID).getName(),
                           StateOfCurrentOperationID.get(userID).getTiker(),
                           StateOfCurrentOperationID.get(userID).getCurrentPrice());
                   List<Assets> assetsList=PortfolioID.get(userID).getListAssets();
                   assetsList.add(assets);
                   List<Transaction> transactionList=PortfolioID.get(userID).getListTransaction();
                   Transaction transaction=new Transaction(StateOfCurrentOperationID.get(userID).getStatus(),assets,
                           StateOfCurrentOperationID.get(userID).getCount()
                           ,StateOfCurrentOperationID.get(userID).getCurrentPrice());
                   transactionList.add(transaction);
                   PortfolioID.replace(userID, portfolio = new Portfolio(assetsList,transactionList,StateOfCurrentOperationID.get(userID).getFreeMoney()));
               }
           }else if(StateOfCurrentOperationID.get(userID).getStatus().equals(statusOfCurrentOperation.SELL_ASSETS_OK)){
               Assets assets=new Assets(StateOfCurrentOperationID.get(userID).getName(),
                       StateOfCurrentOperationID.get(userID).getTiker(),
                       StateOfCurrentOperationID.get(userID).getCurrentPrice());
               List<Assets> assetsList=PortfolioID.get(userID).getListAssets();
               List<Transaction> transactionList=PortfolioID.get(userID).getListTransaction();
               Transaction transaction=new Transaction(StateOfCurrentOperationID.get(userID).getStatus(),assets,
                       StateOfCurrentOperationID.get(userID).getCount()
                       ,StateOfCurrentOperationID.get(userID).getCurrentPrice());
               transactionList.add(transaction);
               PortfolioID.replace(userID, portfolio = new Portfolio(assetsList,transactionList,StateOfCurrentOperationID.get(userID).getFreeMoney()));
               }
           return StateOfCurrentOperationID.get(userID).getStatus();
           }

       }

    public Map<Integer, StateOfCurrentOperation> getStateOfCurrentOperationID() {
        return StateOfCurrentOperationID;
    }
    public String SeeTransaction(Integer userID){
       String SeeTransaction="Все транзакции:\n";
       for(int i=0;i<PortfolioID.get(userID).getListTransaction().size();i++){
           SeeTransaction=SeeTransaction+"\n"+PortfolioID.get(userID).getListTransaction().get(i).getStatus()+":" +
                   "\nДата :"+PortfolioID.get(userID).getListTransaction().get(i).getData()+
                   "\nИндекс акции :"+PortfolioID.get(userID).getListTransaction().get(i).getAssets().getTicker()+
                   "\nНаименование акции :"+PortfolioID.get(userID).getListTransaction().get(i).getAssets().getName()+
                   "\nТекущая цена : "+PortfolioID.get(userID).getListTransaction().get(i).getAssets().getCurrentPrice()+
                   "\nКоличество : "+PortfolioID.get(userID).getListTransaction().get(i).getCount()+
                   "\nЦена : "+PortfolioID.get(userID).getListTransaction().get(i).getPrice();
       }
       SeeTransaction=SeeTransaction+"\n";
       return SeeTransaction;
    }
    public String SeeStatePortfolio(Integer userID){
       String SeeStatePortfolio="Cостояние портфеля\n";
       for(int i=0;i<PortfolioID.get(userID).getListAssets().size();i++){
           SeeStatePortfolio=SeeStatePortfolio+"\nИндекс акции:"+PortfolioID.get(userID).getListAssets().get(i).getTicker()+
                   "\nНаименование акции: "+PortfolioID.get(userID).getListAssets().get(i).getName()+
                   "\nТекущая цена: "+PortfolioID.get(userID).getListAssets().get(i).getCurrentPrice();
       }
       SeeStatePortfolio=SeeStatePortfolio+"\nСвободные деньги : "+PortfolioID.get(userID).getFreeMoney();
       return SeeStatePortfolio;
    }

}
