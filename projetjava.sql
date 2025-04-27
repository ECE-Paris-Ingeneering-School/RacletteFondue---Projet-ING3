-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : dim. 27 avr. 2025 à 12:44
-- Version du serveur : 8.0.31
-- Version de PHP : 8.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `projetjava`
--

-- --------------------------------------------------------

--
-- Structure de la table `admin`
--

DROP TABLE IF EXISTS `admin`;
CREATE TABLE IF NOT EXISTS `admin` (
  `adminId` int NOT NULL,
  PRIMARY KEY (`adminId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `admin`
--

INSERT INTO `admin` (`adminId`) VALUES
(19);

-- --------------------------------------------------------

--
-- Structure de la table `adresse`
--

DROP TABLE IF EXISTS `adresse`;
CREATE TABLE IF NOT EXISTS `adresse` (
  `adresseId` int NOT NULL,
  `adresseCodePostal` int NOT NULL,
  `adresseVille` text NOT NULL,
  `adresseRue` text NOT NULL,
  `adresseNumero` int NOT NULL,
  PRIMARY KEY (`adresseId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `adresse`
--

INSERT INTO `adresse` (`adresseId`, `adresseCodePostal`, `adresseVille`, `adresseRue`, `adresseNumero`) VALUES
(6, 92000, 'Fontenay-Aux-Roses', 'Rue des Ormeaux', 2),
(7, 92000, 'Fontenay-Aux-Roses', 'Boulevard des capucins', 69),
(9, 92260, 'Fontenay-Aux-Roses', 'Rue des Ormeaux', 9),
(10, 75017, 'Paris', 'Avenue des Ternes', 170),
(11, 75015, 'Paris', 'Avenue Emile Zola', 32),
(16, 75116, 'Paris', 'Rue de la Pompe', 12),
(17, 75116, 'Paris', 'Rue de la Tour', 51),
(18, 75015, 'Paris', 'Rue de Javel', 193);

-- --------------------------------------------------------

--
-- Structure de la table `patient`
--

DROP TABLE IF EXISTS `patient`;
CREATE TABLE IF NOT EXISTS `patient` (
  `patientId` int NOT NULL,
  PRIMARY KEY (`patientId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `patient`
--

INSERT INTO `patient` (`patientId`) VALUES
(6),
(9),
(10),
(11),
(16),
(18);

-- --------------------------------------------------------

--
-- Structure de la table `rdv`
--

DROP TABLE IF EXISTS `rdv`;
CREATE TABLE IF NOT EXISTS `rdv` (
  `rdvSpecialiste` int NOT NULL,
  `rdvPatient` int NOT NULL,
  `rdvDate` bigint NOT NULL,
  PRIMARY KEY (`rdvSpecialiste`,`rdvPatient`,`rdvDate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `rdv`
--

INSERT INTO `rdv` (`rdvSpecialiste`, `rdvPatient`, `rdvDate`) VALUES
(7, 10, 1745078400000),
(7, 16, 1744977600000),
(7, 16, 1745064000000),
(7, 16, 1745244000000),
(7, 16, 1745913600000),
(7, 18, 1744876800000),
(7, 19, 1744984800000),
(7, 19, 1745388000000),
(7, 19, 1745395200000),
(7, 19, 1745402400000),
(7, 19, 1745409600000),
(7, 19, 1745416800000),
(7, 19, 1745424000000),
(7, 19, 1745474400000),
(7, 19, 1745481600000),
(7, 19, 1745488800000),
(7, 19, 1745496000000),
(7, 19, 1745503200000),
(7, 19, 1745510400000),
(7, 19, 1745568000000),
(17, 10, 1745388000000),
(17, 16, 1744622927000),
(17, 16, 1744718400000),
(17, 16, 1745042400000),
(17, 16, 1745164800000),
(17, 16, 1745215200000),
(17, 16, 1745236800000),
(17, 19, 1744984800000);

-- --------------------------------------------------------

--
-- Structure de la table `specialiste`
--

DROP TABLE IF EXISTS `specialiste`;
CREATE TABLE IF NOT EXISTS `specialiste` (
  `specialisteId` int NOT NULL,
  `specialisteSpecialite` text NOT NULL,
  `specialisteDescription` text NOT NULL,
  `specialisteTarif` double NOT NULL,
  PRIMARY KEY (`specialisteId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `specialiste`
--

INSERT INTO `specialiste` (`specialisteId`, `specialisteSpecialite`, `specialisteDescription`, `specialisteTarif`) VALUES
(7, 'Généraliste', 'Je suis ici pour vous accompagner dans votre santé au quotidien, avec écoute et bienveillance. N\'hésitez pas à prendre rendez-vous.', 30),
(17, 'Psychologue', 'Je vous accompagne avec bienveillance dans votre cheminement personnel et vos questionnements. Ensemble, nous travaillerons à mieux comprendre vos besoins et à construire des solutions qui vous ressemblent.', 135);

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `utilisateurId` int NOT NULL AUTO_INCREMENT,
  `utilisateurNom` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `utilisateurPrenom` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `utilisateurAge` int NOT NULL,
  `utilisateurSexe` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `utilisateurMail` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `utilisateurPassword` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `utilisateurTel` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `utilisateurImage` text NOT NULL,
  PRIMARY KEY (`utilisateurId`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`utilisateurId`, `utilisateurNom`, `utilisateurPrenom`, `utilisateurAge`, `utilisateurSexe`, `utilisateurMail`, `utilisateurPassword`, `utilisateurTel`, `utilisateurImage`) VALUES
(6, 'Peletier', 'Diane', 45, 'F', 'diane.peletier@gmail.com', 'a', '0746967319', ''),
(7, 'Cartier', '', 0, 'H', '', '', '', 'src/Images/7_profile_image.png'),
(9, 'Girard', 'Christine', 65, 'F', 'christinegirard@gmail.com', 'a', '0665839561', ''),
(10, 'Ducasse', 'Pierre', 54, 'H', 'pierre.ducasse@hotmail.com', 'a', '0782485281', 'src/Images/10_profile_image.jpeg'),
(11, 'Morel', 'Eugénie', 28, 'F', 'morel.eugenie@gmail.com', 'a', '0697359724', ''),
(16, 'Breguet', 'Laurent', 32, 'H', 'lbreguet@gmail.com', 'a', '0643972710', 'src/Images/16_profile_image.jpeg'),
(17, 'Cochet', '', 0, 'H', '', '', '', 'src/Images/17_profile_image.jpg'),
(18, 'Dumontier', 'Pascal', 53, 'H', 'pascal.dumontier@hotmail.com', 'a', '0684957629', ''),
(19, 'ADMIN', 'ADMIN', 100, 'H', 'admin', 'admin', '00', '');

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `admin`
--
ALTER TABLE `admin`
  ADD CONSTRAINT `admin_ibfk_1` FOREIGN KEY (`adminId`) REFERENCES `utilisateur` (`utilisateurId`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Contraintes pour la table `adresse`
--
ALTER TABLE `adresse`
  ADD CONSTRAINT `adresse_ibfk_1` FOREIGN KEY (`adresseId`) REFERENCES `utilisateur` (`utilisateurId`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Contraintes pour la table `patient`
--
ALTER TABLE `patient`
  ADD CONSTRAINT `patient_ibfk_1` FOREIGN KEY (`patientId`) REFERENCES `utilisateur` (`utilisateurId`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Contraintes pour la table `specialiste`
--
ALTER TABLE `specialiste`
  ADD CONSTRAINT `specialiste_ibfk_1` FOREIGN KEY (`specialisteId`) REFERENCES `utilisateur` (`utilisateurId`) ON DELETE RESTRICT ON UPDATE RESTRICT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
