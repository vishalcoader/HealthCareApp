package com.seeksolution.healthcareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class BuyMedicineActivity extends AppCompatActivity {

    private String[][] packages =
            {
                    {"Uprise-03 1000IU Capsules","","","","50"},
                    {"HealthVit Chromium Picolinate 200mcg Capsules","","","","305"},
                    {"Vitamin B Complex Capsules","","","","448"},
                    {"Inlife Vitamin E Wheat Germ oil Capsules","","","","539"},
                    {"Dolo 650mg Tablets","","","","30"},
                    {"Crocin 650 Advances Tablets","","","","50"},
                    {"Strepsils Medicated Lozengers for Sore Throat","","","","40"},
                    {"Tata 1mg Calcium +Vitamin D3","","","","30"},
                    {"Feronia -XT Tablets","","","","140"},
                    {"Uprise-03 Capsules","","","","130"},
            };
    private String[] packages_details = {
      "Building and keeping the bones & teeth strong\n"+
      "Reducing Fatigue/stress and muscular pain \n"+
      "Boosting Imunity and Increasing resistence against Infection",
      "Chromium is an essential trace mineral plays an important role in helping insulin regulate blood glucose.",
       "Provides relif from vitamin B deficiencies \n"+
       "Helps information of red blood cells\n"+
       "Maintain healthy nervous system",
       "It promots health as well as skin benefit.\n"+
       "It helps reduce skin bleanish and pigmentation.\n"+
       "It act as safeguard the skin from the harsh UVB sun rays. ",
        "Dolo 650 Tablet is a common painkiller for treating aches and pains.\n"+
                " It is widely used and rarely causes any side effects\n"+
                " if taken properly. To get the most benefits." +
                " Do not take more or for longer than needed as that can be dangerous.",
            "It helps relieve fever and bring down a high temperature\n" +
                    "Paracetamol 650 is full of analgesic and antipyretic properties\n" +
                    "It is also effective in muscle and joint pain, headache, and period pain",
            "Relieves the symptoms of a bacterial throat infection and soothes the recovery process\n" +
                    "Provides a warm and comforting feeling during sore throat",
            "Improves bone density, bone strength and muscle functions\n" +
                    "It may help lower the risk of osteoporosis\n" +
                    "Helps to maintain strong bones and improve immunity\n" +
                    "Promotes mobility and flexibility of joints",
            "Feronia Hp tablet comprises iron, folic acid, cholecalciferol, and pyridoxine, \n"+
                    "and it is a full anaemia treatment regimen that has all of these nutrients.",
            "Uprise-D3 60K Capsule is a form of vitamin D. It raises vitamin D levels in your blood.\n"+
                    " This in turn raises calcium levels in your blood by helping you absorb more calcium from food."
    };

    HashMap<String,String> item;
    ArrayList arrayList;
    SimpleAdapter sa;
    ListView listView;
    Button buymedicine_back,buymedicine_gotocart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine);
        getSupportActionBar().hide();

        buymedicine_back = findViewById(R.id.buymedicine_back);
        buymedicine_gotocart = findViewById(R.id.buymedicine_gotocart);
        listView = findViewById(R.id.buymedicine_listview);

        buymedicine_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BuyMedicineActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        buymedicine_gotocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BuyMedicineActivity.this, "Your medicine successfully order", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(BuyMedicineActivity.this,CartBuyMedicineActivity.class);
                startActivity(intent);

            }
        });

        arrayList  =  new ArrayList<>();
        for (int i=0;i<packages.length;i++){
            item = new HashMap<String , String>();
            item.put("line1",packages[i][0]);
            item.put("line2",packages[i][1]);
            item.put("line3",packages[i][2]);
            item.put("line4",packages[i][3]);
            item.put("line5","Total Cost : "+packages[i][4]+"/-");
            arrayList.add(item);
        }

        sa = new SimpleAdapter(this,arrayList,
                R.layout.multi_line,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[] {R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        listView.setAdapter(sa);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int i = position;

                Toast.makeText(BuyMedicineActivity.this, "You are select the item"+i, Toast.LENGTH_SHORT).show();
                Intent it = new Intent(getApplicationContext(),BuyMedicineDetailActivity.class);
                it.putExtra("text1",packages[i][0]);
                it.putExtra("text2",packages_details[i]);
                it.putExtra("text3",packages[i][4]);
                startActivity(it);
//
            }
        });

    }
}