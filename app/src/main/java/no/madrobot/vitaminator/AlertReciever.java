package no.madrobot.vitaminator;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import static android.content.Context.NOTIFICATION_SERVICE;


/**
 * Created by Martin Agnar Dah on 11.07.2018.
 */
public class AlertReciever extends BroadcastReceiver {

    NotificationCompat.Builder notification;
    private static final int uniqueID = 111;

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Alarm is on", Toast.LENGTH_LONG);
        notification = new NotificationCompat.Builder(context);
        notification.setAutoCancel(true);
        buildnotif(context);
    }

    private void buildnotif(Context context){
        notification.setSmallIcon(R.drawable.ic_1);
        notification.setTicker("Take a pill!");
        notification.setWhen(System.currentTimeMillis());
        notification.setContentTitle("Did You took Your pills?");
        notification.setContentText("You should check if You took all of Your pils for today.");
        notification.setDefaults(Notification.DEFAULT_SOUND);

        Intent intent = new Intent (context, Check_daily_status.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setContentIntent(pendingIntent);

        NotificationManager manager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        manager.notify(uniqueID, notification.build());
    }
}

