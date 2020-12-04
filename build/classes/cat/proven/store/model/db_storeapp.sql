/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Chems
 * Created: 13-ene-2020
 */

-- Create user for local access.
CREATE USER 'storeapp'@'localhost' IDENTIFIED BY 'psstore';
-- Create database.
CREATE DATABASE dbstore
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;
-- Grant permissions.
GRANT SELECT, INSERT, UPDATE, DELETE ON dbstore.* TO 'storeapp'@'localhost';
-- Use database.
USE dbstore;
-- Create table 'countries'
CREATE TABLE `products` (
`code` varchar(10) NOT NULL,
`description` varchar(40) NOT NULL,
`price` double default NULL,
`stock` int default 0,
PRIMARY KEY (`code`)
) ENGINE=InnoDB;
-- Data insertion.
INSERT INTO `products` (`code`, `description`, `price`, `stock`)
VALUES
('C01','desc01',12546.1,60),
('C02','desc02',473.1,37),
('C03','desc03',444.1,1),
('C04','desc04',789.1,60),
('C05','desc05',222.1,45),
('C40','desc40',666.1,66),
('C41','desc41',777,7);