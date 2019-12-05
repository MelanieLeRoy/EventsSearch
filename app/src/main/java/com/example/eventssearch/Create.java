package com.example.eventssearch;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Create extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);


        Button accueil = findViewById(R.id.accueilC);
        Button btnValider = findViewById(R.id.buttonValider);


        accueil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                setResult(RESULT_CANCELED, i);
                finish();
            }
        });

        btnValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText strId =  findViewById(R.id.editTextId);
                EditText strGenres =  findViewById(R.id.editTextType);
                EditText strTitle =  findViewById(R.id.editTextTitle);
                EditText strDate =  findViewById(R.id.editTextDate);
                EditText strLocation =  findViewById(R.id.editTextLocation);
                EditText strDescription =  findViewById(R.id.editTextDescription);

                Toast.makeText(getApplicationContext(), "Clic valider", Toast.LENGTH_SHORT).show();

                Intent result = new Intent ();
                result.putExtra ( "KEY_ID" , Integer.parseInt(strId.getText().toString()) );
                result.putExtra ("KEY_GENRES", strGenres.getText().toString() );
                result.putExtra ( "KEY_TITLE" , strTitle.getText().toString() );
                result.putExtra ( "KEY_DATE" , strDate.getText().toString() );
                result.putExtra ( "KEY_LOCATION" , strLocation.getText().toString() );
                result.putExtra ( "KEY_DESCRIPTION" , strDescription.getText().toString());

                setResult(RESULT_OK, result);
                finish ();
            }
        });
    }
}
