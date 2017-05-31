package androidlab2017.epam.com.receiver;

import android.app.KeyguardManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import androidlab2017.epam.com.R;
import androidlab2017.epam.com.service.AlarmService;
import androidlab2017.epam.com.ui.main.MainActivity;

/**
 * Created by roman on 25.5.17.
 */

public class AlarmReceiver extends BroadcastReceiver {
    private static final String LOG_TAG = "myLogs";
    private static final String SNOOZE_ACTION = "snooze_action";
    private static final String DISMISS_ACTION = "dismiss_action";
    private static final int NOTIFY_ID = 1;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(LOG_TAG, "onReceive");
        Log.d(LOG_TAG, "action = " + intent.getAction());
        Log.d(LOG_TAG, "data_notif = " + intent.getStringExtra("data_notification"));
        Log.d(LOG_TAG, "ringtone_uri = " + intent.getStringExtra("ringtone_uri"));

        Intent serviceIntent = new Intent(context, AlarmService.class);
//        serviceIntent.putExtra("data_notification", intent.getStringExtra("data_notification"));
        serviceIntent.putExtra("ringtone_uri", intent.getStringExtra("ringtone_uri"));
        serviceIntent.setAction("play_ringtone");
        context.startService(serviceIntent);

        createAndStartNotifs(context, intent);

        KeyguardManager km = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
        if(km.inKeyguardRestrictedInputMode()) {
            // it is locked
        } else {
            //it is not locked
        }
    }


    private void createAndStartNotifs(Context context, Intent intent){
        Context appContext = context.getApplicationContext();

        Intent snoozeReceive = new Intent(appContext, ActionsNotifReceiver.class);
        snoozeReceive.setAction(SNOOZE_ACTION);
        Intent dismissReceive = new Intent(appContext, ActionsNotifReceiver.class);
        dismissReceive.setAction(DISMISS_ACTION);

        PendingIntent pendingIntentSnooze = PendingIntent.getBroadcast(
                appContext, 0, snoozeReceive, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingIntentDismiss = PendingIntent.getBroadcast(
                appContext, 0, dismissReceive, PendingIntent.FLAG_UPDATE_CURRENT
        );


        Intent notificationIntent = new Intent(appContext, MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(
                appContext, 0, notificationIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(appContext);

        Resources res = appContext.getResources();
        builder.setContentIntent(pendingIntent)
                .setSmallIcon(android.R.drawable.ic_lock_idle_alarm)
                .setPriority(Notification.PRIORITY_MAX)
                .setContentIntent(pendingIntent)
                .setAutoCancel(false)
                .setContentTitle(res.getString(R.string.upcoming_alarm))
                .setContentText(intent.getStringExtra("data_notification"))
                .addAction(new NotificationCompat.Action(
                        android.R.drawable.ic_dialog_alert, "SNOOZE", pendingIntentSnooze))
                .addAction(new NotificationCompat.Action(
                        android.R.drawable.ic_dialog_email, "DISMISS", pendingIntentDismiss));

        Notification notification = builder.build();
        NotificationManager nm = (NotificationManager)
                appContext.getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(NOTIFY_ID, notification);
//        startForeground(NOTIFY_ID, builder.build());
    }
}
