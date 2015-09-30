/*Table structure for table `Channel` */
DROP TABLE IF EXISTS `Channel`;
CREATE TABLE `Channel` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createOn` datetime DEFAULT NULL,
  `createdBy` varchar(255) DEFAULT NULL,
  `modifiedBy` varchar(255) DEFAULT NULL,
  `modifyOn` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `bottom` tinyint(1) NOT NULL,
  `display` tinyint(1) NOT NULL,
  `leftSide` tinyint(1) NOT NULL,
  `navigate` tinyint(1) NOT NULL,
  `path` varchar(255) DEFAULT NULL,
  `sort` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

insert  into `Channel`(`id`,`createOn`,`createdBy`,`modifiedBy`,`modifyOn`,`name`,`bottom`,`display`,`leftSide`,`navigate`,`path`,`sort`) values (1,now(),'initial',null,null,'首页',0,1,0,1,'/index',1);
insert  into `Channel`(`id`,`createOn`,`createdBy`,`modifiedBy`,`modifyOn`,`name`,`bottom`,`display`,`leftSide`,`navigate`,`path`,`sort`) values (2,now(),'initial',null,null,'热销商品',0,1,0,1,'/product',2);
insert  into `Channel`(`id`,`createOn`,`createdBy`,`modifiedBy`,`modifyOn`,`name`,`bottom`,`display`,`leftSide`,`navigate`,`path`,`sort`) values (3,now(),'initial',null,null,'资质文件',1,1,0,1,'/qualification',4);
insert  into `Channel`(`id`,`createOn`,`createdBy`,`modifiedBy`,`modifyOn`,`name`,`bottom`,`display`,`leftSide`,`navigate`,`path`,`sort`) values (4,now(),'initial',null,null,'主要业绩',0,1,1,1,'/perfermance',5);
insert  into `Channel`(`id`,`createOn`,`createdBy`,`modifiedBy`,`modifyOn`,`name`,`bottom`,`display`,`leftSide`,`navigate`,`path`,`sort`) values (5,now(),'initial',null,null,'公司简介',1,1,1,1,'/introduce',3);
insert  into `Channel`(`id`,`createOn`,`createdBy`,`modifiedBy`,`modifyOn`,`name`,`bottom`,`display`,`leftSide`,`navigate`,`path`,`sort`) values (6,now(),'initial',null,null,'新闻快讯',0,1,1,1,'/news',6);
insert  into `Channel`(`id`,`createOn`,`createdBy`,`modifiedBy`,`modifyOn`,`name`,`bottom`,`display`,`leftSide`,`navigate`,`path`,`sort`) values (7,now(),'initial',null,null,'客户服务',1,1,1,1,'/service',7);
insert  into `Channel`(`id`,`createOn`,`createdBy`,`modifiedBy`,`modifyOn`,`name`,`bottom`,`display`,`leftSide`,`navigate`,`path`,`sort`) values (8,now(),'initial',null,null,'联系我们',1,1,1,1,'/contact',9);
insert  into `Channel`(`id`,`createOn`,`createdBy`,`modifiedBy`,`modifyOn`,`name`,`bottom`,`display`,`leftSide`,`navigate`,`path`,`sort`) values (9,now(),'initial',null,null,'销售网络',1,1,1,0,'/sales',8);

/*Table structure for table `Company` */
DROP TABLE IF EXISTS `Company`;
CREATE TABLE `Company` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createOn` datetime DEFAULT NULL,
  `createdBy` varchar(255) DEFAULT NULL,
  `modifiedBy` varchar(255) DEFAULT NULL,
  `modifyOn` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `customerService` longtext,
  `description` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `introduce` longtext,
  `keywords` varchar(255) DEFAULT NULL,
  `saleTel` varchar(255) DEFAULT NULL,
  `tax` varchar(255) DEFAULT NULL,
  `techServiceTel` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `website` varchar(255) DEFAULT NULL,
  `zipcode` varchar(255) DEFAULT NULL,
  `salesNetwork` longtext,
  `perfermance` longtext,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

insert  into `Company`(`id`,`createOn`,`createdBy`,`modifiedBy`,`modifyOn`,`name`,`website`) values (1,now(),'initial',null,null,'江苏华世远电力技术有限公司','www.hsypower-epct.com');

/*Table structure for table `News` */
DROP TABLE IF EXISTS `News`;
CREATE TABLE `News` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createOn` datetime DEFAULT NULL,
  `createdBy` varchar(255) DEFAULT NULL,
  `modifiedBy` varchar(255) DEFAULT NULL,
  `modifyOn` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `content` longtext,
  `publish` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `Product` */
DROP TABLE IF EXISTS `Product`;
CREATE TABLE `Product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createOn` datetime DEFAULT NULL,
  `createdBy` varchar(255) DEFAULT NULL,
  `modifiedBy` varchar(255) DEFAULT NULL,
  `modifyOn` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `introduce` longtext DEFAULT NULL,
  `productCategoryId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_854f75009f3f410095ef21834c6` (`productCategoryId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `ProductCategory` */
DROP TABLE IF EXISTS `ProductCategory`;
CREATE TABLE `ProductCategory` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createOn` datetime DEFAULT NULL,
  `createdBy` varchar(255) DEFAULT NULL,
  `modifiedBy` varchar(255) DEFAULT NULL,
  `modifyOn` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `QualificationFile` */
DROP TABLE IF EXISTS `QualificationFile`;
CREATE TABLE `QualificationFile` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createOn` datetime DEFAULT NULL,
  `createdBy` varchar(255) DEFAULT NULL,
  `modifiedBy` varchar(255) DEFAULT NULL,
  `modifyOn` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `filePath` varchar(255) DEFAULT NULL,
  `qualificationFileCategoryId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_1d53a3c6cf3344db84ee8f3a09c` (`qualificationFileCategoryId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `QualificationFileCategory` */
DROP TABLE IF EXISTS `QualificationFileCategory`;
CREATE TABLE `QualificationFileCategory` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createOn` datetime DEFAULT NULL,
  `createdBy` varchar(255) DEFAULT NULL,
  `modifiedBy` varchar(255) DEFAULT NULL,
  `modifyOn` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `filePath` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `User` */
DROP TABLE IF EXISTS `User`;
CREATE TABLE `User` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createOn` datetime DEFAULT NULL,
  `createdBy` varchar(255) DEFAULT NULL,
  `modifiedBy` varchar(255) DEFAULT NULL,
  `modifyOn` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `activity` tinyint(1) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

insert  into `User`(`id`,`createOn`,`createdBy`,`modifiedBy`,`modifyOn`,`name`,`activity`,`password`) values (1,now(),'initial',null,null,'admin',1,'password');