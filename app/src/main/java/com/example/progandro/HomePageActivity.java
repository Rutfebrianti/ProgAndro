package com.example.progandro;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;


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
}
