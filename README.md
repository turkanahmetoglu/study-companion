# Study Companion App
 
A mood-based study companion for Istanbul. You check in with how you feel, the app suggests a study place from a curated list that fits your mood, and it tracks a streak of completed check-ins over time.
 
## Tech Stack
 
- Java, Spring Boot, Spring Data JPA
- PostgreSQL
- Docker and Docker Compose
- JUnit 5, Mockito, MockMvc
- GitHub Actions (CI)
- Swagger UI (springdoc-openapi) for API documentation
## Features
 
- **Mood-based suggestions:** each mood maps to a place tag (`tired` to `comfy`, `sad` to `daylight`, `happy` to `lively`, `motivated` to `focus`). The `motivated` mood only suggests places you have not completed yet.
- **Streak tracking:** `currentStreak` counts your latest run of completed check-ins, and `warmth` is a forgiving score that drops by 1 on a missed check-in instead of resetting to zero.
- **Validation and clear errors:** empty names or moods return a 400 with a readable message, an unknown mood returns a 400 listing the valid moods, and a mood with no matching place returns a 404.
- **Seed data:** a curated list of Istanbul study places loads automatically on first startup.
- **Interactive API docs** via Swagger UI.
## Running Locally
 
1. Clone the repository.
2. Start the database only:
```
   docker compose up -d db
```
3. Run the app:
```
   mvnw spring-boot:run        # Windows
   ./mvnw spring-boot:run      # macOS / Linux
```
4. The API runs at `http://localhost:8080`.
To run the app and the database together in containers instead, use `docker compose up --build` (do not run this at the same time as step 3, since both use port 8080).
 
## API Documentation
 
Interactive docs are available at `http://localhost:8080/swagger-ui.html` while the app is running.
 
![Swagger UI](docs/swagger.png)
 
## API Endpoints
 
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/places` | List all places |
| POST | `/places` | Add a new place (name and area required) |
| GET | `/checkins` | List all check-ins |
| POST | `/checkins` | Submit a check-in (mood, date, and place required) |
| GET | `/suggestions?mood=X` | Get a place suggestion for a mood |
| GET | `/suggestions/streak` | Get current streak and warmth |
 
## Tests
 
The project has 12 automated tests: unit tests for the suggestion and streak logic, Mockito tests with mocked repositories, and MockMvc tests for the controllers.
 
```
mvnw test
```
 
Tests that start the full application need the database running (`docker compose up -d db`).
 
## CI
 
Every push runs the test suite on GitHub Actions against a real PostgreSQL instance.
