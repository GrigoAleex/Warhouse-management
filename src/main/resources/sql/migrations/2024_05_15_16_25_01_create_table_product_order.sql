CREATE TABLE product_order (
    id          INT AUTO_INCREMENT PRIMARY KEY,

    product_id  INT,
    FOREIGN KEY (product_id) REFERENCES products(id),

    order_id    INT,
    FOREIGN KEY (order_id) REFERENCES orders(id)
);