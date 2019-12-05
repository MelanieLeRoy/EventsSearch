package com.example.eventssearch;


import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;


public class MainActivity extends AppCompatActivity {

    EventList myEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.myEvents = new EventList();


        Button btnSearch = findViewById(R.id.btnsearch);
        Button btnCreate = findViewById(R.id.btncreate);
        Button btnCredits = findViewById(R.id.btncredits);
        Button btnRecap = findViewById(R.id.btnrecap);


        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Clic search", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MainActivity.this, Search.class);
                startActivity(i);
            }
        });

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Clic create", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MainActivity.this, Create.class);
                startActivityForResult(i, 2);
            }
        });

        btnRecap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Clic recap", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MainActivity.this, EventsRecap.class);
                i.putExtra("myEventsList", (Parcelable) myEvents);
                startActivity(i);
            }
        });

        btnCredits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Clic credits", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MainActivity.this, Credits.class);
                startActivity(i);
            }
        });


    }

    @Override
    protected void onActivityResult (int requestCode , int resultCode , Intent result) {


        if (resultCode == RESULT_OK && requestCode == 2) {
            int id = result . getIntExtra ( "KEY_ID" ,0);
            String genres = result . getStringExtra("KEY_GENRES");
            String title = result . getStringExtra ( "KEY_TITLE" );
            String date = result . getStringExtra ( "KEY_DATE" );
            String location= result . getStringExtra ( "KEY_LOCATION" );
            String description = result . getStringExtra ( "KEY_DESCRIPTION" );
            final Event event = new Event (id, title, location, date, description);
            event.setGenres(genres);

            String formattedTitle = title.replaceAll(" ", "+");
            String urlImage = "https://www.flickr.com/services/rest/?method=flickr.photos.search&api_key=2095ad9fcc4dfebe27fae2d858d3c712&text=" + formattedTitle + "&extras=url_t&format=json&nojsoncallback=1";

            Ion.with(getApplicationContext())
                    . load(urlImage)
                    . asJsonObject()
                    . setCallback( new FutureCallback<JsonObject>() {
                        @Override
                        public void onCompleted(Exception e, JsonObject result) {
                            System.out.println(result);
                            JsonObject photos = result.get("photos").getAsJsonObject();

                            JsonArray photo = photos.get("photo").getAsJsonArray();


                            JsonObject firstPhoto = (JsonObject) photo.get(0);

                            String urlImagePhoto = firstPhoto.get("url_t").getAsString();
                            event.setUrlImage(urlImagePhoto);
                            System.out.println("URL IMAGE PHOTO : " + urlImagePhoto);
                            myEvents.add(event);
                            System.out.println( "LISTE EVENT" + myEvents);
                        }
                    });



        }

        if (resultCode == RESULT_OK && requestCode == 3) {

        }
    }




}
