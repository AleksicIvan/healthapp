INSERT INTO addresses (id, city, street) VALUES
(1000000, 'Westworld', 'Delos Inc. 1');

INSERT INTO addresses (id, city, street) VALUES
(1000001, 'Beograd', 'Resavska 51');

INSERT INTO addresses (id, city, street) VALUES
(1000002, 'Zaječar', 'Rasadnička bb');

INSERT INTO hospitals (id, name, address_id) VALUES
(1000000, 'Klinika za urologiju', 1000001);

INSERT INTO hospitals (id, name, address_id) VALUES
(1000001, 'Dom zdravlja Zaječar', 1000002);


INSERT INTO users (id, username, email, password, first_name, last_name, roles) VALUES
(1, 'admin', 'admin@gmail.com', '$2a$10$hKDVYxLefVHV/vtuPhWD3OigtRyOykRLDdUAp80Z1crSoS1lFqaFS', 'Ivan', 'Grozni', 'ADMIN');

INSERT INTO users (id, username, email, password, first_name, last_name, roles) VALUES
(2, 'user', 'user@gmail.com', '$2a$10$ByIUiNaRfBKSV6urZoBBxe4UbJ/sS6u1ZaPORHF9AtNWAuVPVz1by', 'Ivan', 'Obicni', 'USER');

INSERT INTO users (id, username, email, password, address_id, first_name, last_name, roles) VALUES
(3, 'dolores', 'dolores@westworld.com', '$2y$12$ZS1yLbXgqvzEuF5GdY7eIeNDXFxjJ72AIoCmbee2iYJd3M7nh8FDG', 1000000, 'Dolores', 'Abernathy', 'USER');


-- PRAVILNIK
-- O SPECIJALIZACIJAMA I UŽIM SPECIJALIZACIJAMA ZDRAVSTVENIH RADNIKA I ZDRAVSTVENIH SARADNIKA
-- ("Sl. glasnik RS", br. 10/2013, 91/2013, 113/2013, 109/2014 i 53/2018)
INSERT INTO specializations (id, name) VALUES
(1, 'Doktor medicine - bez specijalizacije'),
(2, 'Interna medicina'),
(3, 'Internistička onkologija'),
(4, 'Infektologija'),
(5, 'Pedijatrija'),
(6, 'Neurologija'),
(7, 'Psihijatrija'),
(8, 'Dečja neurologija'),
(9, 'Dečja i adolescentna psihijatrija'),
(10, 'Ginekologija i akušerstvo'),
(11, 'Opšta hirurgija'),
(12, 'Abdominalna hirurgija'),
(13, 'Vaskularna hirurgija'),
(14, 'Grudna hirurgija'),
(15, 'Ortopedska hirurgija i traumatologija'),
(16, 'Dečja hirurgija'),
(17, 'Neurohirurgija'),
(18, 'Plastična, rekonstruktivna i estetska hirurgija'),
(19, 'Maksilofacijalna hirurgija'),
(20, 'Urologija'),
(21, 'Kardiohirurgija'),
(22, 'Urgentna medicina'),
(23, 'Anesteziologija, reanimatologija i intenzivna terapija'),
(24, 'Otorinolaringologija'),
(25, 'Oftalmologija'),
(26, 'Dermatovenerologija'),
(27, 'Fizikalna medicina i rehabilitacija'),
(28, 'Opšta medicina'),
(29, 'Medicina rada'),
(30, 'Radiologija'),
(31, 'Radijaciona onkologija'),
(32, 'Nuklearna medicina'),
(33, 'Patologija'),
(34, 'Sudska medicina'),
(35, 'Medicinska mikrobiologija'),
(36, 'Klinička biohemija'),
(37, 'Klinička farmakologija'),
(38, 'Laboratorijska medicina'),
(39, 'Imunologija'),
(40, 'Higijena'),
(41, 'Epidemiologija'),
(42, 'Socijalna medicina'),
(43, 'Sportska medicina'),
(44, 'Transfuzijska medicina'),
(45, 'Vazduhoplovna medicina'),
(46, 'Medicinska statistika i informatika'),
(47, 'Palijativna medicina'),
(48, 'Preventivna i dečja stomatologija'),
(49, 'Bolesti zuba i endodoncija'),
(50, 'Stomatološka protetika'),
(51, 'Parodontologija i oralna medicina'),
(52, 'Ortopedija vilica'),
(53, 'Oralna hirurgija'),
(54, 'Maksilofacijalna hirurgija'),
(55, 'Medicinska statistika i informatika');

INSERT INTO doctors (id, first_name, last_name, no_of_ratings, rating, all_ratings, specialization_id) VALUES
(1000000, 'Nebojša', 'Bojanić', 1.0, 4, 4, 20);

INSERT INTO doctors (id, first_name, last_name, no_of_ratings, rating, all_ratings, specialization_id) VALUES
(1000001, 'Uroš', 'Mihajlović', 1.0, 2, 2, 2);

INSERT INTO health_checks (id, description, hospital_id, created_at, user_id, doctor_id) VALUES
(1, "Pij vise vode", 1000000, "2020-04-04T08:23:21.440423", 3, 1000000);

INSERT INTO reports (id, s3file_url, health_check_id) VALUES
(1, "https://medapp-healthcheck-12042020.s3.eu-central-1.amazonaws.com/3/1/pregled1.png", 1);

INSERT INTO reports (id, s3file_url, health_check_id) VALUES
(2, "https://medapp-healthcheck-12042020.s3.eu-central-1.amazonaws.com/3/1/pregled2.png", 1);





