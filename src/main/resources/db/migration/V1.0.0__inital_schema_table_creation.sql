# CREATE DATABASE `medapp` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

DROP TABLE IF EXISTS `flyway_schema_history`;
CREATE TABLE `flyway_schema_history` (
                                         `installed_rank` int NOT NULL,
                                         `version` varchar(50) DEFAULT NULL,
                                         `description` varchar(200) NOT NULL,
                                         `type` varchar(20) NOT NULL,
                                         `script` varchar(1000) NOT NULL,
                                         `checksum` int DEFAULT NULL,
                                         `installed_by` varchar(100) NOT NULL,
                                         `installed_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                         `execution_time` int NOT NULL,
                                         `success` tinyint(1) NOT NULL,
                                         PRIMARY KEY (`installed_rank`),
                                         KEY `flyway_schema_history_s_idx` (`success`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `addresses`;
CREATE TABLE `addresses` (
                             `id` int NOT NULL AUTO_INCREMENT,
                             `city` varchar(255) DEFAULT NULL,
                             `street` varchar(255) DEFAULT NULL,
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `specializations`;
CREATE TABLE `specializations` (
                                   `id` int NOT NULL,
                                   `name` varchar(255) DEFAULT NULL,
                                   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
                         `id` int NOT NULL,
                         `email` varchar(255) DEFAULT NULL,
                         `first_name` varchar(255) NOT NULL,
                         `last_name` varchar(255) NOT NULL,
                         `password` varchar(255) NOT NULL,
                         `roles` varchar(255) DEFAULT NULL,
                         `username` varchar(255) NOT NULL,
                         `address_id` int DEFAULT NULL,
                         PRIMARY KEY (`id`),
                         KEY `FKe8vydtk7hf0y16bfm558sywbb` (`address_id`),
                         CONSTRAINT `FKe8vydtk7hf0y16bfm558sywbb` FOREIGN KEY (`address_id`) REFERENCES `addresses` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `doctors`;
CREATE TABLE `doctors` (
                           `id` int NOT NULL,
                           `all_ratings` int DEFAULT NULL,
                           `first_name` varchar(255) DEFAULT NULL,
                           `full_name` varchar(255) DEFAULT NULL,
                           `last_name` varchar(255) DEFAULT NULL,
                           `no_of_ratings` double DEFAULT NULL,
                           `rating` int DEFAULT NULL,
                           `specialization_id` int DEFAULT NULL,
                           PRIMARY KEY (`id`),
                           KEY `FK7fcr57y1el74vifvhh7dci90s` (`specialization_id`),
                           CONSTRAINT `FK7fcr57y1el74vifvhh7dci90s` FOREIGN KEY (`specialization_id`) REFERENCES `specializations` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `hospitals`;
CREATE TABLE `hospitals` (
                             `id` int NOT NULL AUTO_INCREMENT,
                             `name` varchar(255) NOT NULL,
                             `address_id` int DEFAULT NULL,
                             PRIMARY KEY (`id`),
                             KEY `FKs4cvs7saxxpsh2ospp5rsecm2` (`address_id`),
                             CONSTRAINT `FKs4cvs7saxxpsh2ospp5rsecm2` FOREIGN KEY (`address_id`) REFERENCES `addresses` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `health_checks`;
CREATE TABLE `health_checks` (
                                 `id` int NOT NULL,
                                 `created_at` datetime(6) NOT NULL,
                                 `description` varchar(255) DEFAULT NULL,
                                 `doctor_id` int NOT NULL,
                                 `hospital_id` int DEFAULT NULL,
                                 `user_id` int NOT NULL,
                                 PRIMARY KEY (`id`),
                                 KEY `FKh8s915jr8lnpnkjpptia3ifxk` (`doctor_id`),
                                 KEY `FKxi9shb0sg9or4r2urejcutmy` (`hospital_id`),
                                 KEY `FK8cdffuffot1q1t0h6silwltn6` (`user_id`),
                                 CONSTRAINT `FK8cdffuffot1q1t0h6silwltn6` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
                                 CONSTRAINT `FKh8s915jr8lnpnkjpptia3ifxk` FOREIGN KEY (`doctor_id`) REFERENCES `doctors` (`id`),
                                 CONSTRAINT `FKxi9shb0sg9or4r2urejcutmy` FOREIGN KEY (`hospital_id`) REFERENCES `hospitals` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `reports`;
CREATE TABLE `reports` (
                           `id` int NOT NULL,
                           `s3file_url` varchar(255) DEFAULT NULL,
                           `health_check_id` int DEFAULT NULL,
                           PRIMARY KEY (`id`),
                           KEY `FKbwkuiukigsws0j9imqn9xheq9` (`health_check_id`),
                           CONSTRAINT `FKbwkuiukigsws0j9imqn9xheq9` FOREIGN KEY (`health_check_id`) REFERENCES `health_checks` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence` (
    next_val INTEGER NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
INSERT INTO hibernate_sequence (next_val) VALUES (0);

DROP TABLE IF EXISTS `seq`;
CREATE TABLE `seq` (
    `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
INSERT INTO seq (next_val) VALUES (0);

