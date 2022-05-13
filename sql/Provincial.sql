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

Date: 2022-05-13 21:57:55
*/


-- ----------------------------
-- Table structure for Provincial
-- ----------------------------
DROP TABLE [dbo].[Provincial]
GO
CREATE TABLE [dbo].[Provincial] (
[pid] int NOT NULL ,
[Provincial] varchar(50) NULL 
)


GO

-- ----------------------------
-- Indexes structure for table Provincial
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table Provincial
-- ----------------------------
ALTER TABLE [dbo].[Provincial] ADD PRIMARY KEY ([pid])
GO
