CREATE DATABASE  IF NOT EXISTS `student_testing_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `student_testing_db`;
-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: localhost    Database: student_testing_db
-- ------------------------------------------------------
-- Server version	8.0.13

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
-- Table structure for table `test`
--

DROP TABLE IF EXISTS `test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `test` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `topic_id` int(10) unsigned NOT NULL,
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `header` varchar(100) DEFAULT NULL,
  `is_public` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_test_topic1_idx` (`topic_id`),
  CONSTRAINT `fk_test_topic1` FOREIGN KEY (`topic_id`) REFERENCES `topic` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test`
--

LOCK TABLES `test` WRITE;
/*!40000 ALTER TABLE `test` DISABLE KEYS */;
INSERT INTO `test` VALUES (1,2,'Тест розрахований на студентів першого курсу або людей, які тільки починають вивчати с++, як першу мову програмування. Перевірте свої навички в таких темах, як базові типи даних, розгалуження, цикли, класи, структури та інше. Хочете попрактикуватися з stl? Тоді Вам тед сюди!','Основи С++',1),(2,2,'Тест дає можливість перевірити свої знання з .Net: ADO.NET, ASP.NET, ASP.NET MVC, Entity framework, .Net Core. В той же час до складу тесту включені питання з основновних мовних конструкцій','С# середній рівень',1),(3,2,'Попрактикуйтеся зі змінними, об\'єктами, DOM-моделлю документа, замиканнями і не тільки...','JavaScript базовий рівень',1),(4,2,'Перевірте свої навички фронтенду. Тест розрахований на знання осоновних HTML-тегів, CSS-селекторів, псевдоелеменів, препроцесору LESS, SVG та інше. Основи Bootstrap5 також включені до складу тесту','HTML5 + CSS + LESS',1),(5,2,'Тест на знання мови Transact-SQL, MySQL, вміння писати запити різної складності, знання нормальних форм баз даних, вміння працювати з Microsoft SQL Server, MySQL Workbench та інше. Вміння якісно проектувати реляційні бази даних також не завадить у проходженні цього тесту','SQL',1),(12,60,'123','321',0);
/*!40000 ALTER TABLE `test` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-01-03 14:36:01
