

-- BUSCA EL ID DEL DEPORTE A TRAVES DE SU NOMBRE
-- FUE CREADO PARA OBTENER EL ID DEL DEPORTE ANTES DE INSERTAR

CREATE OR REPLACE FUNCTION obtenerIdDeporte (nombre VARCHAR(200)) RETURNS INTEGER AS 
$$ 
DECLARE
 resultado INTEGER;
BEGIN 

  SELECT SPORTID INTO  resultado
  FROM  SPORT
  WHERE SPORTNAME=nombre;
  return(resultado);
  
END; 
$$
LANGUAGE 'plpgsql' STABLE;

-- BUSCA EL MET DEL DEPORTE EN FUNCION DEL NOMBRE
CREATE OR REPLACE FUNCTION obtenerMetDeporte (nombre VARCHAR(200)) RETURNS FLOAT AS 
$$ 
DECLARE
 resultado FLOAT;
BEGIN 

  SELECT SPORTMET INTO  resultado
  FROM  SPORT
  WHERE SPORTNAME=nombre;
  return(resultado);
  
END; 
$$
LANGUAGE 'plpgsql' STABLE;



-- INSERTA EL ID DEL USUARIO Y EL ID DEL DEPORTE PARA HABILITARLO 

CREATE OR REPLACE FUNCTION insertarDeporte(usuario INTEGER ,deporte INTEGER) RETURNS BOOLEAN AS $$
BEGIN
	 INSERT INTO EXERCISE (EXERCISEID,FK_PERSON,FK_SPORT) VALUES (nextval('EXERCISEID'),usuario,deporte);
	 
	 RETURN TRUE;
	 
END
$$
LANGUAGE 'plpgsql' VOLATILE; 


-- CARGA TODOS LOS DEPORTES DE UN USUARIO A TRAVES DEL ID DEL DEPORTE Y DEL PROPIO

CREATE OR REPLACE FUNCTION obtenerDeportesUsuario (usuario INTEGER) RETURNS VARCHAR(200) AS 
$$ 
DECLARE
 resultado VARCHAR;
BEGIN 

  SELECT SPORTNAME INTO resultado
  FROM   EXERCISE,SPORT,PERSON
  WHERE  FK_USUARIO = usuario AND 
		 FK_SPORT = SPORTID;
  return(resultado);
  
END; 
$$
LANGUAGE 'plpgsql' STABLE;

-- Elimina deportes registrados

CREATE OR REPLACE FUNCTION eliminarDeporte (usuario INTEGER ,deporte INTEGER) RETURNS BOOLEAN AS $$
BEGIN
	 DELETE FROM EXERCISE WHERE fk_usuario=usuario and fk_sport=deporte;
	 
	 RETURN TRUE;
	 
END
$$
LANGUAGE 'plpgsql' VOLATILE; 



-- INSERTA LOS DATOS PARA EL REGISTROS DE LAS ACTIVIDADES

CREATE OR REPLACE FUNCTION insertarActividad (horainicio TIME, horafinal TIME, fecha DATE, km NUMERIC, caloria NUMERIC, lugarinicio VARCHAR(200),lugarfinal VARCHAR(200) ) RETURNS BOOLEAN AS $$
BEGIN
	 INSERT INTO  ACTIVITY (ACTIVITYID,ACTIVITYSTARTTIME, ACTIVITYENDTIME,ACTIVITYDATE,ACTIVITYKM ,ACTIVITYCALOR,ACTIVITYSTARTSITE,ACTIVITYENDSITE) VALUES (nextval('ACTIVITYID'),
                    horainicio,horafinal,fecha,km,caloria,lugarinicio,lugarfinal) ;
	 
	 RETURN TRUE;
	 
END
$$
LANGUAGE 'plpgsql' VOLATILE; 

-- Modificar ACTIVIDAD--->KM

CREATE OR REPLACE FUNCTION modificarActividad (usuario INTEGER,km NUMERIC) RETURNS BOOLEAN AS $$
BEGIN
	 UPDATE ACTIVITY SET ACTIVITYKM=km
	 WHERE  FK_PERSON=usuario;
	 RETURN TRUE;
	 
END
$$
LANGUAGE 'plpgsql' VOLATILE; 
-- DUDA : CUANDO MODIFICAS LOS KM DEBERIAS MODIFICAR TAMBIEN LAS CALORIAS NO? OSEA DEBERIAN RECALCULARSE
