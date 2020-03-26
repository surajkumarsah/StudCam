package com.example.studcam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.studcam.Prevalent.Save1stPage;

import io.paperdb.Paper;

public class HigherExamDetails_Activity extends AppCompatActivity {

    String course;
    TextView courseText;
    String coursetext;
    Button addProblem, detectText;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_higher_exam_details_);

        courseText = findViewById(R.id.course);
        addProblem = findViewById(R.id.add_problem);
        detectText = findViewById(R.id.detect_text);

        course = getIntent().getExtras().getString("course");
        coursetext = courseText.getText().toString().trim();

        init();

        addProblem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddProblem_Activity.class);
                startActivity(intent);
            }
        });

        detectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });


    }

    private void init()
    {
        courseText.setText("!!! Welcome "+course);
    }
}
