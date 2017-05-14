package com.androidlab2017.epam.tasks.task3;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.androidlab2017.epam.lab001.R;
import com.androidlab2017.epam.tasks.MainActivity;

/**
 * Created by roman on 10.5.17.
 */

public class FirstFragment extends Fragment {
    private View mView;
    private Button mButtonChangeFragments;
    private Button mButtonChangeBackgrounds;
    private MainActivity mParentActivity;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_first,container,false);

        mButtonChangeFragments = (Button) mView.findViewById(R.id.id_button_change_fragments);
        mButtonChangeBackgrounds = (Button) mView.findViewById(R.id.id_button_change_backgrounds);

        mButtonChangeFragments.setOnClickListener((ignored)->onClickChangeFragments());
        mButtonChangeBackgrounds.setOnClickListener((ignored)->onClickChangeBackgrounds());

        return mView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mParentActivity = (MainActivity) context;
    }

    private void onClickChangeFragments(){
        Toast.makeText(this.mView.getContext(),"on click change fragments",Toast.LENGTH_SHORT).show();
        mParentActivity.onChangeFragment();
    }

    private void onClickChangeBackgrounds(){
        Toast.makeText(this.mView.getContext(),"on click change backgrounds",Toast.LENGTH_SHORT).show();
        mParentActivity.onChangeBackground();
    }
}
