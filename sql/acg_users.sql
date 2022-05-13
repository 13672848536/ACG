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

Date: 2022-05-13 21:57:38
*/


-- ----------------------------
-- Table structure for acg_users
-- ----------------------------
DROP TABLE [dbo].[acg_users]
GO
CREATE TABLE [dbo].[acg_users] (
[id] bigint NOT NULL ,
[name] varchar(100) NULL ,
[password] varchar(100) NULL ,
[type] varchar(100) NULL ,
[nickname] varchar(255) NULL ,
[phone] bigint NULL ,
[identification] bigint NOT NULL ,
[email] varchar(100) NULL ,
[address] varchar(100) NULL ,
[sex] varchar(2) NULL ,
[year] int NULL ,
[img] varchar(100) NULL ,
[provincial] varchar(100) NULL ,
[city] varchar(100) NULL 
)


GO

-- ----------------------------
-- Indexes structure for table acg_users
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table acg_users
-- ----------------------------
ALTER TABLE [dbo].[acg_users] ADD PRIMARY KEY ([id])
GO

-- ----------------------------
-- Uniques structure for table acg_users
-- ----------------------------
ALTER TABLE [dbo].[acg_users] ADD UNIQUE ([id] ASC)
GO
ALTER TABLE [dbo].[acg_users] ADD UNIQUE ([nickname] ASC)
GO
