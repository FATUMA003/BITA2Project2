package com.example.project_tracker.service.Impl;

import com.example.project_tracker.model.Feedback;
import com.example.project_tracker.repository.FeedbackRepository;
import com.example.project_tracker.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Override
    public Feedback createFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    @Override
    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAll();
    }

    @Override
    public Feedback getFeedbackById(Long id) {
        return feedbackRepository.findById(id).orElse(null);
    }

    @Override
    public List<Feedback> getFeedbackByUserId(Long userID) {
        return feedbackRepository.findByUserID(userID);
    }

    @Override
    public List<Feedback> getFeedbackByProjectId(Long projectID) {
        return feedbackRepository.findByProjectID(projectID);
    }

    @Override
    public Feedback updateFeedback(Long id, Feedback updatedFeedback) {
        Feedback feedback = feedbackRepository.findById(id).orElse(null);
        if (feedback != null) {
            feedback.setProjectID(updatedFeedback.getProjectID());
            feedback.setUserID(updatedFeedback.getUserID());
            feedback.setMessage(updatedFeedback.getMessage());
            return feedbackRepository.save(feedback);
        }
        return null;
    }

    @Override
    public void deleteFeedback(Long id) {
        feedbackRepository.deleteById(id);
    }
}
