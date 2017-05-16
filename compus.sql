/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50624
Source Host           : 127.0.0.1:3306
Source Database       : compus

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2017-05-16 11:29:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_address
-- ----------------------------
DROP TABLE IF EXISTS `tb_address`;
CREATE TABLE `tb_address` (
  `ADDRESS_ID` varchar(255) NOT NULL COMMENT '收货地址ID',
  `ADDRESS_CONTENT` varchar(255) DEFAULT NULL COMMENT '收货地址',
  `USER_ID` varchar(255) DEFAULT NULL COMMENT '用户ID',
  PRIMARY KEY (`ADDRESS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_address
-- ----------------------------

-- ----------------------------
-- Table structure for tb_clickamount
-- ----------------------------
DROP TABLE IF EXISTS `tb_clickamount`;
CREATE TABLE `tb_clickamount` (
  `AMOUNT_ID` varchar(255) NOT NULL COMMENT '点击量ID',
  `AMOUNT_NUM` varchar(255) DEFAULT NULL COMMENT '点击量描述',
  `LIST_ID` varchar(255) DEFAULT NULL COMMENT '目录ID',
  PRIMARY KEY (`AMOUNT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_clickamount
-- ----------------------------

-- ----------------------------
-- Table structure for tb_comment
-- ----------------------------
DROP TABLE IF EXISTS `tb_comment`;
CREATE TABLE `tb_comment` (
  `COMMENT_ID` varchar(255) NOT NULL COMMENT '评论ID',
  `COMMENT_REPLY_ID` varchar(255) DEFAULT NULL COMMENT '评论回复ID',
  `COMMENT_CONTENT` varchar(255) DEFAULT NULL COMMENT '评论内容',
  `COMMENT_TIME` varchar(255) DEFAULT NULL COMMENT '评论时间',
  `USER_ID` varchar(255) DEFAULT NULL COMMENT '用户ID',
  `ORDER_ID` varchar(255) DEFAULT NULL COMMENT '订单ID',
  PRIMARY KEY (`COMMENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_comment
-- ----------------------------

-- ----------------------------
-- Table structure for tb_file
-- ----------------------------
DROP TABLE IF EXISTS `tb_file`;
CREATE TABLE `tb_file` (
  `FILE_ID` varchar(255) NOT NULL COMMENT '文件ID',
  `FILE_URL` varchar(255) DEFAULT NULL COMMENT '文件路径',
  `FILE_NAME` varchar(255) DEFAULT NULL COMMENT '文件名',
  `FILE_DES` varchar(255) DEFAULT NULL COMMENT '文件描述',
  `FILE_TYPE` varchar(255) DEFAULT NULL COMMENT '文件类型：\r\n0-商品文件,\r\n1-用户头像',
  `GOODS_ID` varchar(255) DEFAULT NULL COMMENT '商品ID',
  PRIMARY KEY (`FILE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_file
-- ----------------------------

-- ----------------------------
-- Table structure for tb_goods
-- ----------------------------
DROP TABLE IF EXISTS `tb_goods`;
CREATE TABLE `tb_goods` (
  `GOODS_ID` varchar(255) NOT NULL COMMENT '商品ID',
  `GOODS_TITLE` varchar(255) DEFAULT NULL COMMENT '商品标题',
  `GOODS_DES` varchar(255) DEFAULT NULL COMMENT '商品描述',
  `GOODS_ORIGINAL` varchar(255) DEFAULT NULL COMMENT '商品原价',
  `GOODS_RESALE` varchar(255) DEFAULT NULL COMMENT '商品转卖价',
  `GOODS_CONDITION` varchar(255) DEFAULT NULL COMMENT '商品成色',
  `GOODS_CREATEDTIME` varchar(255) DEFAULT NULL COMMENT '创建时间',
  `GOODS_RECENTACCESS` varchar(255) DEFAULT NULL COMMENT '最近访问时间',
  `GOODS_VIEWCONTS` varchar(255) DEFAULT NULL COMMENT '访问量',
  `GOODS_DINGNUM` varchar(255) DEFAULT NULL COMMENT '顶数量',
  `GOODS_CAINUM` varchar(255) DEFAULT NULL COMMENT '踩数量',
  `GOODS_STATUS` varchar(255) DEFAULT NULL COMMENT '商品状态(0:未卖出，1：已卖出)',
  `LIST_ID` varchar(255) DEFAULT NULL COMMENT '目录ID',
  PRIMARY KEY (`GOODS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_goods
-- ----------------------------

-- ----------------------------
-- Table structure for tb_goods_card
-- ----------------------------
DROP TABLE IF EXISTS `tb_goods_card`;
CREATE TABLE `tb_goods_card` (
  `GOODS_CARD_ID` varchar(255) NOT NULL COMMENT 'ID',
  `GOODS_ID` varchar(255) DEFAULT NULL COMMENT '商品ID',
  `CARD_ID` varchar(255) DEFAULT NULL COMMENT '购物车ID',
  `GOODS_CARD_DES` varchar(255) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`GOODS_CARD_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_goods_card
-- ----------------------------

-- ----------------------------
-- Table structure for tb_goods_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_goods_user`;
CREATE TABLE `tb_goods_user` (
  `GOODS_USER_ID` varchar(255) NOT NULL COMMENT 'ID',
  `GOODS_ID` varchar(255) DEFAULT NULL COMMENT '商品ID',
  `USER_ID` varchar(255) DEFAULT NULL COMMENT '用户ID',
  `GOODS_USER_DES` varchar(255) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`GOODS_USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_goods_user
-- ----------------------------

-- ----------------------------
-- Table structure for tb_list
-- ----------------------------
DROP TABLE IF EXISTS `tb_list`;
CREATE TABLE `tb_list` (
  `LIST_ID` varchar(255) NOT NULL COMMENT '目录ID',
  `PARENT_ID` varchar(255) DEFAULT NULL COMMENT '父级ID',
  `IS_LAST` varchar(255) DEFAULT NULL COMMENT '是否是最后子级',
  `LIST_NAME` varchar(255) DEFAULT NULL COMMENT '目录名称',
  `LIST_DES` varchar(255) DEFAULT NULL COMMENT '目录表述',
  `TYPE_ID` varchar(255) DEFAULT NULL COMMENT '类型ID（对应tb_type表）',
  `CLICKCOUNT_ID` varchar(255) DEFAULT NULL COMMENT '目录点击量ID（对应TB_CLICKAMOUNT）',
  PRIMARY KEY (`LIST_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_list
-- ----------------------------

-- ----------------------------
-- Table structure for tb_message
-- ----------------------------
DROP TABLE IF EXISTS `tb_message`;
CREATE TABLE `tb_message` (
  `MESSAGE_ID` varchar(255) NOT NULL COMMENT 'ID',
  `MESSAGE_REPLY_ID` varchar(255) DEFAULT NULL COMMENT '留言回复ID(0: 表示没有回复)',
  `MESSAGE_CONTENT` varchar(255) DEFAULT NULL COMMENT '留言内容',
  `MESSAGE_TIME` varchar(255) DEFAULT NULL COMMENT '留言时间',
  `GOODS_ID` varchar(255) DEFAULT NULL COMMENT '商品ID',
  `BUYERSID` varchar(255) DEFAULT NULL COMMENT '买家ID',
  `SELLERID` varchar(255) DEFAULT NULL COMMENT '卖家ID',
  PRIMARY KEY (`MESSAGE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_message
-- ----------------------------

-- ----------------------------
-- Table structure for tb_order
-- ----------------------------
DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order` (
  `ORDER_ID` varchar(255) NOT NULL COMMENT '订单ID',
  `ORDER_TIME` varchar(255) DEFAULT NULL COMMENT '订单生成时间',
  `ORDER_STATU` varchar(255) DEFAULT NULL COMMENT '订单状态(0:未收货，1:已收货)',
  `USER_ID` varchar(255) DEFAULT NULL COMMENT '用户ID',
  `GOODS_ID` varchar(255) DEFAULT NULL COMMENT '商品ID',
  `ADDRESS_ID` varchar(255) DEFAULT NULL COMMENT '收货地址ID',
  PRIMARY KEY (`ORDER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_order
-- ----------------------------

-- ----------------------------
-- Table structure for tb_school
-- ----------------------------
DROP TABLE IF EXISTS `tb_school`;
CREATE TABLE `tb_school` (
  `SCHOOL_ID` varchar(255) NOT NULL COMMENT '学校ID',
  `SCHOOL_NAME` varchar(255) DEFAULT NULL COMMENT '学校名',
  `SCHOOL_ADDRESS` varchar(255) DEFAULT NULL COMMENT '学校地址',
  `SCHOOL_POSTCODE` varchar(255) DEFAULT NULL COMMENT '学校邮编',
  PRIMARY KEY (`SCHOOL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_school
-- ----------------------------

-- ----------------------------
-- Table structure for tb_shoppingcard
-- ----------------------------
DROP TABLE IF EXISTS `tb_shoppingcard`;
CREATE TABLE `tb_shoppingcard` (
  `CARD_ID` varchar(255) NOT NULL COMMENT '购物车ID',
  `USER_ID` varchar(255) DEFAULT NULL COMMENT '用户ID',
  `CARD_DES` varchar(255) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`CARD_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_shoppingcard
-- ----------------------------

-- ----------------------------
-- Table structure for tb_type
-- ----------------------------
DROP TABLE IF EXISTS `tb_type`;
CREATE TABLE `tb_type` (
  `TYPE_ID` varchar(255) NOT NULL COMMENT '类型ID',
  `TYPE_DES` varchar(255) DEFAULT NULL COMMENT '类型描述',
  PRIMARY KEY (`TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_type
-- ----------------------------

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `USER_ID` varchar(255) NOT NULL COMMENT '用户ID',
  `USER_ACCOUNT` varchar(255) DEFAULT NULL COMMENT '账户名',
  `USER_PWD` varchar(255) DEFAULT NULL COMMENT '密码',
  `USER_PICID` varchar(255) DEFAULT NULL COMMENT '头像文件ID',
  `USER_NAME` varchar(255) DEFAULT NULL COMMENT '姓名',
  `USER_PHONE` varchar(255) DEFAULT NULL COMMENT '联系电话',
  `USER_EMAIL` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `USER_SEX` varchar(255) DEFAULT NULL COMMENT '性别',
  `USER_AGE` varchar(255) DEFAULT NULL COMMENT '年龄',
  `USER_ADDRESS` varchar(255) DEFAULT NULL COMMENT '住址',
  `USER_SCHOOLNO` varchar(255) DEFAULT NULL COMMENT '学号',
  `SCHOOL_ID` varchar(255) DEFAULT NULL COMMENT '学校ID',
  `USER_STATUS` varchar(255) DEFAULT NULL COMMENT '用户状态(0:被冻结，1:正常)',
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
