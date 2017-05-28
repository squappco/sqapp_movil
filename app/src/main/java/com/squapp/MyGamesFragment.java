package com.squapp;

import android.app.ListFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;


import com.squapp.model.mygames.Data;
import com.squapp.model.mygames.Games;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

/**
 * Created by JdRod on 27/05/2017.
 */

public class MyGamesFragment extends ListFragment {
    ArrayList<Data> myGames;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myGames = new ArrayList<Data>();
        createPlaceholderGames();
        ArrayAdapter<Data> adapter = new MyGameAdapter(getActivity(), myGames);
        setListAdapter(adapter);
    }
    void createPlaceholderGames(){
        final String url = "http://10.105.168.133/hack/public/mygames?access_token=a";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        Games greeting = restTemplate.getForObject(url, Games.class);

        for (Data data : greeting.getData()) {
            myGames.add(data);
        }
    }
}
