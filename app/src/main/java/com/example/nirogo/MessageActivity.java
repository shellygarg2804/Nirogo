package com.example.nirogo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.nirogo.Adapters.Messages.ItemMessages;
import com.example.nirogo.Adapters.Messages.MessageAdapter;

import java.util.ArrayList;
import java.util.List;

public class MessageActivity extends Activity {

    List<ItemMessages> messagesList = new ArrayList<>();
    MessageAdapter messageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        Button back = findViewById(R.id.backBtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MessageActivity.this, HomeActivity.class);
                startActivity(intent);
                Animatoo.animateSwipeLeft(MessageActivity.this);
            }
        });


        RecyclerView recyclerview = findViewById(R.id.recyclerviewMessages);

        messageAdapter = new MessageAdapter(messagesList, this);
        recyclerview.setAdapter(messageAdapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        messageAdapter.notifyDataSetChanged();

        ItemMessages itemMessages = new ItemMessages();
        itemMessages.setImageUser(R.drawable.user_1);
        itemMessages.setNameUser("Dr. Sood");
        itemMessages.setTextUser("Text XYZ");
        messagesList.add(itemMessages);

        itemMessages = new ItemMessages();
        itemMessages.setImageUser(R.drawable.user_2);
        itemMessages.setNameUser("Dr. Sheetal");
        itemMessages.setTextUser("Text XYZ");
        messagesList.add(itemMessages);

        itemMessages = new ItemMessages();
        itemMessages.setImageUser(R.drawable.user_3);
        itemMessages.setNameUser("Dr. Garg");
        itemMessages.setTextUser("Text XYZ");
        messagesList.add(itemMessages);

        itemMessages = new ItemMessages();
        itemMessages.setImageUser(R.drawable.user_4);
        itemMessages.setNameUser("Dr. Aggarwal");
        itemMessages.setTextUser("Text XYZ");
        messagesList.add(itemMessages);

        itemMessages = new ItemMessages();
        itemMessages.setImageUser(R.drawable.user_5);
        itemMessages.setNameUser("Dr. Mishra");
        itemMessages.setTextUser("Text XYZ");
        messagesList.add(itemMessages);

        itemMessages = new ItemMessages();
        itemMessages.setImageUser(R.drawable.user_6);
        itemMessages.setNameUser("Dr. Sheetal");
        itemMessages.setTextUser("Text XYZ");
        messagesList.add(itemMessages);
    }
}
