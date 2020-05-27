INSERT INTO addresses (id, city, street)
VALUES (1000000, 'Westworld', 'Delos Inc. 1');

INSERT INTO addresses (id, city, street)
VALUES (1000001, 'Beograd', 'Resavska 51');

INSERT INTO addresses (id, city, street)
VALUES (1000002, 'Zaječar', 'Rasadnička bb');

INSERT INTO hospitals (id, name, address_id)
VALUES (1000000, 'Klinika za urologiju', 1000001);

INSERT INTO hospitals (id, name, address_id)
VALUES (1000001, 'Dom zdravlja Zaječar', 1000002);


INSERT INTO users (id, username, email, password, first_name, last_name, roles)
VALUES (1, 'admin', 'admin@gmail.com', '$2a$10$hKDVYxLefVHV/vtuPhWD3OigtRyOykRLDdUAp80Z1crSoS1lFqaFS', 'Ivan', 'Grozni',
        'ADMIN');

INSERT INTO users (id, username, email, password, first_name, last_name, roles)
VALUES (2, 'user', 'user@gmail.com', '$2a$10$ByIUiNaRfBKSV6urZoBBxe4UbJ/sS6u1ZaPORHF9AtNWAuVPVz1by', 'Ivan', 'Obicni',
        'USER');

INSERT INTO users (id, username, email, password, address_id, first_name, last_name, roles)
VALUES (3, 'dolores', 'dolores@westworld.com', '$2y$12$ZS1yLbXgqvzEuF5GdY7eIeNDXFxjJ72AIoCmbee2iYJd3M7nh8FDG', 1000000,
        'Dolores', 'Abernathy', 'USER');