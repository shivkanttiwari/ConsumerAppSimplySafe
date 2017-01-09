package com.example.tiuadmin.simplysafeconusmerapp.Webservices;



import com.example.tiuadmin.simplysafeconusmerapp.Utility.Const;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by mayur.p on 4/4/2016.
 */
public class WebService {

    OkHttpClient client = new OkHttpClient();
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    public WebService() {
    }

    public String deleteWithoutHeader(String url) throws IOException {
        //RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .delete()
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public String deleteWithHeader(String url) throws IOException {
        //RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .delete()
                .addHeader("Authorization", Const.TOKEN_WITH_BEARER)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public String putWithoutHeader(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .put(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public String putWithHeader(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .put(body)
                .addHeader("Authorization", Const.TOKEN_WITH_BEARER)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public String postWithoutHeader(String url, String json) throws IOException {




        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public String postWithHeader(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("Authorization", Const.TOKEN_WITH_BEARER)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public String getWithoutHeader(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public String getWithHeader(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer "+Const.LOGIN_TOKEN.trim())

                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }


}
