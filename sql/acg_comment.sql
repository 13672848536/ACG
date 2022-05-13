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

Date: 2022-05-13 21:56:15
*/


-- ----------------------------
-- Table structure for acg_comment
-- ----------------------------
DROP TABLE [dbo].[acg_comment]
GO
CREATE TABLE [dbo].[acg_comment] (
[productid] bigint NOT NULL ,
[userid] bigint NULL ,
[comment] text NULL ,
[createtime] datetime NOT NULL ,
[id] bigint NULL 
)


GO

-- ----------------------------
-- Records of acg_comment
-- ----------------------------
