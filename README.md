-Tracker-API
-
-Description
-
1)fetches all non-fork repositories of a GiHub user

2)displayes repository name, owner_login and branches

3)for every branch provides its name and last commit sha

4)returns 404 error if the GitHub user does not exist

-Endpoints
-
GET /github/repos/{username}

-Response Example (200 OK)
-
```java
[
    {
        "name": "my-repo",
        "owner_login": "Anastasia561",
        "branches": [
            {
                "name": "main",
                "last_commit_sha": "abcd1234efgh5678"
            }
        ]
    }
]
```
-Error Response Example (404 NOT FOUND)
-
```java
{
    "status": 404,
    "message": "User not found"
}
```
