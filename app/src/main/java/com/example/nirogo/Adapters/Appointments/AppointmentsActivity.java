package com.example.nirogo.Adapters.Appointments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nirogo.Adapters.Feed.FeedAdapter;
import com.example.nirogo.HomeActivity;
import com.example.nirogo.R;

import java.util.ArrayList;
import java.util.List;

public class AppointmentsActivity extends Activity {

    List<ItemAppointment> list = new ArrayList<>();
    AppointmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments);

        RecyclerView recyclerview = findViewById(R.id.recAppointment);
        adapter = new AppointmentAdapter(list, this);
        recyclerview.setAdapter(adapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        adapter.notifyDataSetChanged();

        ItemAppointment itemAppointment = new ItemAppointment();
        itemAppointment.setName("Dr. Garg");
        itemAppointment.setSpeciality("Neurology");
        itemAppointment.setDistance("1 KM");
        itemAppointment.setImage(R.drawable.user_1);
        list.add(itemAppointment);

        itemAppointment = new ItemAppointment();
        itemAppointment.setName("Dr. Bharadwaj");
        itemAppointment.setSpeciality("Surgery");
        itemAppointment.setDistance("2 KM");
        itemAppointment.setImage(R.drawable.user_2);
        list.add(itemAppointment);

        itemAppointment = new ItemAppointment();
        itemAppointment.setName("Dr. Gupta");
        itemAppointment.setSpeciality("Oncologist");
        itemAppointment.setDistance("2.5 KM");
        itemAppointment.setImage(R.drawable.user_3);
        list.add(itemAppointment);


        itemAppointment = new ItemAppointment();
        itemAppointment.setName("Dr. Mishra");
        itemAppointment.setSpeciality("Liver");
        itemAppointment.setDistance("4 KM");
        itemAppointment.setImage(R.drawable.user_4);
        list.add(itemAppointment);

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, HomeActivity.class));
    }
}
