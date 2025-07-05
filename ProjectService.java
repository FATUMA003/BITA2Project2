package com.example.project_tracker.service;

import com.example.project_tracker.model.Project;

import java.util.List;

public interface ProjectService {
    Project createProject(Project project);
    List<Project> getAllProjects();
    Project getProjectById(Long id);
    List<Project> getProjectsByUserId(Long userID);
    Project updateProject(Long id, Project project);
    void deleteProject(Long id);
}
