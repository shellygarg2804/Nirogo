package com.example.nirogo.Adapters.Messages;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nirogo.DoctorProfile;
import com.example.nirogo.R;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ItemMessages> list;
    Context context;

    public MessageAdapter(List<ItemMessages> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_messages, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ItemMessages itemAdapter = list.get(position);

        ((ViewHolder) holder).nameUser.setText(itemAdapter.getNameUser());
        ((ViewHolder) holder).messageText.setText(itemAdapter.getTextUser());
        ((ViewHolder) holder).imgUser.setImageResource(itemAdapter.getImageUser());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameUser, messageText;
        public ImageView imgUser, imgMessage, imgCall;

        public ViewHolder(View itemView) {
            super(itemView);

            nameUser = itemView.findViewById(R.id.nameUser);
            messageText = itemView.findViewById(R.id.messageText);
            imgUser = itemView.findViewById(R.id.imageText);
            imgMessage = itemView.findViewById(R.id.messageIcon);
            imgCall = itemView.findViewById(R.id.callIcon);

            nameUser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), DoctorProfile.class);
                    intent.putExtra("docname", nameUser.getText());
                    v.getContext().startActivity(intent);
                }
            });
            imgUser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Opening User Profile", Toast.LENGTH_LONG).show();

                }
            });

            imgMessage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Opening Chat", Toast.LENGTH_LONG).show();
                }
            });

            imgCall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Opening Call", Toast.LENGTH_LONG).show();
                }
            });
        }
}
}

