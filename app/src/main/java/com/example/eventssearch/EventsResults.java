package com.example.eventssearch;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EventsResults extends AppCompatActivity {

    private static final int DETAILS_EVENT_REQUEST_CODE = 1;
    List<Event> eventsListResult;
    ArrayAdapter<Event> adapterEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_results);

        ListView listEvents = findViewById(R.id.listevents);

        eventsListResult = new ArrayList<>();

        adapterEvents = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, eventsListResult);

        listEvents.setAdapter(adapterEvents);

        System.out.println("LISTE VIDE NORMALEMENT: " + eventsListResult);

        Intent i = getIntent();

        String url = i.getStringExtra("KEY_URL");

        Ion.with(getApplicationContext())
                . load(url)
                . asJsonObject()
                . setCallback( new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        JsonArray events = result.get("events").getAsJsonArray();

                        //Iterator ite = events.iterator();
                        for (JsonElement itemElement : events) {

                            JsonObject item = (JsonObject) itemElement;

                            int id = item.getAsJsonPrimitive("id").getAsInt();

                            String title = item.getAsJsonPrimitive("title").getAsString();

                            JsonObject venue = (JsonObject) item.get("venue");
                            String location = venue.getAsJsonPrimitive("address").getAsString();

                            String date = item.getAsJsonPrimitive("datetime_utc").getAsString();

                            JsonArray taxonomies = item.getAsJsonArray("taxonomies");
                            List<String> genres = new ArrayList<>();

                            Iterator genreIte = taxonomies.iterator();
                            while(genreIte.hasNext()) {
                                JsonObject itemGenre = (JsonObject) genreIte.next();
                                genres.add(itemGenre.getAsJsonPrimitive("name").getAsString());
                            }

                            String description = item.getAsJsonPrimitive("description").getAsString();

                            double score = item.getAsJsonPrimitive("score").getAsDouble();

                            String url = item.getAsJsonPrimitive("url").getAsString();

                            System.out.println("AVANT CHANGEMENT EVENT: " + eventsListResult);

                            Event searchedEvent = new Event(id, title, location, date, description, false);
                            searchedEvent.setTaxonomies(genres);
                            searchedEvent.setScore(score);
                            searchedEvent.setUrlTickets(url);

                            System.out.println("EVENT CHANGE:" + searchedEvent);

                            System.out.println("APRES CHANGEMENT EVENT: " + eventsListResult);

                            JsonObject performerImage = (JsonObject) item.getAsJsonArray("performers").get(0);

                            if(!performerImage.get("image").isJsonNull()) {
                                String urlImage = performerImage.getAsJsonPrimitive("image").getAsString();
                                searchedEvent.setUrlImage(urlImage);
                            }

                            System.out.println("AVANT AJOUT: " + eventsListResult);
                            adapterEvents.add(searchedEvent);
                            System.out.println("APRES AJOUT: " + eventsListResult);
                        }
                    }
                });





    listEvents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Event event = (Event) parent.getItemAtPosition(position);

               Intent intent = new Intent ( EventsResults.this, EventDetails.class );
               intent.putExtra("ID_KEY", event.getId());
               intent.putExtra("TITLE_KEY", event.getTitle());
               intent.putExtra("LOCATION_KEY", event.getLocation());
               intent.putExtra("DATE_KEY", event.getDate());
               intent.putExtra("DESCRIPTION_KEY", event.getDescription());
               intent.putExtra("DATE_KEY", event.getDate());
               intent.putExtra("GENRES_KEY", event.getGenres());
               intent.putExtra("SCORE_KEY", event.getScore());
               intent.putExtra("URL_KEY", event.getUrlTickets());
               intent.putExtra("PARTICIPE_KEY", event.getParticipe());

               if(event.getUrlImage() != null) {
                   intent.putExtra("URLIMAGE_KEY", event.getUrlImage());
               }

               intent.putExtra("POSITION_KEY", position);

               startActivityForResult(intent, DETAILS_EVENT_REQUEST_CODE);
           }
       });


    }

   @Override
   protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

       System.out.println("je rentre dans la fonction");

       if (resultCode == RESULT_OK && requestCode == DETAILS_EVENT_REQUEST_CODE) {

           Toast.makeText(getApplicationContext(), "Event checked", Toast.LENGTH_SHORT).show();
       }
   }

}
