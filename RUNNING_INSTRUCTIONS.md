# 🚀 TaskMaster - Running Instructions

## ✅ Application is Running!

Your TaskMaster Spring Boot application is now running successfully on:
- **Base URL**: http://localhost:8080
- **H2 Console**: http://localhost:8080/h2-console

## 📊 Database Configuration

The application is using **H2 in-memory database** which requires no external database installation.

### H2 Console Access
1. Open your browser and navigate to: http://localhost:8080/h2-console
2. Use these credentials:
   - **JDBC URL**: `jdbc:h2:mem:taskmaster`
   - **Username**: `sa`
   - **Password**: (leave empty)
   - **Driver Class**: `org.h2.Driver`

## 🔧 Running the Application

### Start the Application
```bash
.\gradlew bootRun
```

### Stop the Application
Press `Ctrl + C` in the terminal where the application is running.

## 🧪 Testing the API

### Using Postman
1. Import the collection: `TaskMaster_API.postman_collection.json`
2. Test the endpoints starting with registration and login

### Using cURL

#### 1. Register a User
```bash
curl -X POST http://localhost:8080/api/auth/register `
  -H "Content-Type: application/json" `
  -d '{
    "username": "testuser",
    "email": "test@example.com",
    "password": "password123",
    "fullName": "Test User"
  }'
```

#### 2. Login
```bash
curl -X POST http://localhost:8080/api/auth/login `
  -H "Content-Type: application/json" `
  -d '{
    "usernameOrEmail": "testuser",
    "password": "password123"
  }'
```

#### 3. Create a Task (use the token from login)
```bash
curl -X POST http://localhost:8080/api/tasks `
  -H "Content-Type: application/json" `
  -H "Authorization: Bearer YOUR_TOKEN_HERE" `
  -d '{
    "title": "My First Task",
    "description": "Testing the API",
    "status": "OPEN",
    "priority": "MEDIUM"
  }'
```

## 📝 Available Endpoints

### Authentication
- `POST /api/auth/register` - Register a new user
- `POST /api/auth/login` - Login and get JWT token

### Users
- `GET /api/users/me` - Get current user profile
- `PUT /api/users/me` - Update current user profile

### Tasks
- `GET /api/tasks` - Get all tasks
- `POST /api/tasks` - Create a new task
- `GET /api/tasks/{id}` - Get task by ID
- `PUT /api/tasks/{id}` - Update task
- `DELETE /api/tasks/{id}` - Delete task
- `PUT /api/tasks/{id}/assign` - Assign task to user

### Teams
- `GET /api/teams` - Get all teams
- `POST /api/teams` - Create a new team
- `GET /api/teams/{id}` - Get team by ID
- `PUT /api/teams/{id}` - Update team
- `DELETE /api/teams/{id}` - Delete team
- `POST /api/teams/{id}/members` - Add member to team
- `DELETE /api/teams/{id}/members/{userId}` - Remove member from team

### Projects
- `GET /api/projects` - Get all projects
- `POST /api/projects` - Create a new project
- `GET /api/projects/{id}` - Get project by ID
- `PUT /api/projects/{id}` - Update project
- `DELETE /api/projects/{id}` - Delete project

### Comments
- `GET /api/tasks/{taskId}/comments` - Get all comments for a task
- `POST /api/tasks/{taskId}/comments` - Add comment to task
- `PUT /api/comments/{id}` - Update comment
- `DELETE /api/comments/{id}` - Delete comment

## 🔐 Security Notes

- All endpoints except `/api/auth/**` and `/h2-console/**` require JWT authentication
- Include the JWT token in the `Authorization` header as `Bearer YOUR_TOKEN`
- Tokens expire after 24 hours (86400000 ms)

## 📚 Documentation

- **Full API Documentation**: See `README.md`
- **Quick Start Guide**: See `QUICK_START.md`
- **Implementation Details**: See `IMPLEMENTATION_SUMMARY.md`
- **Postman Collection**: `TaskMaster_API.postman_collection.json`

## 🎯 Next Steps

1. **Test the API**: Use Postman or cURL to test all endpoints
2. **Explore H2 Console**: View the database tables and data
3. **Build a Frontend**: Connect a React/Angular/Vue app to this backend
4. **Deploy**: Deploy to a cloud platform (Heroku, AWS, Azure, etc.)

---

**Happy Coding! 🎉**
