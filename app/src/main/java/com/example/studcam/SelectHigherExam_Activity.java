package com.example.studcam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.studcam.Prevalent.Save1stPage;

import io.paperdb.Paper;

public class SelectHigherExam_Activity extends AppCompatActivity {


    String course;
    RelativeLayout graduationLayout;
    private Button enggBtn, addStudents;
    String subject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_main_exam_);

        course = getIntent().getExtras().getString("course");
        graduationLayout = (RelativeLayout) findViewById(R.id.graduation);
        addStudents = findViewById(R.id.add_Student);


        init();
        initDetails();


        addStudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddProblem_Activity.class);
                startActivity(intent);
            }
        });



        findViewById(R.id.engg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subject = "engg";
                Intent intent = new Intent(SelectHigherExam_Activity.this, SelectGradExam_Activity.class);
                intent.putExtra("subject",subject);
                startActivity(intent);
            }
        });


        findViewById(R.id.medical).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subject = "medical";
                Intent intent = new Intent(SelectHigherExam_Activity.this, SelectGradExam_Activity.class);
                intent.putExtra("subject",subject);
                startActivity(intent);
            }
        });


        findViewById(R.id.bsc_sc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subject = "bsc_sc";
                Intent intent = new Intent(SelectHigherExam_Activity.this, SelectGradExam_Activity.class);
                intent.putExtra("subject",subject);
                startActivity(intent);
            }
        });


        findViewById(R.id.bsc_Comm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subject = "bsc_comm";
                Intent intent = new Intent(SelectHigherExam_Activity.this, SelectGradExam_Activity.class);
                intent.putExtra("subject",subject);
                startActivity(intent);
            }
        });


        findViewById(R.id.ba).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subject = "ba";
                Intent intent = new Intent(SelectHigherExam_Activity.this, SelectGradExam_Activity.class);
                intent.putExtra("subject",subject);
                startActivity(intent);
            }
        });


        findViewById(R.id.bed).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subject = "bed";
                Intent intent = new Intent(SelectHigherExam_Activity.this, SelectGradExam_Activity.class);
                intent.putExtra("subject",subject);
                startActivity(intent);
            }
        });


        findViewById(R.id.llb).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subject = "llb";
                Intent intent = new Intent(SelectHigherExam_Activity.this, SelectGradExam_Activity.class);
                intent.putExtra("subject",subject);
                startActivity(intent);
            }
        });


        findViewById(R.id.others).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subject = "others";
                Intent intent = new Intent(SelectHigherExam_Activity.this, SelectGradExam_Activity.class);
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

    private void initDetails()
    {
        String data = Paper.book().read(Save1stPage.higherExamId);

        if (TextUtils.isEmpty(data))
        {
            return;
        }
        else {

            if (data.equals("ce")) {
                Intent intent = new Intent(SelectHigherExam_Activity.this, HigherExamDetails_Activity.class);
                intent.putExtra("course", "ce");
                startActivity(intent);
                finish();
            } else if (data.equals("ec")) {

                Intent intent = new Intent(SelectHigherExam_Activity.this, HigherExamDetails_Activity.class);
                intent.putExtra("course", "ec");
                startActivity(intent);
                finish();
            } else if (data.equals("ee")) {

                Intent intent = new Intent(SelectHigherExam_Activity.this, HigherExamDetails_Activity.class);
                intent.putExtra("course", "ee");
                startActivity(intent);
                finish();

            } else if (data.equals("me")) {
                Intent intent = new Intent(SelectHigherExam_Activity.this, HigherExamDetails_Activity.class);
                intent.putExtra("course", "me");
                startActivity(intent);
                finish();

            } else if (data.equals("cs")) {
                Intent intent = new Intent(SelectHigherExam_Activity.this, HigherExamDetails_Activity.class);
                intent.putExtra("course", "cs");
                startActivity(intent);
                finish();

            } else if (data.equals("it")) {
                Intent intent = new Intent(SelectHigherExam_Activity.this, HigherExamDetails_Activity.class);
                intent.putExtra("course", "it");
                startActivity(intent);
                finish();

            } else {
                Intent intent = new Intent(SelectHigherExam_Activity.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        }


    }




}
