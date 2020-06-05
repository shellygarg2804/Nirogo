package com.example.nirogo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.nirogo.Adapters.appointments.appointAdapter;
import com.example.nirogo.Adapters.appointments.appointmentItem;

import java.util.ArrayList;

public class appointmentsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments);

        ArrayList<appointmentItem> values = new ArrayList<>();
        values.add(new appointmentItem("safsxgas", "ENT", "7km away"));
        values.add(new appointmentItem("safsxgas", "ENT", "7km away"));
        values.add(new appointmentItem("safsxgas", "ENT", "7km away"));
        values.add(new appointmentItem("safsxgas", "ENT", "7km away"));
        values.add(new appointmentItem("safsxgas", "ENT", "7km away"));
        values.add(new appointmentItem("safsxgas", "ENT", "7km away"));
        values.add(new appointmentItem("safsxgas", "ENT", "7km away"));
        values.add(new appointmentItem("safsxgas", "ENT", "7km away"));
        values.add(new appointmentItem("safsxgas", "ENT", "7km away"));

        ListView listView = (ListView) findViewById(R.id.listViewAppointments);
        appointAdapter adapter = new appointAdapter(this, 0, values);
        listView.setAdapter(adapter);

    }
}
