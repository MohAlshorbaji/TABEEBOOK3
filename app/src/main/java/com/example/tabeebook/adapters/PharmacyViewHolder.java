package com.example.tabeebook.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tabeebook.R;

public class PharmacyViewHolder extends RecyclerView.ViewHolder {
    ImageView pharmacyImg;
    TextView pharmacyName;
    TextView pharmacyAddress;

    OnPharmacyClickListener mOnPharmacyClickListener;

    public PharmacyViewHolder(@NonNull View itemView) {
        super(itemView);
        pharmacyName = itemView.findViewById(R.id.name_pharmacy);
        pharmacyImg = itemView.findViewById(R.id.image_pharmacy);
        pharmacyAddress = itemView.findViewById(R.id.address_clinic);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnPharmacyClickListener.OnPharmacyClick(view, getAdapterPosition());
            }
        });
    }


    public interface OnPharmacyClickListener {
        void OnPharmacyClick(View view, int position);
    }

    public void setOnPharmacyClickListener(OnPharmacyClickListener onPharmacyClickListener) {
        this.mOnPharmacyClickListener = onPharmacyClickListener;
    }
}