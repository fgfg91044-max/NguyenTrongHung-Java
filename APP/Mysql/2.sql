USE defaultdb; -- Phải dùng defaultdb theo cấu hình Backend

CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    fullname VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

-- Tạo tiếp các bảng readings, summaries nếu bạn muốn dùng các chức năng đó.