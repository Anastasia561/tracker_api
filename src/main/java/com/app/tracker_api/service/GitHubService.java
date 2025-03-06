package com.app.tracker_api.service;

import com.app.tracker_api.dto.GitHubBranch;
import com.app.tracker_api.dto.GitHubRepository;
import com.app.tracker_api.repository.GitHubDataRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;

@Service
public class GitHubService {
    @Autowired
    private GitHubDataRepository repository;
    private final ObjectMapper mapper = new ObjectMapper();

    public List<GitHubRepository> getGitHubRepositoriesByUsername(String username) {
        List<GitHubRepository> result = new ArrayList<>();
        try {
            ResponseEntity<String> resp = repository.getReposDataByUsername(username);
            JsonNode rootNode = mapper.readTree(resp.getBody());

            for (JsonNode repo : rootNode) {
                if (!repo.get("fork").asBoolean()) {

                    String ownerLogin = repo.get("owner").get("login").asText();
                    String repoName = repo.get("name").asText();
                    List<GitHubBranch> branches = processGitHubBranches(ownerLogin, repoName);

                    result.add(new GitHubRepository(repoName, ownerLogin, branches));
                }
            }
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("User with username: " + username + " was not found");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private List<GitHubBranch> processGitHubBranches(String ownerLogin, String repoName) {
        List<GitHubBranch> result = new ArrayList<>();
        try {
            ResponseEntity<String> resp = repository.getBranchesDataForRepo(ownerLogin, repoName);
            JsonNode rootNode = mapper.readTree(resp.getBody());

            for (JsonNode repo : rootNode) {
                result.add(new GitHubBranch(repo.get("name").asText(),
                        repo.get("commit").get("sha").asText()));
            }

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
