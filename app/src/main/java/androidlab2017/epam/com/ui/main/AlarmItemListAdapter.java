package androidlab2017.epam.com.ui.main;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.SystemClock;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

import androidlab2017.epam.com.receiver.AlarmReceiver;
import androidlab2017.epam.com.R;
import androidlab2017.epam.com.service.AlarmService;
import androidlab2017.epam.com.ui.ringtone_list.RingtoneListActivity;
import androidlab2017.epam.com.data.AlarmItem;

/**
 * Created by roman on 22.5.17.
 */

public class AlarmItemListAdapter extends
        RecyclerView.Adapter<AlarmItemListAdapter.ViewHolder> {
    private static String LOG_TAG = "myLogs";
    private static final int PICK_RINGTONE_REQUEST = 1;

    private ArrayList<AlarmItem> mAlarmItems;
    private ViewHolder mViewHolder;
    private TextView mCurTimeAlarm;
    private AlarmManager mAlarmManager;
    private MainActivity mMainActivity;
    private Button mCurChooseRingtone;

    public TextView getCurTimeAlarm() {
        return mCurTimeAlarm;
    }


    public ViewHolder getViewHolder() {
        return mViewHolder;
    }


    AlarmItemListAdapter(ArrayList<AlarmItem> alarmItems){
        mAlarmItems = alarmItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.content_alarm_item, parent, false
        );
        mViewHolder = new ViewHolder(view);
        mMainActivity = ((MainActivity) view.getContext());
        mAlarmManager = mMainActivity.getAlarmManager();

        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(mAlarmItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mAlarmItems.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder
            implements TimePickerDialogFragment.OnClickOkButtonListener {
        private TextView mTimeAlarm;
        private Switch mSwitch;
        private ExpandableListView mExpandableListView;
        private PendingIntent mPendingIntent1;
        private PendingIntent mPendingIntent2;
        private PendingIntent mPendingIntent;
        private View mView;
        private CheckBox mRepeatCheckBox;
        private Button mChooseRingtone;

        public ViewHolder(View itemView) {
            super(itemView);

            mView = itemView;
            mTimeAlarm = (TextView) itemView.findViewById(R.id.clock_content_alarm_item);
            mSwitch = (Switch) itemView.findViewById(R.id.switch_content_alarm_item);
            mRepeatCheckBox = (CheckBox) itemView.findViewById(R.id.repeat_checkbox_exp_view);
//            mExpandableListView = (ExpandableListView)
//                    itemView.findViewById(R.id.expand_list_view_content_alarm_item);
            mChooseRingtone = (Button) itemView.findViewById(R.id.name_alarm_ringtone);

            mChooseRingtone.setOnClickListener((view)->clickOnChooseRingtoneButton(view));
            mTimeAlarm.setOnClickListener((view)->clickOnTextClock(view));

            mSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
                Context context = itemView.getContext();
                if (isChecked){
                    createAndAddAlarm();
                    startAlarmService(context);
//                        showSnackbar();
                } else {
                    removeAlarm();
                    stopAlarmService(context);
                }
            });
        }

        private void clickOnChooseRingtoneButton(View view){
            mCurChooseRingtone = (Button) view;
            Intent pickRingtoneIntent = new Intent(view.getContext(), RingtoneListActivity.class);
            ((MainActivity)view.getContext()).
                    startActivityForResult(pickRingtoneIntent, PICK_RINGTONE_REQUEST);
        }

        public void setTextChooseRingtone(String text){
            mCurChooseRingtone.setText(text);
        }

        private void showSnackbar(String info){
            Snackbar.make(mView,"Alarm set for "+info+" from now.",Snackbar.LENGTH_SHORT).show();
        }

        private Intent createIntent(String action, String extra){
            Intent intent = new Intent(mMainActivity, AlarmReceiver.class);
            intent.setAction(action);
            intent.putExtra("extra", extra);
            return intent;
        }

        private void removeAlarm(){
            mAlarmManager.cancel(mPendingIntent);
//            mAlarmManager.cancel(mPendingIntent2);
        }

        private long getMilliFromFormat24Time(){
            String timeString = mTimeAlarm.getText().toString();
            String[] hourMinute = timeString.split(":");
            long hour = Long.parseLong(hourMinute[0]);
            long minute = Long.parseLong(hourMinute[1]);
            long millisHour = hour * 60 * 60 * 1000;
            long millisMinute = minute * 60 * 1000;


            Calendar rightNow = Calendar.getInstance();

            // offset to add since we're not UTC
            long offset = rightNow.get(Calendar.ZONE_OFFSET) +
                    rightNow.get(Calendar.DST_OFFSET);

            long sinceMidnight = (rightNow.getTimeInMillis() + offset) %
                    (24 * 60 * 60 * 1000);

            Log.d(LOG_TAG, String.valueOf(sinceMidnight));
            return (millisHour + millisMinute) - sinceMidnight ;
        }

        private void startAlarmService(Context context){
            Intent serviceIntent = new Intent(context, AlarmService.class);
            serviceIntent.putExtra("ringtone_uri", findRingtone(mChooseRingtone.getText().toString()));
            serviceIntent.putExtra("data_notification", mTimeAlarm.getText().toString());
            context.startService(serviceIntent);
        }

        private void stopAlarmService(Context context){
            Intent intent = new Intent(context, AlarmService.class);
            context.stopService(intent);
        }

        private String findRingtone(String searchedTitle){
            RingtoneManager manager = new RingtoneManager(mView.getContext());
            manager.setType(RingtoneManager.TYPE_RINGTONE);
            Cursor cursor = manager.getCursor();

            String ringtoneTitle;
            Uri ringtoneUri = Uri.EMPTY;
            while (cursor.moveToNext()) {
                ringtoneTitle = cursor.getString(RingtoneManager.TITLE_COLUMN_INDEX);
                if (ringtoneTitle.equals(searchedTitle)){
                    ringtoneUri = manager.getRingtoneUri(cursor.getPosition());
                    break;
                }
            }

            return ringtoneUri.toString();
        }

        private void createAndAddAlarm(){
//            Intent intent = createIntent("alarm action","alarm extra");
            Intent intent = new Intent(mMainActivity, AlarmReceiver.class);
            intent.putExtra("ringtone_uri", findRingtone(mChooseRingtone.getText().toString()));
            intent.putExtra("data_notification", mTimeAlarm.getText().toString());
            mPendingIntent = PendingIntent.getBroadcast(mMainActivity, 0, intent, 0);



            long alarmTime = getMilliFromFormat24Time();

            if (mRepeatCheckBox.isChecked()){
                mAlarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                        SystemClock.elapsedRealtime() + alarmTime,
                        60000, mPendingIntent);
            } else {
                mAlarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                        SystemClock.elapsedRealtime() + alarmTime, mPendingIntent);
            }



            Log.d(LOG_TAG, "start " + alarmTime + " ms");
//            Intent intent1 = createIntent("action 1", "extra 1");
//            mPendingIntent1 = PendingIntent.getBroadcast(mMainActivity, 0, intent1, 0);
//            Intent intent2 = createIntent("action 2", "extra 2");
//            mPendingIntent2 = PendingIntent.getBroadcast(mMainActivity, 0, intent2, 0);

//            mAlarmManager.set(AlarmManager.RTC, System.currentTimeMillis() + 4000, mPendingIntent1);
//            mAlarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME,
//                    SystemClock.elapsedRealtime() + 3000, 60000, mPendingIntent2);
        }

        public void bind(final AlarmItem alarmItem){
            mTimeAlarm.setText("12:50");
        }

        @Override
        public void onClickOkButton(int hour, int minute) {
            TextView timeAlarm = getCurTimeAlarm();
            timeAlarm.setText(
                    String.format(Locale.getDefault(), "%2d:%2d", hour, minute).replace(' ', '0')
            );
        }
    }

    private void clickOnTextClock(View view){
        mCurTimeAlarm = (TextView) view;
        TimePickerDialogFragment tpDialog = new TimePickerDialogFragment();
        MainActivity activity = (MainActivity)view.getContext();

        tpDialog.show(activity.getSupportFragmentManager(), "dialog");
    }
}


//    String[] groups = new String[] {"HTC", "Samsung", "LG"};
//
//    // названия телефонов (элементов)
//    String[] phonesHTC = new String[] {"Sensation", "Desire", "Wildfire", "Hero"};
//    String[] phonesSams = new String[] {"Galaxy S II", "Galaxy Nexus", "Wave"};
//    String[] phonesLG = new String[] {"Optimus", "Optimus Link", "Optimus Black", "Optimus One"};
//
//    // коллекция для групп
//    ArrayList<Map<String, String>> groupData;
//
//    // коллекция для элементов одной группы
//    ArrayList<Map<String, String>> childDataItem;
//
//    // общая коллекция для коллекций элементов
//    ArrayList<ArrayList<Map<String, String>>> childData;
//    // в итоге получится childData = ArrayList<childDataItem>
//
//    // список атрибутов группы или элемента
//    Map<String, String> m;



//            groupData = new ArrayList<>();
//            for (String group : groups) {
//                // заполняем список атрибутов для каждой группы
//                m = new HashMap<>();
//                m.put("groupName", group); // имя компании
//                groupData.add(m);
//            }
//
//            // список атрибутов групп для чтения
//            String groupFrom[] = new String[] {"groupName"};
//            // список ID view-элементов, в которые будет помещены атрибуты групп
//            int groupTo[] = new int[] {android.R.id.text1};
//
//
//            // создаем коллекцию для коллекций элементов
//            childData = new ArrayList<>();
//
//            // создаем коллекцию элементов для первой группы
//            childDataItem = new ArrayList<>();
//            // заполняем список атрибутов для каждого элемента
//            for (String phone : phonesHTC) {
//                m = new HashMap<>();
//                m.put("phoneName", phone); // название телефона
//                childDataItem.add(m);
//            }
//            // добавляем в коллекцию коллекций
//            childData.add(childDataItem);
//
//            // создаем коллекцию элементов для второй группы
//            childDataItem = new ArrayList<>();
//            for (String phone : phonesSams) {
//                m = new HashMap<>();
//                m.put("phoneName", phone);
//                childDataItem.add(m);
//            }
//            childData.add(childDataItem);
//
//            // создаем коллекцию элементов для третьей группы
//            childDataItem = new ArrayList<>();
//            for (String phone : phonesLG) {
//                m = new HashMap<>();
//                m.put("phoneName", phone);
//                childDataItem.add(m);
//            }
//            childData.add(childDataItem);
//
//            // список атрибутов элементов для чтения
//            String childFrom[] = new String[] {"phoneName"};
//            // список ID view-элементов, в которые будет помещены атрибуты элементов
//            int childTo[] = new int[] {android.R.id.text1};
//
//            SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(
//                    itemView.getContext(),
//                    groupData,
//                    android.R.layout.simple_expandable_list_item_1,
//                    groupFrom,
//                    groupTo,
//                    childData,
//                    android.R.layout.simple_list_item_1,
//                    childFrom,
//                    childTo);
////            mExpandableListView = (ExpandableListView) itemView.findViewById(
////                    R.id.expand_list_view_content_alarm_item);
////            SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(
////                    itemView.getContext(),
////                    null,
////                    android.R.layout.simple_expandable_list_item_1,
////                    null,
////                    null,
////                    null,
////                    android.R.layout.simple_list_item_1,
////                    null,
////                    null);
////
//            mExpandableListView.setAdapter(adapter);
//            if (android.os.Build.VERSION.SDK_INT <
//                    android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
//                mExpandableListView.setIndicatorBounds(500, 700);
//            } else {
//                mExpandableListView.setIndicatorBoundsRelative(500, 700);
//            }