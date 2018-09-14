CREATE TABLE IF NOT EXISTS WEAPONS (id INTEGER PRIMARY KEY, type INT, name VARCHAR(150), price INT, level INT, hit_value INT);

CREATE TABLE IF NOT EXISTS PLAYERS (id INTEGER auto_increment PRIMARY KEY, name VARCHAR(255) NOT NULL UNIQUE, money INT,
health INT, level INT, weapon_id INT, opponent_health INT, opponent_weapon_id INT,
foreign key (weapon_id) references WEAPONS(id));

INSERT into WEAPONS VALUES (1, 1, 'Butcher Knife', 100, 1, 3);
INSERT into WEAPONS VALUES (2, 2, 'Glock 17', 150, 1, 8);
INSERT into WEAPONS VALUES (3, 3, 'Smith & Wesson Model 60', 600, 1, 10);
INSERT into WEAPONS VALUES (4, 4, 'Remington 1100', 1000, 2, 13);
INSERT into WEAPONS VALUES (5, 5, 'M-16', 1100, 2, 15);
INSERT into WEAPONS VALUES (6, 6, 'Mossberg 500', 1400, 3, 18);
INSERT into WEAPONS VALUES (7, 7, 'Browning M2', 1600, 3, 20);