package com.squapp;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.widget.ArrayAdapter;

import com.squapp.Game;
import com.squapp.GameAdapter;

import java.util.ArrayList;

/**
 * Created by JdRod on 28/05/2017.
 */

public class GamesOfPlaceFragment extends ListFragment {
    ArrayList<Game> Games;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Games = new ArrayList<Game>();
        createPlaceholderGames();
        ArrayAdapter<Game> adapter = new GameAdapter(getActivity(), Games);
        setListAdapter(adapter);
    }

    void createPlaceholderGames() {
        Games.add(new Game("1", "1", "1", "1", "1", "1"));
        Games.add(new Game("2", "2", "2", "1", "1", "1"));
        Games.add(new Game("3", "3", "3", "1", "1", "1"));
        Games.add(new Game("4", "4", "4", "1", "1", "1"));
    }
}