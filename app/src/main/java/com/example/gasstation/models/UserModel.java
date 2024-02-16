package com.example.gasstation.models;

import androidx.annotation.Nullable;

import com.google.gson.Gson;

import java.util.List;

public class UserModel {
    private String userName;
    private String phoneNumber;
    private List<GasStationModel> favoriteStations;
    private List<String> emergencyContacts;

    public UserModel(String userName, String phoneNumber, List<GasStationModel> favoriteStations, List<String> emergencyContacts) {
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.favoriteStations = favoriteStations;
        this.emergencyContacts = emergencyContacts;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public UserModel copy(@Nullable String userName, @Nullable String phoneNumber, @Nullable List<GasStationModel> favoriteStations, @Nullable List<String> emergencyContacts) {
        return new UserModel(userName == null ? this.userName : userName, phoneNumber == null ? this.phoneNumber : phoneNumber, favoriteStations == null ? this.favoriteStations : favoriteStations, emergencyContacts == null ? this.emergencyContacts : emergencyContacts);
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static UserModel fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, UserModel.class);
    }
}
