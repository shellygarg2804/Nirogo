package com.example.nirogo;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
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

public class HomeActivity extends Activity {

    List<ItemAdapter> list = new ArrayList<>();
    FeedAdapter postAdapter;

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

        CircleImageView chatbtn = findViewById(R.id.chatBtn);
        chatbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, MessageActivity.class);
                startActivity(intent);
                Animatoo.animateFade(HomeActivity.this);
            }
        });

        String type_user = getIntent().getStringExtra("type");
//        Toast.makeText(HomeActivity.this, type_user,Toast.LENGTH_LONG).show();

        CircleImageView user = findViewById(R.id.userIcon);
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));

            }
        });

        RecyclerView recyclerview = findViewById(R.id.recyclerView);

        postAdapter = new FeedAdapter(list, this);
        recyclerview.setAdapter(postAdapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        postAdapter.notifyDataSetChanged();

        ItemAdapter itemAdapter = new ItemAdapter();
        itemAdapter.setImageUser(R.drawable.user_1);
        itemAdapter.setImagePost(R.drawable.post_image_1);
        itemAdapter.setPostDetails("I found about this medicine ");
        itemAdapter.setUserName("Dr. Abhishek Mishra");
        itemAdapter.setUserDetails("B.tech. 2nd Year");
        itemAdapter.setTimeAgo("1 hr");
        itemAdapter.setNoLikes("50");
        list.add(itemAdapter);

        itemAdapter = new ItemAdapter();
        itemAdapter.setImageUser(R.drawable.user_2);
        itemAdapter.setImagePost(R.drawable.post_image_2);
        itemAdapter.setPostDetails("Check This Out");
        itemAdapter.setUserName("Kautuk Dwivedi");
        itemAdapter.setUserDetails("B.tech. Graduate");
        itemAdapter.setTimeAgo("2 hr");
        itemAdapter.setNoLikes("100");
        list.add(itemAdapter);


        itemAdapter = new ItemAdapter();
        itemAdapter.setImageUser(R.drawable.user_3);
        itemAdapter.setImagePost(R.drawable.post_image_3);
        itemAdapter.setPostDetails("I completed this Course");
        itemAdapter.setUserName("Anmol Sharma");
        itemAdapter.setUserDetails("B.tech. Graduate");
        itemAdapter.setTimeAgo("4 hr");
        itemAdapter.setNoLikes("80");
        list.add(itemAdapter);


        itemAdapter = new ItemAdapter();
        itemAdapter.setImageUser(R.drawable.user_4);
        itemAdapter.setImagePost(R.drawable.post_image_4);
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
                    startActivity(new Intent(HomeActivity.this, AmbulanceActivity.class));
                    Animatoo.animateFade(HomeActivity.this);
                }

                else if (position == 2) {
                    startActivity(new Intent(HomeActivity.this, AppointmentsActivity.class));
                    Animatoo.animateFade(HomeActivity.this);
                }

             else if (position == 3) {
                    startActivity(new Intent(HomeActivity.this, CartActivity.class));
                    Animatoo.animateFade(HomeActivity.this);
             }

                else if(position == 4)
                {
                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                    Animatoo.animateFade(HomeActivity.this);
                }

            }
        });

    }}

