package com.seeksolution.healthcareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BuyMedicineBookActivity extends AppCompatActivity {
    private EditText BMbook_name,BMbook_address,BMbook_contact,BMbook_pincode;
    private Button BMbook_btn_register,BMbook_btn_back;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine_book);

        BMbook_address = findViewById(R.id.BMbook_address);
        BMbook_name = findViewById(R.id.BMbook_name);
        BMbook_contact = findViewById(R.id.BMbook_contact);
        BMbook_pincode = findViewById(R.id.BMbook_pincode);

        BMbook_btn_register  = findViewById(R.id.BMbook_btn_register);
        BMbook_btn_back   = findViewById(R.id.BMbook_btn_back);

        Intent intent= getIntent();
        String[] price = intent.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));
        String date = intent.getStringExtra("date");
//        String time = intent.getStringExtra("time");

        BMbook_btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = BMbook_name.getText().toString();
                String address = BMbook_address.getText().toString();
                String contact = BMbook_contact.getText().toString();
                String pincode = BMbook_pincode.getText().toString();
                if (name.isEmpty() || address.isEmpty() || contact.isEmpty() || pincode.isEmpty() ){
                    Toast.makeText(BuyMedicineBookActivity.this, "Please fill the all data", Toast.LENGTH_SHORT).show();
                    BMbook_pincode.setBackgroundResource(R.drawable.error_layout);
                    BMbook_address.setBackgroundResource(R.drawable.error_layout);
                    BMbook_contact.setBackgroundResource(R.drawable.error_layout);
                    BMbook_name.setBackgroundResource(R.drawable.error_layout);

                }else{
                    SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                    String username = sharedPreferences.getString("username","");

                    Database db = new Database(getApplicationContext(),"healthcare",null,1);
                    db.addOrder(username,BMbook_name.getText().toString(),BMbook_address.getText().toString(),BMbook_contact.getText().toString(),Integer.parseInt(BMbook_pincode.getText().toString()),date,"",Float.parseFloat(price[1]),"medicine");
                    db.removeCart(username,"medicine");
                    Toast.makeText(BuyMedicineBookActivity.this, "Your booking is done successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(BuyMedicineBookActivity.this,HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        BMbook_btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( BuyMedicineBookActivity.this,BuyMedicineActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}