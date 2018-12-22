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

 Date: 22/12/2018 20:15:02
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
  INDEX `comment_ibfk_1`(`topicId`) USING BTREE,
  INDEX `comment_ibfk_2`(`authorId`) USING BTREE,
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`topicId`) REFERENCES `topic` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`authorId`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('79463694-5159-43d6-98bf-15dc53656254', 'd7f276c3-79a2-496d-90f6-8d4e57c6fcc2', 'admin', '我最牛逼，不服打我啊', '2018-12-17 21:02:06');
INSERT INTO `comment` VALUES ('a11c2b89-6367-4c89-8e4e-60540d570f4a', '2345', 'admin', '尼玛尼玛呢，我是权限', '2018-12-18 15:44:50');

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
  INDEX `topic_ibfk_1`(`authorId`) USING BTREE,
  CONSTRAINT `topic_ibfk_1` FOREIGN KEY (`authorId`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of topic
-- ----------------------------
INSERT INTO `topic` VALUES ('212e9e8b-9795-4f86-938d-58e55477ddb5', '今后这里就是我说了算了', 'admin', '2018-12-17 21:23:38', '你们都是SB');
INSERT INTO `topic` VALUES ('2345', '尼玛飞了', '1234', '2018-12-09 21:44:45', '尼玛尼玛');
INSERT INTO `topic` VALUES ('962c7d90-d09f-410f-a873-d51a7f62ef82', '你们都不说话是马都死了吗', 'admin', '2018-12-18 14:59:14', '嘴臭一时爽，一直嘴臭一直爽（反正我是管理员）');
INSERT INTO `topic` VALUES ('9e4567c8-3f8e-4a05-b934-7bf1739f61f5', '骑兵还是步兵？', '9a72befb-2677-48a4-8e84-9ac4230f', '2018-12-17 19:03:55', '反正你们都是loser，我是不需要这些的');
INSERT INTO `topic` VALUES ('bf7865cd-531b-4bad-a8ae-f3cb9acf7f69', '哈哈哈哈哈', 'admin', '2018-12-18 15:09:22', '哈哈哈哈哈哈哈我是权限');
INSERT INTO `topic` VALUES ('d7f276c3-79a2-496d-90f6-8d4e57c6fcc2', '管理员在此，谁敢造次', 'admin', '2018-12-17 21:01:42', '你们再敢乱说话当心我权限你们');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `passwd` varchar(25) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
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
INSERT INTO `user` VALUES ('1234', '1234', '王大力', 'f', '手冲', '13339999879', 'N', 'N');
INSERT INTO `user` VALUES ('1235', '1234', '刘皮皮', 'f', '快速手冲', '12334545544', 'N', 'N');
INSERT INTO `user` VALUES ('1237', '1234', '全权大表哥', 'f', '倒立手冲', '13300900808', 'N', 'N');
INSERT INTO `user` VALUES ('3d6b1f3c-adf0-4b09-b0de-1686c31d5c9a', '1234', '王大力', 'f', '手冲', '13339999879', 'N', 'B');
INSERT INTO `user` VALUES ('9a72befb-2677-48a4-8e84-9ac4230f', '1234', '马赛克', 'f', '天天手冲', '19776549765', 'N', 'N');
INSERT INTO `user` VALUES ('admin', '1234', 'admin', 'f', '管事', '18888888888', 'S', 'N');
INSERT INTO `user` VALUES ('d790bc5e-9998-4c0e-9243-3c007697', '1234', '王屁屁', 'f', '当众手冲', '19796959097', 'N', 'N');
INSERT INTO `user` VALUES ('f89c6f6e-7096-47aa-95da-cba984ffb160', '1234', '你妹', 'm', '反向手冲', '18798987654', 'N', 'N');

SET FOREIGN_KEY_CHECKS = 1;
