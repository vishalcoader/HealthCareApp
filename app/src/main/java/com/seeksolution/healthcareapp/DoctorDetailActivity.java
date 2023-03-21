package com.seeksolution.healthcareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;


public class DoctorDetailActivity extends AppCompatActivity {
    private String[][] doctor_detail1 = {
            {"Doctor Name :Abhay Singh ","Hospital Address : Lucknow","Experience : 5years ","Mobile No : 9076796734","600"},
            {"Doctor Name :Shivam singh ","Hospital Address : Ayodhya","Experience : 3years ","Mobile No : 8048796734","300"},
            {"Doctor Name :Omkar yadav ","Hospital Address : Kanpur","Experience : 2years ","Mobile No : 9532796734","800"},
            {"Doctor Name :Kundal singh ","Hospital Address : Varansi","Experience : 6years ","Mobile No : 7645796734","500"},
            {"Doctor Name :Ashok Pandey ","Hospital Address : Ayodhya","Experience : 3years ","Mobile No : 8048796734","300"},
            {"Doctor Name :Divkar Chaurasiya ","Hospital Address : Kanpur","Experience : 2years ","Mobile No : 9532796734","800"},
            {"Doctor Name :Soandeep yadav ","Hospital Address : Varansi","Experience : 6years ","Mobile No : 7645796734","500"},
            {"Doctor Name :Aman Singh ","Hospital Address : Akbarpur","Experience : 4years ","Mobile No : 7846679673","300"}
    };
//    2nd detail

    private String[][] doctor_detail2 = {
            {"Doctor Name :Anshuman Singh ","Hospital Address : Bareli","Experience : 3years ","Mobile No : 7076796734","600"},
            {"Doctor Name :Ashok Pandey ","Hospital Address : Ayodhya","Experience : 3years ","Mobile No : 8048796734","300"},
            {"Doctor Name :Divkar Chaurasiya ","Hospital Address : Kanpur","Experience : 2years ","Mobile No : 9532796734","800"},
            {"Doctor Name :Soandeep yadav ","Hospital Address : Varansi","Experience : 6years ","Mobile No : 7645796734","500"},
            {"Doctor Name :Subhash Pandey ","Hospital Address : Faizabad","Experience : 1years ","Mobile No : 3048796734","300"},
            {"Doctor Name :Harishankar Chaurasiya ","Hospital Delhi : Kanpur","Experience : 6years ","Mobile No : 9532796734","800"},
            {"Doctor Name :Deepak yadav ","Hospital Address : Varansi","Experience : 6years ","Mobile No : 7645796734","500"},
            {"Doctor Name :Suresh Singh ","Hospital Address : Akbarpur","Experience : 4years ","Mobile No : 7846679673","300"}
    };
//    3rd detail

    private String[][] doctor_detail3 = {
            {"Doctor Name :Sachin Singh ","Hospital Address : Lucknow","Experience : 5years ","Mobile No : 9076796734","600"},
            {"Doctor Name :Subhash Pandey ","Hospital Address : Faizabad","Experience : 1years ","Mobile No : 3048796734","300"},
            {"Doctor Name :Harishankar Chaurasiya ","Hospital Delhi : Kanpur","Experience : 6years ","Mobile No : 9532796734","800"},
            {"Doctor Name :Deepak yadav ","Hospital Address : Varansi","Experience : 6years ","Mobile No : 7645796734","500"},
            {"Doctor Name :Abhishek Pandey ","Hospital Address : Ayodhya","Experience : 3years ","Mobile No : 8048796734","300"},
            {"Doctor Name :Sunil Chaurasiya ","Hospital Address : Kanpur","Experience : 2years ","Mobile No : 9532796734","800"},
            {"Doctor Name :Awnish yadav ","Hospital Address : Varansi","Experience : 6years ","Mobile No : 7645796734","500"},
            {"Doctor Name :Kirti Singh ","Hospital Address : Akbarpur","Experience : 4years ","Mobile No : 7846679673","300"}
    };

//    4th detail

    private String[][] doctor_detail4 = {
            {"Doctor Name :Yashi Asthana ","Hospital Address : Lucknow","Experience : 5years ","Mobile No : 9076796734","600"},
            {"Doctor Name :Abhishek Pandey ","Hospital Address : Ayodhya","Experience : 3years ","Mobile No : 8048796734","300"},
            {"Doctor Name :Sunil Chaurasiya ","Hospital Address : Kanpur","Experience : 2years ","Mobile No : 9532796734","800"},
            {"Doctor Name :Awnish yadav ","Hospital Address : Varansi","Experience : 6years ","Mobile No : 7645796734","500"},
            {"Doctor Name :Ajay chaudhary ","Hospital Address : Ayodhya","Experience : 3years ","Mobile No : 8048796734","300"},
            {"Doctor Name :Brijesh Mishra ","Hospital Address : Kanpur","Experience : 2years ","Mobile No : 9532796734","800"},
            {"Doctor Name :Akhilesh srivastav ","Hospital Address : Varansi","Experience : 6years ","Mobile No : 7645796734","500"},
            {"Doctor Name :Abhimanyu srivastav ","Hospital Address : Akbarpur","Experience : 4years ","Mobile No : 7846679673","300"}
    };
//5th detail
    private String[][] doctor_detail5 = {
            {"Doctor Name :Pratap Singh ","Hospital Address : Lucknow","Experience : 5years ","Mobile No : 9076796734","600"},
        {"Doctor Name :Ajay chaudhary ","Hospital Address : Ayodhya","Experience : 3years ","Mobile No : 8048796734","300"},
        {"Doctor Name :Brijesh Mishra ","Hospital Address : Kanpur","Experience : 2years ","Mobile No : 9532796734","800"},
        {"Doctor Name :Akhilesh srivastav ","Hospital Address : Varansi","Experience : 6years ","Mobile No : 7645796734","500"},
            {"Doctor Name :Shivam singh ","Hospital Address : Ayodhya","Experience : 3years ","Mobile No : 8048796734","300"},
            {"Doctor Name :Omkar yadav ","Hospital Address : Kanpur","Experience : 2years ","Mobile No : 9532796734","800"},
            {"Doctor Name :Kundal singh ","Hospital Address : Varansi","Experience : 6years ","Mobile No : 7645796734","500"},
            {"Doctor Name :Hariesh pandey ","Hospital Address : Akbarpur","Experience : 4years ","Mobile No : 7846679673","300"}
    };

    String[][] doctor_detail = {};

    private TextView detail_tv2;
    private ListView listViewDD;
    private Button detail_button;
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_detail);
        getSupportActionBar().hide();

        listViewDD = (ListView) findViewById(R.id.listviewDD);
        detail_tv2= findViewById(R.id.detail_tv2);
        detail_button = findViewById(R.id.detail_btn);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        detail_tv2.setText(title);

//        Here we are put the data in different details

        if(title.compareTo("Physicians")==0){
           doctor_detail = doctor_detail1;
        }else
        if(title.compareTo("Dietician")==0){
            doctor_detail = doctor_detail2;
        }else
        if(title.compareTo("Dentist")==0){
            doctor_detail = doctor_detail3;
        }else
        if(title.compareTo("Surgen")==0){
            doctor_detail = doctor_detail4;
        }else
        if(title.compareTo("Cardiologist")==0){
            doctor_detail = doctor_detail5;
        }else
        {
            Toast.makeText(this, "Dactor not available", Toast.LENGTH_SHORT).show();
        }

       detail_button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(DoctorDetailActivity.this,FindDoctorActivity.class);
               startActivity(intent);
               finish();
           }
       });

        list = new ArrayList<>();
        for (int i=0;i<doctor_detail.length;i++){
            item = new HashMap<String, String>();
            item.put("line1",doctor_detail[i][0]);
            item.put("line2",doctor_detail[i][1]);
            item.put("line3",doctor_detail[i][2]);
            item.put("line4",doctor_detail[i][3]);
            item.put("line5","Cons Fee :"+doctor_detail[i][4]+"/-");
            list.add(item);
        }
        sa = new SimpleAdapter(this,list,
                R.layout.multi_line,
               new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});

    listViewDD.setAdapter(sa);
    listViewDD.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            int i = position;

            Toast.makeText(DoctorDetailActivity.this, "You are select the item"+i, Toast.LENGTH_SHORT).show();
           Intent it = new Intent(getApplicationContext(),AppointmentActivity.class);
            it.putExtra("text1",title);
            it.putExtra("text2",doctor_detail[i][0]);
            it.putExtra("text3",doctor_detail[i][1]);
            it.putExtra("text4",doctor_detail[i][3]);
            it.putExtra("text5",doctor_detail[i][4]);
            startActivity(it);
            finish();
//
        }
    });


    }
}