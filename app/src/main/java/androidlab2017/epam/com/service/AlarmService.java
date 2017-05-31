package androidlab2017.epam.com.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.PixelFormat;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidlab2017.epam.com.R;
import androidlab2017.epam.com.ui.main.MainActivity;
import androidlab2017.epam.com.utils.MediaPlayerUtils;

/**
 * Created by roman on 31.5.17.
 */

public class AlarmService extends Service {
    private static final int NOTIFY_ID = 1;
    private MediaPlayer mMediaPlayer;



    @Override
    public void onCreate() {
        super.onCreate();

        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        someTask();
//        createAndStartNotifs(intent);
        if (intent != null && intent.getAction() != null){
            switch (intent.getAction()) {
                case "play_ringtone":
                    Uri ringtoneUri = Uri.parse(intent.getStringExtra("ringtone_uri"));
                    MediaPlayerUtils.playRingtone(mMediaPlayer, this, ringtoneUri);
                    break;
                case "stop_ringtone":
                    MediaPlayerUtils.stopRingtone(mMediaPlayer);
                    cancelNotif();
                    break;
                default:
                    break;
            }
        }

        Log.d("myLogs", "Alarm Service onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        cancelNotif();
        super.onDestroy();
    }

    private void cancelNotif(){
        NotificationManager nm = (NotificationManager)
                this.getSystemService(Context.NOTIFICATION_SERVICE);
        nm.cancel(NOTIFY_ID);
    }

    private void createAndStartNotifs(Intent intent){
        Context appContext = getApplicationContext();

        Intent notificationIntent = new Intent(appContext, MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(
                appContext, 0, notificationIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);

        Notification.Builder builder = new Notification.Builder(appContext);

        Resources res = appContext.getResources();
        builder.setContentIntent(pendingIntent)
                .setSmallIcon(android.R.drawable.ic_lock_idle_alarm)
                .setPriority(Notification.PRIORITY_MAX)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setContentTitle(res.getString(R.string.upcoming_alarm))
                .setContentText(intent.getStringExtra("data_notification"));

        Notification notification = builder.build();
        NotificationManager nm = (NotificationManager)
                appContext.getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(NOTIFY_ID, notification);
//        startForeground(NOTIFY_ID, builder.build());
    }

    private void someTask(){
        Context appContext = getApplicationContext();
        WindowManager mWindowManager = (WindowManager) appContext.getSystemService(WINDOW_SERVICE);
        View view = LayoutInflater.from(appContext).inflate(R.layout.activity_fullscreen_alarm, null);

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT, 0, 0,
                WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                        | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                        | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
/* | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON */,
                PixelFormat.RGBA_8888);
        mWindowManager.addView(view, layoutParams);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
