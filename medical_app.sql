-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th12 03, 2025 lúc 06:54 PM
-- Phiên bản máy phục vụ: 10.4.32-MariaDB
-- Phiên bản PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `medical_app`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `appointments`
--

CREATE TABLE `appointments` (
  `id` int(11) NOT NULL,
  `appointment_time` datetime(6) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `reason` text DEFAULT NULL,
  `status` enum('CANCELLED','CONFIRMED','DONE','PENDING') DEFAULT NULL,
  `doctor_id` int(11) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `record_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chats`
--

CREATE TABLE `chats` (
  `id` int(11) NOT NULL,
  `attachment` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `message` text DEFAULT NULL,
  `receiver_id` int(11) DEFAULT NULL,
  `sender_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `health_stats`
--

CREATE TABLE `health_stats` (
  `id` int(11) NOT NULL,
  `blood_pressure` varchar(255) DEFAULT NULL,
  `heart_rate` int(11) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `weight` float DEFAULT NULL,
  `allergies` varchar(255) DEFAULT NULL,
  `symptoms` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `households`
--

CREATE TABLE `households` (
  `id` int(11) NOT NULL,
  `address` varchar(200) NOT NULL,
  `is_active` bit(1) NOT NULL,
  `quantity` int(11) NOT NULL,
  `doctor_id` int(11) DEFAULT NULL,
  `househead_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `households`
--

INSERT INTO `households` (`id`, `address`, `is_active`, `quantity`, `doctor_id`, `househead_id`) VALUES
(2, '331 Âu Dương Lân', b'1', 1, NULL, 5),
(3, '105 An Dương Vương', b'1', 1, NULL, 6);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `logs`
--

CREATE TABLE `logs` (
  `id` int(11) NOT NULL,
  `action` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `details` text DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `medical_records`
--

CREATE TABLE `medical_records` (
  `id` int(11) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `diagnosis` text DEFAULT NULL,
  `medications` text DEFAULT NULL,
  `notes` text DEFAULT NULL,
  `status` enum('ARCHIVED','PENDING','REVIEWED') DEFAULT NULL,
  `visit_date` datetime(6) DEFAULT NULL,
  `doctor_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `members`
--

CREATE TABLE `members` (
  `id` int(11) NOT NULL,
  `address` varchar(200) DEFAULT NULL,
  `bhyt` varchar(12) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `fullname` varchar(100) NOT NULL,
  `gender` enum('FEMALE','MALE','OTHER') NOT NULL,
  `id_card` varchar(12) NOT NULL,
  `relation` varchar(100) DEFAULT NULL,
  `household_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `members`
--

INSERT INTO `members` (`id`, `address`, `bhyt`, `date_of_birth`, `fullname`, `gender`, `id_card`, `relation`, `household_id`) VALUES
(2, '331 Âu Dương Lân', '147914141414', '2025-12-04', 'Nguyễn Gia Tài', 'MALE', '082205312456', 'Chủ hộ', 2),
(3, '105 An Dương Vương', '479797979123', NULL, 'Nguyễn Thị Thoa', 'FEMALE', '082105333333', 'Chủ hộ', 3);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `address` text DEFAULT NULL,
  `created_at` datetime(6) DEFAULT current_timestamp(6),
  `date_of_birth` date DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `role` enum('admin','doctor','patient') DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT current_timestamp(6)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `users`
--

INSERT INTO `users` (`id`, `address`, `created_at`, `date_of_birth`, `email`, `gender`, `name`, `password`, `phone`, `role`, `updated_at`) VALUES
(1, '123 Nguyễn Trãi', '2025-12-03 20:54:20.000000', '1996-10-16', 'nguyenngoc@gmail.com', 'Nữ', 'Nguyễn Ngọc', '123456', '0987654321', 'patient', '2025-12-03 20:54:20.000000'),
(5, '331 Âu Dương Lân', '2025-12-03 10:15:15.000000', '2025-12-04', 'giatai@gmail.com', 'MALE', 'Nguyễn Gia Tài', '123456', '0987654321', 'patient', '2025-12-03 10:15:15.000000'),
(6, '105 An Dương Vương', '2025-12-03 10:21:10.000000', NULL, 'thoanguyen@gmail.com', 'FEMALE', 'Nguyễn Thị Thoa', '123456', '0812345679', 'patient', '2025-12-03 10:21:10.000000');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `users_households`
--

CREATE TABLE `users_households` (
  `household_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user_households`
--

CREATE TABLE `user_households` (
  `id` int(11) NOT NULL,
  `household_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `appointments`
--
ALTER TABLE `appointments`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK6u6s6egu60m2cbdjno44jbipa` (`doctor_id`),
  ADD KEY `FKopb2h9yhin1rb4dqote8bws6w` (`patient_id`),
  ADD KEY `FK8f8h5h3xs8e95nkau05sgty6a` (`record_id`);

--
-- Chỉ mục cho bảng `chats`
--
ALTER TABLE `chats`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK6dbye15iemw6gjqt0q4q06nf1` (`receiver_id`),
  ADD KEY `FKla7peq6fislsxok7a4wxv5p36` (`sender_id`);

--
-- Chỉ mục cho bảng `health_stats`
--
ALTER TABLE `health_stats`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKf20q7cp9muq4ds194xq9nxwvd` (`user_id`);

--
-- Chỉ mục cho bảng `households`
--
ALTER TABLE `households`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKb0p0s2utbtg75y41mq1qpbwou` (`doctor_id`),
  ADD KEY `FKkhonxb4j3k7f2b2ifwah6qmpv` (`househead_id`);

--
-- Chỉ mục cho bảng `logs`
--
ALTER TABLE `logs`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKgqy8beil5y4almtq1tiyofije` (`user_id`);

--
-- Chỉ mục cho bảng `medical_records`
--
ALTER TABLE `medical_records`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKkew56covm00kqia866qlo6n4` (`doctor_id`),
  ADD KEY `FKdd9qwwn228b1yaci52j69dkp4` (`user_id`);

--
-- Chỉ mục cho bảng `members`
--
ALTER TABLE `members`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK5kqobltvr3d4ekhc65eb3a4lv` (`household_id`);

--
-- Chỉ mục cho bảng `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`);

--
-- Chỉ mục cho bảng `user_households`
--
ALTER TABLE `user_households`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKni42pojpm2km2i2lu692hxfh3` (`household_id`),
  ADD KEY `FKi230gql1mqbev7e1b62c5dhj7` (`user_id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `appointments`
--
ALTER TABLE `appointments`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `chats`
--
ALTER TABLE `chats`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `health_stats`
--
ALTER TABLE `health_stats`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `households`
--
ALTER TABLE `households`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `logs`
--
ALTER TABLE `logs`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `medical_records`
--
ALTER TABLE `medical_records`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `members`
--
ALTER TABLE `members`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT cho bảng `user_households`
--
ALTER TABLE `user_households`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `appointments`
--
ALTER TABLE `appointments`
  ADD CONSTRAINT `FK6u6s6egu60m2cbdjno44jbipa` FOREIGN KEY (`doctor_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FK8f8h5h3xs8e95nkau05sgty6a` FOREIGN KEY (`record_id`) REFERENCES `medical_records` (`id`),
  ADD CONSTRAINT `FKopb2h9yhin1rb4dqote8bws6w` FOREIGN KEY (`patient_id`) REFERENCES `users` (`id`);

--
-- Các ràng buộc cho bảng `chats`
--
ALTER TABLE `chats`
  ADD CONSTRAINT `FK6dbye15iemw6gjqt0q4q06nf1` FOREIGN KEY (`receiver_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FKla7peq6fislsxok7a4wxv5p36` FOREIGN KEY (`sender_id`) REFERENCES `users` (`id`);

--
-- Các ràng buộc cho bảng `health_stats`
--
ALTER TABLE `health_stats`
  ADD CONSTRAINT `FKf20q7cp9muq4ds194xq9nxwvd` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Các ràng buộc cho bảng `households`
--
ALTER TABLE `households`
  ADD CONSTRAINT `FKb0p0s2utbtg75y41mq1qpbwou` FOREIGN KEY (`doctor_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FKkhonxb4j3k7f2b2ifwah6qmpv` FOREIGN KEY (`househead_id`) REFERENCES `users` (`id`);

--
-- Các ràng buộc cho bảng `logs`
--
ALTER TABLE `logs`
  ADD CONSTRAINT `FKgqy8beil5y4almtq1tiyofije` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Các ràng buộc cho bảng `medical_records`
--
ALTER TABLE `medical_records`
  ADD CONSTRAINT `FKdd9qwwn228b1yaci52j69dkp4` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FKkew56covm00kqia866qlo6n4` FOREIGN KEY (`doctor_id`) REFERENCES `users` (`id`);

--
-- Các ràng buộc cho bảng `members`
--
ALTER TABLE `members`
  ADD CONSTRAINT `FK5kqobltvr3d4ekhc65eb3a4lv` FOREIGN KEY (`household_id`) REFERENCES `households` (`id`);

--
-- Các ràng buộc cho bảng `user_households`
--
ALTER TABLE `user_households`
  ADD CONSTRAINT `FKi230gql1mqbev7e1b62c5dhj7` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FKni42pojpm2km2i2lu692hxfh3` FOREIGN KEY (`household_id`) REFERENCES `households` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
