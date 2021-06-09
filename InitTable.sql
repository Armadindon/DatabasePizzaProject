CREATE DATABASE IF NOT EXISTS RaPizzDB;
USE RaPizzDB;

CREATE TABLE IF NOT EXISTS Pizza(
   id_pizza INT AUTO_INCREMENT,
   nom_pizza VARCHAR(50),
   prix_pizza DECIMAL(4,2),
   taille_pizza ENUM,
   PRIMARY KEY(id_pizza)
);
CREATE TABLE IF NOT EXISTS Ingrédient(
   id_ingredient INT AUTO_INCREMENT,
   nom_ingredient VARCHAR(50),
   PRIMARY KEY(id_ingredient)
);
CREATE TABLE IF NOT EXISTS Adresse(
   id_adresse INT AUTO_INCREMENT,
   ville_adresse VARCHAR(100),
   codePostal_adresse VARCHAR(10),
   rue_adresse INT,
   numéro_adresse VARCHAR(10),
   PRIMARY KEY(id_adresse)
);
CREATE TABLE IF NOT EXISTS Vehicule(
   id_vehicule INT AUTO_INCREMENT,
   immatricule_vehicule VARCHAR(7),
   type_vehicule ENUM,
   PRIMARY KEY(id_vehicule)
);
CREATE TABLE IF NOT EXISTS Client(
   id_client INT AUTO_INCREMENT,
   nom_client VARCHAR(50),
   prenom_client VARCHAR(50),
   solde_client DECIMAL(4,2),
   id_adresse INT NOT NULL,
   PRIMARY KEY(id_client),
   FOREIGN KEY(id_adresse) REFERENCES Adresse(id_adresse)
);
CREATE TABLE IF NOT EXISTS Livreur(
   id_livreur INT AUTO_INCREMENT,
   nom_livreur VARCHAR(50) NOT NULL,
   prenom_livreur VARCHAR(50),
   id_adresse INT NOT NULL,
   PRIMARY KEY(id_livreur),
   FOREIGN KEY(id_adresse) REFERENCES Adresse(id_adresse)
);
CREATE TABLE IF NOT EXISTS Livraison(
   id_livraison INT AUTO_INCREMENT,
   dateCommande_livraison DATETIME,
   dateLivraison_livraison DATETIME,
   id_client INT NOT NULL,
   id_livreur INT NOT NULL,
   id_vehicule INT NOT NULL,
   id_adresse INT NOT NULL,
   PRIMARY KEY(id_livraison),
   FOREIGN KEY(id_client) REFERENCES Client(id_client),
   FOREIGN KEY(id_livreur) REFERENCES Livreur(id_livreur),
   FOREIGN KEY(id_vehicule) REFERENCES Véhicule(id_vehicule),
   FOREIGN KEY(id_adresse) REFERENCES Adresse(id_adresse)
);
CREATE TABLE IF NOT EXISTS Garnir(
   id_pizza INT AUTO_INCREMENT,
   id_ingredient INT,
   PRIMARY KEY(id_pizza, id_ingredient),
   FOREIGN KEY(id_pizza) REFERENCES Pizza(id_pizza),
   FOREIGN KEY(id_ingredient) REFERENCES Ingrédient(id_ingredient)
);
CREATE TABLE IF NOT EXISTS Comporter(
   id_pizza INT AUTO_INCREMENT,
   id_livraison INT,
   PRIMARY KEY(id_pizza, id_livraison),
   FOREIGN KEY(id_pizza) REFERENCES Pizza(id_pizza),
   FOREIGN KEY(id_livraison) REFERENCES Livraison(id_livraison)
);