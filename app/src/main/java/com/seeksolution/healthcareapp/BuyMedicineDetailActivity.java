package com.seeksolution.healthcareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BuyMedicineDetailActivity extends AppCompatActivity {

    private TextView buymedicine_detail_tv1,buymedicine_detail_tv2;
    private EditText buymedicine_detail_edit;
    private Button buymedicine_detail_back,buymedicine_detail_addcart;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine_detail);

        buymedicine_detail_tv1  = findViewById(R.id.buymedicine_detail_tv1);
        buymedicine_detail_edit  = findViewById(R.id.buymedicine_detail_edit);
        buymedicine_detail_tv2  = findViewById(R.id.buymedicine_detail_text);
        buymedicine_detail_back = findViewById(R.id.buymedicine_detail_back);
        buymedicine_detail_addcart = findViewById(R.id.buymedicine_detail_addcart);

        buymedicine_detail_edit.setKeyListener(null);

        Intent intent = getIntent();
        buymedicine_detail_tv1.setText(intent.getStringExtra("text1"));
        buymedicine_detail_edit.setText(intent.getStringExtra("text2"));
        buymedicine_detail_tv2.setText("Total Cost : "+intent.getStringExtra("text3")+"/-");

        buymedicine_detail_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BuyMedicineDetailActivity.this,BuyMedicineActivity.class);
                startActivity(intent);
                finish();
            }
        });

        buymedicine_detail_addcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();
                String product = buymedicine_detail_tv1.getText().toString();
                float price = Float.parseFloat(intent.getStringExtra("text3").toString());

                Database db = new Database(getApplicationContext(),"healthcare",null,1);
                if (db.checkCart(username,product)==1){
                    Toast.makeText(BuyMedicineDetailActivity.this, "Product already added", Toast.LENGTH_SHORT).show();
                }else{
                    db.addCart(username,product,price,"medicine");
                    Toast.makeText(BuyMedicineDetailActivity.this, "Record Inserted in cart", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(BuyMedicineDetailActivity.this,BuyMedicineActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });


    }
}