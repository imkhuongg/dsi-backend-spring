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
create table shopper(
	shopper_id int auto_increment,
	user_id int not null,
    name_shop nvarchar(100) not null,
    email varchar(255) unique not null,
    avatar varchar(255),
    phone int not null,
    shop_address nvarchar(255) not null,
    primary key (shopper_id),
	foreign key (user_id) references users(user_id) on delete cascade

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
    shopper_id int,
    created_at TIMESTAMP,
    updated_at DATETIME,
    PRIMARY KEY (product_id),
    foreign key (shopper_id) references shopper(shopper_id) on delete cascade,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);
create table transport_company(
	transport_company_id int auto_increment,
    transport_company_name nvarchar(50) not null,
    transport_company_taxCode varchar(20) not null,
    transport_company_address nvarchar(100) not null,
    transport_company_logo varchar(255),
    transport_company_email varchar(255),
    transport_company_phoneNum int,
    primary key (transport_company_id)
);

create table shipper(
	shipper_id int auto_increment,
    email varchar(255) not null,
    password varchar(255) not null,
    phone int not null,
    address varchar(255),
    birth timestamp,
    transport_company_id int,
    primary key (shipper_id),
    foreign key (transport_company_id) references transport_company(transport_company_id) on delete cascade
);

create table user_address(
	user_address_id int auto_increment,
	user_id int,
    adress text,
    foreign key (user_id) references users(user_id) on delete cascade,
    primary key (user_address_id)
);

create table cart(
	cart_id int not null primary key auto_increment,
    user_id int,
    created_at timestamp,
    updated_at timestamp,
    foreign key (user_id) references users(user_id) on delete cascade
);
CREATE TABLE cartItem (
    cartItem_id INT PRIMARY KEY AUTO_INCREMENT,
    cart_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL CHECK (quantity > 0),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (cart_id) REFERENCES cart(cart_id) on delete cascade,
    FOREIGN KEY (product_id) REFERENCES product(product_id) on delete cascade
);

CREATE TABLE orders (
    order_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    user_address_id INT NOT NULL,
    order_number VARCHAR(50) NOT NULL UNIQUE,
    total_price DECIMAL(10,2) NOT NULL,
    status ENUM('Pending', 'Processing', 'Completed', 'Cancelled') DEFAULT 'Pending',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id) on delete cascade,
    FOREIGN KEY (user_address_id) REFERENCES user_address(user_address_id) on delete cascade
);

CREATE TABLE orderItem (
    orderItem_id INT PRIMARY KEY AUTO_INCREMENT,
    order_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL CHECK (quantity > 0),
    price DECIMAL(10,2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (order_id) REFERENCES orders(order_id) on delete cascade,
    FOREIGN KEY (product_id) REFERENCES product(product_id) on delete cascade
);
CREATE TABLE Shipping (
    shipping_id INT PRIMARY KEY AUTO_INCREMENT,
    order_id INT NOT NULL,
    shipper_id INT NOT NULL,
    transport_company_id INT NOT NULL,
    shipping_status ENUM('Pending', 'In Transit', 'Delivered', 'Cancelled') DEFAULT 'Pending',
    estimated_delivery DATE,
    shipped_at TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE CASCADE,
    FOREIGN KEY (shipper_id) REFERENCES shipper(shipper_id) ON DELETE CASCADE,
    FOREIGN KEY (transport_company_id) REFERENCES Transport_Company(transport_company_id) ON DELETE CASCADE
);



CREATE USER 'imkhuongg'@'localhost' IDENTIFIED BY '07042003';
GRANT ALL PRIVILEGES ON dsi_v1.* TO 'imkhuongg'@'localhost';