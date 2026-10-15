package com.travelcompanion.repository;

import com.travelcompanion.entity.Review;
import com.travelcompanion.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByReviewee(User reviewee);
    boolean existsByReviewerAndRevieweeAndTripId(User reviewer, User reviewee, Long tripId);
}
