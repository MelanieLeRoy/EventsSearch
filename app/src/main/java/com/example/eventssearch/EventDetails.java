package com.example.eventssearch;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class EventDetails extends AppCompatActivity {

    Event selectedEvent;

    Button book;

    Button accueil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        TextView id_title = findViewById(R.id.id_and_title);
        TextView genres = findViewById(R.id.genres);
        TextView location = findViewById(R.id.location);
        TextView date = findViewById(R.id.date);
        TextView score = findViewById(R.id.score);
        TextView description = findViewById(R.id.description);

        ImageView image = findViewById(R.id.imageView);


        book = findViewById(R.id.bookevent);
        accueil = findViewById(R.id.accueilretour);


        Intent eventIntent = getIntent();

        int idText = eventIntent.getIntExtra("ID_KEY", 0);
        String titleText = eventIntent.getStringExtra("TITLE_KEY");
        String genresText = eventIntent.getStringExtra("GENRES_KEY");
        String locationText = eventIntent.getStringExtra("LOCATION_KEY");
        String dateText = eventIntent.getStringExtra("DATE_KEY");
        double scoreValue = eventIntent.getDoubleExtra("SCORE_KEY", 0);
        String descriptionText = eventIntent.getStringExtra("DESCRIPTION_KEY");
        String urlTicketsText = eventIntent.getStringExtra("URL_KEY");

        selectedEvent = new Event(idText, titleText, locationText, dateText, descriptionText);
        selectedEvent.setGenres(genresText);
        selectedEvent.setScore(scoreValue);
        selectedEvent.setUrlTickets(urlTicketsText);


        System.out.println("URL IMAGE : " + eventIntent.getStringExtra("URLIMAGE_KEY"));
        System.out.println("URL TICKETS : " + eventIntent.getStringExtra("URL_KEY"));
        if(!eventIntent.getStringExtra("URLIMAGE_KEY").equals("") ){

            String urlImage = eventIntent.getStringExtra("URLIMAGE_KEY");
            selectedEvent.setUrlImage(urlImage);

            Picasso.get().load(urlImage).into(image);
        }

        id_title.setText(idText + " - " + titleText);
        genres.setText(genresText);
        location.setText(locationText);
        date.setText(dateText);
        score.setText(scoreValue + "");
        description.setText(descriptionText);



        if(urlTicketsText.equals("")) {
            book.setEnabled(false);
            book.setText("Pas de réservation disponible");
        }


        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!selectedEvent.getUrlTickets().equals("")) {
                    Uri uri = Uri.parse(selectedEvent.getUrlTickets());
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                } else {
                    //book.setText("Pas de réservation disponible");
                    book.setEnabled(false);
                }
            }
        });

        accueil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(EventDetails.this, MainActivity.class);

                startActivity(i);
            }
        });



    }
}
