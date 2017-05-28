package com.squapp;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by JdRod on 28/05/2017.
 */

public class VenuesListFragment extends ListFragment {
    ArrayList<Venue> venues;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        venues  = new ArrayList<Venue>();
        super.onCreate(savedInstanceState);
        createPlaceholderVenues();
        ArrayAdapter<Venue> adapter = new VenuesAdapter(getActivity(), venues);
        setListAdapter(adapter);
    }
    void createPlaceholderVenues(){
        venues.add(new Venue("1","1","1","1","1","1","1","1","1"));
        venues.add(new Venue("2","2","2","1","1","1","1","1","1"));
        venues.add(new Venue("3","3","3","1","1","1","1","1","1"));
        venues.add(new Venue("4","4","4","1","1","1","1","1","1"));
    }
}
