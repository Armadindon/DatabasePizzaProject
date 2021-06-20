SELECT `Pizza`.`nom_pizza`, `Pizza`.`prix_pizza`*0.75 AS `PrixNaine`,`Pizza`.`prix_pizza`AS `PrixHumaine`,`Pizza`.`prix_pizza`*1.5 AS `PrixOgre`, GROUP_CONCAT(`Ingredient`.`nom_ingredient`SEPARATOR ', ' ) AS `Ingredient`
FROM `Pizza` 
    JOIN `Garnir` ON `Garnir`.`id_pizza` = `Pizza`.`id_pizza` 
    JOIN `Ingredient` ON `Garnir`.`id_ingredient` = `Ingredient`.`id_ingredient`
    GROUP BY `Pizza`.`nom_pizza`;
