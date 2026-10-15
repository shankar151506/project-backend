# Travel Companion Backend

Spring Boot 3 REST API for the Travel Companion platform.

## Requirements

- Java 17
- Apache Maven 3.8 or newer

## Run locally with the included H2 profile

The `local` profile stores its development database under `data/`, which is ignored by Git. Set a private JWT signing key before starting the server:

```powershell
$env:JWT_SECRET = 'replace-with-a-private-random-value-at-least-32-characters'
mvn spring-boot:run "-Dspring-boot.run.profiles=local"
```

The API listens on `http://localhost:8080`. The frontend development origins `http://localhost:5173` and `http://127.0.0.1:5173` are allowed by default.

## Database configuration

The default profile connects to MySQL. Configure `DB_USERNAME` and `DB_PASSWORD` in the backend process environment, along with `JWT_SECRET`; never commit credentials. To allow a deployed frontend origin, set `CORS_ALLOWED_ORIGINS` to its comma-separated origin list, for example `https://app.example.com`.

## API

REST endpoints are under `/api`. Authentication uses JWT bearer tokens; public authentication endpoints are under `/api/auth/**`. To build and test:

```powershell
mvn test
mvn package
```
