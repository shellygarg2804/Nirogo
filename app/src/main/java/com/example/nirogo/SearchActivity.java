package com.example.nirogo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.nirogo.Activities.AmbulanceActivity;
import com.example.nirogo.Activities.CartActivity;
import com.example.nirogo.Adapters.Appointments.AppointmentsActivity;
import com.example.nirogo.HomeScreen.HomeActivity;
import com.gauravk.bubblenavigation.BubbleNavigationConstraintView;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;

public class SearchActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Button back = findViewById(R.id.backBtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            }
        });

        final BubbleNavigationConstraintView bubblenavigation = findViewById(R.id.bottomNavSearch);
        bubblenavigation.setNavigationChangeListener(new BubbleNavigationChangeListener() {
            @Override
            public void onNavigationChanged(View view, int position) {
                if (position == 0) {
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));

                } else if (position == 1) {
                    startActivity(new Intent(getApplicationContext(), AmbulanceActivity.class));

                } else if (position == 2) {
                    startActivity(new Intent(getApplicationContext(), AppointmentsActivity.class));
                   } else if (position == 3) {
                    startActivity(new Intent(getApplicationContext(), CartActivity.class));
                   } else if (position == 4) {
                    } else if (position == 5) {
                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                }
            }});
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, HomeActivity.class));
    }
}
