package com.example.praktikum.Admin;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.praktikum.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class AdminAdapterRiwayat extends RecyclerView.Adapter<AdminAdapterRiwayat.ViewHolder> {
    List<String> nopdftar;
    List<String>pyktList;

    Context context;

    public AdminAdapterRiwayat(List<String> nopdftar, List<String> pyktList, Context context) {
        this.nopdftar = nopdftar;
        this.pyktList = pyktList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_adminrwt, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.textView.setText(nopdftar.get(position));
        holder.textView1.setText(pyktList.get(position));
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, nopdftar.get(position), Toast.LENGTH_SHORT).show();

                Intent intent =  new Intent(context, AdminDetailRwtActivity.class);
                intent.putExtra("keluhan", pyktList.get(position));

                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {

        return nopdftar.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView, textView1;
        ConstraintLayout constraintLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.bukgedebuk1);
            textView = itemView.findViewById(R.id.nopdftr);
            textView1 = itemView.findViewById(R.id.penyakitan);
            constraintLayout = itemView.findViewById(R.id.conslisrgs);
        }
    }
}