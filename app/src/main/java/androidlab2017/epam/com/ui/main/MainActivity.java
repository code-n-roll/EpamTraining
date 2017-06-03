package androidlab2017.epam.com.ui.main;

import android.app.AlarmManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

import androidlab2017.epam.com.R;
import androidlab2017.epam.com.data.AlarmItem;

import static androidlab2017.epam.com.utils.StaticFields.CALLED_FROM;
import static androidlab2017.epam.com.utils.StaticFields.CLICK_ON_FAB;
import static androidlab2017.epam.com.utils.StaticFields.CLICK_ON_TEXTCLOCK;
import static androidlab2017.epam.com.utils.StaticFields.PICK_RINGTONE_REQUEST;
import static androidlab2017.epam.com.utils.StaticFields.RINGTONE_TITLE;
import static androidlab2017.epam.com.utils.StaticFields.TP_DIALOG;

public class MainActivity extends AppCompatActivity implements
        TimePickerDialogFragment.OnClickOkButtonListener{
    private AlarmItemListAdapter mAlarmItemsAdapter;
    private AlarmManager mAlarmManager;
    private ArrayList<AlarmItem> mAlarmItems;
    private RecyclerView mAlarmItemsRecycler;
    private String mTime = "";

    public AlarmManager getAlarmManager() {
        return mAlarmManager;
    }

    public AlarmItemListAdapter getAlarmItemsAdapter() {
        return mAlarmItemsAdapter;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_RINGTONE_REQUEST) {
            if (resultCode == RESULT_OK) {
                mAlarmItemsAdapter.getViewHolder().setTextChooseRingtone(
                        data.getStringExtra(RINGTONE_TITLE));
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(view -> clickOnFAButton(view));

        mAlarmItemsRecycler = (RecyclerView) findViewById(R.id.recycler_view_alarm_items);

        mAlarmItems = new ArrayList<>();
//        for (int i = 0; i < 5; i++){
//            mAlarmItems.add(new AlarmItem("",false, false, true, "", "", ""));
//        }

        mAlarmItemsAdapter = new AlarmItemListAdapter(mAlarmItems);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mAlarmItemsRecycler.setLayoutManager(layoutManager);
        mAlarmItemsRecycler.setAdapter(mAlarmItemsAdapter);

        DividerItemDecoration divider = new DividerItemDecoration(mAlarmItemsRecycler.getContext(),
                LinearLayout.VERTICAL);
        mAlarmItemsRecycler.addItemDecoration(divider);

        mAlarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
    }

    private void clickOnFAButton(View view){
        TimePickerDialogFragment tpDialog = new TimePickerDialogFragment();
        Bundle sharedData = new Bundle();

        sharedData.putString(CALLED_FROM, CLICK_ON_FAB);
        tpDialog.setArguments(sharedData);

        tpDialog.show(getSupportFragmentManager(), TP_DIALOG);

        Snackbar.make(view, "Replace with your own action",
                Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClickOkButton(int hour, int minute) {
        mTime = String.format(Locale.getDefault(), "%2d:%2d", hour, minute).replace(' ', '0');
        mAlarmItems.add(new AlarmItem(mTime, false, false, true, "", "", ""));
        mAlarmItemsAdapter.notifyItemChanged(mAlarmItemsAdapter.getItemCount()-1);
    }
}
