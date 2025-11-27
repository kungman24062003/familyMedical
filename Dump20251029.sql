-- ------------------------------------------------------
-- Database creation
-- ------------------------------------------------------
CREATE DATABASE IF NOT EXISTS `medical_app`;
USE `medical_app`;

-- ------------------------------------------------------
-- Table structure for table `users`
-- ------------------------------------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL UNIQUE,
  `password` varchar(255) NOT NULL,
  `role` enum('patient','doctor','admin') DEFAULT 'patient',
  `phone` varchar(20),
  `date_of_birth` date,
  `gender` varchar(10),
  `address` text,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ------------------------------------------------------
-- Table structure for table `medical_records`
-- ------------------------------------------------------
DROP TABLE IF EXISTS `medical_records`;
CREATE TABLE `medical_records` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `doctor_id` int DEFAULT NULL,
  `diagnosis` text,
  `symptoms` text,
  `medications` text,
  `allergies` text,
  `visit_date` datetime,
  `notes` text,
  `status` enum('pending','reviewed','archived') DEFAULT 'pending',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_medical_user` (`user_id`),
  KEY `fk_medical_doctor` (`doctor_id`),
  CONSTRAINT `fk_medical_user` FOREIGN KEY (`user_id`) REFERENCES `users`(`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_medical_doctor` FOREIGN KEY (`doctor_id`) REFERENCES `users`(`id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ------------------------------------------------------
-- Table structure for table `appointments`
-- ------------------------------------------------------
DROP TABLE IF EXISTS `appointments`;
CREATE TABLE `appointments` (
  `id` int NOT NULL AUTO_INCREMENT,
  `patient_id` int NOT NULL,
  `doctor_id` int NOT NULL,
  `record_id` int DEFAULT NULL,
  `appointment_time` datetime NOT NULL,
  `reason` text,
  `status` enum('pending','confirmed','cancelled','done') DEFAULT 'pending',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_appointment_patient` (`patient_id`),
  KEY `fk_appointment_doctor` (`doctor_id`),
  KEY `fk_appointment_record` (`record_id`),
  CONSTRAINT `fk_appointment_patient` FOREIGN KEY (`patient_id`) REFERENCES `users`(`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_appointment_doctor` FOREIGN KEY (`doctor_id`) REFERENCES `users`(`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_appointment_record` FOREIGN KEY (`record_id`) REFERENCES `medical_records`(`id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ------------------------------------------------------
-- Table structure for table `chats`
-- ------------------------------------------------------
DROP TABLE IF EXISTS `chats`;
CREATE TABLE `chats` (
  `id` int NOT NULL AUTO_INCREMENT,
  `sender_id` int NOT NULL,
  `receiver_id` int NOT NULL,
  `message` text NOT NULL,
  `attachment` varchar(255),
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_chat_sender` (`sender_id`),
  KEY `fk_chat_receiver` (`receiver_id`),
  CONSTRAINT `fk_chat_sender` FOREIGN KEY (`sender_id`) REFERENCES `users`(`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_chat_receiver` FOREIGN KEY (`receiver_id`) REFERENCES `users`(`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ------------------------------------------------------
-- Table structure for table `health_stats`
-- ------------------------------------------------------
DROP TABLE IF EXISTS `health_stats`;
CREATE TABLE `health_stats` (
  `id` int NOT NULL AUTO_INCREMENT,
  `member_id` int NOT NULL,
  `blood_pressure` varchar(20),
  `heart_rate` int,
  `weight` float,
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_health_member` (`member_id`),
  CONSTRAINT `fk_health_member` FOREIGN KEY (`member_id`) REFERENCES `members`(`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ------------------------------------------------------
-- Table structure for table `logs`
-- ------------------------------------------------------
DROP TABLE IF EXISTS `logs`;
CREATE TABLE `logs` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `action` varchar(255),
  `details` text,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_logs_user` (`user_id`),
  CONSTRAINT `fk_logs_user` FOREIGN KEY (`user_id`) REFERENCES `users`(`id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ------------------------------------------------------
-- Table structure for table `households`
-- ------------------------------------------------------
DROP TABLE IF EXISTS `households`;
CREATE TABLE `households` (
  `id` int NOT NULL AUTO_INCREMENT,
  `househead_id` int,
  `address` varchar(200) DEFAULT '',
  `quantity` int DEFAULT 1,
  `is_active` tinyint(1) DEFAULT 1,
  PRIMARY KEY (`id`),
  KEY `fk_househead_user` (`househead_id`),
  CONSTRAINT `fk_househead_user` FOREIGN KEY (`househead_id`) REFERENCES `users`(`id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ------------------------------------------------------
-- Table structure for table `members`
-- ------------------------------------------------------
DROP TABLE IF EXISTS `members`;
CREATE TABLE `members` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fullname` varchar(100) NOT NULL,
  `id_card` varchar(12) NOT NULL,
  `address` varchar(200) DEFAULT '',
  `gender` enum('MALE','FEMALE','OTHER') NOT NULL,
  `date_of_birth` date,
  `bhyt` varchar(12) DEFAULT '',
  `relation` enum('CHU_HO','VO','CHONG','CON') DEFAULT 'CHU_HO',
  `household_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_member_household` (`household_id`),
  CONSTRAINT `fk_member_household` FOREIGN KEY (`household_id`) REFERENCES `households`(`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
