package com.example.nirogo.Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nirogo.R;

import java.util.List;

public class FeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ItemAdapter> list;
    Context context;

    public FeedAdapter(List<ItemAdapter> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_feed, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ItemAdapter itemAdapter = list.get(position);

        ((ViewHolder) holder).nameUser.setText(itemAdapter.getUserName());
        ((ViewHolder) holder).descUser.setText(itemAdapter.getUserDetails());
        ((ViewHolder) holder).descPost.setText(itemAdapter.getPostDetails());
        ((ViewHolder) holder).timePost.setText(itemAdapter.getTimeAgo());
        ((ViewHolder) holder).imgUser.setImageResource(itemAdapter.getImageUser());
        ((ViewHolder) holder).imgPost.setImageResource(itemAdapter.getImagePost());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameUser, descUser, descPost, timePost;
        public ImageView imgUser, imgPost;
        LinearLayout likelay, share, comment;
        ImageView btnLike;
        TextView txtLike;


        public ViewHolder(View itemView) {
            super(itemView);

            nameUser = itemView.findViewById(R.id.nameUser);
            descUser = itemView.findViewById(R.id.positionUser);
            descPost = itemView.findViewById(R.id.descPost);
            timePost = itemView.findViewById(R.id.timePost);
            imgUser = itemView.findViewById(R.id.imageUser);
            imgPost = itemView.findViewById(R.id.imagePost);

            likelay = itemView.findViewById(R.id.likeLayout);
            btnLike = itemView.findViewById(R.id.btnLike);
            txtLike = itemView.findViewById(R.id.likeTxt);

            final Context context = itemView.getContext();
            likelay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (txtLike.getCurrentTextColor() == context.getResources().getColor(R.color.Black))
                    {
                        txtLike.setTextColor(context.getResources().getColor(R.color.blue_like));
                        btnLike.setImageResource(R.drawable.like_blue);
                    }

                    else{
                        txtLike.setTextColor(context.getResources().getColor(R.color.Black));
                        btnLike.setImageResource(R.drawable.like_thumb);
                    }

                }
            });


            nameUser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Opening User Profile", Toast.LENGTH_LONG).show();
                }
            });

            imgPost.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Opening Image", Toast.LENGTH_LONG).show();
                }
            });



        }
    }
}
