package com.example.tiuadmin.simplysafeconusmerapp.Models;


public class Merchant {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobilenumber() {
        return mobilenumber;
    }

    public void setMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    public String getPOSURL() {
        return POSURL;
    }

    public void setPOSURL(String POSURL) {
        this.POSURL = POSURL;
    }

    private String id;
    private String name;
    private String mobilenumber;
    private String POSURL;
    private String MerchantType;

    public Merchant(String id, String name, String mobilenumber, String POSURL,String MerchantType) {
        this.id = id;
        this.name = name;
        this.mobilenumber = mobilenumber;
        this.POSURL = POSURL;
        this.MerchantType=MerchantType;
    }








}
