INSERT INTO usuarios(id,nombres,apellidos,clave,fecha_nacimiento,identificacion,sector)VALUES(1,'ninio','perez perez','12345','2010-10-10','123','salento');
INSERT INTO usuarios(id,nombres,apellidos,clave,fecha_nacimiento,identificacion,sector)VALUES(2,'adolescente','perez perez','12345','1995-10-27','1234','armenia');
INSERT INTO usuarios(id,nombres,apellidos,clave,fecha_nacimiento,identificacion,sector)VALUES(3,'adulto','perez perez','12345','1970-11-10','12345','calarca');
INSERT INTO usuarios(id,nombres,apellidos,clave,fecha_nacimiento,identificacion,sector)VALUES(4,'adulto mayor','perez perez','12345','1950-11-11','123456','tebaida');

INSERT INTO prestamos(id,estado,fecha_prestamo,sector,usuario_id)VALUES(1,true,'2020-05-10','salento',1);
INSERT INTO prestamos(id,estado,fecha_prestamo,sector,usuario_id)VALUES(2,true,'2020-05-09','armenia',2);
INSERT INTO prestamos(id,estado,fecha_prestamo,sector,usuario_id)VALUES(3,true,'2020-05-08','calarca',3);
INSERT INTO prestamos(id,estado,fecha_prestamo,sector,usuario_id)VALUES(4,true,'2020-05-07','tebaida',4);

INSERT INTO libros(id,nombre,autor,descripcion,disponibilidad,edad_necesaria,foto,prestamo_id)VALUES(1,'el libro de la selva','perez perez','este libro trata del libro de la selva',false,0,'https://leereslomejor.com/wp-content/uploads/2020/02/1-scaled.jpg',1);
INSERT INTO libros(id,nombre,autor,descripcion,disponibilidad,edad_necesaria,foto)VALUES(2,'nacho aprende java','perez perez','este libro trata de como nacho aprende java',true,0,'https://i.pinimg.com/originals/1a/74/f2/1a74f2e5879499f4105fe7d1f6779e16.jpg');
INSERT INTO libros(id,nombre,autor,descripcion,disponibilidad,edad_necesaria,foto,prestamo_id)VALUES(3,'el joven aprende sql','perez perez','este libro trata de sql',false,1,'https://images-eu.ssl-images-amazon.com/images/I/51U4SZh61jL.jpg',2);
INSERT INTO libros(id,nombre,autor,descripcion,disponibilidad,edad_necesaria,foto)VALUES(4,'nunca rendirse','perez perez','este libro trata de nunca rendirse',true,1,'https://images-na.ssl-images-amazon.com/images/I/71kOB-RFr%2BL.jpg');
INSERT INTO libros(id,nombre,autor,descripcion,disponibilidad,edad_necesaria,foto,prestamo_id)VALUES(5,'miss piggy','perez perez','este libro trata de  miss piggi',false,2,'https://horcrux1.beek.io/umC-qrb0WOzLiu9Nxuiqe-nDFJs=/264x394/smart/store/fd3e28b7e2a48aafd3757c0114989016e86ad49ca9da292df6d9308a8241',3);
INSERT INTO libros(id,nombre,autor,descripcion,disponibilidad,edad_necesaria,foto)VALUES(6,'entender a una mujer','perez perez','este libro trata de como entender a una mujer',true,2,'https://desmotivaciones.es/demots/201105/wikipedia2500x500.jpg');
INSERT INTO libros(id,nombre,autor,descripcion,disponibilidad,edad_necesaria,foto,prestamo_id)VALUES(7,'como ser un viejito','perez perez','este libro trata de como ser un viejito',false,3,'https://i.pinimg.com/originals/a2/30/b9/a230b9afc1e3742e3433b90a3de1ea06.jpg',4);
INSERT INTO libros(id,nombre,autor,descripcion,disponibilidad,edad_necesaria,foto)VALUES(8,'viejo pero no muerto','perez perez','este libro trata de como ser viejo pero no muerto',true,3,'https://i.pinimg.com/originals/05/1b/a0/051ba0131e6313d39fbc8534c5f497dd.jpg');
