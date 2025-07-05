package com.example.project_tracker.controller;

import com.example.project_tracker.model.Upload;
import com.example.project_tracker.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/uploads")
@CrossOrigin(origins = "*")
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @PostMapping
    public Upload createUpload(@RequestBody Upload upload) {
        return uploadService.createUpload(upload);
    }

    @GetMapping
    public List<Upload> getAllUploads() {
        return uploadService.getAllUploads();
    }

    @GetMapping("/project/{projectID}")
    public List<Upload> getUploadsByProjectId(@PathVariable Long projectID) {
        return uploadService.getUploadsByProjectId(projectID);
    }

    @PutMapping("/{id}")
    public Upload updateUpload(@PathVariable Long id, @RequestBody Upload upload) {
        return uploadService.updateUpload(id, upload);
    }

    @DeleteMapping("/{id}")
    public String deleteUpload(@PathVariable Long id) {
        uploadService.deleteUpload(id);
        return "Upload deleted";
    }
}
