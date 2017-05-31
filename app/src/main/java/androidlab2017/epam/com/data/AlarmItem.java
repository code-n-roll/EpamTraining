package androidlab2017.epam.com.data;

/**
 * Created by roman on 22.5.17.
 */

public class AlarmItem {
    public enum DAYS_OF_WEEK {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }

    private CharSequence mTime;
    private boolean mIsSet;
    private boolean mIsRepeat;
    private boolean mIsVibrate;
    private String mLabel;
    private String mAudioResource;
    private String mDaysOfWeek;


    public AlarmItem(CharSequence time,
                     boolean isSet,
                     boolean isRepeat,
                     boolean isVibrate,
                     String label,
                     String audioResource,
                     String daysOfWeek) {
        mTime = time;
        mIsSet = isSet;
        mIsRepeat = isRepeat;
        mIsVibrate = isVibrate;
        mLabel = label;
        mAudioResource = audioResource;
        mDaysOfWeek = daysOfWeek;
    }
}
