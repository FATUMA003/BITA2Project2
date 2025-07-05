package com.example.project_tracker.service;

import com.example.project_tracker.model.Feedback;

import java.util.List;

public interface FeedbackService {
    Feedback createFeedback(Feedback feedback);
    List<Feedback> getAllFeedback();
    Feedback getFeedbackById(Long id);
    List<Feedback> getFeedbackByUserId(Long userID);
    List<Feedback> getFeedbackByProjectId(Long projectID);
    Feedback updateFeedback(Long id, Feedback feedback);
    void deleteFeedback(Long id);
}
