package com.androidlab2017.epam;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by roman on 19.5.17.
 */

public class ItemPageFragment extends Fragment {
    private int mPageNumber;
    private static final String SAVED_POSITION_TAG = "savedPosition";
    private static final String POSITION_TAG = "position";
    private static final String SAVED_CONTENT_TAG = "savedContent";
    private static final String LOG_TAG = "MY_LOG";
    private EditText mEditText;

    public static ItemPageFragment newInstance(int position) {
        ItemPageFragment fragment = new ItemPageFragment();
        Bundle args = new Bundle();
        args.putInt(POSITION_TAG, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        if (getArguments() != null){
            mPageNumber = getArguments().getInt(POSITION_TAG);
            Log.d(LOG_TAG, "onCreate: " + mPageNumber);
        }

        int savedPageNumber = -1;
        if (savedInstanceState != null){
            savedPageNumber = savedInstanceState.getInt(SAVED_POSITION_TAG);
        }
        Log.d(LOG_TAG, "savedPageNumber = " + savedPageNumber);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_page, container, false);

        mEditText = (EditText) view.findViewById(R.id.edittext_fragment_item_page);
        Log.d(LOG_TAG,"onCreateView: " + mPageNumber);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState != null) {
            mEditText.setText(savedInstanceState.getString(SAVED_CONTENT_TAG));
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(LOG_TAG,"onStart: " + mPageNumber);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(LOG_TAG,"onResume: " + mPageNumber);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SAVED_POSITION_TAG, mPageNumber);
        outState.putString(SAVED_CONTENT_TAG, mEditText.getText().toString());
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(LOG_TAG,"onPause: " + mPageNumber);
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(LOG_TAG,"onStop: " + mPageNumber);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.first_menu_item:
                Log.d(LOG_TAG, "PageNumber = " + mPageNumber);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
