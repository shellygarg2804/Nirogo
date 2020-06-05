package com.example.nirogo.Adapters.Appointments;

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

import com.example.nirogo.ProfileActivity;
import com.example.nirogo.R;

import java.util.List;

public class AppointmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ItemAppointment> list;
    Context context;

    public AppointmentAdapter(List<ItemAppointment> list, Context context) {
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

        ItemAppointment itemAdapter = list.get(position);

        ((ViewHolder) holder).docName.setText(itemAdapter.getName());
        ((ViewHolder) holder).docSpec.setText(itemAdapter.getSpeciality());
        ((ViewHolder) holder).docDist.setText(itemAdapter.getDistance());
        ((ViewHolder) holder).image.setImageResource(itemAdapter.getImage());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView docName, docSpec, docDist;
        ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);

            docName = itemView.findViewById(R.id.nameDoc);
            docSpec = itemView.findViewById(R.id.specialityText);
            docDist = itemView.findViewById(R.id.distanceText);
            image = itemView.findViewById(R.id.imageText);
            final Context context = itemView.getContext();

            docName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Opening Doc Profile", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(v.getContext(), ProfileActivity.class);
                    intent.putExtra("docname", docName.getText());
                    v.getContext().startActivity(intent);
                }
            });


        }
    }
}
