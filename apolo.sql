-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 29-11-2023 a las 15:32:01
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
  `lugar_id` int(11) NOT NULL,
  `nombre` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `excursion`
--

INSERT INTO `excursion` (`id`, `lugar_id`, `nombre`) VALUES
(1, 10, 'Excursión a la Escuela de Esgrima'),
(2, 10, 'Excursión a la Tienda de Bombas'),
(3, 1, 'Excursión a la Fuente de la Gran Hada'),
(16, 9, 'Excursión al Bar Lácteo');

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

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `lugar`
--

CREATE TABLE `lugar` (
  `id` int(11) NOT NULL,
  `nombre` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `lugar`
--

INSERT INTO `lugar` (`id`, `nombre`) VALUES
(1, 'Norte'),
(2, 'Sur'),
(3, 'Lavadero'),
(9, 'Este'),
(10, 'Oeste');

--
-- Disparadores `lugar`
--
DELIMITER $$
CREATE TRIGGER `lugar_bi` BEFORE INSERT ON `lugar` FOR EACH ROW begin
	if NEW.nombre = '' then
		signal sqlstate '45000' set 
		message_text = 'Lugar no puede ser cadena vacía.';
	end if;
end
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `lugar_bu` BEFORE UPDATE ON `lugar` FOR EACH ROW begin
	if NEW.nombre = '' then
		signal sqlstate '45000' set 
		message_text = 'Lugar no puede ser cadena vacía.';
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
  ADD UNIQUE KEY `nombre` (`nombre`),
  ADD KEY `lugar_id` (`lugar_id`);

--
-- Indices de la tabla `lugar`
--
ALTER TABLE `lugar`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `excursion`
--
ALTER TABLE `excursion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT de la tabla `lugar`
--
ALTER TABLE `lugar`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `excursion`
--
ALTER TABLE `excursion`
  ADD CONSTRAINT `excursion_ibfk_1` FOREIGN KEY (`lugar_id`) REFERENCES `lugar` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
