package com.seeksolution.healthcareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class HealthArticlesActivity extends AppCompatActivity {

    private  String[][] health_detail = {
            {"Walking Daily","","","","Click more detail"},
            {"Home care up Covid-19","","","","Click more detail"},
            {"Stop Smoking","","","","Click more detail"},
            {"Menstrual camp","","","","Click more detail"},
            {"Healthy gut","","","","Click more detail"}
    };

    private int[] images = {
            R.drawable.health1,
            R.drawable.health2,
            R.drawable.smoke1,
            R.drawable.menstrual,
            R.drawable.goodhealth
    };

    HashMap<String ,String> item;
    ArrayList arrayList;
    SimpleAdapter sa;
    ListView health_listview;
    Button health_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_articles);

        health_back  = findViewById(R.id.health_back);
        health_listview  = findViewById(R.id.listviewhealth);

        health_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HealthArticlesActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        arrayList = new ArrayList<>();
        for (int i=0; i<health_detail.length;i++){
            item = new HashMap<String,String>();
            item.put("line1",health_detail[i][0]);
            item.put("line2",health_detail[i][1]);
            item.put("line3",health_detail[i][2]);
            item.put("line4",health_detail[i][3]);
            item.put("line5",health_detail[i][4]);
            arrayList.add(item);
        }

        sa = new SimpleAdapter(this,arrayList,
                R.layout.multi_line,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});

        health_listview.setAdapter(sa);

        health_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int i = position;

                Toast.makeText(HealthArticlesActivity.this, "You are select the item"+i, Toast.LENGTH_SHORT).show();
                Intent it = new Intent(getApplicationContext(),HealthArticlesDetailActivity.class);
                it.putExtra("text1",health_detail[i][0]);
                it.putExtra("text2",images[i]);
                startActivity(it);
            }
        });

    }
}