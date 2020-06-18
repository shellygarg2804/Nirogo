package com.example.nirogo.HomeScreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.nirogo.Activities.AmbulanceActivity;
import com.example.nirogo.Activities.AppointmentsActivity;
import com.example.nirogo.Activities.CartActivity;
import com.example.nirogo.Activities.OptionActivity;
import com.example.nirogo.Activities.ProfileActivity;
import com.example.nirogo.Adapters.Feed.FeedAdapter;
import com.example.nirogo.Post.PostUploadActivity;
import com.example.nirogo.Post.PostUploadInfo;
import com.example.nirogo.Profile.UserProfile;
import com.example.nirogo.R;
import com.example.nirogo.ScreenSize;
import com.gauravk.bubblenavigation.BubbleNavigationConstraintView;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    List<PostUploadInfo> list = new ArrayList<>();
    FeedAdapter postAdapter;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    View HeaderView;
    Toolbar toolbar;
    ProgressBar progressBar;
    RecyclerView recyclerview;
    SwipeRefreshLayout swipeRefreshLayout;
    //db
    DatabaseReference databaseReference;
    StorageReference storageReference;
    FirebaseAuth mAuth;
    String Database_Path = "Post/";
    String Useremail;


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(HomeActivity.this, OptionActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ScreenSize screenSize = new ScreenSize();
        String size = screenSize.screenCheck(HomeActivity.this);
        if (size.equalsIgnoreCase("Small")) {
            setContentView(R.layout.activity_home_small);
            Log.i("Screen Return Value", "Small");
        } else
            setContentView(R.layout.activity_home);
        mAuth= FirebaseAuth.getInstance();
        Useremail= mAuth.getCurrentUser().getEmail();

        progressBar = findViewById(R.id.progressBar);

        storageReference = FirebaseStorage.getInstance().getReference();



        //setting up navigation drawer
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        navigationView = (NavigationView) findViewById(R.id.navigation);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(false);
        toggle.syncState();

        //change emailtext in nav header
        HeaderView=navigationView.getHeaderView(0);
        TextView NavEmailText= (TextView)HeaderView.findViewById(R.id.nav_header_email);
        NavEmailText.setText(Useremail);



        navigationView.setNavigationItemSelectedListener(this);

        ImageView chatbtn = findViewById(R.id.chatBtn);
        chatbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, MessagePreview.class);
                startActivity(intent);
                Animatoo.animateSwipeRight(HomeActivity.this);
            }
        });

//        Toast.makeText(HomeActivity.this, type_user,Toast.LENGTH_LONG).show();

        ImageView menu = findViewById(R.id.menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START, true);
            }
        });


        //setting swipe to refresh
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeHome);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
            @Override
            public void onRefresh() {
                list.clear();
                settingfeeds();
            }
        });

        settingfeeds();


        final BubbleNavigationConstraintView bubblenavigation = findViewById(R.id.bottomNav);
        bubblenavigation.setCurrentActiveItem(0);
        bubblenavigation.setNavigationChangeListener(new BubbleNavigationChangeListener() {
            @Override
            public void onNavigationChanged(View view, int position) {
                if (position == 1) {
                    Intent intent= new Intent(HomeActivity.this, AppointmentsActivity.class);
                    intent.putExtra("type",getIntent().getStringExtra("type"));
                    startActivity(intent);
                    Animatoo.animateFade(HomeActivity.this);
                } else if (position == 2) {
                    if(getIntent().getStringExtra("type").equals("Doctor")) {
                        Intent intent = new Intent(HomeActivity.this, PostUploadActivity.class);
                        //       intent.putExtra("type", getIntent().getStringExtra("type"));
                        startActivity(intent);
                        Animatoo.animateFade(HomeActivity.this);
                    }
                    else {
                        Toast.makeText(HomeActivity.this,"User cannot upload post",Toast.LENGTH_LONG).show();
                    }
                } else if (position == 3) {
                    Intent intent= new Intent(HomeActivity.this, AmbulanceActivity.class);
                    intent.putExtra("type",getIntent().getStringExtra("type"));
                    startActivity(intent);
                    Animatoo.animateFade(HomeActivity.this);

                }

                else if (position == 4) {

                    Intent intent= new Intent(HomeActivity.this, CartActivity.class);
                    intent.putExtra("type",getIntent().getStringExtra("type"));
                    startActivity(intent);
                    Animatoo.animateFade(HomeActivity.this);

                }

            }
        });
    }

    public void settingfeeds(){

        swipeRefreshLayout.setRefreshing(true);
        recyclerview = findViewById(R.id.recyclerView);

        recyclerview.setLayoutManager(new LinearLayoutManager(this));

        databaseReference = FirebaseDatabase.getInstance().getReference(Database_Path);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    progressBar.setVisibility(View.INVISIBLE);
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

        swipeRefreshLayout.setRefreshing(false);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_item_one:  drawerLayout.closeDrawer(GravityCompat.START,true);
                return true;
            case R.id.nav_item_two :  Intent intent= new Intent(HomeActivity.this, CartActivity.class);
                intent.putExtra("type",getIntent().getStringExtra("type"));
                startActivity(intent);
                return true;
            case R.id.nav_item_three :  intent= new Intent(HomeActivity.this, AmbulanceActivity.class);
                intent.putExtra("type",getIntent().getStringExtra("type"));
                startActivity(intent);
                return true;
            case R.id.nav_item_four : if(getIntent().getStringExtra("type").equals("Doctor")) {
                intent = new Intent(HomeActivity.this, ProfileActivity.class);
                intent.putExtra("type", getIntent().getStringExtra("type"));
                startActivity(intent);
                return true;
            }
            else{
                intent = new Intent(HomeActivity.this, UserProfile.class);
                intent.putExtra("type", getIntent().getStringExtra("type"));
                startActivity(intent);
                return true; }
            case R.id.nav_item_five :   signOut(); return true;

        }
        return true;
    }


    private void signOut() {
        mAuth.signOut();
        finish();
        //Add Alert dialogue Box
        startActivity(new Intent(HomeActivity.this,OptionActivity.class));

    }


}