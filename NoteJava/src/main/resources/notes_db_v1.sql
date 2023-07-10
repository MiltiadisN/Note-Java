-- -----------------------------------------------------
-- Schema notes_db_v1
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `notes_db_v1`;

CREATE SCHEMA `notes_db_v1`;
USE `notes_db_v1` ;

--
-- Table structure for table `notes`
--

DROP TABLE IF EXISTS `notes`;

CREATE TABLE `notes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(45) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
