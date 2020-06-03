package com.example.nirogo.Adapters.appointments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.nirogo.R;

import java.util.ArrayList;

public class appointAdapter extends ArrayAdapter<appointmentItem> {
    public appointAdapter(@NonNull Context context, int resource, ArrayList<appointmentItem> neardoctors) {
        super(context, 0,neardoctors);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        appointmentItem neardoctor = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.appointment_item, parent, false);
        }
            TextView name= (TextView) convertView.findViewById(R.id.docname);
            TextView speciality= (TextView) convertView.findViewById(R.id.docspec);
            TextView distance= (TextView) convertView.findViewById(R.id.distancedoc);
            name.setText(neardoctor.getName());
            speciality.setText(neardoctor.getSpeciality());
            distance.setText(neardoctor.getDistance());

        return convertView;

    }


}
