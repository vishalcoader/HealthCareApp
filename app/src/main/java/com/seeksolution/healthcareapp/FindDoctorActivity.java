package com.seeksolution.healthcareapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FindDoctorActivity extends AppCompatActivity implements View.OnClickListener {
    private CardView finddoctor_exit,physician,dietician,dientist,surgun,cardiologist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_doctor);
        getSupportActionBar().hide();

        finddoctor_exit = findViewById(R.id.finddoctor_exit);
        physician = findViewById(R.id.physician);
        dietician = findViewById(R.id.deintician);
        dientist = findViewById(R.id.dentist);
        surgun = findViewById(R.id.surgen);
        cardiologist = findViewById(R.id.cardiologist);


        finddoctor_exit.setOnClickListener(this);
        physician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FindDoctorActivity.this,DoctorDetailActivity.class);
                intent.putExtra("title","Physicians");
                startActivity(intent);
                finish();
            }
        });

        dietician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FindDoctorActivity.this,DoctorDetailActivity.class);
                intent.putExtra("title","Dietician");
                startActivity(intent);
                finish();
            }
        });

        dientist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FindDoctorActivity.this,DoctorDetailActivity.class);
                intent.putExtra("title","Dentist");
                startActivity(intent);
                finish();

            }
        });

        surgun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FindDoctorActivity.this,DoctorDetailActivity.class);
                intent.putExtra("title","Surgen");
                startActivity(intent);
                finish();
            }
        });

        cardiologist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FindDoctorActivity.this,DoctorDetailActivity.class);
                intent.putExtra("title","Cardiologist");
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(FindDoctorActivity.this,HomeActivity.class);
        startActivity(intent);
        finish();
    }
}