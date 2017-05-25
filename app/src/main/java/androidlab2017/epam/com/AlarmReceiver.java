package androidlab2017.epam.com;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by roman on 25.5.17.
 */

public class AlarmReceiver extends BroadcastReceiver {
    private static final String LOG_TAG = "myLogs";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(LOG_TAG, "onReceive");
        Log.d(LOG_TAG, "action = " + intent.getAction());
        Log.d(LOG_TAG, "extra = " + intent.getStringExtra("extra"));
    }
}
