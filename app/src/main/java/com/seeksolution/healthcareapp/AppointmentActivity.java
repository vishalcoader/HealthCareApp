package com.seeksolution.healthcareapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class AppointmentActivity extends AppCompatActivity {

    private TextView appoint_tv,appoint_date,appoint_time;
    private EditText appoint_name,appoint_address,appoint_contact,appoint_fee;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private AppCompatButton appoint_register,appoint_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);
        getSupportActionBar().hide();

        appoint_tv = findViewById(R.id.appoint_tv);
        appoint_name = findViewById(R.id.appoint_name);
        appoint_contact = findViewById(R.id.appoint_contact);
        appoint_fee = findViewById(R.id.appoint_fee);
        appoint_address = findViewById(R.id.appoint_address);
        appoint_date = findViewById(R.id.appoint_date);
        appoint_time = findViewById(R.id.appoint_time);
        appoint_register = findViewById(R.id.appoint_register);
        appoint_back = findViewById(R.id.appoint_back);

        appoint_name.setKeyListener(null);
        appoint_address.setKeyListener(null);
        appoint_contact.setKeyListener(null);
        appoint_fee.setKeyListener(null);
//         appoint_tv.setKeyListener(null);

        Intent it = getIntent();
        String title = it.getStringExtra("text1");
        String name = it.getStringExtra("text2");
        String address = it.getStringExtra("text3");
        String contact = it.getStringExtra("text4");
        String fee = it.getStringExtra("text5");

        appoint_tv.setText(title);
        appoint_name.setText(name);
        appoint_address.setText(address);
        appoint_contact.setText(contact);
        appoint_fee.setText(fee);

        //  Datepicke method are calling here

        initDatepicker();
        appoint_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

        // Time picker are calling here

        initTimePicker();
        appoint_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog.show();
            }
        });

        appoint_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AppointmentActivity.this ,FindDoctorActivity.class);
                startActivity(intent);
                finish();
            }
        });

        appoint_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = appoint_name.getText().toString();
                String address = appoint_address.getText().toString();
                String contact = appoint_contact.getText().toString();
                String fee = appoint_fee.getText().toString();

              if (name.isEmpty() || address.isEmpty() || contact.isEmpty() || fee.isEmpty()){
                  Toast.makeText(AppointmentActivity.this, "Please fill  all data ", Toast.LENGTH_SHORT).show();
                  appoint_name.setBackgroundResource(R.drawable.error_layout);
                  appoint_address.setBackgroundResource(R.drawable.error_layout);
                  appoint_contact.setBackgroundResource(R.drawable.error_layout);
                  appoint_fee.setBackgroundResource(R.drawable.error_layout);
              }else{
                  Database db = new Database(getApplicationContext(),"healthcare",null,1);
                  SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                  String username = sharedPreferences.getString("username","");

                  if (db.checkAppointmentExixts(username,title+"=>"+name,address,contact,appoint_date.getText().toString(),appoint_time.getText().toString())==1)
                  {
                      Toast.makeText(AppointmentActivity.this, "Appointment Already added ", Toast.LENGTH_SHORT).show();
                  }
                  db.addOrder(username,title+"\n"+name,address,contact,0,appoint_date.getText().toString(),appoint_time.getText().toString(),Float.parseFloat(fee),"appointment");
                  Toast.makeText(AppointmentActivity.this, "Your Appointment has  successfully", Toast.LENGTH_SHORT).show();
                  Intent intent = new Intent(AppointmentActivity.this,HomeActivity.class);
                  startActivity(intent);
                  finish();
              }
            }
        });
    }

    private void initDatepicker(){
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month= month+1;
                appoint_date.setText(dayOfMonth+"/"+month+"/"+year);

            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_DARK;
        datePickerDialog = new DatePickerDialog(this,style,dateSetListener,year,month,day);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis()+86400000);
    }

    private void initTimePicker(){
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                appoint_time.setText(hourOfDay+" : "+minute);
            }
        };
        Calendar cal = Calendar.getInstance();
        int hrs = cal.get(Calendar.HOUR);
        int mins = cal.get(Calendar.MINUTE);
        int style = AlertDialog.THEME_HOLO_DARK;
        timePickerDialog = new TimePickerDialog(this,style,timeSetListener,hrs,mins,true);

    }



}
