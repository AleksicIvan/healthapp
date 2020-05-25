INSERT INTO doctors (id, first_name, last_name, full_name, no_of_ratings, rating, all_ratings, specialization_id) VALUES
(1000000, 'Nebojša', 'Bojanić', 'Nebojša Bojanić', 1.0, 4, 4, 20);

INSERT INTO doctors (id, first_name, last_name, full_name, no_of_ratings, rating, all_ratings, specialization_id) VALUES
(1000001, 'Uroš', 'Mihajlović', 'Uroš Mihajlović', 1.0, 2, 2, 2);

INSERT INTO health_checks (id, description, doctor_rating, hospital_id, created_at, user_id, doctor_id) VALUES
(1, "Pij vise vode", 5, 1000000, "2020-04-04T08:23:21.440423", 3, 1000000);

INSERT INTO reports (id, s3file_url, health_check_id) VALUES
(1, "https://medapp-healthcheck-12042020.s3.eu-central-1.amazonaws.com/3/1/pregled1.png", 1);

INSERT INTO reports (id, s3file_url, health_check_id) VALUES
(2, "https://medapp-healthcheck-12042020.s3.eu-central-1.amazonaws.com/3/1/pregled2.png", 1);
