<--! Prix moyen d'une commande -->
SELECT `Vehicule`.`immatricule_vehicule` 
FROM `Vehicule` 
WHERE `Vehicule`.`id_vehicule` NOT IN (SELECT `Livraison`.`id_vehicule` FROM `Livraison`);

<--! Prix moyen d'une commande -->
SELECT `Client`.`nom_client`, count(`Livraison`.`id_livraison`) AS `NBCOMMANDE`
FROM `Client`,`Livraison`
WHERE `Client`.`id_client` = `Livraison`.`id_client`
GROUP BY `Client`.`nom_client`;

<--! Prix moyen d'une commande -->
SELECT	sum(`Comporter`.`quantite` * `Pizza`.`prix_pizza`) AS `PrixTotal`,count(`Livraison`.`id_livraison`) AS `nBLivraison`,sum(`Comporter`.`quantite` * `Pizza`.`prix_pizza`)/count(`Livraison`.`id_livraison`)  AS `moyenne`
FROM `Comporter`,`Pizza`,`Livraison`
    WHERE `Comporter`.`id_pizza` = `Pizza`.`id_pizza` AND `Comporter`.`id_livraison` = `Livraison`.`id_livraison`;

<--! Nombre de pizzas avant la pizza offerte -->
SELECT Livraison.id_client, nom_client, ABS(SUM(quantite) % 10 - 10) AS "Nombre de pizza avant la pizza Gratuite"
FROM Comporter
NATURAL JOIN Livraison
JOIN Client ON Livraison.id_client = Client.id_client
GROUP BY id_client;
	
<--! Nombre de pizzas moyenne par commande -->
SELECT AVG(quantite) FROM Comporter;

<--! Personnes commandant plus que la moyenne poar commande-->
SELECT nom_client FROM(
    SELECT Livraison.id_client, nom_client, SUM(quantite)/count(Livraison.id_livraison) AS "NBMoy"
        FROM Comporter
        NATURAL JOIN Livraison
        JOIN Client ON Livraison.id_client = Client.id_client
		GROUP BY id_client) AS TABDATA
WHERE TABDATA.NBMoy > (SELECT AVG(quantite) FROM Comporter);