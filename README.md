# EgzaminoKurimasMySQL
Egzamino kurimo console programa (mySQL Hibernate)

MySQL database code:
============================================================
CREATE TABLE Studentas (
                id INT AUTO_INCREMENT NOT NULL,
                vardas VARCHAR(255) NOT NULL,
                PRIMARY KEY (id)
);


CREATE TABLE Egzaminai (
                id INT AUTO_INCREMENT NOT NULL,
                pavadinimas VARCHAR(255) NOT NULL,
                PRIMARY KEY (id)
);


CREATE TABLE Klausimai (
                id INT AUTO_INCREMENT NOT NULL,
                egzaminai_id INT NOT NULL,
                klausimas VARCHAR(255) NOT NULL,
                PRIMARY KEY (id)
);


CREATE TABLE Atsakymai (
                id INT AUTO_INCREMENT NOT NULL,
                klausimo_id INT NOT NULL,
                atsakymas VARCHAR(255) NOT NULL,
                teisingas_neteisingas BOOLEAN NOT NULL,
                PRIMARY KEY (id)
);


CREATE TABLE Sprendimas (
                id INT AUTO_INCREMENT NOT NULL,
                studento_id INT NOT NULL,
                atsakymo_id INT NOT NULL,
                pasirinktas_atsakymas INT NOT NULL,
                PRIMARY KEY (id)
);


ALTER TABLE Sprendimas ADD CONSTRAINT studentas_sprendimas_fk
FOREIGN KEY (studento_id)
REFERENCES Studentas (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE Klausimai ADD CONSTRAINT egzaminai_egzamino_klausimai_fk1
FOREIGN KEY (egzaminai_id)
REFERENCES Egzaminai (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE Atsakymai ADD CONSTRAINT egzamino_klausimai_atsakymai_fk
FOREIGN KEY (klausimo_id)
REFERENCES Klausimai (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE Sprendimas ADD CONSTRAINT atsakymai_sprendimas_fk
FOREIGN KEY (atsakymo_id)
REFERENCES Atsakymai (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;
