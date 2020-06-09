package com.example.nirogo.Adapters.Appointments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.nirogo.Adapters.Feed.FeedAdapter;
import com.example.nirogo.AmbulanceActivity;
import com.example.nirogo.CartActivity;
import com.example.nirogo.HomeActivity;
import com.example.nirogo.ProfileActivity;
import com.example.nirogo.R;
import com.example.nirogo.SearchActivity;
import com.gauravk.bubblenavigation.BubbleNavigationConstraintView;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;

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

        Button back = findViewById(R.id.backBtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            }
        });

        final BubbleNavigationConstraintView bubblenavigation = findViewById(R.id.bottomNavApp);
        bubblenavigation.setNavigationChangeListener(new BubbleNavigationChangeListener() {
            @Override
            public void onNavigationChanged(View view, int position) {
                if (position == 0) {
                    startActivity(new Intent(AppointmentsActivity.this, HomeActivity.class));
                    Animatoo.animateFade(AppointmentsActivity.this);
                   } else if (position == 1) {
                    startActivity(new Intent(AppointmentsActivity.this, AmbulanceActivity.class));
                    Animatoo.animateFade(AppointmentsActivity.this);
                    } else if (position == 2) {
                  } else if (position == 3) {
                    startActivity(new Intent(AppointmentsActivity.this, CartActivity.class));
                    Animatoo.animateFade(AppointmentsActivity.this);
                } else if (position == 4) {
                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                    Animatoo.animateFade(AppointmentsActivity.this);
                }
            }});
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, HomeActivity.class));
        Animatoo.animateSwipeLeft(AppointmentsActivity.this);
    }
}
