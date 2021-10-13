SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

INSERT INTO `admin` (`id`, `username`, `password`, `role`) VALUES
(1, 'test', '$2a$12$WVbut.2BADbtUFxrJ0E6EOznb/IeH5R0uh0ivRIwx3Lx2Qr1fqWh.', 'ROLE_ADMIN'),
(2, 'admin', '$2a$12$nF2pw0ZPBzrsn9zVunxIU.aFDwXR4kvDKcf2sExqkwe7lh9GFvSPW', 'ROLE_ADMIN');

INSERT INTO `client` (`id`, `nom_complet`, `telephone`, `email`, `adresse`) VALUES
(2, 'Manon Fanfrelune', '0235668917', 'm.fanfrelune@company.gov', '56 rue de la Loire, 44000 Nantes'),
(3, 'Thierry Lalongue', '0341274302', 'thierrylalongueg@yahoo.com', '1bis rue des Pommiers, 78000 Versailles'),
(5, 'Crique Lucien', '0688297210', 'luciencrique@random.com', '16 boulevard de la République'),
(6, 'Trouville Philippe', '0453672800', 'phil-trouville@gmail.com', '17bis rue du Temple');

INSERT INTO `hotel` (`id`, `nom`, `etoiles`, `adresse`, `telephone`, `email`, `ville`) VALUES
(1, 'Saint-Christophe', 3, '12 avenue du Maréchal Leclerc', '0144223817', 'hotel-stchristophe@resa.com', 'Marseille'),
(2, 'Malte', 4, '118 boulevard Saint-Martin', '0145323976', 'hotelmalte@resa.fr', 'Paris'),
(3, 'Le Mariott', 5, '15 avenue Polichinelle', '0128736640', 'mariott@hotel.com', 'St Tropez'),
(4, 'El Sombrero', 2, '64 rue de la Fourmi', '0128836640', 'elsombrero@hotmail.com', 'Nice');

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
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
