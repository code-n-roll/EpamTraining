package com.androidlab2017.epam.task1;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.androidlab2017.epam.lab001.R;


public class MainActivity extends AppCompatActivity {
    private Button mButton;
    private final static String CUSTOM_PERMISSION = "com.androidlab2017.epam.task2.custom_permission";
    private final static String REMOTE_PACKAGENAME = "com.androidlab2017.epam.task2";
    private final static int ID_CUSTOM_PERMISSION = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener((ignored)->clickOnButton());

    }

    private void clickOnButton(){
        int permissionCheck = ContextCompat.checkSelfPermission(this, CUSTOM_PERMISSION);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            // Should we show an explanation?
            getPermissions();
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, CUSTOM_PERMISSION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {

                // No explanation needed, we can request the permission.

                // ID_CUSTOM_PERMISSION is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            Intent intent = new Intent();
            intent.setClassName(REMOTE_PACKAGENAME, REMOTE_PACKAGENAME.concat(".MainActivity"));
            startActivity(intent);
        }
    }

    private void getPermissions(){
        ActivityCompat.requestPermissions(this, new String[]{CUSTOM_PERMISSION},
                ID_CUSTOM_PERMISSION);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case ID_CUSTOM_PERMISSION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                    Intent intent = new Intent();
                    intent.setClassName(REMOTE_PACKAGENAME,
                            REMOTE_PACKAGENAME.concat(".MainActivity"));
                    startActivity(intent);
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

}
