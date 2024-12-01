CREATE DATABASE dsi_v1;
USE dsi_v1;

CREATE TABLE users(
    user_id INT NOT NULL AUTO_INCREMENT,
    first_name varchar(50) NOT NULL,
    last_name varchar(50) NOT NULL,
    email varchar(255) NOT NULL UNIQUE,
    password varchar(255) NOT NULL,
    created_at TIMESTAMP,
    updated_at DATETIME,
    PRIMARY KEY(user_id)
);

CREATE TABLE notes(
    note_id INT NOT NULL AUTO_INCREMENT,
    user_id INT NOT NULL,
    title varchar(255),
    body varchar(255),
    created_at TIMESTAMP,
    updated_at DATETIME,
    PRIMARY KEY(note_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

CREATE TABLE product(
    product_id INT NOT NULL AUTO_INCREMENT,
    name_product varchar(50),
    price DECIMAL(10,2),
    user_id INT NOT NULL,
    description TEXT,
    rate DOUBLE(10,2),
    name_brand varchar(50),
    thumb varchar(255),
    quantity_sold int,
    created_at TIMESTAMP,
    updated_at DATETIME,
    PRIMARY KEY (product_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);