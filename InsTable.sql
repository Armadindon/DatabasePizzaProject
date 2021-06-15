USE RaPizzDB;
INSERT INTO Adresse (
        ville_adresse,
        codePostal_adresse,
        rue_adresse,
        numero_adresse
    )
VALUES ('HYERES', '83400', 'GAMBETTA', '10bis'),
    ('CHAMPS-SUR-MARNES', '77420', 'NAPOLEON', '13'),
    ('MAZAMET', '81200', 'RUE DU PORT', '69'),
    ('HYERES', '83400', 'RUE DE LA CHAPELLE', '42');
INSERT INTO Client (
        nom_client,
        prenom_client,
        solde_client,
        id_adresse
    )
VALUES ('MARINO', 'ENZO', 639, 1),
    ('GRECO', 'NINO', 100, 2),
    ('CONTI', 'MILO', 25, 3),
    ('GIORDANO', 'DIEGO', 10, 4);
INSERT INTO Ingredient (`nom_ingredient`)
VALUES ('tomate'),
    ('salade'),
    ('champignon'),
    ('sauce_creme'),
    ('sauce_tomate'),
    ('saumon'),
    ('viande_hache'),
    ('oignon'),
    ('jambon'),
    ('olive'),
    ('Mozarella');
INSERT INTO Pizza (nom_pizza, prix_pizza)
VALUES ('Margaritta', 6.0),
    ('Reine', 6.5),
    ('Atomic', 7.0),
    ('Carnivore', 10.0),
    ('Vege', 10.5),
    ('Norvegienne', 12.0);
INSERT INTO Garnir (id_pizza, id_ingredient)
VALUES (1, 5),
    (1, 11),
    (2, 3),
    (2, 5),
    (2, 11),
    (3, 5),
    (3, 9),
    (3, 11),
    (4, 5),
    (4, 7),
    (4, 3),
    (4, 8),
    (4, 9),
    (5, 1),
    (5, 2),
    (5, 3),
    (5, 5),
    (5, 10),
    (5, 11),
    (6, 2),
    (6, 4),
    (6, 6);
INSERT INTO Vehicule (immatricule_vehicule, type_vehicule)
VALUES ('AG-963-SR', 'VOITURE'),
    ('BK-568-SR', 'MOTO'),
    ('CD-455-GK', 'MOTO');
INSERT INTO Livreur (nom_livreur, prenom_livreur, id_adresse)
VALUES ('ESCOBAR', 'PABLO', 1),
    ('ONYME', 'ANE', 2),
    ('LUIGI', 'COLOMBO', 3),
    ('MARIO', 'DE LUCA', 4);
INSERT INTO Livraison (
        dateCommande_livraison,
        dateLivraison_livraison,
        id_client,
        id_livreur,
        id_vehicule,
        id_adresse
    )
VALUES (
        '2020-06-14 5:00:00',
        '2020-06-14 18:00:00',
        1,
        1,
        1,
        1
    ),
    (
        '2020-06-14 17:00:00',
        '2020-06-14 18:00:00',
        1,
        2,
        1,
        1
    ),
    (
        '2020-06-14 17:00:00',
        '2020-06-14 18:00:00',
        1,
        3,
        1,
        1
    ),
    (
        '2020-07-15 17:00:00',
        '2020-07-15 18:00:00',
        1,
        4,
        2,
        1
    ),
    (
        '2020-07-15 17:00:00',
        '2020-07-15 18:00:00',
        1,
        1,
        2,
        1
    ),
    (
        '2020-07-15 17:00:00',
        '2020-07-15 18:00:00',
        1,
        2,
        2,
        1
    ),
    (
        '2020-07-20 17:00:00',
        '2020-07-20 18:00:00',
        2,
        3,
        3,
        2
    ),
    (
        '2020-07-21 17:00:00',
        '2020-07-21 18:00:00',
        3,
        4,
        3,
        3
    ),
    ('2020-07-23 17:00:00', NULL, 4, 1, 3, 4),
    ('2020-07-23 17:00:00', NULL, 1, 2, 1, 1);

INSERT INTO Comporter (id_pizza, id_livraison, taille_pizza)
VALUES (1, 1, 'NAINE'),
    (1, 2, 'NAINE'),
    (2, 3, 'HUMAINE'),
    (4, 4, 'HUMAINE'),
    (6, 5, 'OGRESSE'),
    (1, 6, 'OGRESSE'),
    (2, 7, 'OGRESSE'),
    (3, 8, 'NAINE'),
    (4, 9, 'HUMAINE'),
    (5, 10, 'HUMAINE');