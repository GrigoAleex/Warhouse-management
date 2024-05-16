CREATE TABLE products (
    id      INT AUTO_INCREMENT PRIMARY KEY,
    name    VARCHAR(255) NOT NULL,
    stock   INT NOT NULL CHECK (stock > 0),
    barcode VARCHAR(6) NOT NULL,
    image   VARCHAR(255) NOT NULL
);