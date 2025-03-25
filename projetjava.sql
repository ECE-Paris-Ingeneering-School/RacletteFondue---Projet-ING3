-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : mar. 25 mars 2025 à 10:26
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

-- --------------------------------------------------------

--
-- Structure de la table `patient`
--

DROP TABLE IF EXISTS `patient`;
CREATE TABLE IF NOT EXISTS `patient` (
  `patientId` int NOT NULL,
  PRIMARY KEY (`patientId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `rdv`
--

DROP TABLE IF EXISTS `rdv`;
CREATE TABLE IF NOT EXISTS `rdv` (
  `rdvSpecialiste` int NOT NULL,
  `rdvPatient` int NOT NULL,
  `rdvDate` int NOT NULL,
  PRIMARY KEY (`rdvSpecialiste`,`rdvPatient`),
  KEY `rdvPatient` (`rdvPatient`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

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
  PRIMARY KEY (`utilisateurId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

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
-- Contraintes pour la table `rdv`
--
ALTER TABLE `rdv`
  ADD CONSTRAINT `rdv_ibfk_1` FOREIGN KEY (`rdvSpecialiste`) REFERENCES `specialiste` (`specialisteId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `rdv_ibfk_2` FOREIGN KEY (`rdvPatient`) REFERENCES `patient` (`patientId`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Contraintes pour la table `specialiste`
--
ALTER TABLE `specialiste`
  ADD CONSTRAINT `specialiste_ibfk_1` FOREIGN KEY (`specialisteId`) REFERENCES `utilisateur` (`utilisateurId`) ON DELETE RESTRICT ON UPDATE RESTRICT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
