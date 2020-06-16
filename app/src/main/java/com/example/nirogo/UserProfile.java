package com.example.nirogo;

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
import com.example.nirogo.Adapters.Appointments.AppointmentsActivity;
import com.example.nirogo.Post.PostUploadActivity;
import com.gauravk.bubblenavigation.BubbleNavigationConstraintView;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;

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



        final BubbleNavigationConstraintView bubblenavigation = findViewById(R.id.bottomNavUserProf);
        bubblenavigation.setNavigationChangeListener(new BubbleNavigationChangeListener() {
            @Override
            public void onNavigationChanged(View view, int position) {

                if (position == 0) {
                    Intent intent = new Intent(UserProfile.this, HomeActivity.class);
                    intent.putExtra("type", "User");
                    startActivity(intent);
                    Animatoo.animateFade(UserProfile.this);
                } else if (position == 1) {
                    Intent intent = new Intent(UserProfile.this, AppointmentsActivity.class);
                    intent.putExtra("type", "User");
                    startActivity(intent);
                    Animatoo.animateFade(UserProfile.this);
                } else if (position == 2) {
                    Intent intent = new Intent(UserProfile.this, AmbulanceActivity.class);
                    intent.putExtra("type", "User");
                    startActivity(intent);
                    Animatoo.animateFade(UserProfile.this);
                }

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

