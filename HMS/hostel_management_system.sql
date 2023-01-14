-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 14, 2023 at 07:14 AM
-- Server version: 10.4.25-MariaDB
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hostel_management_system`
--

-- --------------------------------------------------------

--
-- Table structure for table `allotments`
--

CREATE TABLE `allotments` (
  `allotment_id` int(11) NOT NULL,
  `allotment_room_id` int(11) NOT NULL,
  `allotment_student_id` int(11) NOT NULL,
  `allotment_status` varchar(255) NOT NULL,
  `allotment_date` date NOT NULL DEFAULT current_timestamp(),
  `allotment_left_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `allotments`
--

INSERT INTO `allotments` (`allotment_id`, `allotment_room_id`, `allotment_student_id`, `allotment_status`, `allotment_date`, `allotment_left_date`) VALUES
(3, 5, 5, 'living', '2022-12-29', NULL),
(4, 16, 6, 'living', '2022-12-30', NULL),
(6, 9, 8, 'living', '2022-12-31', NULL),
(7, 5, 9, 'living', '2022-12-31', NULL),
(9, 15, 12, 'left', '2023-01-03', '2023-01-03'),
(10, 4, 13, 'living', '2023-01-11', NULL),
(11, 5, 14, 'left', '2023-01-12', '2023-01-11'),
(12, 5, 15, 'left', '2023-01-12', '2023-01-12');

-- --------------------------------------------------------

--
-- Table structure for table `rooms`
--

CREATE TABLE `rooms` (
  `room_id` int(11) NOT NULL,
  `room_no` varchar(250) NOT NULL,
  `room_status` varchar(15) NOT NULL,
  `room_students` varchar(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `rooms`
--

INSERT INTO `rooms` (`room_id`, `room_no`, `room_status`, `room_students`) VALUES
(1, '205', 'Not Avalaible', '0'),
(2, '220', 'Avalaible', '0'),
(4, '565', 'Avalaible', '1'),
(5, '676', 'Avalaible', '2'),
(6, '888', 'Not Avalaible', '0'),
(7, '9999', 'Avalaible', '0'),
(8, '100', 'Avalaible', '0'),
(9, '200', 'Avalaible', '1'),
(10, '789', 'Avalaible', '0'),
(12, '400', 'Not Avalaible', '0'),
(13, '343', 'Not Avalaible', '0'),
(15, '987', 'Avalaible', '1'),
(16, '6786', 'Avalaible', '1'),
(17, '444', 'Avalaible', '0');

-- --------------------------------------------------------

--
-- Table structure for table `students`
--

CREATE TABLE `students` (
  `student_id` int(11) NOT NULL,
  `student_name` varchar(255) NOT NULL,
  `student_f_name` varchar(255) NOT NULL,
  `student_reg_no` varchar(255) NOT NULL,
  `student_class` varchar(255) NOT NULL,
  `student_contact_no` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `students`
--

INSERT INTO `students` (`student_id`, `student_name`, `student_f_name`, `student_reg_no`, `student_class`, `student_contact_no`) VALUES
(5, 'shahmir ahmed', 'shakeel ahmed', '20-Arid-825', 'bsse-5b-M', '0345212916'),
(6, 'shehryar', 'shakeel', '765', 'bscs-1a', '03215102450'),
(8, 'abdullah sajid', 'sajid khan', '737', 'bsse-5b-m', '03218765768'),
(9, 'hamza', 'khalid', '654', 'bscs-9a', '0322678967'),
(12, 'hassan', 'ali', '20-arid-909', 'bsit-2b-e', '03453234521'),
(13, 'zain', 'nadeem', '20-arid-809', 'bsse-5b-m', '03216754321'),
(14, 'ab', 'gi', '20-arid-999', 'bg', '03452718282'),
(15, 'hammad', 'saleem', '18-arid-909', 'bscs-7b', '03219087632');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `name`, `email`, `username`, `password`) VALUES
(1, 'Shahmir Ahmed', 'ahmed1212514@gmail.com', 'shahmir_ahmed', '12345'),
(2, 'Shehryar Ahmed', 'shehryara17@gmail.com', 'shehryar_ahmed', 'password');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `allotments`
--
ALTER TABLE `allotments`
  ADD PRIMARY KEY (`allotment_id`),
  ADD KEY `fk_room_id` (`allotment_room_id`),
  ADD KEY `fk_student_id` (`allotment_student_id`);

--
-- Indexes for table `rooms`
--
ALTER TABLE `rooms`
  ADD PRIMARY KEY (`room_id`);

--
-- Indexes for table `students`
--
ALTER TABLE `students`
  ADD PRIMARY KEY (`student_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `allotments`
--
ALTER TABLE `allotments`
  MODIFY `allotment_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `rooms`
--
ALTER TABLE `rooms`
  MODIFY `room_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `students`
--
ALTER TABLE `students`
  MODIFY `student_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `allotments`
--
ALTER TABLE `allotments`
  ADD CONSTRAINT `fk_room_id` FOREIGN KEY (`allotment_room_id`) REFERENCES `rooms` (`room_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_student_id` FOREIGN KEY (`allotment_student_id`) REFERENCES `students` (`student_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
