package androidlab2017.epam.com;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import androidlab2017.epam.com.projecttask6.R;

/**
 * Created by roman on 21.5.17.
 */

public class SleepService extends Service {
    private static final String LOG_TAG = "myLogs";
    private static final int NOTIFY_ID = 1;

    @Override
    public void onCreate() {
        Log.d(LOG_TAG, "onCreate");
        super.onCreate();
    }

    
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(LOG_TAG, "onStartCommand");
        sleepTask();
        return super.onStartCommand(intent, flags, startId);
    }

    private void createNotification(){
        Context context = getApplicationContext();

        Intent notificationIntent = new Intent(context, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context,
                0, notificationIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);

        Resources res = context.getResources();
        Notification.Builder builder = new Notification.Builder(context);

        builder.setContentIntent(contentIntent)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setLargeIcon(BitmapFactory.decodeResource(res, android.R.drawable.ic_dialog_info))
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setContentTitle(getResources().getString(R.string.notif_content_title))
                .setContentText(getResources().getString(R.string.notif_content_text));

        Notification notification = builder.build();

        NotificationManager notificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFY_ID, notification);
    }

    private void sleepTask(){
        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                Log.d(LOG_TAG, "i = " + i);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            createNotification();
        }).start();
    }

    @Override
    public void onDestroy() {
        Log.d(LOG_TAG, "onDestroy");
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(LOG_TAG, "onBind");
        return null;
    }
}
