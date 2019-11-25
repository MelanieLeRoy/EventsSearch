package com.example.eventssearch;

import android.app.Instrumentation;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSearch = findViewById(R.id.btnsearch);
        Button btnCreate = findViewById(R.id.btncreate);
        Button btnCredits = findViewById(R.id.btncredits);
        Button btnRecap = findViewById(R.id.btnrecap);


        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Clic search", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MainActivity.this, Search.class);
                startActivityForResult(i, 1);
            }
        });

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Clic create", Toast.LENGTH_SHORT).show();
            }
        });

        btnRecap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Clic recap", Toast.LENGTH_SHORT).show();
            }
        });

        btnCredits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Clic credits", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MainActivity.this, Credits.class);
                startActivityForResult(i, 4);
            }
        });


    }

    @Override
    protected void onActivityResult (int requestCode , int resultCode , Intent result) {

        if (resultCode == RESULT_OK && requestCode == 1) {

        }

        if (resultCode == RESULT_CANCELED && requestCode == 1) {

        }

        if (resultCode == RESULT_OK && requestCode == 2) {

        }

        if (resultCode == RESULT_OK && requestCode == 3) {

        }

        if (resultCode == RESULT_OK && requestCode == 4) {

        }

    }
}
