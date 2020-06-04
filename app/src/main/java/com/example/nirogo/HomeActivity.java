package com.example.nirogo;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.nirogo.Adapters.Feed.FeedAdapter;
import com.example.nirogo.Adapters.Feed.ItemAdapter;
import com.gauravk.bubblenavigation.BubbleNavigationConstraintView;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends Activity {

    List<ItemAdapter> list = new ArrayList<>();
    FeedAdapter postAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

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


        BubbleNavigationConstraintView bubblenavigation = findViewById(R.id.bottomNav);
        bubblenavigation.setNavigationChangeListener(new BubbleNavigationChangeListener() {
            @Override
            public void onNavigationChanged(View view, int position) {

                if(position == 1)
                {
                    Toast.makeText(HomeActivity.this, "Calling Ambulance", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(HomeActivity.this, AmbulanceActivity.class));

                }

                else if(position == 2){
                    startActivity(new Intent(HomeActivity.this,appointmentsActivity.class));
                }
                else if(position == 3)
                {
                    Toast.makeText(HomeActivity.this, "Opening Cart", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(HomeActivity.this, CartActivity.class));

                }

                else if(position == 4)
                {
                    Toast.makeText(HomeActivity.this, "Opening Search", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(HomeActivity.this, SearchActivity.class));

                }

                else if(position == 5)
                {
                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                }

            }
        });

    }


}
