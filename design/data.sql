-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: cookbook_group3
-- ------------------------------------------------------
-- Server version	5.7.17-log

-- Create database schema.
CREATE SCHEMA `cookbook_group3` ;


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
-- Table structure for table `ingredient`
--

USE `cookbook_group3` ;
DROP TABLE IF EXISTS `ingredient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ingredient` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ingredientName` varchar(30) DEFAULT NULL,
  `recipeID` int(11) DEFAULT NULL,
  `quantity` decimal(6,2) DEFAULT NULL,
  `unit` varchar(10) DEFAULT NULL,
  `comments` varchar(50) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8 COMMENT='ingredient table for digital cookbook';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingredient`
--

LOCK TABLES `ingredient` WRITE;
/*!40000 ALTER TABLE `ingredient` DISABLE KEYS */;
INSERT INTO `ingredient` VALUES (1,'cornstarch',1,1,'tablespoon',NULL,1),
(2,'soy sauce',1,4,'tablespoon',NULL,1),
(3,'chicken breast',1,1,'kg',NULL,1),
(4,'Shaoxin rice wine',1,3,'tablespoon',NULL,1),
(5,'sugar',1,2,'tablespoon',NULL,1),
(6,'chicken stock',1,3,'tablespoon',NULL,1),
(7,'Chiangang vinegar',1,4,'teaspoon',NULL,1),
(8,'sesame oil',1,4,'tablespoon',NULL,1),
(9,'dark soy sauce',1,2,'teaspoon',NULL,1),
(10,'peanut oil',1,3,'tablespoon',NULL,1),
(11,'dried red chilis',1,12,'pieces','stemmed, halved crosswise, and seeded',1),
(12,'scallions',1,5,'pieces','white part onyl, thickly sliced crosswise',1),
(13,'garlic',1,1,'cloves','peeled, thinly sliced',1),
(14,'ginger',1,1,'pieces','peeled, minced',1),
(15,'peanuts',1,1,'cups','peeled, thinly sliced',1),
(16,'pork belly',2,1,'kg','cut into 2cm pieces',1),
(17,'cooking oil',2,2,'tablespoon',NULL,1),
(18,'brown sugar',2,1,'tablespoon',NULL,1),
(19,'Shaoxin rice wine',2,3,'tablespoon',NULL,1),
(20,'light soy sauce',2,1,'tablespoon',NULL,1),
(21,'dark soy sauce',2,1,'tablespoon',NULL,1),
(22,'chicken stock or water',2,2,'cups',NULL,1),
(23,'potato noodles',3,1,'bunch',NULL,1),
(24,'peanuts',3,2,'tablespoon','roasted',1),
(25,'spring onion',3,1,'tablespoon','chopped',1),
(26,'coriander',3,1,'tablespoon','chopped',1),
(27,'pickled Sichuan vegetable',3,2,'tablespoon','chopped',1),
(28,'garlic',3,3,'cloves',NULL,1),
(29,'peanut oil',3,1,'tablespoon',NULL,1),
(30,'Sichuan peppercorn powder',3,1,'teaspoon',NULL,1),
(31,'Chinese five spicy powder',3,1,'teaspoon',NULL,1),
(32,'chili powder',3,1,'teaspoon',NULL,1),
(33,'vinegar',3,1,'tablespoon',NULL,1),
(34,'light soy sauce',3,1,'tablespoon',NULL,1),
(35,'salt',3,1,'teaspoon',NULL,1);

/*!40000 ALTER TABLE `ingredient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recipe`
--

DROP TABLE IF EXISTS `recipe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `recipe` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ownerUserid` int(11) DEFAULT NULL,
  `recipeName` varchar(50) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `preparationTime` int(11) DEFAULT NULL,
  `cookingTime` int(11) DEFAULT NULL,
  `peopleAvailable` int(11) DEFAULT NULL,
  `imagePath` varchar(50) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='recipe table in the digital cookbook';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recipe`
--

LOCK TABLES `recipe` WRITE;
/*!40000 ALTER TABLE `recipe` DISABLE KEYS */;
INSERT INTO `recipe` VALUES (1,4,'Hong Shao Rou','Hunan Dish',25,50,4,'hsr.jpg',1),
(2,1,'Gong Bao Ji Ding','Sichuan Dish spicy diced chicken with peanuts and received many positive comments around the world',30,10,4,'gbjd.jpg',1),
(3,3,'Suan La Fen','Sichuan Dish very decilious positive comments good',10,60,2,'slf.jpg',1);
/*!40000 ALTER TABLE `recipe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `step`
--

DROP TABLE IF EXISTS `step`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `step` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(500) DEFAULT NULL,
  `stepOrder` int(11) DEFAULT NULL,
  `recipeID` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COMMENT='step table for digital cookbook.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `step`
--

LOCK TABLES `step` WRITE;
/*!40000 ALTER TABLE `step` DISABLE KEYS */;
INSERT INTO `step` VALUES (1,'Mix together cornstarch and 1 tbsp. of the soy sauce in a medium bowl.',1,1,1),
(2,'Add chicken, toss well, and set aside to marinate for 30 minutes.',2,1,1),
(3,'Meanwhile, mix together the remaining 3 tbsp. soy sauce, rice wine, sugar, stock, vinegar, sesame oil, and dark soy sauce.',3,1,1),
(4,'Set aside.',4,1,1),
(5,'Heat peanut oil in a wok or large nonstick skillet over high heat until just beginning to smoke.',5,1,1),
(6,'Add chilis, half the scallions, garlic, ginger, and chicken and stir-fry until chicken is golden, 3-5 minutes.',6,1,1),
(7,'Add soy sauce mixture and stir-fry until sauce thickens, about 2 minutes.',7,1,1),
(8,'Stir in peanuts.',8,1,1),
(9,'Garnish with remaining scallions.',9,1,1),
(10,'Bring a pot of water to a boil and blanch the pork for a couple minutes.',1,2,1),
(11,'Take the pork out of the pot and set aside.',2,2,1),
(12,'Over low heat, add oil and sugar to your wok.',3,2,1),
(13,'Melt the sugar slightly and add the pork.',4,2,1),
(14,'Raise the heat to medium and cook until the pork is lightly browned.',5,2,1),
(15,'Turn the heat back down to low and add cooking wine, light soy sauce, dark soy sauce, and chicken stock.',6,2,1),
(16,'Cover and simmer for about 60 minutes to 90 minutes until pork is fork tender.',7,2,1),
(17,'Every 5-10 minutes, stir to prevent burning and add water if it gets too dry.',8,2,1),
(18,'Once the pork is fork tender, if there is still a lot of visible liquid, uncover the wok, turn up the heat, and stir continuously the sauce has reduced to a glistening coating.',9,2,1),
(19,'Soak the sweet potato noodles with hot water around 30 minutes.',1,3,1),
(20,'Transfer out and set aside.',2,3,1),
(21,'In the serving bowls and mix all the seasonings.',3,3,1),(22,'Heat up peanuts oil in pan to stir-fry the mashed garlic until aroma.',4,3,1),
(23,'Mix the garlic oil with the seasonings.',5,3,1),
(24,'Add some spring onions and corianders in serving bowls.',6,3,1),
(25,'Pour in boiling water or stock to tune the seasonings.',7,3,1),
(26,'Add vinegar and light soy sauce.',8,3,1),
(27,'Mix well and set aside.',9,3,1),
(28,'Cook soaked sweet potato noodles around 3~5 minutes until you can break the noodles with your fingers.',10,3,1),
(29,'Transfer the noodles out to the serving bowls and then add top with pickled vegetables, roasted peanuts and chopped spring onions and coriander.',11,3,1);
/*!40000 ALTER TABLE `step` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(50) NOT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','8106417f482b5b3a30a433f4a31704bf',1),
(2,'yichen.hua','8106417f482b5b3a30a433f4a31704bf',1),
(3,'jungang.wang','8106417f482b5b3a30a433f4a31704bf',1),
(4,'jiaxiang.shan','8106417f482b5b3a30a433f4a31704bf',1),
(5,'yu.kong','8106417f482b5b3a30a433f4a31704bf',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user-recipe-table`
--

DROP TABLE IF EXISTS `user-recipe-table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user-recipe-table` (
  `userID` int(11) DEFAULT NULL,
  `recipeID` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='to store users favorite recipes';
/*!40101 SET character_set_client = @saved_cs_client */;

INSERT INTO `cookbook_group3`.`user-recipe-table` (`userID`, `recipeID`, `status`) VALUES ('1', '1', '1');
INSERT INTO `cookbook_group3`.`user-recipe-table` (`userID`, `recipeID`, `status`) VALUES ('1', '2', '1');
INSERT INTO `cookbook_group3`.`user-recipe-table` (`userID`, `recipeID`, `status`) VALUES ('1', '3', '1');
INSERT INTO `cookbook_group3`.`user-recipe-table` (`userID`, `recipeID`, `status`) VALUES ('2', '1', '1');

--
-- Dumping data for table `user-recipe-table`
--

LOCK TABLES `user-recipe-table` WRITE;
/*!40000 ALTER TABLE `user-recipe-table` DISABLE KEYS */;
/*!40000 ALTER TABLE `user-recipe-table` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-25 20:51:42