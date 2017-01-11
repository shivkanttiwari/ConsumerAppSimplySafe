package com.example.tiuadmin.simplysafeconusmerapp.Merchant;

import android.Manifest;
import android.app.AlertDialog;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.tiuadmin.simplysafeconusmerapp.R;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class QRCodeScanActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;
    private static final int REQUEST_WRITE_PERMISSION = 20;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qrcodescanner);

        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view


    }

    public void QrScanner(View view){


        Toast.makeText(getApplicationContext(),"TOast",Toast.LENGTH_LONG).show();
      //  ActivityCompat.requestPermissions(QRCodeScanActivity.this, new
             //   String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA}, REQUEST_WRITE_PERMISSION);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA}, REQUEST_WRITE_PERMISSION);
        } else {
            setContentView(mScannerView);

            mScannerView.setResultHandler(this); // Reg
            mScannerView.startCamera();
        }
              // Start camera

    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_WRITE_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    setContentView(mScannerView);
                    Toast.makeText(QRCodeScanActivity.this, "Permission granted!", Toast.LENGTH_SHORT).show();
                    mScannerView.setResultHandler(this); // Reg
                    mScannerView.startCamera();
                } else {
                    Toast.makeText(QRCodeScanActivity.this, "Permission Denied!", Toast.LENGTH_SHORT).show();
                }
        }
    }
    @Override
    public void handleResult(Result rawResult) {
        // Do something with the result here

        Log.e("handler", rawResult.getText()); // Prints scan results
        Log.e("handler", rawResult.getBarcodeFormat().toString()); // Prints the scan format (qrcode)

        // show the scanner result into dialog box.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Scan Result");
        builder.setMessage(rawResult.getText());
        AlertDialog alert1 = builder.create();
        alert1.show();

        // If you would like to resume scanning, call this method below:
       // mScannerView.resumeCameraPreview(this);
    }
}
