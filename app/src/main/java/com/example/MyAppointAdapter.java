package com.example;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nirogo.AdminShow;
import com.example.nirogo.MyAppointments;
import com.example.nirogo.R;

import java.util.List;

public class MyAppointAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<MyAppointments> list;
    Context context;

    public MyAppointAdapter(List<MyAppointments> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_appoint, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        MyAppointments itemAdapter = list.get(position);

        ((ViewHolder) holder).nameDoc.setText(itemAdapter.getDrName());
        ((ViewHolder) holder).date.setText(itemAdapter.getDate());
        ((ViewHolder) holder).time.setText(itemAdapter.getTime());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView nameDoc, date, time;


        public ViewHolder(View itemView) {
            super(itemView);

            nameDoc = itemView.findViewById(R.id.nameDoc);
            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);
        }
    }
}
