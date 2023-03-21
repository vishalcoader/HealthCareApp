package com.seeksolution.healthcareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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

public class LabTestActivity extends AppCompatActivity {

    private String[][] packages =
            {
                    {"Package 1 : Full Body Checkup","","","","999"},
                    {"Package 2 : Blood Glucose Fasting","","","","299"},
                    {"Package 3 : Covid-19 Antibody - IgG","","","","899"},
                    {"Package 4 : Throid Check","","","","499"},
                    {"Package 5 : Immunity Checkup","","","","699"},
            };

    private String[] package_details = {
            "Blood Glucose fasting\n"+
            "HbA1c\n"+
            "Iron Studies\n"+
            "Kidney Function Test\n"+
            "LDH Lacate Dehydrate ,Serum\n"+
            "Lipid Profile\n"+
            "Liver Function Test",
                     "Blood Glucose Fasting",
                     "Covid-19 Antibody - IgG",
                     "Thyroid profile -Total (T3, T4 &TSH Ultra-senstive)",
                     "Complete Hemogram\n"+
                             "CRP (C Reactive Protein ) Quantative,Serium\n"+
                             "Iron Studies\n"+
                             "Kidney Function Test\n"+
                             "Vitamin D Total-25 Hydroxy\n"+
                             "Liver Function Test\n"+
                             "lipid Profile"

    };

    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    Button labtest_gotocart,labtest_back;
    ListView listView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test);
        getSupportActionBar().hide();

        labtest_gotocart = findViewById(R.id.labtest_gotocart);
        labtest_back = findViewById(R.id.labtest_back);
        listView = (ListView) findViewById(R.id.listviewlabtest);

        labtest_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LabTestActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
        labtest_gotocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LabTestActivity.this,CartLabActivity.class);
                startActivity(intent);
                finish();
            }
        });

        list = new ArrayList<>();
        for (int i=0;i<packages.length;i++){
            item = new HashMap<String, String>();
            item.put("line1",packages[i][0]);
            item.put("line2",packages[i][1]);
            item.put("line3",packages[i][2]);
            item.put("line4",packages[i][3]);
            item.put("line5","Cons Fee :"+packages[i][4]+"/-");
            list.add(item);
        }

        sa = new SimpleAdapter(this,list,
                R.layout.multi_line,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});

        listView.setAdapter(sa);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int i = position;

                Toast.makeText(LabTestActivity.this, "You are select the item"+i, Toast.LENGTH_SHORT).show();
                Intent it = new Intent(getApplicationContext(),LabTestDetailActivity.class);

                it.putExtra("text1",packages[i][0]);
                it.putExtra("text2",package_details[i]);
                it.putExtra("text3",packages[i][4]);
                startActivity(it);
                finish();
//
            }
        });
    }
}