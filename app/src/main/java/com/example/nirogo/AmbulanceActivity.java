package com.example.nirogo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.nirogo.Adapters.Appointments.AppointmentsActivity;
import com.gauravk.bubblenavigation.BubbleNavigationConstraintView;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;

public class AmbulanceActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambulance);

        Button back = findViewById(R.id.backBtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            }
        });

        final BubbleNavigationConstraintView bubblenavigation = findViewById(R.id.bottomNavAmb);
        bubblenavigation.setNavigationChangeListener(new BubbleNavigationChangeListener() {
            @Override
            public void onNavigationChanged(View view, int position) {
                if (position == 0) {
                    startActivity(new Intent(AmbulanceActivity.this, HomeActivity.class));
                    Animatoo.animateSwipeLeft(AmbulanceActivity.this);
                } else if (position == 2) {
                    startActivity(new Intent(AmbulanceActivity.this, AppointmentsActivity.class));
                    Animatoo.animateSwipeLeft(AmbulanceActivity.this);
                } else if (position == 3) {
                    startActivity(new Intent(AmbulanceActivity.this, CartActivity.class));
                    Animatoo.animateSwipeLeft(AmbulanceActivity.this);
                 } else if (position == 4) {
                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                    Animatoo.animateSwipeLeft(AmbulanceActivity.this);
                }
            }});
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, HomeActivity.class));
        Animatoo.animateSlideLeft(AmbulanceActivity.this);
    }
}
