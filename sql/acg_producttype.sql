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

Date: 2022-05-13 21:56:59
*/


-- ----------------------------
-- Table structure for acg_producttype
-- ----------------------------
DROP TABLE [dbo].[acg_producttype]
GO
CREATE TABLE [dbo].[acg_producttype] (
[id] bigint NOT NULL ,
[type] varchar(50) NULL ,
[size] varchar(50) NULL ,
[stock] int NOT NULL 
)


GO

-- ----------------------------
-- Records of acg_producttype
-- ----------------------------
