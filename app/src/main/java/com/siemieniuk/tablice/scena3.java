package com.siemieniuk.tablice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

/**
 * Created by student on 2017-04-12.
 */

public class scena3 extends AppCompatActivity {


    TextView t1;
    EditText e1;
    Button b1;

private static DBBaza db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scena3);


        db = new DBBaza(this);
        t1 = (TextView) findViewById(R.id.text1);
        e1 = (EditText) findViewById(R.id.text2);
        b1 = (Button) findViewById(R.id.button);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.addAlarm(new cstrike((String) t1.getText()));

                List<cstrike> moja = db.getAllAlarm();
                for (cstrike cn: moja){
                    String log = "Id:"+cn.getNazwa();
                    Log.d("Baza:", log);


                }


            }



        });



}}