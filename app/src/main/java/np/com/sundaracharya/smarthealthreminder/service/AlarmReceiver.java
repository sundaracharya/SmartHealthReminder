package np.com.sundaracharya.smarthealthreminder.service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Vibrator;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import np.com.sundaracharya.smarthealthreminder.MainActivity;
import np.com.sundaracharya.smarthealthreminder.R;
import np.com.sundaracharya.smarthealthreminder.ui.SplashActivity;
import np.com.sundaracharya.smarthealthreminder.utils.Utils;

public class AlarmReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_BOOT_COMPLETED == intent.getAction()) {

        } else {
            new Utils().notification(context,intent);
        }
    }


}
