package com.squapp;


import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.ListFragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import com.squapp.model.fields.Data;
import com.squapp.model.fields.Fields;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class MapsFragment extends android.app.Fragment {

    MapView mMapView;
    private GoogleMap mMap;

    public MapsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        new HttpRequestTask().execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_maps, container, false);

        mMapView = (MapView) rootView.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately
        new HttpRequestTask().execute();

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap myMap) {
                mMap = myMap;
                LatLng sydney = new LatLng(4.673477, -74.048705);
                CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(15).build();
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));



            }
        });

        return rootView;
    }

    private class HttpRequestTask extends AsyncTask<Void, Void, Fields> {
        @Override
        protected Fields doInBackground(Void... params) {
            try {
                final String url = "http://10.105.168.133/hack/public/fields?access_token=a";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                Fields greeting = restTemplate.getForObject(url, Fields.class);
                return greeting;
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Fields greeting) {
            for (Data data : greeting.getData()) {
                mMap.addMarker(new MarkerOptions()
                        .title(data.getName())
                        .position(new LatLng(
                                Double.valueOf(data.getLat()),
                                Double.valueOf(data.getLng())
                        ))
                );

                mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                    @Override
                    public void onInfoWindowClick(Marker marker) {
//                        Intent intent = new Intent(MapActivity.this,OtherActivity.class);
//                        startActivity(intent);
                        String lat = String.valueOf(marker.getPosition().latitude);
                        String lng = String.valueOf(marker.getPosition().longitude);

                        final String url = "http://10.105.168.133/hack/public/fields?access_token=a&lat=" + lat + "&lng=" + lng;
                        RestTemplate restTemplate = new RestTemplate();
                        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                        Data greeting = restTemplate.getForObject(url, Data.class);

                        ChooseActionPlaceDialog dialog = new ChooseActionPlaceDialog();
                        dialog.setTargetFragment(MapsFragment.this,0);
                        dialog.show(getFragmentManager(),"Create");


                    }
                });
            }

        }

    }

}
