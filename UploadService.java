package com.example.project_tracker.service;

import com.example.project_tracker.model.Upload;

import java.util.List;

public interface UploadService {
    Upload createUpload(Upload upload);
    List<Upload> getAllUploads();
    List<Upload> getUploadsByProjectId(Long projectID);
    Upload updateUpload(Long id, Upload upload);
    void deleteUpload(Long id);
}
