# School Management System

A comprehensive, full-stack School Management System built with Spring Boot and React. This application provides a modern, robust, and scalable platform for managing students, teachers, courses, attendance, and administrative tasks efficiently. 

It features a multi-tenant architecture (supporting multiple schools), role-based access control, and a beautifully designed, responsive glassmorphism dashboard.

## 🌟 Key Features

### 🔐 Authentication & Security
- Secure JWT-based authentication
- Role-based Access Control (Super Admin, School Admin, Teacher, Student)
- Password encryption using BCrypt

### 📊 Admin Dashboard
- Real-time statistics and summary cards
- Quick actions menu
- Responsive, dark-themed modern UI

### 👥 User Management
- **Students**: Complete CRUD operations, course enrollment, fee tracking
- **Teachers**: Profile management, subject assignment
- Multi-school tenant isolation (users only see data for their assigned school)

### 📚 Academics & Operations
- **Courses**: Manage course offerings and associated fees
- **Attendance**: Daily student attendance tracking with date filtering
- **Exams & Results**: Manage examination schedules and student grading
- **Transport**: Manage vehicle routes and transport assignments

---

## 🛠️ Technology Stack

### Backend
- **Framework:** Java, Spring Boot 3+
- **Database:** H2 (In-memory, ready for MySQL/PostgreSQL drop-in)
- **Security:** Spring Security, JSON Web Tokens (JWT)
- **API Documentation:** Swagger / OpenAPI
- **Build Tool:** Maven

### Frontend
- **Framework:** React 18, Vite
- **Styling:** Tailwind CSS v4, Custom CSS (Glassmorphism design)
- **Routing:** React Router v6
- **HTTP Client:** Axios (with automated JWT interceptors)

---

## 🚀 Getting Started

### Prerequisites
- Java 17 or higher
- Node.js (v18+) and npm
- Maven (optional, wrapper included)

### 1. Backend Setup

1. Open the terminal and navigate to the `backend` directory:
   ```bash
   cd backend
   ```
2. Run the application using the Maven wrapper:
   ```bash
   ./mvnw spring-boot:run
   ```
   *(On Windows, use `.\mvnw.cmd spring-boot:run`)*

The backend will start running on `http://localhost:8080`.

**Note on Database:** The application currently uses an H2 in-memory database. Data will be reset upon restarting. To use a persistent database like MySQL, update the `application.properties`.

### 2. Frontend Setup

1. Open a new terminal and navigate to the `frontend` directory:
   ```bash
   cd frontend
   ```
2. Install dependencies:
   ```bash
   npm install
   ```
3. Start the development server:
   ```bash
   npm run dev
   ```

The frontend will start running on `http://localhost:5173`.

---

## 💡 Initial Setup & First Login

Because the system uses multi-tenancy (data isolation per school), follow these steps to set up your first user:

1. **Register a User Account**
   Make a POST request to register:
   ```bash
   curl -X POST http://localhost:8080/auth/register \
   -H "Content-Type: application/json" \
   -d "{\"name\":\"Admin\",\"email\":\"admin@school.com\",\"password\":\"password\"}"
   ```

2. **Access the Application**
   Open `http://localhost:5173` in your browser. Log in using the credentials you just created.

*Note: For the dashboard widgets and CRUD features to function fully, assign a `School` entity to your user record in the database.*

---

## 📂 Project Structure

```text
School Management System/
├── backend/                  # Spring Boot API
│   ├── src/main/java         # Java source code
│   │   ├── config/           # Security and CORS config
│   │   ├── controller/       # REST API Endpoints
│   │   ├── dto/              # Data Transfer Objects
│   │   ├── entity/           # JPA Entities
│   │   ├── repository/       # Database interfaces
│   │   └── service/          # Business logic
│   └── pom.xml               # Maven dependencies
│
└── frontend/                 # React UI
    ├── src/
    │   ├── components/       # Reusable UI components
    │   ├── context/          # React Context (Auth State)
    │   ├── pages/            # View components (Login, Dashboard, etc.)
    │   └── services/         # Axios API clients
    ├── index.html            # Vite entry point
    ├── vite.config.js        # Vite configuration
    └── package.json          # Node dependencies
```

---

## 📝 License
This project is for educational and portfolio purposes. 
