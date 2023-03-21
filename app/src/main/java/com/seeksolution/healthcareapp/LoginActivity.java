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

public class LoginActivity extends AppCompatActivity {

    private EditText edit_name,edit_password;
    private Button button_login;
    private TextView sign_in;
    boolean login ;
    String name,password;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

        edit_name = findViewById(R.id.edit_name);
        edit_password = findViewById(R.id.edit_password);
        button_login = findViewById(R.id.button_login);
        sign_in = findViewById(R.id.sign_in);



        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = edit_name.getText().toString();
                password = edit_password.getText().toString();
                Database database = new Database(getApplicationContext(),"healthcare",null,1);

                if (name.isEmpty() || password.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Please fill the required field", Toast.LENGTH_SHORT).show();
                    edit_name.setBackgroundResource(R.drawable.error_layout);
                    edit_password.setBackgroundResource(R.drawable.error_layout);
                }
                else{
                    if (database.login(name,password)==1){
                        Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("username",name);
                        editor.putBoolean("ISLOOGEDIN",true);
                        // to save our data to key and value
                        editor.apply();
                        Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                        startActivity(intent);
                        finish();

                    }else{
                        Toast.makeText(LoginActivity.this, "Invalid user name and password", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });



    }
}