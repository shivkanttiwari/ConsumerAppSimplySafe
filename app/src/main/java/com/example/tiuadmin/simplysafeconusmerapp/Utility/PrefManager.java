package com.example.tiuadmin.simplysafeconusmerapp.Utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * Created by shivkant .
 */
public class PrefManager {
    // Shared Preferences
    public SharedPreferences pref;

    // Editor for Shared preferences
    Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "SSConusumerAPP";

    // All Shared Preferences Keys
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    private static final String KEY_TOKEN = "token";
    private static final String KEY_REFRESH_TOKEN = "refresh_token";
    private static final String KEY_USER_ID = "user_id";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_NAME = "name";

    private static final String KEY_EMAIL = "email";
    private static final String KEY_MOBILE_NUMBER = "mobile_number";



    private static final String KEY_USER_CITY = "user_city";
    private static final String KEY_PROFILE_URL = "profile_url";

    private static final String LATITUDE = "latitude";
    private static final String LONGITUDE = "longitude";
    public static final String PLACE_NAME = "place_name";
    private static final String HAS_PLACE = "has_place";


    public PrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createLogin( String userId, String username,
                            String name, String email, String number,String city,String profileurl) {

        editor.putBoolean(KEY_IS_LOGGED_IN, true);
        editor.putString(KEY_USER_ID, userId);
        editor.putString(KEY_USERNAME, username);
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_MOBILE_NUMBER, number);
        editor.putString(KEY_USER_CITY, city);
        editor.putString(KEY_PROFILE_URL, profileurl);
        editor.commit();
    }

    public void clearSession() {
        editor.clear();
        editor.commit();
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    public void loggout() {
        editor.putBoolean(KEY_IS_LOGGED_IN, false);
        editor.commit();
    }

    public String getRefreshToken() {
        return pref.getString(KEY_REFRESH_TOKEN, "");
    }

    public void setRefreshToken(String token) {
        editor.putString(KEY_REFRESH_TOKEN, token);
        editor.commit();
    }

    public String getToken() {
        return pref.getString(KEY_TOKEN, "");
    }

    public void setToken(String token) {
        editor.putString(KEY_TOKEN, token);
        editor.commit();
    }

    public String getUserId() {
        return pref.getString(KEY_USER_ID, "");
    }

    public String getUserLocaiton() {
        return pref.getString(KEY_USER_CITY, "");
    }

    public static String getKeyUserCity() {
        return KEY_USER_CITY;
    }

    public void setUserId(String userId) {
        editor.putString(KEY_USER_ID, userId);
        editor.commit();
    }

    public String getUsername() {
        return pref.getString(KEY_USERNAME, "");
    }



    public void setUsername(String usetname) {
        editor.putString(KEY_USERNAME, usetname);
        editor.commit();
    }

    public String getName() {
        return pref.getString(KEY_NAME, "");
    }

    public void setName(String name) {
        editor.putString(KEY_NAME, name);
        editor.commit();
    }


    public String getemail() {
        return pref.getString(KEY_EMAIL, "");
    }

    public void setEmail(String name) {
        editor.putString(KEY_EMAIL, name);
        editor.commit();
    }


    public String getMobileNumber() {
        return pref.getString(KEY_MOBILE_NUMBER, "");
    }

    public void setMobileNumber(String name) {
        editor.putString(KEY_MOBILE_NUMBER, name);
        editor.commit();
    }

//*************Location*********

    public String getLatitude() {
        return pref.getString(LATITUDE, "");
    }

    public void setLatitude(String latitude) {
        editor.putString(LATITUDE, latitude);
        editor.commit();
    }


    public String getLongitude() {
        return pref.getString(LONGITUDE, "");
    }

    public void setLongitude(String longitude) {
        editor.putString(LONGITUDE, longitude);
        editor.commit();
    }


    public String getPlaceName() {
        return pref.getString(PLACE_NAME, "");
    }

    public void setPlaceName(String placeName) {
        editor.putString(PLACE_NAME, placeName);
        editor.putBoolean(HAS_PLACE, true);
        editor.commit();
    }

    public boolean hasPlace() {
        return pref.getBoolean(HAS_PLACE, false);
    }

    public String getProfileUrl() {
        return pref.getString(KEY_PROFILE_URL, "");
    }

    public void setProfileUrl(String url) {
        editor.putString(KEY_PROFILE_URL, url);
        editor.commit();
    }

    //*************Location*********
}
