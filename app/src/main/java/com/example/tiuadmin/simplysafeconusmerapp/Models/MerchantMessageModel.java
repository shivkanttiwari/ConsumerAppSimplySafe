package com.example.tiuadmin.simplysafeconusmerapp.Models;

/**
 * Created by shivkanttiwari on 30/01/17.
 */

public class MerchantMessageModel  {

    public MerchantMessageModel(String merchantMessageID, String merchantMessage) {
        MerchantMessageID = merchantMessageID;
        MerchantMessage = merchantMessage;
    }

    public String getMerchantMessageID() {
        return MerchantMessageID;
    }

    public void setMerchantMessageID(String merchantMessageID) {
        MerchantMessageID = merchantMessageID;
    }

    public String getMerchantMessage() {
        return MerchantMessage;
    }

    public void setMerchantMessage(String merchantMessage) {
        MerchantMessage = merchantMessage;
    }

    String MerchantMessageID;
    String MerchantMessage;


}
