package com.example.nirogo.Adapters.Feed;


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

import com.example.nirogo.Profile.DoctorProfileViewOnly;
import com.example.nirogo.Activities.AppointmentOption;
import com.example.nirogo.Post.PostUploadInfo;
import com.example.nirogo.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<PostUploadInfo> list;
    Context context;

    public FeedAdapter(List<PostUploadInfo> list, Context context) {
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

        PostUploadInfo itemAdapter = list.get(position);

        Picasso.get().load(itemAdapter.getDocImage()).into(((ViewHolder) holder).docImage);
        ((ViewHolder) holder).nameUser.setText(itemAdapter.getDocName());
        ((ViewHolder) holder).descUser.setText(itemAdapter.getDocSpec());
        ((ViewHolder) holder).descPost.setText(itemAdapter.getDesc());
        ((ViewHolder) holder).timePost.setText(itemAdapter.getTime());
        ((ViewHolder) holder).doc_phone.setText(itemAdapter.getDocPhone());

        Picasso.get().load(itemAdapter.getUrl()).into(((ViewHolder) holder).imgPost);

        // ((ViewHolder) holder).txtLike.setText(itemAdapter.getLikes());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameUser, descUser, descPost, timePost,doc_phone;
        ImageView docImage, imgPost;

        LinearLayout likelay, appoint;
        ImageView btnLike;
        TextView txtLike, numLikes;



        public ViewHolder(View itemView) {
            super(itemView);

            nameUser = itemView.findViewById(R.id.nameUser);
            descUser = itemView.findViewById(R.id.positionUser);
            descPost = itemView.findViewById(R.id.descPost);
            timePost = itemView.findViewById(R.id.timePost);
            docImage = itemView.findViewById(R.id.imageUser);
            imgPost = itemView.findViewById(R.id.imagePost);
            doc_phone= itemView.findViewById(R.id.Docphone);


            likelay = itemView.findViewById(R.id.likeLayout);
            btnLike = itemView.findViewById(R.id.btnLike);
            txtLike = itemView.findViewById(R.id.likeTxt);
            numLikes = itemView.findViewById(R.id.noLikes);
            appoint = itemView.findViewById(R.id.shareAppointment);

            appoint.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), AppointmentOption.class);
                    intent.putExtra("docname", nameUser.getText());
                    intent.putExtra("Doc_Phone",doc_phone.getText());
                    v.getContext().startActivity(intent);

                }
            });

            final Context context = itemView.getContext();
            likelay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    int like = Integer.parseInt((String) numLikes.getText());

                    if (txtLike.getCurrentTextColor() == context.getResources().getColor(R.color.Black))
                    {
                        txtLike.setTextColor(context.getResources().getColor(R.color.blue_like));
                        btnLike.setImageResource(R.drawable.like_blue);
//                        like++;
                    }

                    else{
                        txtLike.setTextColor(context.getResources().getColor(R.color.Black));
                        btnLike.setImageResource(R.drawable.like_thumb);
  //                      like--;
                    }
    //                numLikes.setText(Integer.toString(like));
                }
            });

            nameUser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Opening User Profile", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(v.getContext(), DoctorProfileViewOnly.class);
                    intent.putExtra("docname", nameUser.getText());
                    v.getContext().startActivity(intent);
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
