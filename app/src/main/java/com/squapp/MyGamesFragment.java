package com.squapp;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.widget.ArrayAdapter;

import com.squapp.model.games.Game;

import java.util.ArrayList;

/**
 * Created by JdRod on 27/05/2017.
 */

public class MyGamesFragment extends ListFragment {
    ArrayList<Game> myGames;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myGames = new ArrayList<Game>();
        createPlaceholderGames();
        ArrayAdapter<Game> adapter = new GameAdapter(getActivity(), myGames);
        setListAdapter(adapter);
    }
    void createPlaceholderGames(){
        myGames.add(new Game("1","1","1","1","1","1"));
        myGames.add(new Game("2","2","2","1","1","1"));
        myGames.add(new Game("3","3","3","1","1","1"));
        myGames.add(new Game("4","4","4","1","1","1"));
    }
}
