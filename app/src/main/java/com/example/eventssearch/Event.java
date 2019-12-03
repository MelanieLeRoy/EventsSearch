package com.example.eventssearch;

import java.util.ArrayList;
import java.util.List;

public class Event {

    private int id;
    private String title;
    private String location;
    private String date;
    private double score;
    private String urlTickets;
    private String urlImage;
    private List<String> taxonomies;
    private String description;
    private boolean participe;



    public Event(int id, String title, String location, String date, String description, boolean participe) {
        this.id = id;
        this.title = title;
        this.location = location;
        this.date = date;
        this.taxonomies = new ArrayList<>();
        this.description = description;
        this.urlImage = null;
        this.participe = false;
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
        String genres = "";

        for (String taxonomie : this.taxonomies) {
            genres = genres + taxonomie + ',';
        }

        return genres.substring(0, genres.length() - 1);
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

    public boolean getParticipe() {
        return this.participe;
    }

    public void setParticipe(boolean participe) {
        this.participe = participe;
    }
}
