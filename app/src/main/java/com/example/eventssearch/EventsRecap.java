package com.example.eventssearch;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class EventsRecap extends AppCompatActivity {

    EventList myEvents;
    ArrayAdapter<Event> adapterMyEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_recap);

        ListView myEventsList = findViewById(R.id.listmyevents);

        myEvents = new EventList();

        Bundle b = getIntent().getExtras();

        myEvents = b.getParcelable("myEventsList");

        Event unEvent = new Event(35, "Un exemple d'evenement", "dans un lieu reculé", "le 36 du mois prochain", "bonjour l'event");
        unEvent.setScore(0.235);
        unEvent.setGenres("futuriste, comique");
        myEvents.add(unEvent);

        adapterMyEvents = new EventAdapter(getApplicationContext(), myEvents);

        myEventsList.setAdapter(adapterMyEvents);

        myEventsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Event event = (Event) parent.getItemAtPosition(position);

                Intent intent = new Intent ( EventsRecap.this, EventDetails.class );
                intent.putExtra("ID_KEY", event.getId());
                intent.putExtra("TITLE_KEY", event.getTitle());
                intent.putExtra("LOCATION_KEY", event.getLocation());
                intent.putExtra("DATE_KEY", event.getDate());
                intent.putExtra("DESCRIPTION_KEY", event.getDescription());
                intent.putExtra("DATE_KEY", event.getDate());
                intent.putExtra("GENRES_KEY", event.getGenres());
                intent.putExtra("SCORE_KEY", event.getScore());
                intent.putExtra("URL_KEY", event.getUrlTickets());
                intent.putExtra("URLIMAGE_KEY", event.getUrlImage());

                startActivity(intent);

            }
        });

    }
}
