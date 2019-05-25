/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : graduation

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 25/05/2019 23:08:36
*/

CREATE DATABASE IF NOT EXISTS graduation DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `aid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`aid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for common_jar
-- ----------------------------
DROP TABLE IF EXISTS `common_jar`;
CREATE TABLE `common_jar`  (
  `cjid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'jar名称',
  `task_unit_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'jar类型(handle, output)',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'handle(SQL, python...) output(http, kafka...)',
  `main_class` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'jar执行的入口类',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'jar路径',
  `jar_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'flink上传文件id',
  `create_time` timestamp(0) NOT NULL,
  PRIMARY KEY (`cjid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for device
-- ----------------------------
DROP TABLE IF EXISTS `device`;
CREATE TABLE `device`  (
  `did` int(11) NOT NULL AUTO_INCREMENT COMMENT '设备id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '设备名称',
  `device_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备key',
  `device_secret` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '设备secret',
  `status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备状态',
  `create_time` timestamp(0) NOT NULL COMMENT '设备创建时间',
  `active_time` timestamp(0) NULL DEFAULT NULL COMMENT '设备激活时间',
  `last_time` timestamp(0) NULL DEFAULT NULL COMMENT '设备最后一次上线时间',
  `pid` int(11) NOT NULL COMMENT '设备属于的产品id',
  `uid` int(11) NOT NULL COMMENT '设备属于的用户id',
  PRIMARY KEY (`did`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for file
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file`  (
  `fid` int(11) NOT NULL AUTO_INCREMENT COMMENT '文件id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件名称',
  `main_class` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '入口类',
  `implement_class` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '实现类',
  `extensions` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件扩展名',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件路径',
  `jar_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'flink上传文件id',
  `upload_time` timestamp(0) NOT NULL COMMENT '上传时间',
  `uid` int(11) NOT NULL COMMENT '文件所属用户id',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `pid` int(11) NOT NULL AUTO_INCREMENT COMMENT '产品id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '产品名称',
  `product_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '产品key',
  `create_time` timestamp(0) NOT NULL COMMENT '产品创建时间',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品状态',
  `topic` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '产品对应的Topic',
  `uid` int(11) NOT NULL COMMENT '产品属于的uid',
  PRIMARY KEY (`pid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for task
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task`  (
  `tid` int(11) NOT NULL AUTO_INCREMENT COMMENT '任务id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '任务名字',
  `status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '任务状态',
  `create_time` timestamp(0) NOT NULL COMMENT '任务创建时间',
  `update_time` timestamp(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '任务更新时间',
  `uid` int(11) NOT NULL COMMENT '任务对应的用户id',
  PRIMARY KEY (`tid`) USING BTREE,
  UNIQUE INDEX `name`(`name`, `uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for task_send_sum
-- ----------------------------
DROP TABLE IF EXISTS `task_send_sum`;
CREATE TABLE `task_send_sum`  (
  `tssid` int(11) NOT NULL AUTO_INCREMENT,
  `count` int(11) NOT NULL,
  `time` timestamp(0) NOT NULL,
  `tid` int(11) NOT NULL,
  PRIMARY KEY (`tssid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 482 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for task_unit_connect
-- ----------------------------
DROP TABLE IF EXISTS `task_unit_connect`;
CREATE TABLE `task_unit_connect`  (
  `tucid` int(11) NOT NULL AUTO_INCREMENT COMMENT '任务单元连接id',
  `source_tuid` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '连接的起点tuid',
  `target_tuid` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '连接的终点tuid',
  `create_time` timestamp(0) NOT NULL COMMENT '连接的创建时间',
  `tid` int(11) NOT NULL COMMENT '连接所属的任务id',
  PRIMARY KEY (`tucid`) USING BTREE,
  UNIQUE INDEX `source_tuid`(`source_tuid`, `target_tuid`, `tid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 82 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for task_unit_handle
-- ----------------------------
DROP TABLE IF EXISTS `task_unit_handle`;
CREATE TABLE `task_unit_handle`  (
  `tuid` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '任务单元id,uuid',
  `type` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'jar, lua...',
  `fid` int(11) NULL DEFAULT 0 COMMENT 'type为java时, 指向file表',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `left_dis` int(11) NOT NULL COMMENT '任务单元位置',
  `top_dis` int(11) NOT NULL COMMENT '任务单元位置',
  `status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务单元状态',
  `create_time` timestamp(0) NOT NULL COMMENT '任务单元创建时间',
  `job_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务单元提交到flink的job_id',
  `tid` int(11) NOT NULL COMMENT '任务单元所在的任务',
  PRIMARY KEY (`tuid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for task_unit_input
-- ----------------------------
DROP TABLE IF EXISTS `task_unit_input`;
CREATE TABLE `task_unit_input`  (
  `tuid` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '任务单元id,uuid',
  `pid` int(11) NULL DEFAULT 0 COMMENT '输入所选的产品',
  `left_dis` int(11) NULL DEFAULT NULL COMMENT '任务单元位置',
  `top_dis` int(11) NULL DEFAULT NULL COMMENT '任务单元位置',
  `create_time` timestamp(0) NOT NULL COMMENT '任务单元创建时间',
  `tid` int(11) NOT NULL COMMENT '任务单元所在的任务',
  PRIMARY KEY (`tuid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for task_unit_output
-- ----------------------------
DROP TABLE IF EXISTS `task_unit_output`;
CREATE TABLE `task_unit_output`  (
  `tuid` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '任务单元id,uuid',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '目标的类型(kafka,http)',
  `ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '目标的ip',
  `port` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '目标端口',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '目标url',
  `left_dis` int(11) NOT NULL COMMENT '任务单元位置',
  `top_dis` int(11) NOT NULL COMMENT '任务单元位置',
  `status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务单元状态',
  `create_time` timestamp(0) NOT NULL COMMENT '任务单元创建时间',
  `job_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务单元提交到flink的job_id',
  `tid` int(11) NOT NULL COMMENT '任务单元所在的任务',
  PRIMARY KEY (`tuid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` timestamp(0) NOT NULL,
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
