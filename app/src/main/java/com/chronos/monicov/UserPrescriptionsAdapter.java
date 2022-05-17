package com.chronos.monicov;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserPrescriptionsAdapter extends RecyclerView.Adapter<UserPrescriptionsAdapter.MyViewHolder> {

    Context context;

    ArrayList<User.Medication> list;

    public UserPrescriptionsAdapter(Context context, ArrayList<User.Medication> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.medication_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        User.Medication userMedication = list.get(position);
        holder.medicationName.setText(userMedication.getMedicineName());
        holder.medicationDuration.setText(userMedication.getMedicineTime());
        holder.medicationTime.setText(userMedication.getMedicineTime());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView medicationName, medicationDuration, medicationTime;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            medicationName = itemView.findViewById(R.id.tvMedicineName);
            medicationDuration = itemView.findViewById(R.id.tvMedicineDuration);
            medicationTime = itemView.findViewById(R.id.tvMedicineTime);
        }
    }
}
