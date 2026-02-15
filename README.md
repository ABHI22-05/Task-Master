# TaskMaster - Task Tracking and Management System

A comprehensive backend system for task tracking and management that facilitates collaboration and organization within teams or projects.

## 🚀 Features

### Core Functionality
- **User Authentication & Authorization**: Secure JWT-based authentication with role-based access control (USER, ADMIN, MANAGER)
- **Task Management**: Complete CRUD operations with filtering, sorting, and searching capabilities
- **Team Collaboration**: Create teams, invite members, and manage team projects
- **Project Organization**: Organize tasks within projects and teams
- **Comments & Attachments**: Collaborate through task comments and file attachments
- **Real-time Updates**: Track task status, assignments, and progress

### User Stories Implemented
✅ User registration and secure login  
✅ Profile management and updates  
✅ Create tasks with title, description, and due date  
✅ View all assigned tasks  
✅ Mark tasks as completed  
✅ Assign tasks to team members  
✅ Filter tasks by status (OPEN, IN_PROGRESS, COMPLETED, CANCELLED)  
✅ Search tasks by title or description  
✅ Add comments and attachments to tasks  
✅ Create teams/projects and invite members  
✅ Secure logout functionality  

## 🛠️ Technology Stack

- **Framework**: Spring Boot 3.2.2
- **Language**: Java 17
- **Build Tool**: Gradle
- **Database**: PostgreSQL
- **Security**: Spring Security + JWT (JSON Web Tokens)
- **ORM**: Spring Data JPA / Hibernate
- **Validation**: Jakarta Validation
- **Documentation**: Postman Collection included

## 📋 Prerequisites

Before running this application, ensure you have:

1. **Java 17** or higher installed
   ```bash
   java -version
   ```

2. **PostgreSQL** database installed and running
   - Default port: 5432
   - Create a database named `taskmaster`

3. **Gradle** (optional, wrapper included)

## ⚙️ Configuration

### Database Setup

1. **Create PostgreSQL Database**:
   ```sql
   CREATE DATABASE taskmaster;
   ```

2. **Update Database Credentials** (if different from defaults):
   
   Edit `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/taskmaster
   spring.datasource.username=postgres
   spring.datasource.password=postgres
   ```

3. **JWT Configuration**:
   
   The JWT secret key is configured in `application.properties`. For production, use a strong, randomly generated secret:
   ```properties
   jwt.secret=your-very-secure-secret-key-here-minimum-256-bits
   jwt.expiration=86400000
   ```

## 🚀 Running the Application

### Option 1: Using Gradle Wrapper (Recommended)

```bash
# Windows
.\gradlew clean build
.\gradlew bootRun

# Linux/Mac
./gradlew clean build
./gradlew bootRun
```

### Option 2: Using IDE

1. Open the project in IntelliJ IDEA or Eclipse
2. Wait for dependencies to download
3. Run `BackendApplication.java`

### Option 3: Using JAR

```bash
# Build the JAR
.\gradlew clean build

# Run the JAR
java -jar build/libs/backend-0.0.1-SNAPSHOT.jar
```

The application will start on **http://localhost:8080**

## 📚 API Documentation

### Base URL
```
http://localhost:8080/api
```

### Authentication Endpoints

#### Register New User
```http
POST /api/auth/register
Content-Type: application/json

{
  "username": "johndoe",
  "email": "john@example.com",
  "password": "password123",
  "fullName": "John Doe",
  "phone": "+1234567890"
}
```

#### Login
```http
POST /api/auth/login
Content-Type: application/json

{
  "usernameOrEmail": "johndoe",
  "password": "password123"
}

Response:
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "type": "Bearer",
  "username": "johndoe",
  "email": "john@example.com",
  "role": "USER"
}
```

### Task Endpoints

All task endpoints require authentication. Include the JWT token in the Authorization header:
```
Authorization: Bearer {your-jwt-token}
```

#### Create Task
```http
POST /api/tasks
Content-Type: application/json
Authorization: Bearer {token}

{
  "title": "Implement user authentication",
  "description": "Add JWT-based authentication to the API",
  "status": "OPEN",
  "priority": "HIGH",
  "dueDate": "2026-02-20T10:00:00"
}
```

#### Get All Tasks (with filters)
```http
GET /api/tasks?status=OPEN&search=authentication
Authorization: Bearer {token}
```

#### Get My Tasks
```http
GET /api/tasks/my-tasks
Authorization: Bearer {token}
```

#### Update Task
```http
PUT /api/tasks/{id}
Content-Type: application/json
Authorization: Bearer {token}

{
  "title": "Updated Task Title",
  "status": "IN_PROGRESS",
  "priority": "URGENT"
}
```

#### Assign Task
```http
PATCH /api/tasks/{taskId}/assign/{userId}
Authorization: Bearer {token}
```

#### Mark as Completed
```http
PATCH /api/tasks/{id}/complete
Authorization: Bearer {token}
```

#### Delete Task
```http
DELETE /api/tasks/{id}
Authorization: Bearer {token}
```

### Team Endpoints

#### Create Team
```http
POST /api/teams
Content-Type: application/json
Authorization: Bearer {token}

{
  "name": "Development Team",
  "description": "Main development team for the project"
}
```

#### Add Member to Team
```http
POST /api/teams/{teamId}/members/{userId}
Authorization: Bearer {token}
```

#### Get My Teams
```http
GET /api/teams/my-teams
Authorization: Bearer {token}
```

### Project Endpoints

#### Create Project
```http
POST /api/projects
Content-Type: application/json
Authorization: Bearer {token}

{
  "name": "Mobile App Development",
  "description": "Development of mobile application",
  "teamId": 1,
  "status": "ACTIVE",
  "startDate": "2026-02-01T00:00:00",
  "endDate": "2026-06-30T00:00:00"
}
```

### Comment Endpoints

#### Add Comment to Task
```http
POST /api/tasks/{taskId}/comments
Content-Type: application/json
Authorization: Bearer {token}

{
  "content": "This is a comment on the task"
}
```

#### Get Comments for Task
```http
GET /api/tasks/{taskId}/comments
Authorization: Bearer {token}
```

## 📦 Postman Collection

A complete Postman collection is included in the project root: `TaskMaster_API.postman_collection.json`

### Importing to Postman:
1. Open Postman
2. Click "Import" button
3. Select the `TaskMaster_API.postman_collection.json` file
4. The collection will be imported with all endpoints configured

### Using the Collection:
1. Register a new user using the "Register" endpoint
2. Login using the "Login" endpoint
3. The token will be automatically saved to the collection variable
4. All subsequent requests will use this token automatically

## 🗂️ Project Structure

```
src/
├── main/
│   ├── java/com/sneakerhead/backend/
│   │   ├── config/              # Security and application configuration
│   │   │   └── SecurityConfig.java
│   │   ├── controller/          # REST API controllers
│   │   │   ├── AuthController.java
│   │   │   ├── TaskController.java
│   │   │   ├── TeamController.java
│   │   │   ├── ProjectController.java
│   │   │   ├── CommentController.java
│   │   │   └── UserController.java
│   │   ├── dto/                 # Data Transfer Objects
│   │   │   ├── request/         # Request DTOs
│   │   │   └── response/        # Response DTOs
│   │   ├── entity/              # JPA Entities
│   │   │   ├── User.java
│   │   │   ├── Task.java
│   │   │   ├── Team.java
│   │   │   ├── Project.java
│   │   │   ├── Comment.java
│   │   │   └── Attachment.java
│   │   ├── exception/           # Custom exceptions and handlers
│   │   │   ├── GlobalExceptionHandler.java
│   │   │   └── ResourceNotFoundException.java
│   │   ├── repository/          # Spring Data JPA repositories
│   │   │   ├── UserRepository.java
│   │   │   ├── TaskRepository.java
│   │   │   ├── TeamRepository.java
│   │   │   ├── ProjectRepository.java
│   │   │   └── CommentRepository.java
│   │   ├── security/            # Security components
│   │   │   ├── JwtAuthenticationFilter.java
│   │   │   ├── JwtTokenProvider.java
│   │   │   └── CustomUserDetailsService.java
│   │   ├── service/             # Business logic services
│   │   │   ├── AuthService.java
│   │   │   ├── TaskService.java
│   │   │   ├── TeamService.java
│   │   │   ├── ProjectService.java
│   │   │   └── CommentService.java
│   │   └── BackendApplication.java
│   └── resources/
│       └── application.properties
└── test/                        # Test files
```

## 🔐 Security

### Authentication Flow
1. User registers with username, email, and password
2. Password is hashed using BCrypt
3. User logs in with credentials
4. Server validates credentials and generates JWT token
5. Client includes token in Authorization header for subsequent requests
6. Server validates token and extracts user information

### Role-Based Access Control
- **USER**: Can create tasks, join teams, add comments
- **MANAGER**: All USER permissions + manage team members
- **ADMIN**: Full system access

## 🗄️ Database Schema

### Main Tables
- **users**: User accounts and authentication
- **teams**: Team information
- **team_members**: Many-to-many relationship between users and teams
- **projects**: Projects within teams
- **tasks**: Task information and assignments
- **comments**: Task comments
- **attachments**: File attachments for tasks

### Relationships
- User → Tasks (One-to-Many: created tasks)
- User → Tasks (One-to-Many: assigned tasks)
- User ↔ Teams (Many-to-Many)
- Team → Projects (One-to-Many)
- Project → Tasks (One-to-Many)
- Task → Comments (One-to-Many)
- Task → Attachments (One-to-Many)

## 🧪 Testing

### Manual Testing Steps

1. **Start the application**
2. **Register a user**:
   ```bash
   curl -X POST http://localhost:8080/api/auth/register \
     -H "Content-Type: application/json" \
     -d '{"username":"testuser","email":"test@example.com","password":"password123","fullName":"Test User"}'
   ```

3. **Login**:
   ```bash
   curl -X POST http://localhost:8080/api/auth/login \
     -H "Content-Type: application/json" \
     -d '{"usernameOrEmail":"testuser","password":"password123"}'
   ```

4. **Create a task** (use token from login):
   ```bash
   curl -X POST http://localhost:8080/api/tasks \
     -H "Content-Type: application/json" \
     -H "Authorization: Bearer {your-token}" \
     -d '{"title":"Test Task","description":"Test Description","status":"OPEN","priority":"MEDIUM"}'
   ```

## 🐛 Troubleshooting

### Common Issues

#### 1. Database Connection Error
```
Error: Could not connect to database
```
**Solution**: 
- Ensure PostgreSQL is running
- Verify database credentials in `application.properties`
- Check if database `taskmaster` exists

#### 2. Port Already in Use
```
Error: Port 8080 is already in use
```
**Solution**: 
- Change the port in `application.properties`:
  ```properties
  server.port=8081
  ```

#### 3. JWT Token Expired
```
Error: 401 Unauthorized
```
**Solution**: 
- Login again to get a new token
- Adjust token expiration time in `application.properties`

#### 4. Gradle Build Fails
```
Error: Could not resolve dependencies
```
**Solution**: 
- Clear Gradle cache: `.\gradlew clean --refresh-dependencies`
- Check internet connection
- Verify Java version: `java -version`

## 📝 Development Notes

### Adding New Features

1. **Create Entity**: Add new entity class in `entity` package
2. **Create Repository**: Extend JpaRepository in `repository` package
3. **Create DTOs**: Add request/response DTOs in `dto` package
4. **Create Service**: Implement business logic in `service` package
5. **Create Controller**: Add REST endpoints in `controller` package
6. **Update Security**: Configure access rules in `SecurityConfig` if needed

### Best Practices
- Always validate input using `@Valid` annotation
- Use DTOs for API requests/responses (never expose entities directly)
- Handle exceptions using `@ControllerAdvice`
- Write meaningful commit messages
- Keep controllers thin, move logic to services
- Use constructor injection for dependencies

## 🔄 Future Enhancements

- [ ] Real-time notifications using WebSockets
- [ ] Email notifications for task assignments
- [ ] File upload for attachments
- [ ] Task activity timeline
- [ ] Advanced search with Elasticsearch
- [ ] Task dependencies and subtasks
- [ ] Gantt chart visualization
- [ ] Export tasks to PDF/Excel
- [ ] Integration with third-party services (Slack, JIRA)
- [ ] AI-powered task description generation

## 📄 License

This project is created for educational purposes.

## 👥 Contributors

- Backend Development: Spring Boot + PostgreSQL
- Authentication: JWT-based security
- API Design: RESTful principles

## 📞 Support

For issues or questions:
1. Check the troubleshooting section
2. Review the API documentation
3. Test with Postman collection
4. Verify database connection

---

**Happy Task Managing! 🎯**
#   T a s k - M a s t e r  
 