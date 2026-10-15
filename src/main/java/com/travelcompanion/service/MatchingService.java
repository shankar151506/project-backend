package com.travelcompanion.service;

import com.travelcompanion.entity.TravelPlan;
import com.travelcompanion.entity.User;
import com.travelcompanion.repository.TravelPlanRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class MatchingService {

    private final TravelPlanRepository travelPlanRepository;

    public MatchingService(TravelPlanRepository travelPlanRepository) {
        this.travelPlanRepository = travelPlanRepository;
    }

    public List<TravelPlan> findMatchesForUser(User user) {
        List<TravelPlan> allPlans = travelPlanRepository.findAll();
        List<TravelPlan> matches = new ArrayList<>();
        for (TravelPlan plan : allPlans) {
            if (!plan.getUser().getId().equals(user.getId()) && calculateCompatibility(user, plan) >= 50) {
                matches.add(plan);
            }
        }
        matches.sort((a, b) -> Double.compare(calculateCompatibility(user, b), calculateCompatibility(user, a)));
        return matches;
    }

    public double calculateCompatibility(User user, TravelPlan targetPlan) {
        double score = 0;
        List<TravelPlan> userPlans = travelPlanRepository.findByUser(user);
        if (userPlans.isEmpty()) {
            return 0;
        }

        TravelPlan sourcePlan = userPlans.get(0);
        score += destinationScore(sourcePlan.getDestination(), targetPlan.getDestination()) * 0.30;
        score += dateScore(sourcePlan.getDepartureDate(), targetPlan.getDepartureDate()) * 0.20;
        score += interestScore(sourcePlan.getInterests(), targetPlan.getInterests()) * 0.20;
        score += budgetScore(sourcePlan.getBudget(), targetPlan.getBudget()) * 0.10;
        score += styleScore(sourcePlan.getTravelStyle(), targetPlan.getTravelStyle()) * 0.10;
        score += activityScore(sourcePlan.getActivities(), targetPlan.getActivities()) * 0.05;
        score += accommodationScore(sourcePlan.getAccommodationPreference(), targetPlan.getAccommodationPreference()) * 0.05;
        return Math.min(100, Math.max(0, score));
    }

    private double destinationScore(String source, String target) {
        if (source == null || target == null) return 0;
        return source.equalsIgnoreCase(target) ? 100 : 50;
    }

    private double dateScore(java.time.LocalDate source, java.time.LocalDate target) {
        if (source == null || target == null) return 0;
        long diff = Math.abs(java.time.temporal.ChronoUnit.DAYS.between(source, target));
        return Math.max(0, 100 - diff * 2);
    }

    private double interestScore(Set<String> source, Set<String> target) {
        if (source == null || target == null || source.isEmpty() || target.isEmpty()) return 0;
        Set<String> common = new java.util.HashSet<>(source);
        common.retainAll(target);
        return (common.size() * 100.0) / Math.max(source.size(), target.size());
    }

    private double budgetScore(double sourceBudget, double targetBudget) {
        double diff = Math.abs(sourceBudget - targetBudget);
        double avg = (sourceBudget + targetBudget) / 2;
        if (avg == 0) return 0;
        return Math.max(0, 100 - (diff / avg) * 100);
    }

    private double styleScore(String source, String target) {
        return source != null && source.equalsIgnoreCase(target) ? 100 : 40;
    }

    private double activityScore(Set<String> source, Set<String> target) {
        if (source == null || target == null || source.isEmpty() || target.isEmpty()) return 0;
        Set<String> common = new java.util.HashSet<>(source);
        common.retainAll(target);
        return (common.size() * 100.0) / Math.max(source.size(), target.size());
    }

    private double accommodationScore(String source, String target) {
        if (source == null || target == null) return 0;
        return source.equalsIgnoreCase(target) ? 100 : 50;
    }
}
