package com.example.tiuadmin.simplysafeconusmerapp.User;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tiuadmin.simplysafeconusmerapp.Models.Merchant;
import com.example.tiuadmin.simplysafeconusmerapp.R;
import com.example.tiuadmin.simplysafeconusmerapp.Utility.CompressImage;
import com.example.tiuadmin.simplysafeconusmerapp.Utility.Const;
import com.example.tiuadmin.simplysafeconusmerapp.Utility.GeneralFunction;
import com.example.tiuadmin.simplysafeconusmerapp.Webservices.WebService;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import static android.R.id.message;
import static com.example.tiuadmin.simplysafeconusmerapp.R.drawable.phone;

public class UserProfileActivity extends AppCompatActivity implements View.OnClickListener {

    EditText ed_FullName, ed_Email, ed_MobilNumber, ed_Address;
    //ImageView img_Profilepic;
    private static Animation shakeAnimation;
    Button btn_Save;
    ImageView img_Enable_Edit_Porfile;
    ImageView img_profilepic;

    //Select image
    private final int REQUEST_CODE_FROM_GALLERY = 01;
    private final int REQUEST_CODE_CLICK_IMAGE = 02;
    CompressImage compressImage;
    private String imgPath;
    String image1Base64 = null;
    AlertDialog alertdialog;
    private String selectedImagePath = "";
    String status;//=json.getString("status");;;
    String Status_ProfileUPdate;//=json.getString("status");;;

    String fullname;//=ed_FullName.getText().toString();
    String email;//=ed_Email.getText().toString();
    String mobile;//=ed_MobilNumber.getText().toString();
    String address;//=ed_Address.getText().toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        initViews();
        setListeners();
        makeUserDetailRequest();
    }

    // Initiate Views
    private void initViews() {


        ed_FullName = (EditText) findViewById(R.id.user_pro_fullname_edittext);
        ed_Email = (EditText) findViewById(R.id.user_pro_email_editText);
        ed_MobilNumber = (EditText) findViewById(R.id.user_pro_mobno_editText);
        ed_Address = (EditText) findViewById(R.id.user_pro_address_editText);
        img_Enable_Edit_Porfile=(ImageView)findViewById(R.id.user_pro_edit_imageView);
        btn_Save = (Button) findViewById(R.id.btn_Save);
        img_profilepic=(ImageView)findViewById(R.id.user_pro_img_imageView);
        // Load ShakeAnimation
        shakeAnimation = AnimationUtils.loadAnimation(UserProfileActivity.this,
                R.anim.shake);

        ed_FullName.setEnabled(false);
        ed_Email.setEnabled(false);;//, ed_MobilNumber, ed_Address;
        ed_MobilNumber.setEnabled(false);
        ed_Address.setEnabled(false);

        // Setting text selector over textviews
        XmlResourceParser xrp = getResources().getXml(R.drawable.text_selector);
        try {
            ColorStateList csl = ColorStateList.createFromXml(getResources(),
                    xrp);

            ed_FullName.setTextColor(csl);
            ed_Email.setTextColor(csl);
            ed_MobilNumber.setTextColor(csl);
            ed_Address.setTextColor(csl);
            //	show_hide_password.setTextColor(csl);
           // btn_Save.setTextColor(csl);
        } catch (Exception e) {
        }


    }

    // Set Listeners
    private void setListeners() {
        btn_Save.setOnClickListener(this);
        img_Enable_Edit_Porfile.setOnClickListener(this);
        img_profilepic.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_button1:
                Toast.makeText(getApplicationContext(), "Save Clicked", Toast.LENGTH_SHORT).show();
                //getActivity().startActivity(new Intent(getActivity(), DrawerActivity.class));

                //getActivity().finish();
                break;

            case R.id.user_pro_edit_imageView:
                ed_FullName.setEnabled(true);
                ed_Email.setEnabled(true);;//, ed_MobilNumber, ed_Address;
                ed_MobilNumber.setEnabled(true);
                ed_Address.setEnabled(true);

                btn_Save.setVisibility(View.VISIBLE);
                break;
            case R.id.user_pro_img_imageView:
                Toast.makeText(getApplicationContext(), "Save user_pro_img_imageView", Toast.LENGTH_SHORT).show();

                final int REQUEST_EXTERNAL_STORAGE = 1;
                String[] PERMISSIONS_STORAGE = {
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                };
                int permission = ActivityCompat.checkSelfPermission(UserProfileActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

                if (permission != PackageManager.PERMISSION_GRANTED) {
                    // We don't have permission so prompt the user
                    ActivityCompat.requestPermissions(
                            UserProfileActivity.this,
                            PERMISSIONS_STORAGE,
                            REQUEST_EXTERNAL_STORAGE
                    );
                }
                else {

                    showImageSelectionDialog();

                }


                break;

            case R.id.btn_Save:
                Toast.makeText(getApplicationContext(), "Save Clicked", Toast.LENGTH_SHORT).show();

                new AsyncTaskUpdateProfile().execute();
                //getActivity().startActivity(new Intent(getActivity(), DrawerActivity.class));

                //getActivity().finish();
                break;


        }
    }
    void showImageSelectionDialog() {
        // ****************img click dialog box start********************
        compressImage = new CompressImage(UserProfileActivity.this);

        final String[] items = new String[]{"From Camera", "From SD Card"};
        ArrayAdapter<String> aadapter = new ArrayAdapter<String>(this,
                android.R.layout.select_dialog_item, items);
        AlertDialog.Builder builder = new AlertDialog.Builder(UserProfileActivity.this);
        builder.setTitle("Select Image");
        builder.setAdapter(aadapter, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item)
            {


                if (item == 0) {
                    //shivkant
                    // Intent intent = new
                    // Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    // startActivityForResult(intent, REQUEST_CODE_CLICK_IMAGE);

                    final Intent intent = new Intent(
                            MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, setImageUri());
                    startActivityForResult(intent, REQUEST_CODE_CLICK_IMAGE);

                    dialog.cancel();
                } else {
                    // Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    // intent.setType("image/*");
                    // startActivityForResult(intent,
                    // REQUEST_CODE_FROM_GALLERY);

                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_PICK);
                    startActivityForResult(intent,
                            REQUEST_CODE_FROM_GALLERY);

//                    Intent intent = new Intent();
//                    intent.setType("image/*");
//                    intent.setAction(Intent.ACTION_GET_CONTENT);//
//                    startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);
                }
            }
        });

        alertdialog = builder.create();
        alertdialog.show();

        // ****************img click dialog box end********************
    }
    /**
     * Making json object request
     */
    private void makeUserDetailRequest() {
        new GeneralFunction().showProgressDialog(this);
        String res = null;
        String responseCode = null;
        String returnResponse = null;
        try {

            String url = "http://52.66.101.233/Customer-Backend/public/api/user";
          /*  JSONObject jsonrequest = new JSONObject();
            jsonrequest.put("phone", phone);
            jsonrequest.put("sspin", password);
*/


            WebService web = new WebService();
            res = web.getWithHeader(url);
            Log.d(res, res);


            if (res != null && res.length()>0) {
                JSONObject json = new JSONObject(res);
                if (json != null) {


                    Const.USER_NAME=json.getString("name");
                    Const.USER_MOBILENUMBER=json.getString("phone");
                    Const.USER_EMAIL=json.getString("email");
                    String address=json.getString("address");

                    ed_FullName.setText(Const.USER_NAME);
                    ed_Email.setText(Const.USER_EMAIL);
                    ed_MobilNumber.setText(Const.USER_MOBILENUMBER);
                    ed_Address.setText(address);

                   /* String logintoken = json.getString("access_token");

                    Const.LOGIN_TOKEN=logintoken;
                    Const.TOKEN_WITH_BEARER+=Const.LOGIN_TOKEN;
                    Log.d("token",Const.TOKEN_WITH_BEARER);
                    startActivity(new Intent(DrawerActivity.this, DrawerActivity.class));

                   finish();*/
                    new GeneralFunction().hideProgressDialog();
                }
            }
            else {
                Toast.makeText(getApplicationContext(), "Unable to get user information.", Toast.LENGTH_SHORT)
                        .show();
                new GeneralFunction().hideProgressDialog();
            }

        } catch (Exception e) {
            new GeneralFunction().hideProgressDialog();
            Toast.makeText(getApplicationContext(), "Please provide valid mobile number  and password.", Toast.LENGTH_SHORT)
                    .show();
            e.printStackTrace();
        }
    }


    //Pick image

    class ImageCompressionAsyncTask extends AsyncTask<String, Void, String> {
        private boolean fromGallery;

        public ImageCompressionAsyncTask(boolean fromGallery) {
            this.fromGallery = fromGallery;
        }

        @Override
        protected String doInBackground(String... params) {

            String filePath = compressImage.compressImage(params[0]);
            return filePath;
        }



        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            File imgFile = new File(result);
            if (imgFile.exists()) {
               // img_Profilepic.setImageBitmap(null);

                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile
                        .getAbsolutePath());
                img_profilepic.setImageBitmap(myBitmap);


                try {
                    image1Base64 = fileToBase64(result);
/*
                    emaild=edEmail.getText().toString();
                    mobilno=edMobno.getText().toString();
                    city=citytextview.getText().toString();
                    companyname=ed_companyname.getText().toString().trim();*/
                    //new AsyncTaskWS_UpdateProfilePhoto().execute();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }


        }

    }
    // ****************img compression and click end*****************

    // ********************************************************

    public static String fileToBase64(String path) throws IOException {
        byte[] bytes = getByteArrayFromImage(path);
        return Base64.encodeToString(bytes, Base64.DEFAULT);

    }

    public static byte[] getByteArrayFromImage(String filePath)
            throws FileNotFoundException, IOException {

        File file = new File(filePath);
        System.out.println(file.exists() + "!!");

        FileInputStream fis = new FileInputStream(file);
        // create FileInputStream which obtains input bytes from a file in a
        // file system
        // FileInputStream is meant for reading streams of raw bytes such as
        // image data. For reading streams of characters, consider using
        // FileReader.

        // InputStream in = resource.openStream();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        try {
            for (int readNum; (readNum = fis.read(buf)) != -1; ) {
                bos.write(buf, 0, readNum);
                // no doubt here is 0
                /*
                 * Writes len bytes from the specified byte array starting at
				 * offset off to this byte array output stream.
				 */
                System.out.println("read " + readNum + " bytes,");
            }
        } catch (IOException ex) {
            Log.d("error", "error");
        }
        byte[] bytes = bos.toByteArray();
        return bytes;
    }

    public Uri setImageUri() {
        // Store image in dcim
        File file = new File(Environment.getExternalStorageDirectory()
                + "/DCIM/", "image" + System.currentTimeMillis() + ".png");
        Uri imgUri = Uri.fromFile(file);
        this.imgPath = file.getAbsolutePath();
        return imgUri;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
//shivkant
        if (resultCode != Activity.RESULT_CANCELED) {
            if (requestCode == REQUEST_CODE_FROM_GALLERY) {
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(
                        selectedImage, filePathColumn, null, null, null);
                if( cursor != null ) {
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    String filePath = cursor.getString(columnIndex);
                    cursor.close();
                    selectedImagePath = filePath;
                    if (selectedImagePath != null) {
//                    imageExtension = selectedImagePath
//                            .substring(selectedImagePath.lastIndexOf(".") + 1);

                        new ImageCompressionAsyncTask(true)
                                .execute(selectedImagePath);
                    } else {
                        Toast.makeText(getApplicationContext(), "Try Again",
                                Toast.LENGTH_LONG).show();
                    }
                }



            } else if (requestCode == REQUEST_CODE_CLICK_IMAGE) {

                selectedImagePath = getImagePath();

                if (selectedImagePath != null) {
//                    imageExtension = selectedImagePath
//                            .substring(selectedImagePath.lastIndexOf(".") + 1);

                    new ImageCompressionAsyncTask(false)
                            .execute(selectedImagePath);
                } else {
                    Toast.makeText(getApplicationContext(), "Try Again",
                            Toast.LENGTH_LONG).show();
                }

            } else {
                super.onActivityResult(requestCode, resultCode, data);
            }
        }

    }
    public String getImagePath() {
        return imgPath;
    }


    //******************webservice********
    private ProgressDialog progressDialog2 = null;
    String username;
    ArrayList<Merchant> setget = new ArrayList<>();
    String fname, lname, strGender;

    // To use the AsyncTask, it must be subclassed
    private class AsyncTaskUpdateProfile extends AsyncTask<Void, Integer, Void> {
        // Before running code in separate thread
        @Override
        protected void onPreExecute() {
            // Create a new progress dialog
            progressDialog2 = new ProgressDialog(UserProfileActivity.this);
            progressDialog2.getWindow().setBackgroundDrawableResource(R.color.colorPrimaryDark);
            progressDialog2.getWindow().setGravity(Gravity.CENTER);
            // Set the progress dialog to display a horizontal progress bar
            progressDialog2.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            // Set the dialog title to 'Loading...'
            // Set the dialog message to 'Loading application View, please
            // wait...'
            progressDialog2.setMessage("Pleaes Wait...");
            // This dialog can't be canceled by pressing the back key
            progressDialog2.setCancelable(false);
            // This dialog isn't indeterminate
            progressDialog2.setIndeterminate(false);
            // Display the progress dialog
            progressDialog2.show();
        }

        // The code to be executed in a background thread.
        @Override
        protected Void doInBackground(Void... params) {
            try {
                makeUploadProfileRequest();
            } catch (Exception e) {
                progressDialog2.dismiss();
                e.printStackTrace();
            }
            return null;
        }

        // after executing the code in the thread
        @Override
        protected void onPostExecute(Void result) {


        }
    }


    /**
     * Making json object request
     */
    private void makeUploadProfileRequest() {

        String res = null;
        String responseCode = null;
        String returnResponse = null;
        try {

            String url = "http://52.66.101.233/Customer-Backend/public/api/v1/user/image";
          /*  JSONObject jsonrequest = new JSONObject();
            jsonrequest.put("phone", phone);
            jsonrequest.put("sspin", password);
*/


            if(selectedImagePath.length()>0) {
                WebService web = new WebService();
                res = web.postWithHeaderImage(url, selectedImagePath);
                Log.d(res, res);


                if (res != null && res.length() > 0) {
                    JSONObject json = new JSONObject(res);
                    if (json != null) {


                        status = json.getString("status");
                        String message = json.getString("message");
                        if (status.equalsIgnoreCase("true")) {
                            UpdateOnlyProfile();
                        }

                    }
                }
            }else {

                UpdateOnlyProfile();

                }





        } catch (Exception e) {

            Toast.makeText(getApplicationContext(), "Unable to Update User Information.", Toast.LENGTH_SHORT)
                    .show();
            e.printStackTrace();
        }
    }


    public  boolean validation()
    {
        boolean isvalidate=true;
         fullname=ed_FullName.getText().toString();
         email=ed_Email.getText().toString();
         mobile=ed_MobilNumber.getText().toString();
         address=ed_Address.getText().toString();

        if(fullname.length()<=0)
        {
            Toast.makeText(UserProfileActivity.this,"Please enter valid Name",Toast.LENGTH_SHORT).show();
            isvalidate=false;
        }
        else   if(email.length()<=0)
        {
            Toast.makeText(UserProfileActivity.this,"Please enter valid email",Toast.LENGTH_SHORT).show();
            isvalidate=false;
        }
        else   if(mobile.length()<=0)
        {
            Toast.makeText(UserProfileActivity.this,"Mobile Number can not be blank",Toast.LENGTH_SHORT).show();
            isvalidate=false;
        }
        else   if(mobile.length()>=0)
        {
            if(mobile.length()<10) {
                Toast.makeText(UserProfileActivity.this, "Please enter valid mobilenumber", Toast.LENGTH_SHORT).show();
                isvalidate = false;
            }
        }
        else   if(address.length()<=0)
        {
            Toast.makeText(UserProfileActivity.this,"Please enter valid address",Toast.LENGTH_SHORT).show();
            isvalidate=false;
        }

        return  isvalidate;
    }

    public  void UpdateOnlyProfile() {
        try {
            String UpdateUserProfileUrl = "http://52.66.101.233/Customer-Backend/public/api/v1/user/profile/edit";

            boolean value = validation();

            if (value) {
                JSONObject jsonrequest = new JSONObject();
                jsonrequest.put("name", fullname);
                jsonrequest.put("email", email);
                jsonrequest.put("phone", phone);
                jsonrequest.put("address", address);
                jsonrequest.put("city", "Bhopal");
                jsonrequest.put("gender", "Male");

                WebService web1 = new WebService();
                String res1 = web1.postWithHeader(UpdateUserProfileUrl, jsonrequest.toString());
                Log.d(res1, res1);

                if (res1 != null && res1.length() > 0) {
                    JSONObject jsonProfileUpdate = new JSONObject(res1);
                    if (jsonProfileUpdate != null) {


                        Status_ProfileUPdate = jsonProfileUpdate.getString("status");
                        String messageProfileUPdate = jsonProfileUpdate.getString("message");
                        if (Status_ProfileUPdate.equalsIgnoreCase("true")) {


                            Toast.makeText(UserProfileActivity.this, message, Toast.LENGTH_LONG).show();
                            progressDialog2.dismiss();
                        }

                    }
                }
            } else {

            }


        } catch (Exception e) {
            new GeneralFunction().hideProgressDialog();
            Toast.makeText(getApplicationContext(), "Unable to Update User Information.", Toast.LENGTH_SHORT)
                    .show();
            e.printStackTrace();
        }
    }
}
