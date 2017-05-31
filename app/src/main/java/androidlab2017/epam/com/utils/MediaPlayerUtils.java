package androidlab2017.epam.com.utils;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

import java.io.IOException;

/**
 * Created by roman on 31.5.17.
 */

public class MediaPlayerUtils {
    public static void stopRingtone(MediaPlayer mediaPlayer){
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.reset();
        }
    }

    public static void playRingtone(MediaPlayer mediaPlayer,
                                    Context context,
                                    Uri ringtoneUri){
        try {
            mediaPlayer.setDataSource(context, ringtoneUri);
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.start();
    }
}
