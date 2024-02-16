package com.example.gasstation.models;

import androidx.annotation.Nullable;

import com.google.gson.Gson;

import java.util.List;

public class User {
    private String userName;
    private List<GasStationModel> favoriteStations;
    private List<String> emergencyContacts;

    public User(String userName, List<GasStationModel> favoriteStations, List<String> emergencyContacts) {
        this.userName = userName;
        this.favoriteStations = favoriteStations;
        this.emergencyContacts = emergencyContacts;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<GasStationModel> getFavoriteStations() {
        return favoriteStations;
    }

    public void setFavoriteStations(List<GasStationModel> favoriteStations) {
        this.favoriteStations = favoriteStations;
    }

    public List<String> getEmergencyContacts() {
        return emergencyContacts;
    }

    public void setEmergencyContacts(List<String> emergencyContacts) {
        this.emergencyContacts = emergencyContacts;
    }

    public User copy(@Nullable String userName, @Nullable List<GasStationModel> favoriteStations, @Nullable List<String> emergencyContacts) {
        return new User(userName == null ? this.userName : userName, favoriteStations == null ? this.favoriteStations : favoriteStations, emergencyContacts == null ? this.emergencyContacts : emergencyContacts);
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static User fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, User.class);
    }
}
