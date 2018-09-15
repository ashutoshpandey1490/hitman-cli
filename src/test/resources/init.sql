CREATE TABLE IF NOT EXISTS WEAPONS (id INTEGER PRIMARY KEY, type INT, name VARCHAR(150), price INT, level INT, hit_value INT);

CREATE TABLE IF NOT EXISTS PLAYERS (id INTEGER auto_increment PRIMARY KEY, name VARCHAR(255) NOT NULL UNIQUE, money INT,
health INT, level INT, weapon_id INT, opponent_health INT, opponent_weapon_id INT,
foreign key (weapon_id) references WEAPONS(id));

DELETE FROM PLAYERS;
DELETE FROM WEAPONS;
INSERT into WEAPONS VALUES (1, 1, 'Test Knife', 100, 1, 3);
INSERT into WEAPONS VALUES (2, 2, 'Test Gun', 150, 1, 10);
INSERT into WEAPONS VALUES (3, 3, 'Test Machine gun', 300, 2, 15);