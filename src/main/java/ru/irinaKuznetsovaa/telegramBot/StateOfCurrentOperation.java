package ru.irinaKuznetsovaa.telegramBot;

public class StateOfCurrentOperation {

    private static final String FREE_MONEY="��������� ��� ��������...������� �������� ";
    private static final String BUY_ASSETS="������ �����";
    private static final String BUY_ASSETS_INDEX="������� ������ ������ ������� ������ ����������";
    private static final String BUY_ASSETS_NAME="������� ������������ ������ ������ ������� ������ ���������� :";
    private static final String BUY_ASSETS_COUNT="������� ���������� ������������� ����� :";
    private static final String BUY_ASSETS_CURRENT_PRICE="������� ������� ���� �� ��. ���. :";
    private static final String BUY_ASSETS_OK="������ �� ������������ ����� ���������";
    private static final String SELL_ASSETS="������� �����";
    private static final String SELL_ASSETS_INDEX="������� ������ ������ ������� ������ �������";
    private static final String SELL_ASSETS_NAME="������� ������������ ������ ������ ������� ������ ������� :";
    private static final String SELL_ASSETS_COUNT="������� ���������� ����������� ����� :";
    private static final String SELL_ASSETS_CURRENT_PRICE="������� ������� ���� ����������� ����� �� ��. ���. :";
    private static final String SELL_ASSETS_OK="������ �� ������� ����� ���������";
    private static final String NEW="";
    private static final String STATE_PORTFOLIO="��������� ��������";
    private static final String SEE_TRANSACTION="���������� ������ ���� ����������";

    public static String getFreeMoney(){
        return FREE_MONEY;
    }
    public static String getBuyAssets(){
        return BUY_ASSETS;
    }
    public static String getBuyAssetsIndex(){
        return BUY_ASSETS_INDEX;
    }
    public static String getBuyAssetsName(){
        return BUY_ASSETS_NAME;
    }
    public static String getBuyAssetsCount(){
        return BUY_ASSETS_COUNT;
    }
    public static String getBuyAssetsCurrentPrice(){
        return BUY_ASSETS_CURRENT_PRICE;
    }

    public static String getBuyAssetsOk() {
        return BUY_ASSETS_OK;
    }

    public static String getSellAssets() {
        return SELL_ASSETS;
    }

    public static String getSellAssetsCount() {
        return SELL_ASSETS_COUNT;
    }

    public static String getSellAssetsCurrentPrice() {
        return SELL_ASSETS_CURRENT_PRICE;
    }

    public static String getSellAssetsIndex() {
        return SELL_ASSETS_INDEX;
    }

    public static String getSellAssetsName() {
        return SELL_ASSETS_NAME;
    }

    public static String getSellAssetsOk() {
        return SELL_ASSETS_OK;
    }
    public static String getNew(){
        return NEW;
    }

    public static String getStatePortfolio() {
        return STATE_PORTFOLIO;
    }

    public static String getSeeTransaction() {
        return SEE_TRANSACTION;
    }
}

