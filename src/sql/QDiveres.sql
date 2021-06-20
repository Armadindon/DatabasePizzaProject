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
	