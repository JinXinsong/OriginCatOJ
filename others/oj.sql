/*
 Navicat MySQL Data Transfer

 Source Server         : test
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : oj

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 25/04/2020 05:37:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for JudgeResult
-- ----------------------------
DROP TABLE IF EXISTS `JudgeResult`;
CREATE TABLE `JudgeResult` (
  `submitID` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `status` int DEFAULT NULL,
  `timeUsed` int DEFAULT NULL,
  `memoryUsed` int DEFAULT NULL,
  `errorMessage` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `userMail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `questionNum` int DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`submitID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of JudgeResult
-- ----------------------------
BEGIN;
INSERT INTO `JudgeResult` VALUES ('2020041614512038011819', 0, 1, 8476, NULL, 'truealfajin@gmail.com', 1000, '20200416145120380');
INSERT INTO `JudgeResult` VALUES ('2020041615002316247807', 8, 0, 0, NULL, 'truealfajin@gmail.com', 11, '20200416150023162');
INSERT INTO `JudgeResult` VALUES ('2020041615010693885265', 0, 1, 8416, NULL, 'truealfajin@gmail.com', 100, '20200416150106939');
INSERT INTO `JudgeResult` VALUES ('2020041615023617218685', 1, 0, 0, NULL, 'truealfajin@gmail.com', 100, '20200416150236172');
INSERT INTO `JudgeResult` VALUES ('2020041615235156271884', 8, 0, 0, NULL, 'truealfajin@gmail.com', 1, '20200416152351562');
INSERT INTO `JudgeResult` VALUES ('2020041615241995940782', 8, 0, 0, NULL, 'truealfajin@gmail.com', 100, '20200416152419960');
INSERT INTO `JudgeResult` VALUES ('2020041615302132358592', 8, 0, 0, NULL, 'truealfajin@gmail.com', 11, '20200416153021323');
INSERT INTO `JudgeResult` VALUES ('2020041615352561615487', 8, 0, 0, NULL, 'truealfajin@gmail.com', 11, '20200416153525616');
INSERT INTO `JudgeResult` VALUES ('2020041615412789474572', 8, 0, 0, NULL, 'truealfajin@gmail.com', 100, '20200416154127895');
INSERT INTO `JudgeResult` VALUES ('202004161541305575542', 8, 0, 0, NULL, 'truealfajin@gmail.com', 100, '2020041615413055');
INSERT INTO `JudgeResult` VALUES ('2020041615543945981439', 7, 0, 0, '/home/jin/Documents/judgeDemo/tmp/2020041615543945981439/main.cpp:1:4: error: expected ‘]’ before ‘{’ token [[${question.questionID}]]    ^    ]/home/jin/Documents/judgeDemo/tmp/2020041615543945981439/main.cpp:1:25: error: expected unqualified-id before ‘]’ token [[${question.questionID}]]                         ^', 'truealfajin@gmail.com', 11, '20200416155439459');
INSERT INTO `JudgeResult` VALUES ('202004161632028460805', 1, 0, 0, NULL, 'truealfajin@gmail.com', 100, '2020041616320285');
INSERT INTO `JudgeResult` VALUES ('2020041617443840887698', 1, 0, 0, NULL, 'truealfajin@gmail.com', 11, '20200416174438409');
INSERT INTO `JudgeResult` VALUES ('2020041618295399080743', 0, 1, 8548, NULL, 'truealfajin@gmail.com', 11, '20200416182953991');
COMMIT;

-- ----------------------------
-- Table structure for OJUser
-- ----------------------------
DROP TABLE IF EXISTS `OJUser`;
CREATE TABLE `OJUser` (
  `userID` int NOT NULL AUTO_INCREMENT,
  `userMail` varchar(255) DEFAULT NULL,
  `userPassWd` varchar(255) DEFAULT NULL,
  `userPhone` varchar(255) DEFAULT NULL,
  `createTime` date DEFAULT NULL,
  `lastEditTime` date DEFAULT NULL,
  `userKind` int DEFAULT NULL,
  `userName` varchar(255) DEFAULT NULL,
  `userStatus` int DEFAULT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of OJUser
-- ----------------------------
BEGIN;
INSERT INTO `OJUser` VALUES (1, 'jinxinsong@outlook.com', 'wangyue341124', '13956282315', NULL, NULL, 3, 'jinxinsong', 1);
INSERT INTO `OJUser` VALUES (2, 'truealfajin@gmail.com', '1650340007', NULL, '2020-04-06', '2020-04-06', 1, 'alfa jin', 1);
COMMIT;

-- ----------------------------
-- Table structure for Question
-- ----------------------------
DROP TABLE IF EXISTS `Question`;
CREATE TABLE `Question` (
  `questionID` varchar(255) NOT NULL,
  `questionTitle` varchar(255) DEFAULT NULL,
  `questionInput` int DEFAULT NULL,
  `questionAccept` int DEFAULT NULL,
  `questionStatus` int DEFAULT NULL,
  `questionTimeLimit` int DEFAULT NULL,
  `questionMemoryLimit` int DEFAULT NULL,
  `questionContent` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `questionInputSimple` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `questionOutputSimple` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `questionPrompt` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `questionNum` int unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`questionNum`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of Question
-- ----------------------------
BEGIN;
INSERT INTO `Question` VALUES ('Q202004041009121265719', 'A+B问题', 0, 0, 1, 1000, 512000, '输入整数A和B\n输出数字A，B之和', '12 21', '33', 'NULL', 1);
INSERT INTO `Question` VALUES ('Q202004041011111163452', 'A+B问题1', 0, 0, 1, 1000, 512000, '输入整数A和B，输出他们的和', '12 21', '33', 'NULL', 2);
INSERT INTO `Question` VALUES ('Q4052120200318', '123213', 0, 0, 3, 123123123, 2132131, '123123123', '123123123123', '123123213123123', 'NULL', 3);
INSERT INTO `Question` VALUES ('Q5435420200404', '测试2', 0, 0, 3, 1000, 512000, '111111111111111', '11111', '11111', 'NULL', 4);
INSERT INTO `Question` VALUES ('Q6132220200318', 'wqe', 0, 0, 3, 12312, 213123, 'ewqrewqrq\neqwrewqrqwer\newqrqewrqewrq\nqwer\"ewrqewrewq\"\nwqerqwerewq', 'ewqrqwer', 'qwerqwerqewr', 'NULL', 5);
INSERT INTO `Question` VALUES ('Q6143820200318', 'dsafdsaf', 0, 0, 3, 10000, 100000000, 'asdfasdfasdf', 'sadfasdfasdf', 'sadfasfd', 'NULL', 6);
INSERT INTO `Question` VALUES ('Q6450020200318', '456576', 0, 0, 3, 100000, 10000000, 'xffSdsadfsdaf\ndsfadsfasdfadsfdsaf\nsdafadsfasdfads\nfadsf\nadsf\nasdf\nadsf\ndsafdsfasfdsa\nfasd\nfadsfasdfdsafdsafadsfadsfdsaf', 'dsaafasdfdsafadsfdsfadsfdsaf\ndf\nasdfadsfdsa', 'fadfadsf\nDSfa\ndsaf\nadsf\nadsf\nadsfdsafadsfasfdsf', 'NULL', 7);
INSERT INTO `Question` VALUES ('Q7739820200404', '测试', 0, 0, 3, 1000, 512000, '测试', '12', '12', '无', 8);
INSERT INTO `Question` VALUES ('Q9707720200318', '修改测试', 0, 0, 3, 123213, 12312312, '123', '1232131', '123213123123', 'NULL1231231231', 9);
INSERT INTO `Question` VALUES ('Q202004041327291445080', 'A+B问题2', 0, 0, 1, 1000, 512000, '输入整数A和B\n输出他们的和', '12 21', '33', 'NULL', 10);
INSERT INTO `Question` VALUES ('Q2020040413283739779055', 'A+B问题3', 0, 0, 1, 1000, 512000, '输入整数A和B\n输出两数和', '12 21', '33', 'NULL', 11);
INSERT INTO `Question` VALUES ('Q2020040513025428520778', 'A+B问题4', 0, 0, 2, 1000, 512000, '输入整数A和B\n输出AB之和', '12 21', '33', 'NULL', 12);
COMMIT;

-- ----------------------------
-- Table structure for Student
-- ----------------------------
DROP TABLE IF EXISTS `Student`;
CREATE TABLE `Student` (
  `studentMail` varchar(255) DEFAULT NULL,
  `studentClassID` int DEFAULT NULL,
  `studentName` varchar(255) DEFAULT NULL,
  `studentID` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of Student
-- ----------------------------
BEGIN;
INSERT INTO `Student` VALUES ('truealfajin@gmail.com', 1, 'alfa jin', '1650340007');
COMMIT;

-- ----------------------------
-- Table structure for StudentClass
-- ----------------------------
DROP TABLE IF EXISTS `StudentClass`;
CREATE TABLE `StudentClass` (
  `classID` int NOT NULL AUTO_INCREMENT,
  `className` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`classID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of StudentClass
-- ----------------------------
BEGIN;
INSERT INTO `StudentClass` VALUES (1, '测试1');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
