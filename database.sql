CREATE DATABASE IF NOT EXISTS onlinetest_db
  CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Spring Boot creates and updates the application tables through JPA.
-- Register an account through /register, then optionally promote it:
-- UPDATE onlinetest_db.users SET role='ADMIN' WHERE email='admin@example.com';
