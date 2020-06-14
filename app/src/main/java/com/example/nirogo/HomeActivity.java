package com.example.nirogo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.nirogo.Adapters.Feed.FeedAdapter;
import com.example.nirogo.Adapters.Feed.ItemAdapter;
import com.example.nirogo.Adapters.Appointments.AppointmentsActivity;
import com.example.nirogo.Post.PostUploadInfo;
import com.gauravk.bubblenavigation.BubbleNavigationConstraintView;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    List<PostUploadInfo> list = new ArrayList<>();
    FeedAdapter postAdapter;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    RecyclerView recyclerview;
    //db
    DatabaseReference databaseReference;
    StorageReference storageReference;
    String Database_Path = "Post/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        ScreenSize screenSize = new ScreenSize();
        String size = screenSize.screenCheck(HomeActivity.this);
        if (size.equalsIgnoreCase("Small")) {
            setContentView(R.layout.activity_home_small);
            Log.i("Screen Return Value","Small");
        }
        else
        setContentView(R.layout.activity_home);

        storageReference = FirebaseStorage.getInstance().getReference();

        //setting up navigation drawer
        drawerLayout= (DrawerLayout)findViewById(R.id.drawerLayout);
        navigationView= (NavigationView)findViewById(R.id.navigation);
        toolbar=(Toolbar)findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        CircleImageView chatbtn = findViewById(R.id.chatBtn);
        chatbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, MessageActivity.class);
                startActivity(intent);
                Animatoo.animateSwipeRight(HomeActivity.this);
            }
        });

//        Toast.makeText(HomeActivity.this, type_user,Toast.LENGTH_LONG).show();

        ImageView menu = findViewById(R.id.menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START,true);

            }
        });

        recyclerview = findViewById(R.id.recyclerView);

        recyclerview.setLayoutManager(new LinearLayoutManager(this));

        databaseReference = FirebaseDatabase.getInstance().getReference(Database_Path);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){

                    PostUploadInfo postUploadInfo = postSnapshot.getValue(PostUploadInfo.class);
                    list.add(postUploadInfo);
                }
                postAdapter = new FeedAdapter(list, getApplicationContext());
                recyclerview.setAdapter(postAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
            }
        });

        final BubbleNavigationConstraintView bubblenavigation = findViewById(R.id.bottomNav);
        bubblenavigation.setCurrentActiveItem(0);
        final String type_user = getIntent().getStringExtra("type");
        bubblenavigation.setNavigationChangeListener(new BubbleNavigationChangeListener() {
            @Override
            public void onNavigationChanged(View view, int position) {

                 if (position == 1) {
                    startActivity(new Intent(HomeActivity.this, AppointmentsActivity.class));
                    Animatoo.animateFade(HomeActivity.this);
                }

                 else if(position == 2)
                 {
                     startActivity(new Intent(getApplicationContext(), AmbulanceActivity.class));
                     Animatoo.animateFade(HomeActivity.this);
                 }

                 else if(position == 3)
                 {
                     if(type_user.equalsIgnoreCase("doctor")){
                         startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                         Animatoo.animateFade(HomeActivity.this);
                     }
                     else if (type_user.equalsIgnoreCase("user")){
                         startActivity(new Intent(getApplicationContext(), UserProfile.class));
                         Animatoo.animateFade(HomeActivity.this);
                     }
                      }

            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_item_one:  drawerLayout.closeDrawer(GravityCompat.START,true);
                                        return true;
            case R.id.nav_item_two : startActivity(new Intent(HomeActivity.this,CartActivity.class));
                                        return true;
            case R.id.nav_item_three : startActivity(new Intent(HomeActivity.this,AmbulanceActivity.class));
                                        return true;
            case R.id.nav_item_four : Toast.makeText(this, "SIGN OUT", Toast.LENGTH_SHORT).show();
                                        return true;
        }
        return true;
    }
}

