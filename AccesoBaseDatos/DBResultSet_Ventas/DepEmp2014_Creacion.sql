create database depemp;
use depemp;
create table departamentos
( idDep int primary key
, ciudad varchar(10)
, objetivoAnual numeric(8,2)
);

create table empleados
( idEmp int primary key
, nombre  varchar(10)
, apellido varchar(20)
, feAlta date
, sueldo  numeric(6,2)
, totalVentas  numeric(6,2)
, idDep int references departamentos
);

create table proveedores
( idPro int primary key
, nombre varchar(10)
, ciudad varchar(10)
);


create table articulos
( idArt int primary key
, nombre varchar(10)
, existencias int
, ppu numeric(5,2)
, idPro int references proveedores
);

create table ventas
( idEmp int references empleados
, idArt int references articulos
, unidades int
, fecha date
, constraint pk_ventas primary key (idEmp,idArt,fecha)
);


insert into departamentos values (1,'granada', 125.45);
insert into departamentos values (2,'malaga', 1250.45);
insert into departamentos values (3,'jaen', 250);
insert into departamentos values (4,'cordoba', 300.45);

insert into empleados (idEmp,nombre,apellido,feAlta,sueldo,Totalventas,idDep)
					values (1,'pepe','lopez','1997-01-01',1500,3000,1);
insert into empleados (idEmp,nombre,apellido,feAlta,sueldo,Totalventas,idDep)
					values (2,'luis','perez','1997-02-02',1300,null,1);
insert into empleados (idEmp,nombre,apellido,feAlta,sueldo,Totalventas,idDep)
					values (3,'juan','garcia','1997-03-03',2000,7000,2);
insert into empleados (idEmp,nombre,apellido,feAlta,sueldo,Totalventas,idDep)
					values (4,'eva','pozo','1997-04-04',1900,null,2);
insert into empleados (idEmp,nombre,apellido,feAlta,sueldo,Totalventas,idDep)
					values (5,'lola','haro','1997-05-05',1200,2000,3);
insert into empleados (idEmp,nombre,apellido,feAlta,sueldo,Totalventas,idDep)
					values (6,'javier','lopez','1997-06-06',1800,3000,3);
insert into empleados (idEmp,nombre,apellido,feAlta,sueldo,Totalventas,idDep)
					values (7,'antonio','arenas','1997-07-07',1400,2500,4);
insert into empleados (idEmp,nombre,apellido,feAlta,sueldo,Totalventas,idDep)
				values (8,'jose','blanco','1997-08-08',1500,null,4);
insert into empleados (idEmp,nombre,apellido,feAlta,sueldo,Totalventas,idDep)
					values (9,'lola','guerra','1997-09-09',1500,3000,1);
                    
                   

insert into proveedores values (1,'pepe','granada');
insert into proveedores values (2,'luis','jaen');
insert into proveedores values (3,'juan','granada');

insert into articulos (idArt,nombre,ppu,idPro) values (1,'silla',30,1);
insert into articulos (idArt,nombre,ppu,idPro) values (2,'mesa',70,1);
insert into articulos (idArt,nombre,ppu,idPro) values (3,'mesilla',50,1);
insert into articulos (idArt,nombre,ppu,idPro) values (4,'ropero',70,2);
insert into articulos (idArt,nombre,ppu,idPro) values (5,'percha',35,2);
insert into articulos (idArt,nombre,ppu,idPro) values (6,'armario',80,3);
insert into articulos (idArt,nombre,ppu,idPro) values (7,'estanteria',70,3);


insert into ventas (idEmp, idArt, unidades, fecha) values (1,1,6,'2007-10-10');
insert into ventas (idEmp, idArt, unidades, fecha) values (1,3,1,'2007-10-10');
insert into ventas (idEmp, idArt, unidades, fecha) values (2,6,1,'2007-12-10');
insert into ventas (idEmp, idArt, unidades, fecha) values (3,7,2,'2007-10-15');
insert into ventas (idEmp, idArt, unidades, fecha) values (3,1,2,'2007-10-25');
insert into ventas (idEmp, idArt, unidades, fecha) values (1,4,1,'2007-05-11');
insert into ventas (idEmp, idArt, unidades, fecha) values (4,4,2,'2007-11-15');
insert into ventas (idEmp, idArt, unidades, fecha) values (7,1,6,'2007-11-17');
insert into ventas (idEmp, idArt, unidades, fecha) values (9,3,2,'2007-11-17');
insert into ventas (idEmp, idArt, unidades, fecha) values (8,6,2,'2007-05-12');
insert into ventas (idEmp, idArt, unidades, fecha) values (8,1,6,'2007-10-11');





