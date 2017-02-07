package com.example.tiuadmin.simplysafeconusmerapp.Utility;

import com.example.tiuadmin.simplysafeconusmerapp.Models.Merchant;
import com.example.tiuadmin.simplysafeconusmerapp.Models.MerchantDetailMessageModel;

import java.util.ArrayList;

public class Const {
	public static final String URL_JSON_OBJECT = "http://52.66.101.233/Customer-Backend/public/api/user/signup";
	public static final String URL_JSON_ARRAY = "http://api.androidhive.info/volley/person_array.json";
	public static final String URL_STRING_REQ = "http://api.androidhive.info/volley/string_response.html";
	public static final String URL_IMAGE = "http://api.androidhive.info/volley/volley-image.jpg";
	public static String LOGIN_TOKEN ="";
	public static String TOKEN_WITH_BEARER = "Bearer "+LOGIN_TOKEN;
	public static String DEVICE_TOKEN = "";

	public static String SIGNUP_TOKEN ="";
	public static String ForgetPassword_TOKEN ="";

	public static String USER_ID = "";
	public static String USER_NAME = "";
	public static String USER_MOBILENUMBER = "";
	public static String USER_EMAIL = "";

	public static ArrayList<Merchant> MERCHANT_DATA = new ArrayList<>();


	public static String IS_MAIN_DASHBOARD_VIEW = "1";
	public static String IS_MAIN_UTILITY_VIEW = "2";
	public static String IS_UTILITY_ENTERTINMENT_VIEW = "3";
	public static String IS_UTILITY_ENTERTINMENT_CRICKET_VIEW = "4";



	public static String OTP_VERIFICATION_MOUDLE_ID_FOR_SMS = "0";

	public static String MAIN_URL = "http://52.66.101.233/Customer-Backend/public/api/v1/customer/merchant/";

	public static String MERCHANT_DELETE_API = MAIN_URL+"delete";
	public static String REGISTER_DEVICE_FCM = MAIN_URL+"device/register";



	//******Arrays*******
	public static String MerchantID_Selected_For_Detial="";
	public static ArrayList<MerchantDetailMessageModel>merchantDetailMessageModels=new ArrayList<>();


}
