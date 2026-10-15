package com.travelcompanion.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user_profiles")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    private String profilePhotoUrl;
    private String location;
    private String bio;

    @ElementCollection
    @CollectionTable(name = "user_profile_interests", joinColumns = @JoinColumn(name = "profile_id"))
    @Column(name = "interest")
    private Set<String> travelInterests = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "user_profile_destinations", joinColumns = @JoinColumn(name = "profile_id"))
    @Column(name = "destination")
    private Set<String> favoriteDestinations = new HashSet<>();

    private String preferredTravelStyle;
    private String budgetRange;
    private String accommodationPreference;
    private String transportationPreference;
    private String languages;
    private int numberOfTrips;
    private double averageRating;
    private boolean verified;
    private String verificationStatus;

    public UserProfile() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

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

    public int getNumberOfTrips() {
        return numberOfTrips;
    }

    public void setNumberOfTrips(int numberOfTrips) {
        this.numberOfTrips = numberOfTrips;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public String getVerificationStatus() {
        return verificationStatus;
    }

    public void setVerificationStatus(String verificationStatus) {
        this.verificationStatus = verificationStatus;
    }
}
