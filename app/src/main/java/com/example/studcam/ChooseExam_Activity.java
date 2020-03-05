package com.example.studcam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.airbnb.lottie.LottieAnimationView;
import com.example.studcam.Prevalent.Prevalent;
import com.example.studcam.ViewHolder.ExamViewHolder;
import com.example.studcam.model.ExamView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import io.paperdb.Paper;

public class ChooseExam_Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private DatabaseReference GalleryRef;
    private RecyclerView recyclerView;
    String data;
    private LottieAnimationView progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_exam_);

        Paper.init(ChooseExam_Activity.this);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        GalleryRef = FirebaseDatabase.getInstance().getReference().child("ChooseExams");
        recyclerView = findViewById(R.id.exam_recycler_menu);
        recyclerView.setHasFixedSize(true);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        progressBar = (LottieAnimationView) findViewById(R.id.progressBar);

        progressBar.setVisibility(View.VISIBLE);

    }

//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//        return false;
//    }

    @Override
    protected void onStart()
    {
        super.onStart();

        FirebaseRecyclerOptions<ExamView> options = new FirebaseRecyclerOptions.Builder<ExamView>().setQuery(GalleryRef,ExamView.class).build();

        FirebaseRecyclerAdapter<ExamView, ExamViewHolder> adapter = new FirebaseRecyclerAdapter<ExamView, ExamViewHolder>(options) {

            @Override
            protected void onBindViewHolder(@NonNull ExamViewHolder examViewHolder, int i, @NonNull final ExamView examView)
            {
                Picasso.get().load(examView.getImageLink()).into(examViewHolder.imageView);
                examViewHolder.Caption.setText(examView.getName());
                Paper.book().write(Prevalent.id, examView.getId());

                examViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        progressBar.setVisibility(View.GONE);
                        Intent intent = new Intent(ChooseExam_Activity.this,MainActivity.class);
                        startActivity(intent);
                    }
                });

            }

            @NonNull
            @Override
            public ExamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
            {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.choose_exam,parent,false);
                ExamViewHolder holder = new ExamViewHolder(view);
                return holder;
            }
        };

        recyclerView.setAdapter(adapter);
        adapter.startListening();


    }





    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (mToggle.onOptionsItemSelected(item))
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId())
        {
            case R.id.login :
                Intent intent = new Intent(ChooseExam_Activity.this,MainActivity.class);
                startActivity(intent);

                break;

            case R.id.dashboard :
                Intent intent1 = new Intent(ChooseExam_Activity.this,MainActivity.class);
                startActivity(intent1);

                break;

            case R.id.search :
                Intent intent2 = new Intent(ChooseExam_Activity.this,MainActivity.class);
                startActivity(intent2);

                break;

            case R.id.events :
                Intent intent3 = new Intent(ChooseExam_Activity.this,MainActivity.class);
                startActivity(intent3);

                break;

            case R.id.setting :
                Intent intent4 = new Intent(ChooseExam_Activity.this,MainActivity.class);
                startActivity(intent4);

                break;

            case R.id.activities :
                Intent intent5 = new Intent(ChooseExam_Activity.this,MainActivity.class);
                startActivity(intent5);
                break;


            case R.id.nav_share :
                Intent intent6 = new Intent(ChooseExam_Activity.this,MainActivity.class);
                startActivity(intent6);
                break;


            case R.id.nav_send :
                Intent intent7 = new Intent(ChooseExam_Activity.this,MainActivity.class);
                startActivity(intent7);
                break;

        }

        return true;
    }


}
