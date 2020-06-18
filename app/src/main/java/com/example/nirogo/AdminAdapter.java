package com.example.nirogo;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nirogo.Activities.AppointmentOption;
import com.example.nirogo.Post.PostUploadInfo;
import com.example.nirogo.Profile.DoctorProfileViewOnly;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdminAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<AdminShow> list;
    Context context;

    public AdminAdapter(List<AdminShow> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_admin_show, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        AdminShow itemAdapter = list.get(position);

        ((ViewHolder) holder).nameUser.setText(itemAdapter.getDocName());
        ((ViewHolder) holder).nameDoc.setText(itemAdapter.getDocName());
        ((ViewHolder) holder).date.setText(itemAdapter.getDateApt());
        ((ViewHolder) holder).time.setText(itemAdapter.getTimeApt());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView nameUser, nameDoc, date, time;


        public ViewHolder(View itemView) {
            super(itemView);

            nameDoc = itemView.findViewById(R.id.nameDoc);
            nameUser = itemView.findViewById(R.id.nameUser);
            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);
        }
    }
}
