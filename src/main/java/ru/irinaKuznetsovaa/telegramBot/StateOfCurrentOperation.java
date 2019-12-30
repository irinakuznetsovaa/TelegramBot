package ru.irinaKuznetsovaa.telegramBot;

public class StateOfCurrentOperation {
    private String status;
    private String name;
    private String tiker;
    private Double currentPrice;
    private Integer count;
    private Double freeMoney;


    public StateOfCurrentOperation(String status){
        this.status=status;
    }

    public String  getStatus() {
        return status;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public Double getFreeMoney() {
        return freeMoney;
    }

    public Integer getCount() {
        return count;
    }

    public String getName() {
        return name;
    }

    public String getTiker() {
        return tiker;
    }

    public void setStatusTransaction(String StateOfCurrentOperation1){
        StatusOfCurrentOperation statusOperation=new StatusOfCurrentOperation();
        if (status.equals(statusOperation.FREE_MONEY)){
            this.freeMoney=Double.valueOf(StateOfCurrentOperation1);
            this.status=statusOperation.NEW;
        }else if((status.equals(statusOperation.NEW))||(status.equals(statusOperation.BUY_ASSETS_OK))||
                (status.equals(statusOperation.SELL_ASSETS_OK))||
                (status.equals(statusOperation.STATE_PORTFOLIO))||(status.equals( statusOperation.SEE_TRANSACTION))) {
            if (StateOfCurrentOperation1.equals(statusOperation.BUY_ASSETS)) {
                this.status=statusOperation.BUY_ASSETS_INDEX;
            }else if(StateOfCurrentOperation1.equals(statusOperation.SELL_ASSETS)){
                this.status=statusOperation.SELL_ASSETS_INDEX;
            }
        }else if(status.equals(statusOperation.BUY_ASSETS_INDEX)){
            this.status=statusOperation.BUY_ASSETS_NAME;
            this.tiker=StateOfCurrentOperation1;
        }else if(status.equals(statusOperation.BUY_ASSETS_NAME)){
            this.status=statusOperation.BUY_ASSETS_COUNT;
            this.name=StateOfCurrentOperation1;
        }else if(status.equals(statusOperation.BUY_ASSETS_COUNT)){
            this.status=statusOperation.BUY_ASSETS_CURRENT_PRICE;
            this.count=Integer.valueOf(StateOfCurrentOperation1);
        }else if(status.equals(statusOperation.BUY_ASSETS_CURRENT_PRICE)){
            this.status=statusOperation.BUY_ASSETS_OK;
            this.currentPrice=Double.parseDouble(StateOfCurrentOperation1);
            this.freeMoney=freeMoney-(count*currentPrice);
        }else if(status.equals(statusOperation.SELL_ASSETS_INDEX)){
            this.status=statusOperation.SELL_ASSETS_NAME;
            this.tiker=StateOfCurrentOperation1;
        }else if(status.equals(statusOperation.SELL_ASSETS_NAME)){
            this.status=statusOperation.SELL_ASSETS_COUNT;
            this.name=StateOfCurrentOperation1;
        }else if(status.equals(statusOperation.SELL_ASSETS_COUNT)){
            this.status=statusOperation.SELL_ASSETS_CURRENT_PRICE;
            this.count=Integer.valueOf(StateOfCurrentOperation1);
        }else if(status.equals(statusOperation.SELL_ASSETS_CURRENT_PRICE)){
            this.status=statusOperation.SELL_ASSETS_OK;
            this.currentPrice=Double.parseDouble(StateOfCurrentOperation1);
            this.freeMoney=freeMoney+(count*currentPrice);
        }










    }
}
