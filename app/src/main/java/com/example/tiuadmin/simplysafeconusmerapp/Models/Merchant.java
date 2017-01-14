package com.example.tiuadmin.simplysafeconusmerapp.Models;


public class Merchant {


    private String id;

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    private String customerID;

    public String getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(String merchant_id) {
        this.merchant_id = merchant_id;
    }

    private String merchant_id;
    private String name;
    private String mobilenumber;

    public String getPOSURL() {
        return POSURL;
    }

    public void setPOSURL(String POSURL) {
        this.POSURL = POSURL;
    }

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

    public String getMerchantType() {
        return MerchantType;
    }

    public void setMerchantType(String merchantType) {
        MerchantType = merchantType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPos_name() {
        return pos_name;
    }

    public void setPos_name(String pos_name) {
        this.pos_name = pos_name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPos_create_at() {
        return pos_create_at;
    }

    public void setPos_create_at(String pos_create_at) {
        this.pos_create_at = pos_create_at;
    }

    public String getPos_demo_expiry_at() {
        return pos_demo_expiry_at;
    }

    public void setPos_demo_expiry_at(String pos_demo_expiry_at) {
        this.pos_demo_expiry_at = pos_demo_expiry_at;
    }

    public String getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(String payment_status) {
        this.payment_status = payment_status;
    }

    private String POSURL;
    private String MerchantType;


    private String status;
    String email;
    String address;
    String pos_name;
    String message;
    String pos_create_at;
    String pos_demo_expiry_at;
    String payment_status;

    public Merchant(String id,String customerID,String merchant_id, String name, String mobilenumber, String POSURL, String merchantType, String status, String email, String address, String pos_name, String message, String pos_create_at, String pos_demo_expiry_at, String payment_status) {
        this.id = id;
        this.customerID=customerID;
        this.merchant_id=merchant_id;
        this.name = name;
        this.mobilenumber = mobilenumber;
        this.POSURL = POSURL;
        MerchantType = merchantType;
        this.status = status;
        this.email = email;
        this.address = address;
        this.pos_name = pos_name;
        this.message = message;
        this.pos_create_at = pos_create_at;
        this.pos_demo_expiry_at = pos_demo_expiry_at;
        this.payment_status = payment_status;
    }












}
