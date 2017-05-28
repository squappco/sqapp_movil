package com.squapp.model;

/**
 * Created by Sergio on 27/05/2017.
 */

public class FirebaseMarker {

    String name;
    double lat;
    double lng;

    public String getName() {
        return name;
    }

    public void setName(String nombre) {
        this.name = nombre;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
