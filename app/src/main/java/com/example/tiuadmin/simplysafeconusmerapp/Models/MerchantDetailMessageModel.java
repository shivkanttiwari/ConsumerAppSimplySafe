package com.example.tiuadmin.simplysafeconusmerapp.Models;

import java.util.ArrayList;

/**
 * Created by tiuadmin on 07/02/17.
 */

public class MerchantDetailMessageModel {


    Merchant MerchantDetail;

    public Merchant getMerchantDetail() {
        return MerchantDetail;
    }

    public void setMerchantDetail(Merchant merchantDetail) {
        MerchantDetail = merchantDetail;
    }

    public ArrayList<MerchantMessageModel> getMerchantPromotionalMessageArrrya() {
        return MerchantPromotionalMessageArrrya;
    }

    public void setMerchantPromotionalMessageArrrya(ArrayList<MerchantMessageModel> merchantPromotionalMessageArrrya) {
        MerchantPromotionalMessageArrrya = merchantPromotionalMessageArrrya;
    }

    public ArrayList<MerchantTransactionModel> getMerchantTransactionModelArrray() {
        return MerchantTransactionModelArrray;
    }

    public void setMerchantTransactionModelArrray(ArrayList<MerchantTransactionModel> merchantTransactionModelArrray) {
        MerchantTransactionModelArrray = merchantTransactionModelArrray;
    }

    ArrayList<MerchantMessageModel>MerchantPromotionalMessageArrrya;
    ArrayList<MerchantTransactionModel>MerchantTransactionModelArrray;
    public MerchantDetailMessageModel(Merchant merchantDetail, ArrayList<MerchantMessageModel> merchantPromotionalMessageArrrya, ArrayList<MerchantTransactionModel> merchantTransactionModelArrray) {
        MerchantDetail = merchantDetail;
        MerchantPromotionalMessageArrrya = merchantPromotionalMessageArrrya;
        MerchantTransactionModelArrray = merchantTransactionModelArrray;
    }
}
