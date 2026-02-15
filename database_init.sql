-- TaskMaster Database Initialization Script
-- PostgreSQL

-- Create database (run this separately if needed)
-- CREATE DATABASE taskmaster;

-- Connect to the database
\c taskmaster;

-- The tables will be auto-created by Hibernate based on the entities
-- This script provides some sample data for testing

-- Note: Run the application first to let Hibernate create the tables
-- Then you can use these INSERT statements to add sample data

-- Sample Users (passwords are hashed, these are examples only)
-- Password for all users: password123

-- INSERT INTO users (username, email, password, full_name, phone, role, active, created_at, updated_at)
-- VALUES 
--   ('admin', 'admin@taskmaster.com', '$2a$10$...', 'Admin User', '+1234567890', 'ADMIN', true, NOW(), NOW()),
--   ('john_doe', 'john@example.com', '$2a$10$...', 'John Doe', '+1234567891', 'USER', true, NOW(), NOW()),
--   ('jane_smith', 'jane@example.com', '$2a$10$...', 'Jane Smith', '+1234567892', 'MANAGER', true, NOW(), NOW()),
--   ('bob_wilson', 'bob@example.com', '$2a$10$...', 'Bob Wilson', '+1234567893', 'USER', true, NOW(), NOW());

-- Sample Teams
-- INSERT INTO teams (name, description, owner_id, active, created_at, updated_at)
-- VALUES 
--   ('Development Team', 'Main development team', 1, true, NOW(), NOW()),
--   ('QA Team', 'Quality assurance team', 1, true, NOW(), NOW()),
--   ('Design Team', 'UI/UX design team', 3, true, NOW(), NOW());

-- Sample Team Members
-- INSERT INTO team_members (team_id, user_id)
-- VALUES 
--   (1, 1), (1, 2), (1, 4),  -- Development Team
--   (2, 1), (2, 3),           -- QA Team
--   (3, 3), (3, 2);           -- Design Team

-- Sample Projects
-- INSERT INTO projects (name, description, team_id, status, start_date, end_date, created_at, updated_at)
-- VALUES 
--   ('TaskMaster Backend', 'Development of TaskMaster backend API', 1, 'ACTIVE', '2026-02-01', '2026-06-30', NOW(), NOW()),
--   ('TaskMaster Frontend', 'Development of TaskMaster frontend', 1, 'ACTIVE', '2026-02-15', '2026-07-15', NOW(), NOW()),
--   ('Mobile App', 'TaskMaster mobile application', 1, 'ACTIVE', '2026-03-01', '2026-08-31', NOW(), NOW());

-- Sample Tasks
-- INSERT INTO tasks (title, description, project_id, creator_id, assignee_id, status, priority, due_date, created_at, updated_at)
-- VALUES 
--   ('Setup project structure', 'Initialize Spring Boot project with required dependencies', 1, 1, 2, 'COMPLETED', 'HIGH', '2026-02-05', NOW(), NOW()),
--   ('Implement authentication', 'Add JWT-based authentication', 1, 1, 2, 'IN_PROGRESS', 'HIGH', '2026-02-15', NOW(), NOW()),
--   ('Create task management endpoints', 'Develop REST APIs for task CRUD operations', 1, 1, 4, 'OPEN', 'MEDIUM', '2026-02-20', NOW(), NOW()),
--   ('Design login page', 'Create UI mockup for login page', 2, 3, 2, 'OPEN', 'MEDIUM', '2026-02-18', NOW(), NOW());

-- Sample Comments
-- INSERT INTO comments (content, task_id, user_id, created_at, updated_at)
-- VALUES 
--   ('Great progress on this task!', 1, 1, NOW(), NOW()),
--   ('Need help with JWT configuration', 2, 2, NOW(), NOW()),
--   ('Should we use OAuth2 as well?', 2, 1, NOW(), NOW());

-- Verify data
SELECT 'Users' as table_name, COUNT(*) as count FROM users
UNION ALL
SELECT 'Teams', COUNT(*) FROM teams
UNION ALL
SELECT 'Projects', COUNT(*) FROM projects
UNION ALL
SELECT 'Tasks', COUNT(*) FROM tasks
UNION ALL
SELECT 'Comments', COUNT(*) FROM comments;
