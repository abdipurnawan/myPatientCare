package com.example.praktikum.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.praktikum.Model.PendaftaranWithUsers;
import com.example.praktikum.DetailRiwayatrgsActivity;
import com.example.praktikum.R;

import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class AdapterRegisPending extends RecyclerView.Adapter<AdapterRegisPending.ViewHolder> {

    List<PendaftaranWithUsers>pendaftaranList;

    Context context;

    public AdapterRegisPending(List<PendaftaranWithUsers> pendaftaranList, Context context) {
        this.pendaftaranList = pendaftaranList;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_riwayatrgs_menu, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText("No. Pendaftaran: "+(position+1));
        holder.textView1.setText("Keluhan: "+pendaftaranList.get(position).pendaftaran.getKeluhan());
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(context, DetailRiwayatrgsActivity.class);
                intent1.putExtra("id", pendaftaranList.get(position).pendaftaran.getID());
                intent1.putExtra("position", position);
                intent1.setFlags(FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent1);
            }
        });


    }

    @Override
    public int getItemCount() {

        return pendaftaranList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView, textView1;
        Button button1, button2;
        ConstraintLayout constraintLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.bukgedebuk);
            textView = itemView.findViewById(R.id.itemDate);
            textView1 = itemView.findViewById(R.id.penyakitrgs);
            button1 = itemView.findViewById(R.id.editrwtrgs);
            button2 = itemView.findViewById(R.id.delrwtrgs);
            constraintLayout = itemView.findViewById(R.id.consrwtrgs);
        }
    }
}
