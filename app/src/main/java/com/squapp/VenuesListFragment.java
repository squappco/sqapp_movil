package com.squapp;

import android.app.ListFragment;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.squapp.model.fields.Data;
import com.squapp.model.fields.Fields;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

/**
 * Created by JdRod on 28/05/2017.
 */

public class VenuesListFragment extends ListFragment {
    ArrayList<Data> venues;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        venues  = new ArrayList<Data>();
        super.onCreate(savedInstanceState);
        createPlaceholderVenues();
        ArrayAdapter<Data> adapter = new VenuesAdapter(getActivity(), venues);
        setListAdapter(adapter);
    }
    void createPlaceholderVenues(){
        final String url = "http://10.105.168.133/hack/public/fields?access_token=a";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        Fields greeting = restTemplate.getForObject(url, Fields.class);

        for (Data data : greeting.getData()) {
            venues.add(data);
        }
    }
}
