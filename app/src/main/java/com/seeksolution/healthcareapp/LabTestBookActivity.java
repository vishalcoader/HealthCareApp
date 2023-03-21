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

public class LabTestBookActivity extends AppCompatActivity {

    private EditText book_name,book_address,book_contact,book_pincode;
    private Button book_btn_register,book_btn_back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_book);

        book_name = findViewById(R.id.book_name);
        book_address = findViewById(R.id.book_address);
        book_contact = findViewById(R.id.book_contact);
        book_pincode = findViewById(R.id.book_pincode);

        book_btn_register  = findViewById(R.id.book_btn_register);
        book_btn_back   = findViewById(R.id.book_btn_back);

        Intent intent= getIntent();
        String[] price = intent.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));
        String date = intent.getStringExtra("date");
        String time = intent.getStringExtra("time");

        book_btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = book_name.getText().toString();
                String address = book_address.getText().toString();
                String contact = book_contact.getText().toString();
                String pincode = book_pincode.getText().toString();
               if (name.isEmpty() || address.isEmpty() || contact.isEmpty() || pincode.isEmpty() ){
                   Toast.makeText(LabTestBookActivity.this, "Please fill the all data", Toast.LENGTH_SHORT).show();
                   book_pincode.setBackgroundResource(R.drawable.error_layout);
                   book_address.setBackgroundResource(R.drawable.error_layout);
                   book_contact.setBackgroundResource(R.drawable.error_layout);
                   book_name.setBackgroundResource(R.drawable.error_layout);

               }else{
                   SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                   String username = sharedPreferences.getString("username","").toString();

                   Database db = new Database(getApplicationContext(),"healthcare",null,1);
                   db.addOrder(username,book_name.getText().toString(),book_address.getText().toString(),book_contact.getText().toString(),Integer.parseInt(book_pincode.getText().toString()),date,time,Float.parseFloat(price[1]),"lab");
                   db.removeCart(username,"lab");
                   Toast.makeText(LabTestBookActivity.this, "Your booking is done successfully", Toast.LENGTH_SHORT).show();
                   Intent intent = new Intent(LabTestBookActivity.this,HomeActivity.class);
                   startActivity(intent);
                   finish();
               }
            }
        });
        book_btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( LabTestBookActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}