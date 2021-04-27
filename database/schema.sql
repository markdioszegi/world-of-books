-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: world_of_books
-- ------------------------------------------------------
-- Server version	8.0.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `listing_statuses`
--

DROP TABLE IF EXISTS `listing_statuses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `listing_statuses` (
  `id` int NOT NULL,
  `status_name` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `listings`
--

DROP TABLE IF EXISTS `listings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `listings` (
  `id` binary(16) NOT NULL,
  `inventory_item_location_id` binary(16) NOT NULL,
  `listing_status` int NOT NULL,
  `marketplace` int NOT NULL,
  `title` text NOT NULL,
  `description` text NOT NULL,
  `listing_price` decimal(32,2) NOT NULL,
  `currency` text NOT NULL,
  `quantity` int NOT NULL,
  `upload_time` date NOT NULL,
  `owner_email_address` text NOT NULL,
  PRIMARY KEY (`id`),
  KEY `inventory_item_location_id` (`inventory_item_location_id`),
  KEY `listing_status` (`listing_status`),
  KEY `marketplace` (`marketplace`),
  CONSTRAINT `listings_ibfk_1` FOREIGN KEY (`inventory_item_location_id`) REFERENCES `locations` (`id`),
  CONSTRAINT `listings_ibfk_2` FOREIGN KEY (`listing_status`) REFERENCES `listing_statuses` (`id`),
  CONSTRAINT `listings_ibfk_3` FOREIGN KEY (`marketplace`) REFERENCES `marketplaces` (`id`),
  CONSTRAINT `listings_chk_1` CHECK ((`listing_price` > 0)),
  CONSTRAINT `listings_chk_2` CHECK ((length(`currency`) = 3)),
  CONSTRAINT `listings_chk_3` CHECK ((`quantity` > 0)),
  CONSTRAINT `listings_chk_4` CHECK (regexp_like(`owner_email_address`,_utf8mb4'^[^@]+@[^@]+.[^@]{2,}$'))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `locations`
--

DROP TABLE IF EXISTS `locations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `locations` (
  `id` binary(16) NOT NULL,
  `manager_name` text NOT NULL,
  `phone` text NOT NULL,
  `address_primary` text NOT NULL,
  `address_secondary` text NOT NULL,
  `country` text NOT NULL,
  `town` text NOT NULL,
  `postal_code` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `marketplaces`
--

DROP TABLE IF EXISTS `marketplaces`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `marketplaces` (
  `id` int NOT NULL,
  `marketplace_name` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-04-27  5:01:05
