-- drop database if exists guitar_db;
-- create database guitar_db;
-- use guitar_db;


create table guitars(
guitar_id INT not null auto_increment,
Make_Model VARCHAR(40) not null,
Style VARCHAR(20),
Nickname varchar(20),
 
primary key(guitar_id)
);


