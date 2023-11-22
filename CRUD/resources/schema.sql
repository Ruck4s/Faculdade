create database crud_db;

create table usuarios (
id int not null auto_increment primary key,
nome varchar(45),
senha varchar(45),
email varchar(45),
cpf varchar(11),
telefone varchar(20)
)