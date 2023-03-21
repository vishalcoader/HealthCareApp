package com.seeksolution.healthcareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class OrderDetailActivity extends AppCompatActivity {
  private  String[][] order_detail = {};

  HashMap<String,String> item;
  ArrayList list;
  ListView listView;
  Button btn;
  SimpleAdapter sa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        getSupportActionBar().hide();
    btn = findViewById(R.id.orderdetail_back_btn);
    listView = findViewById(R.id.orderdetail_listview);

    btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent  = new Intent(OrderDetailActivity.this,HomeActivity.class);
            startActivity(intent);
            finish();
        }
    });

    Database db = new Database(getApplicationContext(),"healthcare",null,1);
        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","").toString();
        ArrayList dbdata = db.getOrderData(username);

        order_detail = new String[dbdata.size()][];
        for (int i=0;i<order_detail.length;i++){
            order_detail[i] = new String[5];
            String arrData = dbdata.get(i).toString();
            String[] strData = arrData.split(java.util.regex.Pattern.quote("$"));
            order_detail[i][0] = strData[0];
            order_detail[i][1] = strData[1];// + ""+strData[3];
            if (strData[7].compareTo("medicine")==0){
                order_detail[i][3]   = "Del :"+strData[4];
            }else{
                order_detail[i][3]   = "Del :"+strData[4]+" "+strData[5];
            }
            order_detail[i][2] ="Rs. "+strData[6];
            order_detail[i][4] = strData[7];
        }

        list  =  new ArrayList<>();
        for (int i=0;i<order_detail.length;i++){
            item = new HashMap<String , String>();
            item.put("line1",order_detail[i][0]);
            item.put("line2",order_detail[i][1]);
            item.put("line3",order_detail[i][2]);
            item.put("line4",order_detail[i][3]);
            item.put("line5",order_detail[i][4]);
            list.add(item);
        }

//        Log.d("vishal", "onCreate: "+list.size());
    sa = new SimpleAdapter(this,list,
            R.layout.multi_line,
            new String[]{"line1","line2","line3","line4","line5"},
            new int[] {R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
            listView.setAdapter(sa);
//        Toast.makeText(this, "size of data"+dbdata.size(), Toast.LENGTH_SHORT).show();

    }
}