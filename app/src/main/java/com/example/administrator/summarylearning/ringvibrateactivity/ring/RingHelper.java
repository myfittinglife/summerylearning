package com.example.administrator.summarylearning.ringvibrateactivity.ring;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;

public class RingHelper {
    private static MediaPlayer mMediaPlayer;
    //开始播放
    public static void playRing(final Activity activity){
        try {
            Uri bellUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);//用于获取手机默认铃声的Uri
            mMediaPlayer = new MediaPlayer();
            mMediaPlayer.setDataSource(activity, bellUri);
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_RING);                  //告诉mediaPlayer播放的是铃声流
            mMediaPlayer.setLooping(true);
            mMediaPlayer.prepare();
            mMediaPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //停止播放
    public static void stopRing(){
        if (mMediaPlayer!=null){
            if (mMediaPlayer.isPlaying()){
                mMediaPlayer.stop();
                mMediaPlayer.release();
            }
        }
    }
}
