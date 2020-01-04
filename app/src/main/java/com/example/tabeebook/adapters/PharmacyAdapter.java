package com.example.tabeebook.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tabeebook.PharmacyDetailsActivity;
import com.example.tabeebook.R;
import com.example.tabeebook.models.Pharmacy;

import java.util.ArrayList;
import java.util.List;

public class PharmacyAdapter extends RecyclerView.Adapter<PharmacyViewHolder> implements Filterable {


    Context context;
    List<Pharmacy> pharmacyList;
    List<Pharmacy> pharmacyListFull;


    @Override
    public Filter getFilter() {
        return pharmacyFilter;
    }

    private Filter pharmacyFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Pharmacy> filteredList = new ArrayList<>();

            if (charSequence == null || charSequence.length() == 0) {
                filteredList.addAll(pharmacyListFull);
            } else {
                String filterPattern = charSequence.toString().toLowerCase().trim();

                for (Pharmacy pharmacy : pharmacyListFull) {
                    if (pharmacy.getName().concat(" ").concat(pharmacy.getAddress())
                            .toLowerCase().contains(filterPattern)) {
                        filteredList.add(pharmacy);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }


        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            pharmacyList.clear();
            pharmacyList.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }
    };

    public PharmacyAdapter(Context context, List<Pharmacy> pharmacyList) {
        this.context = context;
        this.pharmacyList = pharmacyList;
        this.pharmacyListFull = new ArrayList<>(pharmacyList);
    }

    @NonNull
    @Override
    public PharmacyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.pharmacy_item, parent, false);
        PharmacyViewHolder pharmacyViewHolder = new PharmacyViewHolder(root);
        pharmacyViewHolder.setOnPharmacyClickListener(new PharmacyViewHolder.OnPharmacyClickListener() {
            @Override
            public void OnPharmacyClick(View view, int position) {
                String pharmacyName = pharmacyList.get(position).getName();
                String pharmacyAddress = pharmacyList.get(position).getAddress();
                String pharmacyPhoneNumber = pharmacyList.get(position).getPhoneNumber();
                String pharmacyOwner = pharmacyList.get(position).getOwner();
                String pharmacyOpeningHours = pharmacyList.get(position).getOpeiningHours();
                String pharmacyImage = pharmacyList.get(position).getImage();
                Intent intent = new Intent(context, PharmacyDetailsActivity.class);
                intent.putExtra("pharmacyName", pharmacyName);
                intent.putExtra("pharmacyAddress", pharmacyAddress);
                intent.putExtra("pharmacyPhoneNumber", pharmacyPhoneNumber);
                intent.putExtra("pharmacyOwner", pharmacyOwner);
                intent.putExtra("pharmacyOpeningHours", pharmacyOpeningHours);
                intent.putExtra("pharmacyImage", pharmacyImage);
                context.startActivity(intent);
            }
        });
        return pharmacyViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PharmacyViewHolder holder, int position) {
        holder.pharmacyName.setText(pharmacyList.get(position).getName());
        holder.pharmacyAddress.setText(pharmacyList.get(position).getAddress());
        Glide.with(context).load(pharmacyList.get(position).getImage()).into(holder.pharmacyImg);

        Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
        holder.itemView.startAnimation(animation);
    }

    @Override
    public int getItemCount() {
        return pharmacyList.size();
    }
}
