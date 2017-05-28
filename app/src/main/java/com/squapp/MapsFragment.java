package com.squapp;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
<<<<<<< HEAD
=======
import com.squapp.helpers.GPSTracker;
import com.squapp.model.Data;
import com.squapp.model.Fields;
>>>>>>> 183e380fd52742ebb52b5d0eb471338b47ec8240
import com.squapp.model.FirebaseMarker;
import static com.google.android.gms.location.LocationServices.FusedLocationApi;

<<<<<<< HEAD
public class MapsFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
//    private static String TAG = "MapsFragment";
=======
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import static com.google.android.gms.location.LocationServices.FusedLocationApi;
>>>>>>> 183e380fd52742ebb52b5d0eb471338b47ec8240


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
                mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {

                    @Override public boolean onMarkerClick(Marker marker) {
                        //  Take some action here
                        return true;
                    }

                });

                // For dropping a marker at a point on the Map
                LatLng sydney = new LatLng(4.673477, -74.048705);
                mMap.addMarker(new MarkerOptions().position(sydney).title("Tu Posición").snippet("Marker Description"));

                // For zooming automatically to the location of the marker
                CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(15).build();
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        });

        return rootView;
    }

<<<<<<< HEAD
    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
=======
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
//                        .snippet(Integer.toString(jsonObj.getInt("population")))
                        .position(new LatLng(
                                Double.valueOf(data.getLat()),
                                Double.valueOf(data.getLng())
                        ))
                );
            }

        }

    }

>>>>>>> 183e380fd52742ebb52b5d0eb471338b47ec8240
}
