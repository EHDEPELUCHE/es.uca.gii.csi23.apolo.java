-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 24-10-2023 a las 18:16:59
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `apolo`
--
CREATE DATABASE IF NOT EXISTS `apolo` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `apolo`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `excursion`
--

CREATE TABLE `excursion` (
  `id` int(11) NOT NULL,
  `nombre` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `excursion`
--

INSERT INTO `excursion` (`id`, `nombre`) VALUES
(1, 'Excursión a la Escuela de Esgrima'),
(3, 'Excursión a la Fuente de la Gran Hada'),
(2, 'Excursión a la Tienda de Bombas');

--
-- Disparadores `excursion`
--
DELIMITER $$
CREATE TRIGGER `Excursion_bi` BEFORE INSERT ON `excursion` FOR EACH ROW begin
	if NEW.nombre = '' then
    	signal sqlstate '45000' set
        	message_text = 'El nombre de la excusión no puede ser cadena vacía.';
    end if;
end
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `Excursion_bu` BEFORE UPDATE ON `excursion` FOR EACH ROW begin
	if NEW.nombre = '' then
    	signal sqlstate '45000' set
        	message_text = 'El nombre de la excursión no puede ser una cadena vacía.';
    end if;
end
$$
DELIMITER ;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `excursion`
--
ALTER TABLE `excursion`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nombre` (`nombre`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `excursion`
--
ALTER TABLE `excursion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
