
DROP TABLE IF EXISTS customer;
CREATE TABLE customer (
  id int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  surname varchar(45) NOT NULL,
  email varchar(45) NOT NULL,
  address varchar(45) NOT NULL,
  age int(11) DEFAULT NULL,
  phone_number varchar(45) DEFAULT NULL
);

DROP TABLE IF EXISTS merchant;
CREATE TABLE merchant (
  id int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  email varchar(45) NOT NULL,
  address varchar(45) NOT NULL
);

DROP TABLE IF EXISTS product;
CREATE TABLE product (
  id int(11) NOT NULL AUTO_INCREMENT,
  merchant_id int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `description` varchar(80) NOT NULL,
  price double NOT NULL,
  created_at datetime NOT NULL,
  available int(11) NOT NULL
);

DROP TABLE IF EXISTS bank_account;
CREATE TABLE bank_account (
  id int(11) NOT NULL AUTO_INCREMENT,
  customer_id int(11) NOT NULL,
   `bank_account_number` varchar(45) NOT NULL,
  `account_balance` varchar(45) NOT NULL,
  created_at datetime NOT NULL
);

DROP TABLE IF EXISTS customer_account;
CREATE TABLE customer_account (
  id int(11) NOT NULL AUTO_INCREMENT,
  customer_id int(11) NOT NULL,
  money double NOT NULL
);

DROP TABLE IF EXISTS bought_product;
CREATE TABLE bought_product (
  id int(11) NOT NULL AUTO_INCREMENT,
  product_id int(11) NOT NULL,
  customer_id int(11) NOT NULL,
  quantity int(11) NOT NULL,
  bought_at datetime NOT NULL

);