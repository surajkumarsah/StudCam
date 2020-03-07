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
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.airbnb.lottie.LottieAnimationView;
import com.example.studcam.Prevalent.Prevalent;
import com.example.studcam.ViewHolder.ExamViewHolder;
import com.example.studcam.ViewHolder.TestViewHolder;
import com.example.studcam.model.ExamView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import io.paperdb.Paper;

public class ChooseExam_Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    //Toolbar toolbar;
    private DatabaseReference GalleryRef;
    private RecyclerView recyclerView;
    String data;
    TextView userName, userMail;
    CircleImageView userImage;
    FirebaseUser user;
    FirebaseAuth mAuth;
    private LottieAnimationView progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_exam_);

        userName = (TextView) findViewById(R.id.user_name);
        userMail = (TextView) findViewById(R.id.user_mail);
        userImage = (CircleImageView) findViewById(R.id.user_image);

        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser() != null) {
            FirebaseUser user = mAuth.getCurrentUser();

//            String photo = String.valueOf(user.getPhotoUrl());
//            userName.setText(user.getDisplayName());
//            userMail.setText(user.getEmail());
//            Picasso.get().load(photo).into(userImage);
        }


        Paper.init(ChooseExam_Activity.this);
//
//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

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

                    @NonNull
                    ViewGroup parent;
                    @Override
                    public void onClick(View view) {
                        progressBar.setVisibility(View.GONE);



                        if (examView.getId().equals("1st_5th"))
                        {
                            Intent intent = new Intent(ChooseExam_Activity.this,SelectMainExam_Activity.class);
                            intent.putExtra("course",examView.getId());
                            startActivity(intent);
                        }
                        else if (examView.getId().equals("6th_8th"))
                        {
                            Intent intent = new Intent(ChooseExam_Activity.this,SelectMainExam_Activity.class);
                            intent.putExtra("course",examView.getId());
                            startActivity(intent);
                        }
                        else if (examView.getId().equals("9th_10th"))
                        {
                            Intent intent = new Intent(ChooseExam_Activity.this,SelectMainExam_Activity.class);
                            intent.putExtra("course",examView.getId());
                            startActivity(intent);
                        }
                        else if (examView.getId().equals("11th_12th"))
                        {
                            Intent intent = new Intent(ChooseExam_Activity.this,SelectMainExam_Activity.class);
                            intent.putExtra("course",examView.getId());
                            startActivity(intent);
                        }
                        else if (examView.getId().equals("graduation"))
                        {
                            Intent intent = new Intent(ChooseExam_Activity.this,SelectMainExam_Activity.class);
                            intent.putExtra("course",examView.getId());
                            startActivity(intent);
                        }
                        else if (examView.getId().equals("higher"))
                        {
                            Intent intent = new Intent(ChooseExam_Activity.this,SelectMainExam_Activity.class);
                            intent.putExtra("course",examView.getId());
                            startActivity(intent);
                        }
                        else
                        {
                            Intent intent = new Intent(ChooseExam_Activity.this,MainActivity.class);
                            startActivity(intent);
                        }

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

        int id = menuItem.getItemId();

        if (id == R.id.login)
        {
            Toast.makeText(this, "Login",Toast.LENGTH_SHORT).show();
        }else if (id == R.id.dashboard)
        {
            Toast.makeText(this, "dashboard",Toast.LENGTH_SHORT).show();

        }
        else if (id == R.id.search)
        {
            Toast.makeText(this, "search",Toast.LENGTH_SHORT).show();

        }
        else
        {
            Toast.makeText(this, "else",Toast.LENGTH_SHORT).show();
        }

        return true;
    }


}
