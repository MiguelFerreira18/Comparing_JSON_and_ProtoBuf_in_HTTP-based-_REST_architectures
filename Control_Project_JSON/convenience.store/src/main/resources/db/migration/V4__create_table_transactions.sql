CREATE TABLE Transactions (
    id BIGINT IDENTITY NOT NULL PRIMARY KEY,
    cpf VARCHAR(255) NOT NULL,
    product_id BIGINT NOT NULL,
    created_at DATETIME NOT NULL,
    CONSTRAINT fk_products_id FOREIGN KEY (product_id) REFERENCES Products(id)
);
