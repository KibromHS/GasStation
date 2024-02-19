package com.example.gasstation.models;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;
import java.util.Map;

public class GasStationModel {
    private String stationId;
    private LatLng location;
    private String imageUrl;
    private boolean isOpen;
    private String queueLength;
    private List<Map<String, Object>> ratesReviews; // [{"userID": "", "rating": 0, "review": ""}]
    private List<Map<String, Object>> fuels; // [{"fuelType": "petroleum", "price": 70}]

    public GasStationModel(String stationId, LatLng location, String imageUrl, boolean isOpen, String queueLength, List<Map<String, Object>> ratesReviews, List<Map<String, Object>> fuels) {
        this.stationId = stationId;
        this.location = location;
        this.imageUrl = imageUrl;
        this.isOpen = isOpen;
        this.queueLength = queueLength;
        this.ratesReviews = ratesReviews;
        this.fuels = fuels;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public LatLng getLocation() {
        return location;
    }

    public void setLocation(LatLng location) {
        this.location = location;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public String getQueueLength() {
        return queueLength;
    }

    public void setQueueLength(String queueLength) {
        this.queueLength = queueLength;
    }

    public List<Map<String, Object>> getRatesReviews() {
        return ratesReviews;
    }

    public void setRatesReviews(List<Map<String, Object>> ratesReviews) {
        this.ratesReviews = ratesReviews;
    }

    public List<Map<String, Object>> getFuels() {
        return fuels;
    }

    public void setFuels(List<Map<String, Object>> fuels) {
        this.fuels = fuels;
    }
}
