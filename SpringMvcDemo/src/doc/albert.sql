/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50529
Source Host           : localhost:3306
Source Database       : albert

Target Server Type    : MYSQL
Target Server Version : 50529
File Encoding         : 65001

Date: 2017-01-20 11:24:13
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
) ENGINE=InnoDB AUTO_INCREMENT=25547 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=9141093 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for twitter_media
-- ----------------------------
DROP TABLE IF EXISTS `twitter_media`;
CREATE TABLE `twitter_media` (
  `id` bigint(20) NOT NULL,
  `media_url` varchar(255) DEFAULT NULL,
  `post_id` bigint(20) NOT NULL,
  `video_info_url` varchar(255) DEFAULT NULL COMMENT '如果没有说明只有图片，一张图片对应一个mp4',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for twitter_post
-- ----------------------------
DROP TABLE IF EXISTS `twitter_post`;
CREATE TABLE `twitter_post` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `text` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `create_at` datetime DEFAULT NULL,
  `post_type` int(11) NOT NULL DEFAULT '0' COMMENT '0:我的时间线的帖子;1:搜索结果的帖子；如果都是，保留前者',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=822017049136394241 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for twitter_post_search
-- ----------------------------
DROP TABLE IF EXISTS `twitter_post_search`;
CREATE TABLE `twitter_post_search` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `search_id` bigint(20) NOT NULL,
  `post_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for twitter_search_history
-- ----------------------------
DROP TABLE IF EXISTS `twitter_search_history`;
CREATE TABLE `twitter_search_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `search_date` date NOT NULL COMMENT '根据日期决定要不要重新调用推特接口来更新结果集',
  `search_key` varchar(255) NOT NULL COMMENT '搜索关键字',
  `search_type` int(11) NOT NULL DEFAULT '0' COMMENT '0:按照用户名搜索；1：按照内容搜索',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for twitter_user
-- ----------------------------
DROP TABLE IF EXISTS `twitter_user`;
CREATE TABLE `twitter_user` (
  `id` bigint(20) NOT NULL,
  `screen_name` varchar(255) NOT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for weibo_status
-- ----------------------------
DROP TABLE IF EXISTS `weibo_status`;
CREATE TABLE `weibo_status` (
  `id` bigint(20) NOT NULL,
  `annotations` longtext,
  `bmiddlePic` varchar(255) DEFAULT NULL,
  `commentsCount` int(11) DEFAULT NULL,
  `createdAt` datetime DEFAULT NULL,
  `favorited` bit(1) DEFAULT NULL,
  `geo` varchar(255) DEFAULT NULL,
  `idstr` varchar(255) DEFAULT NULL,
  `inReplyToScreenName` varchar(255) DEFAULT NULL,
  `inReplyToStatusId` varchar(255) DEFAULT NULL,
  `inReplyToUserId` varchar(255) DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `mid` varchar(255) DEFAULT NULL,
  `mlevel` int(11) DEFAULT NULL,
  `originalPic` varchar(255) DEFAULT NULL,
  `repostsCount` int(11) DEFAULT NULL,
  `source_name` varchar(255) DEFAULT NULL,
  `source_relationShip` varchar(255) DEFAULT NULL,
  `source_url` varchar(255) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `thumbnailPic` varchar(255) DEFAULT NULL,
  `truncated` bit(1) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_4ujpud87xki9xppbs0ab3w52m` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for weibo_user
-- ----------------------------
DROP TABLE IF EXISTS `weibo_user`;
CREATE TABLE `weibo_user` (
  `id` bigint(20) NOT NULL,
  `allowAllActMsg` bit(1) DEFAULT NULL,
  `allowAllComment` bit(1) DEFAULT NULL,
  `avatarLarge` varchar(255) DEFAULT NULL,
  `biFollowersCount` int(11) DEFAULT NULL,
  `city` int(11) DEFAULT NULL,
  `createdAt` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `domain` varchar(255) DEFAULT NULL,
  `favouritesCount` int(11) DEFAULT NULL,
  `followMe` bit(1) DEFAULT NULL,
  `followersCount` int(11) DEFAULT NULL,
  `following` bit(1) DEFAULT NULL,
  `friendsCount` int(11) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `idstr` varchar(255) DEFAULT NULL,
  `lang` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `onlineStatus` int(11) DEFAULT NULL,
  `profileImageUrl` varchar(255) DEFAULT NULL,
  `province` int(11) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `screenName` varchar(255) DEFAULT NULL,
  `statusesCount` int(11) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `verified` bit(1) DEFAULT NULL,
  `verifiedReason` varchar(255) DEFAULT NULL,
  `verifiedType` int(11) DEFAULT NULL,
  `weihao` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
