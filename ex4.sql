-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: יוני 19, 2022 בזמן 03:05 AM
-- גרסת שרת: 10.4.22-MariaDB
-- PHP Version: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ex4`
--

-- --------------------------------------------------------

--
-- מבנה טבלה עבור טבלה `book`
--

CREATE TABLE `book` (
  `id` bigint(20) NOT NULL,
  `discount` double DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` double NOT NULL,
  `quantity` int(11) NOT NULL,
  `url` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- הוצאת מידע עבור טבלה `book`
--

INSERT INTO `book` (`id`, `discount`, `name`, `price`, `quantity`, `url`) VALUES
(1, 20, 'Ugly Love', 53, 2, 'https://d1w7fb2mkkr3kw.cloudfront.net/assets/images/book/lrg/9781/4711/9781471136726.jpg'),
(2, 10, 'Seven Husbands of Evelyn Hugo : The Sunday Times Bestseller', 42, 2, 'https://d1w7fb2mkkr3kw.cloudfront.net/assets/images/book/lrg/9781/3985/9781398515697.jpg'),
(3, 20, 'Nick and Charlie', 100, 12, 'https://d1w7fb2mkkr3kw.cloudfront.net/assets/images/book/lrg/9780/0083/9780008389666.jpg'),
(4, 5, 'The Island of Missing Trees ', 40, 5, 'https://d1w7fb2mkkr3kw.cloudfront.net/assets/images/book/lrg/9780/2419/9780241988725.jpg'),
(5, 7, 'Great Circle', 70, 7, 'https://d1w7fb2mkkr3kw.cloudfront.net/assets/images/book/lrg/9781/5291/9781529176643.jpg'),
(6, 20, 'Cat And Mouse', 200, 1, 'https://d1w7fb2mkkr3kw.cloudfront.net/assets/images/book/lrg/9781/4091/9781409188506.jpg'),
(7, 9, 'PlantYou', 90, 9, 'https://d1w7fb2mkkr3kw.cloudfront.net/assets/images/book/lrg/9780/3069/9780306923043.jpg'),
(8, 15, 'Ottolenghi SIMPLE', 150, 10, 'https://d1w7fb2mkkr3kw.cloudfront.net/assets/images/book/lrg/9781/7850/9781785031168.jpg'),
(9, 5, 'Take One Fish', 50, 5, 'https://d1w7fb2mkkr3kw.cloudfront.net/assets/images/book/lrg/9781/7437/9781743796634.jpg'),
(10, 8, 'The Sentence', 80, 8, 'https://d1w7fb2mkkr3kw.cloudfront.net/assets/images/book/lrg/9780/0632/9780063205628.jpg');

--
-- Indexes for dumped tables
--

--
-- אינדקסים לטבלה `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
