SELECT `Livreur`.`nom_livreur`, `Vehicule`.`type_vehicule`, `Pizza`.`nom_pizza`,`Pizza`.`prix_pizza`,`Client`.`nom_client`,`Livraison`.`dateCommande_livraison`, `Livraison`.`dateLivraison_livraison`,TIMEDIFF(`Livraison`.`dateLivraison_livraison`,`Livraison`.`dateCommande_livraison` ) > "00:30:00" AS `RETARD`
FROM `Livreur` 
    JOIN `Livraison` ON `Livraison`.`id_livreur` = `Livreur`.`id_livreur` 
    JOIN `Vehicule` ON `Livraison`.`id_vehicule` = `Vehicule`.`id_vehicule` 
    JOIN `Comporter` ON `Comporter`.`id_livraison` = `Livraison`.`id_livraison` 
    JOIN `Pizza` ON `Comporter`.`id_pizza` = `Pizza`.`id_pizza`
    JOIN `Client` ON `Livraison`.`id_client` = `Client`.`id_client`;