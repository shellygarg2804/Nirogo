package com.example.nirogo.Activities;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.nirogo.HomeScreen.HomeActivity;
import com.example.nirogo.Post.PostUploadActivity;
import com.example.nirogo.R;
import com.gauravk.bubblenavigation.BubbleNavigationConstraintView;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;

public class CartActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        Button back = findViewById(R.id.backBtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(CartActivity.this, HomeActivity.class);
                intent.putExtra("type",getIntent().getStringExtra("type"));
                startActivity(intent);
                Animatoo.animateSlideLeft(CartActivity.this);
            }
        });

        final BubbleNavigationConstraintView bubblenavigation = findViewById(R.id.bottomNavCart);
        bubblenavigation.setNavigationChangeListener(new BubbleNavigationChangeListener() {
            @Override
            public void onNavigationChanged(View view, int position) {
                if (position == 0) {
                    Intent intent= new Intent(CartActivity.this, HomeActivity.class);
                    intent.putExtra("type",getIntent().getStringExtra("type"));
                    startActivity(intent);
                    Animatoo.animateFade(CartActivity.this);
                }
                else if (position == 1) {
                    Intent intent= new Intent(CartActivity.this, AppointmentsActivity.class);
                    intent.putExtra("type",getIntent().getStringExtra("type"));
                    startActivity(intent);
                    Animatoo.animateFade(CartActivity.this);
                }
                else if (position == 2) {
                    if(getIntent().getStringExtra("type").equals("Doctor")) {
                        Intent intent = new Intent(CartActivity.this, PostUploadActivity.class);
                        intent.putExtra("type", getIntent().getStringExtra("type"));
                        startActivity(intent);
                        Animatoo.animateFade(CartActivity.this);
                    }
                    else {
                        Toast.makeText(CartActivity.this,"User cannot upload post",Toast.LENGTH_LONG).show();
                    }
                }
                else if (position == 3) {
                    Intent intent= new Intent(CartActivity.this, AmbulanceActivity.class);
                    intent.putExtra("type",getIntent().getStringExtra("type"));
                    startActivity(intent);
                    Animatoo.animateFade(CartActivity.this);
                }

            }});

    }

    @Override
    public void onBackPressed() {
        Intent intent= new Intent(CartActivity.this, HomeActivity.class);
        intent.putExtra("type",getIntent().getStringExtra("type"));
        startActivity(intent);
        Animatoo.animateSlideLeft(CartActivity.this);
    }
}
