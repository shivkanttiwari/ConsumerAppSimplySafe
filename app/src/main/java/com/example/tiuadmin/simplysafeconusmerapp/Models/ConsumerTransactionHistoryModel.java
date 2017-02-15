package com.example.tiuadmin.simplysafeconusmerapp.Models;

/**
 * Created by tiuadmin on 15/02/17.
 */

public class ConsumerTransactionHistoryModel {

    String id;
    String CustomerID;
    String MerchantID;
    String MerchantName;

    public String getPoints() {
        return Points;
    }

    public void setPoints(String points) {
        Points = points;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String customerID) {
        CustomerID = customerID;
    }

    public String getMerchantID() {
        return MerchantID;
    }

    public void setMerchantID(String merchantID) {
        MerchantID = merchantID;
    }

    public String getMerchantName() {
        return MerchantName;
    }

    public void setMerchantName(String merchantName) {
        MerchantName = merchantName;
    }

    public String getMerchantOrderID() {
        return MerchantOrderID;
    }

    public void setMerchantOrderID(String merchantOrderID) {
        MerchantOrderID = merchantOrderID;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getRewardOrderID() {
        return RewardOrderID;
    }

    public void setRewardOrderID(String rewardOrderID) {
        RewardOrderID = rewardOrderID;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    String Points;
    String MerchantOrderID;
    String Type;
    String RewardOrderID;
    String Remark;
    public ConsumerTransactionHistoryModel(String id, String customerID, String merchantID, String merchantName, String points, String merchantOrderID, String type, String rewardOrderID, String remark) {
        this.id = id;
        CustomerID = customerID;
        MerchantID = merchantID;
        MerchantName = merchantName;
        Points = points;
        MerchantOrderID = merchantOrderID;
        Type = type;
        RewardOrderID = rewardOrderID;
        Remark = remark;
    }


}
