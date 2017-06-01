package androidlab2017.epam.com.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidlab2017.epam.com.service.AlarmService;

import static androidlab2017.epam.com.utils.StaticFields.DISMISS_ACTION;
import static androidlab2017.epam.com.utils.StaticFields.MY_LOGS;
import static androidlab2017.epam.com.utils.StaticFields.SNOOZE_ACTION;
import static androidlab2017.epam.com.utils.StaticFields.STOP_RINGTONE;

/**
 * Created by roman on 31.5.17.
 */

public class ActionsNotifReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if(SNOOZE_ACTION.equals(action)) {
            Log.d(MY_LOGS,"Pressed SNOOZE");
        } else if(DISMISS_ACTION.equals(action)) {
            Intent serviceIntent = new Intent(context, AlarmService.class);
            serviceIntent.setAction(STOP_RINGTONE);
            context.startService(serviceIntent);

            Log.d(MY_LOGS,"Pressed DISMISS");
        }
        Log.d(MY_LOGS,"ActionsNotifReceiver onReceive");
    }
}
