package com.example.studcam.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studcam.ClickListner.ExamClickListner;
import com.example.studcam.ClickListner.TestClickListner;
import com.example.studcam.R;

public class TestViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{

    public TextView Caption;
    public TestClickListner examClickListner;


    public TestViewHolder(@NonNull View itemView)
    {
        super(itemView);
        Caption = (TextView) itemView.findViewById(R.id.test);

    }

    public void setTestClickListner(TestClickListner listener)
    {
        this.examClickListner = listener;
    }

    @Override
    public void onClick(View v)
    {
        examClickListner.onClick(v,getAdapterPosition(),false);
    }
}
