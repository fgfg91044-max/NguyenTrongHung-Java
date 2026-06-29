CREATE DATABASE IF NOT EXISTS ReadQuizDB;
USE ReadQuizDB;

-- Bảng Người dùng
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    fullname VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

-- Bảng Bài đọc
CREATE TABLE readings (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL
);

-- Bảng Tóm tắt
CREATE TABLE summaries (
    id INT AUTO_INCREMENT PRIMARY KEY,
    reading_id INT NOT NULL,
    summary TEXT NOT NULL,
    CONSTRAINT fk_summary_reading FOREIGN KEY (reading_id) REFERENCES readings(id) ON DELETE CASCADE
);

-- Bảng Câu hỏi trắc nghiệm
CREATE TABLE quizzes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    reading_id INT NOT NULL,
    question TEXT NOT NULL,
    optionA VARCHAR(255) NOT NULL,
    optionB VARCHAR(255) NOT NULL,
    optionC VARCHAR(255) NOT NULL,
    optionD VARCHAR(255) NOT NULL,
    correctAnswer CHAR(1) NOT NULL,
    CONSTRAINT fk_quiz_reading FOREIGN KEY (reading_id) REFERENCES readings(id) ON DELETE CASCADE
);

-- Bảng Kết quả điểm số
CREATE TABLE results (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    score DOUBLE NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_result_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

