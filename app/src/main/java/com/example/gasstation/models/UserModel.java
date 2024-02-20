package com.example.gasstation.models;

import androidx.annotation.Nullable;

import com.google.gson.Gson;

import java.util.List;

public class UserModel {
    private final String userId;
    private String userName;
    private String phoneNumber;
    private String profileImageUrl;
    private List<String> favoriteStations; // ["stationId"]
    private List<String> emergencyContacts;

    public UserModel(String userId, String userName, String phoneNumber, String profileImageUrl, List<String> favoriteStations, List<String> emergencyContacts) {
        this.userId = userId;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.profileImageUrl = profileImageUrl;
        this.favoriteStations = favoriteStations;
        this.emergencyContacts = emergencyContacts;
    }

    public String getUserId() {
        return userId;
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

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public List<String> getFavoriteStations() {
        return favoriteStations;
    }

    public void setFavoriteStations(List<String> favoriteStations) {
        this.favoriteStations = favoriteStations;
    }

    public List<String> getEmergencyContacts() {
        return emergencyContacts;
    }

    public void setEmergencyContacts(List<String> emergencyContacts) {
        this.emergencyContacts = emergencyContacts;
    }

    public UserModel copy(@Nullable String userName, @Nullable String phoneNumber, @Nullable String profileImageUrl, @Nullable List<String> favoriteStations, @Nullable List<String> emergencyContacts) {
        UserModel user = new UserModel(this.userId, this.userName, this.phoneNumber, this.profileImageUrl, this.favoriteStations, this.emergencyContacts);
        if (userName != null) user.setUserName(userName);
        if (phoneNumber != null) user.setPhoneNumber(phoneNumber);
        if (profileImageUrl != null) user.setProfileImageUrl(profileImageUrl);
        if (favoriteStations != null) user.setFavoriteStations(favoriteStations);
        if (emergencyContacts != null) user.setEmergencyContacts(emergencyContacts);

        return user;
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
