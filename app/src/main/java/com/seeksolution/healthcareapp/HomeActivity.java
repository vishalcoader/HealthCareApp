package com.seeksolution.healthcareapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {
    private CardView labtest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        labtest = findViewById(R.id.labtest);

        labtest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,LabTestActivity.class);
                startActivity(intent);
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("username","").toString();
        Toast.makeText(this, "Welcome ! "+name, Toast.LENGTH_SHORT).show();

//        there are we are create a button of logout for the event
        CardView logout = findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                Toast.makeText(HomeActivity.this, "Logout Succesfully "+name, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(HomeActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

//        Here we are create a find doctor page so we are find the id

        CardView finddoctor = findViewById(R.id.finddoctor);

        finddoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, FindDoctorActivity.class);
                startActivity(intent);
            }
        });

        CardView orderdetail = findViewById(R.id.orderdetail);
        orderdetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,OrderDetailActivity.class);
                startActivity(intent);
            }
        });

        CardView buymedicine = findViewById(R.id.buymedicine);

        buymedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,BuyMedicineActivity.class);
                startActivity(intent);
            }
        });

        CardView healtharticles = findViewById(R.id.healtharticles);
        healtharticles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,HealthArticlesActivity.class);
                startActivity(intent);
            }
        });
    }
}