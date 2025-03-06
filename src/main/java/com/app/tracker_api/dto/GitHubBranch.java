package com.app.tracker_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GitHubBranch {
    private final String name;
    @JsonProperty("last_commit_sha")
    private final String lastCommitSha;
}
