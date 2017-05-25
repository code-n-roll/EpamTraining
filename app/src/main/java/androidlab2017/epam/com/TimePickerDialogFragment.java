package androidlab2017.epam.com;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by roman on 25.5.17.
 */

public class TimePickerDialogFragment extends DialogFragment {
    private Button mButtonOk;
    private Button mButtonCancel;
    private View mView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_time_picker, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mView = view;
        mButtonCancel = (Button) view.findViewById(R.id.button_cancel_time_picker);
        mButtonOk = (Button) view.findViewById(R.id.button_ok_time_picker);

        mButtonCancel.setOnClickListener((ignored)->clickOnCancelButton());
        mButtonOk.setOnClickListener((ignored)->clickOnOkButton());
    }

    private void clickOnOkButton(){
        MainActivity activity = (MainActivity) getContext();
        activity.getAlarmItemsAdapter().getViewHolder().onClickOkButton();
    }

    private void clickOnCancelButton(){
        dismiss();
    }

    interface OnClickOkButtonListener {
        void onClickOkButton();
    }
}
