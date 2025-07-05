package com.example.project_tracker.service.Impl;

import com.example.project_tracker.model.Upload;
import com.example.project_tracker.repository.UploadRepository;
import com.example.project_tracker.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UploadServiceImpl implements UploadService {

    @Autowired
    private UploadRepository uploadRepository;

    @Override
    public Upload createUpload(Upload upload) {
        return uploadRepository.save(upload);
    }

    @Override
    public List<Upload> getAllUploads() {
        return uploadRepository.findAll();
    }

    @Override
    public List<Upload> getUploadsByProjectId(Long projectID) {
        return uploadRepository.findByProjectID(projectID);
    }

    @Override
    public Upload updateUpload(Long id, Upload updatedUpload) {
        Upload upload = uploadRepository.findById(id).orElse(null);
        if (upload != null) {
            upload.setProjectID(updatedUpload.getProjectID());
            upload.setGithubLink(updatedUpload.getGithubLink());
            return uploadRepository.save(upload);
        }
        return null;
    }

    @Override
    public void deleteUpload(Long id) {
        uploadRepository.deleteById(id);
    }
}
