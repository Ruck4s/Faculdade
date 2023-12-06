# Projeto A3 - Programação de Soluções Computacionais

Projeto envolvendo a construção de um sistema CRUD utilizando **Java** e um banco de dados **MySQL**.
O sistema é composto por:
- Login (Com email e senha).
- Cadastro.
- Gerenciamente de Usuários (Com os métodos de Listar, Editar e Excluir).
- Gerenciamente de Carros (Com os métodos de Criar, Listar, Editar e Excluir).
- Além de validações de CPF e Email repetidos.

## Observações:
- **Baixar [driver](https://dev.mysql.com/downloads/connector/j/) JDBC do MySQL, e adicionar na pasta "libraries" no NetBeans para ser realizado a conexão.**
- **Para acessar o banco, baixar o [WAMPSERVER](https://sourceforge.net/projects/wampserver/files/WampServer%203/WampServer%203.0.0/wampserver3.3.2_x64.exe/download).**
- **Projeto desenvolvido utilizando as ferramentas: WAMPSERVER, Beekeeper Studio, MySQL Workbench, Apache NetBeans.**
- **Para utilizar o sistema é necessário baixar o WAMPSERVER e MySQL connector. Além de criar o banco de dados crud_db.**
- **Baixar arquivos que estão na pasta utilitários.**

- SRIPT MYSQL:
- SCRIPT MYSQL:
```sql
CREATE DATABASE crud_db;

USE crud_db;

CREATE TABLE users (
    id VARCHAR(255) NOT NULL PRIMARY KEY,
    name TEXT,
    email TEXT UNIQUE,
    cpf TEXT UNIQUE,
    phone TEXT,
    password TEXT
);

CREATE TABLE cars (
    id VARCHAR(255) NOT NULL PRIMARY KEY,
    brand TEXT,
    model TEXT,
    version TEXT,
    car_condition TEXT
);
```


## Integrantes do grupo 13:

- Daniel Benício Matias de Araújo, RA 822160985.
- Gustavo Alexandre Ruckert, RA 822144341.
- João Pedro de Souza Silva, RA 821232761.
- Leonardo Hossokawa de Oliveira, RA 82215283.
- Sanndy Cristina Fogaça da Silva, RA 821113694.
