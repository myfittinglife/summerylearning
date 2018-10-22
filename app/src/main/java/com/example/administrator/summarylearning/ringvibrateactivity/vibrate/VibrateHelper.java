package com.example.administrator.summarylearning.ringvibrateactivity.vibrate;

import android.app.Activity;
import android.app.Service;
import android.os.Vibrator;

public class VibrateHelper {
    public  static void VibrateHelper(final Activity activity, long milliseconds) {
        Vibrator vib = (Vibrator) activity.getSystemService(Service.VIBRATOR_SERVICE);
        vib.vibrate(milliseconds);
    }

    public  static void VibrateHelper(final Activity activity, long[] pattern, boolean isRepeat) {
        Vibrator vib = (Vibrator) activity.getSystemService(Service.VIBRATOR_SERVICE);
        vib.vibrate(pattern, isRepeat ? 1 : -1);
    }
    //取消震动
    public static void CancleVibrate(final Activity activity) {
        Vibrator vib = (Vibrator) activity.getSystemService(Service.VIBRATOR_SERVICE);
        vib.cancel();
    }
}
