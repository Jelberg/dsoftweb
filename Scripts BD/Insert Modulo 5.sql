	CREATE TABLE ACTIVITY(

	ACTIVITYID			 INTEGER NOT NULL PRIMARY KEY,
	ACTIVITYSTARTTIME	 TIME,
	ACTIVITYENDTIME		 TIME,
	ACTIVITYDATE		 DATE,
	ACTIVITYKM			 NUMERIC NOT NULL,
	ACTIVITYCALOR		 NUMERIC,
	ACTIVITYSTARTSITE	 VARCHAR(200),
	ACTIVITYENDSITE		 VARCHAR(200),
	FK_SPORT			INTEGER,
	FK_REGISTRY	 INTEGER,
	FK_TRAINING	INTEGER
);

INSERT INTO SPORT (SPORTID,SPORTNAME,SPORTMET,SPORTTYPE) VALUES (nextval('SPORTID'),'CAMINAR',3.3,'T');
INSERT INTO SPORT (SPORTID,SPORTNAME,SPORTMET,SPORTTYPE) VALUES (nextval('SPORTID'),'TROTAR',7,'T');
INSERT INTO SPORT (SPORTID,SPORTNAME,SPORTMET,SPORTTYPE) VALUES (nextval('SPORTID'),'BICICLETA',8,'F');
INSERT INTO SPORT (SPORTID,SPORTNAME,SPORTMET,SPORTTYPE) VALUES (nextval('SPORTID'),'NATACION',8.5,'F');
INSERT INTO SPORT (SPORTID,SPORTNAME,SPORTMET,SPORTTYPE) VALUES (nextval('SPORTID'),'YOGA',2.5,'F');
INSERT INTO SPORT (SPORTID,SPORTNAME,SPORTMET,SPORTTYPE) VALUES (nextval('SPORTID'),'ESTIRAMIENTOS',2.5,'F');
INSERT INTO SPORT (SPORTID,SPORTNAME,SPORTMET,SPORTTYPE) VALUES (nextval('SPORTID'),'ELIPTICA',7,'F');
INSERT INTO SPORT (SPORTID,SPORTNAME,SPORTMET,SPORTTYPE) VALUES (nextval('SPORTID'),'ESCALERAS',9,'F');
INSERT INTO SPORT (SPORTID,SPORTNAME,SPORTMET,SPORTTYPE) VALUES (nextval('SPORTID'),'BAILAR',5,'F');
INSERT INTO SPORT (SPORTID,SPORTNAME,SPORTMET,SPORTTYPE) VALUES (nextval('SPORTID'),'AEROBIC',8.5,'F');
INSERT INTO SPORT (SPORTID,SPORTNAME,SPORTMET,SPORTTYPE) VALUES (nextval('SPORTID'),'REMO',8,'F');
INSERT INTO SPORT (SPORTID,SPORTNAME,SPORTMET,SPORTTYPE) VALUES (nextval('SPORTID'),'BASKETBALL',6,'F');
INSERT INTO SPORT (SPORTID,SPORTNAME,SPORTMET,SPORTTYPE) VALUES (nextval('SPORTID'),'FUTBOL',6.5,'F');
INSERT INTO SPORT (SPORTID,SPORTNAME,SPORTMET,SPORTTYPE) VALUES (nextval('SPORTID'),'TENIS',5,'F');
INSERT INTO SPORT (SPORTID,SPORTNAME,SPORTMET,SPORTTYPE) VALUES (nextval('SPORTID'),'VOLEIBOL',6.5,'F');