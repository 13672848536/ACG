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

Date: 2022-05-13 21:57:46
*/


-- ----------------------------
-- Table structure for City
-- ----------------------------
DROP TABLE [dbo].[City]
GO
CREATE TABLE [dbo].[City] (
[cid] int NOT NULL ,
[city] varchar(50) NOT NULL ,
[pid] int NULL 
)


GO

-- ----------------------------
-- Indexes structure for table City
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table City
-- ----------------------------
ALTER TABLE [dbo].[City] ADD PRIMARY KEY ([city])
GO

-- ----------------------------
-- Foreign Key structure for table [dbo].[City]
-- ----------------------------
ALTER TABLE [dbo].[City] ADD FOREIGN KEY ([pid]) REFERENCES [dbo].[Provincial] ([pid]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO
