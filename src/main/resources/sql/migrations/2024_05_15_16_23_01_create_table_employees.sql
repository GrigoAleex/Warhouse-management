CREATE TABLE employees (
    id          INT AUTO_INCREMENT PRIMARY KEY,

    user_id     INT,
    FOREIGN KEY (user_id) REFERENCES users(id),

    password_hash VARCHAR(255) NOT NULL
);