package com.siemieniuk.tablice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import static android.R.id.text1;

/**
 * Created by student on 2017-04-12.
 */

public class scena1 extends AppCompatActivity {

    Button b1;
    EditText e1;
    TextView t1;
    public static ArrayList<String> lista = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scena1);

        b1 = (Button) findViewById(R.id.button);
        e1 = (EditText) findViewById(R.id.text1);
        t1 = (TextView) findViewById(R.id.text2);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String tekst = e1.getText().toString();
                lista.add(tekst);
                Log.d("dane lista", String.valueOf(lista));

                for (int i=0; i<lista.size();i++){

                    t1.setText(lista.get(i));

                }


            }



        });
}}
