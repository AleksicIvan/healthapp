delete from  users;

INSERT INTO users (id, username, email, password, roles) VALUES
(1, 'admin', 'admin@gmail.com', '$2a$10$hKDVYxLefVHV/vtuPhWD3OigtRyOykRLDdUAp80Z1crSoS1lFqaFS', 'ADMIN');

INSERT INTO users (id, username, email, password, roles) VALUES
(2, 'user', 'user@gmail.com', '$2a$10$ByIUiNaRfBKSV6urZoBBxe4UbJ/sS6u1ZaPORHF9AtNWAuVPVz1by', 'USER');
