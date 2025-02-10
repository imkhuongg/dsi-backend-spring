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
	product_id int,
    user_id int,
    transport_company_id int,
    shopper_id int,
    shipper_id int,
    quantity int,
    total decimal(10,2),
    user_address_id int,
    status nvarchar(50),
    created_at timestamp,
    updated_at timestamp,
    foreign key (product_id) references product(product_id) on delete cascade,
    foreign key (user_id) references users(user_id) on delete cascade,
	foreign key (transport_company_id) references transport_company(transport_company_id) on delete cascade,
    foreign key (shipper_id) references shipper(shipper_id ) on delete cascade,
    foreign key (user_address_id) references user_address(user_address_id)  on delete cascade
)

CREATE USER 'imkhuongg'@'localhost' IDENTIFIED BY '07042003';
GRANT ALL PRIVILEGES ON dsi_v1.* TO 'imkhuongg'@'localhost';