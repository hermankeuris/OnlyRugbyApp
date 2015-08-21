-- phpMyAdmin SQL Dump
-- version 4.0.10.7
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Aug 21, 2015 at 10:22 AM
-- Server version: 5.5.40-cll
-- PHP Version: 5.4.23

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `leskohhl_onlyRugby`
--
CREATE DATABASE IF NOT EXISTS `leskohhl_onlyRugby` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `leskohhl_onlyRugby`;

-- --------------------------------------------------------

--
-- Table structure for table `fixtures`
--

DROP TABLE IF EXISTS `fixtures`;
CREATE TABLE IF NOT EXISTS `fixtures` (
  `fixture_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `start_time` datetime NOT NULL,
  `team_a_id` int(11) NOT NULL,
  `team_b_id` int(11) NOT NULL,
  `location` text NOT NULL,
  PRIMARY KEY (`fixture_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `matches`
--

DROP TABLE IF EXISTS `matches`;
CREATE TABLE IF NOT EXISTS `matches` (
  `match_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `score_a` int(11) NOT NULL,
  `score_b` int(11) NOT NULL,
  `team_a_id` int(11) NOT NULL,
  `team_b_id` int(11) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`match_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `matches`
--

INSERT INTO `matches` (`match_id`, `score_a`, `score_b`, `team_a_id`, `team_b_id`, `timestamp`) VALUES
(1, -1, -1, 1, 2, '2015-08-21 07:48:03');

-- --------------------------------------------------------

--
-- Table structure for table `players`
--

DROP TABLE IF EXISTS `players`;
CREATE TABLE IF NOT EXISTS `players` (
  `player_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` text NOT NULL,
  `surname` text NOT NULL,
  `height` double NOT NULL,
  `weight` double NOT NULL,
  `curr_position` int(11) NOT NULL,
  `team_id` int(11) NOT NULL,
  PRIMARY KEY (`player_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=47 ;

--
-- Dumping data for table `players`
--

INSERT INTO `players` (`player_id`, `name`, `surname`, `height`, `weight`, `curr_position`, `team_id`) VALUES
(1, 'T1P1', '', 0, 0, 1, 1),
(2, 'T1P2', '', 0, 0, 2, 1),
(3, 'T1P3', '', 0, 0, 3, 1),
(4, 'T1P4', '', 0, 0, 4, 1),
(5, 'T1P5', '', 0, 0, 5, 1),
(6, 'T1P6', '', 0, 0, 6, 1),
(7, 'T1P7', '', 0, 0, 7, 1),
(8, 'T1P8', '', 0, 0, 8, 1),
(9, 'T1P9', '', 0, 0, 9, 1),
(10, 'T1P10', '', 0, 0, 10, 1),
(11, 'T1P11', '', 0, 0, 11, 1),
(12, 'T1P12', '', 0, 0, 12, 1),
(13, 'T1P13', '', 0, 0, 13, 1),
(14, 'T1P14', '', 0, 0, 14, 1),
(15, 'T1P15', '', 0, 0, 15, 1),
(16, 'T1R1', '', 0, 0, 16, 1),
(17, 'T1R2', '', 0, 0, 17, 1),
(18, 'T1R3', '', 0, 0, 18, 1),
(19, 'T1R4', '', 0, 0, 19, 1),
(20, 'T1R5', '', 0, 0, 20, 1),
(21, 'T1R6', '', 0, 0, 21, 1),
(22, 'T1R7', '', 0, 0, 22, 1),
(23, 'T1R8', '', 0, 0, 23, 1),
(24, 'T2P1', '', 0, 0, 1, 2),
(25, 'T2P2', '', 0, 0, 2, 2),
(26, 'T2P3', '', 0, 0, 3, 2),
(27, 'T2P4', '', 0, 0, 4, 2),
(28, 'T2P5', '', 0, 0, 5, 2),
(29, 'T2P6', '', 0, 0, 6, 2),
(30, 'T2P7', '', 0, 0, 7, 2),
(31, 'T2P8', '', 0, 0, 8, 2),
(32, 'T2P9', '', 0, 0, 9, 2),
(33, 'T2P10', '', 0, 0, 10, 2),
(34, 'T2P11', '', 0, 0, 11, 2),
(35, 'T2P12', '', 0, 0, 12, 2),
(36, 'T2P13', '', 0, 0, 13, 2),
(37, 'T2P14', '', 0, 0, 14, 2),
(38, 'T2P15', '', 0, 0, 15, 2),
(39, 'T2R1', '', 0, 0, 16, 2),
(40, 'T2R2', '', 0, 0, 17, 2),
(41, 'T2R3', '', 0, 0, 18, 2),
(42, 'T2R4', '', 0, 0, 19, 2),
(43, 'T2R5', '', 0, 0, 20, 2),
(44, 'T2R6', '', 0, 0, 21, 2),
(45, 'T2R7', '', 0, 0, 22, 2),
(46, 'T2R8', '', 0, 0, 23, 2);

-- --------------------------------------------------------

--
-- Table structure for table `statistics`
--

DROP TABLE IF EXISTS `statistics`;
CREATE TABLE IF NOT EXISTS `statistics` (
  `stat_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `player_id` int(11) NOT NULL,
  `match_id` int(11) NOT NULL,
  `tries` int(11) NOT NULL,
  `conversions` int(11) NOT NULL,
  `penalty_goal` int(11) NOT NULL,
  `drop_goal` int(11) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`stat_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `teams`
--

DROP TABLE IF EXISTS `teams`;
CREATE TABLE IF NOT EXISTS `teams` (
  `team_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  PRIMARY KEY (`team_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `teams`
--

INSERT INTO `teams` (`team_id`, `name`) VALUES
(1, 'TestTeamA'),
(2, 'TestTeamB');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `users_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `surname` text NOT NULL,
  `email` text NOT NULL,
  `username` text NOT NULL,
  `password` text NOT NULL,
  `syncsts` tinyint(1) NOT NULL,
  PRIMARY KEY (`users_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`users_id`, `name`, `surname`, `email`, `username`, `password`, `syncsts`) VALUES
(1, 'koos', '', '', 'koos', 'koos', 0),
(2, 'pieter', '', '', '', '', 0),
(3, 'Herman', '', '', '', '', 0),
(4, 'admin', '', '', 'admin', 'admin', 0);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
