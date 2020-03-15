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
    String []enggSubjectCode = new String[] {"ce", "ec","ee", "me", "cs", "it", "ag", "ar", "bm","bt", "ch", "cy", "ey", "gg", "in", "ma", "mn","mt","pe","ph","pi","st","tf","xe_a","xe_b","xe_c","xe_d","xe_e","xe_f","xe_g","xl_p","xl_q","xl_r","xl_s","xl_t","xe_u" };
    String []medicalSubjectCode = new String[] {"obst","ortho","neuro","cardio","endo","gynae","paediatric","psychi","dermato","radio","pathology","medical_type1_others","paedia","psurgery","cardio_thoracic","uro","cardiac","cosmestic_sur","ent","ophtha","gynaecology","obste","orthopaedics","medical_type2_others"};
    String []bScSubjectCode = new String[] {"bio","bioc","botany","chem","evs","math","phy","zoo","bsc_others"};
    String []baComSubjectCode = new String[] {"acco","cost","stat","manage","human","comp","economy","eng","law","marketin","finance","ba_comm_others"};
    String []baSubjectCode = new String[] {"hist","eco","geo","politsc", "Socio","philo","human_rt","inform","ba_others"};

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

            for(int i=0; i<string_size-1;i++)
            {
                if(data.equals(medicalSubjectCode[i]))
                {
                    Intent intent = new Intent(SelectGradExam_Activity.this, HigherExamDetails_Activity.class);
                    intent.putExtra("course", data);
                    startActivity(intent);
                    finish();
                }
            }

            for(int i=0; i<string_size-1;i++)
            {
                if(data.equals(bScSubjectCode[i]))
                {
                    Intent intent = new Intent(SelectGradExam_Activity.this, HigherExamDetails_Activity.class);
                    intent.putExtra("course", data);
                    startActivity(intent);
                    finish();
                }
            }
            for(int i=0; i<string_size-1;i++)
            {
                if(data.equals(baSubjectCode[i]))
                {
                    Intent intent = new Intent(SelectGradExam_Activity.this, HigherExamDetails_Activity.class);
                    intent.putExtra("course", data);
                    startActivity(intent);
                    finish();
                }
            }


            for(int i=0; i<string_size-1;i++)
            {
                if(data.equals(baComSubjectCode[i]))
                {
                    Intent intent = new Intent(SelectGradExam_Activity.this, HigherExamDetails_Activity.class);
                    intent.putExtra("course", data);
                    startActivity(intent);
                    finish();
                }
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
