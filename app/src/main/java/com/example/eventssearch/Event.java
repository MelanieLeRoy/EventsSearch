package com.example.eventssearch;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Event implements Parcelable {

    private int id;
    private String title;
    private String location;
    private String date;
    private double score;
    private String urlTickets;
    private String urlImage;
    private List<String> taxonomies;
    private String description;
    private String genres;

    public Event() {

    }

    public Event(int id, String title, String location, String date, String description) {
        this.id = id;
        this.title = title;
        this.location = location;
        this.date = date;
        this.taxonomies = new ArrayList<>();
        this.description = description;
        this.urlTickets = "";
        this.urlImage = "";
        this.score = 0;
        this.genres = "";
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getUrlTickets() {
        return urlTickets;
    }

    public void setUrlTickets(String urlTickets) {
        this.urlTickets = urlTickets;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public List<String> getTaxonomies() {
        return taxonomies;
    }

    public void setTaxonomies(List<String> taxonomies) {
        this.taxonomies = taxonomies;
    }

    public String getGenres() {

        return this.genres;

    }

    public void setGenres() {

        String someGenres = "";

        for (String taxonomie : this.taxonomies) {
            someGenres += taxonomie + ',';
        }

        this.setGenres(someGenres);
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }


    public void addTaxonomies(String taxonomie) {
        this.taxonomies.add(taxonomie);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString(){
        return this.getTitle() + " - " + this.getId() + " - " + this.getGenres() + " - " + this.getDate() + " - " + this.getDescription();
    }



    //Second constructeur qui sera appelé lors de la "Deparcelablisation"
    public Event(Parcel in)
    {
        this.id = in.readInt();
        this.title = in.readString();
        this.location = in.readString();
        this.date = in.readString();
        this.description = in.readString();
        this.score = in.readDouble();
        this.urlTickets = in.readString();
        this.urlImage = in.readString();
        this.genres = in.readString();
    }

    @SuppressWarnings("rawtypes")
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator()
    {
        public Event createFromParcel(Parcel in)
        {
            return new Event(in);
        }

        @Override
        public Object[] newArray(int size) {
            return null;
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    //On ecrit dans le parcel les données de notre objet
    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.location);
        dest.writeString(this.date);
        dest.writeString(this.description);
        dest.writeDouble(this.score);
        dest.writeString(this.urlTickets);
        dest.writeString(this.urlImage);
        dest.writeString(this.genres);
    }


}
