package com.example.project_tracker.model;

import jakarta.persistence.*;

@Entity


public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feedbackID;

    private Long projectID;

    private Long userID;

    private String message;

    @ManyToOne
    @JoinColumn(name = "project_projectid")
    private Project project;
    @ManyToOne
    @JoinColumn(name = "user_id") // jina la column katika database yako
    private User user;


    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Feedback() {}

    public Long getFeedbackID() {
        return feedbackID;
    }

    public void setFeedbackID(Long feedbackID) {
        this.feedbackID = feedbackID;
    }

    public Long getProjectID() {
        return projectID;
    }

    public void setProjectID(Long projectID) {
        this.projectID = projectID;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Feedback(Long projectID, Long userID, String message) {
        this.projectID = projectID;
        this.userID = userID;
        this.message = message;
    }
}
