--liquibase formatted sql

--changeset arthur.juchem:2
ALTER TABLE orders
ADD total_value DECIMAL(10, 2) NOT NULL;

ALTER TABLE orders
ADD purchase_date DATE NOT NULL;
