--liquibase formatted sql

--changeset arthur.juchem:1
CREATE TABLE users (
id BIGINT PRIMARY KEY,
name VARCHAR(45) NOT NULL
);

CREATE TABLE orders (
id BIGINT PRIMARY KEY,
user_id BIGINT NOT NULL,
CONSTRAINT fk_user
      FOREIGN KEY(user_id)
        REFERENCES users(id)
);

CREATE TABLE products (
id BIGINT PRIMARY KEY,
order_id BIGINT NOT NULL,
value DECIMAL(10, 2) NOT NULL,
CONSTRAINT fk_order
      FOREIGN KEY(order_id)
        REFERENCES orders(id)
);
