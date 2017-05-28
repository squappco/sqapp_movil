package com.squapp;

/**
 * Created by JdRod on 28/05/2017.
 */

public class Venue {
    private String id;
    private String name;
    private String address;
    private String lat;
    private String lng;
    private String serviceTime;
    private String startTime;
    private String finishTime;
    private String price;

    public Venue(String id, String name, String address, String lat, String lng,  String serviceTime, String startTime, String finishTime, String price) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.lat = lat;
        this.lng = lng;
        this.serviceTime = serviceTime;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.price = price;
    }

    public String getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(String serviceTime) {
        this.serviceTime = serviceTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getTime() {
        return startTime +"-" + finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
