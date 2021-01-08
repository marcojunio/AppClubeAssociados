CREATE DATABASE  IF NOT EXISTS `clube_associado` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `clube_associado`;
-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: clube_associado
-- ------------------------------------------------------
-- Server version	8.0.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `associado`
--

DROP TABLE IF EXISTS `associado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `associado` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_clube` int NOT NULL,
  `nome` varchar(50) DEFAULT NULL,
  `cpf` varchar(21) DEFAULT NULL,
  `rg` varchar(19) DEFAULT NULL,
  `telefone` varchar(20) DEFAULT NULL,
  `endereco` varchar(20) DEFAULT NULL,
  `data_nascimento` date DEFAULT NULL,
  `conta` varchar(10) DEFAULT NULL,
  `agencia` varchar(10) DEFAULT NULL,
  `valor_mensalidade` float(8,2) DEFAULT NULL,
  `banco` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_clube_associado` (`id_clube`),
  CONSTRAINT `fk_clube_associado` FOREIGN KEY (`id_clube`) REFERENCES `clube` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `associado`
--

LOCK TABLES `associado` WRITE;
/*!40000 ALTER TABLE `associado` DISABLE KEYS */;
INSERT INTO `associado` VALUES (9,1,'regisegay','158741','541','545115','endereco teste','2020-11-25','5411','6115',50.00,'Banco do brasil'),(11,4,'aa','11','11','11','aa','2003-01-02','11','11',10.00,'a');
/*!40000 ALTER TABLE `associado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bar`
--

DROP TABLE IF EXISTS `bar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bar` (
  `id_clube` int NOT NULL,
  `id` int NOT NULL,
  `responsavel` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_bar` (`id_clube`),
  CONSTRAINT `fk_bar` FOREIGN KEY (`id_clube`) REFERENCES `clube` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bar`
--

LOCK TABLES `bar` WRITE;
/*!40000 ALTER TABLE `bar` DISABLE KEYS */;
INSERT INTO `bar` VALUES (1,1,'Renato'),(4,2,'Josias');
/*!40000 ALTER TABLE `bar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bar_produto`
--

DROP TABLE IF EXISTS `bar_produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bar_produto` (
  `id_bar` int NOT NULL,
  `id_produto` int NOT NULL,
  `nome_produto` varchar(45) DEFAULT NULL,
  KEY `fk_produto_bar` (`id_bar`),
  KEY `fk_produto_bar_idx` (`id_produto`),
  CONSTRAINT `fk_bar_produto` FOREIGN KEY (`id_bar`) REFERENCES `bar` (`id`),
  CONSTRAINT `fk_produto_bar` FOREIGN KEY (`id_produto`) REFERENCES `produto` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bar_produto`
--

LOCK TABLES `bar_produto` WRITE;
/*!40000 ALTER TABLE `bar_produto` DISABLE KEYS */;
INSERT INTO `bar_produto` VALUES (2,5,'WHISKEY JACK DANIELS 500ML');
/*!40000 ALTER TABLE `bar_produto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clube`
--

DROP TABLE IF EXISTS `clube`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clube` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) DEFAULT NULL,
  `cnpj` varchar(20) DEFAULT NULL,
  `responsavel` varchar(50) DEFAULT NULL,
  `localidade` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clube`
--

LOCK TABLES `clube` WRITE;
/*!40000 ALTER TABLE `clube` DISABLE KEYS */;
INSERT INTO `clube` VALUES (1,'Clube Associados','5656546','Marcos andré','Juiz de Fora'),(4,'Clube XPTO','541841','Regis Pastori','Matias Barbosa');
/*!40000 ALTER TABLE `clube` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comanda`
--

DROP TABLE IF EXISTS `comanda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comanda` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_cliente` int NOT NULL,
  `id_bar` int NOT NULL,
  `data_atendimento` datetime DEFAULT NULL,
  `valor` float(8,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_cliente_comanda` (`id_cliente`),
  KEY `fk_bar_comanda` (`id_bar`),
  CONSTRAINT `fk_bar_comanda` FOREIGN KEY (`id_bar`) REFERENCES `bar` (`id`),
  CONSTRAINT `fk_cliente_comanda` FOREIGN KEY (`id_cliente`) REFERENCES `associado` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comanda`
--

LOCK TABLES `comanda` WRITE;
/*!40000 ALTER TABLE `comanda` DISABLE KEYS */;
INSERT INTO `comanda` VALUES (1,9,1,'2020-11-29 13:39:00',51.60),(2,11,2,'2020-11-29 14:08:27',60.00);
/*!40000 ALTER TABLE `comanda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comanda_venda_produto`
--

DROP TABLE IF EXISTS `comanda_venda_produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comanda_venda_produto` (
  `id_comanda` int NOT NULL,
  `id_produto` int NOT NULL,
  `nome_produto` varchar(50) DEFAULT NULL,
  `valor_produto` double(8,2) DEFAULT NULL,
  KEY `fk_comanda` (`id_comanda`),
  KEY `fk_ccomanda_produto` (`id_produto`),
  CONSTRAINT `fk_ccomanda_produto` FOREIGN KEY (`id_produto`) REFERENCES `produto` (`id`),
  CONSTRAINT `fk_comanda` FOREIGN KEY (`id_comanda`) REFERENCES `comanda` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comanda_venda_produto`
--

LOCK TABLES `comanda_venda_produto` WRITE;
/*!40000 ALTER TABLE `comanda_venda_produto` DISABLE KEYS */;
/*!40000 ALTER TABLE `comanda_venda_produto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dependente`
--

DROP TABLE IF EXISTS `dependente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dependente` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_responsavel` int DEFAULT NULL,
  `parentesco` enum('marido','esposa','filho','filha') DEFAULT NULL,
  `nome` varchar(50) DEFAULT NULL,
  `cpf` varchar(23) DEFAULT NULL,
  `data_nascimento` date DEFAULT NULL,
  `rg` varchar(25) DEFAULT NULL,
  `telefone` varchar(20) DEFAULT NULL,
  `endereco` varchar(50) DEFAULT NULL,
  `idade` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_responsavel_dependente` (`id_responsavel`),
  CONSTRAINT `fk_responsavel_dependente` FOREIGN KEY (`id_responsavel`) REFERENCES `associado` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dependente`
--

LOCK TABLES `dependente` WRITE;
/*!40000 ALTER TABLE `dependente` DISABLE KEYS */;
INSERT INTO `dependente` VALUES (2,11,'esposa','dfgdfgdfg','3453534','2005-06-10','345345','45345','fdgdfgdfg',32),(9,11,'esposa','marcos','3423432','2010-10-19','234324','234324','sffgdsfgdfg',23),(16,11,'filho','fdgvdfghfg','34534556','2010-10-10','456456','5646456','dfgdfgdf',12);
/*!40000 ALTER TABLE `dependente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lancamento`
--

DROP TABLE IF EXISTS `lancamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lancamento` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_clube` int NOT NULL,
  `ref_inicial` varchar(15) DEFAULT NULL,
  `ref_final` varchar(15) DEFAULT NULL,
  `total_mensalidades` decimal(8,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_clube_lancamento` (`id_clube`),
  CONSTRAINT `fk_clube_lancamento` FOREIGN KEY (`id_clube`) REFERENCES `clube` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lancamento`
--

LOCK TABLES `lancamento` WRITE;
/*!40000 ALTER TABLE `lancamento` DISABLE KEYS */;
/*!40000 ALTER TABLE `lancamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produto`
--

DROP TABLE IF EXISTS `produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `produto` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) DEFAULT NULL,
  `marca` varchar(40) DEFAULT NULL,
  `preco` decimal(5,2) DEFAULT NULL,
  `validade` date DEFAULT NULL,
  `perecivel` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produto`
--

LOCK TABLES `produto` WRITE;
/*!40000 ALTER TABLE `produto` DISABLE KEYS */;
INSERT INTO `produto` VALUES (5,'WHISKEY JACK DANIELS 500ML','JACK DANIELS',2.50,'2020-11-26',1);
/*!40000 ALTER TABLE `produto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (3,'markgod','32230136'),(4,'tete','1234'),(5,'regisgay','1234');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'clube_associado'
--

--
-- Dumping routines for database 'clube_associado'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-11-29 12:01:17
