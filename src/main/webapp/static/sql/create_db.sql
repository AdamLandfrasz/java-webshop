DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS supplier;
DROP TABLE IF EXISTS product_category;

CREATE TABLE supplier
(
id          SERIAL PRIMARY KEY NOT NULL,
name        TEXT,
description TEXT,
products    INT[]
);

CREATE TABLE product_category
(
id          SERIAL PRIMARY KEY NOT NULL,
name        TEXT,
description TEXT,
department  TEXT,
products    INT[]
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

INSERT INTO supplier(name, description, products) VALUES ('Sony Interactive Entertainment', 'Video games and gaming console network services', '{1}');
INSERT INTO product_category(name, description, department, products) VALUES ('PlayStation 4', 'Gaming Platform', 'Gaming console developed and maintained by Sony Entertainment Inc.', '{1}');
INSERT INTO product(name, description, default_price, default_currency, product_category, supplier) VALUES ('God of War', 'Kratos ANGERY', 59.99, 'EUR', 1, 1);
