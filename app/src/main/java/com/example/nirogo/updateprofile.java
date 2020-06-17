package com.example.nirogo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.nirogo.Doctor.DocUploadInfo;
import com.example.nirogo.Post.PostUploadInfo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class updateprofile extends Activity {
    private EditText name;
    private EditText age;
    private EditText speciality;
    private EditText about;
    private EditText experience;
    private EditText education;
    private EditText email;
    private EditText phone;
    private Button submitupdate;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updateprofile);

        Button back = findViewById(R.id.backBtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
            }
        });

        firebaseAuth = FirebaseAuth.getInstance();
        String id = firebaseAuth.getCurrentUser().getUid();

        databaseReference = FirebaseDatabase.getInstance().getReference("Doctor/").child(id);

        Intent intent = this.getIntent();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DocUploadInfo postUploadInfo = dataSnapshot.getValue(DocUploadInfo.class);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        name= (EditText) findViewById(R.id.nameupdate);
        age= (EditText) findViewById(R.id.ageupdate);
        speciality= (EditText) findViewById(R.id.specialityupdate);
        about= (EditText) findViewById(R.id.aboutupdate);
        experience= (EditText) findViewById(R.id.Experienceupdate);
        education= (EditText) findViewById(R.id.educationupdate);
        email= (EditText) findViewById(R.id.emailupdate);
        phone= (EditText) findViewById(R.id.phoneupdate);

        submitupdate=(Button)findViewById(R.id.submitupdate);

//
//        name.setHint(intent.getStringExtra("namep"));
//        age.setHint(intent.getStringExtra("agep"));
//        speciality.setHint(intent.getStringExtra("specialityp"));
//        about.setHint(intent.getStringExtra("aboutp"));
//        experience.setHint(intent.getStringExtra("experiencep"));
//        education.setHint(intent.getStringExtra("educationp"));
//        email.setHint(intent.getStringExtra("emailp"));
//        phone.setHint(intent.getStringExtra("phonep"));

        submitupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namestring = name.getText().toString();
                String agestring= age.getText().toString();
                String specialitystring= speciality.getText().toString();
                String aboutstring= about.getText().toString();
                String experiencestring= experience.getText().toString();
                String educationstring= education.getText().toString();
                String emailstring= email.getText().toString();
                String phonestring= phone.getText().toString();
                Log.i("Phone no.",phonestring);

                Intent i= new Intent(updateprofile.this, ProfileActivity.class);
//
//                i.putExtra("Activity","UpdateProfileActivity");
//                i.putExtra("namestring",namestring);
//                i.putExtra("agestring",agestring);
//                i.putExtra("specialitystring",specialitystring);
//                i.putExtra("aboutstring",aboutstring);
//                i.putExtra("experiencestring",experiencestring);
//                i.putExtra("educationstring",educationstring);
//                i.putExtra("emailstring",emailstring);
//                i.putExtra("phonestring",phonestring);
//                startActivity(i);

            }
        });

    }




}
