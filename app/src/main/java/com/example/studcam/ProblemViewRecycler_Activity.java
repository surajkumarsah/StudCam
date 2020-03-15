package com.example.studcam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.studcam.ViewHolder.QuestionsViewHolder;
import com.example.studcam.model.QuestionsViewModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class ProblemViewRecycler_Activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DatabaseReference prlmRef;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_view_recycler_);

        prlmRef  = FirebaseDatabase.getInstance().getReference().child("SubjectNote");
        recyclerView = findViewById(R.id.prlm_recycler_menu);

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<QuestionsViewModel> options = new FirebaseRecyclerOptions.Builder<QuestionsViewModel>().setQuery(prlmRef, QuestionsViewModel.class).build();

        FirebaseRecyclerAdapter<QuestionsViewModel, QuestionsViewHolder> adapter = new FirebaseRecyclerAdapter<QuestionsViewModel, QuestionsViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull QuestionsViewHolder questionsViewHolder, int i, @NonNull QuestionsViewModel questionsViewModel) {

                Picasso.get().load(questionsViewModel.getImage()).into(questionsViewHolder.problemImg);
                questionsViewHolder.submittedBy.setText(questionsViewModel.getSubmittedby());
                questionsViewHolder.date.setText(questionsViewModel.getDate());
            }

            @NonNull
            @Override
            public QuestionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.classnote_recycler, parent, false);
                QuestionsViewHolder holder = new QuestionsViewHolder(view);
                return holder;
            }
        };

        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
}
