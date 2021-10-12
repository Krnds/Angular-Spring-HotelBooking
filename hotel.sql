-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mar. 12 oct. 2021 à 12:14
-- Version du serveur : 10.4.19-MariaDB
-- Version de PHP : 8.0.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `hotel`
--
CREATE DATABASE IF NOT EXISTS `hotel` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `hotel`;

-- --------------------------------------------------------

--
-- Structure de la table `admin`
--

CREATE TABLE IF NOT EXISTS `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `role` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `admin`
--

INSERT INTO `admin` (`id`, `username`, `password`, `role`) VALUES
(1, 'test', '$2a$12$WVbut.2BADbtUFxrJ0E6EOznb/IeH5R0uh0ivRIwx3Lx2Qr1fqWh.', 'ROLE_ADMIN'),
(2, 'admin', '$2a$12$nF2pw0ZPBzrsn9zVunxIU.aFDwXR4kvDKcf2sExqkwe7lh9GFvSPW', 'ROLE_ADMIN');

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE IF NOT EXISTS `client` (
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `nom_complet` varchar(100) NOT NULL,
  `telephone` varchar(10) NOT NULL,
  `email` varchar(100) NOT NULL,
  `adresse` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`id`, `nom_complet`, `telephone`, `email`, `adresse`) VALUES
(2, 'Manon Fanfrelune', '0235668917', 'm.fanfrelune@company.gov', '56 rue de la Loire, 44000 Nantes'),
(3, 'Thierry Lalongue', '0341274302', 'thierrylalongueg@yahoo.com', '1bis rue des Pommiers, 78000 Versailles'),
(5, 'Crique Lucien', '0688297210', 'luciencrique@random.com', '16 boulevard de la République, 31000 Toulouse'),
(6, 'Trouville Philippe', '0453672800', 'phil-trouville@gmail.com', '17bis rue du Temple, 75000 Paris');

-- --------------------------------------------------------

--
-- Structure de la table `hotel`
--

CREATE TABLE IF NOT EXISTS `hotel` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) NOT NULL,
  `etoiles` int(1) NOT NULL,
  `adresse` varchar(100) NOT NULL,
  `telephone` varchar(10) NOT NULL,
  `email` varchar(50) NOT NULL,
  `ville` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `hotel`
--

INSERT INTO `hotel` (`id`, `nom`, `etoiles`, `adresse`, `telephone`, `email`, `ville`) VALUES
(1, 'Saint-Christophe', 3, '12 avenue du Maréchal Leclerc', '0144223817', 'hotel-stchristophe@resa.com', 'Marseille'),
(2, 'Malte', 4, '118 boulevard Saint-Martin', '0145323976', 'hotelmalte@resa.fr', 'Paris'),
(3, 'Le Mariott', 5, '15 avenue Polichinelle', '0128736640', 'mariott@hotel.com', 'St Tropez'),
(4, 'El Sombrero', 2, '64 rue de la Fourmi', '0128836640', 'elsombrero@hotmail.com', 'Nice');

-- --------------------------------------------------------

--
-- Structure de la table `reservation`
--

CREATE TABLE IF NOT EXISTS `reservation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `client` int(11) NOT NULL,
  `hotel` int(11) NOT NULL,
  `date_debut` date NOT NULL DEFAULT current_timestamp(),
  `date_fin` date NOT NULL,
  `num_chambre` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_client` (`client`),
  KEY `fk_hotel` (`hotel`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `reservation`
--

INSERT INTO `reservation` (`id`, `client`, `hotel`, `date_debut`, `date_fin`, `num_chambre`) VALUES
(1, 2, 3, '2021-10-08', '2021-10-10', 233),
(2, 3, 2, '2021-10-14', '2021-10-16', 26),
(3, 2, 4, '2021-11-08', '2021-11-10', 404),
(4, 3, 1, '2021-10-08', '2021-10-10', 3),
(8, 5, 2, '2021-12-24', '2022-01-03', 99),
(9, 2, 2, '2021-12-26', '2021-12-29', 500),
(10, 3, 2, '2021-12-20', '2021-12-25', 500),
(13, 5, 4, '2021-10-27', '2021-10-28', 3),
(14, 6, 2, '2021-10-22', '2021-10-30', 77);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `fk_client` FOREIGN KEY (`client`) REFERENCES `client` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_hotel` FOREIGN KEY (`hotel`) REFERENCES `hotel` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
