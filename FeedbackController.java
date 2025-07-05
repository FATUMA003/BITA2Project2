package com.example.project_tracker.controller;

import com.example.project_tracker.model.Feedback;
import com.example.project_tracker.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedback")
@CrossOrigin(origins = "*")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping
    public Feedback createFeedback(@RequestBody Feedback feedback) {
        return feedbackService.createFeedback(feedback);
    }

    @GetMapping
    public List<Feedback> getAllFeedback() {
        return feedbackService.getAllFeedback();
    }

    @GetMapping("/{id}")
    public Feedback getFeedbackById(@PathVariable Long id) {
        return feedbackService.getFeedbackById(id);
    }

    @GetMapping("/user/{userID}")
    public List<Feedback> getFeedbackByUser(@PathVariable Long userID) {
        return feedbackService.getFeedbackByUserId(userID);
    }

    @GetMapping("/project/{projectID}")
    public List<Feedback> getFeedbackByProject(@PathVariable Long projectID) {
        return feedbackService.getFeedbackByProjectId(projectID);
    }

    @PutMapping("/{id}")
    public Feedback updateFeedback(@PathVariable Long id, @RequestBody Feedback feedback) {
        return feedbackService.updateFeedback(id, feedback);
    }

    @DeleteMapping("/{id}")
    public String deleteFeedback(@PathVariable Long id) {
        feedbackService.deleteFeedback(id);
        return "Feedback deleted";
    }
}
