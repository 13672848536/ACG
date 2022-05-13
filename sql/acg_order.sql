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

Date: 2022-05-13 21:56:31
*/


-- ----------------------------
-- Table structure for acg_order
-- ----------------------------
DROP TABLE [dbo].[acg_order]
GO
CREATE TABLE [dbo].[acg_order] (
[id] bigint NOT NULL ,
[userid] bigint NULL ,
[total] int NULL ,
[createtime] datetime NULL ,
[productid] bigint NULL ,
[state] varchar(22) NULL ,
[type] varchar(50) NULL ,
[size] varchar(50) NULL ,
[month] int NULL ,
[style] varchar(100) NULL 
)


GO

-- ----------------------------
-- Records of acg_order
-- ----------------------------

-- ----------------------------
-- Indexes structure for table acg_order
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table acg_order
-- ----------------------------
ALTER TABLE [dbo].[acg_order] ADD PRIMARY KEY ([id])
GO
