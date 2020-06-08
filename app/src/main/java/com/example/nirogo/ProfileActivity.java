package com.example.nirogo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.nirogo.Adapters.Appointments.AppointmentsActivity;
import com.gauravk.bubblenavigation.BubbleNavigationConstraintView;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;

public class ProfileActivity extends Activity {

    private ImageView update;
    private TextView name;
    private TextView age;
    private TextView speciality;
    private TextView about;
    private TextView experience;
    private TextView education;
    private TextView email;
    private TextView phoneno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        name=(TextView) findViewById(R.id.nameProfile);
        about=(TextView)findViewById(R.id.aboutprofile);
        experience=(TextView)findViewById(R.id.experienceprofile);
        education=(TextView)findViewById(R.id.educationprofile);

        if (getIntent().hasExtra("docname")){
            String nameUser = getIntent().getStringExtra("docname");
            name.setText(nameUser);
        }

        Intent intent= this.getIntent();
        if(intent!=null){
            if(intent.hasExtra("Activity")&&intent.getStringExtra("Activity").equals("UpdateProfileActivity")){
                Log.e("LOG_TAG","REACHED here");
                if(!(intent.getStringExtra("namestring").equals(""))){
                    name.setText(intent.getStringExtra("namestring"));
                }
                if(!(intent.getStringExtra("agestring").isEmpty())) {
                    age.setText(intent.getStringExtra("agestring"));
                }
                if(!(intent.getStringExtra("specialitystring").isEmpty())) {
                    speciality.setText(intent.getStringExtra("specialitystring"));
                }
                if(!(intent.getStringExtra("aboutstring").isEmpty())) {
                    about.setText(intent.getStringExtra("aboutstring"));
                }
                if(!(intent.getStringExtra("experiencestring").isEmpty())) {
                    experience.setText(intent.getStringExtra("experiencestring"));
                }
                if(!(intent.getStringExtra("educationstring").isEmpty())) {
                    education.setText(intent.getStringExtra("educationstring"));
                }
                if(!(intent.getStringExtra("emailstring").isEmpty())){
                    email.setText(intent.getStringExtra("emailstring"));
                }
                if(!(intent.getStringExtra("phonestring").isEmpty())){
                    phoneno.setText(intent.getStringExtra("phonestring"));
                }

            }
        }

        update= (ImageView) findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(ProfileActivity.this,updateprofile.class);
                i.putExtra("namep",name.getText());
                i.putExtra("aboutp",about.getText());
                i.putExtra("experiencep",experience.getText());
                i.putExtra("educationp",experience.getText());
                startActivity(i);


            }
        });

        final BubbleNavigationConstraintView bubblenavigation = findViewById(R.id.bottomNavProf);
        bubblenavigation.setNavigationChangeListener(new BubbleNavigationChangeListener() {
            @Override
            public void onNavigationChanged(View view, int position) {

                if (position == 0) {
                    bubblenavigation.setCurrentActiveItem(5);
                    startActivity(new Intent(ProfileActivity.this, HomeActivity.class));
                    Animatoo.animateFade(ProfileActivity.this);
                    }

                   else if (position == 1) {
                    startActivity(new Intent(ProfileActivity.this, AmbulanceActivity.class));
                    Animatoo.animateFade(ProfileActivity.this);
                   }
                   else if (position == 2) {
                    startActivity(new Intent(ProfileActivity.this, AppointmentsActivity.class));
                    Animatoo.animateFade(ProfileActivity.this);
                }
                   else if (position == 3) {
                    startActivity(new Intent(ProfileActivity.this, CartActivity.class));
                    Animatoo.animateFade(ProfileActivity.this);
                }
            }});

            }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, HomeActivity.class));
        Animatoo.animateFade(ProfileActivity.this);
    }
}

