CREATE DATABASE examinerdb;
USE examinerdb;

CREATE TABLE usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome varchar (60),
    senha VARCHAR(255) NOT NULL,
    RGM int(10) NOT NULL UNIQUE
);

CREATE TABLE Prova (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome_prova VARCHAR(255) UNIQUE,
    nota int
);

select * from Prova;

select * from usuario;

SET SQL_SAFE_UPDATES = 0;



