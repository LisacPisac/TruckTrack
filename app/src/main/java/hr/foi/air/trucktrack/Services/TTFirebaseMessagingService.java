package hr.foi.air.trucktrack.Services;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

import hr.foi.air.trucktrack.DisponentHome;
import hr.foi.air.trucktrack.DisponentJobsFragment;
import hr.foi.air.trucktrack.DriverJobs;

/**
 * Created by roman on 1/31/18.
 */

public class TTFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "TRUCKTRACK_FCM";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
            Map<String, String> payload = remoteMessage.getData();
            sendNotification(payload.get("title"), payload.get("body"));
        }
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "MEssage Notification Body: " + remoteMessage.getNotification().getBody());
        }
    }

    private void sendNotification(String title, String body) {
        Intent intent = new Intent(this, DriverJobs.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        //intent.putExtra("body", body);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(android.R.drawable.ic_dialog_alert)
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notificationBuilder.build());
    }
}
