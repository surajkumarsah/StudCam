package com.example.studcam;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

public class AppIntro_Activity extends AppIntro {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        addSlide(AppIntroFragment.newInstance("1st Title", "1st Description", R.drawable.undraw_podcast_q6p7, 0xFF039BE5));
        addSlide(AppIntroFragment.newInstance("2nd Title", "2nd Description", R.drawable.undraw_modern_design_v3wv, 0xFF039BE5));
        addSlide(AppIntroFragment.newInstance("3rd Title", "3rd Description", R.drawable.undraw_moments_0y20, 0xFF039BE5));
        addSlide(AppIntroFragment.newInstance("4th Title", "4th Description", R.drawable.undraw_dev_focus_b9xo, 0xFF039BE5));

        showSkipButton(true);

    }

    @Override
    public void onSkipPressed() {
        super.onSkipPressed();

        Intent intent = new Intent(AppIntro_Activity.this, GmailLogin_Activity.class);
        startActivity(intent);

    }

    @Override
    public void onDonePressed() {
        super.onDonePressed();
        Intent intent = new Intent(AppIntro_Activity.this, GmailLogin_Activity.class);
        startActivity(intent);

    }
}
