package com.example.nirogo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

    TextView txt;
    public static String size;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScreenSize size_check = new ScreenSize();
        size = size_check.screenCheck(MainActivity.this);

        if (size.equalsIgnoreCase("Small")) {
            setContentView(R.layout.activity_main_small);
            Log.i("Screen Return Value","Small");
        }
        else
            setContentView(R.layout.activity_main);

    txt = findViewById(R.id.txtStart);
    txt.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent =  new Intent(MainActivity.this, OptionActivity.class);
            startActivity(intent);
        }
    });

    }
}
