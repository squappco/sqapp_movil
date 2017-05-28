package com.squapp;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.squapp.model.fields.Data;

import java.util.ArrayList;

/**
 * Created by JdRod on 28/05/2017.
 */

public class VenuesAdapter extends ArrayAdapter<Data> {
    Activity activity;

    public VenuesAdapter(Activity activity, ArrayList<Data> venues) {
        super(activity, 0, venues);
        this.activity = activity;

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = activity.getLayoutInflater().inflate(R.layout.venue_item_list, null);

        Data venue = getItem(position);
        ((TextView) convertView.findViewById(R.id.list_venue_name)).setText(venue.getName());
        ((TextView) convertView.findViewById(R.id.list_venue_address)).setText(venue.getAddress());
        ((TextView) convertView.findViewById(R.id.list_venue_hours)).setText(venue.getServiceTime());
        ((TextView) convertView.findViewById(R.id.list_venue_cost)).setText(venue.getPrice());
        ((TextView) convertView.findViewById(R.id.list_venue_service)).setText(venue.getServiceTime());

        return convertView;
    }
}