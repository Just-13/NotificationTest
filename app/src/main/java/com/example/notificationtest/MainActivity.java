package com.example.notificationtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    // Идентификатор уведомления
    public int NOTIFY_ID = 1;
    // Идентификатор канала
    private static final String CHANNEL_ID = "Notification";
    private int Page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button CreateNewNotif = findViewById(R.id.CreateNewNotif);
        final Button Plus = findViewById(R.id.buttonPlus);
        final String textTitle = "Chat heads active";
        final String textContent = "Notification ";
        final Button MinusPage = findViewById(R.id.buttonMinus);
        final TextView Pagis = findViewById(R.id.textView);


        View Minus = findViewById(R.id.buttonMinus);

        Intent notificationIntent = new Intent(MainActivity.this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(MainActivity.this,
                0, notificationIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID)
                        .setSmallIcon(R.drawable.notification_icon)
                        .setContentTitle(textTitle)
                        .setContentText(textContent+NOTIFY_ID)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setContentIntent(contentIntent);
        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(MainActivity.this);



        MinusPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Page--;
                Pagis.setText(String.valueOf(Page));


                if (Page < 2) {
                    Minus.setVisibility(View.INVISIBLE);
                }
                notificationManager.cancel(NOTIFY_ID);
                NOTIFY_ID--;
                builder.setContentText(textContent+NOTIFY_ID);

           }
        });

        Plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Minus.setVisibility(View.VISIBLE);
                Page++;
                Pagis.setText(String.valueOf(Page));
                NOTIFY_ID++;
                builder.setContentText(textContent+NOTIFY_ID);


            }
        });


        CreateNewNotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast toast = Toast.makeText(getApplicationContext(),
                        "You create a notification "+NOTIFY_ID, Toast.LENGTH_SHORT);
                toast.show();
                notificationManager.notify(NOTIFY_ID, builder.build());

            }
        });

    }
}