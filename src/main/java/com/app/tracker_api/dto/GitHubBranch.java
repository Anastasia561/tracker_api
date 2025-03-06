package com.app.tracker_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GitHubBranch {
    private final String name;
    private final String lastCommitSha;
}
