create database crm;

CREATE TABLE crm.customers ( 
id int NOT NULL AUTO_INCREMENT, 
firstname varchar(255) DEFAULT NULL, 
lastname varchar(255) DEFAULT NULL, 
email varchar(255) DEFAULT NULL, 
PRIMARY KEY (id) );

select * from crm.customers;