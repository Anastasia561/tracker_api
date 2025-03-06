package com.app.tracker_api.controller;

import com.app.tracker_api.service.GitHubService;
import com.app.tracker_api.dto.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/github")
public class GitHubController {
    @Autowired
    private GitHubService service;

    @GetMapping("/repos/{username}")
    public ResponseEntity<?> getNonForkedRepositoriesByUsername(@PathVariable String username) {
        try {
            return ResponseEntity.ok(service.getGitHubRepositoriesByUsername(username));
        } catch (Exception e) {
            return ResponseEntity.status(404).body(new ErrorResponse(404, e.getMessage()));
        }
    }
}
