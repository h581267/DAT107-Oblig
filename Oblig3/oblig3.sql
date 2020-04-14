DROP SCHEMA IF EXISTS oblig3 CASCADE;
CREATE SCHEMA oblig3;
SET search_path TO oblig3;

CREATE TABLE Avdeling 
(
	avdNr SERIAL, 
	navn varchar NOT NULL, 
	sjefId integer, 
	CONSTRAINT avdNrPK PRIMARY KEY (avdNr)
);

CREATE TABLE Ansatt 
(
	ansattId SERIAL PRIMARY KEY, 
	brukernavn varchar NOT NULL, 
	fornavn varchar NOT NULL, 
	etternavn varchar NOT NULL, 
	ansattdato date NOT NULL, 
	stilling varchar, 
	mndlonn DECIMAL NOT NULL,
	avdNr integer,
	prosjektnr integer,
	CONSTRAINT AvdelingFK FOREIGN KEY (avdNr) REFERENCES Avdeling(avdNr)
); 

ALTER TABLE Avdeling
ADD CONSTRAINT sjefFK FOREIGN KEY (sjefId) REFERENCES Ansatt(ansattId);


CREATE TABLE Prosjekt
(
	prosjektNr SERIAL,
	navn varchar NOT NULL,
	CONSTRAINT ProsjektPK PRIMARY KEY (prosjektNr)
);

CREATE TABLE Prosjektdeltagelse
(
ansattId INTEGER,
prosjektId INTEGER,
timer INTEGER,
roller varchar,
CONSTRAINT ProsjektdeltagelsePK PRIMARY KEY (ansattId, prosjektId),
CONSTRAINT AnsattFK FOREIGN KEY (ansattId) REFERENCES Ansatt(ansattId),
CONSTRAINT ProsjektFK FOREIGN KEY (prosjektId) REFERENCES Prosjekt(prosjektNr)
);

INSERT INTO Avdeling(Navn)
VALUES ('Service');

INSERT INTO Ansatt(fornavn, etternavn,brukernavn, ansattdato, stilling, mndlonn, avdNr)
VALUES('Mads', 'Hetlevik','MAHE', '2012-06-27','Servicesjef', 1400000, 1); 

UPDATE Avdeling SET sjefId = 1 WHERE avdNr = 1; 