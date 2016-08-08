package com.example.toshiba.testdb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private DBcontent dbcontent;
    TextView txv,txv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txv=(TextView)findViewById(R.id.txv);
        txv2=(TextView)findViewById(R.id.txv2);
        // 建立資料庫物件
        dbcontent = new DBcontent(getApplicationContext());
        dbcontent.sample();
        //int a=dbcontent.get_bmi(1);

        txv.setText(String.valueOf(dbcontent.get_bmi(1)));
        //dbcontent.update_bmi(1,20160722,170,65);
        txv2.setText(String.valueOf(dbcontent.get_bmi(1)));
        //txv.setText(String.valueOf(dbcontent.getCount_bmi()));

    }
}
