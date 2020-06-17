package com.example.nirogo.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.nirogo.Profile.DoctorProfile;
import com.example.nirogo.HomeScreen.HomeActivity;
import com.example.nirogo.R;
import com.example.nirogo.Profile.UserProfile;
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
                Intent intent= new Intent(AmbulanceActivity.this, HomeActivity.class);
                intent.putExtra("type",getIntent().getStringExtra("type"));
                startActivity(intent);
                Animatoo.animateFade(AmbulanceActivity.this);
            }
        });

        final BubbleNavigationConstraintView bubblenavigation = findViewById(R.id.bottomNavambu);
        bubblenavigation.setNavigationChangeListener(new BubbleNavigationChangeListener() {
            @Override
            public void onNavigationChanged(View view, int position) {
                if (position == 0) {
                    Intent intent= new Intent(AmbulanceActivity.this, HomeActivity.class);
                    intent.putExtra("type",getIntent().getStringExtra("type"));
                    startActivity(intent);
                    Animatoo.animateFade(AmbulanceActivity.this);
                }
                else if (position == 1) {
                    Intent intent= new Intent(AmbulanceActivity.this, AppointmentsActivity.class);
                    intent.putExtra("type",getIntent().getStringExtra("type"));
                    startActivity(intent);
                    Animatoo.animateFade(AmbulanceActivity.this);
                }
                else if (position == 3) {
                    if(getIntent().getStringExtra("type").equals("Doctor")) {
                        startActivity(new Intent(getApplicationContext(), DoctorProfile.class));
                        Animatoo.animateFade(AmbulanceActivity.this);
                    }
                    else{
                        startActivity(new Intent(getApplicationContext(), UserProfile.class));
                        Animatoo.animateFade(AmbulanceActivity.this);
                    }
                }

            }});


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent= new Intent(AmbulanceActivity.this, HomeActivity.class);
        intent.putExtra("type",getIntent().getStringExtra("type"));
        startActivity(intent);
        Animatoo.animateFade(AmbulanceActivity.this);
    }
}
