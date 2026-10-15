package com.travelcompanion.service;

import com.travelcompanion.dto.ReviewRequest;
import com.travelcompanion.entity.Review;
import com.travelcompanion.entity.TravelPlan;
import com.travelcompanion.entity.User;
import com.travelcompanion.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserService userService;
    private final TravelPlanService travelPlanService;

    public ReviewService(ReviewRepository reviewRepository, UserService userService, TravelPlanService travelPlanService) {
        this.reviewRepository = reviewRepository;
        this.userService = userService;
        this.travelPlanService = travelPlanService;
    }

    public Review createReview(ReviewRequest request) {
        User reviewer = userService.getCurrentUser();
        User reviewee = userService.getUserById(request.getRevieweeId());
        TravelPlan trip = travelPlanService.getPlanById(request.getTripId());

        if (reviewRepository.existsByReviewerAndRevieweeAndTripId(reviewer, reviewee, trip.getId())) {
            throw new IllegalArgumentException("You already reviewed this traveler for this trip");
        }

        Review review = new Review();
        review.setReviewer(reviewer);
        review.setReviewee(reviewee);
        review.setTrip(trip);
        review.setRating(request.getRating());
        review.setComment(request.getComment());
        return reviewRepository.save(review);
    }

    public List<Review> getReviewsForUser(Long userId) {
        User user = userService.getUserById(userId);
        return reviewRepository.findByReviewee(user);
    }
}
