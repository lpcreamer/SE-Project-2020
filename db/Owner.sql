-- phpMyAdmin SQL Dump
-- version 3.4.10.1deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Dec 17, 2020 at 12:10 PM
-- Server version: 5.5.47
-- PHP Version: 5.3.10-1ubuntu3.21

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `bmls`
--

-- --------------------------------------------------------

--
-- Table structure for table `Owner`
--

CREATE TABLE IF NOT EXISTS `Owner` (
  `Name` varchar(30) NOT NULL,
  `Username` varchar(30) NOT NULL,
  `Password` varchar(255) NOT NULL,
  `Email` varchar(30) NOT NULL,
  `Phone Number` bigint(10) NOT NULL,
  `pet_type` varchar(30) NOT NULL,
  `pet_breed` varchar(30) NOT NULL,
  `pet_name` varchar(30) NOT NULL,
  `pet_care_instructions` varchar(500) NOT NULL,
  `pet_weight` int(11) NOT NULL,
  `State` varchar(2) NOT NULL,
  `Street` varchar(30) NOT NULL,
  `City` varchar(30) NOT NULL,
  `Zip Code` int(5) NOT NULL,
  PRIMARY KEY (`Username`),
  UNIQUE KEY `Email` (`Email`,`Phone Number`),
  KEY `pet_weight` (`pet_weight`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
