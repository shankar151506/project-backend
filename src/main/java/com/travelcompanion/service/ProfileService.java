package com.travelcompanion.service;

import com.travelcompanion.dto.ProfileRequest;
import com.travelcompanion.entity.User;
import com.travelcompanion.entity.UserProfile;
import com.travelcompanion.repository.UserProfileRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    private final UserProfileRepository userProfileRepository;
    private final UserService userService;

    public ProfileService(UserProfileRepository userProfileRepository, UserService userService) {
        this.userProfileRepository = userProfileRepository;
        this.userService = userService;
    }

    public UserProfile getCurrentUserProfile() {
        User user = userService.getCurrentUser();
        return userProfileRepository.findByUser(user)
                .orElseGet(() -> {
                    UserProfile profile = new UserProfile();
                    profile.setUser(user);
                    return userProfileRepository.save(profile);
                });
    }

    public UserProfile updateProfile(ProfileRequest request) {
        UserProfile profile = getCurrentUserProfile();
        if (request.getProfilePhotoUrl() != null) profile.setProfilePhotoUrl(request.getProfilePhotoUrl());
        if (request.getLocation() != null) profile.setLocation(request.getLocation());
        if (request.getBio() != null) profile.setBio(request.getBio());
        if (request.getTravelInterests() != null) profile.setTravelInterests(request.getTravelInterests());
        if (request.getFavoriteDestinations() != null) profile.setFavoriteDestinations(request.getFavoriteDestinations());
        if (request.getPreferredTravelStyle() != null) profile.setPreferredTravelStyle(request.getPreferredTravelStyle());
        if (request.getBudgetRange() != null) profile.setBudgetRange(request.getBudgetRange());
        if (request.getAccommodationPreference() != null) profile.setAccommodationPreference(request.getAccommodationPreference());
        if (request.getTransportationPreference() != null) profile.setTransportationPreference(request.getTransportationPreference());
        if (request.getLanguages() != null) profile.setLanguages(request.getLanguages());
        return userProfileRepository.save(profile);
    }
}
