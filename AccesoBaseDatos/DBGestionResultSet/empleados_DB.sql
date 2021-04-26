create database PruebaUno;

use pruebaUno;

create table empleado
( idEmp int primary key
, nombre varchar(20)
, sueldo numeric(6,2)
);

insert into empleado(idemp, nombre, sueldo) values (1,'pepe',1200.20);
