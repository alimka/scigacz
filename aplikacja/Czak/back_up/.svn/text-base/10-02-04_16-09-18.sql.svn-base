-- MySQL dump 10.13  Distrib 5.1.41, for Win32 (ia32)
--
-- Host: localhost    Database: Scigacz
-- ------------------------------------------------------
-- Server version	5.1.41-community

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
-- Table structure for table `kategorie`
--

DROP TABLE IF EXISTS `kategorie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kategorie` (
  `idKategorii` int(11) NOT NULL AUTO_INCREMENT,
  `nazwa` varchar(255) NOT NULL,
  PRIMARY KEY (`idKategorii`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kategorie`
--

LOCK TABLES `kategorie` WRITE;
/*!40000 ALTER TABLE `kategorie` DISABLE KEYS */;
INSERT INTO `kategorie` VALUES (1,'ubrania');
/*!40000 ALTER TABLE `kategorie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `opinie`
--

DROP TABLE IF EXISTS `opinie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `opinie` (
  `idOpinii` int(11) NOT NULL AUTO_INCREMENT,
  `data` datetime NOT NULL,
  `kto` int(11) NOT NULL,
  `tresc` varchar(255) NOT NULL,
  `idOpiniujacego` int(11) NOT NULL,
  `idUzytkownika` int(11) NOT NULL,
  `idWypozyczenia` int(11) NOT NULL,
  PRIMARY KEY (`idOpinii`),
  KEY `FK8D2A8682D6C2C051` (`idWypozyczenia`),
  KEY `FK8D2A8682CCD83A27` (`idUzytkownika`),
  KEY `FK8D2A86822D28344C` (`idOpiniujacego`),
  KEY `FK8D2A8682799956D5` (`idWypozyczenia`),
  KEY `FK8D2A8682B952C323` (`idUzytkownika`),
  KEY `FK8D2A868219A2BD48` (`idOpiniujacego`),
  CONSTRAINT `FK8D2A868219A2BD48` FOREIGN KEY (`idOpiniujacego`) REFERENCES `uzytkownicy` (`idUzytkownika`),
  CONSTRAINT `FK8D2A86822D28344C` FOREIGN KEY (`idOpiniujacego`) REFERENCES `uzytkownicy` (`idUzytkownika`),
  CONSTRAINT `FK8D2A8682799956D5` FOREIGN KEY (`idWypozyczenia`) REFERENCES `wypozyczenia` (`idWypozyczenia`),
  CONSTRAINT `FK8D2A8682B952C323` FOREIGN KEY (`idUzytkownika`) REFERENCES `uzytkownicy` (`idUzytkownika`),
  CONSTRAINT `FK8D2A8682CCD83A27` FOREIGN KEY (`idUzytkownika`) REFERENCES `uzytkownicy` (`idUzytkownika`),
  CONSTRAINT `FK8D2A8682D6C2C051` FOREIGN KEY (`idWypozyczenia`) REFERENCES `wypozyczenia` (`idWypozyczenia`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `opinie`
--

LOCK TABLES `opinie` WRITE;
/*!40000 ALTER TABLE `opinie` DISABLE KEYS */;
/*!40000 ALTER TABLE `opinie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rezerwacje`
--

DROP TABLE IF EXISTS `rezerwacje`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rezerwacje` (
  `idRezerwacji` int(11) NOT NULL AUTO_INCREMENT,
  `numerOczekujacego` int(11) NOT NULL,
  `idWypozyczacza` int(11) NOT NULL,
  `idZasobu` int(11) NOT NULL,
  PRIMARY KEY (`idRezerwacji`),
  KEY `FKE7EDC6E0CC566411` (`idZasobu`),
  KEY `FKE7EDC6E0980087F9` (`idWypozyczacza`),
  CONSTRAINT `FKE7EDC6E0980087F9` FOREIGN KEY (`idWypozyczacza`) REFERENCES `uzytkownicy` (`idUzytkownika`),
  CONSTRAINT `FKE7EDC6E0CC566411` FOREIGN KEY (`idZasobu`) REFERENCES `zasoby` (`idZasobu`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rezerwacje`
--

LOCK TABLES `rezerwacje` WRITE;
/*!40000 ALTER TABLE `rezerwacje` DISABLE KEYS */;
/*!40000 ALTER TABLE `rezerwacje` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `systemowe`
--

DROP TABLE IF EXISTS `systemowe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `systemowe` (
  `idSys` int(11) NOT NULL AUTO_INCREMENT,
  `czyPrzeczytana` bit(1) NOT NULL,
  `data` datetime NOT NULL,
  `jestNadawca` bit(1) NOT NULL,
  `jestOdbiorca` bit(1) NOT NULL,
  `rodzaj` int(11) NOT NULL,
  `temat` varchar(255) NOT NULL,
  `tresc` varchar(255) NOT NULL,
  `idOdbiorcy` int(11) NOT NULL,
  `idDoZwrotu` int(11) DEFAULT NULL,
  `idZasobu` int(11) DEFAULT NULL,
  PRIMARY KEY (`idSys`),
  KEY `FKBABF7ACEF80D72CE` (`idOdbiorcy`),
  KEY `FKBABF7ACEE487FBCA` (`idOdbiorcy`),
  CONSTRAINT `FKBABF7ACEE487FBCA` FOREIGN KEY (`idOdbiorcy`) REFERENCES `uzytkownicy` (`idUzytkownika`),
  CONSTRAINT `FKBABF7ACEF80D72CE` FOREIGN KEY (`idOdbiorcy`) REFERENCES `uzytkownicy` (`idUzytkownika`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `systemowe`
--

LOCK TABLES `systemowe` WRITE;
/*!40000 ALTER TABLE `systemowe` DISABLE KEYS */;
INSERT INTO `systemowe` VALUES (1,'\0','2010-01-15 13:22:52','','',2,'!!!  NAGANA !!!','UWAGA UWAGA UWAGA \nPaulinka\nZostajesz upomniany naganą od administratora\nJak najszybciej zwróć zasób : lampka nocnaużytkownikowi: lala lala',2,NULL,NULL),(2,'\0','2010-01-15 13:22:53','','',2,'!!!  ILOŚć DNI OPÓŹNIENIA: 5  !!!','UWAGA UWAGA UWAGA \nPaulinka\nUpłynął już termin wypożyczenia przez Ciebie zasobu: latarka.\nWłasciciel lala lala prosi o bardzo szybki kontakt! \nIlość dni opóźnienia: 5.\n',2,NULL,NULL),(3,'\0','2010-01-15 13:23:01','','',2,'ZA TYDZIEŃ UPŁYWA TERMIN WYPOŻYCZENIA !!!','UWAGA UWAGA UWAGA \nlala!!!\nZa tydzień użytkownik Paulinka ÅoÅ-Zembrzycka zwraca Ci: latarnia uliczna.\n',1,NULL,NULL),(4,'\0','2010-01-15 13:23:02','','',2,'ZA TYDZIEŃ UPŁYWA TERMIN WYPOŻYCZENIA !!!','UWAGA UWAGA UWAGA \nPaulinka!!!\nZa tydzień upływa termin wypożyczenia przez Ciebie zasobu: latarnia uliczna.\nWłaściciel lala lala prosi o zwrot na czas.',2,NULL,NULL),(5,'\0','2010-01-15 13:31:48','','',2,'!!!  NAGANA !!!','UWAGA UWAGA UWAGA \nPaulinka\nZostajesz upomniany naganą od administratora\n Jak najszybciej zwróć zasób : lampka nocna użytkownikowi: lala lala',2,NULL,NULL),(6,'\0','2010-01-15 13:31:49','','',2,'!!!  ILOŚć DNI OPÓŹNIENIA: 5  !!!','UWAGA UWAGA UWAGA \nPaulinka\nUpłynął już termin wypożyczenia przez Ciebie zasobu: latarka.\nWłasciciel lala lala prosi o bardzo szybki kontakt! \nIlość dni opóźnienia: 5.\n',2,NULL,NULL),(7,'\0','2010-01-15 13:31:56','','',2,'ZA TYDZIEŃ UPŁYWA TERMIN WYPOŻYCZENIA !!!','UWAGA UWAGA UWAGA \nlala!!!\nZa tydzień użytkownik Paulinka ÅoÅ-Zembrzycka zwraca Ci: latarnia uliczna.\n',1,NULL,NULL),(8,'\0','2010-01-15 13:31:57','','',2,'ZA TYDZIEŃ UPŁYWA TERMIN WYPOŻYCZENIA !!!','UWAGA UWAGA UWAGA \nPaulinka!!!\nZa tydzień upływa termin wypożyczenia przez Ciebie zasobu: latarnia uliczna.\nWłaściciel lala lala prosi o zwrot na czas.',2,NULL,NULL),(9,'\0','2010-01-19 19:32:48','','',2,'!!!  ILOŚć DNI OPÓŹNIENIA: 18  !!!','UWAGA UWAGA UWAGA \nPaulinka\nUpłynął już termin wypożyczenia przez Ciebie zasobu: lampka nocna.\nWłasciciel lala lala prosi o bardzo szybki kontakt! \nIlość dni opóźnienia: 18.\n',2,0,NULL),(10,'\0','2010-01-19 19:32:49','','',2,'!!!  ILOŚć DNI OPÓŹNIENIA: 9  !!!','UWAGA UWAGA UWAGA \nPaulinka\nUpłynął już termin wypożyczenia przez Ciebie zasobu: latarka.\nWłasciciel lala lala prosi o bardzo szybki kontakt! \nIlość dni opóźnienia: 9.\n',2,0,NULL),(11,'','2010-01-20 10:17:15','','\0',2,'!!!  ILOŚć DNI OPÓŹNIENIA: 7  !!!','UWAGA UWAGA UWAGA \nPaulinka\nUpłynął już termin wypożyczenia przez Ciebie zasobu: latarnia uliczna.\nWłasciciel Ania Pedros prosi o bardzo szybki kontakt! \nIlość dni opóźnienia: 7.\n',2,0,-1),(12,'\0','2010-01-20 10:17:20','','',2,'ZA TYDZIEŃ UPŁYWA TERMIN WYPOŻYCZENIA !!!','UWAGA UWAGA UWAGA \nAnia!!!\nZa tydzień użytkownik Paulinka Łoś zwraca Ci: lampka nocna.\n',3,0,-1),(13,'\0','2010-01-20 10:17:21','','\0',2,'ZA TYDZIEŃ UPŁYWA TERMIN WYPOŻYCZENIA !!!','UWAGA UWAGA UWAGA \nPaulinka!!!\nZa tydzień upływa termin wypożyczenia przez Ciebie zasobu: lampka nocna.\nWłaściciel Ania Pedros prosi o zwrot na czas.',2,0,-1),(14,'','2010-01-30 18:48:20','','\0',4,'Prosba o wypozyczenie zasobu','Użytkownik <a href=\"/Scigacz/OsobaServlet?id=4\">trala</a> prosi o wypożyczenie zasobu <a href=\"/Scigacz/RzeczServlet?id=1\">lampka nocna</a>.',2,4,1),(15,'','2010-01-30 18:49:28','','\0',1,'Zaproszenie do znajomych','Uzytkownik trala zaprosił Cię do znajomych',2,4,-1),(16,'','2010-02-03 18:01:08','','\0',5,'Zwrot zasobu','Użytkownik <a href=\"/Scigacz/OsobaServlet?id=2\">plos</a> oddał zasób <a href=\"/Scigacz/RzeczServlet?id=1\">lampka nocna</a>.',2,2,1);
/*!40000 ALTER TABLE `systemowe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `uzytkownicy`
--

DROP TABLE IF EXISTS `uzytkownicy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `uzytkownicy` (
  `idUzytkownika` int(11) NOT NULL AUTO_INCREMENT,
  `avatar` varchar(255) NOT NULL,
  `datablokada` datetime NOT NULL,
  `email` varchar(255) NOT NULL,
  `haslo` varchar(255) NOT NULL,
  `imie` varchar(255) NOT NULL,
  `komentarzblokada` varchar(255) NOT NULL,
  `login` varchar(255) NOT NULL,
  `miasto` varchar(255) NOT NULL,
  `nazwisko` varchar(255) NOT NULL,
  `telefon` int(11) NOT NULL,
  `uprawnienia` int(11) NOT NULL,
  `wojewodztwo` varchar(255) NOT NULL,
  PRIMARY KEY (`idUzytkownika`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uzytkownicy`
--

LOCK TABLES `uzytkownicy` WRITE;
/*!40000 ALTER TABLE `uzytkownicy` DISABLE KEYS */;
INSERT INTO `uzytkownicy` VALUES (1,'av.jpg','1970-01-01 01:00:00','lala@qp.pl','2e3817293fc275dbee74bd71ce6eb056','lala','','lala','dds','lala',234,0,'kujawsko-pomorskie'),(2,'kot1.jpeg','1970-01-01 01:00:00','paulina.los@gmail.com','e147ea2fc47ea1b06a5633467567396','Paulinka','','plos','Lublin','Łoś',0,0,'lubelskie'),(3,'av.jpg','1970-01-01 01:00:00','an.gajos@gmail.com','5f59ac736640f43e61c67284bf1c6','Ania','','ania','Lublin','Pedros',0,0,'lubelskie'),(4,'av.jpg','1970-01-01 01:00:00','lala@wp.pl','2e3817293fc275dbee74bd71ce6eb056','lala','','trala','Lublin','lala',12121,0,'kujawsko-pomorskie');
/*!40000 ALTER TABLE `uzytkownicy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wiadomosci`
--

DROP TABLE IF EXISTS `wiadomosci`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wiadomosci` (
  `idWiadomosci` int(11) NOT NULL AUTO_INCREMENT,
  `czyPrzeczytana` bit(1) NOT NULL,
  `data` datetime NOT NULL,
  `jestNadawca` bit(1) NOT NULL,
  `jestOdbiorca` bit(1) NOT NULL,
  `temat` varchar(255) NOT NULL,
  `tresc` varchar(255) NOT NULL,
  `idNadawcy` int(11) NOT NULL,
  `idOdbiorcy` int(11) NOT NULL,
  PRIMARY KEY (`idWiadomosci`),
  KEY `FK9EE63EFDF80D72CE` (`idOdbiorcy`),
  KEY `FK9EE63EFD43940280` (`idNadawcy`),
  KEY `FK9EE63EFDE487FBCA` (`idOdbiorcy`),
  KEY `FK9EE63EFD300E8B7C` (`idNadawcy`),
  CONSTRAINT `FK9EE63EFD300E8B7C` FOREIGN KEY (`idNadawcy`) REFERENCES `uzytkownicy` (`idUzytkownika`),
  CONSTRAINT `FK9EE63EFD43940280` FOREIGN KEY (`idNadawcy`) REFERENCES `uzytkownicy` (`idUzytkownika`),
  CONSTRAINT `FK9EE63EFDE487FBCA` FOREIGN KEY (`idOdbiorcy`) REFERENCES `uzytkownicy` (`idUzytkownika`),
  CONSTRAINT `FK9EE63EFDF80D72CE` FOREIGN KEY (`idOdbiorcy`) REFERENCES `uzytkownicy` (`idUzytkownika`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wiadomosci`
--

LOCK TABLES `wiadomosci` WRITE;
/*!40000 ALTER TABLE `wiadomosci` DISABLE KEYS */;
INSERT INTO `wiadomosci` VALUES (1,'','2010-01-30 18:45:02','','\0','sajd','kdjfjk',4,2),(2,'\0','2010-01-30 19:00:51','\0','','Dodanie do znajomych','plos zgodził/a dodać się Ciebie do listy znajomych.',2,4),(3,'\0','2010-02-03 17:45:42','\0','','uuu','tarara',2,4),(4,'\0','2010-02-03 18:01:25','\0','','Oddałeś zasób','Oddałeś mi zasób <a href=\"/Scigacz/RzeczServlet?id=1\">lampka nocna</a>. Dziękuję za współpracę.',2,2),(5,'\0','2010-02-03 18:02:05','\0','','aaa','bbbb',2,2);
/*!40000 ALTER TABLE `wiadomosci` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wypozyczenia`
--

DROP TABLE IF EXISTS `wypozyczenia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wypozyczenia` (
  `idWypozyczenia` int(11) NOT NULL AUTO_INCREMENT,
  `dataWypozyczenia` datetime NOT NULL,
  `dataZwrotu` datetime NOT NULL,
  `idUdostepniacza` int(11) NOT NULL,
  `idWypozyczacza` int(11) NOT NULL,
  `idZasobu` int(11) NOT NULL,
  PRIMARY KEY (`idWypozyczenia`),
  KEY `FK593D3FF8CC566411` (`idZasobu`),
  KEY `FK593D3FF8EFB6D393` (`idUdostepniacza`),
  KEY `FK593D3FF8980087F9` (`idWypozyczacza`),
  KEY `FK593D3FF89F712D95` (`idZasobu`),
  KEY `FK593D3FF8DC315C8F` (`idUdostepniacza`),
  KEY `FK593D3FF8847B10F5` (`idWypozyczacza`),
  CONSTRAINT `FK593D3FF8847B10F5` FOREIGN KEY (`idWypozyczacza`) REFERENCES `uzytkownicy` (`idUzytkownika`),
  CONSTRAINT `FK593D3FF8980087F9` FOREIGN KEY (`idWypozyczacza`) REFERENCES `uzytkownicy` (`idUzytkownika`),
  CONSTRAINT `FK593D3FF89F712D95` FOREIGN KEY (`idZasobu`) REFERENCES `zasoby` (`idZasobu`),
  CONSTRAINT `FK593D3FF8CC566411` FOREIGN KEY (`idZasobu`) REFERENCES `zasoby` (`idZasobu`),
  CONSTRAINT `FK593D3FF8DC315C8F` FOREIGN KEY (`idUdostepniacza`) REFERENCES `uzytkownicy` (`idUzytkownika`),
  CONSTRAINT `FK593D3FF8EFB6D393` FOREIGN KEY (`idUdostepniacza`) REFERENCES `uzytkownicy` (`idUzytkownika`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wypozyczenia`
--

LOCK TABLES `wypozyczenia` WRITE;
/*!40000 ALTER TABLE `wypozyczenia` DISABLE KEYS */;
INSERT INTO `wypozyczenia` VALUES (2,'2010-01-20 00:00:00','2010-01-20 00:00:00',3,2,2),(3,'2010-01-15 00:00:00','2010-01-13 00:00:00',3,2,3);
/*!40000 ALTER TABLE `wypozyczenia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zasoby`
--

DROP TABLE IF EXISTS `zasoby`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `zasoby` (
  `idZasobu` int(11) NOT NULL AUTO_INCREMENT,
  `czasPrzetrzymywania` int(11) NOT NULL,
  `dlakogo` int(11) NOT NULL,
  `dostepnosc` int(11) NOT NULL,
  `nazwa` varchar(255) NOT NULL,
  `opis` varchar(255) NOT NULL,
  `idKategorii` int(11) NOT NULL,
  `idUzytkownika` int(11) NOT NULL,
  PRIMARY KEY (`idZasobu`),
  KEY `FK9F21037ACCD83A27` (`idUzytkownika`),
  KEY `FK9F21037AE1967581` (`idKategorii`),
  KEY `FK9F21037AB952C323` (`idUzytkownika`),
  KEY `FK9F21037A5BD70D7D` (`idKategorii`),
  CONSTRAINT `FK9F21037A5BD70D7D` FOREIGN KEY (`idKategorii`) REFERENCES `kategorie` (`idKategorii`),
  CONSTRAINT `FK9F21037AB952C323` FOREIGN KEY (`idUzytkownika`) REFERENCES `uzytkownicy` (`idUzytkownika`),
  CONSTRAINT `FK9F21037ACCD83A27` FOREIGN KEY (`idUzytkownika`) REFERENCES `uzytkownicy` (`idUzytkownika`),
  CONSTRAINT `FK9F21037AE1967581` FOREIGN KEY (`idKategorii`) REFERENCES `kategorie` (`idKategorii`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zasoby`
--

LOCK TABLES `zasoby` WRITE;
/*!40000 ALTER TABLE `zasoby` DISABLE KEYS */;
INSERT INTO `zasoby` VALUES (1,20,0,0,'lampka nocna','swietna',1,2),(2,20,0,0,'latarka','dobra',1,2),(3,20,0,0,'latarnia uliczna','jasno swieci',1,2),(4,12,0,0,'zdjf','ks',1,4),(5,23,0,0,'dfdjshgfhj','jdgdsg',1,4);
/*!40000 ALTER TABLE `zasoby` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zdjecia`
--

DROP TABLE IF EXISTS `zdjecia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `zdjecia` (
  `idZdjecia` int(11) NOT NULL AUTO_INCREMENT,
  `nrZdjecia` int(11) NOT NULL,
  `plik` varchar(255) NOT NULL,
  `idZasobu` int(11) NOT NULL,
  PRIMARY KEY (`idZdjecia`),
  KEY `FK499A9896CC566411` (`idZasobu`),
  KEY `FK499A98969F712D95` (`idZasobu`),
  CONSTRAINT `FK499A98969F712D95` FOREIGN KEY (`idZasobu`) REFERENCES `zasoby` (`idZasobu`),
  CONSTRAINT `FK499A9896CC566411` FOREIGN KEY (`idZasobu`) REFERENCES `zasoby` (`idZasobu`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zdjecia`
--

LOCK TABLES `zdjecia` WRITE;
/*!40000 ALTER TABLE `zdjecia` DISABLE KEYS */;
/*!40000 ALTER TABLE `zdjecia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `znajomi`
--

DROP TABLE IF EXISTS `znajomi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `znajomi` (
  `idZnajomosci` int(11) NOT NULL AUTO_INCREMENT,
  `czyBliskiUzytkownika` bit(1) NOT NULL,
  `czyBliskiZnajomego` bit(1) NOT NULL,
  `idUzytkownika` int(11) NOT NULL,
  `idZnajomego` int(11) NOT NULL,
  PRIMARY KEY (`idZnajomosci`),
  KEY `FK5A2EAEAE9B372B5` (`idZnajomego`),
  KEY `FK5A2EAEAECCD83A27` (`idUzytkownika`),
  KEY `FK5A2EAEAEF62DFBB1` (`idZnajomego`),
  KEY `FK5A2EAEAEB952C323` (`idUzytkownika`),
  CONSTRAINT `FK5A2EAEAEB952C323` FOREIGN KEY (`idUzytkownika`) REFERENCES `uzytkownicy` (`idUzytkownika`),
  CONSTRAINT `FK5A2EAEAE9B372B5` FOREIGN KEY (`idZnajomego`) REFERENCES `uzytkownicy` (`idUzytkownika`),
  CONSTRAINT `FK5A2EAEAECCD83A27` FOREIGN KEY (`idUzytkownika`) REFERENCES `uzytkownicy` (`idUzytkownika`),
  CONSTRAINT `FK5A2EAEAEF62DFBB1` FOREIGN KEY (`idZnajomego`) REFERENCES `uzytkownicy` (`idUzytkownika`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `znajomi`
--

LOCK TABLES `znajomi` WRITE;
/*!40000 ALTER TABLE `znajomi` DISABLE KEYS */;
INSERT INTO `znajomi` VALUES (1,'\0','\0',2,4);
/*!40000 ALTER TABLE `znajomi` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2010-02-04 16:09:20
