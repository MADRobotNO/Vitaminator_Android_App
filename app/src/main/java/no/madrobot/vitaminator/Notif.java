package no.madrobot.vitaminator;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

/**
 * Created by Martin Agnar Dah on 11.07.2018.
 */
public class Notif extends Application {

    public static final String CHANNEL_1_ID = "Ch1";
    public static final String CHANNEL_2_ID = "Ch2";

    @Override
    public void onCreate() {
        super.onCreate();

        createNotificationChannels();

    }

    private void createNotificationChannels(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel1 = new NotificationChannel(
                    CHANNEL_1_ID,
                    "Remember to take a pill!",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel1.setDescription("Notif 1");

            NotificationChannel channel2 = new NotificationChannel(
                    CHANNEL_2_ID,
                    "Remember to take a pill!",
                    NotificationManager.IMPORTANCE_LOW
            );
            channel2.setDescription("Notif 2");

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
            manager.createNotificationChannel(channel2);
        }
    }
}
