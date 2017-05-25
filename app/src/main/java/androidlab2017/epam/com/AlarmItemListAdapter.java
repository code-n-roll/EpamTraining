package androidlab2017.epam.com;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Switch;
import android.widget.TextClock;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by roman on 22.5.17.
 */

public class AlarmItemListAdapter extends
        RecyclerView.Adapter<AlarmItemListAdapter.ViewHolder> {
    private ArrayList<AlarmItem> mAlarmItems;
    private ViewHolder mViewHolder;

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
        private TextClock mTextClock;
        private Switch mSwitch;
        private ExpandableListView mExpandableListView;

        public ViewHolder(View itemView) {
            super(itemView);

            mTextClock = (TextClock) itemView.findViewById(R.id.clock_content_alarm_item);
            mSwitch = (Switch) itemView.findViewById(R.id.switch_content_alarm_item);
//            mExpandableListView = (ExpandableListView)
//                    itemView.findViewById(R.id.expand_list_view_content_alarm_item);

            mTextClock.setOnClickListener((view)->clickOnTextClock(view));


        }

        public void bind(final AlarmItem alarmItem){
            mTextClock.setFormat24Hour("12:50");
        }

        @Override
        public void onClickOkButton() {
            mTextClock.setFormat24Hour("01:01");
        }
    }

    private void clickOnTextClock(View view){
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