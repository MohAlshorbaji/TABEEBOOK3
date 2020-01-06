package com.example.tabeebook.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tabeebook.R;

public class LaboratoryViewHolder extends RecyclerView.ViewHolder {

    ImageView laboratoryImg;
    TextView laboratoryName;
    TextView laboratoryAddress;
    OnLaboratoryClickListener mOnLaboratoryClickListener;



    public LaboratoryViewHolder(@NonNull View itemView) {
        super(itemView);
        laboratoryName = itemView.findViewById(R.id.name_laboratory);
        laboratoryImg = itemView.findViewById(R.id.image_laboratory);
        laboratoryAddress = itemView.findViewById(R.id.country_laboratory);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnLaboratoryClickListener.OnLaboratoryClick(view, getAdapterPosition());
            }
        });
    }


    public interface OnLaboratoryClickListener {
        void OnLaboratoryClick(View view, int position);
    }

    public void setOnLaboratoryClickListener(LaboratoryViewHolder.OnLaboratoryClickListener onLaboratoryClickListener) {
        this.mOnLaboratoryClickListener = onLaboratoryClickListener;
    }
}