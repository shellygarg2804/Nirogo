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
import com.gauravk.bubblenavigation.BubbleNavigationConstraintView;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    List<ItemAdapter> list = new ArrayList<>();
    FeedAdapter postAdapter;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

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

        //setting up navigation drawer
        drawerLayout= (DrawerLayout)findViewById(R.id.drawerLayout);
        navigationView= (NavigationView)findViewById(R.id.navigation);
        toolbar=(Toolbar)findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
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

        String type_user = getIntent().getStringExtra("type");
//        Toast.makeText(HomeActivity.this, type_user,Toast.LENGTH_LONG).show();

        ImageView menu = findViewById(R.id.menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START,true);

            }
        });

        RecyclerView recyclerview = findViewById(R.id.recyclerView);

        postAdapter = new FeedAdapter(list, this);
        recyclerview.setAdapter(postAdapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        postAdapter.notifyDataSetChanged();

        ItemAdapter itemAdapter = new ItemAdapter();
        itemAdapter.setImageUser(R.drawable.user_1);
        itemAdapter.setImagePost(R.drawable.rsz_post_image_1);
        itemAdapter.setPostDetails("I found about this medicine ");
        itemAdapter.setUserName("Dr. Abhishek Mishra");
        itemAdapter.setUserDetails("B.tech. 2nd Year");
        itemAdapter.setTimeAgo("1 hr");
        itemAdapter.setNoLikes("50");
        list.add(itemAdapter);

        itemAdapter = new ItemAdapter();
        itemAdapter.setImageUser(R.drawable.user_2);
        itemAdapter.setImagePost(R.drawable.rsz_post_image_2);
        itemAdapter.setPostDetails("Check This Out");
        itemAdapter.setUserName("Kautuk Dwivedi");
        itemAdapter.setUserDetails("B.tech. Graduate");
        itemAdapter.setTimeAgo("2 hr");
        itemAdapter.setNoLikes("100");
        list.add(itemAdapter);


        itemAdapter = new ItemAdapter();
        itemAdapter.setImageUser(R.drawable.user_3);
        itemAdapter.setImagePost(R.drawable.rsz_post_image_3);
        itemAdapter.setPostDetails("I completed this Course");
        itemAdapter.setUserName("Anmol Sharma");
        itemAdapter.setUserDetails("B.tech. Graduate");
        itemAdapter.setTimeAgo("4 hr");
        itemAdapter.setNoLikes("80");
        list.add(itemAdapter);


        itemAdapter = new ItemAdapter();
        itemAdapter.setImageUser(R.drawable.user_4);
        itemAdapter.setImagePost(R.drawable.rsz_post_image_4);
        itemAdapter.setPostDetails("I completed this Course which i never started");
        itemAdapter.setUserName("XYZ ABC");
        itemAdapter.setUserDetails("B.tech. Graduate");
        itemAdapter.setTimeAgo("5 hr");
        itemAdapter.setNoLikes("10");
        list.add(itemAdapter);


        final BubbleNavigationConstraintView bubblenavigation = findViewById(R.id.bottomNav);
        bubblenavigation.setCurrentActiveItem(0);
        bubblenavigation.setNavigationChangeListener(new BubbleNavigationChangeListener() {
            @Override
            public void onNavigationChanged(View view, int position) {

                 if (position == 1) {
                    startActivity(new Intent(HomeActivity.this, AppointmentsActivity.class));
                    Animatoo.animateFade(HomeActivity.this);
                }

                else if(position == 2)
                {
                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                    Animatoo.animateFade(HomeActivity.this);
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

