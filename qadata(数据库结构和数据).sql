/*
 Navicat MySQL Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 50560
 Source Host           : localhost:3306
 Source Schema         : wechat

 Target Server Type    : MySQL
 Target Server Version : 50560
 File Encoding         : 65001

 Date: 16/07/2018 16:53:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for qadata
-- ----------------------------
DROP TABLE IF EXISTS `qadata`;
CREATE TABLE `qadata`  (
  `id` bigint(20) NULL DEFAULT NULL,
  `question` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `optA` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `optB` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `optC` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `optD` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `answer` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of qadata
-- ----------------------------
INSERT INTO `qadata` VALUES (1, '请问世界杯冠军是谁？', '中国', '日本', '韩国', '菲利宾', 'A');
INSERT INTO `qadata` VALUES (2, '请问湖南科技大学在哪里？', '长沙', '北京', '上海', '火星', 'D');
INSERT INTO `qadata` VALUES (3, '请问1+1=？', '0', '1', '2', '3', 'C');
INSERT INTO `qadata` VALUES (4, '请问1+2=？', '0', '1', '2', '3', 'D');
INSERT INTO `qadata` VALUES (5, '请问2*2=？', '4', '3', '8', '10', 'A');
INSERT INTO `qadata` VALUES (6, '请问3*3=？', '5', '9', '7', '1', 'B');
INSERT INTO `qadata` VALUES (7, '请问9-5=？', '3', '2', '4', '5', 'C');
INSERT INTO `qadata` VALUES (8, '请问10/5=？', '2', '1', '4', '7', 'A');
INSERT INTO `qadata` VALUES (9, '请问6^2=？', '15', '10', '20', '36', 'D');
INSERT INTO `qadata` VALUES (10, '请问5!=？', '240', '100', '150', '300', 'A');
INSERT INTO `qadata` VALUES (0, '请问5!=？', '240', '100', '150', '300', 'A');

SET FOREIGN_KEY_CHECKS = 1;
