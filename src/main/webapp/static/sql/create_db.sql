DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS supplier;
DROP TABLE IF EXISTS product_category;
DROP TABLE IF EXISTS paid_order;
DROP TABLE IF EXISTS billing_address;
DROP TABLE IF EXISTS users;

CREATE TABLE supplier
(
id          SERIAL PRIMARY KEY NOT NULL,
name        TEXT,
description TEXT
);

CREATE TABLE product_category
(
id          SERIAL PRIMARY KEY NOT NULL,
name        TEXT,
description TEXT,
department  TEXT
);

CREATE TABLE product
(
    id SERIAL PRIMARY KEY NOT NULL,
    name TEXT,
    description TEXT,
    default_price FLOAT,
    default_currency VARCHAR(3),
    product_category INT REFERENCES product_category(id) ON DELETE CASCADE,
    supplier INT REFERENCES supplier(id) ON DELETE CASCADE
);

CREATE TABLE billing_address
(
    id SERIAL PRIMARY KEY NOT NULL,
    firstName TEXT NOT NULL,
    lastName TEXT NOT NULL,
    email TEXT NOT NULL,
    address TEXT NOT NULL,
    country TEXT NOT NULL,
    state TEXT NOT NULL,
    zip TEXT NOT NULL
);

CREATE TABLE paid_order
(
    id SERIAL PRIMARY KEY NOT NULL,
    billing_address_id INT NOT NULL REFERENCES billing_address(id),
    cart TEXT NOT NULL ,
    date TIMESTAMP NOT NULL DEFAULT now()
);

CREATE TABLE users (
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL,
    email TEXT NOT NULL,
    hashed_password TEXT NOT NULL
);

INSERT INTO supplier(name, description) VALUES ('Sony Interactive Entertainment', 'Video games and gaming console network services');
INSERT INTO supplier(name, description) VALUES ('Rockstar Games', 'Video game development');
INSERT INTO supplier(name, description) VALUES ('Electronic Arts', 'Video game development');
INSERT INTO supplier(name, description) VALUES ('Ubisoft Entertainment', 'Video game development');
INSERT INTO supplier(name, description) VALUES ('Activision Publishing', 'Video game development');
INSERT INTO supplier(name, description) VALUES ('CD Project', 'Video game development');
INSERT INTO supplier(name, description) VALUES ('Nintendo Company', 'Video game development');
INSERT INTO supplier(name, description) VALUES ('From Software', 'Video game development');
INSERT INTO supplier(name, description) VALUES ('Remedy Entertainment', 'Video game development');
INSERT INTO supplier(name, description) VALUES ('Capcom', 'Video game development');


INSERT INTO product_category(name, description, department) VALUES ('PlayStation 4', 'Gaming Platform', 'Gaming console developed and maintained by Sony Entertainment Inc.');
INSERT INTO product_category(name, description, department) VALUES ('XboxOne', 'Gaming Platform', 'Gaming console developed and maintained by Microsoft.');
INSERT INTO product_category(name, description, department) VALUES ('PC', 'Gaming Platform', 'Personal computer.');
INSERT INTO product_category(name, description, department) VALUES ('Nintendo Switch', 'Gaming Platform', 'Gaming console developed and maintained by Nintendo.');


INSERT INTO product(name, description, default_price, default_currency, product_category, supplier) VALUES ('God of War', 'Journey into the fearsome Norse wilds where Kratos must master his legendary rage - not only to learn from his bloody past... but to give his son a future.', 59.99, 'EUR', 1, 1);
INSERT INTO product(name, description, default_price, default_currency, product_category, supplier) VALUES ('Marvel''s Spider-Man', 'Swing into an action-packed open-world full of deadly super villains and high personal stakes - put on the iconic mask as Spider-man in a brand new and authentic adventure.', 59.99, 'EUR', 1, 1);
INSERT INTO product(name, description, default_price, default_currency, product_category, supplier) VALUES ('Death Stranding', 'Sam Bridges must brave a world utterly transformed by the Death Stranding. Carrying the disconnected remnants of our future in his hands, he embarks on a journey to reconnect the shattered world one step at a time.', 59.99, 'EUR', 1, 1);
INSERT INTO product(name, description, default_price, default_currency, product_category, supplier) VALUES ('Death Stranding', 'Sam Bridges must brave a world utterly transformed by the Death Stranding. Carrying the disconnected remnants of our future in his hands, he embarks on a journey to reconnect the shattered world one step at a time.', 59.99, 'EUR', 3, 1);
INSERT INTO product(name, description, default_price, default_currency, product_category, supplier) VALUES ('Red Dead Redemption 2', 'The end of the wild west era has began as lawmen hunt down the last remaining outlaw gangs.', 59.99, 'EUR', 1, 2);
INSERT INTO product(name, description, default_price, default_currency, product_category, supplier) VALUES ('Red Dead Redemption 2', 'The end of the wild west era has began as lawmen hunt down the last remaining outlaw gangs.', 59.99, 'EUR', 2, 2);
INSERT INTO product(name, description, default_price, default_currency, product_category, supplier) VALUES ('Red Dead Redemption 2', 'The end of the wild west era has began as lawmen hunt down the last remaining outlaw gangs.', 59.99, 'EUR', 3, 2);
INSERT INTO product(name, description, default_price, default_currency, product_category, supplier) VALUES ('Call of Duty: Modern Warfare', 'Take on the role of lethal Tier One operators in a heart-racing saga that will affect the global balance of power.', 59.99, 'EUR', 1, 5);
INSERT INTO product(name, description, default_price, default_currency, product_category, supplier) VALUES ('Call of Duty: Modern Warfare', 'Take on the role of lethal Tier One operators in a heart-racing saga that will affect the global balance of power.', 59.99, 'EUR', 2, 5);
INSERT INTO product(name, description, default_price, default_currency, product_category, supplier) VALUES ('Call of Duty: Modern Warfare', 'Take on the role of lethal Tier One operators in a heart-racing saga that will affect the global balance of power.', 59.99, 'EUR', 3, 5);
INSERT INTO product(name, description, default_price, default_currency, product_category, supplier) VALUES ('The Witcher 3: Wild Hunt', 'Become monster slayer Geralt of Rivia and take on the most important contract of your life - tracking down the Child of Prophecy, a living weapon that can alter the shape of the world.', 59.99, 'EUR', 1, 6);
INSERT INTO product(name, description, default_price, default_currency, product_category, supplier) VALUES ('The Witcher 3: Wild Hunt', 'Become monster slayer Geralt of Rivia and take on the most important contract of your life - tracking down the Child of Prophecy, a living weapon that can alter the shape of the world.', 59.99, 'EUR', 2, 6);
INSERT INTO product(name, description, default_price, default_currency, product_category, supplier) VALUES ('The Witcher 3: Wild Hunt', 'Become monster slayer Geralt of Rivia and take on the most important contract of your life - tracking down the Child of Prophecy, a living weapon that can alter the shape of the world.', 59.99, 'EUR', 3, 6);
INSERT INTO product(name, description, default_price, default_currency, product_category, supplier) VALUES ('The Witcher 3: Wild Hunt', 'Become monster slayer Geralt of Rivia and take on the most important contract of your life - tracking down the Child of Prophecy, a living weapon that can alter the shape of the world.', 59.99, 'EUR', 4, 6);
INSERT INTO product(name, description, default_price, default_currency, product_category, supplier) VALUES ('Super Smash Bros. Ultimate', 'A series of crossover fighting video games published by Nintendo, and primarily features characters of various Nintendo franchises', 59.99, 'EUR', 4, 10);
INSERT INTO product(name, description, default_price, default_currency, product_category, supplier) VALUES ('Horizon Zero Dawn', 'Become humanity''s last hope in a deadly open-world adventure where nature has reclaimed the land and machines are the dominant species.', 59.99, 'EUR', 1, 1);
INSERT INTO product(name, description, default_price, default_currency, product_category, supplier) VALUES ('The Last of Us Part II', 'Experience a brutal post-pandemic world where how you survive is almost as important as survival itself.  The next chapter of Ellie''s harrowing adventure begins here....', 59.99, 'EUR', 1, 1);
INSERT INTO product(name, description, default_price, default_currency, product_category, supplier) VALUES ('Days Gone', 'Welcome to the harsh wilderness, ravaged by mindless, feral creatures called Freakers – and as bounty hunter Deacon St John, it''s up to you to not just survive, but find a reason to live.', 59.99, 'EUR', 1, 1);
INSERT INTO product(name, description, default_price, default_currency, product_category, supplier) VALUES ('Uncharted 4: A Thief''s End', 'Join legendary – and retired – fortune hunter Nathan Drake on one last globe-trotting adventure where he''s forced to decide what he''s willing to sacrifice to save the ones he loves.', 59.99, 'EUR', 1, 1);
INSERT INTO product(name, description, default_price, default_currency, product_category, supplier) VALUES ('Assassin''s Creed Odyssey', 'From outcast to living legend, embark on an odyssey to uncover the secrets of your past and change the fate of Ancient Greece.', 59.99, 'EUR', 1, 4);
INSERT INTO product(name, description, default_price, default_currency, product_category, supplier) VALUES ('Assassin''s Creed Odyssey', 'From outcast to living legend, embark on an odyssey to uncover the secrets of your past and change the fate of Ancient Greece.', 59.99, 'EUR', 2, 4);
INSERT INTO product(name, description, default_price, default_currency, product_category, supplier) VALUES ('Assassin''s Creed Odyssey', 'From outcast to living legend, embark on an odyssey to uncover the secrets of your past and change the fate of Ancient Greece.', 59.99, 'EUR', 3, 4);
INSERT INTO product(name, description, default_price, default_currency, product_category, supplier) VALUES ('Far Cry 5', 'Welcome to Hope County, Montana, land of the free and the brave but also home to a fanatical doomsday cult known as Eden’s Gate. Stand up to cult leader Joseph Seed and liberate the besieged community.', 59.99, 'EUR', 1, 4);
INSERT INTO product(name, description, default_price, default_currency, product_category, supplier) VALUES ('Far Cry 5', 'Welcome to Hope County, Montana, land of the free and the brave but also home to a fanatical doomsday cult known as Eden’s Gate. Stand up to cult leader Joseph Seed and liberate the besieged community.', 59.99, 'EUR', 2, 4);
INSERT INTO product(name, description, default_price, default_currency, product_category, supplier) VALUES ('Far Cry 5', 'Welcome to Hope County, Montana, land of the free and the brave but also home to a fanatical doomsday cult known as Eden’s Gate. Stand up to cult leader Joseph Seed and liberate the besieged community.', 59.99, 'EUR', 3, 4);
INSERT INTO product(name, description, default_price, default_currency, product_category, supplier) VALUES ('Detroit: Become Human', 'Enter a world where moral dilemmas and difficult decisions can turn android slaves into world-changing revolutionaries as you see the world through the eyes of a machine.', 59.99, 'EUR', 1, 1);
INSERT INTO product(name, description, default_price, default_currency, product_category, supplier) VALUES ('Sekiro: Shadows Die Twice', 'Explore late 1500s Sengoku Japan, a brutal period of constant life and death conflict, as you come face to face with larger than life foes in a dark and twisted world. Take Revenge. Restore your honor. Kill Ingeniously.', 59.99, 'EUR', 1, 8);
INSERT INTO product(name, description, default_price, default_currency, product_category, supplier) VALUES ('Sekiro: Shadows Die Twice', 'Explore late 1500s Sengoku Japan, a brutal period of constant life and death conflict, as you come face to face with larger than life foes in a dark and twisted world. Take Revenge. Restore your honor. Kill Ingeniously.', 59.99, 'EUR', 2, 8);
INSERT INTO product(name, description, default_price, default_currency, product_category, supplier) VALUES ('Sekiro: Shadows Die Twice', 'Explore late 1500s Sengoku Japan, a brutal period of constant life and death conflict, as you come face to face with larger than life foes in a dark and twisted world. Take Revenge. Restore your honor. Kill Ingeniously.', 59.99, 'EUR', 3, 8);
INSERT INTO product(name, description, default_price, default_currency, product_category, supplier) VALUES ('Control', 'Take part in Jesse''s supernatural action-adventure, that will challenge you to master the combination of supernatural abilities, modifiable loadouts, and reactive environments while fighting through a deep and unpredictable world.', 59.99, 'EUR', 1, 9);
INSERT INTO product(name, description, default_price, default_currency, product_category, supplier) VALUES ('Control', 'Take part in Jesse''s supernatural action-adventure, that will challenge you to master the combination of supernatural abilities, modifiable loadouts, and reactive environments while fighting through a deep and unpredictable world.', 59.99, 'EUR', 2, 9);
INSERT INTO product(name, description, default_price, default_currency, product_category, supplier) VALUES ('Control', 'Take part in Jesse''s supernatural action-adventure, that will challenge you to master the combination of supernatural abilities, modifiable loadouts, and reactive environments while fighting through a deep and unpredictable world.', 59.99, 'EUR', 3, 9);
