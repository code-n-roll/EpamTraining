package androidlab2017.epam.com.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TimePicker;

import androidlab2017.epam.com.R;

/**
 * Created by roman on 25.5.17.
 */

public class TimePickerDialogFragment extends DialogFragment {
    private TimePicker mTimePicker;

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

        View view1 = view;
        mTimePicker = (TimePicker) view.findViewById(R.id.timePicker);
        mTimePicker.setIs24HourView(true);
        Button buttonCancel = (Button) view.findViewById(R.id.button_cancel_time_picker);
        Button buttonOk = (Button) view.findViewById(R.id.button_ok_time_picker);

        buttonCancel.setOnClickListener((ignored)->clickOnCancelButton());
        buttonOk.setOnClickListener((ignored)->clickOnOkButton());
    }

    private void clickOnOkButton(){
        int hour = mTimePicker.getHour();
        int minute = mTimePicker.getMinute();
        MainActivity activity = (MainActivity) getContext();
        activity.getAlarmItemsAdapter().getViewHolder().onClickOkButton(hour, minute);
        dismiss();
    }

    private void clickOnCancelButton(){
        dismiss();
    }

    public interface OnClickOkButtonListener {
        void onClickOkButton(int hour, int minute);
    }
}
