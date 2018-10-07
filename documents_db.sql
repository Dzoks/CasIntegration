-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: localhost    Database: documents_db
-- ------------------------------------------------------
-- Server version	8.0.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `document`
--

DROP TABLE IF EXISTS `document`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `document` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `document_type_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `serial_number` varchar(10) NOT NULL,
  `residence` varchar(45) NOT NULL,
  `issuing_authority` varchar(45) NOT NULL,
  `date_of_issue` timestamp NOT NULL,
  `valid_until` timestamp NOT NULL,
  `citizenship` varchar(45) DEFAULT NULL,
  `entity_citizenship` varchar(45) DEFAULT NULL,
  `country_code` varchar(3) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_document_user_idx` (`user_id`),
  KEY `fk_document_document_type1_idx` (`document_type_id`),
  CONSTRAINT `fk_document_document_type1` FOREIGN KEY (`document_type_id`) REFERENCES `document_type` (`id`),
  CONSTRAINT `fk_document_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `document`
--

LOCK TABLES `document` WRITE;
/*!40000 ALTER TABLE `document` DISABLE KEYS */;
INSERT INTO `document` VALUES (1,1,1,'540T05F31','PRNJAVOR','MUP RS, PRNJAVOR','2015-08-03 00:00:00','2025-08-03 00:00:00','BIH','REPUBLIKA SRPSKA',''),(2,2,1,'548T03K93','PRNJAVOR','MUP RS, PRNJAVOR','2015-09-30 00:00:00','2025-09-30 00:00:00','BIH','',''),(3,3,1,'B12226091','PRNJAVOR','MUP RS, PRNJAVOR','2016-11-04 01:00:00','2026-11-04 01:00:00','BIH','','BIH'),(4,3,2,'B13321021','SRBAC','MUP RS,SRBAC','2015-08-03 00:00:00','2025-08-03 00:00:00','BIH','','BIH'),(5,1,2,'540T05M33','SRBAC','MUP RS,SRBAC','2014-02-04 01:00:00','2024-02-04 01:00:00','BIH','REPUBLIKA SRPSKA','');
/*!40000 ALTER TABLE `document` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `document_has_driving_category`
--

DROP TABLE IF EXISTS `document_has_driving_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `document_has_driving_category` (
  `driving_category_id` int(11) NOT NULL,
  `document_id` int(11) NOT NULL,
  `exam_date` timestamp NOT NULL,
  PRIMARY KEY (`driving_category_id`,`document_id`),
  KEY `fk_document_type_has_driving_category_driving_category1_idx` (`driving_category_id`),
  KEY `fk_document_type_has_driving_category_document1_idx` (`document_id`),
  CONSTRAINT `fk_document_type_has_driving_category_document1` FOREIGN KEY (`document_id`) REFERENCES `document` (`id`),
  CONSTRAINT `fk_document_type_has_driving_category_driving_category1` FOREIGN KEY (`driving_category_id`) REFERENCES `driving_category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `document_has_driving_category`
--

LOCK TABLES `document_has_driving_category` WRITE;
/*!40000 ALTER TABLE `document_has_driving_category` DISABLE KEYS */;
INSERT INTO `document_has_driving_category` VALUES (2,2,'2015-09-25 00:00:00'),(3,2,'2015-09-25 00:00:00');
/*!40000 ALTER TABLE `document_has_driving_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `document_type`
--

DROP TABLE IF EXISTS `document_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `document_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `document_type`
--

LOCK TABLES `document_type` WRITE;
/*!40000 ALTER TABLE `document_type` DISABLE KEYS */;
INSERT INTO `document_type` VALUES (1,'Lična karta'),(2,'Vozačka dozvola'),(3,'Pasoš');
/*!40000 ALTER TABLE `document_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `driving_category`
--

DROP TABLE IF EXISTS `driving_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `driving_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `driving_category`
--

LOCK TABLES `driving_category` WRITE;
/*!40000 ALTER TABLE `driving_category` DISABLE KEYS */;
INSERT INTO `driving_category` VALUES (1,'A1'),(2,'B'),(3,'B1'),(4,'A'),(5,'C1'),(6,'C'),(7,'D1'),(8,'D'),(9,'BE'),(10,'C1E'),(11,'CE'),(12,'D1E'),(13,'DE');
/*!40000 ALTER TABLE `driving_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) COLLATE utf8_czech_ci NOT NULL,
  `password` varchar(256) COLLATE utf8_czech_ci DEFAULT NULL,
  `jmbg` char(13) COLLATE utf8_czech_ci NOT NULL,
  `fname` varchar(45) COLLATE utf8_czech_ci NOT NULL,
  `lname` varchar(45) COLLATE utf8_czech_ci NOT NULL,
  `male` tinyint(4) NOT NULL,
  `birth_date` date NOT NULL,
  `administrator` tinyint(4) NOT NULL,
  `token` char(6) COLLATE utf8_czech_ci DEFAULT NULL,
  `token_expiration_time` timestamp NULL DEFAULT NULL,
  `logged_in` tinyint(4) NOT NULL DEFAULT '0',
  `place_of_birth` varchar(45) COLLATE utf8_czech_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'turjacanin.djordje@gmail.com','$2a$12$uBFJsDHk5hwnB27VkUO1w.tkhf.FOOlXgtFOyTbf9UN6vzuJfs7rS','1312995100035','Djordje','Turjacanin',1,'1995-12-13',1,'449DE6','2018-10-08 13:01:02',1,'Banja Luka'),(2,'etfbl.dzoks@gmail.com ','$2a$12$qaLTYaJZ816upfKZr8WlcOSu0uagpiVtPVAoqaBNjYnjNKLTF4Cj2','1111990100036','Marko','Milosevic',1,'1990-11-11',0,NULL,NULL,0,'Srbac');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-10-07 21:49:49
