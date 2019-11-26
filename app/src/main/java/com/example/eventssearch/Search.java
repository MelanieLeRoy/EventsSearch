package com.example.eventssearch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;



public class Search extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Button accueilS = findViewById(R.id.accueilS);

        final EditText searchQuery = findViewById(R.id.searchquery);

        Button rechercher = findViewById(R.id.rechercher);

        accueilS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                setResult(RESULT_CANCELED, i);
                finish();
            }
        });

        rechercher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = searchQuery.getText().toString();

                String url = "https://api.seatgeek.com/2/events?&q=" + query + "&client_id=MTk2Mjk1Nzd8MTU3NDY4NTkyMy40Nw";

                Intent i = new Intent(Search.this, EventsResults.class);
                i.putExtra("KEY_URL", url);
                startActivity(i);
                setResult(RESULT_OK, i);


            }
        });
    }
}
