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

public class AdminAdapterListAdmin extends RecyclerView.Adapter<AdminAdapterListAdmin.ViewHolder> {

    List<String> namaadm;
    List<String>emailadm;

    Context context;

    public AdminAdapterListAdmin(List<String> namausr, List<String> emailusr, Context context) {
        this.namaadm = namausr;
        this.emailadm = emailusr;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_admin_listadmin, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.textView.setText(namaadm.get(position));
        holder.textView1.setText(emailadm.get(position));
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, nopdftar.get(position), Toast.LENGTH_SHORT).show();

                Intent intent =  new Intent(context, AdminDetailListadmActivity.class);
                intent.putExtra("namaadm", namaadm.get(position));
                intent.putExtra("emailadm", emailadm.get(position));

                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {

        return namaadm.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView, textView1;
        ConstraintLayout constraintLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.profilrecy1);
            textView = itemView.findViewById(R.id.Namaadmin);
            textView1 = itemView.findViewById(R.id.emailadmin);
            constraintLayout = itemView.findViewById(R.id.conslayadmin);
        }
    }
}
