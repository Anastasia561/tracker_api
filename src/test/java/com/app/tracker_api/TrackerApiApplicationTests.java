package com.app.tracker_api;

import com.app.tracker_api.dto.GitHubBranch;
import com.app.tracker_api.dto.GitHubRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TrackerApiApplicationTests {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldReturnNonForkRepositoriesWithBranchesAndCommitsTest() {
        String username = "Anastasia561"; //existing gitHub username

        ResponseEntity<GitHubRepository[]> response = restTemplate.getForEntity(
                "/github/repos/{username}",
                GitHubRepository[].class,
                username
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotEmpty();

        for (GitHubRepository repo : response.getBody()) {
            assertThat(repo.getName()).isNotEmpty();
            assertThat(repo.getOwnerLogin()).isEqualTo(username);
            assertThat(repo.getBranches()).isNotEmpty();

            for (GitHubBranch branch : repo.getBranches()) {
                assertThat(branch.getName()).isNotEmpty();
                assertThat(branch.getLastCommitSha()).isNotEmpty();
            }
        }
    }
}
