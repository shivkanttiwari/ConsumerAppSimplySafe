package com.example.tiuadmin.simplysafeconusmerapp.Models;

/**
 * Created by tiuadmin on 01/02/17.
 */

public class MerchantTransactionModel {
    public MerchantTransactionModel(String messageID, String merchantMessageID, String merchantMessageHeading, String merchantMessage, String merchantMessageImage,String DateTime) {
        MessageID = messageID;
        MerchantMessageID = merchantMessageID;
        MerchantMessageHeading = merchantMessageHeading;
        MerchantMessage = merchantMessage;
        MerchantMessageImage = merchantMessageImage;
        this.DateTime=DateTime;
    }

    public String getMessageID() {
        return MessageID;
    }

    public void setMessageID(String messageID) {
        MessageID = messageID;
    }

    public String getMerchantMessageID() {
        return MerchantMessageID;
    }

    public void setMerchantMessageID(String merchantMessageID) {
        MerchantMessageID = merchantMessageID;
    }

    public String getMerchantMessageHeading() {
        return MerchantMessageHeading;
    }

    public void setMerchantMessageHeading(String merchantMessageHeading) {
        MerchantMessageHeading = merchantMessageHeading;
    }

    public String getMerchantMessage() {
        return MerchantMessage;
    }

    public void setMerchantMessage(String merchantMessage) {
        MerchantMessage = merchantMessage;
    }

    public String getMerchantMessageImage() {
        return MerchantMessageImage;
    }

    public void setMerchantMessageImage(String merchantMessageImage) {
        MerchantMessageImage = merchantMessageImage;
    }

    String MessageID;
    String MerchantMessageID;
    String MerchantMessageHeading;
    String MerchantMessage;
    String MerchantMessageImage;

    public String getDateTime() {
        return DateTime;
    }

    public void setDateTime(String dateTime) {
        DateTime = dateTime;
    }

    String DateTime;
}
