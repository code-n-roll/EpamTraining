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

import static androidlab2017.epam.com.utils.StaticFields.CALLED_FROM;
import static androidlab2017.epam.com.utils.StaticFields.CLICK_ON_FAB;
import static androidlab2017.epam.com.utils.StaticFields.CLICK_ON_TEXTCLOCK;
import static androidlab2017.epam.com.utils.StaticFields.CUR_TIME_ALARM;

/**
 * Created by roman on 25.5.17.
 */

public class TimePickerDialogFragment extends DialogFragment {
    private TimePicker mTimePicker;
    private Button mButtonCancel;
    private Button mButtonOk;
    private String mCalledFrom;

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

        findViewsById(view);
        initTimePicker();
        setListenersOnViews();
    }

    private void setListenersOnViews(){
        mButtonCancel.setOnClickListener((ignored)->clickOnCancelButton());
        mButtonOk.setOnClickListener((ignored)->clickOnOkButton());
    }

    private void initTimePicker(){
        Bundle sharedData = getArguments();
        mCalledFrom = sharedData.getString(CALLED_FROM);

        int[] timestamp = getTimestamp(sharedData);
        if (timestamp.length > 1){
            mTimePicker.setHour(timestamp[0]);
            mTimePicker.setMinute(timestamp[1]);
        }
        mTimePicker.setIs24HourView(true);
    }

    private int[] getTimestamp(Bundle sharedData){
        int[] timestamp = new int[2];
        String curTimeAlarm = null;
        if (sharedData != null) {
            curTimeAlarm = sharedData.getString(CUR_TIME_ALARM);
        }
        if (curTimeAlarm != null){
            String[] timestampInString = curTimeAlarm.split(":");
            timestamp[0] = Integer.valueOf(timestampInString[0]);
            timestamp[1] = Integer.valueOf(timestampInString[1]);
        }
        return timestamp;
    }

    private void findViewsById(View view){
        mTimePicker = (TimePicker) view.findViewById(R.id.timePicker);
        mButtonCancel = (Button) view.findViewById(R.id.button_cancel_time_picker);
        mButtonOk = (Button) view.findViewById(R.id.button_ok_time_picker);
    }

    private void clickOnOkButton(){
        int hour = mTimePicker.getHour();
        int minute = mTimePicker.getMinute();
        MainActivity activity = (MainActivity) getContext();

        dismiss();
        switch (mCalledFrom){
            case CLICK_ON_TEXTCLOCK:
                if (activity.getAlarmItemsAdapter().getViewHolder() != null) {
                    activity.getAlarmItemsAdapter().getViewHolder().onClickOkButton(hour, minute);
                }
                break;
            case CLICK_ON_FAB:
                activity.onClickOkButton(hour, minute);
                break;
        }
    }

    private void clickOnCancelButton(){
        dismiss();
    }

    public interface OnClickOkButtonListener {
        void onClickOkButton(int hour, int minute);
    }
}
