# Online Test Management System

A full-stack assessment application built with Java 17, Spring Boot, Spring Security, Thymeleaf, Spring Data JPA and MySQL.

## Features

- User registration and login
- BCrypt password hashing
- Role-based access control (USER and ADMIN)
- Admin-only assessment creation and activation/deactivation
- Multiple-choice question management
- Test attempts with automatic scoring
- Recent attempt history
- CSRF protection on form submissions
- MySQL persistence through Spring Data JPA

## Requirements

- JDK 17+
- Maven 3.8+
- MySQL 8+

## Database setup

Create the database:

```sql
CREATE DATABASE onlinetest_db;
```

Or run:

```bash
mysql -u root -p < database.sql
```

Tables are created and updated by Hibernate when the application starts.

## Configure database credentials

Set environment variables in the terminal before running the application:

```bash
export DB_URL='jdbc:mysql://localhost:3306/onlinetest_db?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC'
export DB_USER='root'
export DB_PASSWORD='your_mysql_password'
```

Credentials are read from environment variables and are not stored in the repository.

## Build and test

```bash
mvn clean test package
```

## Run

```bash
mvn spring-boot:run
```

Open http://localhost:8080.

## First-time setup

1. Register an account at `/register`.
2. Sign in at `/login`.
3. To enable the administrator interface, promote your registered account directly in MySQL:

```sql
UPDATE users SET role='ADMIN' WHERE email='your-email@example.com';
```

4. Sign out and sign in again. The Admin link will appear.
5. Create an assessment, add questions and options, and specify the correct answer exactly as one of the options.
6. Normal users can take active tests and see their scores in the dashboard.

New registrations always receive the USER role. Do not expose administrator privileges through public registration.

## Project structure

```text
src/main/java/com/onlinetest/
  config/       Security configuration
  controller/   Login, registration, quiz and admin routes
  model/        User, quiz, question and attempt entities
  repository/   Spring Data repositories
  service/      Registration, authentication and scoring services
src/main/resources/
  templates/    Thymeleaf pages
  static/css/   Application styles
```

## Security notes

- Passwords are stored using BCrypt hashes.
- Spring Security protects authenticated routes.
- Admin routes require the ADMIN role.
- CSRF protection remains enabled.
- Database credentials should be supplied locally through environment variables.
- This is an educational project; review production deployment, database migrations, rate limiting and operational security before exposing it publicly.
