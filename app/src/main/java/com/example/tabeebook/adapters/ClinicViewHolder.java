package com.example.tabeebook.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tabeebook.R;

public class ClinicViewHolder extends RecyclerView.ViewHolder {
    ImageView clinicImg;
    TextView clinicName;
    TextView clinicAddress;
    TextView clinictype;

    OnClinicClickListener mOnClinicClickListener;


    public ClinicViewHolder(@NonNull View itemView) {
        super(itemView);
        clinicImg = itemView.findViewById(R.id.image_clinic);
        clinicName = itemView.findViewById(R.id.name_clinic);
        clinicAddress = itemView.findViewById(R.id.country_clinic);
        clinictype = itemView.findViewById(R.id.type_clinic);


        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnClinicClickListener.OnClinicClick(view, getAdapterPosition());

            }
        });
    }

    public interface OnClinicClickListener {
        void OnClinicClick(View view, int position);
    }

    public void setOnClinicClickListener(OnClinicClickListener onClinicClickListener) {
        this.mOnClinicClickListener = onClinicClickListener;
    }

}

