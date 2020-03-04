package com.example.studcam.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studcam.ClickListner.ExamClickListner;
import com.example.studcam.R;

public class ExamViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public ImageView imageView;
    public TextView Caption;
    public ExamClickListner examClickListner;


    public ExamViewHolder(@NonNull View itemView)
    {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.exam_image);
        Caption = (TextView) itemView.findViewById(R.id.exam_text);

    }

    public void setExamClickListner(ExamClickListner listener)
    {
        this.examClickListner = listener;
    }

    @Override
    public void onClick(View v)
    {
        examClickListner.onClick(v,getAdapterPosition(),false);
    }
}
