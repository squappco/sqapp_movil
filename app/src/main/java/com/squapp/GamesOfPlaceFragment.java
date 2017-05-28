package com.squapp;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;

import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

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
    int index=0;
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
    }
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        index = position+1;
       Toast toast = Toast.makeText(getActivity().getBaseContext(),"Added to game # "+ index,Toast.LENGTH_SHORT);
        toast.show();

    }

}