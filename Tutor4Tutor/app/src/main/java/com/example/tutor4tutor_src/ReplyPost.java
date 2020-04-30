package com.example.tutor4tutor_src;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class ReplyPost extends AppCompatActivity {

    private static final String TAG = ReplyPost.class.getSimpleName();
    private final String CHANNEL_ID = "reply_to_post";
    private final int NOTIFICATION_ID = 001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply_post);
    }

    public void sendmsg(View view) {
        EditText msg = (EditText) findViewById(R.id.Message);
        String sms = msg.getText().toString();
        Intent smsIntent = new Intent(Intent.ACTION_SENDTO);
        // Add the message (sms) with the key ("sms_body").
        smsIntent.putExtra("sms_body", sms);
        if (smsIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(smsIntent);
        } else {
            Log.d(TAG, "Can't resolve app for ACTION_SENDTO Intent");
        }
        Snackbar.make(view, "Responnded to the post", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();


        CreateNotificationChannel();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_sms_notification);
        builder.setContentTitle("testuser");
        builder.setContentText("Message Send");
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(NOTIFICATION_ID,builder.build());
    }

    private void CreateNotificationChannel(){
        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.O){
            CharSequence name = "Tutor4Tutor";
            String description = "Reply from a user";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID,name,importance);
            notificationChannel.setDescription(description);

            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }
}