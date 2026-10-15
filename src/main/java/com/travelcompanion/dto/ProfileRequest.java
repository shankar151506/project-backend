package com.travelcompanion.dto;

import java.util.Set;

public class ProfileRequest {
    private String profilePhotoUrl;
    private String location;
    private String bio;
    private Set<String> travelInterests;
    private Set<String> favoriteDestinations;
    private String preferredTravelStyle;
    private String budgetRange;
    private String accommodationPreference;
    private String transportationPreference;
    private String languages;

    public String getProfilePhotoUrl() {
        return profilePhotoUrl;
    }

    public void setProfilePhotoUrl(String profilePhotoUrl) {
        this.profilePhotoUrl = profilePhotoUrl;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Set<String> getTravelInterests() {
        return travelInterests;
    }

    public void setTravelInterests(Set<String> travelInterests) {
        this.travelInterests = travelInterests;
    }

    public Set<String> getFavoriteDestinations() {
        return favoriteDestinations;
    }

    public void setFavoriteDestinations(Set<String> favoriteDestinations) {
        this.favoriteDestinations = favoriteDestinations;
    }

    public String getPreferredTravelStyle() {
        return preferredTravelStyle;
    }

    public void setPreferredTravelStyle(String preferredTravelStyle) {
        this.preferredTravelStyle = preferredTravelStyle;
    }

    public String getBudgetRange() {
        return budgetRange;
    }

    public void setBudgetRange(String budgetRange) {
        this.budgetRange = budgetRange;
    }

    public String getAccommodationPreference() {
        return accommodationPreference;
    }

    public void setAccommodationPreference(String accommodationPreference) {
        this.accommodationPreference = accommodationPreference;
    }

    public String getTransportationPreference() {
        return transportationPreference;
    }

    public void setTransportationPreference(String transportationPreference) {
        this.transportationPreference = transportationPreference;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }
}
