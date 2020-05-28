package com.example.nirogo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import de.hdodenhof.circleimageview.CircleImageView;

public class OptionActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);


        final CircleImageView doctor, patient, supplier;

        doctor = findViewById(R.id.doctor);
        doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (doctor.getBorderColor() == getResources().getColor(R.color.colorAccent)){
                    doctor.setBorderColor(getResources().getColor(R.color.White));
                }
                else
                    doctor.setBorderColor(getResources().getColor(R.color.colorAccent));
                Intent intent = new Intent(OptionActivity.this, DoctorActivity.class);
                startActivity(intent);
            }
        });

        patient = findViewById(R.id.patient);
        patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (patient.getBorderColor() == getResources().getColor(R.color.colorAccent)){
                    patient.setBorderColor(getResources().getColor(R.color.White));
                }
                else
                    patient.setBorderColor(getResources().getColor(R.color.colorAccent));
                Intent intent = new Intent(OptionActivity.this, PatientActivity.class);
                startActivity(intent);
            }
        });

        supplier = findViewById(R.id.supplier);
        supplier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (supplier.getBorderColor() == getResources().getColor(R.color.colorAccent)){
                    supplier.setBorderColor(getResources().getColor(R.color.White));
                }
                else
                    supplier.setBorderColor(getResources().getColor(R.color.colorAccent));

                Intent intent = new Intent(OptionActivity.this, SupplierActivity.class);
                startActivity(intent);
            }
        });
    }
}
