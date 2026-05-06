-- Create users table
CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    role ENUM('admin', 'user') DEFAULT 'user',
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_login TIMESTAMP NULL
);

-- Insert sample users
-- The password is 'password123' hashed with BCrypt
INSERT INTO users (username, password, full_name, role) VALUES
('admin', '$2a$10$f6B0L6Xv9G4M2D2jQ4lW6eUj/6R/R6sM9e8U6H3eH0eM0d9C3Yp0G', 'Admin User', 'admin'),
('john', '$2a$10$f6B0L6Xv9G4M2D2jQ4lW6eUj/6R/R6sM9e8U6H3eH0eM0d9C3Yp0G', 'John Doe', 'user'),
('jane', '$2a$10$f6B0L6Xv9G4M2D2jQ4lW6eUj/6R/R6sM9e8U6H3eH0eM0d9C3Yp0G', 'Jane Smith', 'user');
