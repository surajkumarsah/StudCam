package com.example.studcam;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.studcam.model.ScreenItem;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class App_Intro_Activity extends AppCompatActivity {

    private ViewPager screenPager;
    IntroViewPagerAdapter introViewPagerAdapter;
    TabLayout tabIndicator;
    Button btnNext;
    int position = 0;
    Button btnGetStarted;
    Animation btnAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        if (restroePrefData())
        {
            Intent intent = new Intent(getApplicationContext(), GmailLogin_Activity.class);
            startActivity(intent);
            finish();
        }

        setContentView(R.layout.app_intro);
        getSupportActionBar().hide();

        tabIndicator = findViewById(R.id.tab_indecator);
        btnNext = findViewById(R.id.btn_next);
        btnGetStarted = findViewById(R.id.get_started);
        btnAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.btn_animation);


        final List<ScreenItem> mList = new ArrayList<>();
        mList.add(new ScreenItem(R.drawable.undraw_dev_focus_b9xo, "title 1", "Description 1"));
        mList.add(new ScreenItem(R.drawable.undraw_modern_design_v3wv, "title 2", "Description 2"));
        mList.add(new ScreenItem(R.drawable.undraw_moments_0y20, "title 3", "Description 3"));
        mList.add(new ScreenItem(R.drawable.undraw_podcast_q6p7, "title 4", "Description 4"));


        screenPager = findViewById(R.id.view_pager);
        introViewPagerAdapter = new IntroViewPagerAdapter(this,mList);
        screenPager.setAdapter(introViewPagerAdapter);

        tabIndicator.setupWithViewPager(screenPager);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position = screenPager.getCurrentItem();
                if (position < mList.size())
                {
                    position++;
                    screenPager.setCurrentItem(position);
                }

                if (position == mList.size() - 1)
                {
                    loadLastScreen();
                }
            }
        });

        tabIndicator.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if (tab.getPosition() == mList.size() - 1)
                {
                    loadLastScreen();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainActivity = new Intent(getApplicationContext(), GmailLogin_Activity.class);
                startActivity(mainActivity);

                savePrefsData();
                finish();
            }
        });


    }

    private boolean restroePrefData()
    {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs",MODE_PRIVATE);
        Boolean isIntroActivityOpenedBefore = pref.getBoolean("isIntroOpened", false);
        return isIntroActivityOpenedBefore;
    }

    private void savePrefsData()
    {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isIntroOpened", true);
        editor.commit();
    }

    private void loadLastScreen()
    {
        btnNext.setVisibility(View.INVISIBLE);
        btnGetStarted.setVisibility(View.VISIBLE);
        tabIndicator.setVisibility(View.INVISIBLE);

        btnGetStarted.setAnimation(btnAnimation);
    }
}
