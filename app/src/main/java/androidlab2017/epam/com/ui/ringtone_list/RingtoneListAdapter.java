package androidlab2017.epam.com.ui.ringtone_list;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;

import java.util.ArrayList;
import java.util.Map;

import androidlab2017.epam.com.R;
import androidlab2017.epam.com.utils.MediaPlayerUtils;

/**
 * Created by roman on 31.5.17.
 */

public class RingtoneListAdapter extends
        RecyclerView.Adapter<RingtoneListAdapter.ViewHolder>{
    private ArrayList<String> mRingtones;
    private Map<String, Uri> mTitleUriRingtones;
    private MediaPlayer mMediaPlayer;
    private String curRingtoneTitle;

    public String getCurRingtoneTitle() {
        return curRingtoneTitle;
    }


    public RingtoneListAdapter(MediaPlayer mediaPlayer,
                               ArrayList<String> ringtones,
                               Map<String, Uri> titleUriRingtones) {
        mMediaPlayer = mediaPlayer;
        mRingtones = ringtones;
        mTitleUriRingtones = titleUriRingtones;
    }



    @Override
    public RingtoneListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.content_ringtone_item, parent, false);

        return new RingtoneListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RingtoneListAdapter.ViewHolder holder, int position) {
        holder.bind(mRingtones.get(position));
    }

    @Override
    public int getItemCount() {
        return mRingtones.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private CheckedTextView mCheckedTextView;
        private Context mContext;

        public ViewHolder(View itemView) {
            super(itemView);

            mContext = itemView.getContext();
            mCheckedTextView = (CheckedTextView) itemView.findViewById(R.id.ringtone_item);
        }

        public void bind(final String ringtoneTitle){
            mCheckedTextView.setText(ringtoneTitle);
            mCheckedTextView.setOnClickListener((ignored)->clickOnCheckedTextView(ringtoneTitle));
        }

        private void clickOnCheckedTextView(String ringtoneTitle){
            curRingtoneTitle = ringtoneTitle;
            if (mCheckedTextView.isChecked()){
                mCheckedTextView.setChecked(false);
                MediaPlayerUtils.stopRingtone(mMediaPlayer);
            } else {
                mCheckedTextView.setChecked(true);
                MediaPlayerUtils.stopRingtone(mMediaPlayer);

                Uri ringtoneUri = mTitleUriRingtones.get(ringtoneTitle);
                MediaPlayerUtils.playRingtone(mMediaPlayer, mContext, ringtoneUri);

            }
        }
    }
}
