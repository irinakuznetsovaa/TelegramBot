package ru.irinaKuznetsovaa.telegramBot;

import java.util.HashMap;
import java.util.Map;

public class StateManager {
    public Map<Integer,String>StateOfCurrentOperationID=new HashMap<>();

   public String StateManager(Integer userID,String StateOfCurrentOperation) {
       StateOfCurrentOperation stateOfCurrentOperation = new StateOfCurrentOperation();
       if (StateOfCurrentOperationID.containsKey(userID) == false) {
           StateOfCurrentOperationID.put(userID, stateOfCurrentOperation.getFreeMoney());
           if(StateOfCurrentOperation.equals(stateOfCurrentOperation.getBuyAssets())){
               StateOfCurrentOperationID.replace(userID, stateOfCurrentOperation.getBuyAssets());
           }

       }
       if (StateOfCurrentOperationID.containsKey(userID) == true) {
           if((StateOfCurrentOperation.equals(stateOfCurrentOperation.getSeeTransaction()))
                   ||(StateOfCurrentOperation.equals(stateOfCurrentOperation.getStatePortfolio()))){
               StateOfCurrentOperationID.replace(userID, stateOfCurrentOperation.getNew());
           }



           if(StateOfCurrentOperationID.get(userID).equals(stateOfCurrentOperation.getBuyAssetsOk())||
                   (StateOfCurrentOperationID.get(userID).equals(stateOfCurrentOperation.getSellAssetsOk()))||
                   (StateOfCurrentOperationID.get(userID).equals( stateOfCurrentOperation.getNew()))
                           &&(StateOfCurrentOperation.equals(stateOfCurrentOperation.getSellAssets()))){
               StateOfCurrentOperationID.replace(userID, stateOfCurrentOperation.getSellAssets());
           }
           if(((StateOfCurrentOperationID.get(userID).equals( stateOfCurrentOperation.getNew())) ||
                   (StateOfCurrentOperationID.get(userID).equals(stateOfCurrentOperation.getBuyAssetsOk()))||
                   (StateOfCurrentOperationID.get(userID).equals(stateOfCurrentOperation.getSellAssetsOk()))||
                   (StateOfCurrentOperationID.get(userID).equals( stateOfCurrentOperation.getBuyAssets()))||
                   (StateOfCurrentOperationID.get(userID).equals( stateOfCurrentOperation.getSellAssets())))
                   &&(StateOfCurrentOperation.equals(stateOfCurrentOperation.getBuyAssets())))
           {
               StateOfCurrentOperationID.replace(userID, stateOfCurrentOperation.getBuyAssets());
           }


           if (StateOfCurrentOperationID.get(userID).equals(stateOfCurrentOperation.getBuyAssets())) {
               StateOfCurrentOperationID.replace(userID, stateOfCurrentOperation.getBuyAssetsIndex());
           }else if(StateOfCurrentOperationID.get(userID).equals(stateOfCurrentOperation.getBuyAssetsIndex())) {
               StateOfCurrentOperationID.replace(userID, stateOfCurrentOperation.getBuyAssetsName());
           } else if (StateOfCurrentOperationID.get(userID).equals(stateOfCurrentOperation.getBuyAssetsName())) {
               StateOfCurrentOperationID.replace(userID, stateOfCurrentOperation.getBuyAssetsCount());
           } else if (StateOfCurrentOperationID.get(userID).equals( stateOfCurrentOperation.getBuyAssetsCount())) {
               StateOfCurrentOperationID.replace(userID, stateOfCurrentOperation.getBuyAssetsCurrentPrice());
           } else if (StateOfCurrentOperationID.get(userID).equals(stateOfCurrentOperation.getBuyAssetsCurrentPrice()) ) {
               StateOfCurrentOperationID.replace(userID, stateOfCurrentOperation.getBuyAssetsOk());

           }else if (StateOfCurrentOperationID.get(userID).equals(stateOfCurrentOperation.getSellAssets())){
               StateOfCurrentOperationID.replace(userID,stateOfCurrentOperation.getSellAssetsIndex());
           }else if (StateOfCurrentOperationID.get(userID).equals(stateOfCurrentOperation.getSellAssetsIndex())){
               StateOfCurrentOperationID.replace(userID,stateOfCurrentOperation.getSellAssetsName());
           }else if (StateOfCurrentOperationID.get(userID).equals(stateOfCurrentOperation.getSellAssetsName())){
               StateOfCurrentOperationID.replace(userID,stateOfCurrentOperation.getSellAssetsCount());
           }else if (StateOfCurrentOperationID.get(userID).equals( stateOfCurrentOperation.getSellAssetsCount())) {
               StateOfCurrentOperationID.replace(userID, stateOfCurrentOperation.getSellAssetsCurrentPrice());
           } else if (StateOfCurrentOperationID.get(userID).equals(stateOfCurrentOperation.getSellAssetsCurrentPrice()) ) {
               StateOfCurrentOperationID.replace(userID, stateOfCurrentOperation.getSellAssetsOk());
           }else if((StateOfCurrentOperation.equals(stateOfCurrentOperation.getStatePortfolio()))) {
               StateOfCurrentOperationID.replace(userID, stateOfCurrentOperation.getFreeMoney());
           }else if(StateOfCurrentOperationID.get(userID).equals(stateOfCurrentOperation.getFreeMoney())){
               StateOfCurrentOperationID.replace(userID, stateOfCurrentOperation.getNew());
           }
       }
           return StateOfCurrentOperationID.get(userID);

   }

    public Map<Integer, String> getStateOfCurrentOperationID() {
        return StateOfCurrentOperationID;
    }

}
