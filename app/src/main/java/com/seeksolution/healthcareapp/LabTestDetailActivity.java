package com.seeksolution.healthcareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LabTestDetailActivity extends AppCompatActivity {
  private TextView labtest_detail_tv2,labtest_detail_tv3;
  private EditText labtest_detail_edit;
  private Button labtest_detail_addcart,labtest_detail_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_detail);
        getSupportActionBar().hide();

        labtest_detail_tv2 = findViewById(R.id.labtest_detail_tv2);
        labtest_detail_tv3 = findViewById(R.id.labtest_detail_tv3);
        labtest_detail_edit = findViewById(R.id.labtest_detail_edittext);

        labtest_detail_addcart = findViewById(R.id.labtest_detail_addcart);
        labtest_detail_back = findViewById(R.id.labtest_detail_back);


        labtest_detail_edit.setKeyListener(null);

        Intent intent = getIntent();
        labtest_detail_tv2.setText(intent.getStringExtra("text1"));
        labtest_detail_tv3.setText("Total cost : "+intent.getStringExtra("text3")+"/-");
        labtest_detail_edit.setText(intent.getStringExtra("text2"));
        labtest_detail_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LabTestDetailActivity.this,LabTestActivity.class);
                startActivity(intent);
                finish();
            }
        });

        labtest_detail_addcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();
                String product = labtest_detail_tv2.getText().toString();
                float price = Float.parseFloat(intent.getStringExtra("text3").toString());

                Database db = new Database(getApplicationContext(),"healthcare",null,1);
                if (db.checkCart(username,product)==1){
                    Toast.makeText(LabTestDetailActivity.this, "Product already added", Toast.LENGTH_SHORT).show();
                }else{
                    db.addCart(username,product,price,"lab");
                    Toast.makeText(LabTestDetailActivity.this, "Record Inserted in cart", Toast.LENGTH_SHORT).show();
                   Intent intent = new Intent(LabTestDetailActivity.this,LabTestActivity.class);
                   startActivity(intent);
                   finish();
                }
            }
        });

    }
}