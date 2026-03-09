# School Management System

A comprehensive **multi-tenant** Spring Boot backend for managing schools, students, courses, fees, exams, and transportation with role-based authentication.

This interview-ready project showcases advanced backend concepts including multi-tenancy, JWT authentication with custom claims, role-based access control, and modular architecture.

## 🎯 **FEATURES**

### Core Features
- **Multi-Tenancy**: Complete data isolation per school
- **Role-Based Access Control**: SUPER_ADMIN, SCHOOL_ADMIN, STUDENT
- **JWT Authentication**: Secure token-based auth with `schoolId` and `role` claims
- **User Management**: Registration, login, and user-school assignment

### Modules
- **Student Management**: CRUD operations for students with school association
- **Course Management**: Create and manage courses per school
- **Fee Management**: Track payments and fee summaries per student
- **Exam Management**: Schedule exams, record results, grade management
- **Transport Management**: Manage routes and vehicles with driver information

## 🏗️ **Architecture**

```
Multi-Tenant Design:
├── School (Tenant)
│   ├── Users (SCHOOL_ADMIN, STUDENT)
│   ├── Students
│   ├── Courses
│   ├── Payments
│   ├── Exams & Results
│   └── Transport Routes & Vehicles
```

All data is automatically scoped by `schoolId` from JWT token.

## 🔐 **Roles & Permissions**

| Role | Permissions |
|------|-------------|
| **SUPER_ADMIN** | Manage all schools, create school admins |
| **SCHOOL_ADMIN** | Full CRUD on their school's data |
| **STUDENT** | Read-only access, cannot delete resources |

## 🚀 **Tech Stack**

- **Java 21**
- **Spring Boot 3.2.5**
- **Spring Security + JWT**
- **Spring Data JPA**
- **MySQL** (Production) / **H2** (Testing)
- **Maven**
- **SpringDoc OpenAPI** (API Documentation)

## 📡 **API Endpoints**

### Authentication
- `POST /auth/register` - Register new user
- `POST /auth/login` - Login and get JWT token

### Schools (SUPER_ADMIN only)
- `POST /api/schools` - Create school
- `GET /api/schools` - List all schools
- `GET /api/schools/{id}` - Get school details
- `PUT /api/schools/{id}` - Update school
- `DELETE /api/schools/{id}` - Delete school

### Exams (School-scoped)
- `POST /api/exams` - Create exam
- `GET /api/exams` - List exams
- `GET /api/exams/course/{courseId}` - Exams by course
- `POST /api/exams/results` - Record result
- `GET /api/exams/results/student/{studentId}` - Student results

### Transport (School-scoped)
- `POST /api/transport/routes` - Create route
- `GET /api/transport/routes` - List routes
- `POST /api/transport/vehicles` - Add vehicle
- `GET /api/transport/vehicles/route/{routeId}` - Vehicles by route

### Students, Courses, Payments
- All existing endpoints now school-scoped via JWT

> **Note**: Most endpoints require JWT token with format: `Authorization: Bearer <token>`

## 💻 **Run Locally**

### Prerequisites
- Java 21
- MySQL running on `localhost:3306`
- Maven (or use included wrapper)

### Setup

1. **Create Database**
```sql
CREATE DATABASE schooldb;
```

2. **Set Environment Variables**
```bash
DB_URL=jdbc:mysql://localhost:3306/schooldb
DB_USERNAME=root
DB_PASSWORD=yourpassword
```

3. **Run Application**
```bash
./mvnw spring-boot:run
```

4. **Access API**
- Base URL: `http://localhost:8080`
- Swagger UI: `http://localhost:8080/swagger-ui.html` (if enabled)

## 📊 **Quick Start Guide**

1. **Create a School** (as SUPER_ADMIN):
```bash
POST /api/schools
{
  "name": "ABC High School",
  "address": "123 Main St",
  "email": "admin@abcschool.com",
  "contactNumber": "9876543210"
}
```

2. **Create School Admin** (directly in database or via SUPER_ADMIN endpoint)

3. **Login as School Admin**:
```bash
POST /auth/login
{
  "email": "admin@abcschool.com",
  "password": "password"
}
```

4. **Use JWT Token** in subsequent requests:
```bash
Authorization: Bearer eyJhbGc...
```

## 🎓 **What I Learned**

- **Multi-tenancy architecture** with row-level security
- **JWT custom claims** for context-aware authentication
- **Role-based access control** in Spring Security
- **Advanced JPA relationships** (@ManyToOne, @OneToMany)
- **Repository pattern** with custom query methods
- **RESTful API design** principles
- **Modular backend architecture**
- **Interview-ready project structure**

## 📝 **License**

This is a practice/educational project.

---

**Interview Ready** ✅ | **Production Ready Architecture** 🏗️ | **Scalable Multi-Tenant** 🚀