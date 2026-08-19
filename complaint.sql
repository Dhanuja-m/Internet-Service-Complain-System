CREATE DATABASE internet_complaint_system;

USE internet_complaint_system;

-- Users Table
CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(15),
    password VARCHAR(100) NOT NULL,
    role ENUM('Admin','Customer') NOT NULL
);

-- Complaints Table
CREATE TABLE complaints (
    complaint_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    issue_type VARCHAR(100),
    description TEXT,
    priority ENUM('Low','Medium','High'),
    status ENUM('Pending','In Progress','Resolved') DEFAULT 'Pending',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

-- Default Admin Account
INSERT INTO users(name,email,phone,password,role)
VALUES(
'Admin',
'admin@gmail.com',
'6238738638',
'admin123',
'Admin'
);