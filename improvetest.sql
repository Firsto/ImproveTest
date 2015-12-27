# ************************************************************
# Sequel Pro SQL dump
# ������ 4499
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# �����: 127.0.0.1 (MySQL 5.7.9)
# �����: improvetest
# ����� ��������: 2015-12-26 21:28:30 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# ���� ������� cat
# ------------------------------------------------------------

DROP TABLE IF EXISTS `cat`;

CREATE TABLE `cat` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=cp1251;

LOCK TABLES `cat` WRITE;
/*!40000 ALTER TABLE `cat` DISABLE KEYS */;

INSERT INTO `cat` (`id`, `name`)
VALUES
	(1,'Принтеры'),
	(2,'Картриджи для принтеров');

/*!40000 ALTER TABLE `cat` ENABLE KEYS */;
UNLOCK TABLES;


# ���� ������� prod
# ------------------------------------------------------------

DROP TABLE IF EXISTS `prod`;

CREATE TABLE `prod` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `cat_id` int(11) unsigned NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` decimal(16,2) DEFAULT '0.00',
  PRIMARY KEY (`id`),
  KEY `fk_cat` (`cat_id`),
  CONSTRAINT `fk_cat` FOREIGN KEY (`cat_id`) REFERENCES `cat` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=cp1251;

LOCK TABLES `prod` WRITE;
/*!40000 ALTER TABLE `prod` DISABLE KEYS */;

INSERT INTO `prod` (`id`, `cat_id`, `name`, `price`)
VALUES
	(1,1,'Принтер Epson',200.00),
	(2,1,'Принтер HP',220.00),
	(3,1,'Принтер Samsung',195.95),
	(4,1,'Принтер The Print',999999.99),
	(5,2,'Картридж для принтера Epson',30.00),
	(6,2,'Картридж для принтера HP',55.55),
	(7,2,'Картридж для принтера Samsung',79.90),
	(8,2,'Картридж для принтера The Print',999999.99),
	(9,1,'Ещё один продукт',0.00);

/*!40000 ALTER TABLE `prod` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
