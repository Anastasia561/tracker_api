package com.app.tracker_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class GitHubRepository {
    private final String name;
    private final String ownerLogin;
    private final List<GitHubBranch> branches;
}
