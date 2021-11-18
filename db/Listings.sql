-- phpMyAdmin SQL Dump
-- version 3.4.10.1deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Dec 17, 2020 at 12:11 PM
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
-- Table structure for table `Listings`
--

CREATE TABLE IF NOT EXISTS `Listings` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `Date` date NOT NULL,
  `Start Time` time NOT NULL,
  `Duration` decimal(10,1) NOT NULL,
  `Owner Username` varchar(30) NOT NULL,
  `Description` varchar(255) NOT NULL,
  `Booked` tinyint(1) NOT NULL,
  `Sitter Username` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `Username` (`Owner Username`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=28 ;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `Listings`
--
ALTER TABLE `Listings`
  ADD CONSTRAINT `Listings_ibfk_2` FOREIGN KEY (`Owner Username`) REFERENCES `Owner` (`Username`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
