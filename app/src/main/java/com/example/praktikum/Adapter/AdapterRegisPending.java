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

import com.example.praktikum.Template.DetailRiwayatrgsActivity;
import com.example.praktikum.Model.Pendaftaran;
import com.example.praktikum.R;

import java.util.ArrayList;

public class AdapterRegisPending extends RecyclerView.Adapter<AdapterRegisPending.ViewHolder> {

    ArrayList<Pendaftaran>pendaftaranList;

    Context context;

    public AdapterRegisPending(ArrayList<Pendaftaran>pendaftaranList, Context context) {
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

        Pendaftaran pendaftaran = pendaftaranList.get(position);

        holder.textView.setText("No. Pendaftaran: "+(position+1));
        holder.textView1.setText("Keluhan: "+pendaftaran.getKeluhan());
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =  new Intent(context, DetailRiwayatrgsActivity.class);
                intent.putExtra("keluhan", pendaftaran.getKeluhan());
                intent.putExtra("penyakit_bawaan", pendaftaran.getPenyakit_bawaan());
                intent.putExtra("poli", pendaftaran.getPoli());
                intent.putExtra("tinggi", pendaftaran.getTinggi_badan());
                intent.putExtra("berat", pendaftaran.getBerat_badan());
                intent.putExtra("status", pendaftaran.getStatus());
                intent.putExtra("tanggal", pendaftaran.getTgl_regis());
                intent.putExtra("id", pendaftaran.getId());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
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
