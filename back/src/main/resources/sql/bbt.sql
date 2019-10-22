/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : bbt

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 22/10/2019 15:17:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `admin_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '管理员id',
  `admin_email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '邮件',
  `password` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  PRIMARY KEY (`admin_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for attitude
-- ----------------------------
DROP TABLE IF EXISTS `attitude`;
CREATE TABLE `attitude`  (
  `attitude_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户态度id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `product_id` int(11) NOT NULL COMMENT '产品id',
  `product_type` int(11) NOT NULL COMMENT '产品类型',
  `attitude` int(11) NOT NULL COMMENT '机会id',
  PRIMARY KEY (`attitude_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for collect
-- ----------------------------
DROP TABLE IF EXISTS `collect`;
CREATE TABLE `collect`  (
  `collect_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户收藏id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `product_id` int(11) NOT NULL COMMENT '产品id',
  `product_type` int(11) NOT NULL COMMENT '产品类型',
  PRIMARY KEY (`collect_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `product_id` int(11) NOT NULL COMMENT '产品id',
  `product_type` int(11) NOT NULL COMMENT '产品类型',
  `product_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '产品名称',
  `content` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评论',
  `like_num` int(11) NOT NULL COMMENT '点赞数',
  `date` datetime(0) NOT NULL COMMENT '评论时间',
  PRIMARY KEY (`comment_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for comment_like
-- ----------------------------
DROP TABLE IF EXISTS `comment_like`;
CREATE TABLE `comment_like`  (
  `comment_like_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '评论点赞id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `comment_id` int(11) NOT NULL COMMENT '评论id',
  `like_time` datetime(0) NOT NULL COMMENT '点赞时间',
  PRIMARY KEY (`comment_like_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for computer
-- ----------------------------
DROP TABLE IF EXISTS `computer`;
CREATE TABLE `computer`  (
  `product_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '产品id',
  `computer_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '电脑名字',
  `vendor` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '厂商',
  `after_sales_ability` int(11) DEFAULT NULL COMMENT '售后能力',
  `premium_ability` int(11) DEFAULT NULL COMMENT '溢价能力',
  `cpu` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'CPU',
  `gpu` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'GPU',
  `running_memory` int(11) DEFAULT NULL COMMENT '运行内存',
  `storage_memory_type` int(11) DEFAULT NULL COMMENT '存储内存类型',
  `storage_memory_size` int(11) DEFAULT NULL COMMENT '存储内存大小',
  `body_weight` int(11) DEFAULT NULL COMMENT '机身重量',
  `life_time` int(11) DEFAULT NULL COMMENT '续航时间',
  `keyboard_type` int(11) DEFAULT NULL COMMENT '键盘类型',
  `unlock_mode` int(11) DEFAULT NULL COMMENT '解锁模式',
  `camera` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '摄像头',
  `sound_system` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '音效系统',
  `heat_dissipation` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '散热',
  `appearance1` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '外观1',
  `appearance2` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '外观2',
  `appearance3` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '外观3',
  `screen` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '屏幕',
  `interface_num` int(11) DEFAULT NULL COMMENT '接口数量',
  `product_category` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '产品类别',
  `library` int(11) DEFAULT NULL COMMENT '所属库',
  `product_analysis` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品分析',
  PRIMARY KEY (`product_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for crawler
-- ----------------------------
DROP TABLE IF EXISTS `crawler`;
CREATE TABLE `crawler`  (
  `parameter_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '参数id',
  `crawl_address` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '爬虫地址',
  `time_interval` int(11) NOT NULL COMMENT '时间间隔',
  `script_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '爬虫名称',
  `remarks` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '备注参数',
  PRIMARY KEY (`parameter_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for phone
-- ----------------------------
DROP TABLE IF EXISTS `phone`;
CREATE TABLE `phone`  (
  `product_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '产品id',
  `phone_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '手机名字',
  `vendor` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '厂商',
  `after_sales_ability` int(11) DEFAULT NULL COMMENT '售后能力',
  `premium_ability` int(11) DEFAULT NULL COMMENT '溢价能力',
  `front_camera` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '前置摄像头',
  `rear_camera` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '后置摄像头',
  `camera_capability` int(11) DEFAULT NULL COMMENT '摄像能力',
  `screen_surface` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '屏幕曲面',
  `fingerprint_module` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '指纹模块',
  `body_color` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '机身色彩',
  `picture1` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '图片1',
  `picture2` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '图片2',
  `picture3` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '图片3',
  `body_weight` int(11) DEFAULT NULL COMMENT '机身重量',
  `body_material` int(11) DEFAULT NULL COMMENT '机身材质',
  `resolution` int(11) DEFAULT NULL COMMENT '分辨率',
  `screen_manufacturer` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '屏幕厂家',
  `battery_capacity` int(11) DEFAULT NULL COMMENT '电池容量',
  `charging_speed` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '充电速度',
  `actual_performance` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '实际使用表现',
  `headphone_plug` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '耳机孔',
  `waterproof_level` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '防水级别',
  `sales_volume` int(11) DEFAULT NULL COMMENT '销量',
  `chip` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '芯片',
  `speaker` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '扬声器',
  `product_category` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '产品类别',
  `library` int(11) DEFAULT NULL COMMENT '所属库',
  `product_analysis` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品分析',
  PRIMARY KEY (`product_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for product_like
-- ----------------------------
DROP TABLE IF EXISTS `product_like`;
CREATE TABLE `product_like`  (
  `product_like_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '产品点赞id',
  `product_id` int(11) NOT NULL COMMENT '产品id',
  `product_type` int(11) NOT NULL COMMENT '产品类型',
  `like_num` int(11) NOT NULL COMMENT '浏览次数',
  PRIMARY KEY (`product_like_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for record
-- ----------------------------
DROP TABLE IF EXISTS `record`;
CREATE TABLE `record`  (
  `record_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '记录id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `product_id` int(11) NOT NULL COMMENT '产品id',
  `product_type` int(11) NOT NULL COMMENT '产品类型',
  `product_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '产品名称',
  `product_picture` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '产品缩略图',
  `browse_time` datetime(0) NOT NULL COMMENT '浏览时间',
  `browse_num` int(11) NOT NULL COMMENT '浏览次数',
  PRIMARY KEY (`record_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `nick_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户昵称',
  `password` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '电话',
  `user_email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '邮件',
  `age` int(11) NOT NULL COMMENT '年龄',
  `profession` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '职业',
  `tag1` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标签1',
  `tag2` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标签2',
  `tag3` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标签3',
  `used` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '使用过的产品',
  `is_ban` int(11) NOT NULL COMMENT '是否封禁',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_like
-- ----------------------------
DROP TABLE IF EXISTS `user_like`;
CREATE TABLE `user_like`  (
  `user_like_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户点赞id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `product_id` int(11) NOT NULL COMMENT '产品id',
  `product_type` int(11) NOT NULL COMMENT '产品类型',
  `like_time` datetime(0) NOT NULL COMMENT '点赞时间',
  PRIMARY KEY (`user_like_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
