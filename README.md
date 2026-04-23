# auth

## integrate with this repo

```bash
https://github.com/RABUNTHOEUN/nuxt_intergrate_with_spring.git
```

# 🚀 Spring Boot Auth + Page Builder Backend

A secure and scalable **Spring Boot REST API** providing:

- 🔐 JWT Authentication (Login / Register / Logout)
- 🔄 Refresh Token System
- 👤 User Management
- 🧾 Token Blacklisting
- 🏗 Dynamic Page Builder API (JSON-based CMS)
- 🗄 MySQL Database Integration

---

# 📌 Features

- JWT Authentication (Access + Refresh Token)
- Secure Logout with Token Revocation
- Token Blacklist System
- Role-based system (USER / ADMIN)
- RESTful API structure
- Page Builder JSON storage system
- CORS configured for Nuxt frontend
- Stateless authentication (Spring Security 6)

---

# 🧱 Tech Stack

- Java 17+ / 21
- Spring Boot 3+
- Spring Security 6
- JWT (JSON Web Token)
- MySQL / PostgreSQL
- JPA / Hibernate
- Lombok

---

# 📁 Project Structure

src/main/java/com/dev/auth/
│
├── auth/ # Authentication logic
├── config/ # Security + JWT config
├── token/ # Token + Refresh token system
├── user/ # User entity + repository
├── site/ # Page builder (CMS system)
├── controller/ # REST APIs
└── service/ # Business logic

---

# ⚙️ Setup Instructions

## 1. Clone project

```bash
git clone https://github.com/RABUNTHOEUN/auth.git
cd backend
2. Configure database
application.yml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/your_db
    username: root
    password: root

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
3. Run project
mvn spring-boot:run

Backend runs on:

http://localhost:8080
🔐 Authentication API
Register
POST /api/v1/auth/register
Request
{
  "firstname": "John",
  "lastname": "Doe",
  "email": "john@mail.com",
  "password": "123456"
}
Login
POST /api/v1/auth/authenticate
Logout
POST /api/v1/auth/logout
Authorization: Bearer <token>

Response:

{
  "success": true,
  "message": "Logout successful"
}
Refresh Token
POST /api/v1/auth/refresh
🔑 Token System

This project uses:

✔ Access Token
Short-lived (15 min)
Used for API authentication
✔ Refresh Token
Long-lived (7 days)
Used to generate new access token
🚪 Logout System

When user logs out:

Access token is extracted from request
Token is marked as:
expired = true
revoked = true
Stored in DB blacklist
🏗 Page Builder System

This backend supports dynamic page structure using JSON.

Example Site JSON
{
  "name": "Home Page",
  "config": {
    "header": {
      "theme": "header1"
    },
    "sections": [
      {
        "type": "hero",
        "props": {
          "title": "Welcome",
          "subtitle": "Spring Boot + Nuxt Builder"
        }
      }
    ]
  }
}
Create Site
POST /api/sites
Authorization: Bearer <token>
Get Sites
GET /api/sites
Authorization: Bearer <token>
🔒 Security Configuration
Stateless JWT authentication
CSRF disabled
CORS enabled for frontend:
allowedOrigins("http://localhost:3000")
🌐 CORS Setup

Make sure frontend is allowed:

registry.addMapping("/**")
    .allowedOrigins("http://localhost:3000")
    .allowedMethods("*")
    .allowedHeaders("*")
🧠 Architecture Flow
Nuxt Frontend
     ↓
JWT Login Request
     ↓
Spring Boot Auth API
     ↓
Access Token + Refresh Token
     ↓
Secure API Requests
     ↓
Page Builder JSON stored in DB
🚀 Future Improvements
Redis Token Blacklist
Role-based permissions (ADMIN builder system)
Multi-tenant SaaS support
Real-time collaboration editor
Drag & drop page builder API
Version history for pages
👨‍💻 Author

Built with ❤️ using:

Spring Boot
JWT Authentication
MySQL
Nuxt 4 Frontend
```
