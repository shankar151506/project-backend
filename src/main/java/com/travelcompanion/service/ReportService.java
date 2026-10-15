package com.travelcompanion.service;

import com.travelcompanion.dto.ReportRequest;
import com.travelcompanion.entity.Report;
import com.travelcompanion.entity.User;
import com.travelcompanion.repository.ReportRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

    private final ReportRepository reportRepository;
    private final UserService userService;

    public ReportService(ReportRepository reportRepository, UserService userService) {
        this.reportRepository = reportRepository;
        this.userService = userService;
    }

    public Report createReport(ReportRequest request) {
        User reporter = userService.getCurrentUser();
        Report report = new Report();
        report.setReporter(reporter);
        report.setTargetType(request.getTargetType());
        report.setTargetId(request.getTargetId());
        report.setReason(request.getReason());
        return reportRepository.save(report);
    }

    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }
}
