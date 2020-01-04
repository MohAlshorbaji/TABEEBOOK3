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
import com.example.tabeebook.ClinicDetailsActivity;
import com.example.tabeebook.R;
import com.example.tabeebook.models.Clinic;

import java.util.ArrayList;
import java.util.List;

public class ClinicAdapter extends RecyclerView.Adapter<ClinicViewHolder> implements Filterable {

    Context context;
    List<Clinic> clinicList;
    List<Clinic> clinicListFull;

    @Override
    public Filter getFilter() {
        return clinicFilter;
    }

    private Filter clinicFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Clinic> filteredList = new ArrayList<>();

            if (charSequence == null || charSequence.length() == 0) {
                filteredList.addAll(clinicListFull);
            } else {
                String filterPattern = charSequence.toString().toLowerCase().trim();

                for (Clinic clinic : clinicListFull) {
                    if (clinic.getName().concat(" ").concat(clinic.getAddress())
                            .concat(" ").concat(clinic.getType())
                            .contains(filterPattern)) {
                        filteredList.add(clinic);
                    } else if (clinic.getAddress().concat(" ").concat(clinic.getName())
                            .concat(" ").concat(clinic.getType())
                            .contains(filterPattern)) {
                        filteredList.add(clinic);
                    } else if (clinic.getType().concat(" ")
                            .concat(clinic.getName()).concat(" ").concat(clinic.getAddress())
                            .contains(filterPattern)) {
                        filteredList.add(clinic);
                    } else if (clinic.getType().concat(" ")
                            .concat(clinic.getAddress()).concat(" ").concat(clinic.getName())
                            .contains(filterPattern)) {
                        filteredList.add(clinic);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }


        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            clinicList.clear();
            clinicList.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }
    };

    public ClinicAdapter(Context context, List<Clinic> clinicList) {
        this.context = context;
        this.clinicList = clinicList;
        clinicListFull = new ArrayList<>(clinicList);

    }

    @NonNull
    @Override
    public ClinicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.clinic_item, parent, false);
        ClinicViewHolder clinicViewHolder = new ClinicViewHolder(root);
        clinicViewHolder.setOnClinicClickListener(new ClinicViewHolder.OnClinicClickListener() {
            @Override
            public void OnClinicClick(View view, int position) {
                String clinicName = clinicList.get(position).getName();
                String clinicAddress = clinicList.get(position).getAddress();
                String clinicPhoneNumber = clinicList.get(position).getPhoneNumber();
                String clinicOwner = clinicList.get(position).getOwner();
                String clinicType = clinicList.get(position).getType();
                String clinicOpeningHours = clinicList.get(position).getOpeiningHours();
                String clinicImage = clinicList.get(position).getImage();
                Intent intent = new Intent(context, ClinicDetailsActivity.class);
                intent.putExtra("clinicName", clinicName);
                intent.putExtra("clinicAddress", clinicAddress);
                intent.putExtra("clinicPhoneNumber", clinicPhoneNumber);
                intent.putExtra("clinicOwner", clinicOwner);
                intent.putExtra("clinicType", clinicType);
                intent.putExtra("clinicOpeningHours", clinicOpeningHours);
                intent.putExtra("clinicImage", clinicImage);
                context.startActivity(intent);
            }
        });
        return clinicViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ClinicViewHolder holder, int position) {
        holder.clinicName.setText(clinicList.get(position).getName());
        holder.clinicAddress.setText(clinicList.get(position).getAddress());
        holder.clinictype.setText(clinicList.get(position).getType());
        Glide.with(context).load(clinicList.get(position).getImage()).into(holder.clinicImg);

        Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
        holder.itemView.startAnimation(animation);
    }

    @Override
    public int getItemCount() {
        return clinicList.size();
    }
}
