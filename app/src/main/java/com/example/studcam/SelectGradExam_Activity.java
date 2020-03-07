package com.example.studcam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class SelectGradExam_Activity extends AppCompatActivity {


    RelativeLayout enggTypeLayout, enggOtherLayout,medicalTypeLayout, medicalType1Layout, medicalType2Layout, BscTypeLayout, BAcommTypeLayout, BAartsTypeLayout ;
    String subject;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_grad_exam_);

        subject = getIntent().getExtras().getString("subject");


        enggTypeLayout = findViewById(R.id.engg_exam_layout);
        enggOtherLayout = findViewById(R.id.engg_others_layout);
        medicalTypeLayout = findViewById(R.id.medical_exam_layout);
        medicalType1Layout = findViewById(R.id.medical_exam_type1_layout);
        medicalType2Layout = findViewById(R.id.medical_exam_type2_layout);
        BscTypeLayout = findViewById(R.id.bsc_layout);
        BAcommTypeLayout = findViewById(R.id.ba_comm_layout);
        BAartsTypeLayout = findViewById(R.id.ba_layout);
        //B = findViewById(R.id.engg_exam_layout);
        //BscTypeLayout = findViewById(R.id.engg_exam_layout);

        init();

    }

    private void init()
    {
        if (subject.equals("engg"))
        {
            enggTypeLayout.setVisibility(View.VISIBLE);
        }
        if (subject.equals("medical"))
        {
            medicalTypeLayout.setVisibility(View.VISIBLE);
        }
        if (subject.equals("bsc_sc"))
        {
            BscTypeLayout.setVisibility(View.VISIBLE);
        }
        if (subject.equals("bsc_comm"))
        {
            BAcommTypeLayout.setVisibility(View.VISIBLE);
        }
        if (subject.equals("ba"))
        {
            BAartsTypeLayout.setVisibility(View.VISIBLE);
        }

        findViewById(R.id.engg_others).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enggOtherLayout.setVisibility(View.VISIBLE);
            }
        });

        findViewById(R.id.md).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                medicalType1Layout.setVisibility(View.VISIBLE);
            }
        });


        findViewById(R.id.ms).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                medicalType2Layout.setVisibility(View.VISIBLE);
            }
        });


    }
}
