package com.example.tutor4tutor_src;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class ReplyPost extends AppCompatActivity {

    private static final String TAG = ReplyPost.class.getSimpleName();

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

    }
}
