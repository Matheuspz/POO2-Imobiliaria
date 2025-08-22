CREATE DATABASE IF NOT EXISTS `imobiliaria`;
USE `imobiliaria`;

CREATE TABLE IF NOT EXISTS `CLIENTE`(
idCliente INT auto_increment primary KEY,
nome Varchar(255),
email varchar(255),
CPF varchar(255)
);

CREATE TABLE IF NOT EXISTS `IMOVEL`(
idImovel int auto_increment primary key,
endereco varchar(255),
qtdComodos int,
disponivel boolean
);

CREATE TABLE IF NOT exists `CONTRATO`(
idContrato int auto_increment primary key, 
idCliente int,
idImovel int,
valor decimal(10,2),
dataInicio datetime,
dataFim datetime,
foreign key (idCliente) references CLIENTE(idCliente),
foreign key (idImovel) references IMOVEL(idImovel)
);

INSERT INTO CLIENTE (nome, email, CPF)
VALUES ('João Silva', 'joao.silva@example.com', '123.456.789-00');
INSERT INTO IMOVEL (endereco, qtdComodos, disponivel)
VALUES ('Rua das Flores, 123', 4, TRUE);
INSERT INTO CONTRATO (idCliente, idImovel, valor, dataInicio, dataFim)
VALUES (1, 1, 1350.00, '2025-08-10 15:00:00', '2025-08-30 12:00:00');


