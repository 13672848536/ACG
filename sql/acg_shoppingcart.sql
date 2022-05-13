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

Date: 2022-05-13 21:57:31
*/


-- ----------------------------
-- Table structure for acg_shoppingcart
-- ----------------------------
DROP TABLE [dbo].[acg_shoppingcart]
GO
CREATE TABLE [dbo].[acg_shoppingcart] (
[userid] bigint NULL ,
[productid] bigint NULL ,
[amount] int NULL ,
[id] bigint NOT NULL ,
[type] varchar(255) NULL ,
[size] varchar(255) NULL 
)


GO

-- ----------------------------
-- Indexes structure for table acg_shoppingcart
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table acg_shoppingcart
-- ----------------------------
ALTER TABLE [dbo].[acg_shoppingcart] ADD PRIMARY KEY ([id])
GO
