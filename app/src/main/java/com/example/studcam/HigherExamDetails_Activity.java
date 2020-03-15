package com.example.studcam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.studcam.Prevalent.Save1stPage;

import io.paperdb.Paper;

public class HigherExamDetails_Activity extends AppCompatActivity {

    String course;
    TextView courseText;
    String coursetext;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_higher_exam_details_);

        courseText = findViewById(R.id.course);
        course = getIntent().getExtras().getString("course");
        coursetext = courseText.getText().toString().trim();

        init();
    }

    private void init()
    {
        courseText.setText("!!! Welcome "+course);
    }
}
