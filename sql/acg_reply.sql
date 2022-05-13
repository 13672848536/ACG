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

Date: 2022-05-13 21:57:07
*/


-- ----------------------------
-- Table structure for acg_reply
-- ----------------------------
DROP TABLE [dbo].[acg_reply]
GO
CREATE TABLE [dbo].[acg_reply] (
[id] bigint NULL ,
[userid] bigint NULL ,
[reply] text NULL ,
[createtime] datetime2(7) NULL 
)


GO
