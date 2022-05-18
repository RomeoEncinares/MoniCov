package com.chronos.monicov;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MedicalProfessionalPatientListAdapter extends RecyclerView.Adapter<MedicalProfessionalPatientListAdapter.MyViewHolder>{

    Context context;

    ArrayList<MedicalProfessional.assignedPatient> list;

    public MedicalProfessionalPatientListAdapter(Context context, ArrayList<MedicalProfessional.assignedPatient> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MedicalProfessionalPatientListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.patient_item, parent, false);
        return new MedicalProfessionalPatientListAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicalProfessionalPatientListAdapter.MyViewHolder holder, int position) {
        MedicalProfessional.assignedPatient patientList = list.get(position);
        holder.patientFirsName.setText(patientList.getPatientFirstName());
        holder.patientLastName.setText(patientList.getPatientLastName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(context, "Item clicked at " + position, Toast.LENGTH_SHORT).show();
                Intent passData = new Intent(view.getContext(), medicalProfessionalPatientStatusLandingActivity.class);
                passData.putExtra("patient", patientList.getPatientEmail());
                context.startActivity(passData);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView patientFirsName, patientLastName, patientEmail;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            patientFirsName = itemView.findViewById(R.id.tvPatientFirstName);
            patientLastName = itemView.findViewById(R.id.tvPatientLastName);
        }
    }
}
