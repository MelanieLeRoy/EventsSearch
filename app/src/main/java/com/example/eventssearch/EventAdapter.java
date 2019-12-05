package com.example.eventssearch;

import android.content.Context;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class EventAdapter extends ArrayAdapter<Event> {


    public EventAdapter(Context context, List<Event> myList) {
        super(context, 0, myList);
    }

    @Override
    public View getView(int position , View convertView , ViewGroup parent) {

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_rowevent, parent, false);
        }

        TextView idTextview = (TextView) convertView.findViewById(R.id.idnumber);
        TextView titleTextview = (TextView) convertView.findViewById(R.id.titlerow);
        TextView locationTextview = (TextView) convertView.findViewById(R.id.locationrow);
        TextView dateTextView = (TextView) convertView.findViewById(R.id.dateevent);

        idTextview.setText(getItem(position).getId() + "");
        titleTextview.setText(getItem(position).getTitle());
        locationTextview.setText(getItem(position).getLocation());
        dateTextView.setText(getItem(position).getDate());


        return convertView;
    }
}
