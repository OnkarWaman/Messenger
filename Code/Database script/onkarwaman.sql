-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jun 12, 2023 at 04:23 PM
-- Server version: 8.0.33
-- PHP Version: 7.4.3-4ubuntu2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `onkarwaman`
--

-- --------------------------------------------------------

--
-- Table structure for table `msginfo`
--

CREATE TABLE `msginfo` (
  `dateandtime` timestamp NOT NULL,
  `name` varchar(20) NOT NULL,
  `message` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `msginfo`
--

INSERT INTO `msginfo` (`dateandtime`, `name`, `message`) VALUES
('2023-05-01 22:46:41', 'User2', 'Hello Everyone, user2 here..!'),
('2023-05-01 22:51:14', 'User1', 'Hi User2, How are you'),
('2023-05-02 18:51:01', 'User3', '\n');

--
-- Triggers `msginfo`
--
DELIMITER $$
CREATE TRIGGER `timestampupdater` BEFORE INSERT ON `msginfo` FOR EACH ROW BEGIN

set new.dateandtime = CONVERT_TZ(CURRENT_TIME(),'+00:00','+05:30');

END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `name` varchar(20) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`name`, `username`, `password`) VALUES
('User1', 'user1', 'password'),
('User2', 'user2', 'password'),
('User3', 'user3', 'password');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `msginfo`
--
ALTER TABLE `msginfo`
  ADD UNIQUE KEY `dateandtime` (`dateandtime`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD UNIQUE KEY `username` (`username`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
