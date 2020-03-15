package com.example.studcam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.studcam.Prevalent.Save1stPage;

import io.paperdb.Paper;

public class SelectGradExam_Activity extends AppCompatActivity {


    RelativeLayout enggTypeLayout, enggOtherLayout,medicalTypeLayout, medicalType1Layout, medicalType2Layout, BscTypeLayout, BAcommTypeLayout, BAartsTypeLayout ;
    String subject;
    String subCode;

    int string_size;
    String []enggSubjectCode = new String[] {"ce", "ec","ee", "me", "cs", "it"};
    String []medicalSubjectCode = new String[] {"obst"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_grad_exam_);

        subject = getIntent().getExtras().getString("subject");
        string_size = enggSubjectCode.length;

        Paper.init(this);

        enggTypeLayout = findViewById(R.id.engg_exam_layout);
        enggOtherLayout = findViewById(R.id.engg_others_layout);
        medicalTypeLayout = findViewById(R.id.medical_exam_layout);
        medicalType1Layout = findViewById(R.id.medical_exam_type1_layout);
        medicalType2Layout = findViewById(R.id.medical_exam_type2_layout);
        BscTypeLayout = findViewById(R.id.bsc_layout);
        BAcommTypeLayout = findViewById(R.id.ba_comm_layout);
        BAartsTypeLayout = findViewById(R.id.ba_layout);

        init();
        initDetails();

    }

    private void initDetails()
    {
        String data = Paper.book().read(Save1stPage.higherExamId);

        if (TextUtils.isEmpty(data))
        {
            return;
        }
        else {

            for(int i=0; i<string_size-1;i++)
            {
                if(data.equals(enggSubjectCode[i]))
                {
                    Intent intent = new Intent(SelectGradExam_Activity.this, HigherExamDetails_Activity.class);
                    intent.putExtra("course", data);
                    startActivity(intent);
                    finish();
                }
            }


//            if (data.equals("ce")) {
//                Intent intent = new Intent(SelectGradExam_Activity.this, HigherExamDetails_Activity.class);
//                intent.putExtra("course", "ce");
//                startActivity(intent);
//                finish();
//            } else if (data.equals("ec")) {
//
//                Intent intent = new Intent(SelectGradExam_Activity.this, HigherExamDetails_Activity.class);
//                intent.putExtra("course", "ec");
//                startActivity(intent);
//                finish();
//            } else if (data.equals("ee")) {
//
//                Intent intent = new Intent(SelectGradExam_Activity.this, HigherExamDetails_Activity.class);
//                intent.putExtra("course", "ee");
//                startActivity(intent);
//                finish();
//
//            } else if (data.equals("me")) {
//                Intent intent = new Intent(SelectGradExam_Activity.this, HigherExamDetails_Activity.class);
//                intent.putExtra("course", "me");
//                startActivity(intent);
//                finish();
//
//            } else if (data.equals("cs")) {
//                Intent intent = new Intent(SelectGradExam_Activity.this, HigherExamDetails_Activity.class);
//                intent.putExtra("course", "cs");
//                startActivity(intent);
//                finish();
//
//            } else if (data.equals("it")) {
//                Intent intent = new Intent(SelectGradExam_Activity.this, HigherExamDetails_Activity.class);
//                intent.putExtra("course", "it");
//                startActivity(intent);
//                finish();
//
//            }
//
//            //Medical Type - 1
             if (data.equals("obst")) {

                Intent intent = new Intent(SelectGradExam_Activity.this, HigherExamDetails_Activity.class);
                intent.putExtra("course", "obst");
                startActivity(intent);
                finish();
            } else if (data.equals("neur")) {

                Intent intent = new Intent(SelectGradExam_Activity.this, HigherExamDetails_Activity.class);
                intent.putExtra("course", "neur");
                startActivity(intent);
                finish();

            } else if (data.equals("endo")) {
                Intent intent = new Intent(SelectGradExam_Activity.this, HigherExamDetails_Activity.class);
                intent.putExtra("course", "endo");
                startActivity(intent);
                finish();

            } else if (data.equals("paediatric")) {
                Intent intent = new Intent(SelectGradExam_Activity.this, HigherExamDetails_Activity.class);
                intent.putExtra("course", "paediatric");
                startActivity(intent);
                finish();

            }
            else if (data.equals("dermato")) {
                Intent intent = new Intent(SelectGradExam_Activity.this, HigherExamDetails_Activity.class);
                intent.putExtra("course", "dermato");
                startActivity(intent);
                finish();

            } else if (data.equals("pathology")) {
                Intent intent = new Intent(SelectGradExam_Activity.this, HigherExamDetails_Activity.class);
                intent.putExtra("course", "pathology");
                startActivity(intent);
                finish();

            } else if (data.equals("orthopaedics")) {
                Intent intent = new Intent(SelectGradExam_Activity.this, HigherExamDetails_Activity.class);
                intent.putExtra("course", "orthopaedics");
                startActivity(intent);
                finish();

            }
            else if (data.equals("cardio")) {
                Intent intent = new Intent(SelectGradExam_Activity.this, HigherExamDetails_Activity.class);
                intent.putExtra("course", "cardio");
                startActivity(intent);
                finish();

            } else if (data.equals("gynaecology")) {
                Intent intent = new Intent(SelectGradExam_Activity.this, HigherExamDetails_Activity.class);
                intent.putExtra("course", "gynaecology");
                startActivity(intent);
                finish();

            } else if (data.equals("psychi")) {
                Intent intent = new Intent(SelectGradExam_Activity.this, HigherExamDetails_Activity.class);
                intent.putExtra("course", "psychi");
                startActivity(intent);
                finish();

            }
            else if (data.equals("radio_Diagnosis")) {

                Intent intent = new Intent(SelectGradExam_Activity.this, HigherExamDetails_Activity.class);
                intent.putExtra("course", "radio_Diagnosis");
                startActivity(intent);
                finish();
            } else if (data.equals("Others")) {

                Intent intent = new Intent(SelectGradExam_Activity.this, HigherExamDetails_Activity.class);
                intent.putExtra("course", "Others");
                startActivity(intent);
                finish();

            }

            else {
                Intent intent = new Intent(SelectGradExam_Activity.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        }
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






        //Engineering

        findViewById(R.id.engg_others).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enggOtherLayout.setVisibility(View.VISIBLE);
            }
        });

        findViewById(R.id.ce).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Paper.book().write(Save1stPage.higherExamId, "ce");
                subCode = "ce";
                Intent intent = new Intent(getApplicationContext(), HigherExamDetails_Activity.class);
                intent.putExtra("course", subCode);
                startActivity(intent);
                finish();
            }
        });


        findViewById(R.id.ec).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Paper.book().write(Save1stPage.higherExamId, "ec");
                subCode = "ec";
                Intent intent = new Intent(getApplicationContext(), HigherExamDetails_Activity.class);
                intent.putExtra("course", subCode);
                startActivity(intent);
                finish();
            }
        });

        findViewById(R.id.ee).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Paper.book().write(Save1stPage.higherExamId, "ee");
                subCode = "ee";
                Intent intent = new Intent(getApplicationContext(), HigherExamDetails_Activity.class);
                intent.putExtra("course", subCode);
                startActivity(intent);
                finish();
            }
        });

        findViewById(R.id.me).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Paper.book().write(Save1stPage.higherExamId, "me");
                subCode = "me";
                Intent intent = new Intent(getApplicationContext(), HigherExamDetails_Activity.class);
                intent.putExtra("course", subCode);
                startActivity(intent);
                finish();
            }
        });

        findViewById(R.id.cs).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Paper.book().write(Save1stPage.higherExamId, "cs");
                subCode = "cs";
                Intent intent = new Intent(getApplicationContext(), HigherExamDetails_Activity.class);
                intent.putExtra("course", subCode);
                startActivity(intent);
                finish();
            }
        });

        findViewById(R.id.it).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Paper.book().write(Save1stPage.higherExamId, "it");
                subCode = "it";
                Intent intent = new Intent(getApplicationContext(), HigherExamDetails_Activity.class);
                intent.putExtra("course", subCode);
                startActivity(intent);
                finish();
            }
        });



        //Medical

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


        findViewById(R.id.obst).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Paper.book().write(Save1stPage.higherExamId, "obst");
                subCode = "obst";
                Intent intent = new Intent(getApplicationContext(), HigherExamDetails_Activity.class);
                intent.putExtra("course", subCode);
                startActivity(intent);
                finish();
            }
        });

        findViewById(R.id.neur).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Paper.book().write(Save1stPage.higherExamId, "neur");
                subCode = "neur";
                Intent intent = new Intent(getApplicationContext(), HigherExamDetails_Activity.class);
                intent.putExtra("course", subCode);
                startActivity(intent);
                finish();
            }
        });

        findViewById(R.id.endo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Paper.book().write(Save1stPage.higherExamId, "endo");
                subCode = "endo";
                Intent intent = new Intent(getApplicationContext(), HigherExamDetails_Activity.class);
                intent.putExtra("course", subCode);
                startActivity(intent);
                finish();
            }
        });

        findViewById(R.id.paediatric).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Paper.book().write(Save1stPage.higherExamId, "paediatric");
                subCode = "paediatric";
                Intent intent = new Intent(getApplicationContext(), HigherExamDetails_Activity.class);
                intent.putExtra("course", subCode);
                startActivity(intent);
                finish();
            }
        });

        findViewById(R.id.dermato).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Paper.book().write(Save1stPage.higherExamId, "dermato");
                subCode = "dermato";
                Intent intent = new Intent(getApplicationContext(), HigherExamDetails_Activity.class);
                intent.putExtra("course", subCode);
                startActivity(intent);
                finish();
            }
        });


        findViewById(R.id.pathology).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Paper.book().write(Save1stPage.higherExamId, "pathology");
                subCode = "pathology";
                Intent intent = new Intent(getApplicationContext(), HigherExamDetails_Activity.class);
                intent.putExtra("course", subCode);
                startActivity(intent);
                finish();
            }
        });



        findViewById(R.id.orthopaedics).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Paper.book().write(Save1stPage.higherExamId, "orthopaedics");
                subCode = "orthopaedics";
                Intent intent = new Intent(getApplicationContext(), HigherExamDetails_Activity.class);
                intent.putExtra("course", subCode);
                startActivity(intent);
                finish();
            }
        });


        findViewById(R.id.cardio).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Paper.book().write(Save1stPage.higherExamId, "cardio");
                subCode = "cardio";
                Intent intent = new Intent(getApplicationContext(), HigherExamDetails_Activity.class);
                intent.putExtra("course", subCode);
                startActivity(intent);
                finish();
            }
        });


        findViewById(R.id.gynaecology).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Paper.book().write(Save1stPage.higherExamId, "gynaecology");
                subCode = "gynaecology";
                Intent intent = new Intent(getApplicationContext(), HigherExamDetails_Activity.class);
                intent.putExtra("course", subCode);
                startActivity(intent);
                finish();
            }
        });


        findViewById(R.id.psychi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Paper.book().write(Save1stPage.higherExamId, "psychi");
                subCode = "psychi";
                Intent intent = new Intent(getApplicationContext(), HigherExamDetails_Activity.class);
                intent.putExtra("course", subCode);
                startActivity(intent);
                finish();
            }
        });


        findViewById(R.id.radio).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Paper.book().write(Save1stPage.higherExamId, "radio_Diagnosis");
                subCode = "radio_Diagnosis";
                Intent intent = new Intent(getApplicationContext(), HigherExamDetails_Activity.class);
                intent.putExtra("course", subCode);
                startActivity(intent);
                finish();
            }
        });


        findViewById(R.id.medical_type1_others).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Paper.book().write(Save1stPage.higherExamId, "Others");
                subCode = "Others";
                Intent intent = new Intent(getApplicationContext(), HigherExamDetails_Activity.class);
                intent.putExtra("course", subCode);
                startActivity(intent);
                finish();
            }
        });






    }

}
