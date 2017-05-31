package androidlab2017.epam.com.ui.main;

import android.app.AlarmManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

import java.util.ArrayList;

import androidlab2017.epam.com.ui.main.AlarmItemListAdapter;
import androidlab2017.epam.com.R;
import androidlab2017.epam.com.data.AlarmItem;

public class MainActivity extends AppCompatActivity {
    private static final int PICK_RINGTONE_REQUEST = 1;

    private RecyclerView mAlarmItemsRecycler;
    private ArrayList<AlarmItem> mAlarmItems;
    private AlarmItemListAdapter mAlarmItemsAdapter;
    private SimpleExpandableListAdapter mSimpleExpanListAdapter;
    private ExpandableListView mExpandableListView;
    private AlarmManager mAlarmManager;
    private Button mChooseRingtone;

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
                        data.getStringExtra("ringtone_title"));
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
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());

        mAlarmItemsRecycler = (RecyclerView) findViewById(R.id.recycler_view_alarm_items);
        mAlarmItems = new ArrayList<>();
        for (int i = 0; i < 5; i++){
            mAlarmItems.add(new AlarmItem("",false, false, true, "", "", ""));
        }



        mAlarmItemsAdapter = new AlarmItemListAdapter(mAlarmItems);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mAlarmItemsRecycler.setLayoutManager(layoutManager);
        mAlarmItemsRecycler.setAdapter(mAlarmItemsAdapter);

        mAlarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
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
}
