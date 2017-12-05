-- phpMyAdmin SQL Dump
-- version 4.5.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Dec 02, 2017 at 03:26 AM
-- Server version: 10.1.9-MariaDB
-- PHP Version: 5.6.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tugasakhir`
--

-- --------------------------------------------------------

--
-- Table structure for table `irs`
--

CREATE TABLE `irs` (
  `id` int(6) NOT NULL,
  `is_disetujui` tinyint(1) NOT NULL,
  `id_term` int(6) NOT NULL,
  `id_mahasiswa` int(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `irs`
--

INSERT INTO `irs` (`id`, `is_disetujui`, `id_term`, `id_mahasiswa`) VALUES
(1, 0, 5, 1),
(2, 0, 5, 2),
(3, 0, 5, 3),
(4, 0, 6, 1),
(5, 0, 6, 1),
(6, 0, 6, 1),
(7, 0, 6, 1),
(8, 0, 6, 1),
(9, 0, 6, 1),
(10, 0, 6, 1),
(11, 0, 6, 1),
(12, 0, 6, 1),
(13, 0, 6, 1),
(14, 0, 6, 1),
(15, 0, 6, 1),
(16, 0, 6, 1),
(17, 0, 6, 1),
(18, 0, 6, 1),
(19, 0, 6, 1),
(20, 0, 6, 1),
(21, 0, 6, 1),
(22, 0, 6, 1),
(23, 0, 6, 1),
(24, 0, 6, 1),
(25, 0, 6, 1),
(26, 0, 6, 1),
(27, 0, 6, 1),
(28, 0, 6, 1),
(29, 0, 6, 1),
(30, 0, 6, 1),
(31, 0, 6, 1),
(32, 0, 6, 1),
(33, 0, 6, 1),
(34, 0, 6, 1),
(35, 0, 6, 1),
(36, 0, 6, 1),
(37, 0, 6, 1),
(38, 0, 6, 1),
(39, 0, 6, 1),
(40, 0, 6, 1),
(41, 0, 6, 1),
(42, 0, 6, 1),
(43, 0, 6, 1),
(44, 0, 6, 1),
(45, 0, 6, 1),
(46, 0, 6, 1),
(47, 0, 6, 1),
(48, 0, 6, 1),
(49, 0, 6, 1),
(50, 0, 6, 1);

-- --------------------------------------------------------

--
-- Table structure for table `irs_kelas`
--

CREATE TABLE `irs_kelas` (
  `id_irs` int(6) NOT NULL,
  `id_kelas` int(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `irs_kelas`
--

INSERT INTO `irs_kelas` (`id_irs`, `id_kelas`) VALUES
(1, 1),
(2, 2),
(3, 3),
(1, 2),
(19, 1),
(20, 1),
(21, 1),
(22, 1),
(22, 3),
(23, 1),
(23, 3),
(24, 1),
(24, 3),
(25, 1),
(25, 3),
(26, 1),
(26, 3),
(27, 1),
(27, 3),
(28, 1),
(28, 3),
(29, 1),
(29, 3),
(31, 1),
(32, 1),
(31, 3),
(32, 3),
(32, 1),
(32, 3),
(33, 1),
(34, 1),
(35, 1),
(36, 1),
(36, 3),
(37, 1),
(37, 3),
(38, 1),
(38, 3),
(39, 1),
(39, 3),
(40, 1),
(40, 3),
(41, 3),
(42, 3),
(43, 1),
(43, 3),
(44, 3),
(45, 1),
(45, 3),
(46, 1),
(47, 1),
(48, 1),
(49, 2),
(49, 4),
(50, 2),
(50, 3);

-- --------------------------------------------------------

--
-- Table structure for table `mahasiswa`
--

CREATE TABLE `mahasiswa` (
  `id` int(6) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `npm` varchar(50) NOT NULL,
  `is_dapat_lulus` tinyint(1) DEFAULT NULL,
  `ip` double NOT NULL,
  `ipk` double NOT NULL,
  `sks_total` int(3) NOT NULL,
  `id_univ` int(3) NOT NULL,
  `id_fakultas` int(3) NOT NULL,
  `id_program_studi` int(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `mahasiswa`
--

INSERT INTO `mahasiswa` (`id`, `nama`, `npm`, `is_dapat_lulus`, `ip`, `ipk`, `sks_total`, `id_univ`, `id_fakultas`, `id_program_studi`) VALUES
(1, 'Rich Chigga', '1506386654', 0, 3, 3, 100, 2, 1, 1),
(2, 'Seto Mulyadi', '1406686664', 0, 3.3, 3.8, 121, 1, 1, 1),
(3, 'Dimas Bagus', '1406785644', 0, 3.23, 3.78, 105, 1, 1, 1),
(8, 'Muhammad Hasby Rosyadi', '9879798798', 0, 0, 0, 0, 1, 1, 2),
(9, 'Muhammad Hasby Rosyadi', '324353453453', 0, 0, 0, 0, 1, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `range_nilai`
--

CREATE TABLE `range_nilai` (
  `nilai_awal` int(3) NOT NULL,
  `nilai_akhir` int(3) NOT NULL,
  `nilai_huruf` varchar(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `range_nilai`
--

INSERT INTO `range_nilai` (`nilai_awal`, `nilai_akhir`, `nilai_huruf`) VALUES
(0, 40, 'E'),
(40, 55, 'D'),
(55, 60, 'C'),
(60, 65, 'C+'),
(65, 70, 'B-'),
(70, 75, 'B'),
(75, 80, 'B+'),
(80, 85, 'A-'),
(85, 100, 'A');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `enabled` tinyint(4) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`username`, `password`, `enabled`) VALUES
('admin', 'admin', 1),
('user', 'password', 1);

-- --------------------------------------------------------

--
-- Table structure for table `user_roles`
--

CREATE TABLE `user_roles` (
  `user_role_id` int(11) NOT NULL,
  `username` varchar(45) NOT NULL,
  `role` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_roles`
--

INSERT INTO `user_roles` (`user_role_id`, `username`, `role`) VALUES
(2, 'admin', 'ROLE_ADMIN'),
(1, 'user', 'ROLE_USER');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `irs`
--
ALTER TABLE `irs`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_mahasiswa` (`id_mahasiswa`),
  ADD KEY `irs_ibfk_2` (`id_term`);

--
-- Indexes for table `irs_kelas`
--
ALTER TABLE `irs_kelas`
  ADD KEY `id_irs` (`id_irs`),
  ADD KEY `id_kelas` (`id_kelas`);

--
-- Indexes for table `mahasiswa`
--
ALTER TABLE `mahasiswa`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_program_studi` (`id_program_studi`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `user_roles`
--
ALTER TABLE `user_roles`
  ADD PRIMARY KEY (`user_role_id`),
  ADD UNIQUE KEY `uni_username_role` (`role`,`username`),
  ADD KEY `fk_username_idx` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `irs`
--
ALTER TABLE `irs`
  MODIFY `id` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51;
--
-- AUTO_INCREMENT for table `mahasiswa`
--
ALTER TABLE `mahasiswa`
  MODIFY `id` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `user_roles`
--
ALTER TABLE `user_roles`
  MODIFY `user_role_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `irs_kelas`
--
ALTER TABLE `irs_kelas`
  ADD CONSTRAINT `irs_kelas_ibfk_1` FOREIGN KEY (`id_irs`) REFERENCES `irs` (`id`);

--
-- Constraints for table `user_roles`
--
ALTER TABLE `user_roles`
  ADD CONSTRAINT `fk_username` FOREIGN KEY (`username`) REFERENCES `users` (`username`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
