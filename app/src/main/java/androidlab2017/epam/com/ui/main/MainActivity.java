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
import android.widget.LinearLayout;

import java.util.ArrayList;

import androidlab2017.epam.com.R;
import androidlab2017.epam.com.data.AlarmItem;

import static androidlab2017.epam.com.utils.StaticFields.RINGTONE_TITLE;

public class MainActivity extends AppCompatActivity {
    private static final int PICK_RINGTONE_REQUEST = 1;

    private AlarmItemListAdapter mAlarmItemsAdapter;
    private AlarmManager mAlarmManager;

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
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());

        RecyclerView alarmItemsRecycler = (RecyclerView) findViewById(R.id.recycler_view_alarm_items);

        ArrayList<AlarmItem> alarmItems = new ArrayList<>();
        for (int i = 0; i < 5; i++){
            alarmItems.add(new AlarmItem("",false, false, true, "", "", ""));
        }

        mAlarmItemsAdapter = new AlarmItemListAdapter(alarmItems);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        alarmItemsRecycler.setLayoutManager(layoutManager);
        alarmItemsRecycler.setAdapter(mAlarmItemsAdapter);

        DividerItemDecoration divider = new DividerItemDecoration(alarmItemsRecycler.getContext(),
                LinearLayout.VERTICAL);
        alarmItemsRecycler.addItemDecoration(divider);

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
