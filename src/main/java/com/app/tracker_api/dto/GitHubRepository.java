package com.app.tracker_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class GitHubRepository {
    private final String name;
    @JsonProperty("owner_login")
    private final String ownerLogin;
    private final List<GitHubBranch> branches;
}
