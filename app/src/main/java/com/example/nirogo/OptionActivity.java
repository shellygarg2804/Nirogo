package com.example.nirogo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.nirogo.User.UserActivity;

import de.hdodenhof.circleimageview.CircleImageView;

public class OptionActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);

        LinearLayout doctor, user, supplier;

        doctor = findViewById(R.id.doctor);
        doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OptionActivity.this, LoginActivity.class);
                intent.putExtra("type","Doctor");
                startActivity(intent);
            }
        });

        user = findViewById(R.id.user);
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OptionActivity.this, UserActivity.class);
                intent.putExtra("type","User");
                startActivity(intent);
            }
        });

        supplier = findViewById(R.id.supplier);
        supplier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(OptionActivity.this, LoginActivity.class);
                String Type= "Supplier";
                intent.putExtra("type",Type);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(OptionActivity.this, MainActivity.class);
        startActivity(intent);

    }
}
