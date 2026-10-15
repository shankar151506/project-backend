# ✈️ Travel Companion Backend API

Spring Boot 3 REST API for the **Travel Companion Matching Platform**.

[![Deployment Status](https://img.shields.io/badge/Render-Deployed-success?logo=render)](https://travel-companion-backend-xdgq.onrender.com/actuator/health)
[![Frontend Client](https://img.shields.io/badge/Vercel-Connected-black?logo=vercel)](https://frontend-peach-eight-99.vercel.app)

- **Live API URL:** [https://travel-companion-backend-xdgq.onrender.com](https://travel-companion-backend-xdgq.onrender.com)
- **Health Endpoint:** [https://travel-companion-backend-xdgq.onrender.com/actuator/health](https://travel-companion-backend-xdgq.onrender.com/actuator/health)
- **Connected Frontend:** [https://frontend-peach-eight-99.vercel.app](https://frontend-peach-eight-99.vercel.app)

---

## 🛠️ Tech Stack

- **Language & Framework:** Java 17, Spring Boot 3.3.4
- **Security:** Spring Security 6 with Stateless JWT Authentication & BCrypt
- **ORM / Persistence:** Spring Data JPA, Hibernate
- **Database:** MySQL (Production) / Embedded H2 (Graceful fallback)
- **Monitoring:** Spring Boot Actuator (`/actuator/health`)
- **Deployment:** Render (Docker containerized)

---

## 🚀 Getting Started Locally

### Prerequisites
- Java 17 (JDK)
- Apache Maven 3.8+

### Run Locally

```powershell
# Set required JWT secret
$env:JWT_SECRET = "your-private-secret-key-at-least-32-chars"

# Run Spring Boot
mvn spring-boot:run
```

The API will listen at `http://localhost:8080`.

---

## ⚙️ Environment Variables

| Variable | Description | Default / Example |
| :--- | :--- | :--- |
| `JWT_SECRET` | Secret key for signing JWT tokens (min 32 chars) | *(Required)* |
| `PORT` | HTTP port the server listens on | `8080` (or dynamic on Render) |
| `DB_URL` | JDBC Database Connection URL | `jdbc:mysql://<host>:3306/<dbname>?useSSL=true` |
| `DB_USERNAME` | Database username | `root` |
| `DB_PASSWORD` | Database password | `******` |
| `CORS_ALLOWED_ORIGINS` | Comma-separated list of allowed origins | `http://localhost:5173,https://*.vercel.app` |
| `SPRING_PROFILES_ACTIVE` | Active profile (`prod` or default) | `prod` |

---

## 📡 Key API Endpoints

| Method | Endpoint | Description | Auth Required |
| :--- | :--- | :--- | :--- |
| `GET` | `/actuator/health` | Service health status | ❌ No |
| `POST` | `/api/auth/register` | Register new user | ❌ No |
| `POST` | `/api/auth/login` | Authenticate & get JWT | ❌ No |
| `GET` | `/api/auth/me` | Current authenticated user profile | ✅ Bearer JWT |
| `GET/POST`| `/api/trips/**` | Travel plans & companion matching | ✅ Bearer JWT |
| `GET/POST`| `/api/messages/**` | Chat & companion messaging | ✅ Bearer JWT |
| `GET/POST`| `/api/connections/**`| Companion connection requests | ✅ Bearer JWT |
