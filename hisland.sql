/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 100119
 Source Host           : localhost:3306
 Source Schema         : hisland

 Target Server Type    : MySQL
 Target Server Version : 100119
 File Encoding         : 65001

 Date: 15/12/2018 22:23:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `topicId` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `authorId` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `date` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `topicId`(`topicId`) USING BTREE,
  INDEX `authorId`(`authorId`) USING BTREE,
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`topicId`) REFERENCES `topic` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`authorId`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('9cb7b8de-5efd-4900-817e-c40d5291d930', '2346', 'd790bc5e-9998-4c0e-9243-3c007697', '你们放的屁怎么都那么臭呀', '2018-12-15 22:22:19');
INSERT INTO `comment` VALUES ('9df29a0d-6d54-4167-a227-9c1efb74b5b8', '2346', '1235', '楼上的尼玛死了', '2018-12-15 22:23:09');

-- ----------------------------
-- Table structure for topic
-- ----------------------------
DROP TABLE IF EXISTS `topic`;
CREATE TABLE `topic`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `authorId` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `date` datetime(0) NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `authorId`(`authorId`) USING BTREE,
  CONSTRAINT `topic_ibfk_1` FOREIGN KEY (`authorId`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of topic
-- ----------------------------
INSERT INTO `topic` VALUES ('2345', '尼玛飞了', '1234', '2018-12-09 21:44:45', '尼玛尼玛');
INSERT INTO `topic` VALUES ('2346', '楼上的马才飞了呢', '1235', '2018-12-09 21:45:45', '尼玛才尼玛呢');
INSERT INTO `topic` VALUES ('e638731e-4a98-403f-88a4-6ad5a85e49db', '你们的屁真臭', 'd790bc5e-9998-4c0e-9243-3c007697', '2018-12-15 21:36:06', '你们屁放那么大声干嘛');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `sex` varchar(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `hobby` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `group` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `states` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1234', '王大力', 'f', '手冲', '13339999879', 'N', 'N');
INSERT INTO `user` VALUES ('1235', '刘皮皮', 'f', '快速手冲', '12334545544', 'N', 'N');
INSERT INTO `user` VALUES ('3d6b1f3c-adf0-4b09-b0de-1686c31d5c9a', '王大力', 'f', '手冲', '13339999879', 'N', 'N');
INSERT INTO `user` VALUES ('9a72befb-2677-48a4-8e84-9ac4230f', '马赛克', 'f', '天天手冲', '19776549765', 'N', 'N');
INSERT INTO `user` VALUES ('d790bc5e-9998-4c0e-9243-3c007697', '王屁屁', 'f', '当众手冲', '19796959097', 'N', 'N');
INSERT INTO `user` VALUES ('f89c6f6e-7096-47aa-95da-cba984ffb160', '你妹', 'm', '反向手冲', '18798987654', 'N', 'N');

SET FOREIGN_KEY_CHECKS = 1;
