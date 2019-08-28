/*
Navicat MySQL Data Transfer

Source Server         : myDB
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : booksystem

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2019-08-28 12:01:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '管理员id',
  `name` varchar(10) DEFAULT NULL COMMENT '管理员姓名',
  `password` varchar(16) DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=33437 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('33434', '周五', '444xxx');
INSERT INTO `admin` VALUES ('33435', 'lkg', 'nb');
INSERT INTO `admin` VALUES ('33436', 'admin', 'admin');

-- ----------------------------
-- Table structure for `book`
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `isbn` varchar(20) NOT NULL COMMENT '书号',
  `bookName` varchar(20) DEFAULT NULL COMMENT '书名',
  `bookType` varchar(20) DEFAULT NULL COMMENT '类别',
  `author` varchar(20) DEFAULT NULL COMMENT '作者',
  `publisher` varchar(50) DEFAULT NULL COMMENT '出版社',
  `instoreTime` date DEFAULT '2019-08-01' COMMENT '入库时间',
  `isRent` tinyint(1) DEFAULT NULL COMMENT '是否借出',
  `count` int(10) DEFAULT NULL COMMENT '库存',
  PRIMARY KEY (`isbn`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('1234', 'Java核心思想', 'java', 'james', '北京', '2019-08-09', '1', '2');
INSERT INTO `book` VALUES ('20', '32', '3333', '231', '322', '2018-03-01', '1', '354');
INSERT INTO `book` VALUES ('978-A', '青年文摘', '杂志类1', '新青年', '光明日报出版社', '2019-09-01', '0', '30');
INSERT INTO `book` VALUES ('987-1001', '安徒生童话', '童话类', '汉斯·克里斯汀·安徒生 ', '英国皇家出版社', '2019-05-01', '0', '80');
INSERT INTO `book` VALUES ('987-1002', '水浒传', '小说类', '施耐庵', '中国人民出版社', '2019-08-06', '1', '500');
INSERT INTO `book` VALUES ('987-123', '骆驼祥子', '小说类', '老舍', '清华大学出版社', '2019-08-05', '1', '101');
INSERT INTO `book` VALUES ('ABC', '动物与自然', '科学类', '达尔文', '北京大学出版社', '2011-05-06', '1', '10');

-- ----------------------------
-- Table structure for `reader`
-- ----------------------------
DROP TABLE IF EXISTS `reader`;
CREATE TABLE `reader` (
  `bookCard` bigint(12) NOT NULL COMMENT '借书证号',
  `name` varchar(20) DEFAULT NULL COMMENT '姓名',
  `sex` varchar(4) DEFAULT NULL COMMENT '性别',
  `major` varchar(20) DEFAULT NULL COMMENT '专业',
  `department` varchar(20) DEFAULT NULL COMMENT '院系',
  PRIMARY KEY (`bookCard`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of reader
-- ----------------------------
INSERT INTO `reader` VALUES ('20096689', '刘亦菲', '女', '影视传媒', '电影学院');
INSERT INTO `reader` VALUES ('20173234', '韩信', '男', '计算机科学与技术', '男');
INSERT INTO `reader` VALUES ('20171004110', '不知火舞', '女', '软件工程', '女');
INSERT INTO `reader` VALUES ('201620513223', '阿凡达', '男', '人文科学研究', '文学院');

-- ----------------------------
-- Table structure for `rentlog`
-- ----------------------------
DROP TABLE IF EXISTS `rentlog`;
CREATE TABLE `rentlog` (
  `bookCard` bigint(12) NOT NULL COMMENT '借书证',
  `isbn` varchar(20) NOT NULL COMMENT '书号',
  `rentDate` date DEFAULT '2019-08-01' COMMENT '借书日期',
  `returnDate` date DEFAULT '2019-08-01' COMMENT '还书日期',
  PRIMARY KEY (`bookCard`,`isbn`),
  KEY `fk_isbn` (`isbn`),
  CONSTRAINT `fk_bookCard` FOREIGN KEY (`bookCard`) REFERENCES `reader` (`bookCard`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_isbn` FOREIGN KEY (`isbn`) REFERENCES `book` (`isbn`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of rentlog
-- ----------------------------
INSERT INTO `rentlog` VALUES ('20096689', '987-1001', '2019-08-01', '2019-08-07');
INSERT INTO `rentlog` VALUES ('20096689', '987-1002', '2019-08-01', '2019-08-07');
INSERT INTO `rentlog` VALUES ('20173234', '987-1001', '2019-05-05', '2019-06-06');
