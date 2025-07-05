package com.example.project_tracker.service.Impl;

import com.example.project_tracker.model.Project;
import com.example.project_tracker.repository.ProjectRepository;
import com.example.project_tracker.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class ProjectServiceImp implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Project getProjectById(Long id) {
        return projectRepository.findById(id).orElse(null);
    }

    @Override
    public List<Project> getProjectsByUserId(Long userID) {
        return projectRepository.findByUserID(userID);
    }

    @Override
    public Project updateProject(Long id, Project updatedProject) {
        Project project = projectRepository.findById(id).orElse(null);
        if (project != null) {
            project.setUserID(updatedProject.getUserID());
            project.setGroupName(updatedProject.getGroupName());
            project.setProjectName(updatedProject.getProjectName());
            project.setDescription(updatedProject.getDescription());
            project.setDate(updatedProject.getDate());
            return projectRepository.save(project);
        }
        return null;
    }

    @Override
    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }
}
