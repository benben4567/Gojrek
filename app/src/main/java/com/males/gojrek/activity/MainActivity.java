package com.males.gojrek.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.males.gojrek.R;

public class MainActivity extends AppCompatActivity {
    Button postCash, postGopay, getCash, getGopay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        postCash = (Button) findViewById(R.id.postCash);
        postGopay = (Button) findViewById(R.id.postGopay);
        getCash = (Button) findViewById(R.id.getCash);
        getGopay = (Button) findViewById(R.id.getGopay);

        postCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), TambahCashActivity.class);
                startActivity(i);
            }
        });

        postGopay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), TambahGopayActivity.class);
                startActivity(i);
            }
        });

        getCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), CashActivity.class);
                startActivity(i);
            }
        });

        getGopay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), GopayActivity.class);
                startActivity(i);
            }
        });
    }
}
