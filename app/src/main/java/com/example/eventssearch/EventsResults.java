package com.example.eventssearch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_results);

        final TextView textJson = findViewById(R.id.tryEvents);

        List<Event> eventsListResult= new ArrayList<>();

        Intent i = getIntent();

        String url = i.getStringExtra("KEY_URL");

        Ion.with(getApplicationContext())
                . load(url)
                . asJsonObject()
                . setCallback( new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        JsonArray events = result.get("events").getAsJsonArray();
                        textJson.setText(events.toString());
                        Iterator ite = events.iterator();
                        while (ite.hasNext()) {
                            JsonObject item = (JsonObject) ite.next();

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


                            Event searchedEvent = new Event(id, title, location, date, description);
                            searchedEvent.setTaxonomies(genres);
                            searchedEvent.setScore(score);
                            searchedEvent.setUrlTickets(url);

                            JsonObject performerImage = (JsonObject) item.getAsJsonArray("performers").get(0);

                            if(!performerImage.get("image").isJsonNull()) {
                                String urlImage = performerImage.getAsJsonPrimitive("image").getAsString();
                                searchedEvent.setUrlImage(urlImage);
                            }

                            System.out.println(searchedEvent.toString());
                        }
                    }
                });
    }
}
