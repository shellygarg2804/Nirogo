package com.example.nirogo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ProfileActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        if (getIntent().hasExtra("docname")){
            String name = getIntent().getStringExtra("docname");
            TextView nameTxt = findViewById(R.id.textView);
            nameTxt.setText(name);
        }

    }
}
