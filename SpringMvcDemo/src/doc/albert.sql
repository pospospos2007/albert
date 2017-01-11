/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50529
Source Host           : localhost:3306
Source Database       : albert

Target Server Type    : MYSQL
Target Server Version : 50529
File Encoding         : 65001

Date: 2017-01-11 18:29:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for airticle
-- ----------------------------
DROP TABLE IF EXISTS `airticle`;
CREATE TABLE `airticle` (
  `airticle_id` int(10) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  `content` mediumtext,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `insert_time` datetime DEFAULT NULL,
  PRIMARY KEY (`airticle_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for chat_record
-- ----------------------------
DROP TABLE IF EXISTS `chat_record`;
CREATE TABLE `chat_record` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `sender` varchar(1000) DEFAULT NULL,
  `receiver` varchar(1000) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `message` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for hdwan_res
-- ----------------------------
DROP TABLE IF EXISTS `hdwan_res`;
CREATE TABLE `hdwan_res` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `torrent` varchar(255) DEFAULT NULL,
  `desc` longtext,
  `metadata` longtext,
  `img` varchar(1000) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  `review_num` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_face
-- ----------------------------
DROP TABLE IF EXISTS `t_face`;
CREATE TABLE `t_face` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(100) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_face_attribute
-- ----------------------------
DROP TABLE IF EXISTS `t_face_attribute`;
CREATE TABLE `t_face_attribute` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `gender` varchar(10) DEFAULT NULL,
  `glass` varchar(10) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `race` varchar(20) DEFAULT NULL,
  `face_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_file_exchange
-- ----------------------------
DROP TABLE IF EXISTS `t_file_exchange`;
CREATE TABLE `t_file_exchange` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `new_url` varchar(1000) DEFAULT NULL,
  `old_url` varchar(1000) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_files
-- ----------------------------
DROP TABLE IF EXISTS `t_files`;
CREATE TABLE `t_files` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(100) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `title` varchar(50) DEFAULT NULL,
  `discription` varchar(500) DEFAULT NULL,
  `add_time` datetime NOT NULL,
  `file_format` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_images
-- ----------------------------
DROP TABLE IF EXISTS `t_images`;
CREATE TABLE `t_images` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `image_format` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_message
-- ----------------------------
DROP TABLE IF EXISTS `t_message`;
CREATE TABLE `t_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `message` mediumtext,
  `theme_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `add_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25545 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_theme
-- ----------------------------
DROP TABLE IF EXISTS `t_theme`;
CREATE TABLE `t_theme` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `theme` varchar(100) NOT NULL,
  `content` mediumtext,
  `user_id` int(11) NOT NULL,
  `add_time` datetime NOT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `review_num` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=62845 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `ip` varchar(1000) DEFAULT NULL COMMENT '注册ip',
  `register_time` datetime DEFAULT NULL COMMENT '注册时间',
  `email` varchar(1000) DEFAULT NULL COMMENT '邮箱',
  `avatar` varchar(1000) DEFAULT NULL COMMENT '头像',
  `auto_login_code` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10678 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_zhihu
-- ----------------------------
DROP TABLE IF EXISTS `t_zhihu`;
CREATE TABLE `t_zhihu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  `content` mediumtext,
  `add_time` datetime DEFAULT NULL,
  `images` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `css` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `js` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `review_num` int(11) DEFAULT '0',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9138908 DEFAULT CHARSET=utf8mb4;
