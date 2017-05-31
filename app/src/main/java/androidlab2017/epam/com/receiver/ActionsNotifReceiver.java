package androidlab2017.epam.com.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidlab2017.epam.com.service.AlarmService;

/**
 * Created by roman on 31.5.17.
 */

public class ActionsNotifReceiver extends BroadcastReceiver {
    private static final String SNOOZE_ACTION = "snooze_action";
    private static final String DISMISS_ACTION = "dismiss_action";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if(SNOOZE_ACTION.equals(action)) {
            Log.d("myLogs","Pressed SNOOZE");
        } else if(DISMISS_ACTION.equals(action)) {

            Intent serviceIntent = new Intent(context, AlarmService.class);
            serviceIntent.setAction("stop_ringtone");
            context.startService(serviceIntent);


            Log.d("myLogs","Pressed DISMISS");
        }
        Log.d("myLogs","ActionsNotifReceiver onReceive");
    }
}
