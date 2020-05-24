/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50018
Source Host           : localhost:3306
Source Database       : library

Target Server Type    : MYSQL
Target Server Version : 50018
File Encoding         : 65001

Date: 2020-05-24 12:26:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `book_category`
-- ----------------------------
DROP TABLE IF EXISTS `book_category`;
CREATE TABLE `book_category` (
  `category_id` int(11) NOT NULL default '0',
  `category_name` varchar(255) default NULL,
  PRIMARY KEY  (`category_id`),
  KEY `category_name` (`category_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of book_category
-- ----------------------------
INSERT INTO `book_category` VALUES ('2', '编程书');
INSERT INTO `book_category` VALUES ('1', '课外书');

-- ----------------------------
-- Table structure for `t_book`
-- ----------------------------
DROP TABLE IF EXISTS `t_book`;
CREATE TABLE `t_book` (
  `book_id` int(11) NOT NULL,
  `book_name` varchar(255) NOT NULL,
  `book_author` varchar(255) NOT NULL,
  `book_num` int(11) NOT NULL,
  `book_category` int(11) NOT NULL,
  `book_state` int(11) default NULL,
  PRIMARY KEY  (`book_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_book
-- ----------------------------
INSERT INTO `t_book` VALUES ('1', 'java', 'sdf', '1', '2', '0');

-- ----------------------------
-- Table structure for `t_borrowing_info`
-- ----------------------------
DROP TABLE IF EXISTS `t_borrowing_info`;
CREATE TABLE `t_borrowing_info` (
  `borrowing_info_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `book_id` int(11) NOT NULL,
  `borrowing_state` int(11) default NULL,
  `borrowing_time` datetime default NULL,
  `giveback_time` datetime default NULL,
  PRIMARY KEY  (`borrowing_info_id`),
  KEY `book_id` (`book_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_borrowing_info
-- ----------------------------
INSERT INTO `t_borrowing_info` VALUES ('3', '3', '1', '0', '2020-04-03 15:05:43', '2020-04-03 12:00:00');
INSERT INTO `t_borrowing_info` VALUES ('4', '3', '1', '0', '2020-04-01 15:46:50', null);
INSERT INTO `t_borrowing_info` VALUES ('5', '3', '1', '0', '2020-04-09 19:11:45', '0000-00-00 00:00:00');
INSERT INTO `t_borrowing_info` VALUES ('6', '3', '1', '0', '2020-04-09 19:12:41', '2020-04-09 12:00:00');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL,
  `user_name` varchar(200) default NULL,
  `password` varchar(200) default NULL,
  `age` int(11) default NULL,
  `gender` varchar(20) default NULL,
  `phone_no` varchar(255) default NULL,
  `address` varchar(255) default NULL,
  `user_state` int(11) default NULL,
  `identity` int(11) NOT NULL,
  PRIMARY KEY  (`user_id`),
  UNIQUE KEY `unique` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'chen', '123', '20', '男', '1234', '福建', '0', '0');
INSERT INTO `user` VALUES ('2', '莉亚', '111', '23', '男', '12345', '英国', '0', '1');
INSERT INTO `user` VALUES ('3', 'noel', '123', '30', '男', '3255', '英国', '1', '1');
INSERT INTO `user` VALUES ('4', '、b', '3', '4', '3', '3', '3+', '3', '0');
INSERT INTO `user` VALUES ('5', 'abc', '2', '3', '4', '2', '3+', '4', '0');
