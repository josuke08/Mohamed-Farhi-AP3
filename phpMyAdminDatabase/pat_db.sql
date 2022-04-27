-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 27, 2022 at 01:58 AM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.4.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pat_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `app_role`
--

CREATE TABLE `app_role` (
  `role_id` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `role_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `app_role`
--

INSERT INTO `app_role` (`role_id`, `description`, `role_name`) VALUES
(1, '', 'USER'),
(2, '', 'ADMIN');

-- --------------------------------------------------------

--
-- Table structure for table `app_user`
--

CREATE TABLE `app_user` (
  `user_id` varchar(255) NOT NULL,
  `active` bit(1) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `app_user`
--

INSERT INTO `app_user` (`user_id`, `active`, `password`, `username`) VALUES
('249f018d-b244-43dd-9972-c83bb65d7fae', b'1', '$2a$10$tilIGJXxH9.y7XBWSOhF.uZJHhGUZnosvkXK/TdZvCLDOSlCcK/36', 'momo'),
('6afcbb86-267b-4cc4-995e-afaa4efbb51f', b'1', '$2a$10$O1x0glJ.MK49hWwVRf.Yv.SakzIgER4/lFF5d8S3zWxHwjf8vq4KS', 'sut'),
('c0123df3-1c6a-4b4e-b985-6fc0a12863a4', b'1', '$2a$10$hPGvU/XMmTWPTmkYRMzaWuQ5DYNvPHjpUqErPTjaWdxi9Pgnc4Gzu', 'mohamed');

-- --------------------------------------------------------

--
-- Table structure for table `app_user_app_roles`
--

CREATE TABLE `app_user_app_roles` (
  `app_user_user_id` varchar(255) NOT NULL,
  `app_roles_role_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `app_user_app_roles`
--

INSERT INTO `app_user_app_roles` (`app_user_user_id`, `app_roles_role_id`) VALUES
('c0123df3-1c6a-4b4e-b985-6fc0a12863a4', 1),
('c0123df3-1c6a-4b4e-b985-6fc0a12863a4', 2),
('249f018d-b244-43dd-9972-c83bb65d7fae', 1),
('6afcbb86-267b-4cc4-995e-afaa4efbb51f', 1);

-- --------------------------------------------------------

--
-- Table structure for table `medecin`
--

CREATE TABLE `medecin` (
  `id` bigint(20) NOT NULL,
  `cin` varchar(255) DEFAULT NULL,
  `nom` varchar(40) DEFAULT NULL,
  `specialite` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `medecin`
--

INSERT INTO `medecin` (`id`, `cin`, `nom`, `specialite`) VALUES
(1, 'kkl', 'Hippocrates', 'cardiologie'),
(4, 'fl', 'Sutton', 'cardiologie'),
(6, 'kakl', 'Hippocrate', 'cardiologie'),
(7, 'kadl', 'TrishaPaytas', 'cardiologie'),
(9, 'fal', 'Sutton', 'cardiologie'),
(11, 'ksakl', 'Hippocrate', 'cardiologie'),
(13, 'ksasl', 'Frank', 'cardiologie'),
(14, 'fasl', 'Sutton', 'cardiologie'),
(15, 'ksakdl', 'Hippocrate', 'cardiologie'),
(16, 'ksadld', 'TrishPaytas', 'cardiologie'),
(17, 'ksasld', 'Frank', 'cardiologie'),
(18, 'fasdl', 'Sutton', 'cardiologie'),
(20, 'ksakdsl', 'Hippocrate', 'cardiologie'),
(21, 'kssadld', 'TrishPaytas', 'cardiologie'),
(22, 'ksaqsld', 'Frank', 'cardiologie'),
(23, 'fassdl', 'Sutton', 'cardiologie'),
(25, 'momp', 'Sutton', 'dermatologie');

-- --------------------------------------------------------

--
-- Table structure for table `medecin_rendez_vous`
--

CREATE TABLE `medecin_rendez_vous` (
  `medecin_id` bigint(20) NOT NULL,
  `rendez_vous_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `patient`
--

CREATE TABLE `patient` (
  `id` bigint(20) NOT NULL,
  `date_naissance` date DEFAULT NULL,
  `malade` bit(1) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `score` int(11) NOT NULL,
  `cin` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `patient`
--

INSERT INTO `patient` (`id`, `date_naissance`, `malade`, `nom`, `score`, `cin`) VALUES
(217, '2022-04-26', b'0', 'manal', 120, 'waadmgsddal54'),
(219, '2022-04-26', b'1', 'Yazid', 120, 'waadfmqdlsgsg54'),
(220, '2022-04-26', b'0', 'salos', 120, 'waagddsgdmdl54');

-- --------------------------------------------------------

--
-- Table structure for table `patient_rendez_vous`
--

CREATE TABLE `patient_rendez_vous` (
  `patient_id` bigint(20) NOT NULL,
  `rendez_vous_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `rendez_vous`
--

CREATE TABLE `rendez_vous` (
  `id` bigint(20) NOT NULL,
  `date` date DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `medecin_id` bigint(20) DEFAULT NULL,
  `patient_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `role` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`role`) VALUES
('ADMIN'),
('USER');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `username` varchar(15) NOT NULL,
  `password` varchar(255) NOT NULL,
  `active` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`username`, `password`, `active`) VALUES
('admin', '$2a$10$JcNRcylveZzz6FCNcY3pyOGR769kk74vUGYZrk8BM6O8UlK9EvS8i', 1),
('user1', '$2a$10$JcNRcylveZzz6FCNcY3pyOGR769kk74vUGYZrk8BM6O8UlK9EvS8i', 1),
('user2', '$2a$10$JcNRcylveZzz6FCNcY3pyOGR769kk74vUGYZrk8BM6O8UlK9EvS8i', 1);

-- --------------------------------------------------------

--
-- Table structure for table `users_roles`
--

CREATE TABLE `users_roles` (
  `username` varchar(15) NOT NULL,
  `role` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users_roles`
--

INSERT INTO `users_roles` (`username`, `role`) VALUES
('admin', 'ADMIN'),
('admin', 'USER'),
('user1', 'ADMIN'),
('user1', 'USER'),
('user2', 'USER');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `app_role`
--
ALTER TABLE `app_role`
  ADD PRIMARY KEY (`role_id`),
  ADD UNIQUE KEY `UK_c9vam58sxsparp1djngaittd6` (`role_name`);

--
-- Indexes for table `app_user`
--
ALTER TABLE `app_user`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `UK_3k4cplvh82srueuttfkwnylq0` (`username`);

--
-- Indexes for table `app_user_app_roles`
--
ALTER TABLE `app_user_app_roles`
  ADD KEY `FKhvbph50p74vrde5kfjjlq3297` (`app_roles_role_id`),
  ADD KEY `FK5vfowd6g3wbl0y2tpl98bvnpg` (`app_user_user_id`);

--
-- Indexes for table `medecin`
--
ALTER TABLE `medecin`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_o8i3xgu6uqmyvs50mg4c88o0f` (`cin`);

--
-- Indexes for table `medecin_rendez_vous`
--
ALTER TABLE `medecin_rendez_vous`
  ADD UNIQUE KEY `UK_11xqqn8na8ai2p3i0cebiwu5a` (`rendez_vous_id`),
  ADD KEY `FKox8f2cceti9vbdbjkocrtejws` (`medecin_id`);

--
-- Indexes for table `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_jfrdqj6v8tndwwtwaabo9l2hi` (`cin`);

--
-- Indexes for table `patient_rendez_vous`
--
ALTER TABLE `patient_rendez_vous`
  ADD UNIQUE KEY `UK_k5ad8vkn54v91aqmn7de687f3` (`rendez_vous_id`),
  ADD KEY `FK4q5v42eiip0blfhstya0kc7eg` (`patient_id`);

--
-- Indexes for table `rendez_vous`
--
ALTER TABLE `rendez_vous`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK3cfgw39eujld73uwqc3efmom2` (`medecin_id`),
  ADD KEY `FK35ayulwe26jii3vq14v6sokp3` (`patient_id`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`role`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `users_roles`
--
ALTER TABLE `users_roles`
  ADD PRIMARY KEY (`username`,`role`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `app_role`
--
ALTER TABLE `app_role`
  MODIFY `role_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `medecin`
--
ALTER TABLE `medecin`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT for table `patient`
--
ALTER TABLE `patient`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=222;

--
-- AUTO_INCREMENT for table `rendez_vous`
--
ALTER TABLE `rendez_vous`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `app_user_app_roles`
--
ALTER TABLE `app_user_app_roles`
  ADD CONSTRAINT `FK5vfowd6g3wbl0y2tpl98bvnpg` FOREIGN KEY (`app_user_user_id`) REFERENCES `app_user` (`user_id`),
  ADD CONSTRAINT `FKhvbph50p74vrde5kfjjlq3297` FOREIGN KEY (`app_roles_role_id`) REFERENCES `app_role` (`role_id`);

--
-- Constraints for table `medecin_rendez_vous`
--
ALTER TABLE `medecin_rendez_vous`
  ADD CONSTRAINT `FK7fk5sj3lkxb3yowhfd6v9h24u` FOREIGN KEY (`rendez_vous_id`) REFERENCES `rendez_vous` (`id`),
  ADD CONSTRAINT `FKox8f2cceti9vbdbjkocrtejws` FOREIGN KEY (`medecin_id`) REFERENCES `medecin` (`id`);

--
-- Constraints for table `patient_rendez_vous`
--
ALTER TABLE `patient_rendez_vous`
  ADD CONSTRAINT `FK3ygjwxi615bidadfx3xbrw9ru` FOREIGN KEY (`rendez_vous_id`) REFERENCES `rendez_vous` (`id`),
  ADD CONSTRAINT `FK4q5v42eiip0blfhstya0kc7eg` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`);

--
-- Constraints for table `rendez_vous`
--
ALTER TABLE `rendez_vous`
  ADD CONSTRAINT `FK35ayulwe26jii3vq14v6sokp3` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`),
  ADD CONSTRAINT `FK3cfgw39eujld73uwqc3efmom2` FOREIGN KEY (`medecin_id`) REFERENCES `medecin` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
