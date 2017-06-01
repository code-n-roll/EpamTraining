package androidlab2017.epam.com.ui.ringtone_list;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidlab2017.epam.com.R;
import androidlab2017.epam.com.utils.StaticFields;

import static androidlab2017.epam.com.utils.MediaPlayerUtils.stopRingtone;

/**
 * Created by roman on 31.5.17.
 */

public class RingtoneListActivity extends AppCompatActivity {
    private RingtoneListAdapter mRingtoneListAdapter;
    private Map<String, Uri> mTitleUriRingtones;
    private MediaPlayer mMediaPlayer;

    @Override
    public void onBackPressed() {
        returnResultAndCloseActivity();
        super.onBackPressed();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ringtone_list);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }

        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_ALARM);

        RecyclerView ringtoneListRecycler = (RecyclerView) findViewById(R.id.ringtone_list);
        mRingtoneListAdapter =
                new RingtoneListAdapter(mMediaPlayer, getRingtones(), mTitleUriRingtones);
        ringtoneListRecycler.setAdapter(mRingtoneListAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        ringtoneListRecycler.setLayoutManager(layoutManager);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopRingtone(mMediaPlayer);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                returnResultAndCloseActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void returnResultAndCloseActivity(){
        Intent resultIntent = new Intent();
        resultIntent.putExtra(StaticFields.RINGTONE_TITLE, mRingtoneListAdapter.getCurRingtoneTitle());
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }



    public ArrayList<String> getRingtones() {
        RingtoneManager manager = new RingtoneManager(this);
        manager.setType(RingtoneManager.TYPE_ALARM);
        Cursor cursor = manager.getCursor();

        mTitleUriRingtones = new HashMap<>();
        ArrayList<String> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            String ringtoneTitle = cursor.getString(RingtoneManager.TITLE_COLUMN_INDEX);
            Uri ringtoneUri = manager.getRingtoneUri(cursor.getPosition());

            mTitleUriRingtones.put(ringtoneTitle, ringtoneUri);
            list.add(ringtoneTitle);
        }

        return list;
    }
}
