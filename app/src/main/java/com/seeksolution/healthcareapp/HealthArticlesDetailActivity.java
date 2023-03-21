package com.seeksolution.healthcareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class HealthArticlesDetailActivity extends AppCompatActivity {

    private TextView healthdetail_tv2;
    private ImageView healthdetail_image;
    private Button healthdetail_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_articles_detail);

        healthdetail_btn = findViewById(R.id.healthdetail_back);
        healthdetail_image = findViewById(R.id.healthdetail_image);
        healthdetail_tv2 = findViewById(R.id.healthdetail_tv2);
        Intent  intent = getIntent();
        healthdetail_tv2.setText(intent.getStringExtra("text1"));
        healthdetail_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HealthArticlesDetailActivity.this,HealthArticlesActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            int resId = bundle.getInt("text2");
            healthdetail_image.setImageResource(resId);

        }
    }
}