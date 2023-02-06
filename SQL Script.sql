create database systemregister;

use systemregister;

create table typeClient(
id int not null auto_increment,
type varchar(60) default null,
primary key(id)
);

create table client(
id int not null auto_increment,
name varchar(100) not null,
rg int(20) not null,
cpf int(20) not null,
birthDate datetime not null,
idTypeClient int not null,
primary key(id),
foreign key (idTypeClient) references typeClient(id)
);


insert into typeClient (type) values ('Pessoa Física');
insert into client (name, rg, cpf, birthDate, idTypeClient) values ('Gustavo F', 123456, 123456, '2000-08-17 00:00:00', 1)