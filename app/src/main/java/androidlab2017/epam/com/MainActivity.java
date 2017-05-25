package androidlab2017.epam.com;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mAlarmItemsRecycler;
    private ArrayList<AlarmItem> mAlarmItems;
    private AlarmItemListAdapter mAlarmItemsAdapter;
    private SimpleExpandableListAdapter mSimpleExpanListAdapter;
    private ExpandableListView mExpandableListView;

    public AlarmItemListAdapter getAlarmItemsAdapter() {
        return mAlarmItemsAdapter;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mAlarmItemsRecycler = (RecyclerView) findViewById(R.id.recycler_view_alarm_items);
        mAlarmItems = new ArrayList<>();
        for (int i = 0; i < 5; i++){
            mAlarmItems.add(new AlarmItem("",false, false, true, "", "", ""));
        }





        mAlarmItemsAdapter = new AlarmItemListAdapter(mAlarmItems);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mAlarmItemsRecycler.setLayoutManager(layoutManager);
        mAlarmItemsRecycler.setAdapter(mAlarmItemsAdapter);




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
