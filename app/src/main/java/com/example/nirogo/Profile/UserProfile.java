package com.example.nirogo.Profile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.INotificationSideChannel;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.nirogo.HomeScreen.HomeActivity;
import com.example.nirogo.R;

public class UserProfile extends Activity {

    LinearLayout pastAppointments;
    LinearLayout reports;
    LinearLayout Notifications;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);

        pastAppointments=(LinearLayout)findViewById(R.id.userprofileappointment);
        reports=(LinearLayout)findViewById(R.id.userprofilereports);
        Notifications=(LinearLayout)findViewById(R.id.userprofilenotification);

        pastAppointments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Past Appointments",Toast.LENGTH_SHORT).show();
            }
        });

        reports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Reports",Toast.LENGTH_SHORT).show();
            }
        });

        Notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Notifications",Toast.LENGTH_SHORT).show();
            }
        });




    }

    @Override
    public void onBackPressed () {
        Intent intent = new Intent(UserProfile.this, HomeActivity.class);
        intent.putExtra("type", "User");
        startActivity(intent);
        Animatoo.animateFade(UserProfile.this);
    }
}

