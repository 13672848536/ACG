/*
Navicat SQL Server Data Transfer

Source Server         : sqls
Source Server Version : 105000
Source Host           : localhost:1433
Source Database       : ACG
Source Schema         : dbo

Target Server Type    : SQL Server
Target Server Version : 105000
File Encoding         : 65001

Date: 2022-05-13 21:55:48
*/


-- ----------------------------
-- Table structure for acg_browse 
-- ----------------------------
DROP TABLE [dbo].[acg_browse ]
GO
CREATE TABLE [dbo].[acg_browse ] (
[userid] bigint NOT NULL ,
[productid] bigint NULL ,
[createtime] datetime NULL ,
[age] int NULL ,
[type] varchar(100) NULL 
)


GO

-- ----------------------------
-- Records of acg_browse 
-- ----------------------------
