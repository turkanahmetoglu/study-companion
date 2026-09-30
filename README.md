# Study Companion App

A mood-based study companion. You check in with how you're feeling, the app suggests a study place from a curated list that fits your mood, and it tracks a streak of completed check-ins over time.

## Tech Stack

- Java, Spring Boot
- Spring Data JPA
- PostgreSQL
- Docker and Docker Compose
- GitHub Actions for CI

## Features

- Add and list study places, each tagged with attributes like `comfy`, `daylight`, `lively`, or `focus`
- Submit a mood check-in, linked to a suggested place
- Get a place suggestion based on mood, with unvisited places prioritized for the "motivated" mood
- Track a streak: `warmth` reflects overall consistency over time, `currentStreak` reflects your current run of completed check-ins

## Running Locally

1. Clone the repository
2. Start the database:
   ```
   docker compose up -d
   ```
3. Run the app:
   ```
   ./mvnw spring-boot:run
   ```
4. The app runs at `http://localhost:8080`

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/places` | List all places |
| POST | `/places` | Add a new place |
| GET | `/checkins` | List all check-ins |
| POST | `/checkins` | Submit a new check-in |
| GET | `/suggestions?mood=X` | Get a place suggestion for a mood |
| GET | `/suggestions/streak` | Get current warmth and streak |

## Running Tests

```
./mvnw test
```

## CI

Every push runs the test suite against a real PostgreSQL instance via GitHub Actions.