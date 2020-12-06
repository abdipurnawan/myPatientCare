package com.example.praktikum.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.praktikum.Admin.AdminDetailListadmActivity;
import com.example.praktikum.Admin.AdminHomeActivity;
import com.example.praktikum.Admin.AdminListPoli;
import com.example.praktikum.Admin.AdminListUserActivity;
import com.example.praktikum.AuthAndUser.DetailRiwayatrgsActivity;
import com.example.praktikum.AuthAndUser.RiwayatPendingActivity;
import com.example.praktikum.Database.RoomDB;
import com.example.praktikum.Model.Poli;
import com.example.praktikum.Model.User;
import com.example.praktikum.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class AdminAdapterListPoli extends RecyclerView.Adapter<AdminAdapterListPoli.ViewHolder> {

    List<Poli>poliList;
    RoomDB database;
    EditText namaPoli;
    Button btnUpdate;
    private Activity context;

    public AdminAdapterListPoli(List<Poli> poliList, Activity context) {
        this.poliList = poliList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_admin_list_poli, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.textView.setText("Nomor Poli: "+(position+1));
        holder.textView1.setText(poliList.get(position).getPoli());
        holder.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database = RoomDB.getInstance(context);
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_edit_poli);
                int width = WindowManager.LayoutParams.MATCH_PARENT;
                int height = WindowManager.LayoutParams.WRAP_CONTENT;
                dialog.getWindow().setLayout(width,height);
                dialog.show();

                namaPoli = dialog.findViewById(R.id.editTextEditPoli);
                btnUpdate = dialog.findViewById(R.id.btnEditPoli);

                namaPoli.setText(poliList.get(position).getPoli());

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String poliName = namaPoli.getText().toString().trim();
                        int poliId = poliList.get(position).getID();
                        database.poliDao().updatePoli(poliId, poliName);
                        notifyItemChanged(position);
                        notifyDataSetChanged();
                        dialog.dismiss();
                        Intent intent = new Intent(context, AdminListPoli.class);
                        context.startActivity(intent);
                        context.finish();
                        Toast.makeText(context, "Edit Poli Success", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        holder.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Confirm");
                builder.setMessage("Delete Poli?");
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        database = RoomDB.getInstance(context);
                        database.poliDao().deletePoli(poliList.get(position).getID());
                        notifyItemRemoved(position);
                        notifyDataSetChanged();
                        Intent intent = new Intent(context, AdminListPoli.class);
                        context.startActivity(intent);
                        context.finish();
                        Toast.makeText(context, "Delete Poli Success", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {

        return poliList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView, textView1;
        Button button1, button2;
        ConstraintLayout constraintLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.profilrecy1);
            textView = itemView.findViewById(R.id.noPoli);
            textView1 = itemView.findViewById(R.id.namaPoli);
            constraintLayout = itemView.findViewById(R.id.conslayadmin);
            button1 = itemView.findViewById(R.id.editrwtrgs);
            button2 = itemView.findViewById(R.id.delrwtrgs);
        }
    }
}
