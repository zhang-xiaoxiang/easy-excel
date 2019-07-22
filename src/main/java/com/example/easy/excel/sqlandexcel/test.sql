/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 22/07/2019 21:43:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin_user
-- ----------------------------
DROP TABLE IF EXISTS `admin_user`;
CREATE TABLE `admin_user`  (
  `admin_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '管理员主键ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `level` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理员级别',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`admin_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 66 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '管理员用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_user
-- ----------------------------
INSERT INTO `admin_user` VALUES (1, '周杰伦', '123456', 'CEO', '2019-07-21 19:02:25');
INSERT INTO `admin_user` VALUES (102, '管理员1', '888888', '打酱油', '2019-07-22 21:10:14');
INSERT INTO `admin_user` VALUES (103, '管理员2', '999999', '超级管理员', '2019-07-22 21:10:14');
INSERT INTO `admin_user` VALUES (104, '管理员2', '222222', '主管', '2019-07-22 21:10:14');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` varbinary(16) NOT NULL COMMENT '用户主键ID(使用bigint也行)',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户姓名',
  `age` int(11) NULL DEFAULT NULL COMMENT '用户年龄',
  `birthday` date NULL DEFAULT NULL COMMENT '用户生日',
  `sex` int(2) NULL DEFAULT NULL COMMENT '性别(0表示女,1表示男)可以拓展3表示变态',
  `height` double(8, 2) NULL DEFAULT NULL COMMENT '用户身高',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户地址',
  `credits` float(8, 2) NULL DEFAULT NULL COMMENT '用户积分',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (0x31, '长草颜团子', 5, '2004-06-12', 1, 75.05, '重庆市江北区江北城', 10.50, '2019-07-18 10:33:30');
INSERT INTO `user` VALUES (0x3130303836, '用户1', 5, '2014-03-27', 1, 75.05, '四川省大竹县', 10.80, '2019-07-22 21:10:14');
INSERT INTO `user` VALUES (0x3130303837, '用户2', 27, '1992-06-14', 1, 176.00, '重庆市渝中区临江门', 10.80, '2019-07-22 21:10:14');
INSERT INTO `user` VALUES (0x3130303838, '张三', 12, '2019-07-23', 0, 129.00, '北京市朝阳区', 10.80, '2019-07-22 21:10:14');
INSERT INTO `user` VALUES (0x3130303839, '李四', 22, '2019-07-23', 0, 189.00, '重庆市渝中区临江门', 10.80, '2019-07-22 21:10:14');
INSERT INTO `user` VALUES (0x3130303930, '王五', 13, '2019-07-24', 1, 190.00, '北京市朝阳区', 10.80, '2019-07-22 21:10:14');
INSERT INTO `user` VALUES (0x32, '张晓祥', 27, '1992-06-12', 1, 176.00, '重庆市江北区五里店', 20.00, '2019-07-21 11:25:17');

SET FOREIGN_KEY_CHECKS = 1;
