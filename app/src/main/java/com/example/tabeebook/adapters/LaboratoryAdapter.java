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
import com.example.tabeebook.LaboratoryDetailsActivity;
import com.example.tabeebook.PharmacyDetailsActivity;
import com.example.tabeebook.R;
import com.example.tabeebook.models.Laboratory;

import java.util.ArrayList;
import java.util.List;

public class LaboratoryAdapter extends RecyclerView.Adapter<LaboratoryViewHolder> implements Filterable {

    Context context;
    List<Laboratory> laboratoryList;
    List<Laboratory> laboratoryListFull;

    @Override
    public Filter getFilter() {
        return laboratoryFilter;
    }

    private Filter laboratoryFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Laboratory> filteredList = new ArrayList<>();

            if (charSequence == null || charSequence.length() == 0) {
                filteredList.addAll(laboratoryListFull);
            } else {
                String filterPattern = charSequence.toString().toLowerCase().trim();

                for (Laboratory laboratory : laboratoryListFull) {
                    if (laboratory.getName().concat(" ").concat(laboratory.getAddress())
                            .toLowerCase().contains(filterPattern)) {
                        filteredList.add(laboratory);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }


        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            laboratoryList.clear();
            laboratoryList.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }
    };

    public LaboratoryAdapter(Context context, List<Laboratory> laboratoryList) {
        this.context = context;
        this.laboratoryList = laboratoryList ;
        this.laboratoryListFull = new ArrayList<>(laboratoryList);
    }

    @NonNull
    @Override
    public LaboratoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.laboratory_item, parent, false);
        LaboratoryViewHolder laboratoryViewHolder = new LaboratoryViewHolder(root);
        laboratoryViewHolder.setOnLaboratoryClickListener(new  LaboratoryViewHolder.OnLaboratoryClickListener() {
            @Override
            public void OnLaboratoryClick(View view, int position) {

                String laboratoryName = laboratoryList.get(position).getName();
                String laboratoryAddress = laboratoryList.get(position).getAddress();
                String laboratoryPhoneNumber =laboratoryList.get(position).getPhoneNumber();
               // String pharmacyOwner = laboratoryList.get(position).getOwner();
                String laboratoryOpeningHours = laboratoryList.get(position).getOpeiningHours();
                String laboratoryImage = laboratoryList.get(position).getImage();
                Intent intent = new Intent(context, LaboratoryDetailsActivity.class);
                intent.putExtra("laboratoryName", laboratoryName);
                intent.putExtra("laboratoryAddress", laboratoryAddress);
                intent.putExtra("laboratoryPhoneNumber", laboratoryPhoneNumber);
                //intent.putExtra("pharmacyOwner", pharmacyOwner);
                intent.putExtra("laboratoryOpeningHours", laboratoryOpeningHours);
                intent.putExtra("laboratoryImage", laboratoryImage);
                context.startActivity(intent);
            }
        });
        return laboratoryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LaboratoryViewHolder holder, int position) {
        holder.laboratoryName.setText(laboratoryList.get(position).getName());
        holder.laboratoryAddress.setText(laboratoryList.get(position).getAddress());
        Glide.with(context).load(laboratoryList.get(position).getImage()).into(holder.laboratoryImg);

        Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
        holder.itemView.startAnimation(animation);
    }

    @Override
    public int getItemCount() {
        return laboratoryList.size();
    }
}

