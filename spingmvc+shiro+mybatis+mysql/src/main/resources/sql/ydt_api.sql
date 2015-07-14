/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50611
Source Host           : localhost:3306
Source Database       : ydt_api

Target Server Type    : MYSQL
Target Server Version : 50611
File Encoding         : 65001

Date: 2014-10-31 11:36:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `apk`
-- ----------------------------
DROP TABLE IF EXISTS `apk`;
CREATE TABLE `apk` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `pack_name` varchar(64) NOT NULL,
  `introduction` longtext NOT NULL,
  `version_code` int(11) NOT NULL,
  `icon_url` varchar(255) NOT NULL,
  `type` int(11) NOT NULL,
  `created` varchar(255) NOT NULL,
  `publiced` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of apk
-- ----------------------------
INSERT INTO `apk` VALUES ('1', '滴滴打石', '专属滴滴豆神', '自主研发，下载免费', '20150612','http://doubi.com.cn/apk?id=1','1','2015-06-12','1');
-- ----------------------------
-- Table structure for `apk_image`
-- ----------------------------
DROP TABLE IF EXISTS `apk_image`;
CREATE TABLE `apk_image` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `image_url` varchar(255) NOT NULL,
  `apk_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of apk_image
-- ----------------------------

-- ----------------------------
-- Table structure for `article`
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(64) NOT NULL,
  `author` varchar(64) NOT NULL,
  `content` longtext NOT NULL,
  `publiced` tinyint(1) NOT NULL,
  `updated` datetime NOT NULL,
  `created` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of article
-- ----------------------------

-- ----------------------------
-- Table structure for `authentication`
-- ----------------------------
DROP TABLE IF EXISTS `authentication`;
CREATE TABLE `authentication` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `device_id` bigint(20) NOT NULL,
  `hospital_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of authentication
-- ----------------------------

-- ----------------------------
-- Table structure for `banner`
-- ----------------------------
DROP TABLE IF EXISTS `banner`;
CREATE TABLE `banner` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` int(11) NOT NULL,
  `cover_url` varchar(255) NOT NULL,
  `created` datetime NOT NULL,
  `priority` int(11) NOT NULL,
  `article_id` bigint(20) DEFAULT NULL,
  `hospital_id` bigint(20) DEFAULT NULL,
  `apk_id` bigint(20) DEFAULT NULL,
  `film_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of banner
-- ----------------------------
INSERT INTO `banner` VALUES ('2', '1', 'http://png.png', '2014-10-29 11:27:44', '1', null, null, null, '1');
INSERT INTO `banner` VALUES ('3', '2', 'http://png.png', '2014-10-29 14:47:21', '1', '1', null, null, null);

-- ----------------------------
-- Table structure for `body`
-- ----------------------------
DROP TABLE IF EXISTS `body`;
CREATE TABLE `body` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `part_name` varchar(64) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of body
-- ----------------------------

-- ----------------------------
-- Table structure for `department`
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL,
  `cover_url` varchar(255) DEFAULT NULL,
  `introduction` longtext NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of department
-- ----------------------------

-- ----------------------------
-- Table structure for `department_hospitals`
-- ----------------------------
DROP TABLE IF EXISTS `department_hospitals`;
CREATE TABLE `department_hospitals` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `department_id` bigint(20) NOT NULL,
  `hospital_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of department_hospitals
-- ----------------------------

-- ----------------------------
-- Table structure for `device`
-- ----------------------------
DROP TABLE IF EXISTS `device`;
CREATE TABLE `device` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `imei` varchar(16) DEFAULT NULL,
  `mac` varchar(16) NOT NULL,
  `imsi` varchar(16) DEFAULT NULL,
  `dpi` varchar(16) DEFAULT NULL,
  `model` varchar(32) DEFAULT NULL,
  `created` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `mac` (`mac`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of device
-- ----------------------------

-- ----------------------------
-- Table structure for `device_usage`
-- ----------------------------
DROP TABLE IF EXISTS `device_usage`;
CREATE TABLE `device_usage` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `net_type` tinyint(4) NOT NULL,
  `ip` varchar(255) NOT NULL,
  `province` varchar(32) DEFAULT NULL,
  `city` varchar(32) DEFAULT NULL,
  `open_time` datetime NOT NULL,
  `exit_time` datetime NOT NULL,
  `created` datetime NOT NULL,
  `device_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of device_usage
-- ----------------------------

-- ----------------------------
-- Table structure for `disease`
-- ----------------------------
DROP TABLE IF EXISTS `disease`;
CREATE TABLE `disease` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `introduction` longtext NOT NULL,
  `cause` longtext NOT NULL,
  `pathogenesis` longtext NOT NULL,
  `clinical_manifestation` longtext NOT NULL,
  `complication` longtext NOT NULL,
  `diagnosis` longtext NOT NULL,
  `treatment` longtext NOT NULL,
  `prevention` varchar(255) NOT NULL,
  `cover_url` longtext,
  `publiced` tinyint(1) NOT NULL,
  `department_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of disease
-- ----------------------------

-- ----------------------------
-- Table structure for `disease_symptoms`
-- ----------------------------
DROP TABLE IF EXISTS `disease_symptoms`;
CREATE TABLE `disease_symptoms` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `disease_id` bigint(20) NOT NULL,
  `symptom_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of disease_symptoms
-- ----------------------------

-- ----------------------------
-- Table structure for `film`
-- ----------------------------
DROP TABLE IF EXISTS `film`;
CREATE TABLE `film` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL,
  `duration` varchar(32) DEFAULT NULL,
  `cover_url` varchar(255) NOT NULL,
  `introduction` longtext NOT NULL,
  `director` varchar(32) NOT NULL,
  `actor` varchar(64) NOT NULL,
  `star` decimal(2,1) NOT NULL,
  `country` varchar(32) NOT NULL,
  `screen` varchar(32) NOT NULL,
  `type` int(11) NOT NULL,
  `country_id` bigint(20) NOT NULL,
  `screen_id` bigint(20) NOT NULL,
  `publiced` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of film
-- ----------------------------
INSERT INTO `film` VALUES ('1', '白发魔女传', '1:40:30', 'http://192.168.1.83:8080/static/img/baifamonv.jpg', '川陕总督卓仲廉（胡晓光 饰）的孙子卓一航（黄晓明 饰）刚刚成为武当派的新掌门人，逢此良机，他奉紫阳道长（于承惠 饰）之命前往京城进奉红丸。谁知魏忠贤（倪大宏 饰）从中作梗，用假红丸毒死登基不久的明光宗，之后把持朝政，派出锦衣卫追杀卓一航。与此同时，川陕副将金独异（赵文卓 饰）图谋害死卓仲廉，栽赃于救助灾民的魔教女子玉罗刹练霓裳（范冰冰 饰）。混战中有过一面之缘的练霓裳和卓一航再度...', '张艺谋', '章子怡', '9.9', '大陆', '2014', '1', '5', '3', '0');
INSERT INTO `film` VALUES ('2', '四大名捕2', '1:45:30', 'http://192.168.1.83:8080/static/img/sidamingbu2.jpg', '多事的追命（郑中基 饰）、铁手（邹兆龙 饰）耍花招，撮合冷血（邓超 饰）、无情（刘亦菲 饰）这对小情人，神侯府上下正其乐融融，空气却传来浓烈的血腥味道，三里之外的城郊老宅，杀人凶案正在发生！ 六扇门接获消息，捕神（成泰燊 饰）也带同姬摇花（江一燕 饰）等前来了解案情，双方合并追查，发现此案与六扇门早前追查的几宗灭门案有牵连。 \r\n　　悬案未决，六扇门却在碑林发现了捕神的尸体，而此前正是...', '', '', '6.0', '大陆', '2013', '1', '6', '4', '0');
INSERT INTO `film` VALUES ('3', '心花路放', '1:40:30', 'http://192.168.1.83:8080/static/img/xinhuanufang.jpg', '耿浩（黄渤 饰）在偶遇“小三”危机后，陷入情感困境。面对背叛，耿浩陷入了难以自拔的痛苦之中，好基友郝义（徐峥 饰）为了帮他摆脱痛苦，决定带他南下“猎艳”，遍访“百花”。于是两个“暴走兄弟”带上一只狗，开始了一段疯狂而搞笑的放浪旅途。一路上他们结识了各式女伴，并经历了一连串奇葩的遭遇。最后，两人最终明白了爱的真谛，并收获了彼此的幸福。', '', '', '5.0', '大陆', '2012', '1', '7', '3', '0');
INSERT INTO `film` VALUES ('4', '丛林战士', '1:37:20', 'http://192.168.1.83:8080/static/img/chonglinzhanshi.jpg', '一些无名的战争英雄在越战的战场上奋勇杀敌，直到今天我们才能了解他们过去的事迹。', '', '', '4.0', '欧美', '2000', '1', '8', '4', '0');
INSERT INTO `film` VALUES ('5', '阻击手', '1:43:20', 'http://192.168.1.83:8080/static/img/chonglinzhanshi.jpg', '一些无名的战争英雄在越战的战场上奋勇杀敌，直到今天我们才能了解他们过去的事迹。', '', '', '3.0', '日本', '80', '1', '9', '3', '0');
INSERT INTO `film` VALUES ('6', '影片名', '1:30:50', 'http://192.168.1.83:8080/static/img/chonglinzhanshi.jpg', '一些无名的战争英雄在越战的战场上奋勇杀敌，直到今天我们才能了解他们过去的事迹。', '', '', '6.0', '韩国', '90', '2', '5', '3', '0');
INSERT INTO `film` VALUES ('7', '影片名', '1:30:50', 'http://192.168.1.83:8080/static/img/chonglinzhanshi.jpg', '一些无名的战争英雄在越战的战场上奋勇杀敌，直到今天我们才能了解他们过去的事迹。', '', '', '7.0', '大陆', '80', '2', '6', '3', '0');
INSERT INTO `film` VALUES ('8', '影片名', '1:30:50', 'http://192.168.1.83:8080/static/img/chonglinzhanshi.jpg', '一些无名的战争英雄在越战的战场上奋勇杀敌，直到今天我们才能了解他们过去的事迹。', '', '', '4.0', '大陆', '90', '2', '7', '3', '0');
INSERT INTO `film` VALUES ('9', '影片名', '1:30:50', 'http://192.168.1.83:8080/static/img/chonglinzhanshi.jpg', '一些无名的战争英雄在越战的战场上奋勇杀敌，直到今天我们才能了解他们过去的事迹。', '', '', '3.0', '大陆', '80', '2', '8', '3', '0');
INSERT INTO `film` VALUES ('10', '影片名', '1:30:50', 'http://192.168.1.83:8080/static/img/chonglinzhanshi.jpg', '一些无名的战争英雄在越战的战场上奋勇杀敌，直到今天我们才能了解他们过去的事迹。', '', '', '9.9', '大陆', '90', '2', '9', '3', '0');
INSERT INTO `film` VALUES ('11', '影片名', '1:30:50', 'http://192.168.1.83:8080/static/img/chonglinzhanshi.jpg', '一些无名的战争英雄在越战的战场上奋勇杀敌，直到今天我们才能了解他们过去的事迹。', '', '', '7.0', '大陆', '2014', '2', '5', '3', '0');
INSERT INTO `film` VALUES ('12', '影片名', '1:30:50', 'http://192.168.1.83:8080/static/img/chonglinzhanshi.jpg', '一些无名的战争英雄在越战的战场上奋勇杀敌，直到今天我们才能了解他们过去的事迹。', '', '', '5.0', '大陆', '2013', '2', '6', '4', '0');
INSERT INTO `film` VALUES ('13', '影片名', '1:30:50', 'http://192.168.1.83:8080/static/img/chonglinzhanshi.jpg', '一些无名的战争英雄在越战的战场上奋勇杀敌，直到今天我们才能了解他们过去的事迹。', '', '', '3.0', '大陆', '2012', '2', '7', '4', '0');
INSERT INTO `film` VALUES ('14', '影片名', '1:30:50', 'http://192.168.1.83:8080/static/img/chonglinzhanshi.jpg', '一些无名的战争英雄在越战的战场上奋勇杀敌，直到今天我们才能了解他们过去的事迹。', '', '', '2.0', '大陆', '2011', '1', '8', '4', '0');
INSERT INTO `film` VALUES ('15', '影片名', '1:30:50', 'http://192.168.1.83:8080/static/img/chonglinzhanshi.jpg', '一些无名的战争英雄在越战的战场上奋勇杀敌，直到今天我们才能了解他们过去的事迹。', '', '', '5.0', '大陆', '3', '1', '9', '4', '0');
INSERT INTO `film` VALUES ('16', '11', '11', '/upload/201410/6a90a88a1050417aa3abcac0cb1bbc30.png', '22', '11', '11', '9.9', '大陆', '2013', '1', '5', '4', '0');
INSERT INTO `film` VALUES ('17', '11', '11', '/upload/201410/808d594b33c34724ab49761846fa126a.png', '1545', '1', '1', '9.9', '大陆', '2014', '1', '5', '3', '0');

-- ----------------------------
-- Table structure for `film_section`
-- ----------------------------
DROP TABLE IF EXISTS `film_section`;
CREATE TABLE `film_section` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `section` int(11) NOT NULL,
  `film_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of film_section
-- ----------------------------
INSERT INTO `film_section` VALUES ('21', '1', '1');
INSERT INTO `film_section` VALUES ('22', '2', '1');
INSERT INTO `film_section` VALUES ('23', '1', '2');
INSERT INTO `film_section` VALUES ('24', '2', '2');
INSERT INTO `film_section` VALUES ('25', '3', '2');
INSERT INTO `film_section` VALUES ('34', '4', '2');
INSERT INTO `film_section` VALUES ('35', '5', '2');
INSERT INTO `film_section` VALUES ('36', '1', '3');
INSERT INTO `film_section` VALUES ('37', '2', '3');

-- ----------------------------
-- Table structure for `film_type`
-- ----------------------------
DROP TABLE IF EXISTS `film_type`;
CREATE TABLE `film_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `priority` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of film_type
-- ----------------------------
INSERT INTO `film_type` VALUES ('1', '电视剧', '1');
INSERT INTO `film_type` VALUES ('2', '电影', '2');
INSERT INTO `film_type` VALUES ('3', '综艺', '3');

-- ----------------------------
-- Table structure for `hospital`
-- ----------------------------
DROP TABLE IF EXISTS `hospital`;
CREATE TABLE `hospital` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL,
  `province` varchar(32) NOT NULL,
  `city` varchar(32) NOT NULL,
  `address` varchar(128) NOT NULL,
  `telephone` varchar(32) DEFAULT NULL,
  `website` varchar(128) DEFAULT NULL,
  `cover_url` varchar(255) DEFAULT NULL,
  `introduction` longtext NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hospital
-- ----------------------------

-- ----------------------------
-- Table structure for `music`
-- ----------------------------
DROP TABLE IF EXISTS `music`;
CREATE TABLE `music` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `music_url` varchar(128) NOT NULL,
#   `created` datetime NOT NULL,
  `created` varchar(32) NOT NULL,
  `publiced` tinyint(1) NOT NULL,
  `singer_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of music
-- ----------------------------
INSERT INTO `music` VALUES ('1', '时间都去哪儿', 'http://baidu.com/music?id=10000',  '2014-12-12','1','1');
INSERT INTO `music` VALUES ('2', '爱你一万年', 'http://baidu.com/music?id=10201',  '2015-2-12','1','1');
INSERT INTO `music` VALUES ('3', 'Call Me Maybe', 'http://baidu.com/music?id=10200',  '2015-06-12','1','1');
-- ----------------------------
-- Table structure for `notice`
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(128) NOT NULL,
  `content` longtext NOT NULL,
  `created` datetime NOT NULL,
  `publiced` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of notice
-- ----------------------------

-- ----------------------------
-- Table structure for `novel`
-- ----------------------------
DROP TABLE IF EXISTS `novel`;
CREATE TABLE `novel` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `author` varchar(32) NOT NULL,
  `status` int(11) NOT NULL,
  `cover_url` varchar(255) NOT NULL,
  `novel_url` varchar(255) NOT NULL,
  `introduction` longtext NOT NULL,
  `created` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of novel
-- ----------------------------
INSERT INTO novel VALUES ('1','平凡的世界','路遥','1','http://baidu.com/novel?cover=1','http://baidu.com/novel?id=1','讲述一个发生在文革期间，发生在农民身上的故事','1986-05-01');
-- ----------------------------
-- Table structure for `section_segment`
-- ----------------------------
DROP TABLE IF EXISTS `section_segment`;
CREATE TABLE `section_segment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `index` int(11) NOT NULL,
  `url` varchar(255) NOT NULL,
  `film_section_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of section_segment
-- ----------------------------
INSERT INTO `section_segment` VALUES ('19', '0', 'http://192.168.1.83:8080/static/film/0001.flv', '21');
INSERT INTO `section_segment` VALUES ('20', '1', 'http://192.168.1.83:8080/static/film/0001.flv', '21');
INSERT INTO `section_segment` VALUES ('21', '2', 'http://192.168.1.83:8080/static/film/0001.flv', '21');
INSERT INTO `section_segment` VALUES ('22', '3', 'http://192.168.1.83:8080/static/film/0001.flv', '21');
INSERT INTO `section_segment` VALUES ('23', '4', 'http://192.168.1.83:8080/static/film/0001.flv', '21');
INSERT INTO `section_segment` VALUES ('24', '0', 'http://192.168.1.83:8080/static/film/0001.flv', '22');
INSERT INTO `section_segment` VALUES ('25', '1', 'http://192.168.1.83:8080/static/film/0001.flv', '22');
INSERT INTO `section_segment` VALUES ('26', '0', 'http://192.168.1.83:8080/static/film/0001.flv', '23');
INSERT INTO `section_segment` VALUES ('27', '1', 'http://192.168.1.83:8080static/film/0002.flv', '23');
INSERT INTO `section_segment` VALUES ('28', '2', 'http://192.168.1.83:8080/static/film/0003.flv', '23');
INSERT INTO `section_segment` VALUES ('29', '3', 'http://192.168.1.83:8080/static/film/0006.flv', '23');
INSERT INTO `section_segment` VALUES ('30', '4', 'http://192.168.1.83:8080/static/film/0007.flv', '23');
INSERT INTO `section_segment` VALUES ('31', '5', 'http://192.168.1.83:8080/static/film/0008.flv', '23');
INSERT INTO `section_segment` VALUES ('32', '6', 'http://192.168.1.83:8080/static/film/0009.flv', '23');
INSERT INTO `section_segment` VALUES ('33', '7', 'http://192.168.1.83:8080/static/film/0010.flv', '23');
INSERT INTO `section_segment` VALUES ('34', '8', 'http://192.168.1.83:8080/static/film/0011.flv', '23');
INSERT INTO `section_segment` VALUES ('35', '9', 'http://192.168.1.83:8080/static/film/00012.flv', '23');
INSERT INTO `section_segment` VALUES ('36', '10', 'http://192.168.1.83:8080static/film/0013.flv', '23');
INSERT INTO `section_segment` VALUES ('37', '0', 'http://192.168.1.83:8080/static/film/0001.flv', '24');
INSERT INTO `section_segment` VALUES ('38', '1', 'http://192.168.1.80:8080/static/film/0002.flv', '24');
INSERT INTO `section_segment` VALUES ('39', '0', 'http://192.168.1.80:8080/static/film/0001.flv', '25');
INSERT INTO `section_segment` VALUES ('40', '1', 'http://192.168.1.80:8080/static/film/0002.flv', '25');
INSERT INTO `section_segment` VALUES ('41', '0', 'http://192.168.1.80:8080/static/film/0001.flv', '34');
INSERT INTO `section_segment` VALUES ('42', '1', 'http://192.168.1.80:8080/static/film/0002.flv', '34');
INSERT INTO `section_segment` VALUES ('43', '0', 'http://192.168.1.80:8080/static/film/0001.flv', '35');
INSERT INTO `section_segment` VALUES ('44', '1', 'http://192.168.1.80:8080/static/film/0001.flv', '35');
INSERT INTO `section_segment` VALUES ('45', '0', 'http://192.168.1.80:8080/static/film/0001.flv', '36');
INSERT INTO `section_segment` VALUES ('46', '1', 'http://192.168.1.80:8080/static/film/0002.flv', '36');
INSERT INTO `section_segment` VALUES ('47', '0', 'http://192.168.1.80:8080/static/film/0001.flv', '37');
INSERT INTO `section_segment` VALUES ('48', '1', 'http://192.168.1.80:8080/static/film/0002.flv', '37');

-- ----------------------------
-- Table structure for `singer`
-- ----------------------------
DROP TABLE IF EXISTS `singer`;
CREATE TABLE `singer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `introduction` longtext NOT NULL,
  `country` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  `created` datetime NOT NULL,
  `publiced` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of singer
-- ----------------------------

-- ----------------------------
-- Table structure for `symptom`
-- ----------------------------
DROP TABLE IF EXISTS `symptom`;
CREATE TABLE `symptom` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `introduction` longtext NOT NULL,
  `suggestion` longtext NOT NULL,
  `body_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of symptom
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_dept`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `deptId` int(11) NOT NULL,
  `deptName` text,
  PRIMARY KEY (`deptId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('1', '技术部');
INSERT INTO `sys_dept` VALUES ('2', '营销部');

-- ----------------------------
-- Table structure for `sys_permissions`
-- ----------------------------
DROP TABLE IF EXISTS `sys_permissions`;
CREATE TABLE `sys_permissions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `permission` varchar(100) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `available` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_sys_permissions_permission` (`permission`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_permissions
-- ----------------------------
INSERT INTO `sys_permissions` VALUES ('13', 'rbac/user:create', '创建用户', '1');
INSERT INTO `sys_permissions` VALUES ('14', 'rbac/user:update', '更新用户', '1');
INSERT INTO `sys_permissions` VALUES ('16', 'rbac/user:delete', '删除用户', '1');
INSERT INTO `sys_permissions` VALUES ('17', 'rbac/user:list', '用户列表', '1');
INSERT INTO `sys_permissions` VALUES ('18', 'rbac/user:correlationRoles', '分配角色', '1');
INSERT INTO `sys_permissions` VALUES ('19', 'rbac/role:list', '角色列表', '1');
INSERT INTO `sys_permissions` VALUES ('20', 'rbac/role:create', '创建角色', '1');
INSERT INTO `sys_permissions` VALUES ('21', 'rbac/role:update', '更新角色', '1');
INSERT INTO `sys_permissions` VALUES ('22', 'rbac/role:delete', '删除角色', '1');
INSERT INTO `sys_permissions` VALUES ('23', 'rbac/role:allRoles', '获取用户角色', '1');
INSERT INTO `sys_permissions` VALUES ('24', 'rbac/role:correlationPermissions', '分配权限', '1');
INSERT INTO `sys_permissions` VALUES ('25', 'rbac/permission:list', '权限列表', '1');
INSERT INTO `sys_permissions` VALUES ('26', 'rbac/permission:create', '创建权限', '1');
INSERT INTO `sys_permissions` VALUES ('27', 'rbac/permission:update', '更新权限', '1');
INSERT INTO `sys_permissions` VALUES ('28', 'rbac/permission:delete', '删除权限', '1');
INSERT INTO `sys_permissions` VALUES ('29', 'rbac/permission:allPermissions', '获取角色权限', '1');

-- ----------------------------
-- Table structure for `sys_roles`
-- ----------------------------
DROP TABLE IF EXISTS `sys_roles`;
CREATE TABLE `sys_roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role` varchar(100) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `available` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_sys_roles_role` (`role`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_roles
-- ----------------------------
INSERT INTO `sys_roles` VALUES ('9', 'admin', '管理员', '1');
INSERT INTO `sys_roles` VALUES ('10', 'user', '其他用户', '1');

-- ----------------------------
-- Table structure for `sys_roles_permissions`
-- ----------------------------
DROP TABLE IF EXISTS `sys_roles_permissions`;
CREATE TABLE `sys_roles_permissions` (
  `role_id` bigint(20) NOT NULL DEFAULT '0',
  `permission_id` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`role_id`,`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_roles_permissions
-- ----------------------------
INSERT INTO `sys_roles_permissions` VALUES ('9', '13');
INSERT INTO `sys_roles_permissions` VALUES ('9', '14');
INSERT INTO `sys_roles_permissions` VALUES ('9', '16');
INSERT INTO `sys_roles_permissions` VALUES ('9', '17');
INSERT INTO `sys_roles_permissions` VALUES ('9', '18');
INSERT INTO `sys_roles_permissions` VALUES ('9', '19');
INSERT INTO `sys_roles_permissions` VALUES ('9', '20');
INSERT INTO `sys_roles_permissions` VALUES ('9', '21');
INSERT INTO `sys_roles_permissions` VALUES ('9', '22');
INSERT INTO `sys_roles_permissions` VALUES ('9', '23');
INSERT INTO `sys_roles_permissions` VALUES ('9', '24');
INSERT INTO `sys_roles_permissions` VALUES ('9', '25');
INSERT INTO `sys_roles_permissions` VALUES ('9', '26');
INSERT INTO `sys_roles_permissions` VALUES ('9', '27');
INSERT INTO `sys_roles_permissions` VALUES ('9', '28');
INSERT INTO `sys_roles_permissions` VALUES ('9', '29');
INSERT INTO `sys_roles_permissions` VALUES ('10', '17');
INSERT INTO `sys_roles_permissions` VALUES ('10', '19');
INSERT INTO `sys_roles_permissions` VALUES ('10', '25');

-- ----------------------------
-- Table structure for `sys_users`
-- ----------------------------
DROP TABLE IF EXISTS `sys_users`;
CREATE TABLE `sys_users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `salt` varchar(100) DEFAULT NULL,
  `locked` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_sys_users_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_users
-- ----------------------------
INSERT INTO `sys_users` VALUES ('17', 'admin', '0c1c8921b8f68b0990f315ee8a5c3200', '057eadae2287857953eb0ef826c4026f', '0');
INSERT INTO `sys_users` VALUES ('19', 'kevin', '059e9246141a3534320782da180322a2', '8cf922101deff08da9ac022ba71c8a1c', '0');

-- ----------------------------
-- Table structure for `sys_users_roles`
-- ----------------------------
DROP TABLE IF EXISTS `sys_users_roles`;
CREATE TABLE `sys_users_roles` (
  `user_id` bigint(20) NOT NULL DEFAULT '0',
  `role_id` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_users_roles
-- ----------------------------
INSERT INTO `sys_users_roles` VALUES ('17', '9');
INSERT INTO `sys_users_roles` VALUES ('19', '10');

-- ----------------------------
-- Table structure for `tag`
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` int(11) NOT NULL,
  `name` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tag
-- ----------------------------
INSERT INTO `tag` VALUES ('1', '1', '惊悚');
INSERT INTO `tag` VALUES ('2', '1', '喜剧');
INSERT INTO `tag` VALUES ('3', '1', '恐怖');
INSERT INTO `tag` VALUES ('4', '1', '动作');
INSERT INTO `tag` VALUES ('5', '1', '科幻');
INSERT INTO `tag` VALUES ('6', '1', '爱情');
INSERT INTO `tag` VALUES ('7', '1', '励志');
INSERT INTO `tag` VALUES ('8', '2', '2014');
INSERT INTO `tag` VALUES ('9', '2', '2015');
INSERT INTO `tag` VALUES ('10', '3', '大陆');
INSERT INTO `tag` VALUES ('11', '3', '香港');
INSERT INTO `tag` VALUES ('12', '3', '欧美');
INSERT INTO `tag` VALUES ('13', '3', '日本');
INSERT INTO `tag` VALUES ('14', '3', '韩国');
INSERT INTO `tag` VALUES ('15', '3', '其他地区');

-- ----------------------------
-- Table structure for `tag_apks`
-- ----------------------------
DROP TABLE IF EXISTS `tag_apks`;
CREATE TABLE `tag_apks` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tag_id` bigint(20) NOT NULL,
  `apk_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tag_apks
-- ----------------------------

-- ----------------------------
-- Table structure for `tag_films`
-- ----------------------------
DROP TABLE IF EXISTS `tag_films`;
CREATE TABLE `tag_films` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `film_id` bigint(20) NOT NULL,
  `tag_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tag_films
-- ----------------------------
INSERT INTO `tag_films` VALUES ('1', '1', '1');
INSERT INTO `tag_films` VALUES ('2', '1', '2');

-- ----------------------------
-- Table structure for `tag_novels`
-- ----------------------------
DROP TABLE IF EXISTS `tag_novels`;
CREATE TABLE `tag_novels` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tag_id` bigint(20) NOT NULL,
  `novel_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tag_novels
-- ----------------------------
