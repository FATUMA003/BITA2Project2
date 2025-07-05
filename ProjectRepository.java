package com.example.project_tracker.repository;

import com.example.project_tracker.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByUserID(Long userID);  // projects za user fulani
}
