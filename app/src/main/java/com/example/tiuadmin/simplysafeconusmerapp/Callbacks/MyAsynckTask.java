package com.example.tiuadmin.simplysafeconusmerapp.Callbacks;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.tiuadmin.simplysafeconusmerapp.Utility.PrefManager;
import com.example.tiuadmin.simplysafeconusmerapp.Webservices.WebService;

import org.json.JSONObject;

/**
 * Created by tiuadmin on 15/02/17.
 */

public class MyAsynckTask extends AsyncTask<String, Void, String> {

    ProgressDialog dialog;
    Activity context;
    Callback callback;
    String URL;
    PrefManager prefManager;
    public MyAsynckTask(Callback callback,String url, Activity context){
        this.callback=callback;
        this.context=context;
        prefManager = new PrefManager(context);
        URL=url;
    }



    protected void onPreExecute() {
        super.onPreExecute();
        dialog = ProgressDialog.show(context, "Loading", "Please wait...", true);
    }

    protected String doInBackground(String... params) {
        //do download here
      String response=  makeServerRequest(URL);

return response;
    }

    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        callback.onResult(result);
        dialog.dismiss();
    }

    /**
     * Making json object request
     */
    private String makeServerRequest(String url) {
        // new GeneralFunction().showProgressDialog(getActivity());


        String res = null;
        String responseCode = null;
        String returnResponse = null;
        try {



            WebService web = new WebService();
            res = web.getWithHeader(url,prefManager.getToken());
            Log.d(res, res);

            if (res != null && res.length() > 0) {
                JSONObject json = new JSONObject(res);
                if (json != null) {

                    return  res;

                }
            } else {
                Toast.makeText(context, "unable to execut your query,please try agian.", Toast.LENGTH_SHORT).show();
                // new GeneralFunction().hideProgressDialog();
            }

        } catch (Exception e) {
            Toast.makeText(context, "unable to execut your query,please try agian.", Toast.LENGTH_SHORT).show();
            //new GeneralFunction().hideProgressDialog();
            e.printStackTrace();
        }
        return res;
    }
}
