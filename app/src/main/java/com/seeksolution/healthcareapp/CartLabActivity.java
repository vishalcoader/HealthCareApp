package com.seeksolution.healthcareapp;

import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class CartLabActivity extends AppCompatActivity {
    HashMap<String,String> item;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    ArrayList list;
    private String[][] packages = {};
    SimpleAdapter sa;
    Button cartlab_back,cartlab_checkout;
    ListView listView;
    TextView cartlab_package,cartlab_price,cartlab_date,cartlab_time;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_lab);
        getSupportActionBar().hide();

        cartlab_back = findViewById(R.id.cardlab_back);
        cartlab_checkout = findViewById(R.id.cartlab_chekout);
        cartlab_package = findViewById(R.id.cartlab_package);
        cartlab_price = findViewById(R.id.cartlab_price);
        cartlab_date = findViewById(R.id.cartlab_date);
        cartlab_time = findViewById(R.id.cartlab_time);
        listView = findViewById(R.id.cardlab_listview);

        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","").toString();
        Database database = new Database(getApplicationContext(),"healthcare",null,1);
        float totalAmount = 0;

        ArrayList dbta  = database.getCartData(username,"lab");
        Toast.makeText(this, ""+dbta, Toast.LENGTH_SHORT).show();
        packages = new String[dbta.size()][];
        for (int i=0;i<packages.length;i++){
            packages[i] = new String[5];
        }

        for(int i=0;i<dbta.size();i++){
            String arrData = dbta.get(i).toString();
            String[] strdata = arrData.split(java.util.regex.Pattern.quote("$"));
            packages[i][0]=strdata[0];
            packages[i][4] = "Cost : "+strdata[1]+"/-";
            totalAmount = totalAmount+Float.parseFloat(strdata[1]);

        }


        cartlab_price.setText("Total Cost :"+totalAmount);
        list = new ArrayList<>();
        for (int i=0; i<packages.length;i++){
            item = new HashMap<String,String>();
            item.put("line1",packages[i][0]);
            item.put("line2",packages[i][1]);
            item.put("line3",packages[i][2]);
            item.put("line4",packages[i][3]);
            item.put("line5",packages[i][4]);
            list.add(item);
        }

        sa = new SimpleAdapter(this,list,
                R.layout.multi_line,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});

        listView.setAdapter(sa);

        cartlab_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartLabActivity.this,LabTestActivity.class);
                startActivity(intent);
                finish();
            }
        });

        cartlab_checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartLabActivity.this,LabTestBookActivity.class);
                intent.putExtra("price",cartlab_price.getText());
                intent.putExtra("date",cartlab_date.getText());
                intent.putExtra("time",cartlab_time.getText());
                startActivity(intent);
                finish();
            }
        });


        initDatepicker();
        cartlab_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

        // Time picker are calling here

        initTimePicker();
        cartlab_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog.show();
            }
        });


    }


    private void initDatepicker(){
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month= month+1;
                cartlab_date.setText(dayOfMonth+"/"+month+"/"+year);

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
                cartlab_time.setText(hourOfDay+" : "+minute);
            }
        };
        Calendar cal = Calendar.getInstance();
        int hrs = cal.get(Calendar.HOUR);
        int mins = cal.get(Calendar.MINUTE);
        int style = AlertDialog.THEME_HOLO_DARK;
        timePickerDialog = new TimePickerDialog(this,style,timeSetListener,hrs,mins,true);

    }

}