package com.example.nirogo.NearbyDoctors;

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

import com.example.nirogo.Activities.AppointmentOption;
import com.example.nirogo.Profile.DoctorProfileViewOnly;
import com.example.nirogo.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AppointmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<UploadInfo> list;
    Context context;

    public AppointmentAdapter(List<UploadInfo> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.appointment_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {


        UploadInfo itemAdapter = list.get(position);

        ((ViewHolder) holder).docName.setText(itemAdapter.getName());
        ((ViewHolder) holder).docSpec.setText(itemAdapter.getSpeciality());
        ((ViewHolder) holder).docCity.setText(itemAdapter.getCity());
        Picasso.get().load(itemAdapter.getUrl()).into(((ViewHolder) holder).image);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView docName, docSpec, docCity;
        ImageView image, appointmentIcon;

        public ViewHolder(View itemView) {
            super(itemView);

            docCity = itemView.findViewById(R.id.cityText);
            docName = itemView.findViewById(R.id.nameDoc);
            docSpec = itemView.findViewById(R.id.specialityText);
            image = itemView.findViewById(R.id.imageText);
            appointmentIcon = itemView.findViewById(R.id.appointmentIcon);

            final Context context = itemView.getContext();

            docName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Opening Doc Profile", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(v.getContext(), DoctorProfileViewOnly.class);
                    intent.putExtra("docname", docName.getText());
                    v.getContext().startActivity(intent);
                }
            });

            appointmentIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), AppointmentOption.class);
                    intent.putExtra("docname", docName.getText());
                    v.getContext().startActivity(intent);

                }
            });
        }
    }
}
