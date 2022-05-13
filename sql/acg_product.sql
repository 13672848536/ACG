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

Date: 2022-05-13 21:56:50
*/


-- ----------------------------
-- Table structure for acg_product
-- ----------------------------
DROP TABLE [dbo].[acg_product]
GO
CREATE TABLE [dbo].[acg_product] (
[id] bigint NOT NULL ,
[product] varchar(100) NULL ,
[title] varchar(255) NULL ,
[content] varchar(255) NULL ,
[apv] int NULL ,
[img] varchar(255) NULL ,
[type] varchar(100) NULL ,
[amount] int NULL ,
[productid] bigint NOT NULL ,
[price] int NULL ,
[stock] int NULL ,
[username] varchar(100) NULL ,
[state] varchar(100) NULL 
)


GO

-- ----------------------------
-- Records of acg_product
-- ----------------------------

-- ----------------------------
-- Indexes structure for table acg_product
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table acg_product
-- ----------------------------
ALTER TABLE [dbo].[acg_product] ADD PRIMARY KEY ([productid])
GO
