package com.example.eventssearch;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class EventList extends ArrayList<Event> implements Parcelable {

    public EventList() {

    }

    public EventList(Parcel in) {
        this.getFromParcel(in);
    }

    @SuppressWarnings("rawtypes")
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public EventList createFromParcel(Parcel in) {
            return new EventList(in);
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

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        //Taille de la liste
        int size = this.size();
        dest.writeInt(size);
        for (int i = 0; i < size; i++) {
            Event event = this.get(i); //On vient lire chaque objet event
            dest.writeInt(event.getId());
            dest.writeString(event.getTitle());
            dest.writeString(event.getLocation());
            dest.writeString(event.getDate());
            dest.writeString(event.getDescription());
            dest.writeDouble(event.getScore());
            dest.writeString(event.getUrlTickets());
            dest.writeString(event.getUrlImage());
            dest.writeString(event.getGenres());
        }
    }

    public void getFromParcel(Parcel in) {
        // On vide la liste avant tout remplissage
        this.clear();

        //Récupération du nombre d'objet
        int size = in.readInt();

        //On repeuple la liste avec de nouveau objet
        for (int i = 0; i < size; i++) {
            Event event = new Event();
            event.setId(in.readInt());
            event.setTitle(in.readString());
            event.setLocation(in.readString());
            event.setDate(in.readString());
            event.setDescription(in.readString());
            event.setScore(in.readDouble());
            event.setUrlTickets(in.readString());
            event.setUrlImage(in.readString());
            event.setGenres(in.readString());
            this.add(event);
        }

    }
}