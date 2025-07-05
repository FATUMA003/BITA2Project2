package com.example.project_tracker.repository;

import com.example.project_tracker.model.Upload;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UploadRepository extends JpaRepository<Upload, Long> {
    List<Upload> findByProjectID(Long projectID);
}
