# 🧠 Online Test Management System

A full-stack **Spring Boot + MySQL** web application that enables admins to create online quizzes and users to take them securely. Supports role-based login, test creation, result tracking, and dynamic rendering with Thymeleaf.

---

## 💻 Tech Stack

- **Java 17**
- **Spring Boot**
- **Spring Security**
- **Thymeleaf** (template engine)
- **MySQL**
- **JPA (Hibernate)**

---

## 🎯 Features

- 🔐 **Admin & User login** with role-based access
- 📝 Admin can **create, edit, and delete** quizzes
- 📊 Users can **attempt tests**, and get results instantly
- 🔒 Passwords stored securely with **BCrypt**
- 🌐 Thymeleaf templates for dynamic frontend rendering

---

## 🏗️ Folder Structure


```
OnlineTestManagementSystem/
├── src/
│ ├── main/
│ │ ├── java/com/onlinetest/
│ │ │ ├── controller/
│ │ │ ├── model/
│ │ │ ├── repository/
│ │ │ └── OnlineTestApp.java
│ │ └── resources/
│ │ ├── templates/
│ │ └── application.properties
├── pom.xml
