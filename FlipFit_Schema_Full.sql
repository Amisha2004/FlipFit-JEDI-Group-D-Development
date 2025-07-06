-- MySQL dump 10.13  Distrib 8.0.42, for macos15.2 (arm64)
--
-- Host: localhost    Database: FlipFit_Schema
-- ------------------------------------------------------
-- Server version	8.0.42

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
-- Table structure for table `Booking`
--

DROP TABLE IF EXISTS `Booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Booking` (
  `bookingId` int NOT NULL AUTO_INCREMENT,
  `userId` int NOT NULL,
  `slotId` int NOT NULL,
  `status` varchar(45) NOT NULL DEFAULT 'Pending',
  PRIMARY KEY (`bookingId`),
  UNIQUE KEY `bookingId_UNIQUE` (`bookingId`),
  KEY `booking_user_id_idx` (`userId`),
  CONSTRAINT `booking_user_id` FOREIGN KEY (`userId`) REFERENCES `User` (`userId`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Booking`
--

LOCK TABLES `Booking` WRITE;
/*!40000 ALTER TABLE `Booking` DISABLE KEYS */;
INSERT INTO `Booking` VALUES (4,913,3,'Pending'),(6,914,3,'Pending');
/*!40000 ALTER TABLE `Booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `GymCentre`
--

DROP TABLE IF EXISTS `GymCentre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `GymCentre` (
  `gymId` int NOT NULL AUTO_INCREMENT,
  `gymName` varchar(255) NOT NULL,
  `ownerId` int NOT NULL,
  `approvalStatus` tinyint NOT NULL,
  `city` varchar(45) NOT NULL,
  `state` varchar(45) NOT NULL,
  `pincode` varchar(45) NOT NULL,
  PRIMARY KEY (`gymId`),
  KEY `fk_owner_Id` (`ownerId`),
  CONSTRAINT `fk_owner_Id` FOREIGN KEY (`ownerId`) REFERENCES `GymOwner` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `GymCentre`
--

LOCK TABLES `GymCentre` WRITE;
/*!40000 ALTER TABLE `GymCentre` DISABLE KEYS */;
INSERT INTO `GymCentre` VALUES (8,'gy1',912,1,'fshg','jhj','123456');
/*!40000 ALTER TABLE `GymCentre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `GymCustomer`
--

DROP TABLE IF EXISTS `GymCustomer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `GymCustomer` (
  `userId` int NOT NULL,
  `paymentInfo` varchar(45) NOT NULL,
  KEY `userId.idx` (`userId`),
  CONSTRAINT `fk_GymCustomer_userId_new` FOREIGN KEY (`userId`) REFERENCES `User` (`userId`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `GymCustomer`
--

LOCK TABLES `GymCustomer` WRITE;
/*!40000 ALTER TABLE `GymCustomer` DISABLE KEYS */;
INSERT INTO `GymCustomer` VALUES (913,'UPI');
/*!40000 ALTER TABLE `GymCustomer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `GymOwner`
--

DROP TABLE IF EXISTS `GymOwner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `GymOwner` (
  `userId` int NOT NULL,
  `panId` varchar(45) NOT NULL,
  `gstNumber` varchar(45) NOT NULL,
  `aadharNumber` varchar(45) NOT NULL,
  `isApproved` tinyint NOT NULL,
  UNIQUE KEY `aadharNumber_UNIQUE` (`aadharNumber`),
  UNIQUE KEY `gstNumber_UNIQUE` (`gstNumber`),
  UNIQUE KEY `panId_UNIQUE` (`panId`),
  UNIQUE KEY `fk_GymOwner_userId_idx` (`userId`),
  CONSTRAINT `fk_GymOwner_userId` FOREIGN KEY (`userId`) REFERENCES `User` (`userId`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `GymOwner`
--

LOCK TABLES `GymOwner` WRITE;
/*!40000 ALTER TABLE `GymOwner` DISABLE KEYS */;
INSERT INTO `GymOwner` VALUES (912,'1234567890','1234567890','1234567890',1),(103,'ABCDE1234F','29ABCDE1234F1Z9','123456789012',1);
/*!40000 ALTER TABLE `GymOwner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Payments`
--

DROP TABLE IF EXISTS `Payments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Payments` (
  `paymentId` int NOT NULL AUTO_INCREMENT,
  `userId` int NOT NULL,
  `paymentInfo` varchar(45) NOT NULL,
  PRIMARY KEY (`paymentId`),
  UNIQUE KEY `paymentId_UNIQUE` (`paymentId`),
  KEY `user_payment_id_idx` (`userId`),
  CONSTRAINT `user_payment_id` FOREIGN KEY (`userId`) REFERENCES `User` (`userId`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Payments`
--

LOCK TABLES `Payments` WRITE;
/*!40000 ALTER TABLE `Payments` DISABLE KEYS */;
INSERT INTO `Payments` VALUES (3,913,'UPI');
/*!40000 ALTER TABLE `Payments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Role`
--

DROP TABLE IF EXISTS `Role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Role` (
  `roleId` int NOT NULL,
  `roleName` varchar(45) NOT NULL,
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Role`
--

LOCK TABLES `Role` WRITE;
/*!40000 ALTER TABLE `Role` DISABLE KEYS */;
INSERT INTO `Role` VALUES (1,'Admin'),(2,'GymOwner'),(3,'GymCustomer');
/*!40000 ALTER TABLE `Role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Slots`
--

DROP TABLE IF EXISTS `Slots`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Slots` (
  `slotId` int NOT NULL AUTO_INCREMENT,
  `gymId` int NOT NULL,
  `slotTime` time NOT NULL,
  `seatsAvailable` int NOT NULL,
  `maxCapacity` int NOT NULL,
  `slotDate` date NOT NULL,
  PRIMARY KEY (`slotId`),
  UNIQUE KEY `slotId_UNIQUE` (`slotId`),
  KEY `fk_slots_gymId` (`gymId`),
  CONSTRAINT `fk_slots_gymId` FOREIGN KEY (`gymId`) REFERENCES `GymCentre` (`gymId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Slots`
--

LOCK TABLES `Slots` WRITE;
/*!40000 ALTER TABLE `Slots` DISABLE KEYS */;
INSERT INTO `Slots` VALUES (3,8,'14:30:00',47,100,'2003-07-25');
/*!40000 ALTER TABLE `Slots` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `User` (
  `userId` int NOT NULL AUTO_INCREMENT,
  `userName` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `emailId` varchar(45) NOT NULL,
  `phoneNumber` varchar(45) NOT NULL,
  `city` varchar(45) NOT NULL,
  `pinCode` varchar(45) NOT NULL,
  `roleId` int NOT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE KEY `userId_UNIQUE` (`userId`),
  KEY `fk_user_role` (`roleId`),
  CONSTRAINT `fk_user_role` FOREIGN KEY (`roleId`) REFERENCES `Role` (`roleId`) ON UPDATE CASCADE,
  CONSTRAINT `CHK_Email_Format` CHECK (regexp_like(`emailId`,_utf8mb4'^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$')),
  CONSTRAINT `CHK_Password_Strength` CHECK (regexp_like(`password`,_utf8mb4'^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[^A-Za-z0-9]).{12,}$')),
  CONSTRAINT `CHK_PhoneNumber_Length` CHECK (regexp_like(`phoneNumber`,_utf8mb4'^[0-9]{10}$')),
  CONSTRAINT `CHK_PinCode_Length` CHECK (regexp_like(`pinCode`,_utf8mb4'^[0-9]{6}$'))
) ENGINE=InnoDB AUTO_INCREMENT=933 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` VALUES (103,'Kyle','Protector@1984','kyle.r@future.net','9678901234','Los Angeles','900011',2),(106,'Flipkart','flip1234#$%tyhujik','flipk@flipkart.com','8687890000','Bengaluru','899010',1),(912,'a123','A1a@#$%^&*()_RT','a@gmail.com','1234567890','Delhi','678901',2),(913,'adfr123','Asdftg@#$%^&1*()','adfr@gmail.com','1234567890','Delhi','678901',3),(914,'Amisha','Amisha123@#$%%^','amisha@gmail.com','8790892789','Patna','890123',3);
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-07-06 23:14:00
