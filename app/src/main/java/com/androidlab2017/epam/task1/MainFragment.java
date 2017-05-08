package com.androidlab2017.epam.task1;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.androidlab2017.epam.lab001.R;

/**
 * Created by roman on 8.5.17.
 */

public class MainFragment extends Fragment{
    private View mView;
    private Button mButton;
    private Button mButtonToSecondActivity;
    private Button mCloseMainActivity;
    private Button mButtonToColorFragment;
    private final static String CUSTOM_PERMISSION = "com.androidlab2017.epam.task2.custom_permission";
    private final static String REMOTE_PACKAGENAME = "com.androidlab2017.epam.task2";
    private final static int ID_CUSTOM_PERMISSION = 0;
    private final static String COLOR_BUTTONS_FRAGMENT = "ColorButtonsFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_activity_main, container, false);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getActivity() != null) {

            AppCompatActivity activity = (AppCompatActivity)getActivity();

            activity.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            activity.getSupportActionBar().setDisplayShowHomeEnabled(false);

            mButton = (Button) getActivity().findViewById(R.id.button);
            mButton.setOnClickListener((ignored) -> clickOnButton());

            mButtonToColorFragment = (Button) getActivity().findViewById(R.id.button_to_color_fragment);
            mButtonToColorFragment.setOnClickListener((ignored) -> clickOnButtonToColorFragment());

            mButtonToSecondActivity = (Button) getActivity().findViewById(R.id.button_to_second_activity);
            mButtonToSecondActivity.setOnClickListener((ignored) -> clickOnButtonToSecondActivity());

            mCloseMainActivity = (Button) getActivity().findViewById(R.id.button_close_main_activity);
            mCloseMainActivity.setOnClickListener((ignored) -> clickOnCloseMainActivity());

        }
    }

    private void clickOnButtonToColorFragment(){
        getFragmentManager().
                beginTransaction().
                replace(R.id.main_container,new ColorButtonsFragment(),COLOR_BUTTONS_FRAGMENT).
                commit();
        ((MainActivity)getActivity()).setCurFragmentTag(COLOR_BUTTONS_FRAGMENT);
    }

    private void clickOnCloseMainActivity(){
        getActivity().finish();
    }


    private void clickOnButtonToSecondActivity(){
        Intent intent = new Intent(getActivity(), SecondActivity.class);
        startActivity(intent);
    }

    private void clickOnButton(){
        int permissionCheck = ContextCompat.checkSelfPermission(getActivity(), CUSTOM_PERMISSION);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            // Should we show an explanation?
            getPermissions();
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), CUSTOM_PERMISSION)) {

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
        ActivityCompat.requestPermissions(getActivity(), new String[]{CUSTOM_PERMISSION},
                ID_CUSTOM_PERMISSION);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
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
