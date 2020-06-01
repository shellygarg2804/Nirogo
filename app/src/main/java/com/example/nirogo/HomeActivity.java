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
        Toast.makeText(HomeActivity.this, type_user,Toast.LENGTH_LONG).show();

        RecyclerView recyclerview = findViewById(R.id.recyclerView);

        postAdapter = new FeedAdapter(list, this);
        recyclerview.setAdapter(postAdapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        postAdapter.notifyDataSetChanged();

        ItemAdapter itemAdapter = new ItemAdapter();
        itemAdapter.setImageUser(R.drawable.ic_user);
        itemAdapter.setImagePost(R.drawable.img_sample_1);
        itemAdapter.setPostDetails("I completed this Course that I started a week ago");
        itemAdapter.setUserName("Abhishek Mishra");
        itemAdapter.setUserDetails("B.tech. 2nd Year");
        itemAdapter.setTimeAgo("1 hr");
        list.add(itemAdapter);

        itemAdapter = new ItemAdapter();
        itemAdapter.setImageUser(R.drawable.ic_user);
        itemAdapter.setImagePost(R.drawable.img_sample_2);
        itemAdapter.setPostDetails("I completed this Course which I started 2 months ago");
        itemAdapter.setUserName("Kautuk Dwivedi");
        itemAdapter.setUserDetails("B.tech. Graduate");
        itemAdapter.setTimeAgo("2 hr");
        list.add(itemAdapter);


        itemAdapter = new ItemAdapter();
        itemAdapter.setImageUser(R.drawable.ic_user);
        itemAdapter.setImagePost(R.drawable.sample_image_3);
        itemAdapter.setPostDetails("I completed this Course");
        itemAdapter.setUserName("Anmol Sharma");
        itemAdapter.setUserDetails("B.tech. Graduate");
        itemAdapter.setTimeAgo("4 hr");
        list.add(itemAdapter);


        itemAdapter = new ItemAdapter();
        itemAdapter.setImageUser(R.drawable.ic_user);
        itemAdapter.setImagePost(R.drawable.sample_image_4);
        itemAdapter.setPostDetails("I completed this Course which i never started");
        itemAdapter.setUserName("XYZ ABC");
        itemAdapter.setUserDetails("B.tech. Graduate");
        itemAdapter.setTimeAgo("5 hr");
        list.add(itemAdapter);


        BubbleNavigationConstraintView bubblenavigation = findViewById(R.id.bottomNav);
        bubblenavigation.setNavigationChangeListener(new BubbleNavigationChangeListener() {
            @Override
            public void onNavigationChanged(View view, int position) {
                if (position == 0)
                    Toast.makeText(HomeActivity.this, "Viewing Home", Toast.LENGTH_LONG).show();

                else if(position == 1)
                    Toast.makeText(HomeActivity.this, "Calling Ambulance", Toast.LENGTH_LONG).show();

                else if(position == 2){
                    Toast.makeText(HomeActivity.this, "Opening Checkist", Toast.LENGTH_LONG).show();
                }
                else if(position == 3)
                    Toast.makeText(HomeActivity.this, "Opening Cart", Toast.LENGTH_LONG).show();

                else if(position == 4)
                    Toast.makeText(HomeActivity.this, "Opening Search", Toast.LENGTH_LONG).show();

                else if(position == 5)
                    Toast.makeText(HomeActivity.this, "User Profile ", Toast.LENGTH_LONG).show();

            }
        });

    }


}
