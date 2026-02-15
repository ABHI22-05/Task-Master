# 🚀 Quick Start Guide - TaskMaster Backend

## Prerequisites Check

Before starting, ensure you have:
- ✅ Java 21 installed (`java -version`)
- ✅ PostgreSQL installed and running
- ✅ Postman (optional, for API testing)

## Step-by-Step Setup

### Step 1: Database Setup (5 minutes)

1. **Start PostgreSQL** (if not running)

2. **Create the database**:
   ```sql
   -- Open psql or pgAdmin and run:
   CREATE DATABASE taskmaster;
   ```

3. **Verify connection** (optional):
   ```sql
   \c taskmaster
   ```

### Step 2: Configure Application (2 minutes)

1. **Open** `src/main/resources/application.properties`

2. **Update database credentials** (if different from defaults):
   ```properties
   spring.datasource.username=YOUR_POSTGRES_USERNAME
   spring.datasource.password=YOUR_POSTGRES_PASSWORD
   ```

3. **Save the file**

### Step 3: Run the Application (1 minute)

**Option A: Using Gradle (Recommended)**
```bash
.\gradlew bootRun
```

**Option B: Using IDE**
1. Open project in IntelliJ IDEA
2. Run `BackendApplication.java`

### Step 4: Verify It's Running

You should see:
```
Started BackendApplication in X.XXX seconds
```

The API is now available at: **http://localhost:8080**

## 🧪 Test the API (5 minutes)

### Using Postman

1. **Import the collection**:
   - Open Postman
   - Click "Import"
   - Select `TaskMaster_API.postman_collection.json`

2. **Register a user**:
   - Use the "Register" request
   - Click "Send"

3. **Login**:
   - Use the "Login" request
   - Token will be saved automatically

4. **Create a task**:
   - Use the "Create Task" request
   - Click "Send"

### Using cURL

```bash
# 1. Register
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "email": "test@example.com",
    "password": "password123",
    "fullName": "Test User"
  }'

# 2. Login
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "usernameOrEmail": "testuser",
    "password": "password123"
  }'

# Copy the token from the response

# 3. Create a task
curl -X POST http://localhost:8080/api/tasks \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_TOKEN_HERE" \
  -d '{
    "title": "My First Task",
    "description": "Testing the API",
    "status": "OPEN",
    "priority": "MEDIUM"
  }'
```

## 📝 Common Commands

```bash
# Build the project
.\gradlew build -x test

# Run the application
.\gradlew bootRun

# Clean build
.\gradlew clean build

# Run tests
.\gradlew test

# Check dependencies
.\gradlew dependencies
```

## 🔍 Troubleshooting

### Application won't start

**Error**: `Connection refused` or `Cannot connect to database`
```
Solution:
1. Check PostgreSQL is running
2. Verify database 'taskmaster' exists
3. Check credentials in application.properties
```

**Error**: `Port 8080 already in use`
```
Solution:
Change port in application.properties:
server.port=8081
```

**Error**: `JWT secret key is too short`
```
Solution:
The default secret is fine for development.
For production, use a longer secret (256+ bits)
```

### Database tables not created

```
Solution:
The tables are created automatically on first run.
Check application logs for any Hibernate errors.
```

## 🎯 What's Next?

Now that your backend is running:

1. **Explore the API**: Use the Postman collection to test all endpoints
2. **Create teams**: Invite users to collaborate
3. **Manage tasks**: Create, assign, and track tasks
4. **Add comments**: Collaborate on tasks
5. **Build a frontend**: Connect a React/Angular/Vue app

## 📚 Documentation

- **Full API Documentation**: See `README.md`
- **Implementation Details**: See `IMPLEMENTATION_SUMMARY.md`
- **Postman Collection**: `TaskMaster_API.postman_collection.json`

## 🆘 Need Help?

1. Check the console logs for error messages
2. Review `README.md` for detailed documentation
3. Verify all prerequisites are met
4. Ensure PostgreSQL is running and accessible

---

**You're all set! Happy coding! 🎉**
