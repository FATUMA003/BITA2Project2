package com.example.project_tracker.repository;

import com.example.project_tracker.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findByUserID(Long userID);
    List<Feedback> findByProjectID(Long projectID);
}
