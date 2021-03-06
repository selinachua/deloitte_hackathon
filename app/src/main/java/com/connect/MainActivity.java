package com.connect;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.viewpager.widget.ViewPager;

import com.connect.ui.home.CustomAdapter;
import com.connect.ui.home.DateObject;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ViewPager slideViewPager;
    private SlideAdapter slideAdapter;
    private BottomNavigationView bottomNavView;
    private AppBarConfiguration appBarConfiguration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // custom title
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        slideViewPager = (ViewPager) findViewById(R.id.slideViewPager);
        slideAdapter = new SlideAdapter(getSupportFragmentManager());
        slideViewPager.setAdapter(slideAdapter);
        // Sets first page we see to be the home page.
        slideViewPager.setCurrentItem(1);

        // listener that changes the icon in nav bar
        slideViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}
            @Override
            public void onPageSelected(int position) {
                bottomNavView.getMenu().getItem(position).setChecked(true);
            }
            @Override
            public void onPageScrollStateChanged(int state) {}
        });

        // Bottom Navigation View
        bottomNavView = (BottomNavigationView) findViewById(R.id.bottomNavView);
        // Highlights the home page button in nav bar.
        bottomNavView.getMenu().getItem(1).setChecked(true);
        bottomNavView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            // add all the possible navigations.
                            case R.id.navigation_profile:
                                slideViewPager.setCurrentItem(0);
                                item.setChecked(true);
                                break;
                            case R.id.navigation_home:
                                slideViewPager.setCurrentItem(1);
                                item.setChecked(true);
                                break;
                            case R.id.navigation_calendar:
                                slideViewPager.setCurrentItem(2);
                                item.setChecked(true);
                                break;
                        }
                        return false;
                    }
                }
        );
    }

}