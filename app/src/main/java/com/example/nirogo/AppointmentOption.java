package com.example.nirogo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AppointmentOption extends AppCompatActivity {

    LinearLayout offline, online;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_option);

        if (getIntent().hasExtra("docname"));
            String doc = getIntent().getStringExtra("docname");

        TextView doctxt = findViewById(R.id.docName);
        doctxt.setText(doc);

        offline = findViewById(R.id.offline);
        online = findViewById(R.id.online);

        offline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), MessageActivity.class);
            intent.putExtra( "type", "Offline");
            startActivity(intent);
            }
        });

        online.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MessageActivity.class);
                intent.putExtra( "type", "Online");
                startActivity(intent);
            }
        });

    }
}
