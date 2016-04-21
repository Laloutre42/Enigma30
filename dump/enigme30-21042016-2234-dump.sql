-- MySQL dump 10.13  Distrib 5.6.28, for Win64 (x86_64)
--
-- Host: localhost    Database: enigme30
-- ------------------------------------------------------
-- Server version	5.6.28-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `databasechangelog`
--

DROP TABLE IF EXISTS `databasechangelog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `databasechangelog` (
  `ID` varchar(255) NOT NULL,
  `AUTHOR` varchar(255) NOT NULL,
  `FILENAME` varchar(255) NOT NULL,
  `DATEEXECUTED` datetime NOT NULL,
  `ORDEREXECUTED` int(11) NOT NULL,
  `EXECTYPE` varchar(10) NOT NULL,
  `MD5SUM` varchar(35) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `COMMENTS` varchar(255) DEFAULT NULL,
  `TAG` varchar(255) DEFAULT NULL,
  `LIQUIBASE` varchar(20) DEFAULT NULL,
  `CONTEXTS` varchar(255) DEFAULT NULL,
  `LABELS` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `databasechangelog`
--

LOCK TABLES `databasechangelog` WRITE;
/*!40000 ALTER TABLE `databasechangelog` DISABLE KEYS */;
INSERT INTO `databasechangelog` VALUES ('00000000000001','jhipster','classpath:config/liquibase/changelog/00000000000000_initial_schema.xml','2016-04-02 12:59:34',1,'EXECUTED','7:e5d421759980df5ea9b5cd2ebcfd994c','createTable, createIndex (x2), createTable (x2), addPrimaryKey, createTable, addForeignKeyConstraint (x3), loadData, dropDefaultValue, loadData (x2), createTable (x2), addPrimaryKey, createIndex (x2), addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL),('00000000000002','azd','classpath:config/liquibase/changelog/00000000000001_update_enigma.xml','2016-04-02 12:59:34',2,'EXECUTED','7:62d0bb510b4a4b01a41a66fbe1a0e889','createTable','',NULL,'3.4.2',NULL,NULL),('00000000000003','azd','classpath:config/liquibase/changelog/00000000000002_update_enigma_csv.xml','2016-04-02 12:59:34',3,'EXECUTED','7:da02c354d9eec7f1e603534ccd0cb29d','loadData','',NULL,'3.4.2',NULL,NULL),('00000000000004','azd','classpath:config/liquibase/changelog/00000000000003_update_enigma_execution.xml','2016-04-02 12:59:34',4,'EXECUTED','7:bd820e35e7bfc97ea8428a35fb622990','createTable','',NULL,'3.4.2',NULL,NULL);
/*!40000 ALTER TABLE `databasechangelog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `databasechangeloglock`
--

DROP TABLE IF EXISTS `databasechangeloglock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `databasechangeloglock` (
  `ID` int(11) NOT NULL,
  `LOCKED` bit(1) NOT NULL,
  `LOCKGRANTED` datetime DEFAULT NULL,
  `LOCKEDBY` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `databasechangeloglock`
--

LOCK TABLES `databasechangeloglock` WRITE;
/*!40000 ALTER TABLE `databasechangeloglock` DISABLE KEYS */;
INSERT INTO `databasechangeloglock` VALUES (1,'\0',NULL,NULL);
/*!40000 ALTER TABLE `databasechangeloglock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `enigma`
--

DROP TABLE IF EXISTS `enigma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `enigma` (
  `id` bigint(20) NOT NULL,
  `number` int(11) DEFAULT NULL,
  `question` varchar(255) DEFAULT NULL,
  `answer` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enigma`
--

LOCK TABLES `enigma` WRITE;
/*!40000 ALTER TABLE `enigma` DISABLE KEYS */;
INSERT INTO `enigma` VALUES (1,1,'Le Carré Virgule','Rond Point'),(2,2,'La citadelle de bois sculpté','Château Rochetaillée'),(3,3,'Le caveau de la Maurienne','Cimetière Tarentaise'),(4,4,'Goulet du café rose','Thélis Combe'),(5,5,'Ton écharpe','Montchal'),(6,6,'Le HLM dans la cité','Maison Dans Nature');
/*!40000 ALTER TABLE `enigma` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `enigma_execution`
--

DROP TABLE IF EXISTS `enigma_execution`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `enigma_execution` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `enigma_id` bigint(20) NOT NULL,
  `duration` bigint(20) NOT NULL,
  `answer` varchar(255) NOT NULL,
  `is_ok` tinyint(4) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FK_iu0qs7bit2u4tojeyfarmtv79` (`enigma_id`),
  KEY `FK_oschwt0kses16s3sk1j2yf63` (`user_id`),
  CONSTRAINT `FK_iu0qs7bit2u4tojeyfarmtv79` FOREIGN KEY (`enigma_id`) REFERENCES `enigma` (`id`),
  CONSTRAINT `FK_oschwt0kses16s3sk1j2yf63` FOREIGN KEY (`user_id`) REFERENCES `jhi_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enigma_execution`
--

LOCK TABLES `enigma_execution` WRITE;
/*!40000 ALTER TABLE `enigma_execution` DISABLE KEYS */;
/*!40000 ALTER TABLE `enigma_execution` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jhi_authority`
--

DROP TABLE IF EXISTS `jhi_authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jhi_authority` (
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jhi_authority`
--

LOCK TABLES `jhi_authority` WRITE;
/*!40000 ALTER TABLE `jhi_authority` DISABLE KEYS */;
INSERT INTO `jhi_authority` VALUES ('ROLE_ADMIN'),('ROLE_USER');
/*!40000 ALTER TABLE `jhi_authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jhi_persistent_audit_event`
--

DROP TABLE IF EXISTS `jhi_persistent_audit_event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jhi_persistent_audit_event` (
  `event_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `principal` varchar(255) NOT NULL,
  `event_date` timestamp NULL DEFAULT NULL,
  `event_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`event_id`),
  KEY `idx_persistent_audit_event` (`principal`,`event_date`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jhi_persistent_audit_event`
--

LOCK TABLES `jhi_persistent_audit_event` WRITE;
/*!40000 ALTER TABLE `jhi_persistent_audit_event` DISABLE KEYS */;
INSERT INTO `jhi_persistent_audit_event` VALUES (1,'admin','2016-04-16 06:27:01','AUTHENTICATION_SUCCESS'),(2,'user','2016-04-16 06:49:53','AUTHENTICATION_SUCCESS'),(3,'admin','2016-04-17 17:52:08','AUTHENTICATION_SUCCESS'),(4,'zdziobeck.arnaud@gmail.com','2016-04-17 17:52:36','AUTHENTICATION_FAILURE'),(5,'zdziobeck.arnaud@gmail.com','2016-04-17 17:53:30','AUTHENTICATION_FAILURE'),(6,'arnaud','2016-04-17 17:53:39','AUTHENTICATION_SUCCESS'),(7,'natacha.vray@gmail.com','2016-04-18 15:44:30','AUTHENTICATION_FAILURE'),(8,'natacha.vray@gmail.com','2016-04-18 15:44:37','AUTHENTICATION_FAILURE'),(9,'natacha.vray@gmail.com','2016-04-18 15:44:40','AUTHENTICATION_FAILURE'),(10,'natacha.vray@gmail.com','2016-04-18 15:44:47','AUTHENTICATION_FAILURE'),(11,'natacha.vray@gmail.com','2016-04-18 16:52:31','AUTHENTICATION_FAILURE'),(12,'natacha.vray@gmail.com','2016-04-18 16:52:53','AUTHENTICATION_FAILURE'),(13,'nat','2016-04-18 16:53:01','AUTHENTICATION_SUCCESS'),(14,'user','2016-04-18 16:58:32','AUTHENTICATION_SUCCESS'),(15,'user','2016-04-18 16:59:32','AUTHENTICATION_SUCCESS'),(16,'zdziobeck.arnaud@gmail.com','2016-04-18 17:02:26','AUTHENTICATION_FAILURE'),(17,'user','2016-04-18 17:02:36','AUTHENTICATION_SUCCESS'),(18,'Odilepointeau@gmail.com','2016-04-19 15:29:50','AUTHENTICATION_FAILURE'),(19,'odilepointeau@gmail.com','2016-04-19 15:31:33','AUTHENTICATION_FAILURE'),(20,'odilepointeau@gmail.com','2016-04-19 15:31:45','AUTHENTICATION_FAILURE'),(21,'odile','2016-04-19 15:31:57','AUTHENTICATION_SUCCESS'),(22,'thomas','2016-04-19 18:06:38','AUTHENTICATION_FAILURE'),(23,'thomas','2016-04-19 18:06:45','AUTHENTICATION_SUCCESS'),(24,'arnaud','2016-04-20 09:08:45','AUTHENTICATION_FAILURE'),(25,'user','2016-04-20 09:08:49','AUTHENTICATION_SUCCESS'),(26,'user','2016-04-20 10:23:52','AUTHENTICATION_SUCCESS'),(27,'bellet','2016-04-20 10:24:42','AUTHENTICATION_SUCCESS'),(28,'bellet','2016-04-20 11:54:53','AUTHENTICATION_SUCCESS'),(29,'admin','2016-04-21 20:12:56','AUTHENTICATION_SUCCESS'),(30,'user','2016-04-21 20:23:06','AUTHENTICATION_SUCCESS');
/*!40000 ALTER TABLE `jhi_persistent_audit_event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jhi_persistent_audit_evt_data`
--

DROP TABLE IF EXISTS `jhi_persistent_audit_evt_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jhi_persistent_audit_evt_data` (
  `event_id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`event_id`,`name`),
  KEY `idx_persistent_audit_evt_data` (`event_id`),
  CONSTRAINT `fk_evt_pers_audit_evt_data` FOREIGN KEY (`event_id`) REFERENCES `jhi_persistent_audit_event` (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jhi_persistent_audit_evt_data`
--

LOCK TABLES `jhi_persistent_audit_evt_data` WRITE;
/*!40000 ALTER TABLE `jhi_persistent_audit_evt_data` DISABLE KEYS */;
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (1,'remoteAddress','0:0:0:0:0:0:0:1'),(1,'sessionId','04CE7F47034342EF0A7550E3C8F4F566'),(2,'remoteAddress','0:0:0:0:0:0:0:1'),(2,'sessionId','3C9077C3FCF6590BCA877F9987FA5729'),(3,'remoteAddress','78.226.169.202'),(3,'sessionId','A6784C582BAB1973C23927BBC5C95FC2'),(4,'message','Bad credentials'),(4,'type','org.springframework.security.authentication.BadCredentialsException'),(5,'message','Bad credentials'),(5,'type','org.springframework.security.authentication.BadCredentialsException'),(6,'remoteAddress','78.226.169.202'),(6,'sessionId','F5550AAC87309B4088A1E8FE5F391969'),(7,'message','Bad credentials'),(7,'type','org.springframework.security.authentication.BadCredentialsException'),(8,'message','Bad credentials'),(8,'type','org.springframework.security.authentication.BadCredentialsException'),(9,'message','Bad credentials'),(9,'type','org.springframework.security.authentication.BadCredentialsException'),(10,'message','Bad credentials'),(10,'type','org.springframework.security.authentication.BadCredentialsException'),(11,'message','Bad credentials'),(11,'type','org.springframework.security.authentication.BadCredentialsException'),(12,'message','Bad credentials'),(12,'type','org.springframework.security.authentication.BadCredentialsException'),(13,'remoteAddress','78.226.169.202'),(13,'sessionId','88A50674E15733D3ED99A41C831640DB'),(14,'remoteAddress','78.226.169.202'),(14,'sessionId','CC2E181139F24D3B701083291CABD8BE'),(15,'remoteAddress','78.226.169.202'),(15,'sessionId','D4438B21B1D087D8FF2763073083C757'),(16,'message','Bad credentials'),(16,'type','org.springframework.security.authentication.BadCredentialsException'),(17,'remoteAddress','78.226.169.202'),(17,'sessionId','3AB008166DF2EB5A2767101A82E82113'),(18,'message','Bad credentials'),(18,'type','org.springframework.security.authentication.BadCredentialsException'),(19,'message','Bad credentials'),(19,'type','org.springframework.security.authentication.BadCredentialsException'),(20,'message','Bad credentials'),(20,'type','org.springframework.security.authentication.BadCredentialsException'),(21,'remoteAddress','37.163.34.45'),(21,'sessionId','38E7C2390366F0F9DFD6DD117D657D20'),(22,'message','Bad credentials'),(22,'type','org.springframework.security.authentication.BadCredentialsException'),(23,'remoteAddress','176.184.51.10'),(23,'sessionId','C10D602D7E4580EE08C5CE36FB3ECE5D'),(24,'message','Bad credentials'),(24,'type','org.springframework.security.authentication.BadCredentialsException'),(25,'remoteAddress','185.14.3.130'),(25,'sessionId','A254C1C5D3250381FC50098B967B1EC7'),(26,'remoteAddress','193.51.236.66'),(26,'sessionId','1A3C7AC6E325906250FE3A06E4961AEE'),(27,'remoteAddress','193.51.236.66'),(27,'sessionId','0B45EC5093F9C1E1355BD18BFA5D7849'),(28,'remoteAddress','193.51.236.66'),(28,'sessionId','941FB40938DC3A4962E3B53A9A82C794'),(29,'remoteAddress','0:0:0:0:0:0:0:1'),(29,'sessionId','0E99147CDBA0CA74B99DC6EF73B32307'),(30,'remoteAddress','0:0:0:0:0:0:0:1'),(30,'sessionId','106036D4EEA9F3BE81145EB0FEC7EAAB');
/*!40000 ALTER TABLE `jhi_persistent_audit_evt_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jhi_persistent_token`
--

DROP TABLE IF EXISTS `jhi_persistent_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jhi_persistent_token` (
  `series` varchar(255) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `token_value` varchar(255) NOT NULL,
  `token_date` date DEFAULT NULL,
  `ip_address` varchar(39) DEFAULT NULL,
  `user_agent` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`series`),
  KEY `fk_user_persistent_token` (`user_id`),
  CONSTRAINT `fk_user_persistent_token` FOREIGN KEY (`user_id`) REFERENCES `jhi_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jhi_persistent_token`
--

LOCK TABLES `jhi_persistent_token` WRITE;
/*!40000 ALTER TABLE `jhi_persistent_token` DISABLE KEYS */;
INSERT INTO `jhi_persistent_token` VALUES ('BYwAdm9jAEuF6JpgfhOj9A==',8,'Yf76LvVeO3w6HvhSHiB+uw==','2016-04-19','78.226.169.202','Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.110 Safari/537.36'),('FIjdYl1ew7/u/lfxzEIccA==',4,'voIMQjoIod7+PSUMT4BqSg==','2016-04-19','78.226.169.202','Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.112 Safari/537.36'),('NOB7h7arftGUKL7keXO8lA==',9,'8Wp0jOXdG7sbNDTToTcXHQ==','2016-04-20','37.163.101.201','Mozilla/5.0 (Linux; Android 5.1; FP2 Build/FP2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.105 Mobile Safari/537.36'),('PKNW8tffGfP9WnJB19UbrQ==',11,'z/GXLy2H4lXhc1F4I3rUJQ==','2016-04-20','193.51.236.66','Mozilla/5.0 (Macintosh; Intel Mac OS X 10.11; rv:42.0) Gecko/20100101 Firefox/42.0'),('q3PxBDNO2lv0IGeX1eNSew==',11,'G8fbpNSGagXu9Jk1t4eZwA==','2016-04-20','193.51.236.66','Mozilla/5.0 (Macintosh; Intel Mac OS X 10.11; rv:42.0) Gecko/20100101 Firefox/42.0'),('wFtfW5tNfr1wyG+S9mCS7g==',4,'JumeMFtgwPxOWpnJm+gRcg==','2016-04-21','0:0:0:0:0:0:0:1','Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.112 Safari/537.36'),('xZpo1uqU0jPvFEdNwtCVVA==',4,'z/uZoVbLp2/9gxO5ECbEfQ==','2016-04-20','185.14.3.130','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.112 Safari/537.36'),('Yg3aBaAufwmpVarbjTfifA==',10,'t1y/MsV2+1Nb+mMOjA4P4A==','2016-04-19','176.184.51.10','Mozilla/5.0 (Windows NT 6.1; rv:45.0) Gecko/20100101 Firefox/45.0');
/*!40000 ALTER TABLE `jhi_persistent_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jhi_user`
--

DROP TABLE IF EXISTS `jhi_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jhi_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `login` varchar(50) NOT NULL,
  `password_hash` varchar(60) DEFAULT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `activated` bit(1) NOT NULL,
  `lang_key` varchar(5) DEFAULT NULL,
  `activation_key` varchar(20) DEFAULT NULL,
  `reset_key` varchar(20) DEFAULT NULL,
  `created_by` varchar(50) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `reset_date` timestamp NULL DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login` (`login`),
  UNIQUE KEY `idx_user_login` (`login`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `idx_user_email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jhi_user`
--

LOCK TABLES `jhi_user` WRITE;
/*!40000 ALTER TABLE `jhi_user` DISABLE KEYS */;
INSERT INTO `jhi_user` VALUES (1,'system','$2a$10$mE.qmcV0mFU5NcKh73TZx.z4ueI/.bDWbj0T1BYyqP481kGGarKLG','System','System','system@localhost','','en',NULL,NULL,'system','2016-04-02 10:59:34',NULL,NULL,NULL),(2,'anonymousUser','$2a$10$j8S5d7Sr7.8VTOYNviDPOeWX8KcYILUVJBsYV83Y5NtECayypx9lO','Anonymous','User','anonymous@localhost','','en',NULL,NULL,'system','2016-04-02 10:59:34',NULL,NULL,NULL),(3,'admin','$2a$10$gSAhZrxMllrbgj/kkK9UceBPpChGWJA7SYIb1Mqo.n5aNLq1/oRrC','Administrator','Administrator','admin@localhost','','en',NULL,NULL,'system','2016-04-02 10:59:34',NULL,NULL,NULL),(4,'user','$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K','User','User','user@localhost','','en',NULL,NULL,'system','2016-04-02 10:59:34',NULL,NULL,NULL),(7,'arnaud','$2a$10$m2vAq1j37k8GkURBxYE1WuL08eDHaqHUp2OrWEMagxbHVS3gs2MNW',NULL,NULL,'zdziobeck.arnaud@gmail.com','','en',NULL,NULL,'anonymousUser','2016-04-17 17:52:52',NULL,'anonymousUser','2016-04-17 17:53:26'),(8,'nat','$2a$10$kSkKJn.qDa9lazLNUXDn2OJ5J4cjHO/Qd0NlkuYIdePPG5UofNCBC',NULL,NULL,'natacha.vray@gmail.com','','en',NULL,NULL,'anonymousUser','2016-04-18 15:43:54',NULL,'anonymousUser','2016-04-18 16:52:25'),(9,'odile','$2a$10$kqptZFmGaJ/kWfnW4oitGOFjGSolnB6RNLdbxrQ2ZxnxyirzBj5.q',NULL,NULL,'odilepointeau@gmail.com','','en',NULL,NULL,'anonymousUser','2016-04-19 15:31:00',NULL,'anonymousUser','2016-04-19 15:31:21'),(10,'thomas','$2a$10$EMZlANzZNfd3vG0GeJiuxO4vepck29DHMpNq60D44.vTCCsUKNN5K',NULL,NULL,'thomasthelisson@hotmail.fr','','en',NULL,NULL,'anonymousUser','2016-04-19 18:03:42',NULL,'anonymousUser','2016-04-19 18:06:18'),(11,'bellet','$2a$10$1VMszWOl2A9GifjkwY61gO6/pTZijkOzZSp/TTgjl1YrJPLxhCUBW',NULL,NULL,'aurelien.bellet@gmail.com','','en',NULL,NULL,'anonymousUser','2016-04-20 10:23:39',NULL,'anonymousUser','2016-04-20 10:24:37');
/*!40000 ALTER TABLE `jhi_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jhi_user_authority`
--

DROP TABLE IF EXISTS `jhi_user_authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jhi_user_authority` (
  `user_id` bigint(20) NOT NULL,
  `authority_name` varchar(50) NOT NULL,
  PRIMARY KEY (`user_id`,`authority_name`),
  KEY `fk_authority_name` (`authority_name`),
  CONSTRAINT `fk_authority_name` FOREIGN KEY (`authority_name`) REFERENCES `jhi_authority` (`name`),
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `jhi_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jhi_user_authority`
--

LOCK TABLES `jhi_user_authority` WRITE;
/*!40000 ALTER TABLE `jhi_user_authority` DISABLE KEYS */;
INSERT INTO `jhi_user_authority` VALUES (1,'ROLE_ADMIN'),(3,'ROLE_ADMIN'),(1,'ROLE_USER'),(3,'ROLE_USER'),(4,'ROLE_USER'),(7,'ROLE_USER'),(8,'ROLE_USER'),(9,'ROLE_USER'),(10,'ROLE_USER'),(11,'ROLE_USER');
/*!40000 ALTER TABLE `jhi_user_authority` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-04-21 22:34:52
