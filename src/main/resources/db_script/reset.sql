USE sks;

DROP TABLE oving_brukere;
DROP TABLE oving;
DROP TABLE delemne_brukere;
DROP TABLE delemne;
DROP TABLE koe_brukere;
DROP TABLE emner_brukere;
DROP TABLE brukere;
DROP TABLE koe;
DROP TABLE emne;
DROP TABLE rettighet;


CREATE TABLE rettighet(
	rettighet_id                 INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	navn                         VARCHAR(255) NOT NULL
	) ENGINE = InnoDB;

CREATE TABLE emne(
        emnekode                 VARCHAR(255) NOT NULL PRIMARY KEY,
        emnenavn                 VARCHAR(255)
	)ENGINE = InnoDB;

CREATE TABLE koe(
        koe_id                        INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
        aapen                        TINYINT NOT NULL
)ENGINE = InnoDB;

CREATE TABLE brukere(
	      mail                        VARCHAR(255) NOT NULL PRIMARY KEY,
        rettighet_id                 INT NOT NULL,
        fornavn                 VARCHAR(255) NOT NULL,
        etternavn                 VARCHAR(255) NOT NULL,
        passord                 VARCHAR(32) NOT NULL,
        aktiv                         TINYINT NOT NULL
	)ENGINE = InnoDB;

CREATE TABLE emner_brukere(
        emnekode                VARCHAR(255) NOT NULL,
        mail                        VARCHAR(255) NOT NULL,
        foreleser                   TINYINT NOT NULL,
        CONSTRAINT emner_brukere_pk PRIMARY KEY(emnekode, mail)
	)ENGINE = InnoDB;

CREATE TABLE plassering(
        plassering_navn                  VARCHAR(255) NOT NULL PRIMARY KEY,
        ant_bord                        INT
)ENGINE = InnoDB;

CREATE TABLE koe_brukere(
        koe_id                        INT NOT NULL AUTO_INCREMENT,
        mail                        VARCHAR(255) NOT NULL,
        plassering_navn                VARCHAR(255) NOT NULL,
        bordnummer                INT NOT NULL,
        ovingsnummer                VARCHAR(30),
        info                    VARCHAR(255),
        koe_plass                INT NOT NULL,
        CONSTRAINT koe_brukere_pk PRIMARY KEY(koe_id, mail)
	)ENGINE = InnoDB;

CREATE TABLE delemne(
        delemne_nr                 INT NOT NULL,
        emnekode                VARCHAR(255) NOT NULL,
        koe_id                        INT NOT NULL,
        delemnenavn                VARCHAR(255) NOT NULL,
        semester                VARCHAR(1) NOT NULL,
        ant_ovinger                INT,
        ovingsregler                VARCHAR(225),
        CONSTRAINT pk_delemne PRIMARY KEY(delemne_nr, emnekode)
	)ENGINE = InnoDB;

CREATE TABLE delemne_brukere(
        mail                        VARCHAR(255) NOT NULL,
        emnekode                VARCHAR(255) NOT NULL,
        delemne_nr                INT NOT NULL,
	CONSTRAINT pk_delemne_brukere PRIMARY KEY(mail, emnekode, delemne_nr)
	)ENGINE = InnoDB;

CREATE TABLE oving(
        oving_id                INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
        oving_nr                INT NOT NULL,
        emnekode                VARCHAR(255) NOT NULL,
        delemne_nr                INT NOT NULL
	)ENGINE = InnoDB;

CREATE TABLE oving_brukere(
        oving_id                INT NOT NULL,
        mail                        VARCHAR(255) NOT NULL,
        godkjent                TINYINT NOT NULL,
        godkjent_av                VARCHAR(255),
        godkjent_tid                TIMESTAMP
	)ENGINE = InnoDB;

ALTER TABLE koe_brukere ADD CONSTRAINT koe_brukere_fk1 FOREIGN KEY(mail) REFERENCES brukere (mail);
ALTER TABLE koe_brukere ADD CONSTRAINT koe_brukere_fk2 FOREIGN KEY(koe_id) REFERENCES koe(koe_id);
ALTER TABLE koe_brukere ADD CONSTRAINT koe_brukere_fk3 FOREIGN KEY(plassering_navn) REFERENCES plassering (plassering_navn);

ALTER TABLE emner_brukere ADD CONSTRAINT emner_brukere_fk1 FOREIGN KEY(emnekode) REFERENCES emne(emnekode);
ALTER TABLE emner_brukere ADD CONSTRAINT emner_brukere_fk2 FOREIGN KEY(mail) REFERENCES brukere(mail);

ALTER TABLE delemne_brukere ADD CONSTRAINT delemne_brukere_fk1 FOREIGN KEY(mail) REFERENCES brukere(mail);
ALTER TABLE delemne_brukere ADD CONSTRAINT delemne_brukere_fk2 FOREIGN KEY(delemne_nr, emnekode) REFERENCES delemne(delemne_nr, emnekode);

ALTER TABLE brukere ADD CONSTRAINT brukere_fk1 FOREIGN KEY(rettighet_id) REFERENCES rettighet(rettighet_id);

ALTER TABLE oving_brukere ADD CONSTRAINT oving_brukere_fk1 FOREIGN KEY(oving_id) REFERENCES oving(oving_id);
ALTER TABLE oving_brukere ADD CONSTRAINT oving_brukere_fk2 FOREIGN KEY(mail) REFERENCES brukere(mail);

ALTER TABLE oving ADD CONSTRAINT oving_fk1 FOREIGN KEY(delemne_nr,emnekode) REFERENCES delemne(delemne_nr, emnekode);

ALTER TABLE delemne ADD CONSTRAINT delemne_fk1 FOREIGN KEY(emnekode) REFERENCES emne(emnekode);
ALTER TABLE delemne ADD CONSTRAINT delemne_fk2 FOREIGN KEY(koe_id) REFERENCES koe(koe_id);

INSERT INTO rettighet (navn) VALUES ('Admin');
INSERT INTO rettighet (navn) VALUES ('Foreleser');
INSERT INTO rettighet (navn) VALUES ('Student');

INSERT INTO brukere (mail, rettighet_id, fornavn, etternavn, passord, aktiv) VALUES ('gm@mail.com' , 1, 'Geir Morten', 'Larsen', 'eaaseQasexaseaaseQasexaseaaseQas',1);
INSERT INTO brukere (mail, rettighet_id, fornavn, etternavn, passord, aktiv) VALUES ('tk@mail.com' , 1, 'Ted Johan', 'Kristoffersen', 'eaaseQasexaseaaseQasexaseaaseQas',1);
INSERT INTO brukere (mail, rettighet_id, fornavn, etternavn, passord, aktiv) VALUES ('js@mail.com' , 1, 'Jørgen Lien', 'Sellæg', 'eaaseQasexaseaaseQasexaseaaseQas',1);
INSERT INTO brukere (mail, rettighet_id, fornavn, etternavn, passord, aktiv) VALUES ('ob@mail.com' , 1, 'Olve Andre', 'Børmark', 'eaaseQasexaseaaseQasexaseaaseQas',1);
INSERT INTO brukere (mail, rettighet_id, fornavn, etternavn, passord, aktiv) VALUES ('ts@mail.com' , 1, 'Thomas', 'Sve', 'eaaseQasexaseaaseQasexaseaaseQas',1);
INSERT INTO brukere (mail, rettighet_id, fornavn, etternavn, passord, aktiv) VALUES ('hb@mail.com' , 1, 'Henriette', 'Berg', 'eaaseQasexaseaaseQasexaseaaseQas',1);
INSERT INTO brukere (mail, rettighet_id, fornavn, etternavn, passord, aktiv) VALUES ('og@mail.com' , 1, 'Øyvind', 'Grimstad', 'eaaseQasexaseaaseQasexaseaaseQas',1);
INSERT INTO brukere (mail, rettighet_id, fornavn, etternavn, passord, aktiv) VALUES ('km@mail.com' , 1, 'Ketta', 'Mykkelgjerd', 'eaaseQasexaseaaseQasexaseaaseQas',1);
INSERT INTO brukere (mail, rettighet_id, fornavn, etternavn, passord, aktiv) VALUES ('eo@mail.com' , 1, 'Eirik', 'Øvstedal', 'eaaseQasexaseaaseQasexaseaaseQas',0);
INSERT INTO brukere (mail, rettighet_id, fornavn, etternavn, passord, aktiv) VALUES ('grethe@mail.com' , 2, 'Grethe', 'Sandstrak', 'eaaseQasexaseaaseQasexaseaaseQas',1);
INSERT INTO brukere (mail, rettighet_id, fornavn, etternavn, passord, aktiv) VALUES ('anette@mail.com' , 3, 'Anette', 'Wrlsen', 'eaaseQasexaseaaseQasexaseaaseQas',1);

INSERT INTO emne (emnekode, emnenavn) VALUES ('TDAT2001-A', 'Realfag for dataingeniører');
INSERT INTO emne (emnekode, emnenavn) VALUES ('TDAT2002-A', 'Matematikk 2');
INSERT INTO emne (emnekode, emnenavn) VALUES ('TDAT2003-A', 'Systemutvikling 2 med web-applikasjoner');
INSERT INTO emne (emnekode, emnenavn) VALUES ('TDAT2004-A', 'Datakommunikasjon med nettverksprogrammering');
INSERT INTO emne (emnekode, emnenavn) VALUES ('TDAT2005-A', 'Algoritmer ig datastrukturer');
INSERT INTO emne (emnekode, emnenavn) VALUES ('TDAT3018-A', 'Internasjonalt emne');
INSERT INTO emne (emnekode, emnenavn) VALUES ('TDAT1003-A', 'Datateknikk og operativsystem');
INSERT INTO emne (emnekode, emnenavn) VALUES ('TDAT1001-A', 'Programmering grunnkurs');
INSERT INTO emne (emnekode, emnenavn) VALUES ('IINI3006', 'HTML5');
INSERT INTO emne (emnekode, emnenavn) VALUES ('ALM800F-B', 'Fysikk');
INSERT INTO emne (emnekode, emnenavn) VALUES ('ALM801F-A', 'Engelsk');
INSERT INTO emne (emnekode, emnenavn) VALUES ('ALM802F-B', 'Matematikk');
INSERT INTO emne (emnekode, emnenavn) VALUES ('ALM803F-A', 'Norsk');
INSERT INTO emne (emnekode, emnenavn) VALUES ('ALM805F-A', 'Teknologi og samfunn');

INSERT INTO koe (aapen) VALUES (1);
INSERT INTO koe (aapen) VALUES (1);
INSERT INTO koe (aapen) VALUES (1);
INSERT INTO koe (aapen) VALUES (1);
INSERT INTO koe (aapen) VALUES (1);
INSERT INTO koe (aapen) VALUES (1);
INSERT INTO koe (aapen) VALUES (1);
INSERT INTO koe (aapen) VALUES (1);
INSERT INTO koe (aapen) VALUES (1);
INSERT INTO koe (aapen) VALUES (1);
INSERT INTO koe (aapen) VALUES (1);
INSERT INTO koe (aapen) VALUES (1);
INSERT INTO koe (aapen) VALUES (1);
INSERT INTO koe (aapen) VALUES (1);
INSERT INTO koe (aapen) VALUES (1);
INSERT INTO koe (aapen) VALUES (1);
INSERT INTO koe (aapen) VALUES (1);
INSERT INTO koe (aapen) VALUES (1);
INSERT INTO koe (aapen) VALUES (1);
INSERT INTO koe (aapen) VALUES (1);
INSERT INTO koe (aapen) VALUES (1);
INSERT INTO koe (aapen) VALUES (0);
INSERT INTO koe (aapen) VALUES (0);
INSERT INTO koe (aapen) VALUES (0);
INSERT INTO koe (aapen) VALUES (0);
INSERT INTO koe (aapen) VALUES (0);
INSERT INTO koe (aapen) VALUES (0);
INSERT INTO koe (aapen) VALUES (0);
INSERT INTO koe (aapen) VALUES (0);
INSERT INTO koe (aapen) VALUES (0);
INSERT INTO koe (aapen) VALUES (0);
INSERT INTO koe (aapen) VALUES (0);
INSERT INTO koe (aapen) VALUES (0);
INSERT INTO koe (aapen) VALUES (0);
INSERT INTO koe (aapen) VALUES (0);
INSERT INTO koe (aapen) VALUES (0);
INSERT INTO koe (aapen) VALUES (0);
INSERT INTO koe (aapen) VALUES (0);
INSERT INTO koe (aapen) VALUES (0);
INSERT INTO koe (aapen) VALUES (0);
INSERT INTO koe (aapen) VALUES (0);
INSERT INTO koe (aapen) VALUES (0);

INSERT INTO delemne (delemne_nr, emnekode, koe_id, delemnenavn, semester, ant_ovinger, ovingsregler) VALUES(1 , 'ALM805F-A', 1, 'Politikk' ,'H', '10', '1-5:2,6-10:4,1-10:7,6-7:2,2-2:1' );
INSERT INTO delemne (delemne_nr, emnekode, koe_id, delemnenavn, semester, ant_ovinger, ovingsregler) VALUES(2 , 'ALM805F-A', 2, 'Kjemi' ,'H', '10', '1-10:8' );
INSERT INTO delemne (delemne_nr, emnekode, koe_id, delemnenavn, semester, ant_ovinger, ovingsregler) VALUES(3 , 'ALM805F-A', 3, 'Økonomi' ,'V', '10', '' );
INSERT INTO delemne (delemne_nr, emnekode, koe_id, delemnenavn, semester, ant_ovinger, ovingsregler) VALUES(4 , 'ALM805F-A', 4, 'Etikk' ,'V', '10', '2-2:1,5-5:1,8-8:1' );
INSERT INTO delemne (delemne_nr, emnekode, koe_id, delemnenavn, semester, ant_ovinger, ovingsregler) VALUES(1 , 'TDAT2005-A', 5, 'Algoritmer og datastrukturer' ,'H', '10', '1-6:6' );
INSERT INTO delemne (delemne_nr, emnekode, koe_id, delemnenavn, semester, ant_ovinger, ovingsregler) VALUES(1 , 'TDAT2003-A', 6, 'Systemutvikling 2 med web-applikasjoner' ,'H', '10', '1-6:3' );

INSERT INTO emner_brukere (emnekode, mail, foreleser) VALUES ('ALM805F-A', 'gm@mail.com' ,0);
INSERT INTO emner_brukere (emnekode, mail, foreleser) VALUES ('TDAT2002-A', 'gm@mail.com',0);
INSERT INTO emner_brukere (emnekode, mail, foreleser) VALUES ('TDAT2003-A', 'gm@mail.com',0);
INSERT INTO emner_brukere (emnekode, mail, foreleser) VALUES ('TDAT2004-A', 'gm@mail.com',0);
INSERT INTO emner_brukere (emnekode, mail, foreleser) VALUES ('TDAT1003-A', 'gm@mail.com',0);
INSERT INTO emner_brukere (emnekode, mail, foreleser) VALUES ('TDAT1001-A', 'gm@mail.com',0);
INSERT INTO emner_brukere (emnekode, mail, foreleser) VALUES ('IINI3006', 'tk@mail.com',0);
INSERT INTO emner_brukere (emnekode, mail, foreleser) VALUES ('TDAT2004-A', 'tk@mail.com',0);
INSERT INTO emner_brukere (emnekode, mail, foreleser) VALUES ('ALM802F-B', 'tk@mail.com',0);
INSERT INTO emner_brukere (emnekode, mail, foreleser) VALUES ('TDAT1001-A', 'tk@mail.com',0);
INSERT INTO emner_brukere (emnekode, mail, foreleser) VALUES ('TDAT2003-A', 'ob@mail.com',0);
INSERT INTO emner_brukere (emnekode, mail, foreleser) VALUES ('TDAT2004-A', 'ob@mail.com',0);
INSERT INTO emner_brukere (emnekode, mail, foreleser) VALUES ('TDAT1003-A', 'ob@mail.com',0);
INSERT INTO emner_brukere (emnekode, mail, foreleser) VALUES ('ALM805F-A', 'ob@mail.com',0);
INSERT INTO emner_brukere (emnekode, mail, foreleser) VALUES ('IINI3006', 'ob@mail.com',0);
INSERT INTO emner_brukere (emnekode, mail, foreleser) VALUES ('ALM803F-A', 'ob@mail.com',0);
INSERT INTO emner_brukere (emnekode, mail, foreleser) VALUES ('ALM802F-B', 'ob@mail.com',0);
INSERT INTO emner_brukere (emnekode, mail, foreleser) VALUES ('TDAT1001-A', 'ob@mail.com',0);
INSERT INTO emner_brukere (emnekode, mail, foreleser) VALUES ('TDAT2001-A', 'og@mail.com',0);
INSERT INTO emner_brukere (emnekode, mail, foreleser) VALUES ('ALM802F-B', 'og@mail.com',0);
INSERT INTO emner_brukere (emnekode, mail, foreleser) VALUES ('ALM802F-B', 'grethe@mail.com',1);
INSERT INTO emner_brukere (emnekode, mail, foreleser) VALUES ('ALM802F-B', 'anette@mail.com',1);
INSERT INTO emner_brukere (emnekode, mail, foreleser) VALUES ('TDAT2001-A', 'grethe@mail.com',1);
INSERT INTO emner_brukere (emnekode, mail, foreleser) VALUES ('IINI3006', 'grethe@mail.com',1);
INSERT INTO emner_brukere (emnekode, mail, foreleser) VALUES ('ALM805F-A', 'grethe@mail.com',1);

INSERT INTO plassering (plassering_navn, ant_bord) VALUES('Labben 2.etg', 19);
INSERT INTO plassering (plassering_navn, ant_bord) VALUES('Polareal 1.etg', 24);
INSERT INTO plassering (plassering_navn, ant_bord) VALUES('Sukkerhuset 4.etg', 19);
INSERT INTO plassering (plassering_navn, ant_bord) VALUES('Tihlde drift', 9);
INSERT INTO plassering (plassering_navn, ant_bord) VALUES('P-Lab 1.etg', 19);

INSERT INTO koe_brukere (koe_id, mail, plassering_navn, ovingsnummer, koe_plass, bordnummer,info) VALUES (1, 'gm@mail.com', 'Labben 2.etg', 3, 1, 2, '?!?!?!');
INSERT INTO koe_brukere (koe_id, mail, plassering_navn, ovingsnummer, koe_plass, bordnummer) VALUES (2, 'gm@mail.com', 'Labben 2.etg', 2, 1, 3);
INSERT INTO koe_brukere (koe_id, mail, plassering_navn, ovingsnummer, koe_plass, bordnummer,info) VALUES (3, 'gm@mail.com', 'Labben 2.etg', 5, 1, 4, 'Hjelp!');
INSERT INTO koe_brukere (koe_id, mail, plassering_navn, ovingsnummer, koe_plass, bordnummer) VALUES (4, 'gm@mail.com', 'Labben 2.etg', 9, 1, 2);
INSERT INTO koe_brukere (koe_id, mail, plassering_navn, ovingsnummer, koe_plass, bordnummer,info) VALUES (41, 'gm@mail.com', 'Labben 2.etg', 5, 1, 2, 'Godkjenning!');
INSERT INTO koe_brukere (koe_id, mail, plassering_navn, ovingsnummer, koe_plass, bordnummer) VALUES (38, 'gm@mail.com', 'Labben 2.etg', 2, 1, 9);
INSERT INTO koe_brukere (koe_id, mail, plassering_navn, ovingsnummer, koe_plass, bordnummer) VALUES (1, 'ob@mail.com', 'Labben 2.etg', 9, 2, 9);
INSERT INTO koe_brukere (koe_id, mail, plassering_navn, ovingsnummer, koe_plass, bordnummer) VALUES (2, 'ob@mail.com', 'Labben 2.etg', 0, 2, 9);
INSERT INTO koe_brukere (koe_id, mail, plassering_navn, ovingsnummer, koe_plass, bordnummer) VALUES (3, 'ob@mail.com', 'Labben 2.etg', 3, 2, 12);
INSERT INTO koe_brukere (koe_id, mail, plassering_navn, ovingsnummer, koe_plass, bordnummer) VALUES (6, 'ob@mail.com', 'Labben 2.etg', 9, 2, 9);

INSERT INTO oving (oving_nr, emnekode, delemne_nr) VALUES (1, 'ALM805F-A', 1);
INSERT INTO oving (oving_nr, emnekode, delemne_nr) VALUES (2, 'ALM805F-A', 1);
INSERT INTO oving (oving_nr, emnekode, delemne_nr) VALUES (3, 'ALM805F-A', 1);
INSERT INTO oving (oving_nr, emnekode, delemne_nr) VALUES (4, 'ALM805F-A', 1);
INSERT INTO oving (oving_nr, emnekode, delemne_nr) VALUES (5, 'ALM805F-A', 1);
INSERT INTO oving (oving_nr, emnekode, delemne_nr) VALUES (6, 'ALM805F-A', 1);
INSERT INTO oving (oving_nr, emnekode, delemne_nr) VALUES (1, 'ALM805F-A', 2);
INSERT INTO oving (oving_nr, emnekode, delemne_nr) VALUES (2, 'ALM805F-A', 2);
INSERT INTO oving (oving_nr, emnekode, delemne_nr) VALUES (3, 'ALM805F-A', 2);
INSERT INTO oving (oving_nr, emnekode, delemne_nr) VALUES (4, 'ALM805F-A', 2);
INSERT INTO oving (oving_nr, emnekode, delemne_nr) VALUES (5, 'ALM805F-A', 2);
INSERT INTO oving (oving_nr, emnekode, delemne_nr) VALUES (1, 'ALM805F-A', 3);
INSERT INTO oving (oving_nr, emnekode, delemne_nr) VALUES (2, 'ALM805F-A', 3);
INSERT INTO oving (oving_nr, emnekode, delemne_nr) VALUES (3, 'ALM805F-A', 3);
INSERT INTO oving (oving_nr, emnekode, delemne_nr) VALUES (1, 'TDAT2005-A', 1);
INSERT INTO oving (oving_nr, emnekode, delemne_nr) VALUES (2, 'TDAT2005-A', 1);
INSERT INTO oving (oving_nr, emnekode, delemne_nr) VALUES (3, 'TDAT2005-A', 1);
INSERT INTO oving (oving_nr, emnekode, delemne_nr) VALUES (4, 'TDAT2005-A', 1);

INSERT INTO delemne_brukere(mail, emnekode, delemne_nr) VALUES('js@mail.com','ALM805F-A', 2);

INSERT INTO oving_brukere(oving_id, mail, godkjent, godkjent_av, godkjent_tid) VALUES(1, 'ob@mail.com', 0, null, null);
INSERT INTO oving_brukere(oving_id, mail, godkjent, godkjent_av, godkjent_tid) VALUES(2, 'ob@mail.com', 0, null, null);
INSERT INTO oving_brukere(oving_id, mail, godkjent, godkjent_av, godkjent_tid) VALUES(3, 'ob@mail.com', 1, 'Grethe', '1970-20-01 12:02:09');
INSERT INTO oving_brukere(oving_id, mail, godkjent, godkjent_av, godkjent_tid) VALUES(4, 'ob@mail.com', 1, 'Anette', '1989-06-30 10:20:21');
INSERT INTO oving_brukere(oving_id, mail, godkjent, godkjent_av, godkjent_tid) VALUES(6, 'ob@mail.com', 1, 'Jørgen', '2000-12-03 09:40:11');
INSERT INTO oving_brukere(oving_id, mail, godkjent, godkjent_av, godkjent_tid) VALUES(7, 'ob@mail.com', 0, null, null);

