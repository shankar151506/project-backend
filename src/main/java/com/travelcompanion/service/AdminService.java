package com.travelcompanion.service;

import com.travelcompanion.entity.Report;
import com.travelcompanion.entity.TravelPlan;
import com.travelcompanion.entity.User;
import com.travelcompanion.repository.ReportRepository;
import com.travelcompanion.repository.TravelPlanRepository;
import com.travelcompanion.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminService {

    private final UserRepository userRepository;
    private final TravelPlanRepository travelPlanRepository;
    private final ReportRepository reportRepository;

    public AdminService(UserRepository userRepository,
                       TravelPlanRepository travelPlanRepository,
                       ReportRepository reportRepository) {
        this.userRepository = userRepository;
        this.travelPlanRepository = travelPlanRepository;
        this.reportRepository = reportRepository;
    }

    public Map<String, Object> getDashboardStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalUsers", userRepository.count());
        stats.put("activeUsers", userRepository.findAll().stream().filter(User::isEnabled).count());
        stats.put("totalTrips", travelPlanRepository.count());
        stats.put("completedTrips", travelPlanRepository.findAll().stream().filter(plan -> plan.getStatus() != null && plan.getStatus().name().equals("COMPLETED")).count());
        stats.put("pendingVerifications", 0L);
        stats.put("reports", reportRepository.count());
        return stats;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public List<TravelPlan> getTrips() {
        return travelPlanRepository.findAll();
    }

    public List<Report> getReports() {
        return reportRepository.findAll();
    }
}
