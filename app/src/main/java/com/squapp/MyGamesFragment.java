package com.squapp;

import android.app.ListFragment;
import android.os.Bundle;
<<<<<<< HEAD
import android.support.annotation.Nullable;
=======
import android.support.v4.app.ListFragment;
>>>>>>> 086a101e21c055342ed62d0a51e3ba00f7e38a12
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
