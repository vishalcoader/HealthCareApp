package com.seeksolution.healthcareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.Task;

public class RegisterActivity extends AppCompatActivity {

    private EditText regedit_name,regedit_email,regedit_password,regconedit_password;
    private Button reg_button;
    private TextView reg_tv;
    Database database;

    String reg_name,reg_email,reg_password,con_password;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        regedit_name = findViewById(R.id.regedit_name);
        regedit_email = findViewById(R.id.regedit_email);
        regedit_password = findViewById(R.id.regedit_password);
        regconedit_password = findViewById(R.id.regconedit_password);
        reg_button = findViewById(R.id.reg_button);
        reg_tv = findViewById(R.id.reg_tv);

        reg_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reg_name=regedit_name.getText().toString();
                reg_email=regedit_email.getText().toString();
                reg_password=regedit_password.getText().toString();
                con_password= regconedit_password.getText().toString();

                // Here we are create a database object and pass the parameter database name ,
                database = new Database(getApplicationContext(),"healthcare",null,1);


                if (reg_name.length()==0 || reg_email.length()==0||reg_password.length()==0||con_password.length()==0)
                {
                    Toast.makeText(RegisterActivity.this, "Please field are required", Toast.LENGTH_SHORT).show();
                    regedit_name.setBackgroundResource(R.drawable.error_layout);
                    regedit_password.setBackgroundResource(R.drawable.error_layout);
                    regedit_email.setBackgroundResource(R.drawable.error_layout);
                    regconedit_password.setBackgroundResource(R.drawable.error_layout);
                }
                else{

                    if(reg_password.compareTo(con_password)==0)
                    {
                        if (validPassword(reg_password)){
                            // here we are calling method of database
                            database.register(reg_name,reg_email,reg_password);
                            Toast.makeText(RegisterActivity.this, "Record inserted", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterActivity.this , LoginActivity.class);
                            startActivity(intent);
                            finish();

                        }else{
                            Toast.makeText(RegisterActivity.this, "Please password at least 8 digit, having letter digit and special letter", Toast.LENGTH_SHORT).show();

                        }
                    }else{
                        Toast.makeText(RegisterActivity.this, "Password are didn't match ", Toast.LENGTH_SHORT).show();

                    }
                }


            }
        });

        reg_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }

    public static boolean validPassword(String reg_password){
        int f1=0,f2=0,f3=0;
        if(reg_password.length()<8){
            return false;
        }else{

            for(int p=0; p<reg_password.length();p++){
                if(Character.isLetter(reg_password.charAt(p))){
                    f1=1;
                }

            }

            for(int q=0; q<reg_password.length();q++){
                if(Character.isDigit(reg_password.charAt(q))){
                    f2=1;
                }

            }

            for(int r=0; r<reg_password.length();r++){
                char c = reg_password.charAt(r);
                if(c>=33&&c<=46||c==64){
                    f3=1;
                }

            }

            if (f1==1 && f2==1 && f3==1)
                return true;
            return false;

        }
    }
}



















