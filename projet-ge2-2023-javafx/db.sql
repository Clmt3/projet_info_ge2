-- Créer la base de données
CREATE DATABASE chainedeproduction;

-- Utiliser la base de données
USE chainedeproduction;

-- Table Machine
CREATE TABLE IF NOT EXISTS Machine (
    id INT AUTO_INCREMENT PRIMARY KEY,
    reference VARCHAR(255) NOT NULL,
    description TEXT,
    puissance INT
);

-- Table TypeOperation
CREATE TABLE IF NOT EXISTS TypeOperation (
    id INT AUTO_INCREMENT PRIMARY KEY,
    description TEXT NOT NULL
);

-- Table Produit
CREATE TABLE IF NOT EXISTS Produit (
    id INT AUTO_INCREMENT PRIMARY KEY,
    reference VARCHAR(255) NOT NULL,
    description TEXT
);

-- Table Operation
CREATE TABLE IF NOT EXISTS Operation (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_produit INT,
    id_type_operation INT,
    FOREIGN KEY (id_produit) REFERENCES Produit(id),
    FOREIGN KEY (id_type_operation) REFERENCES TypeOperation(id)
);

-- Table Realise
CREATE TABLE IF NOT EXISTS Realise (
    id_machine INT,
    id_operation INT,
    statut INT,
    FOREIGN KEY (id_machine) REFERENCES Machine(id),
    FOREIGN KEY (id_operation) REFERENCES Operation(id)
);

-- Table Utilisateur
CREATE TABLE IF NOT EXISTS Utilisateur (
    id INT AUTO_INCREMENT PRIMARY KEY,
    prenom VARCHAR(255),
    nom VARCHAR(255),
    email VARCHAR(255) NOT NULL,
    motdepasse VARCHAR(255) NOT NULL
);

-- Table Utilise
CREATE TABLE IF NOT EXISTS Utilise (
    id_utilisateur INT,
    id_poste_de_travail INT,
    FOREIGN KEY (id_utilisateur) REFERENCES Utilisateur(id),
    FOREIGN KEY (id_poste_de_travail) REFERENCES PosteDeTravail(id)
);

-- Table PosteDeTravail
CREATE TABLE IF NOT EXISTS PosteDeTravail (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_utilisateur INT,
    id_machine INT,
    reference VARCHAR(255),
    description TEXT,
    FOREIGN KEY (id_utilisateur) REFERENCES Utilisateur(id),
    FOREIGN KEY (id_machine) REFERENCES Machine(id)
);

-- Créer la table Etat_Machine
CREATE TABLE IF NOT EXISTS Etat_Machine (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_machine INT,
    debut TIMESTAMP,
    fin TIMESTAMP,
    FOREIGN KEY (id_machine) REFERENCES Machine(id)
);

-- Ajouter un utilisateur administrateur
INSERT INTO Utilisateur (prenom, nom, email, motdepasse)
VALUES ('admin', 'admin', 'admin@example.com', 'admin');
