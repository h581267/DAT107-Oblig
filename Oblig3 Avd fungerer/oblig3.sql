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
	prosjektId SERIAL,
	navn varchar NOT NULL,
	CONSTRAINT ProsjektPK PRIMARY KEY (prosjektId)
);

CREATE TABLE Prosjektdeltagelse
(
  AnsattId INTEGER,
  ProsjektId INTEGER,
  Timer     INTEGER,
  CONSTRAINT ProsjektdeltagelsePK PRIMARY KEY (AnsattId, ProsjektId),
  CONSTRAINT AnsattFK FOREIGN KEY (AnsattId) REFERENCES Ansatt(ansattId),
  CONSTRAINT ProsjektFK FOREIGN KEY (ProsjektId) REFERENCES Prosjekt(prosjektId)  
);

INSERT INTO Avdeling(Navn)
VALUES ('Salg');

INSERT INTO Ansatt(fornavn, etternavn,brukernavn,stilling, ansattdato, mndlonn, avdNr)
VALUES('Petter', 'Olsen','PEOL','Salgssjef', '2012-06-27', 1400000, 1); 

UPDATE Avdeling SET sjefId = 1 WHERE avdNr = 1; 