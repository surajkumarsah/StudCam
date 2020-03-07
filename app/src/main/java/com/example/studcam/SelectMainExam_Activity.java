package com.example.studcam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class SelectMainExam_Activity extends AppCompatActivity {


    String course;
    RelativeLayout graduationLayout;
    private Button enggBtn;
    String subject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_main_exam_);

        course = getIntent().getExtras().getString("course");
        graduationLayout = (RelativeLayout) findViewById(R.id.graduation);



        init();



        findViewById(R.id.engg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subject = "engg";
                Intent intent = new Intent(SelectMainExam_Activity.this, SelectGradExam_Activity.class);
                intent.putExtra("subject",subject);
                startActivity(intent);
            }
        });


        findViewById(R.id.medical).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subject = "medical";
                Intent intent = new Intent(SelectMainExam_Activity.this, SelectGradExam_Activity.class);
                intent.putExtra("subject",subject);
                startActivity(intent);
            }
        });


        findViewById(R.id.bsc_sc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subject = "bsc_sc";
                Intent intent = new Intent(SelectMainExam_Activity.this, SelectGradExam_Activity.class);
                intent.putExtra("subject",subject);
                startActivity(intent);
            }
        });


        findViewById(R.id.bsc_Comm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subject = "bsc_comm";
                Intent intent = new Intent(SelectMainExam_Activity.this, SelectGradExam_Activity.class);
                intent.putExtra("subject",subject);
                startActivity(intent);
            }
        });


        findViewById(R.id.ba).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subject = "ba";
                Intent intent = new Intent(SelectMainExam_Activity.this, SelectGradExam_Activity.class);
                intent.putExtra("subject",subject);
                startActivity(intent);
            }
        });


        findViewById(R.id.bed).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subject = "bed";
                Intent intent = new Intent(SelectMainExam_Activity.this, SelectGradExam_Activity.class);
                intent.putExtra("subject",subject);
                startActivity(intent);
            }
        });


        findViewById(R.id.llb).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subject = "llb";
                Intent intent = new Intent(SelectMainExam_Activity.this, SelectGradExam_Activity.class);
                intent.putExtra("subject",subject);
                startActivity(intent);
            }
        });


        findViewById(R.id.others).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subject = "others";
                Intent intent = new Intent(SelectMainExam_Activity.this, SelectGradExam_Activity.class);
                intent.putExtra("subject",subject);
                startActivity(intent);
            }
        });


    }

    private void init()
    {
        if (course.equals("graduation"))
        {
            graduationLayout.setVisibility(View.VISIBLE);
        }
    }
}
