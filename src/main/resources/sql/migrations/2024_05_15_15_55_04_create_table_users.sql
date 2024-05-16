CREATE TABLE users (
    id              INT AUTO_INCREMENT PRIMARY KEY,
    name            VARCHAR(255) NOT NULL,
    phone_number    VARCHAR(11) NOT NULL,
    email           VARCHAR(255) NOT NULL,
    avatar          VARCHAR(255) DEFAULT '/avatars/default.png'
);