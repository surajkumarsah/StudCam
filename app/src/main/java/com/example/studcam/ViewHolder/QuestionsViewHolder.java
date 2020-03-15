package com.example.studcam.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studcam.ClickListner.QuestionClickListner;
import com.example.studcam.R;

public class QuestionsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public ImageView problemImg;
    public TextView submittedBy, date;
    QuestionClickListner questionClickListner;

    public QuestionsViewHolder(@NonNull View itemView) {
        super(itemView);

        problemImg = itemView.findViewById(R.id.prlm_img);
        submittedBy = itemView.findViewById(R.id.prlb_submitted_by);
        date = itemView.findViewById(R.id.prlm_date);
    }

public void setQuestionClickListner(QuestionClickListner listener)
    {
        this.questionClickListner = listener;
    }


    @Override
    public void onClick(View v) {

    }
}
