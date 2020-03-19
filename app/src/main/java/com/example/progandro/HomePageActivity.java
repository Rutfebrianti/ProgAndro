package com.example.progandro;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import static com.example.progandro.CheckWiFi.offWiFi;
import static com.example.progandro.CheckWiFi.onWiFi;


public class HomePageActivity extends AppCompatActivity {
    private View view;
    private NotificationManagerCompat notificationManagerCompat;
    private static final String TAG = "Activities";
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new ViewAdapter(getSupportFragmentManager()));

        TabLayout tabLayout = findViewById(R.id.tab);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0);
        tabLayout.getTabAt(1);
        tabLayout.getTabAt(2);

        notificationManagerCompat = NotificationManagerCompat.from(this);
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, WifiManager.WIFI_STATE_UNKNOWN);
            if (wifiState == WifiManager.WIFI_STATE_ENABLED) {
                wifiOn(view);
            }
            else if (wifiState == WifiManager.WIFI_STATE_DISABLED) {
                wifiOff(view);
            }
        }
    };

    protected void onStart() {
        super.onStart();
        registerReceiver(receiver, new IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION));
    }

    protected void onStop() {
        super.onStop();
        unregisterReceiver(receiver);
    }

    public void wifiOn(View view){
        String title = "Apps";
        String message = "WiFi is On";
        android.app.Notification notification = new NotificationCompat.Builder(this,onWiFi)
                .setSmallIcon(R.drawable.ic_wifi_black)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setContentTitle(title)
                .setContentText(message)
                .build();

        notificationManagerCompat.notify(1,notification);
    }

    public void wifiOff(View view){
        String title = "Apps";
        String message = "WiFi is Off";
        android.app.Notification notification = new NotificationCompat.Builder(this,offWiFi)
                .setSmallIcon(R.drawable.ic_wifi_black)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setContentTitle(title)
                .setContentText(message)
                .build();

        notificationManagerCompat.notify(2,notification);
    }
}
