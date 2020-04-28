INSERT INTO addresses (id, city, street) VALUES
(1000000, 'Westworld', 'Delos Inc. 1');

INSERT INTO addresses (id, city, street) VALUES
(1000001, 'Beograd', 'Resavska 51');

INSERT INTO hospitals (id, name, address_id) VALUES
(1000000, 'Klinika za urologiju', 1000001);


INSERT INTO users (id, username, email, password, roles) VALUES
(1, 'admin', 'admin@gmail.com', '$2a$10$hKDVYxLefVHV/vtuPhWD3OigtRyOykRLDdUAp80Z1crSoS1lFqaFS', 'ADMIN');

INSERT INTO users (id, username, email, password, roles) VALUES
(2, 'user', 'user@gmail.com', '$2a$10$ByIUiNaRfBKSV6urZoBBxe4UbJ/sS6u1ZaPORHF9AtNWAuVPVz1by', 'USER');

INSERT INTO users (id, username, email, password, address_id, roles) VALUES
(3, 'dolores', 'dolores@westworld.com', '$2y$12$ZS1yLbXgqvzEuF5GdY7eIeNDXFxjJ72AIoCmbee2iYJd3M7nh8FDG', 1000000,  'USER');


-- PRAVILNIK
-- O SPECIJALIZACIJAMA I UŽIM SPECIJALIZACIJAMA ZDRAVSTVENIH RADNIKA I ZDRAVSTVENIH SARADNIKA
-- ("Sl. glasnik RS", br. 10/2013, 91/2013, 113/2013, 109/2014 i 53/2018)
INSERT INTO specializations (id, name) VALUES
(0, 'Doktor medicine - bez specijalizacije'),
(1, 'Interna medicina'),
(2, 'Internistička onkologija'),
(3, 'Infektologija'),
(4, 'Pedijatrija'),
(5, 'Neurologija'),
(6, 'Psihijatrija'),
(7, 'Dečja neurologija'),
(8, 'Dečja i adolescentna psihijatrija'),
(9, 'Ginekologija i akušerstvo'),
(10, 'Opšta hirurgija'),
(11, 'Abdominalna hirurgija'),
(12, 'Vaskularna hirurgija'),
(13, 'Grudna hirurgija'),
(14, 'Ortopedska hirurgija i traumatologija'),
(15, 'Dečja hirurgija'),
(16, 'Neurohirurgija'),
(17, 'Plastična, rekonstruktivna i estetska hirurgija'),
(18, 'Maksilofacijalna hirurgija'),
(19, 'Urologija'),
(20, 'Kardiohirurgija'),
(21, 'Urgentna medicina'),
(22, 'Anesteziologija, reanimatologija i intenzivna terapija'),
(23, 'Otorinolaringologija'),
(24, 'Oftalmologija'),
(25, 'Dermatovenerologija'),
(26, 'Fizikalna medicina i rehabilitacija'),
(27, 'Opšta medicina'),
(28, 'Medicina rada'),
(29, 'Radiologija'),
(30, 'Radijaciona onkologija'),
(31, 'Nuklearna medicina'),
(32, 'Patologija'),
(33, 'Sudska medicina'),
(34, 'Medicinska mikrobiologija'),
(35, 'Klinička biohemija'),
(36, 'Klinička farmakologija'),
(37, 'Laboratorijska medicina'),
(38, 'Imunologija'),
(39, 'Higijena'),
(40, 'Epidemiologija'),
(41, 'Socijalna medicina'),
(42, 'Sportska medicina'),
(43, 'Transfuzijska medicina'),
(44, 'Vazduhoplovna medicina'),
(45, 'Medicinska statistika i informatika'),
(46, 'Palijativna medicina'),
(47, 'Preventivna i dečja stomatologija'),
(48, 'Bolesti zuba i endodoncija'),
(49, 'Stomatološka protetika'),
(50, 'Parodontologija i oralna medicina'),
(51, 'Ortopedija vilica'),
(52, 'Oralna hirurgija'),
(53, 'Maksilofacijalna hirurgija'),
(54, 'Medicinska statistika i informatika');

INSERT INTO doctors (id, first_name, last_name, specialization_id) VALUES
(1000000, 'Nebojša', 'Bojanić', 19);

INSERT INTO health_checks (id, description, hospital_id, created_at, user_id, doctor_id) VALUES
(1, "Pij vise vode", 1000000, "2020-04-04T08:23:21.440423", 3, 1000000);

INSERT INTO reports (id, s3file_url, health_check_id) VALUES
(1, "https://medapp-healthcheck-12042020.s3.eu-central-1.amazonaws.com/3/1/pregled1.png", 1);

INSERT INTO reports (id, s3file_url, health_check_id) VALUES
(2, "https://medapp-healthcheck-12042020.s3.eu-central-1.amazonaws.com/3/1/pregled2.png", 1);





