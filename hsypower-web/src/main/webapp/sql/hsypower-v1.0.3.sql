/*Table structure for table `Audit` */
DROP TABLE IF EXISTS `Audit`;
CREATE TABLE `Audit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `referer` varchar(255) DEFAULT NULL,
  `remoteIp` varchar(32) DEFAULT NULL,
  `localIp` varchar(32) DEFAULT NULL,
  `userAgent` varchar(255) DEFAULT NULL,
  `accessDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
