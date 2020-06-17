package com.example.nirogo.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.nirogo.Adapters.Feed.FeedAdapter;
import com.example.nirogo.HomeScreen.HomeActivity;
import com.example.nirogo.NearbyDoctors.AppointmentAdapter;
import com.example.nirogo.NearbyDoctors.UploadInfo;
import com.example.nirogo.Post.PostUploadActivity;
import com.example.nirogo.R;
import com.gauravk.bubblenavigation.BubbleNavigationConstraintView;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class AppointmentsActivity extends Activity {

    List<UploadInfo> list = new ArrayList<>();

    AppointmentAdapter postAdapter;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    String Database_Path = "DocNearby/";

    EditText cityEnter;
    ImageView searchCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments);

        final RecyclerView recyclerview = findViewById(R.id.recAppointment);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));

        postAdapter = new AppointmentAdapter(list, this);
        postAdapter.notifyDataSetChanged();
        recyclerview.setAdapter(postAdapter);

        storageReference = FirebaseStorage.getInstance().getReference();

        databaseReference = FirebaseDatabase.getInstance().getReference(Database_Path);

        cityEnter = findViewById(R.id.cityEnter);

        searchCity = findViewById(R.id.searchIcon);
        searchCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                list.clear();

                final String city = cityEnter.getText().toString();

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            UploadInfo docNearby = postSnapshot.getValue(UploadInfo.class);

                            String userCity = docNearby.getCity();

                            if (userCity.equalsIgnoreCase(city)){
                                list.add(docNearby);
                            }

                        }
                        postAdapter = new AppointmentAdapter(list, getApplicationContext());
                        recyclerview.setAdapter(postAdapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });


        Button back = findViewById(R.id.backBtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(AppointmentsActivity.this, HomeActivity.class);
                intent.putExtra("type",getIntent().getStringExtra("type"));
                startActivity(intent);
                Animatoo.animateFade(AppointmentsActivity.this);
            }
        });

        final BubbleNavigationConstraintView bubblenavigation = findViewById(R.id.bottomNavApp);
        bubblenavigation.setNavigationChangeListener(new BubbleNavigationChangeListener() {
            @Override
            public void onNavigationChanged(View view, int position) {
                if (position == 0) {
                    Intent intent= new Intent(AppointmentsActivity.this, HomeActivity.class);
                    intent.putExtra("type",getIntent().getStringExtra("type"));
                    startActivity(intent);
                    Animatoo.animateFade(AppointmentsActivity.this);
                }
                else if (position == 2) {
                    if(getIntent().getStringExtra("type").equals("Doctor")) {
                        Intent intent = new Intent(AppointmentsActivity.this, PostUploadActivity.class);
                        intent.putExtra("type", getIntent().getStringExtra("type"));
                        startActivity(intent);
                        Animatoo.animateFade(AppointmentsActivity.this);
                    }
                    else {
                        Toast.makeText(AppointmentsActivity.this,"User cannot upload post",Toast.LENGTH_LONG).show();
                    }
                }
                else if (position == 3) {
                    Intent intent= new Intent(AppointmentsActivity.this, AmbulanceActivity.class);
                    intent.putExtra("type",getIntent().getStringExtra("type"));
                    startActivity(intent);
                    Animatoo.animateFade(AppointmentsActivity.this);

                }

                else if (position == 4) {
                    Intent intent= new Intent(AppointmentsActivity.this, CartActivity.class);
                    intent.putExtra("type",getIntent().getStringExtra("type"));
                    startActivity(intent);
                    Animatoo.animateFade(AppointmentsActivity.this);

                }

            }});
    }


    @Override
    public void onBackPressed() {
        Intent intent= new Intent(AppointmentsActivity.this, HomeActivity.class);

        intent.putExtra("type",getIntent().getStringExtra("type"));
        startActivity(intent);
        Animatoo.animateFade(AppointmentsActivity.this);
    }
}
