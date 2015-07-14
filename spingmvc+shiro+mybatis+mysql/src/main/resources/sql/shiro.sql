/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50614
Source Host           : localhost:3306
Source Database       : shiro

Target Server Type    : MYSQL
Target Server Version : 50614
File Encoding         : 65001

Date: 2014-05-16 17:17:04
*/

SET FOREIGN_KEY_CHECKS=0;

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
INSERT INTO `sys_permissions` VALUES ('13', 'shiro/user:create', '创建用户', '1');
INSERT INTO `sys_permissions` VALUES ('14', 'shiro/user:update', '更新用户', '1');
INSERT INTO `sys_permissions` VALUES ('16', 'shiro/user:delete', '删除用户', '1');
INSERT INTO `sys_permissions` VALUES ('17', 'shiro/user:list', '用户列表', '1');
INSERT INTO `sys_permissions` VALUES ('18', 'shiro/user:correlationRoles', '分配角色', '1');
INSERT INTO `sys_permissions` VALUES ('19', 'shiro/role:list', '角色列表', '1');
INSERT INTO `sys_permissions` VALUES ('20', 'shiro/role:create', '创建角色', '1');
INSERT INTO `sys_permissions` VALUES ('21', 'shiro/role:update', '更新角色', '1');
INSERT INTO `sys_permissions` VALUES ('22', 'shiro/role:delete', '删除角色', '1');
INSERT INTO `sys_permissions` VALUES ('23', 'shiro/role:allRoles', '获取用户角色', '1');
INSERT INTO `sys_permissions` VALUES ('24', 'shiro/role:correlationPermissions', '分配权限', '1');
INSERT INTO `sys_permissions` VALUES ('25', 'shiro/permission:list', '权限列表', '1');
INSERT INTO `sys_permissions` VALUES ('26', 'shiro/permission:create', '创建权限', '1');
INSERT INTO `sys_permissions` VALUES ('27', 'shiro/permission:update', '更新权限', '1');
INSERT INTO `sys_permissions` VALUES ('28', 'shiro/permission:delete', '删除权限', '1');
INSERT INTO `sys_permissions` VALUES ('29', 'shiro/permission:allPermissions', '获取角色权限', '1');

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
INSERT INTO `sys_users` VALUES ('17', 'admin', '90623ca892b905f32f7015e788a6ebdf', '8b03c436dd1468c7f6302bdb586e24e9', '0');
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
