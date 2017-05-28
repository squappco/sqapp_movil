package com.squapp;

import android.app.ListFragment;
import android.os.Bundle;

import android.os.StrictMode;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.squapp.model.games.Data;
import com.squapp.model.games.Game;
import com.squapp.model.games.Games;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

/**
 * Created by JdRod on 28/05/2017.
 */

public class
GamesOfPlaceFragment extends ListFragment {
    ArrayList<Data> games;
    public String id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        id = ((MapsActivity)getActivity()).getId();
        Log.d("Test",id);
        games = new ArrayList<Data>();
        createPlaceholderGames();
        ArrayAdapter<Data> adapter = new GameAdapter(getActivity(), games);
        setListAdapter(adapter);
    }

    void createPlaceholderGames() {
        final String url = "http://10.105.168.133/hack/public/fields/"+id+"/games";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        Games greeting = restTemplate.getForObject(url, Games.class);

        for (Data data : greeting.getData()) {
            games.add(data);
        }
//        games.add(new Game("1", "1", "1", "1", "1", "1"));
//        games.add(new Game("2", "2", "2", "1", "1", "1"));
//        games.add(new Game("3", "3", "3", "1", "1", "1"));
//        games.add(new Game("4", "4", "4", "1", "1", "1"));
    }
}