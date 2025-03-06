package com.app.tracker_api.repository;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class GitHubDataRepository {
    private static final String GITHUB_API_URL = "https://api.github.com/";
    private final RestTemplate restTemplate = new RestTemplate();

    public ResponseEntity<String> getReposDataByUsername(String username) {
        return restTemplate.getForEntity(GITHUB_API_URL + "users/" + username + "/repos", String.class);
    }

    public ResponseEntity<String> getBranchesDataForRepo(String ownerLogin, String repoName) {
        return restTemplate.getForEntity(GITHUB_API_URL + "repos/" + ownerLogin + "/" + repoName + "/branches", String.class);
    }
}
