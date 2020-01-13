/*
 Navicat Premium Data Transfer

 Source Server         : xb
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : 39.106.95.230:3306
 Source Schema         : guns

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 13/01/2020 17:08:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for code_dbinfo
-- ----------------------------
DROP TABLE IF EXISTS `code_dbinfo`;
CREATE TABLE `code_dbinfo`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '别名',
  `db_driver` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '数据库驱动',
  `db_url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '数据库地址',
  `db_user_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '数据库账户',
  `db_password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '连接密码',
  `db_type` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据库类型',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '数据库链接信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `num` int(11) NULL DEFAULT NULL COMMENT '排序',
  `pid` int(11) NULL DEFAULT NULL COMMENT '父部门id',
  `pids` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父级ids',
  `simplename` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '简称',
  `fullname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '全称',
  `tips` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '提示',
  `version` int(11) NULL DEFAULT NULL COMMENT '版本（乐观锁保留字段）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 44 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (24, 1, 0, '[0],', '总公司', '总公司', '', NULL);
INSERT INTO `sys_dept` VALUES (25, 2, 24, '[0],[24],', '开发部', '开发部', '', NULL);
INSERT INTO `sys_dept` VALUES (26, 3, 24, '[0],[24],', '运营部', '运营部', '', NULL);
INSERT INTO `sys_dept` VALUES (27, 4, 24, '[0],[24],', '战略部', '战略部', '', NULL);

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `num` int(11) NULL DEFAULT NULL COMMENT '排序',
  `pid` int(11) NULL DEFAULT NULL COMMENT '父级字典',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `tips` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '提示',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '值',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 60 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES (50, 0, 0, '性别', NULL, 'sys_sex');
INSERT INTO `sys_dict` VALUES (51, 1, 50, '男', NULL, '1');
INSERT INTO `sys_dict` VALUES (52, 2, 50, '女', NULL, '2');
INSERT INTO `sys_dict` VALUES (53, 0, 0, '状态', NULL, 'sys_state');
INSERT INTO `sys_dict` VALUES (54, 1, 53, '启用', NULL, '1');
INSERT INTO `sys_dict` VALUES (55, 2, 53, '禁用', NULL, '2');
INSERT INTO `sys_dict` VALUES (56, 0, 0, '账号状态', NULL, 'account_state');
INSERT INTO `sys_dict` VALUES (57, 1, 56, '启用', NULL, '1');
INSERT INTO `sys_dict` VALUES (58, 2, 56, '冻结', NULL, '2');
INSERT INTO `sys_dict` VALUES (59, 3, 56, '已删除', NULL, '3');

-- ----------------------------
-- Table structure for sys_expense
-- ----------------------------
DROP TABLE IF EXISTS `sys_expense`;
CREATE TABLE `sys_expense`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `money` decimal(20, 2) NULL DEFAULT NULL COMMENT '报销金额',
  `desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '描述',
  `createtime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `state` int(11) NULL DEFAULT NULL COMMENT '状态: 1.待提交  2:待审核   3.审核通过 4:驳回',
  `userid` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `processId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程定义id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '报销表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_login_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_login_log`;
CREATE TABLE `sys_login_log`  (
  `id` int(65) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `logname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '日志名称',
  `userid` int(65) NULL DEFAULT NULL COMMENT '管理员id',
  `createtime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `succeed` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否执行成功',
  `message` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '具体消息',
  `ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录ip',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 499 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '登录记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_login_log
-- ----------------------------
INSERT INTO `sys_login_log` VALUES (463, '退出日志', 45, '2020-01-10 21:48:52', '成功', NULL, '39.106.95.230');
INSERT INTO `sys_login_log` VALUES (464, '登录失败日志', NULL, '2020-01-10 21:48:58', '成功', '账号:admin,账号密码错误', '39.106.95.230');
INSERT INTO `sys_login_log` VALUES (465, '登录日志', 1, '2020-01-10 21:49:10', '成功', NULL, '39.106.95.230');
INSERT INTO `sys_login_log` VALUES (466, '登录日志', 1, '2020-01-10 21:59:47', '成功', NULL, '39.106.95.230');
INSERT INTO `sys_login_log` VALUES (467, '退出日志', 1, '2020-01-10 22:21:53', '成功', NULL, '39.106.95.230');
INSERT INTO `sys_login_log` VALUES (468, '登录日志', 45, '2020-01-10 22:21:58', '成功', NULL, '39.106.95.230');
INSERT INTO `sys_login_log` VALUES (469, '退出日志', 45, '2020-01-10 22:22:13', '成功', NULL, '39.106.95.230');
INSERT INTO `sys_login_log` VALUES (470, '登录日志', 1, '2020-01-10 22:22:16', '成功', NULL, '39.106.95.230');
INSERT INTO `sys_login_log` VALUES (471, '退出日志', 1, '2020-01-10 22:35:21', '成功', NULL, '39.106.95.230');
INSERT INTO `sys_login_log` VALUES (472, '登录日志', 45, '2020-01-10 22:35:25', '成功', NULL, '39.106.95.230');
INSERT INTO `sys_login_log` VALUES (473, '退出日志', 45, '2020-01-10 22:39:41', '成功', NULL, '39.106.95.230');
INSERT INTO `sys_login_log` VALUES (474, '登录日志', 1, '2020-01-10 22:39:45', '成功', NULL, '39.106.95.230');
INSERT INTO `sys_login_log` VALUES (475, '登录日志', 1, '2020-01-10 23:22:34', '成功', NULL, '39.106.95.230');
INSERT INTO `sys_login_log` VALUES (476, '登录日志', 1, '2020-01-10 23:51:15', '成功', NULL, '39.106.95.230');
INSERT INTO `sys_login_log` VALUES (477, '退出日志', 1, '2020-01-10 23:52:42', '成功', NULL, '39.106.95.230');
INSERT INTO `sys_login_log` VALUES (478, '登录日志', 45, '2020-01-10 23:52:45', '成功', NULL, '39.106.95.230');
INSERT INTO `sys_login_log` VALUES (479, '登录日志', 1, '2020-01-11 00:02:47', '成功', NULL, '39.106.95.230');
INSERT INTO `sys_login_log` VALUES (480, '退出日志', 1, '2020-01-11 00:03:49', '成功', NULL, '39.106.95.230');
INSERT INTO `sys_login_log` VALUES (481, '登录日志', 45, '2020-01-11 00:03:53', '成功', NULL, '39.106.95.230');
INSERT INTO `sys_login_log` VALUES (482, '退出日志', 45, '2020-01-11 00:13:08', '成功', NULL, '39.106.95.230');
INSERT INTO `sys_login_log` VALUES (483, '登录日志', 1, '2020-01-11 00:13:24', '成功', NULL, '39.106.95.230');
INSERT INTO `sys_login_log` VALUES (484, '登录日志', 1, '2020-01-11 00:25:07', '成功', NULL, '39.106.95.230');
INSERT INTO `sys_login_log` VALUES (485, '退出日志', 1, '2020-01-11 00:25:23', '成功', NULL, '39.106.95.230');
INSERT INTO `sys_login_log` VALUES (486, '登录日志', 45, '2020-01-11 00:25:26', '成功', NULL, '39.106.95.230');
INSERT INTO `sys_login_log` VALUES (487, '退出日志', 45, '2020-01-11 00:30:53', '成功', NULL, '39.106.95.230');
INSERT INTO `sys_login_log` VALUES (488, '登录日志', 1, '2020-01-11 00:31:00', '成功', NULL, '39.106.95.230');
INSERT INTO `sys_login_log` VALUES (489, '退出日志', 1, '2020-01-11 00:35:44', '成功', NULL, '39.106.95.230');
INSERT INTO `sys_login_log` VALUES (490, '登录日志', 45, '2020-01-11 00:35:47', '成功', NULL, '39.106.95.230');
INSERT INTO `sys_login_log` VALUES (491, '登录日志', 45, '2020-01-11 00:48:02', '成功', NULL, '39.106.95.230');
INSERT INTO `sys_login_log` VALUES (492, '登录日志', 45, '2020-01-11 00:59:00', '成功', NULL, '39.106.95.230');
INSERT INTO `sys_login_log` VALUES (493, '退出日志', 45, '2020-01-11 01:00:42', '成功', NULL, '39.106.95.230');
INSERT INTO `sys_login_log` VALUES (494, '登录日志', 1, '2020-01-11 01:00:46', '成功', NULL, '39.106.95.230');
INSERT INTO `sys_login_log` VALUES (495, '退出日志', 1, '2020-01-11 01:01:14', '成功', NULL, '39.106.95.230');
INSERT INTO `sys_login_log` VALUES (496, '登录日志', 1, '2020-01-11 18:42:07', '成功', NULL, '39.106.95.230');
INSERT INTO `sys_login_log` VALUES (497, '登录日志', 45, '2020-01-12 16:14:15', '成功', NULL, '39.106.95.230');
INSERT INTO `sys_login_log` VALUES (498, '登录日志', 1, '2020-01-13 16:13:22', '成功', NULL, '39.106.95.230');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单编号',
  `pcode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单父编号',
  `pcodes` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '当前菜单的所有父菜单编号',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'url地址',
  `num` int(65) NULL DEFAULT NULL COMMENT '菜单排序号',
  `levels` int(65) NULL DEFAULT NULL COMMENT '菜单层级',
  `ismenu` int(11) NULL DEFAULT NULL COMMENT '是否是菜单（1：是  0：不是）',
  `tips` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `status` int(65) NULL DEFAULT NULL COMMENT '菜单状态 :  1:启用   0:不启用',
  `isopen` int(11) NULL DEFAULT NULL COMMENT '是否打开:    1:打开   0:不打开',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1207143149412900868 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (105, 'system', '0', '[0],', '系统管理', 'fa-cog', '#', 99, 1, 1, NULL, 1, 1);
INSERT INTO `sys_menu` VALUES (106, 'mgr', 'system', '[0],[system],', '用户管理', '', '/mgr', 1, 2, 1, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (107, 'mgr_add', 'mgr', '[0],[system],[mgr],', '添加用户', NULL, '/mgr/add', 1, 3, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (108, 'mgr_edit', 'mgr', '[0],[system],[mgr],', '修改用户', NULL, '/mgr/edit', 2, 3, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (109, 'mgr_delete', 'mgr', '[0],[system],[mgr],', '删除用户', NULL, '/mgr/delete', 3, 3, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (110, 'mgr_reset', 'mgr', '[0],[system],[mgr],', '重置密码', NULL, '/mgr/reset', 4, 3, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (111, 'mgr_freeze', 'mgr', '[0],[system],[mgr],', '冻结用户', NULL, '/mgr/freeze', 5, 3, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (112, 'mgr_unfreeze', 'mgr', '[0],[system],[mgr],', '解除冻结用户', NULL, '/mgr/unfreeze', 6, 3, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (113, 'mgr_setRole', 'mgr', '[0],[system],[mgr],', '分配角色', NULL, '/mgr/setRole', 7, 3, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (114, 'role', 'system', '[0],[system],', '角色管理', NULL, '/role', 2, 2, 1, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (115, 'role_add', 'role', '[0],[system],[role],', '添加角色', NULL, '/role/add', 1, 3, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (116, 'role_edit', 'role', '[0],[system],[role],', '修改角色', NULL, '/role/edit', 2, 3, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (117, 'role_remove', 'role', '[0],[system],[role],', '删除角色', NULL, '/role/remove', 3, 3, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (118, 'role_setAuthority', 'role', '[0],[system],[role],', '配置权限', NULL, '/role/setAuthority', 4, 3, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (119, 'menu', 'system', '[0],[system],', '菜单管理', NULL, '/menu', 4, 2, 1, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (120, 'menu_add', 'menu', '[0],[system],[menu],', '添加菜单', NULL, '/menu/add', 1, 3, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (121, 'menu_edit', 'menu', '[0],[system],[menu],', '修改菜单', NULL, '/menu/edit', 2, 3, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (122, 'menu_remove', 'menu', '[0],[system],[menu],', '删除菜单', NULL, '/menu/remove', 3, 3, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (128, 'log', 'system', '[0],[system],', '业务日志', NULL, '/log', 6, 2, 1, NULL, 0, 0);
INSERT INTO `sys_menu` VALUES (130, 'druid', 'system', '[0],[system],', '监控管理', NULL, '/druid', 7, 2, 1, NULL, 0, NULL);
INSERT INTO `sys_menu` VALUES (131, 'dept', 'system', '[0],[system],', '部门管理', NULL, '/dept', 3, 2, 1, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (132, 'dict', 'system', '[0],[system],', '字典管理', NULL, '/dict', 4, 2, 1, NULL, 0, NULL);
INSERT INTO `sys_menu` VALUES (133, 'loginLog', 'system', '[0],[system],', '登录日志', NULL, '/loginLog', 6, 2, 1, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (134, 'log_clean', 'log', '[0],[system],[log],', '清空日志', NULL, '/log/delLog', 3, 3, 0, NULL, 0, NULL);
INSERT INTO `sys_menu` VALUES (135, 'dept_add', 'dept', '[0],[system],[dept],', '添加部门', NULL, '/dept/add', 1, 3, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (136, 'dept_update', 'dept', '[0],[system],[dept],', '修改部门', NULL, '/dept/update', 1, 3, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (137, 'dept_delete', 'dept', '[0],[system],[dept],', '删除部门', NULL, '/dept/delete', 1, 3, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (138, 'dict_add', 'dict', '[0],[system],[dict],', '添加字典', NULL, '/dict/add', 1, 3, 0, NULL, 0, NULL);
INSERT INTO `sys_menu` VALUES (139, 'dict_update', 'dict', '[0],[system],[dict],', '修改字典', NULL, '/dict/update', 1, 3, 0, NULL, 0, NULL);
INSERT INTO `sys_menu` VALUES (140, 'dict_delete', 'dict', '[0],[system],[dict],', '删除字典', NULL, '/dict/delete', 1, 3, 0, NULL, 0, NULL);
INSERT INTO `sys_menu` VALUES (141, 'notice', 'system', '[0],[system],', '通知管理', NULL, '/notice', 9, 2, 1, NULL, 0, NULL);
INSERT INTO `sys_menu` VALUES (142, 'notice_add', 'notice', '[0],[system],[notice],', '添加通知', NULL, '/notice/add', 1, 3, 0, NULL, 0, NULL);
INSERT INTO `sys_menu` VALUES (143, 'notice_update', 'notice', '[0],[system],[notice],', '修改通知', NULL, '/notice/update', 2, 3, 0, NULL, 0, NULL);
INSERT INTO `sys_menu` VALUES (144, 'notice_delete', 'notice', '[0],[system],[notice],', '删除通知', NULL, '/notice/delete', 3, 3, 0, NULL, 0, NULL);
INSERT INTO `sys_menu` VALUES (145, 'hello', '0', '[0],', '用户树状结构', 'fa-sitemap', '/wxUser/spaceTreeUser', 1, 1, 1, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (148, 'code', '0', '[0],', '代码生成', 'fa-code', '/code', 9, 1, 1, NULL, 0, NULL);
INSERT INTO `sys_menu` VALUES (149, 'api_mgr', '0', '[0],', '接口文档', 'fa-leaf', '/swagger-ui.html', 8, 1, 1, NULL, 0, NULL);
INSERT INTO `sys_menu` VALUES (150, 'to_menu_edit', 'menu', '[0],[system],[menu],', '菜单编辑跳转', '', '/menu/menu_edit', 4, 3, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (151, 'menu_list', 'menu', '[0],[system],[menu],', '菜单列表', '', '/menu/list', 5, 3, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (152, 'to_dept_update', 'dept', '[0],[system],[dept],', '修改部门跳转', '', '/dept/dept_update', 4, 3, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (153, 'dept_list', 'dept', '[0],[system],[dept],', '部门列表', '', '/dept/list', 5, 3, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (154, 'dept_detail', 'dept', '[0],[system],[dept],', '部门详情', '', '/dept/detail', 6, 3, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (155, 'to_dict_edit', 'dict', '[0],[system],[dict],', '修改菜单跳转', '', '/dict/dict_edit', 4, 3, 0, NULL, 0, NULL);
INSERT INTO `sys_menu` VALUES (156, 'dict_list', 'dict', '[0],[system],[dict],', '字典列表', '', '/dict/list', 5, 3, 0, NULL, 0, NULL);
INSERT INTO `sys_menu` VALUES (157, 'dict_detail', 'dict', '[0],[system],[dict],', '字典详情', '', '/dict/detail', 6, 3, 0, NULL, 0, NULL);
INSERT INTO `sys_menu` VALUES (158, 'log_list', 'log', '[0],[system],[log],', '日志列表', '', '/log/list', 2, 3, 0, NULL, 0, NULL);
INSERT INTO `sys_menu` VALUES (159, 'log_detail', 'log', '[0],[system],[log],', '日志详情', '', '/log/detail', 3, 3, 0, NULL, 0, NULL);
INSERT INTO `sys_menu` VALUES (160, 'del_login_log', 'loginLog', '[0],[system],[loginLog],', '清空登录日志', '', '/loginLog/delLoginLog', 1, 3, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (161, 'login_log_list', 'loginLog', '[0],[system],[loginLog],', '登录日志列表', '', '/loginLog/list', 2, 3, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (162, 'to_role_edit', 'role', '[0],[system],[role],', '修改角色跳转', '', '/role/role_edit', 5, 3, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (163, 'to_role_assign', 'role', '[0],[system],[role],', '角色分配跳转', '', '/role/role_assign', 6, 3, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (164, 'role_list', 'role', '[0],[system],[role],', '角色列表', '', '/role/list', 7, 3, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (165, 'to_assign_role', 'mgr', '[0],[system],[mgr],', '分配角色跳转', '', '/mgr/role_assign', 8, 3, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (166, 'to_user_edit', 'mgr', '[0],[system],[mgr],', '编辑用户跳转', '', '/mgr/user_edit', 9, 3, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (167, 'mgr_list', 'mgr', '[0],[system],[mgr],', '用户列表', '', '/mgr/list', 10, 3, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (168, 'qunge', '0', '[0],', '销帮', '', '/notice/hello', 99, 1, 1, NULL, 0, NULL);
INSERT INTO `sys_menu` VALUES (1191268370864762882, 'card', '0', '[0],', '名片管理', 'fa-id-card', '/card', 3, 1, 1, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1191268370864762883, 'card_list', 'card', '[0],[card],', '列表', '', '/card/list', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1191268370864762884, 'card_add', 'card', '[0],[card],', '添加', '', '/card/add', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1191268370864762885, 'card_update', 'card', '[0],[card],', '更新', '', '/card/update', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1191268370864762886, 'card_delete', 'card', '[0],[card],', '删除', '', '/card/delete', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1191268370864762887, 'card_detail', 'card', '[0],[card],', '详情', '', '/card/detail', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1197772074778439681, 'incomeFlowing', '0', '[0],', '收入流水管理', 'fa-list-ul', '/incomeFlowing', 6, 1, 1, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1197772074778439682, 'incomeFlowing_list', 'incomeFlowing', '[0],[incomeFlowing],', '收入流水列表', '', '/incomeFlowing/list', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1197772074778439683, 'incomeFlowing_add', 'incomeFlowing', '[0],[incomeFlowing],', '收入流水添加', '', '/incomeFlowing/add', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1197772074778439684, 'incomeFlowing_update', 'incomeFlowing', '[0],[incomeFlowing],', '收入流水更新', '', '/incomeFlowing/update', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1197772074778439685, 'incomeFlowing_delete', 'incomeFlowing', '[0],[incomeFlowing],', '收入流水删除', '', '/incomeFlowing/delete', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1197772074778439686, 'incomeFlowing_detail', 'incomeFlowing', '[0],[incomeFlowing],', '收入流水详情', '', '/incomeFlowing/detail', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1197840048637984770, 'outFlowing', '0', '[0],', '客户提现流水管理', 'fa-list-ul', '/outFlowing', 7, 1, 1, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1197840048637984771, 'outFlowing_list', 'outFlowing', '[0],[outFlowing],', '平台支出流水列表', '', '/outFlowing/list', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1197840048637984772, 'outFlowing_add', 'outFlowing', '[0],[outFlowing],', '平台支出流水添加', '', '/outFlowing/add', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1197840048637984773, 'outFlowing_update', 'outFlowing', '[0],[outFlowing],', '平台支出流水更新', '', '/outFlowing/update', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1197840048637984774, 'outFlowing_delete', 'outFlowing', '[0],[outFlowing],', '平台支出流水删除', '', '/outFlowing/delete', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1197840048637984775, 'outFlowing_detail', 'outFlowing', '[0],[outFlowing],', '平台支出流水详情', '', '/outFlowing/detail', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1198212272058929153, 'wxUser', '0', '[0],', '小程序用户管理', 'fa-users', '/wxUser', 2, 1, 1, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1198212272058929154, 'wxUser_list', 'wxUser', '[0],[wxUser],', '小程序用户列表', '', '/wxUser/list', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1198212272058929155, 'wxUser_add', 'wxUser', '[0],[wxUser],', '小程序用户添加', '', '/wxUser/add', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1198212272058929156, 'wxUser_update', 'wxUser', '[0],[wxUser],', '小程序用户更新', '', '/wxUser/update', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1198212272058929157, 'wxUser_delete', 'wxUser', '[0],[wxUser],', '小程序用户删除', '', '/wxUser/delete', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1198212272058929158, 'wxUser_detail', 'wxUser', '[0],[wxUser],', '小程序用户详情', '', '/wxUser/detail', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1202253396163579905, 'callCenter', '0', '[0],', '客服中心管理', 'fa-volume-control-phone', '/callCenter', 5, 1, 1, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1202253396163579906, 'callCenter_list', 'callCenter', '[0],[callCenter],', '客服中心记录列表', '', '/callCenter/list', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1202253396163579907, 'callCenter_add', 'callCenter', '[0],[callCenter],', '客服中心记录添加', '', '/callCenter/add', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1202253396163579908, 'callCenter_update', 'callCenter', '[0],[callCenter],', '客服中心记录更新', '', '/callCenter/update', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1202253396163579909, 'callCenter_delete', 'callCenter', '[0],[callCenter],', '客服中心记录删除', '', '/callCenter/delete', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1202253396163579910, 'callCenter_detail', 'callCenter', '[0],[callCenter],', '客服中心记录详情', '', '/callCenter/detail', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1202253396163579911, 'xxxx', 'mgr', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1207143149404512257, 'material', '0', '[0],', '商品图片管理', 'fa-picture-o', '/material', 4, 1, 1, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1207143149404512258, 'material_list', 'material', '[0],[material],', '素材列表', '', '/material/list', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1207143149404512259, 'material_add', 'material', '[0],[material],', '素材添加', '', '/material/add', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1207143149412900865, 'material_update', 'material', '[0],[material],', '素材更新', '', '/material/update', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1207143149412900866, 'material_delete', 'material', '[0],[material],', '素材删除', '', '/material/delete', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1207143149412900867, 'material_detail', 'material', '[0],[material],', '素材详情', '', '/material/detail', 99, 2, 0, NULL, 1, 0);

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `type` int(11) NULL DEFAULT NULL COMMENT '类型',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容',
  `createtime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `creater` int(11) NULL DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '通知表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_notice
-- ----------------------------
INSERT INTO `sys_notice` VALUES (6, '世界', 10, '欢迎使用销帮后台管理系统', '2017-01-11 08:53:20', 1);
INSERT INTO `sys_notice` VALUES (8, '你好', NULL, '你好', '2017-05-10 19:28:57', 1);

-- ----------------------------
-- Table structure for sys_operation_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_operation_log`;
CREATE TABLE `sys_operation_log`  (
  `id` int(65) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `logtype` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '日志类型',
  `logname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '日志名称',
  `userid` int(65) NULL DEFAULT NULL COMMENT '用户id',
  `classname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类名称',
  `method` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '方法名称',
  `createtime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `succeed` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否成功',
  `message` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 663 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '操作日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_operation_log
-- ----------------------------
INSERT INTO `sys_operation_log` VALUES (641, '业务日志', '清空业务日志', 45, 'cn.stylefeng.guns.modular.system.controller.LogController', 'delLog', '2020-01-10 21:48:35', '成功', '主键id=null');
INSERT INTO `sys_operation_log` VALUES (642, '业务日志', '清空登录日志', 45, 'cn.stylefeng.guns.modular.system.controller.LoginLogController', 'delLog', '2020-01-10 21:48:43', '成功', '主键id=null');
INSERT INTO `sys_operation_log` VALUES (643, '业务日志', '添加角色', 1, 'cn.stylefeng.guns.modular.system.controller.RoleController', 'add', '2020-01-10 22:19:32', '成功', '角色名称=员工');
INSERT INTO `sys_operation_log` VALUES (644, '业务日志', '修改管理员', 1, 'cn.stylefeng.guns.modular.system.controller.UserMgrController', 'edit', '2020-01-10 22:33:43', '成功', '账号=admin;;;字段名称:头像,旧值:ae92a4b6-f357-4ca4-a6f2-c4f687af7685.jpg,新值:;;;字段名称:null,旧值:d1d77b8cf5e0fca3448c02bc58fdae78,新值:;;;字段名称:部门名称,旧值:战略部,新值:');
INSERT INTO `sys_operation_log` VALUES (645, '业务日志', '删除管理员', 1, 'cn.stylefeng.guns.modular.system.controller.UserMgrController', 'delete', '2020-01-10 22:41:44', '成功', '账号=test');
INSERT INTO `sys_operation_log` VALUES (646, '异常日志', '', 1, NULL, NULL, '2020-01-10 22:42:28', '失败', 'cn.stylefeng.roses.kernel.model.exception.ServiceException: 权限异常\n	at cn.stylefeng.guns.modular.system.controller.UserMgrController.edit(UserMgrController.java:240)\n	at cn.stylefeng.guns.modular.system.controller.UserMgrControllerTTFastClassBySpringCGLIBTT7c4c2edf.invoke(<generated>)\n	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:204)\n	at org.springframework.aop.framework.CglibAopProxyTCglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:747)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)\n	at org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint.proceed(MethodInvocationProceedingJoinPoint.java:89)\n	at cn.stylefeng.guns.core.aop.LogAop.recordSysLog(LogAop.java:60)\n	at sun.reflect.GeneratedMethodAccessor628.invoke(Unknown Source)\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n	at java.lang.reflect.Method.invoke(Method.java:498)\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethodWithGivenArgs(AbstractAspectJAdvice.java:644)\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethod(AbstractAspectJAdvice.java:633)\n	at org.springframework.aop.aspectj.AspectJAroundAdvice.invoke(AspectJAroundAdvice.java:70)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:174)\n	at org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint.proceed(MethodInvocationProceedingJoinPoint.java:89)\n	at cn.stylefeng.guns.core.interceptor.SessionHolderInterceptor.sessionKit(SessionHolderInterceptor.java:44)\n	at sun.reflect.GeneratedMethodAccessor331.invoke(Unknown Source)\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n	at java.lang.reflect.Method.invoke(Method.java:498)\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethodWithGivenArgs(AbstractAspectJAdvice.java:644)\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethod(AbstractAspectJAdvice.java:633)\n	at org.springframework.aop.aspectj.AspectJAroundAdvice.invoke(AspectJAroundAdvice.java:70)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:185)\n	at org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:92)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:185)\n	at org.springframework.aop.framework.CglibAopProxyTDynamicAdvisedInterceptor.intercept(CglibAopProxy.java:689)\n	at cn.stylefeng.guns.modular.system.controller.UserMgrControllerTTEnhancerBySpringCGLIBTTc96507ec.edit(<generated>)\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n	at java.lang.reflect.Method.invoke(Method.java:498)\n	at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:209)\n	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:136)\n	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:102)\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:877)\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:783)\n	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)\n	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:991)\n	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:925)\n	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:974)\n	at org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:877)\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:661)\n	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:851)\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:742)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:231)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:52)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:61)\n	at org.apache.shiro.web.servlet.AdviceFilter.executeChain(AdviceFilter.java:108)\n	at org.apache.shiro.web.servlet.AdviceFilter.doFilterInternal(AdviceFilter.java:137)\n	at org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:125)\n	at org.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:66)\n	at org.apache.shiro.web.servlet.AbstractShiroFilter.executeChain(AbstractShiroFilter.java:449)\n	at org.apache.shiro.web.servlet.AbstractShiroFilterT1.call(AbstractShiroFilter.java:365)\n	at org.apache.shiro.subject.support.SubjectCallable.doCall(SubjectCallable.java:90)\n	at org.apache.shiro.subject.support.SubjectCallable.call(SubjectCallable.java:83)\n	at org.apache.shiro.subject.support.DelegatingSubject.execute(DelegatingSubject.java:387)\n	at org.apache.shiro.web.servlet.AbstractShiroFilter.doFilterInternal(AbstractShiroFilter.java:362)\n	at org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:125)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at cn.stylefeng.roses.core.xss.XssFilter.doFilter(XssFilter.java:31)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at com.alibaba.druid.support.http.WebStatFilter.doFilter(WebStatFilter.java:123)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:99)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.springframework.web.filter.HttpPutFormContentFilter.doFilterInternal(HttpPutFormContentFilter.java:109)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.springframework.web.filter.HiddenHttpMethodFilter.doFilterInternal(HiddenHttpMethodFilter.java:81)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:200)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:198)\n	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:96)\n	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:496)\n	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:140)\n	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:81)\n	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:87)\n	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:342)\n	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:803)\n	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:66)\n	at org.apache.coyote.AbstractProtocolTConnectionHandler.process(AbstractProtocol.java:790)\n	at org.apache.tomcat.util.net.NioEndpointTSocketProcessor.doRun(NioEndpoint.java:1459)\n	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)\n	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)\n	at java.util.concurrent.ThreadPoolExecutorTWorker.run(ThreadPoolExecutor.java:617)\n	at org.apache.tomcat.util.threads.TaskThreadTWrappingRunnable.run(TaskThread.java:61)\n	at java.lang.Thread.run(Thread.java:748)\n');
INSERT INTO `sys_operation_log` VALUES (647, '异常日志', '', 1, NULL, NULL, '2020-01-10 22:42:33', '失败', 'cn.stylefeng.roses.kernel.model.exception.ServiceException: 权限异常\n	at cn.stylefeng.guns.modular.system.controller.UserMgrController.edit(UserMgrController.java:240)\n	at cn.stylefeng.guns.modular.system.controller.UserMgrControllerTTFastClassBySpringCGLIBTT7c4c2edf.invoke(<generated>)\n	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:204)\n	at org.springframework.aop.framework.CglibAopProxyTCglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:747)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)\n	at org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint.proceed(MethodInvocationProceedingJoinPoint.java:89)\n	at cn.stylefeng.guns.core.aop.LogAop.recordSysLog(LogAop.java:60)\n	at sun.reflect.GeneratedMethodAccessor628.invoke(Unknown Source)\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n	at java.lang.reflect.Method.invoke(Method.java:498)\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethodWithGivenArgs(AbstractAspectJAdvice.java:644)\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethod(AbstractAspectJAdvice.java:633)\n	at org.springframework.aop.aspectj.AspectJAroundAdvice.invoke(AspectJAroundAdvice.java:70)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:174)\n	at org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint.proceed(MethodInvocationProceedingJoinPoint.java:89)\n	at cn.stylefeng.guns.core.interceptor.SessionHolderInterceptor.sessionKit(SessionHolderInterceptor.java:44)\n	at sun.reflect.GeneratedMethodAccessor331.invoke(Unknown Source)\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n	at java.lang.reflect.Method.invoke(Method.java:498)\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethodWithGivenArgs(AbstractAspectJAdvice.java:644)\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethod(AbstractAspectJAdvice.java:633)\n	at org.springframework.aop.aspectj.AspectJAroundAdvice.invoke(AspectJAroundAdvice.java:70)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:185)\n	at org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:92)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:185)\n	at org.springframework.aop.framework.CglibAopProxyTDynamicAdvisedInterceptor.intercept(CglibAopProxy.java:689)\n	at cn.stylefeng.guns.modular.system.controller.UserMgrControllerTTEnhancerBySpringCGLIBTTc96507ec.edit(<generated>)\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n	at java.lang.reflect.Method.invoke(Method.java:498)\n	at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:209)\n	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:136)\n	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:102)\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:877)\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:783)\n	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)\n	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:991)\n	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:925)\n	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:974)\n	at org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:877)\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:661)\n	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:851)\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:742)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:231)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:52)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:61)\n	at org.apache.shiro.web.servlet.AdviceFilter.executeChain(AdviceFilter.java:108)\n	at org.apache.shiro.web.servlet.AdviceFilter.doFilterInternal(AdviceFilter.java:137)\n	at org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:125)\n	at org.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:66)\n	at org.apache.shiro.web.servlet.AbstractShiroFilter.executeChain(AbstractShiroFilter.java:449)\n	at org.apache.shiro.web.servlet.AbstractShiroFilterT1.call(AbstractShiroFilter.java:365)\n	at org.apache.shiro.subject.support.SubjectCallable.doCall(SubjectCallable.java:90)\n	at org.apache.shiro.subject.support.SubjectCallable.call(SubjectCallable.java:83)\n	at org.apache.shiro.subject.support.DelegatingSubject.execute(DelegatingSubject.java:387)\n	at org.apache.shiro.web.servlet.AbstractShiroFilter.doFilterInternal(AbstractShiroFilter.java:362)\n	at org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:125)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at cn.stylefeng.roses.core.xss.XssFilter.doFilter(XssFilter.java:31)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at com.alibaba.druid.support.http.WebStatFilter.doFilter(WebStatFilter.java:123)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:99)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.springframework.web.filter.HttpPutFormContentFilter.doFilterInternal(HttpPutFormContentFilter.java:109)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.springframework.web.filter.HiddenHttpMethodFilter.doFilterInternal(HiddenHttpMethodFilter.java:81)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:200)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:198)\n	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:96)\n	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:496)\n	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:140)\n	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:81)\n	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:87)\n	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:342)\n	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:803)\n	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:66)\n	at org.apache.coyote.AbstractProtocolTConnectionHandler.process(AbstractProtocol.java:790)\n	at org.apache.tomcat.util.net.NioEndpointTSocketProcessor.doRun(NioEndpoint.java:1459)\n	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)\n	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)\n	at java.util.concurrent.ThreadPoolExecutorTWorker.run(ThreadPoolExecutor.java:617)\n	at org.apache.tomcat.util.threads.TaskThreadTWrappingRunnable.run(TaskThread.java:61)\n	at java.lang.Thread.run(Thread.java:748)\n');
INSERT INTO `sys_operation_log` VALUES (648, '异常日志', '', 1, NULL, NULL, '2020-01-10 22:42:43', '失败', 'cn.stylefeng.roses.kernel.model.exception.ServiceException: 权限异常\n	at cn.stylefeng.guns.modular.system.controller.UserMgrController.edit(UserMgrController.java:240)\n	at cn.stylefeng.guns.modular.system.controller.UserMgrControllerTTFastClassBySpringCGLIBTT7c4c2edf.invoke(<generated>)\n	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:204)\n	at org.springframework.aop.framework.CglibAopProxyTCglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:747)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)\n	at org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint.proceed(MethodInvocationProceedingJoinPoint.java:89)\n	at cn.stylefeng.guns.core.aop.LogAop.recordSysLog(LogAop.java:60)\n	at sun.reflect.GeneratedMethodAccessor628.invoke(Unknown Source)\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n	at java.lang.reflect.Method.invoke(Method.java:498)\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethodWithGivenArgs(AbstractAspectJAdvice.java:644)\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethod(AbstractAspectJAdvice.java:633)\n	at org.springframework.aop.aspectj.AspectJAroundAdvice.invoke(AspectJAroundAdvice.java:70)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:174)\n	at org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint.proceed(MethodInvocationProceedingJoinPoint.java:89)\n	at cn.stylefeng.guns.core.interceptor.SessionHolderInterceptor.sessionKit(SessionHolderInterceptor.java:44)\n	at sun.reflect.GeneratedMethodAccessor331.invoke(Unknown Source)\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n	at java.lang.reflect.Method.invoke(Method.java:498)\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethodWithGivenArgs(AbstractAspectJAdvice.java:644)\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethod(AbstractAspectJAdvice.java:633)\n	at org.springframework.aop.aspectj.AspectJAroundAdvice.invoke(AspectJAroundAdvice.java:70)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:185)\n	at org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:92)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:185)\n	at org.springframework.aop.framework.CglibAopProxyTDynamicAdvisedInterceptor.intercept(CglibAopProxy.java:689)\n	at cn.stylefeng.guns.modular.system.controller.UserMgrControllerTTEnhancerBySpringCGLIBTTc96507ec.edit(<generated>)\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n	at java.lang.reflect.Method.invoke(Method.java:498)\n	at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:209)\n	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:136)\n	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:102)\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:877)\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:783)\n	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)\n	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:991)\n	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:925)\n	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:974)\n	at org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:877)\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:661)\n	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:851)\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:742)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:231)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:52)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:61)\n	at org.apache.shiro.web.servlet.AdviceFilter.executeChain(AdviceFilter.java:108)\n	at org.apache.shiro.web.servlet.AdviceFilter.doFilterInternal(AdviceFilter.java:137)\n	at org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:125)\n	at org.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:66)\n	at org.apache.shiro.web.servlet.AbstractShiroFilter.executeChain(AbstractShiroFilter.java:449)\n	at org.apache.shiro.web.servlet.AbstractShiroFilterT1.call(AbstractShiroFilter.java:365)\n	at org.apache.shiro.subject.support.SubjectCallable.doCall(SubjectCallable.java:90)\n	at org.apache.shiro.subject.support.SubjectCallable.call(SubjectCallable.java:83)\n	at org.apache.shiro.subject.support.DelegatingSubject.execute(DelegatingSubject.java:387)\n	at org.apache.shiro.web.servlet.AbstractShiroFilter.doFilterInternal(AbstractShiroFilter.java:362)\n	at org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:125)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at cn.stylefeng.roses.core.xss.XssFilter.doFilter(XssFilter.java:31)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at com.alibaba.druid.support.http.WebStatFilter.doFilter(WebStatFilter.java:123)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:99)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.springframework.web.filter.HttpPutFormContentFilter.doFilterInternal(HttpPutFormContentFilter.java:109)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.springframework.web.filter.HiddenHttpMethodFilter.doFilterInternal(HiddenHttpMethodFilter.java:81)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:200)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:198)\n	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:96)\n	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:496)\n	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:140)\n	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:81)\n	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:87)\n	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:342)\n	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:803)\n	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:66)\n	at org.apache.coyote.AbstractProtocolTConnectionHandler.process(AbstractProtocol.java:790)\n	at org.apache.tomcat.util.net.NioEndpointTSocketProcessor.doRun(NioEndpoint.java:1459)\n	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)\n	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)\n	at java.util.concurrent.ThreadPoolExecutorTWorker.run(ThreadPoolExecutor.java:617)\n	at org.apache.tomcat.util.threads.TaskThreadTWrappingRunnable.run(TaskThread.java:61)\n	at java.lang.Thread.run(Thread.java:748)\n');
INSERT INTO `sys_operation_log` VALUES (649, '异常日志', '', 1, NULL, NULL, '2020-01-10 22:43:01', '失败', 'cn.stylefeng.roses.kernel.model.exception.ServiceException: 权限异常\n	at cn.stylefeng.guns.modular.system.controller.UserMgrController.edit(UserMgrController.java:240)\n	at cn.stylefeng.guns.modular.system.controller.UserMgrControllerTTFastClassBySpringCGLIBTT7c4c2edf.invoke(<generated>)\n	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:204)\n	at org.springframework.aop.framework.CglibAopProxyTCglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:747)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)\n	at org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint.proceed(MethodInvocationProceedingJoinPoint.java:89)\n	at cn.stylefeng.guns.core.aop.LogAop.recordSysLog(LogAop.java:60)\n	at sun.reflect.GeneratedMethodAccessor628.invoke(Unknown Source)\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n	at java.lang.reflect.Method.invoke(Method.java:498)\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethodWithGivenArgs(AbstractAspectJAdvice.java:644)\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethod(AbstractAspectJAdvice.java:633)\n	at org.springframework.aop.aspectj.AspectJAroundAdvice.invoke(AspectJAroundAdvice.java:70)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:174)\n	at org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint.proceed(MethodInvocationProceedingJoinPoint.java:89)\n	at cn.stylefeng.guns.core.interceptor.SessionHolderInterceptor.sessionKit(SessionHolderInterceptor.java:44)\n	at sun.reflect.GeneratedMethodAccessor331.invoke(Unknown Source)\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n	at java.lang.reflect.Method.invoke(Method.java:498)\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethodWithGivenArgs(AbstractAspectJAdvice.java:644)\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethod(AbstractAspectJAdvice.java:633)\n	at org.springframework.aop.aspectj.AspectJAroundAdvice.invoke(AspectJAroundAdvice.java:70)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:185)\n	at org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:92)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:185)\n	at org.springframework.aop.framework.CglibAopProxyTDynamicAdvisedInterceptor.intercept(CglibAopProxy.java:689)\n	at cn.stylefeng.guns.modular.system.controller.UserMgrControllerTTEnhancerBySpringCGLIBTTc96507ec.edit(<generated>)\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n	at java.lang.reflect.Method.invoke(Method.java:498)\n	at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:209)\n	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:136)\n	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:102)\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:877)\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:783)\n	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)\n	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:991)\n	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:925)\n	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:974)\n	at org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:877)\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:661)\n	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:851)\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:742)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:231)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:52)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:61)\n	at org.apache.shiro.web.servlet.AdviceFilter.executeChain(AdviceFilter.java:108)\n	at org.apache.shiro.web.servlet.AdviceFilter.doFilterInternal(AdviceFilter.java:137)\n	at org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:125)\n	at org.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:66)\n	at org.apache.shiro.web.servlet.AbstractShiroFilter.executeChain(AbstractShiroFilter.java:449)\n	at org.apache.shiro.web.servlet.AbstractShiroFilterT1.call(AbstractShiroFilter.java:365)\n	at org.apache.shiro.subject.support.SubjectCallable.doCall(SubjectCallable.java:90)\n	at org.apache.shiro.subject.support.SubjectCallable.call(SubjectCallable.java:83)\n	at org.apache.shiro.subject.support.DelegatingSubject.execute(DelegatingSubject.java:387)\n	at org.apache.shiro.web.servlet.AbstractShiroFilter.doFilterInternal(AbstractShiroFilter.java:362)\n	at org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:125)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at cn.stylefeng.roses.core.xss.XssFilter.doFilter(XssFilter.java:31)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at com.alibaba.druid.support.http.WebStatFilter.doFilter(WebStatFilter.java:123)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:99)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.springframework.web.filter.HttpPutFormContentFilter.doFilterInternal(HttpPutFormContentFilter.java:109)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.springframework.web.filter.HiddenHttpMethodFilter.doFilterInternal(HiddenHttpMethodFilter.java:81)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:200)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:198)\n	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:96)\n	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:496)\n	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:140)\n	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:81)\n	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:87)\n	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:342)\n	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:803)\n	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:66)\n	at org.apache.coyote.AbstractProtocolTConnectionHandler.process(AbstractProtocol.java:790)\n	at org.apache.tomcat.util.net.NioEndpointTSocketProcessor.doRun(NioEndpoint.java:1459)\n	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)\n	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)\n	at java.util.concurrent.ThreadPoolExecutorTWorker.run(ThreadPoolExecutor.java:617)\n	at org.apache.tomcat.util.threads.TaskThreadTWrappingRunnable.run(TaskThread.java:61)\n	at java.lang.Thread.run(Thread.java:748)\n');
INSERT INTO `sys_operation_log` VALUES (650, '异常日志', '', 1, NULL, NULL, '2020-01-10 22:43:47', '失败', 'cn.stylefeng.roses.kernel.model.exception.ServiceException: 权限异常\n	at cn.stylefeng.guns.modular.system.controller.UserMgrController.edit(UserMgrController.java:240)\n	at cn.stylefeng.guns.modular.system.controller.UserMgrControllerTTFastClassBySpringCGLIBTT7c4c2edf.invoke(<generated>)\n	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:204)\n	at org.springframework.aop.framework.CglibAopProxyTCglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:747)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)\n	at org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint.proceed(MethodInvocationProceedingJoinPoint.java:89)\n	at cn.stylefeng.guns.core.aop.LogAop.recordSysLog(LogAop.java:60)\n	at sun.reflect.GeneratedMethodAccessor628.invoke(Unknown Source)\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n	at java.lang.reflect.Method.invoke(Method.java:498)\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethodWithGivenArgs(AbstractAspectJAdvice.java:644)\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethod(AbstractAspectJAdvice.java:633)\n	at org.springframework.aop.aspectj.AspectJAroundAdvice.invoke(AspectJAroundAdvice.java:70)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:174)\n	at org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint.proceed(MethodInvocationProceedingJoinPoint.java:89)\n	at cn.stylefeng.guns.core.interceptor.SessionHolderInterceptor.sessionKit(SessionHolderInterceptor.java:44)\n	at sun.reflect.GeneratedMethodAccessor331.invoke(Unknown Source)\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n	at java.lang.reflect.Method.invoke(Method.java:498)\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethodWithGivenArgs(AbstractAspectJAdvice.java:644)\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethod(AbstractAspectJAdvice.java:633)\n	at org.springframework.aop.aspectj.AspectJAroundAdvice.invoke(AspectJAroundAdvice.java:70)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:185)\n	at org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:92)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:185)\n	at org.springframework.aop.framework.CglibAopProxyTDynamicAdvisedInterceptor.intercept(CglibAopProxy.java:689)\n	at cn.stylefeng.guns.modular.system.controller.UserMgrControllerTTEnhancerBySpringCGLIBTTc96507ec.edit(<generated>)\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n	at java.lang.reflect.Method.invoke(Method.java:498)\n	at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:209)\n	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:136)\n	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:102)\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:877)\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:783)\n	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)\n	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:991)\n	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:925)\n	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:974)\n	at org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:877)\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:661)\n	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:851)\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:742)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:231)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:52)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:61)\n	at org.apache.shiro.web.servlet.AdviceFilter.executeChain(AdviceFilter.java:108)\n	at org.apache.shiro.web.servlet.AdviceFilter.doFilterInternal(AdviceFilter.java:137)\n	at org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:125)\n	at org.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:66)\n	at org.apache.shiro.web.servlet.AbstractShiroFilter.executeChain(AbstractShiroFilter.java:449)\n	at org.apache.shiro.web.servlet.AbstractShiroFilterT1.call(AbstractShiroFilter.java:365)\n	at org.apache.shiro.subject.support.SubjectCallable.doCall(SubjectCallable.java:90)\n	at org.apache.shiro.subject.support.SubjectCallable.call(SubjectCallable.java:83)\n	at org.apache.shiro.subject.support.DelegatingSubject.execute(DelegatingSubject.java:387)\n	at org.apache.shiro.web.servlet.AbstractShiroFilter.doFilterInternal(AbstractShiroFilter.java:362)\n	at org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:125)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at cn.stylefeng.roses.core.xss.XssFilter.doFilter(XssFilter.java:31)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at com.alibaba.druid.support.http.WebStatFilter.doFilter(WebStatFilter.java:123)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:99)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.springframework.web.filter.HttpPutFormContentFilter.doFilterInternal(HttpPutFormContentFilter.java:109)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.springframework.web.filter.HiddenHttpMethodFilter.doFilterInternal(HiddenHttpMethodFilter.java:81)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:200)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:198)\n	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:96)\n	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:496)\n	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:140)\n	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:81)\n	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:87)\n	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:342)\n	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:803)\n	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:66)\n	at org.apache.coyote.AbstractProtocolTConnectionHandler.process(AbstractProtocol.java:790)\n	at org.apache.tomcat.util.net.NioEndpointTSocketProcessor.doRun(NioEndpoint.java:1459)\n	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)\n	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)\n	at java.util.concurrent.ThreadPoolExecutorTWorker.run(ThreadPoolExecutor.java:617)\n	at org.apache.tomcat.util.threads.TaskThreadTWrappingRunnable.run(TaskThread.java:61)\n	at java.lang.Thread.run(Thread.java:748)\n');
INSERT INTO `sys_operation_log` VALUES (651, '异常日志', '', 1, NULL, NULL, '2020-01-10 22:45:32', '失败', 'cn.stylefeng.roses.kernel.model.exception.ServiceException: 权限异常\n	at cn.stylefeng.guns.modular.system.controller.UserMgrController.edit(UserMgrController.java:240)\n	at cn.stylefeng.guns.modular.system.controller.UserMgrControllerTTFastClassBySpringCGLIBTT7c4c2edf.invoke(<generated>)\n	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:204)\n	at org.springframework.aop.framework.CglibAopProxyTCglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:747)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)\n	at org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint.proceed(MethodInvocationProceedingJoinPoint.java:89)\n	at cn.stylefeng.guns.core.aop.LogAop.recordSysLog(LogAop.java:60)\n	at sun.reflect.GeneratedMethodAccessor628.invoke(Unknown Source)\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n	at java.lang.reflect.Method.invoke(Method.java:498)\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethodWithGivenArgs(AbstractAspectJAdvice.java:644)\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethod(AbstractAspectJAdvice.java:633)\n	at org.springframework.aop.aspectj.AspectJAroundAdvice.invoke(AspectJAroundAdvice.java:70)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:174)\n	at org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint.proceed(MethodInvocationProceedingJoinPoint.java:89)\n	at cn.stylefeng.guns.core.interceptor.SessionHolderInterceptor.sessionKit(SessionHolderInterceptor.java:44)\n	at sun.reflect.GeneratedMethodAccessor331.invoke(Unknown Source)\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n	at java.lang.reflect.Method.invoke(Method.java:498)\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethodWithGivenArgs(AbstractAspectJAdvice.java:644)\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethod(AbstractAspectJAdvice.java:633)\n	at org.springframework.aop.aspectj.AspectJAroundAdvice.invoke(AspectJAroundAdvice.java:70)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:185)\n	at org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:92)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:185)\n	at org.springframework.aop.framework.CglibAopProxyTDynamicAdvisedInterceptor.intercept(CglibAopProxy.java:689)\n	at cn.stylefeng.guns.modular.system.controller.UserMgrControllerTTEnhancerBySpringCGLIBTTc96507ec.edit(<generated>)\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n	at java.lang.reflect.Method.invoke(Method.java:498)\n	at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:209)\n	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:136)\n	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:102)\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:877)\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:783)\n	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)\n	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:991)\n	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:925)\n	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:974)\n	at org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:877)\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:661)\n	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:851)\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:742)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:231)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:52)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:61)\n	at org.apache.shiro.web.servlet.AdviceFilter.executeChain(AdviceFilter.java:108)\n	at org.apache.shiro.web.servlet.AdviceFilter.doFilterInternal(AdviceFilter.java:137)\n	at org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:125)\n	at org.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:66)\n	at org.apache.shiro.web.servlet.AbstractShiroFilter.executeChain(AbstractShiroFilter.java:449)\n	at org.apache.shiro.web.servlet.AbstractShiroFilterT1.call(AbstractShiroFilter.java:365)\n	at org.apache.shiro.subject.support.SubjectCallable.doCall(SubjectCallable.java:90)\n	at org.apache.shiro.subject.support.SubjectCallable.call(SubjectCallable.java:83)\n	at org.apache.shiro.subject.support.DelegatingSubject.execute(DelegatingSubject.java:387)\n	at org.apache.shiro.web.servlet.AbstractShiroFilter.doFilterInternal(AbstractShiroFilter.java:362)\n	at org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:125)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at cn.stylefeng.roses.core.xss.XssFilter.doFilter(XssFilter.java:31)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at com.alibaba.druid.support.http.WebStatFilter.doFilter(WebStatFilter.java:123)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:99)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.springframework.web.filter.HttpPutFormContentFilter.doFilterInternal(HttpPutFormContentFilter.java:109)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.springframework.web.filter.HiddenHttpMethodFilter.doFilterInternal(HiddenHttpMethodFilter.java:81)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:200)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:198)\n	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:96)\n	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:496)\n	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:140)\n	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:81)\n	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:87)\n	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:342)\n	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:803)\n	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:66)\n	at org.apache.coyote.AbstractProtocolTConnectionHandler.process(AbstractProtocol.java:790)\n	at org.apache.tomcat.util.net.NioEndpointTSocketProcessor.doRun(NioEndpoint.java:1459)\n	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)\n	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)\n	at java.util.concurrent.ThreadPoolExecutorTWorker.run(ThreadPoolExecutor.java:617)\n	at org.apache.tomcat.util.threads.TaskThreadTWrappingRunnable.run(TaskThread.java:61)\n	at java.lang.Thread.run(Thread.java:748)\n');
INSERT INTO `sys_operation_log` VALUES (652, '业务日志', '修改管理员', 1, 'cn.stylefeng.guns.modular.system.controller.UserMgrController', 'edit', '2020-01-10 23:22:50', '成功', '账号=manager;;;字段名称:null,旧值:b53cac62e7175637d4beb3b16b2f7915,新值:');
INSERT INTO `sys_operation_log` VALUES (653, '业务日志', '分配角色', 1, 'cn.stylefeng.guns.modular.system.controller.UserMgrController', 'setRole', '2020-01-10 23:23:00', '成功', '账号=manager,角色名称集合=超级管理员,员工');
INSERT INTO `sys_operation_log` VALUES (654, '业务日志', '分配角色', 1, 'cn.stylefeng.guns.modular.system.controller.UserMgrController', 'setRole', '2020-01-10 23:23:19', '成功', '账号=manager,角色名称集合=员工');
INSERT INTO `sys_operation_log` VALUES (655, '业务日志', '修改管理员', 1, 'cn.stylefeng.guns.modular.system.controller.UserMgrController', 'edit', '2020-01-10 23:23:53', '成功', '账号=小明;;;字段名称:账号,旧值:test,新值:小明;;;字段名称:null,旧值:ed8000d22cf59b2bdab6ec846ed2cd1d,新值:;;;字段名称:名字,旧值:test,新值:小明');
INSERT INTO `sys_operation_log` VALUES (656, '业务日志', '修改管理员', 1, 'cn.stylefeng.guns.modular.system.controller.UserMgrController', 'edit', '2020-01-10 23:24:15', '成功', '账号=小明;;;字段名称:账号,旧值:test,新值:小明;;;字段名称:null,旧值:ed8000d22cf59b2bdab6ec846ed2cd1d,新值:');
INSERT INTO `sys_operation_log` VALUES (657, '业务日志', '修改管理员', 1, 'cn.stylefeng.guns.modular.system.controller.UserMgrController', 'edit', '2020-01-10 23:24:38', '成功', '账号=xiaoming;;;字段名称:账号,旧值:test,新值:xiaoming;;;字段名称:null,旧值:ed8000d22cf59b2bdab6ec846ed2cd1d,新值:');
INSERT INTO `sys_operation_log` VALUES (658, '业务日志', '删除管理员', 1, 'cn.stylefeng.guns.modular.system.controller.UserMgrController', 'delete', '2020-01-10 23:24:47', '成功', '账号=xx');
INSERT INTO `sys_operation_log` VALUES (659, '业务日志', '分配角色', 1, 'cn.stylefeng.guns.modular.system.controller.UserMgrController', 'setRole', '2020-01-10 23:24:56', '成功', '账号=test,角色名称集合=员工');
INSERT INTO `sys_operation_log` VALUES (660, '异常日志', '', 1, NULL, NULL, '2020-01-10 23:31:25', '失败', 'org.mybatis.spring.MyBatisSystemException: nested exception is org.apache.ibatis.binding.BindingException: Parameter \'list\' not found. Available parameters are [arg0, isAdmin, param1, param2]\n	at org.mybatis.spring.MyBatisExceptionTranslator.translateExceptionIfPossible(MyBatisExceptionTranslator.java:77)\n	at org.mybatis.spring.SqlSessionTemplateTSqlSessionInterceptor.invoke(SqlSessionTemplate.java:446)\n	at com.sun.proxy.TProxy72.selectList(Unknown Source)\n	at org.mybatis.spring.SqlSessionTemplate.selectList(SqlSessionTemplate.java:230)\n	at org.apache.ibatis.binding.MapperMethod.executeForMany(MapperMethod.java:139)\n	at org.apache.ibatis.binding.MapperMethod.execute(MapperMethod.java:76)\n	at org.apache.ibatis.binding.MapperProxy.invoke(MapperProxy.java:59)\n	at com.sun.proxy.TProxy103.menuTreeListByMenuIds(Unknown Source)\n	at cn.stylefeng.guns.modular.system.service.impl.MenuServiceImpl.menuTreeListByMenuIds(MenuServiceImpl.java:91)\n	at cn.stylefeng.guns.modular.system.service.impl.MenuServiceImplTTFastClassBySpringCGLIBTT5136b849.invoke(<generated>)\n	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:204)\n	at org.springframework.aop.framework.CglibAopProxyTCglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:747)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)\n	at com.alibaba.druid.support.spring.stat.DruidStatInterceptor.invoke(DruidStatInterceptor.java:72)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:185)\n	at org.springframework.aop.framework.CglibAopProxyTDynamicAdvisedInterceptor.intercept(CglibAopProxy.java:689)\n	at cn.stylefeng.guns.modular.system.service.impl.MenuServiceImplTTEnhancerBySpringCGLIBTT106bcfd1.menuTreeListByMenuIds(<generated>)\n	at cn.stylefeng.guns.modular.system.controller.MenuController.menuTreeListByRoleId(MenuController.java:231)\n	at cn.stylefeng.guns.modular.system.controller.MenuControllerTTFastClassBySpringCGLIBTT3bdef105.invoke(<generated>)\n	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:204)\n	at org.springframework.aop.framework.CglibAopProxyTCglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:747)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)\n	at org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint.proceed(MethodInvocationProceedingJoinPoint.java:89)\n	at cn.stylefeng.guns.core.interceptor.SessionHolderInterceptor.sessionKit(SessionHolderInterceptor.java:44)\n	at sun.reflect.GeneratedMethodAccessor215.invoke(Unknown Source)\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n	at java.lang.reflect.Method.invoke(Method.java:498)\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethodWithGivenArgs(AbstractAspectJAdvice.java:644)\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethod(AbstractAspectJAdvice.java:633)\n	at org.springframework.aop.aspectj.AspectJAroundAdvice.invoke(AspectJAroundAdvice.java:70)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:185)\n	at org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:92)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:185)\n	at org.springframework.aop.framework.CglibAopProxyTDynamicAdvisedInterceptor.intercept(CglibAopProxy.java:689)\n	at cn.stylefeng.guns.modular.system.controller.MenuControllerTTEnhancerBySpringCGLIBTT57c8049d.menuTreeListByRoleId(<generated>)\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n	at java.lang.reflect.Method.invoke(Method.java:498)\n	at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:209)\n	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:136)\n	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:102)\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:877)\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:783)\n	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)\n	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:991)\n	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:925)\n	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:974)\n	at org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:877)\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:661)\n	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:851)\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:742)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:231)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:52)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:61)\n	at org.apache.shiro.web.servlet.AdviceFilter.executeChain(AdviceFilter.java:108)\n	at org.apache.shiro.web.servlet.AdviceFilter.doFilterInternal(AdviceFilter.java:137)\n	at org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:125)\n	at org.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:66)\n	at org.apache.shiro.web.servlet.AbstractShiroFilter.executeChain(AbstractShiroFilter.java:449)\n	at org.apache.shiro.web.servlet.AbstractShiroFilterT1.call(AbstractShiroFilter.java:365)\n	at org.apache.shiro.subject.support.SubjectCallable.doCall(SubjectCallable.java:90)\n	at org.apache.shiro.subject.support.SubjectCallable.call(SubjectCallable.java:83)\n	at org.apache.shiro.subject.support.DelegatingSubject.execute(DelegatingSubject.java:387)\n	at org.apache.shiro.web.servlet.AbstractShiroFilter.doFilterInternal(AbstractShiroFilter.java:362)\n	at org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:125)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at cn.stylefeng.roses.core.xss.XssFilter.doFilter(XssFilter.java:31)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at com.alibaba.druid.support.http.WebStatFilter.doFilter(WebStatFilter.java:123)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:99)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.springframework.web.filter.HttpPutFormContentFilter.doFilterInternal(HttpPutFormContentFilter.java:109)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.springframework.web.filter.HiddenHttpMethodFilter.doFilterInternal(HiddenHttpMethodFilter.java:81)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:200)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:198)\n	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:96)\n	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:496)\n	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:140)\n	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:81)\n	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:87)\n	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:342)\n	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:803)\n	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:66)\n	at org.apache.coyote.AbstractProtocolTConnectionHandler.process(AbstractProtocol.java:790)\n	at org.apache.tomcat.util.net.NioEndpointTSocketProcessor.doRun(NioEndpoint.java:1459)\n	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)\n	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)\n	at java.util.concurrent.ThreadPoolExecutorTWorker.run(ThreadPoolExecutor.java:617)\n	at org.apache.tomcat.util.threads.TaskThreadTWrappingRunnable.run(TaskThread.java:61)\n	at java.lang.Thread.run(Thread.java:748)\nCaused by: org.apache.ibatis.binding.BindingException: Parameter \'list\' not found. Available parameters are [arg0, isAdmin, param1, param2]\n	at org.apache.ibatis.binding.MapperMethodTParamMap.get(MapperMethod.java:204)\n	at org.apache.ibatis.scripting.xmltags.DynamicContextTContextAccessor.getProperty(DynamicContext.java:115)\n	at org.apache.ibatis.ognl.OgnlRuntime.getProperty(OgnlRuntime.java:2685)\n	at org.apache.ibatis.ognl.ASTProperty.getValueBody(ASTProperty.java:114)\n	at org.apache.ibatis.ognl.SimpleNode.evaluateGetValueBody(SimpleNode.java:212)\n	at org.apache.ibatis.ognl.SimpleNode.getValue(SimpleNode.java:258)\n	at org.apache.ibatis.ognl.Ognl.getValue(Ognl.java:470)\n	at org.apache.ibatis.ognl.Ognl.getValue(Ognl.java:434)\n	at org.apache.ibatis.scripting.xmltags.OgnlCache.getValue(OgnlCache.java:44)\n	at org.apache.ibatis.scripting.xmltags.ExpressionEvaluator.evaluateIterable(ExpressionEvaluator.java:43)\n	at org.apache.ibatis.scripting.xmltags.ForEachSqlNode.apply(ForEachSqlNode.java:55)\n	at org.apache.ibatis.scripting.xmltags.MixedSqlNode.apply(MixedSqlNode.java:33)\n	at org.apache.ibatis.scripting.xmltags.DynamicSqlSource.getBoundSql(DynamicSqlSource.java:41)\n	at org.apache.ibatis.mapping.MappedStatement.getBoundSql(MappedStatement.java:292)\n	at org.apache.ibatis.executor.CachingExecutor.query(CachingExecutor.java:81)\n	at sun.reflect.GeneratedMethodAccessor182.invoke(Unknown Source)\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n	at java.lang.reflect.Method.invoke(Method.java:498)\n	at org.apache.ibatis.plugin.Plugin.invoke(Plugin.java:63)\n	at com.sun.proxy.TProxy152.query(Unknown Source)\n	at org.apache.ibatis.session.defaults.DefaultSqlSession.selectList(DefaultSqlSession.java:148)\n	at org.apache.ibatis.session.defaults.DefaultSqlSession.selectList(DefaultSqlSession.java:141)\n	at sun.reflect.GeneratedMethodAccessor200.invoke(Unknown Source)\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n	at java.lang.reflect.Method.invoke(Method.java:498)\n	at org.mybatis.spring.SqlSessionTemplateTSqlSessionInterceptor.invoke(SqlSessionTemplate.java:433)\n	... 107 more\n');
INSERT INTO `sys_operation_log` VALUES (661, '业务日志', '分配角色', 45, 'cn.stylefeng.guns.modular.system.controller.UserMgrController', 'setRole', '2020-01-11 00:37:31', '成功', '账号=boss,角色名称集合=boos');
INSERT INTO `sys_operation_log` VALUES (662, '异常日志', '', 1, NULL, NULL, '2020-01-13 16:14:06', '失败', 'java.lang.NullPointerException\n	at cn.stylefeng.roses.core.util.ToolUtil.getWebRootPath(ToolUtil.java:281)\n	at cn.stylefeng.guns.generator.modular.factory.DefaultTemplateFactory.getDefaultParams(DefaultTemplateFactory.java:43)\n	at cn.stylefeng.guns.generator.modular.controller.CodeController.blackboard(CodeController.java:40)\n	at cn.stylefeng.guns.generator.modular.controller.CodeControllerTTFastClassBySpringCGLIBTT55266069.invoke(<generated>)\n	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:204)\n	at org.springframework.aop.framework.CglibAopProxyTCglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:747)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)\n	at org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint.proceed(MethodInvocationProceedingJoinPoint.java:89)\n	at cn.stylefeng.guns.core.interceptor.SessionHolderInterceptor.sessionKit(SessionHolderInterceptor.java:44)\n	at sun.reflect.GeneratedMethodAccessor517.invoke(Unknown Source)\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n	at java.lang.reflect.Method.invoke(Method.java:498)\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethodWithGivenArgs(AbstractAspectJAdvice.java:644)\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethod(AbstractAspectJAdvice.java:633)\n	at org.springframework.aop.aspectj.AspectJAroundAdvice.invoke(AspectJAroundAdvice.java:70)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:185)\n	at org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:92)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:185)\n	at org.springframework.aop.framework.CglibAopProxyTDynamicAdvisedInterceptor.intercept(CglibAopProxy.java:689)\n	at cn.stylefeng.guns.generator.modular.controller.CodeControllerTTEnhancerBySpringCGLIBTT521546e3.blackboard(<generated>)\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n	at java.lang.reflect.Method.invoke(Method.java:498)\n	at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:209)\n	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:136)\n	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:102)\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:877)\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:783)\n	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)\n	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:991)\n	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:925)\n	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:974)\n	at org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:866)\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:635)\n	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:851)\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:742)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:231)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:52)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:61)\n	at org.apache.shiro.web.servlet.AdviceFilter.executeChain(AdviceFilter.java:108)\n	at org.apache.shiro.web.servlet.AdviceFilter.doFilterInternal(AdviceFilter.java:137)\n	at org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:125)\n	at org.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:66)\n	at org.apache.shiro.web.servlet.AbstractShiroFilter.executeChain(AbstractShiroFilter.java:449)\n	at org.apache.shiro.web.servlet.AbstractShiroFilterT1.call(AbstractShiroFilter.java:365)\n	at org.apache.shiro.subject.support.SubjectCallable.doCall(SubjectCallable.java:90)\n	at org.apache.shiro.subject.support.SubjectCallable.call(SubjectCallable.java:83)\n	at org.apache.shiro.subject.support.DelegatingSubject.execute(DelegatingSubject.java:387)\n	at org.apache.shiro.web.servlet.AbstractShiroFilter.doFilterInternal(AbstractShiroFilter.java:362)\n	at org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:125)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at cn.stylefeng.roses.core.xss.XssFilter.doFilter(XssFilter.java:31)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at com.alibaba.druid.support.http.WebStatFilter.doFilter(WebStatFilter.java:123)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:99)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.springframework.web.filter.HttpPutFormContentFilter.doFilterInternal(HttpPutFormContentFilter.java:109)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.springframework.web.filter.HiddenHttpMethodFilter.doFilterInternal(HiddenHttpMethodFilter.java:81)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:200)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:198)\n	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:96)\n	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:496)\n	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:140)\n	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:81)\n	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:87)\n	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:342)\n	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:803)\n	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:66)\n	at org.apache.coyote.AbstractProtocolTConnectionHandler.process(AbstractProtocol.java:790)\n	at org.apache.tomcat.util.net.NioEndpointTSocketProcessor.doRun(NioEndpoint.java:1459)\n	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)\n	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)\n	at java.util.concurrent.ThreadPoolExecutorTWorker.run(ThreadPoolExecutor.java:617)\n	at org.apache.tomcat.util.threads.TaskThreadTWrappingRunnable.run(TaskThread.java:61)\n	at java.lang.Thread.run(Thread.java:748)\n');

-- ----------------------------
-- Table structure for sys_relation
-- ----------------------------
DROP TABLE IF EXISTS `sys_relation`;
CREATE TABLE `sys_relation`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `menuid` bigint(11) NULL DEFAULT NULL COMMENT '菜单id',
  `roleid` int(11) NULL DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5236 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色和菜单关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_relation
-- ----------------------------
INSERT INTO `sys_relation` VALUES (5034, 105, 1);
INSERT INTO `sys_relation` VALUES (5035, 106, 1);
INSERT INTO `sys_relation` VALUES (5036, 107, 1);
INSERT INTO `sys_relation` VALUES (5037, 108, 1);
INSERT INTO `sys_relation` VALUES (5038, 109, 1);
INSERT INTO `sys_relation` VALUES (5039, 110, 1);
INSERT INTO `sys_relation` VALUES (5040, 111, 1);
INSERT INTO `sys_relation` VALUES (5041, 112, 1);
INSERT INTO `sys_relation` VALUES (5042, 113, 1);
INSERT INTO `sys_relation` VALUES (5043, 165, 1);
INSERT INTO `sys_relation` VALUES (5044, 166, 1);
INSERT INTO `sys_relation` VALUES (5045, 167, 1);
INSERT INTO `sys_relation` VALUES (5046, 114, 1);
INSERT INTO `sys_relation` VALUES (5047, 115, 1);
INSERT INTO `sys_relation` VALUES (5048, 116, 1);
INSERT INTO `sys_relation` VALUES (5049, 117, 1);
INSERT INTO `sys_relation` VALUES (5050, 118, 1);
INSERT INTO `sys_relation` VALUES (5051, 162, 1);
INSERT INTO `sys_relation` VALUES (5052, 163, 1);
INSERT INTO `sys_relation` VALUES (5053, 164, 1);
INSERT INTO `sys_relation` VALUES (5054, 119, 1);
INSERT INTO `sys_relation` VALUES (5055, 120, 1);
INSERT INTO `sys_relation` VALUES (5056, 121, 1);
INSERT INTO `sys_relation` VALUES (5057, 122, 1);
INSERT INTO `sys_relation` VALUES (5058, 150, 1);
INSERT INTO `sys_relation` VALUES (5059, 151, 1);
INSERT INTO `sys_relation` VALUES (5060, 128, 1);
INSERT INTO `sys_relation` VALUES (5061, 134, 1);
INSERT INTO `sys_relation` VALUES (5062, 158, 1);
INSERT INTO `sys_relation` VALUES (5063, 159, 1);
INSERT INTO `sys_relation` VALUES (5064, 130, 1);
INSERT INTO `sys_relation` VALUES (5065, 131, 1);
INSERT INTO `sys_relation` VALUES (5066, 135, 1);
INSERT INTO `sys_relation` VALUES (5067, 136, 1);
INSERT INTO `sys_relation` VALUES (5068, 137, 1);
INSERT INTO `sys_relation` VALUES (5069, 152, 1);
INSERT INTO `sys_relation` VALUES (5070, 153, 1);
INSERT INTO `sys_relation` VALUES (5071, 154, 1);
INSERT INTO `sys_relation` VALUES (5072, 132, 1);
INSERT INTO `sys_relation` VALUES (5073, 138, 1);
INSERT INTO `sys_relation` VALUES (5074, 139, 1);
INSERT INTO `sys_relation` VALUES (5075, 140, 1);
INSERT INTO `sys_relation` VALUES (5076, 155, 1);
INSERT INTO `sys_relation` VALUES (5077, 156, 1);
INSERT INTO `sys_relation` VALUES (5078, 157, 1);
INSERT INTO `sys_relation` VALUES (5079, 133, 1);
INSERT INTO `sys_relation` VALUES (5080, 160, 1);
INSERT INTO `sys_relation` VALUES (5081, 161, 1);
INSERT INTO `sys_relation` VALUES (5082, 141, 1);
INSERT INTO `sys_relation` VALUES (5083, 142, 1);
INSERT INTO `sys_relation` VALUES (5084, 143, 1);
INSERT INTO `sys_relation` VALUES (5085, 144, 1);
INSERT INTO `sys_relation` VALUES (5086, 145, 1);
INSERT INTO `sys_relation` VALUES (5087, 148, 1);
INSERT INTO `sys_relation` VALUES (5088, 168, 1);
INSERT INTO `sys_relation` VALUES (5089, 1191268370864762882, 1);
INSERT INTO `sys_relation` VALUES (5090, 1191268370864762883, 1);
INSERT INTO `sys_relation` VALUES (5091, 1191268370864762884, 1);
INSERT INTO `sys_relation` VALUES (5092, 1191268370864762885, 1);
INSERT INTO `sys_relation` VALUES (5093, 1191268370864762886, 1);
INSERT INTO `sys_relation` VALUES (5094, 1191268370864762887, 1);
INSERT INTO `sys_relation` VALUES (5095, 1197772074778439681, 1);
INSERT INTO `sys_relation` VALUES (5096, 1197772074778439682, 1);
INSERT INTO `sys_relation` VALUES (5097, 1197772074778439683, 1);
INSERT INTO `sys_relation` VALUES (5098, 1197772074778439684, 1);
INSERT INTO `sys_relation` VALUES (5099, 1197772074778439685, 1);
INSERT INTO `sys_relation` VALUES (5100, 1197772074778439686, 1);
INSERT INTO `sys_relation` VALUES (5101, 1197840048637984770, 1);
INSERT INTO `sys_relation` VALUES (5102, 1197840048637984771, 1);
INSERT INTO `sys_relation` VALUES (5103, 1197840048637984772, 1);
INSERT INTO `sys_relation` VALUES (5104, 1197840048637984773, 1);
INSERT INTO `sys_relation` VALUES (5105, 1197840048637984774, 1);
INSERT INTO `sys_relation` VALUES (5106, 1197840048637984775, 1);
INSERT INTO `sys_relation` VALUES (5107, 1198212272058929153, 1);
INSERT INTO `sys_relation` VALUES (5108, 1198212272058929154, 1);
INSERT INTO `sys_relation` VALUES (5109, 1198212272058929155, 1);
INSERT INTO `sys_relation` VALUES (5110, 1198212272058929156, 1);
INSERT INTO `sys_relation` VALUES (5111, 1198212272058929157, 1);
INSERT INTO `sys_relation` VALUES (5112, 1198212272058929158, 1);
INSERT INTO `sys_relation` VALUES (5113, 1202253396163579905, 1);
INSERT INTO `sys_relation` VALUES (5114, 1202253396163579906, 1);
INSERT INTO `sys_relation` VALUES (5115, 1202253396163579907, 1);
INSERT INTO `sys_relation` VALUES (5116, 1202253396163579908, 1);
INSERT INTO `sys_relation` VALUES (5117, 1202253396163579909, 1);
INSERT INTO `sys_relation` VALUES (5118, 1202253396163579910, 1);
INSERT INTO `sys_relation` VALUES (5119, 1207143149404512257, 1);
INSERT INTO `sys_relation` VALUES (5120, 1207143149404512258, 1);
INSERT INTO `sys_relation` VALUES (5121, 1207143149404512259, 1);
INSERT INTO `sys_relation` VALUES (5122, 1207143149412900865, 1);
INSERT INTO `sys_relation` VALUES (5123, 1207143149412900866, 1);
INSERT INTO `sys_relation` VALUES (5124, 1207143149412900867, 1);
INSERT INTO `sys_relation` VALUES (5125, 105, 5);
INSERT INTO `sys_relation` VALUES (5126, 106, 5);
INSERT INTO `sys_relation` VALUES (5127, 107, 5);
INSERT INTO `sys_relation` VALUES (5128, 108, 5);
INSERT INTO `sys_relation` VALUES (5129, 109, 5);
INSERT INTO `sys_relation` VALUES (5130, 110, 5);
INSERT INTO `sys_relation` VALUES (5131, 111, 5);
INSERT INTO `sys_relation` VALUES (5132, 112, 5);
INSERT INTO `sys_relation` VALUES (5133, 113, 5);
INSERT INTO `sys_relation` VALUES (5134, 165, 5);
INSERT INTO `sys_relation` VALUES (5135, 166, 5);
INSERT INTO `sys_relation` VALUES (5136, 167, 5);
INSERT INTO `sys_relation` VALUES (5137, 1202253396163579911, 5);
INSERT INTO `sys_relation` VALUES (5138, 114, 5);
INSERT INTO `sys_relation` VALUES (5139, 115, 5);
INSERT INTO `sys_relation` VALUES (5140, 116, 5);
INSERT INTO `sys_relation` VALUES (5141, 117, 5);
INSERT INTO `sys_relation` VALUES (5142, 118, 5);
INSERT INTO `sys_relation` VALUES (5143, 162, 5);
INSERT INTO `sys_relation` VALUES (5144, 163, 5);
INSERT INTO `sys_relation` VALUES (5145, 164, 5);
INSERT INTO `sys_relation` VALUES (5146, 119, 5);
INSERT INTO `sys_relation` VALUES (5147, 120, 5);
INSERT INTO `sys_relation` VALUES (5148, 121, 5);
INSERT INTO `sys_relation` VALUES (5149, 122, 5);
INSERT INTO `sys_relation` VALUES (5150, 150, 5);
INSERT INTO `sys_relation` VALUES (5151, 151, 5);
INSERT INTO `sys_relation` VALUES (5152, 131, 5);
INSERT INTO `sys_relation` VALUES (5153, 135, 5);
INSERT INTO `sys_relation` VALUES (5154, 136, 5);
INSERT INTO `sys_relation` VALUES (5155, 137, 5);
INSERT INTO `sys_relation` VALUES (5156, 152, 5);
INSERT INTO `sys_relation` VALUES (5157, 153, 5);
INSERT INTO `sys_relation` VALUES (5158, 154, 5);
INSERT INTO `sys_relation` VALUES (5159, 133, 5);
INSERT INTO `sys_relation` VALUES (5160, 160, 5);
INSERT INTO `sys_relation` VALUES (5161, 161, 5);
INSERT INTO `sys_relation` VALUES (5162, 145, 5);
INSERT INTO `sys_relation` VALUES (5163, 1191268370864762882, 5);
INSERT INTO `sys_relation` VALUES (5164, 1191268370864762883, 5);
INSERT INTO `sys_relation` VALUES (5165, 1191268370864762884, 5);
INSERT INTO `sys_relation` VALUES (5166, 1191268370864762885, 5);
INSERT INTO `sys_relation` VALUES (5167, 1191268370864762886, 5);
INSERT INTO `sys_relation` VALUES (5168, 1191268370864762887, 5);
INSERT INTO `sys_relation` VALUES (5169, 1197772074778439681, 5);
INSERT INTO `sys_relation` VALUES (5170, 1197772074778439682, 5);
INSERT INTO `sys_relation` VALUES (5171, 1197772074778439683, 5);
INSERT INTO `sys_relation` VALUES (5172, 1197772074778439684, 5);
INSERT INTO `sys_relation` VALUES (5173, 1197772074778439685, 5);
INSERT INTO `sys_relation` VALUES (5174, 1197772074778439686, 5);
INSERT INTO `sys_relation` VALUES (5175, 1197840048637984770, 5);
INSERT INTO `sys_relation` VALUES (5176, 1197840048637984771, 5);
INSERT INTO `sys_relation` VALUES (5177, 1197840048637984772, 5);
INSERT INTO `sys_relation` VALUES (5178, 1197840048637984773, 5);
INSERT INTO `sys_relation` VALUES (5179, 1197840048637984774, 5);
INSERT INTO `sys_relation` VALUES (5180, 1197840048637984775, 5);
INSERT INTO `sys_relation` VALUES (5181, 1198212272058929153, 5);
INSERT INTO `sys_relation` VALUES (5182, 1198212272058929154, 5);
INSERT INTO `sys_relation` VALUES (5183, 1198212272058929155, 5);
INSERT INTO `sys_relation` VALUES (5184, 1198212272058929156, 5);
INSERT INTO `sys_relation` VALUES (5185, 1198212272058929157, 5);
INSERT INTO `sys_relation` VALUES (5186, 1198212272058929158, 5);
INSERT INTO `sys_relation` VALUES (5187, 1202253396163579905, 5);
INSERT INTO `sys_relation` VALUES (5188, 1202253396163579906, 5);
INSERT INTO `sys_relation` VALUES (5189, 1202253396163579907, 5);
INSERT INTO `sys_relation` VALUES (5190, 1202253396163579908, 5);
INSERT INTO `sys_relation` VALUES (5191, 1202253396163579909, 5);
INSERT INTO `sys_relation` VALUES (5192, 1202253396163579910, 5);
INSERT INTO `sys_relation` VALUES (5193, 1207143149404512257, 5);
INSERT INTO `sys_relation` VALUES (5194, 1207143149404512258, 5);
INSERT INTO `sys_relation` VALUES (5195, 1207143149404512259, 5);
INSERT INTO `sys_relation` VALUES (5196, 1207143149412900865, 5);
INSERT INTO `sys_relation` VALUES (5197, 1207143149412900866, 5);
INSERT INTO `sys_relation` VALUES (5198, 1207143149412900867, 5);
INSERT INTO `sys_relation` VALUES (5199, 145, 6);
INSERT INTO `sys_relation` VALUES (5200, 1191268370864762882, 6);
INSERT INTO `sys_relation` VALUES (5201, 1191268370864762883, 6);
INSERT INTO `sys_relation` VALUES (5202, 1191268370864762884, 6);
INSERT INTO `sys_relation` VALUES (5203, 1191268370864762885, 6);
INSERT INTO `sys_relation` VALUES (5204, 1191268370864762886, 6);
INSERT INTO `sys_relation` VALUES (5205, 1191268370864762887, 6);
INSERT INTO `sys_relation` VALUES (5206, 1197772074778439681, 6);
INSERT INTO `sys_relation` VALUES (5207, 1197772074778439682, 6);
INSERT INTO `sys_relation` VALUES (5208, 1197772074778439683, 6);
INSERT INTO `sys_relation` VALUES (5209, 1197772074778439684, 6);
INSERT INTO `sys_relation` VALUES (5210, 1197772074778439685, 6);
INSERT INTO `sys_relation` VALUES (5211, 1197772074778439686, 6);
INSERT INTO `sys_relation` VALUES (5212, 1197840048637984770, 6);
INSERT INTO `sys_relation` VALUES (5213, 1197840048637984771, 6);
INSERT INTO `sys_relation` VALUES (5214, 1197840048637984772, 6);
INSERT INTO `sys_relation` VALUES (5215, 1197840048637984773, 6);
INSERT INTO `sys_relation` VALUES (5216, 1197840048637984774, 6);
INSERT INTO `sys_relation` VALUES (5217, 1197840048637984775, 6);
INSERT INTO `sys_relation` VALUES (5218, 1198212272058929153, 6);
INSERT INTO `sys_relation` VALUES (5219, 1198212272058929154, 6);
INSERT INTO `sys_relation` VALUES (5220, 1198212272058929155, 6);
INSERT INTO `sys_relation` VALUES (5221, 1198212272058929156, 6);
INSERT INTO `sys_relation` VALUES (5222, 1198212272058929157, 6);
INSERT INTO `sys_relation` VALUES (5223, 1198212272058929158, 6);
INSERT INTO `sys_relation` VALUES (5224, 1202253396163579905, 6);
INSERT INTO `sys_relation` VALUES (5225, 1202253396163579906, 6);
INSERT INTO `sys_relation` VALUES (5226, 1202253396163579907, 6);
INSERT INTO `sys_relation` VALUES (5227, 1202253396163579908, 6);
INSERT INTO `sys_relation` VALUES (5228, 1202253396163579909, 6);
INSERT INTO `sys_relation` VALUES (5229, 1202253396163579910, 6);
INSERT INTO `sys_relation` VALUES (5230, 1207143149404512257, 6);
INSERT INTO `sys_relation` VALUES (5231, 1207143149404512258, 6);
INSERT INTO `sys_relation` VALUES (5232, 1207143149404512259, 6);
INSERT INTO `sys_relation` VALUES (5233, 1207143149412900865, 6);
INSERT INTO `sys_relation` VALUES (5234, 1207143149412900866, 6);
INSERT INTO `sys_relation` VALUES (5235, 1207143149412900867, 6);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `num` int(11) NULL DEFAULT NULL COMMENT '序号',
  `pid` int(11) NULL DEFAULT NULL COMMENT '父角色id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `deptid` int(11) NULL DEFAULT NULL COMMENT '部门名称',
  `tips` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '提示',
  `version` int(11) NULL DEFAULT NULL COMMENT '保留字段(暂时没用）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, 1, 0, '超级管理员', 24, 'administrator', 1);
INSERT INTO `sys_role` VALUES (5, 2, 1, 'boos', 24, 'boosadmin', NULL);
INSERT INTO `sys_role` VALUES (6, NULL, 5, '员工', 24, 'op', NULL);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `account` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号',
  `password` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `salt` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'md5密码盐',
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名字',
  `birthday` datetime(0) NULL DEFAULT NULL COMMENT '生日',
  `sex` int(11) NULL DEFAULT NULL COMMENT '性别（1：男 2：女）',
  `email` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电子邮件',
  `phone` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `roleid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色id',
  `deptid` int(11) NULL DEFAULT NULL COMMENT '部门id',
  `status` int(11) NULL DEFAULT NULL COMMENT '状态(1：启用  2：冻结  3：删除）',
  `createtime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `version` int(11) NULL DEFAULT NULL COMMENT '保留字段',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 49 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '管理员表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'ae92a4b6-f357-4ca4-a6f2-c4f687af7685.jpg', 'admin', 'd1d77b8cf5e0fca3448c02bc58fdae78', '8pgby', 'ADMIN', '1995-05-18 00:00:00', 1, 'sn93@qq.com', '18200000000', '1', 0, 1, '2016-01-29 08:49:53', 25);
INSERT INTO `sys_user` VALUES (44, NULL, 'test', '45abb7879f6a8268f1ef600e6038ac73', 'ssts3', 'test', '2017-05-01 00:00:00', 1, 'abc@123.com', '', '5', 26, 3, '2017-05-16 20:33:37', NULL);
INSERT INTO `sys_user` VALUES (45, NULL, 'boss', 'a2f60f964eb03e9610f9a2f0a8e06a0b', '847te', 'CEO', '2017-12-04 00:00:00', 1, '', '', '5', 24, 1, '2017-12-04 22:24:02', NULL);
INSERT INTO `sys_user` VALUES (46, NULL, 'manager', 'b53cac62e7175637d4beb3b16b2f7915', 'j3cs9', '经理', '2017-12-04 00:00:00', 1, '', '', '6', 24, 1, '2017-12-04 22:24:24', NULL);
INSERT INTO `sys_user` VALUES (47, '', 'xx', '790624dd8811f51ec40ad3ecc456324d', 'plwjw', 'xx', '2020-01-10 00:00:00', 1, '', '', NULL, 25, 3, '2020-01-10 19:33:13', NULL);
INSERT INTO `sys_user` VALUES (48, '', 'test', 'ed8000d22cf59b2bdab6ec846ed2cd1d', 'ai0bq', '小明', '2020-01-10 00:00:00', 1, '', '', '6', 25, 1, '2020-01-10 21:46:37', NULL);

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test`  (
  `aaa` int(11) NOT NULL AUTO_INCREMENT,
  `bbb` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`aaa`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wx_banner
-- ----------------------------
DROP TABLE IF EXISTS `wx_banner`;
CREATE TABLE `wx_banner`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `img_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片地址',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `default_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '默认图片',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `card_id` int(11) NULL DEFAULT NULL COMMENT '名片id',
  `is_deleted` tinyint(3) NULL DEFAULT 0 COMMENT '是否删除（0否，1是）',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `update_by` varchar(225) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 190 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '轮播' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wx_call_center
-- ----------------------------
DROP TABLE IF EXISTS `wx_call_center`;
CREATE TABLE `wx_call_center`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '问题内容',
  `mobile` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `content_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内容图片',
  `reply_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '回复内容',
  `reply_time` datetime(0) NULL DEFAULT NULL COMMENT '回复时间',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `status` tinyint(3) NULL DEFAULT 0 COMMENT '状态（0处理中，1已处理）',
  `valuation_level` tinyint(5) NULL DEFAULT 5 COMMENT '评估等级',
  `is_deleted` tinyint(3) NULL DEFAULT 0 COMMENT '是否删除（0否，1是）',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '客服中心记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wx_call_center
-- ----------------------------
INSERT INTO `wx_call_center` VALUES (14, '11', '18321678888', 'http://https://img.xbdzmp.com/20200113035511b1c611c37f1d.jpg', NULL, NULL, 55, 0, 5, 0, '2020-01-13 15:55:14', '18321678888', '2020-01-13 15:55:14', NULL);
INSERT INTO `wx_call_center` VALUES (15, '66', '18321678888', 'http://https://img.xbdzmp.com/202001130356547b583922f3ad.jpg', '00', '2020-01-13 16:14:00', 55, 1, 3, 0, '2020-01-13 15:56:58', '18321678888', '2020-01-13 15:56:58', NULL);
INSERT INTO `wx_call_center` VALUES (16, '123', '18838214579', 'https://img.xbdzmp.com/202001130432248757604f8319.jpg', NULL, NULL, 54, 0, 5, 0, '2020-01-13 16:32:41', '18838214579', '2020-01-13 16:32:41', NULL);
INSERT INTO `wx_call_center` VALUES (17, '123', '18838214579', 'https://img.xbdzmp.com/2020011304335500bced6b4da3.jpg', NULL, NULL, 54, 0, 5, 0, '2020-01-13 16:34:10', '18838214579', '2020-01-13 16:34:10', NULL);
INSERT INTO `wx_call_center` VALUES (18, '123', '18838214579', 'https://img.xbdzmp.com/20200113043533d678f7e97a80.jpg', NULL, NULL, 54, 0, 5, 0, '2020-01-13 16:35:35', '18838214579', '2020-01-13 16:35:35', NULL);
INSERT INTO `wx_call_center` VALUES (19, '测试', '18838214579', 'https://img.xbdzmp.com/20200113043916eabf47491882.jpg', NULL, NULL, 54, 0, 5, 0, '2020-01-13 16:39:18', '18838214579', '2020-01-13 16:39:18', NULL);
INSERT INTO `wx_call_center` VALUES (20, '1233455', '18838214579', 'https://img.xbdzmp.com/202001130443322d8ff53a2e11.jpg', NULL, NULL, 56, 0, 5, 0, '2020-01-13 16:43:35', '18838214579', '2020-01-13 16:43:35', NULL);
INSERT INTO `wx_call_center` VALUES (21, '测试', '18838214579', 'https://img.xbdzmp.com/20200113045647c25e13d58f64.jpg', NULL, NULL, 54, 0, 5, 0, '2020-01-13 16:56:51', '18838214579', '2020-01-13 16:56:51', NULL);
INSERT INTO `wx_call_center` VALUES (22, '44', '18321678888', 'https://img.xbdzmp.com/20200113045838d22bc7f17bb5.jpg', NULL, NULL, 55, 0, 5, 0, '2020-01-13 16:58:42', '18321678888', '2020-01-13 16:58:42', NULL);

-- ----------------------------
-- Table structure for wx_card
-- ----------------------------
DROP TABLE IF EXISTS `wx_card`;
CREATE TABLE `wx_card`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `mobile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `company` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司',
  `position` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职位',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `fax` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '传真',
  `logo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'logo',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `type` tinyint(11) NULL DEFAULT 1 COMMENT '模版类型',
  `share_img_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分享图片',
  `share_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分享文案',
  `share_type` tinyint(3) NULL DEFAULT NULL COMMENT '分享类型',
  `is_deleted` tinyint(3) NULL DEFAULT 0 COMMENT '是否删除（0否，1是）',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `flag2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '小程序码',
  `flag3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段',
  `flag4` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 186 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '名片基础信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wx_card
-- ----------------------------
INSERT INTO `wx_card` VALUES (183, '付', '18838214579', '付', NULL, NULL, NULL, NULL, 'https://img.xbdzmp.com/202001130334294ebd58f0f295.jpg', 54, 1, NULL, NULL, NULL, 0, '2020-01-13 15:34:49', NULL, '2020-01-13 15:34:49', NULL, 'https://img.xbdzmp.com/code20200113033448232ee5356d56.jpg', NULL, NULL);
INSERT INTO `wx_card` VALUES (184, 'qunge', '18321678888', 'test', NULL, NULL, NULL, NULL, 'https://img.xbdzmp.com/202001130336123f3b2fe202b7.jpg', 55, 2, NULL, NULL, NULL, 0, '2020-01-13 15:36:49', NULL, '2020-01-13 15:36:49', NULL, 'https://img.xbdzmp.com/code20200113033647701c3bda5f08.jpg', NULL, NULL);
INSERT INTO `wx_card` VALUES (185, '付', '18838214579', '付', NULL, NULL, NULL, NULL, 'https://img.xbdzmp.com/202001130442571e421d7f91d3.jpg', 56, 1, NULL, NULL, NULL, 0, '2020-01-13 16:43:14', NULL, '2020-01-13 16:43:14', NULL, 'https://img.xbdzmp.com/code20200113044313fbf6306321ee.jpg', NULL, NULL);

-- ----------------------------
-- Table structure for wx_card_forward
-- ----------------------------
DROP TABLE IF EXISTS `wx_card_forward`;
CREATE TABLE `wx_card_forward`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `card_id` bigint(11) NULL DEFAULT NULL COMMENT '名片ID',
  `card_logo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名片logo图',
  `card_position` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名片职务',
  `card_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名片名称',
  `card_company` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名片公司',
  `type` tinyint(3) NULL DEFAULT 1 COMMENT '类型 1转发 2进入 3收藏',
  `user_id` bigint(11) NULL DEFAULT NULL COMMENT '转发/进入ID',
  `head_img` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参与人头像',
  `nickname` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参与人昵称',
  `forwarder_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '转发者头像',
  `forwarder_id` bigint(11) NULL DEFAULT NULL COMMENT '转发者ID',
  `openid` varchar(225) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微信openid',
  `company_id` bigint(11) NULL DEFAULT NULL COMMENT '公司ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_card_id`(`card_id`) USING BTREE,
  INDEX `idx_user_id`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 238 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '名片转发打开记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wx_card_forward
-- ----------------------------
INSERT INTO `wx_card_forward` VALUES (234, 183, NULL, NULL, NULL, NULL, 2, 55, 'https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83erz7jYrZibUKCmRD7CuNLG14Rvdz4ABWcuj57vDgY2609zeUWpR6rjKevQB1Nf4FOcpmXPyDnzTe5A/132', 'xīn yǒu líng xī', NULL, 54, 'o1xq25Eleed552v4FQ7FTliQV_ks', NULL, '2020-01-13 15:38:08');
INSERT INTO `wx_card_forward` VALUES (235, 183, 'https://img.xbdzmp.com/202001130334294ebd58f0f295.jpg', NULL, NULL, NULL, 3, 55, 'https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83erz7jYrZibUKCmRD7CuNLG14Rvdz4ABWcuj57vDgY2609zeUWpR6rjKevQB1Nf4FOcpmXPyDnzTe5A/132', 'xīn yǒu líng xī', NULL, 54, 'o1xq25Eleed552v4FQ7FTliQV_ks', NULL, '2020-01-13 15:38:09');
INSERT INTO `wx_card_forward` VALUES (236, 184, NULL, NULL, NULL, NULL, 2, 54, 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJDCzIXQRbicndLxBVXrFq4G9l6OicWRXiagsoFtiakJls0daMs4SBHTnAfkNWF4ucwHPW9icZwGOib2D2g/132', '算了吧！', NULL, 55, 'o1xq25GKRjOKaKPBB4YC5EaguvVI', NULL, '2020-01-13 15:39:31');
INSERT INTO `wx_card_forward` VALUES (237, 184, 'https://img.xbdzmp.com/202001130336123f3b2fe202b7.jpg', NULL, NULL, NULL, 3, 54, 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJDCzIXQRbicndLxBVXrFq4G9l6OicWRXiagsoFtiakJls0daMs4SBHTnAfkNWF4ucwHPW9icZwGOib2D2g/132', '算了吧！', NULL, 55, 'o1xq25GKRjOKaKPBB4YC5EaguvVI', NULL, '2020-01-13 15:39:45');

-- ----------------------------
-- Table structure for wx_category
-- ----------------------------
DROP TABLE IF EXISTS `wx_category`;
CREATE TABLE `wx_category`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `num` int(11) NULL DEFAULT NULL COMMENT '排序',
  `pid` int(11) NULL DEFAULT NULL COMMENT '父部门id',
  `pids` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父级ids',
  `simplename` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '简称',
  `fullname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '全称',
  `tips` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '提示',
  `version` int(11) NULL DEFAULT NULL COMMENT '版本（乐观锁保留字段）',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `card_id` int(11) NULL DEFAULT NULL COMMENT '名片id',
  `is_deleted` tinyint(3) NULL DEFAULT 0 COMMENT '是否删除（0否，1是）',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `flag1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段',
  `flag2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 119 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '类别' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wx_income_flowing
-- ----------------------------
DROP TABLE IF EXISTS `wx_income_flowing`;
CREATE TABLE `wx_income_flowing`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `merchant_fee` int(11) NULL DEFAULT 0 COMMENT '商户收入金额（分）',
  `merchant_total_fee` int(100) NULL DEFAULT 0 COMMENT '商户收入总金额（分）',
  `myself_fee` int(11) NULL DEFAULT 0 COMMENT '个人收入金额（分）',
  `myself_total_fee` int(50) NULL DEFAULT 0 COMMENT '个人收入总金额（分）',
  `card_id` int(11) NULL DEFAULT NULL COMMENT '名片id',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `customer_id` int(11) NULL DEFAULT NULL COMMENT '客户id',
  `openid` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微信用户标识',
  `body` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付描述',
  `out_trade_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内部订单号',
  `notify_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '通知地址',
  `package_value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付数据包',
  `pay_type` tinyint(3) NULL DEFAULT 1 COMMENT '支付类型（1微信，2支付宝，3银联）',
  `pay_status` tinyint(3) NULL DEFAULT 1 COMMENT '支付状态（1成功，2失败，3进行中）',
  `is_deleted` tinyint(3) NULL DEFAULT 0 COMMENT '是否删除（0否，1是）',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '收入流水' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wx_income_flowing
-- ----------------------------
INSERT INTO `wx_income_flowing` VALUES (26, 5800, 0, 2000, NULL, NULL, 54, 55, 'o1xq25F_g3NvvYZtIq3Q0d7WbiSk', '支付', '202001051801503401011712', '/pages/my/main', 'prepay_id=wx051852505830205a1f07b29d1082927100', 1, 1, 0, '2020-01-13 18:53:01', '13116858811', '2020-01-13 18:53:01', NULL);

-- ----------------------------
-- Table structure for wx_material
-- ----------------------------
DROP TABLE IF EXISTS `wx_material`;
CREATE TABLE `wx_material`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `pid` int(11) NULL DEFAULT 0 COMMENT '父级id',
  `pcode` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '父级编码',
  `img_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片地址',
  `price` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '价格',
  `default_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '默认图片',
  `simplename` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '简称',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `num` int(11) NULL DEFAULT NULL COMMENT '排序',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `card_id` int(11) NULL DEFAULT NULL COMMENT '名片id',
  `category_id` int(11) NULL DEFAULT 0 COMMENT '类别id',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编码',
  `is_deleted` tinyint(3) NULL DEFAULT 0 COMMENT '是否删除（0否，1是）',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `flag1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段',
  `flag2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 779 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '素材' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wx_material
-- ----------------------------
INSERT INTO `wx_material` VALUES (756, 0, '[0],', 'https://img.xbdzmp.com/2020011303400611baf5166e0d.jpg', NULL, '', NULL, '9', NULL, 55, 184, 0, NULL, 0, '2020-01-13 15:41:23', '18321678888', '2020-01-13 15:41:23', NULL, NULL, NULL);
INSERT INTO `wx_material` VALUES (757, 0, '[0],', 'https://img.xbdzmp.com/2020011303400663107b964535.jpg', NULL, '', NULL, '8', NULL, 55, 184, 0, NULL, 0, '2020-01-13 15:41:23', '18321678888', '2020-01-13 15:41:23', NULL, NULL, NULL);
INSERT INTO `wx_material` VALUES (758, 0, '[0],', 'https://img.xbdzmp.com/2020011303400643fa6d7368a9.jpg', NULL, '', NULL, '7', NULL, 55, 184, 0, NULL, 0, '2020-01-13 15:41:23', '18321678888', '2020-01-13 15:41:23', NULL, NULL, NULL);
INSERT INTO `wx_material` VALUES (759, 0, '[0],', 'https://img.xbdzmp.com/20200113034006c25424753d2f.jpg', NULL, '', NULL, '6', NULL, 55, 184, 0, NULL, 0, '2020-01-13 15:41:23', '18321678888', '2020-01-13 15:41:23', NULL, NULL, NULL);
INSERT INTO `wx_material` VALUES (760, 0, '[0],', 'https://img.xbdzmp.com/202001130340069ccdeaacb38d.jpg', NULL, '', NULL, '5', NULL, 55, 184, 0, NULL, 0, '2020-01-13 15:41:23', '18321678888', '2020-01-13 15:41:23', NULL, NULL, NULL);
INSERT INTO `wx_material` VALUES (761, 0, '[0],', 'https://img.xbdzmp.com/20200113034006512243f2d84c.jpg', NULL, '', NULL, '4', NULL, 55, 184, 0, NULL, 0, '2020-01-13 15:41:23', '18321678888', '2020-01-13 15:41:23', NULL, NULL, NULL);
INSERT INTO `wx_material` VALUES (762, 0, '[0],', 'https://img.xbdzmp.com/20200113034006c65d87568a4f.jpg', NULL, '', NULL, '3', NULL, 55, 184, 0, NULL, 0, '2020-01-13 15:41:23', '18321678888', '2020-01-13 15:41:23', NULL, NULL, NULL);
INSERT INTO `wx_material` VALUES (763, 0, '[0],', 'https://img.xbdzmp.com/20200113034006b737a8d6ccec.jpg', NULL, '', NULL, '2', NULL, 55, 184, 0, NULL, 0, '2020-01-13 15:41:24', '18321678888', '2020-01-13 15:41:24', NULL, NULL, NULL);
INSERT INTO `wx_material` VALUES (764, 0, '[0],', 'https://img.xbdzmp.com/202001130340061ed4426bc05e.jpg', NULL, '', NULL, '1', NULL, 55, 184, 0, NULL, 0, '2020-01-13 15:41:24', '18321678888', '2020-01-13 15:41:24', NULL, NULL, NULL);
INSERT INTO `wx_material` VALUES (766, 0, '[0],', 'https://img.xbdzmp.com/20200113034437fd6afb542e35.jpg', NULL, '', NULL, '10', NULL, 55, 184, 0, NULL, 0, '2020-01-13 15:44:46', '18321678888', '2020-01-13 15:44:46', NULL, NULL, NULL);
INSERT INTO `wx_material` VALUES (767, 766, '[0],[766],', 'https://img.xbdzmp.com/202001130410590f1436b17fc5.jpg', NULL, '', NULL, '1', NULL, 55, 184, 0, NULL, 0, '2020-01-13 16:11:13', '18321678888', '2020-01-13 16:11:13', NULL, NULL, NULL);
INSERT INTO `wx_material` VALUES (768, 766, '[0],[766],', 'https://img.xbdzmp.com/202001130410597945862a9468.jpg', NULL, '', NULL, '2', NULL, 55, 184, 0, NULL, 0, '2020-01-13 16:11:13', '18321678888', '2020-01-13 16:11:13', NULL, NULL, NULL);
INSERT INTO `wx_material` VALUES (769, 0, '[0],', 'https://img.xbdzmp.com/20200113043606b8224c9bde30.png', NULL, '', NULL, '', NULL, 54, 183, 0, NULL, 0, '2020-01-13 16:36:09', '18838214579', '2020-01-13 16:36:09', NULL, NULL, NULL);
INSERT INTO `wx_material` VALUES (770, 0, '[0],', 'https://img.xbdzmp.com/20200113043606c0dfb5426d26.jpg', NULL, '', NULL, '', NULL, 54, 183, 0, NULL, 0, '2020-01-13 16:36:09', '18838214579', '2020-01-13 16:36:09', NULL, NULL, NULL);
INSERT INTO `wx_material` VALUES (771, 0, '[0],', 'https://img.xbdzmp.com/20200113043606813d4cf426ce.jpg', NULL, '', NULL, '', NULL, 54, 183, 0, NULL, 0, '2020-01-13 16:36:09', '18838214579', '2020-01-13 16:36:09', NULL, NULL, NULL);
INSERT INTO `wx_material` VALUES (772, 0, '[0],', 'https://img.xbdzmp.com/20200113043606f7947309c02f.jpg', NULL, '', NULL, '', NULL, 54, 183, 0, NULL, 0, '2020-01-13 16:36:09', '18838214579', '2020-01-13 16:36:09', NULL, NULL, NULL);
INSERT INTO `wx_material` VALUES (773, 0, '[0],', 'https://img.xbdzmp.com/2020011304360674e114c818ca.png', NULL, '', NULL, '', NULL, 54, 183, 0, NULL, 0, '2020-01-13 16:36:09', '18838214579', '2020-01-13 16:36:09', NULL, NULL, NULL);
INSERT INTO `wx_material` VALUES (774, 0, '[0],', 'https://img.xbdzmp.com/20200113043606b1bfb9e33256.png', NULL, '', NULL, '', NULL, 54, 183, 0, NULL, 0, '2020-01-13 16:36:09', '18838214579', '2020-01-13 16:36:09', NULL, NULL, NULL);
INSERT INTO `wx_material` VALUES (775, 0, '[0],', 'https://img.xbdzmp.com/20200113043606744830bc52b3.png', NULL, '', NULL, '', NULL, 54, 183, 0, NULL, 0, '2020-01-13 16:36:09', '18838214579', '2020-01-13 16:36:09', NULL, NULL, NULL);
INSERT INTO `wx_material` VALUES (776, 0, '[0],', 'https://img.xbdzmp.com/202001130436062f3fdb4b5873.jpg', NULL, '', NULL, '', NULL, 54, 183, 0, NULL, 0, '2020-01-13 16:36:09', '18838214579', '2020-01-13 16:36:09', NULL, NULL, NULL);
INSERT INTO `wx_material` VALUES (777, 0, '[0],', 'https://img.xbdzmp.com/20200113043858af9ac85d1a36.jpg', NULL, '', NULL, '', NULL, 54, 183, 0, NULL, 0, '2020-01-13 16:36:20', '18838214579', '2020-01-13 16:36:20', NULL, NULL, NULL);
INSERT INTO `wx_material` VALUES (778, 0, '[0],', 'https://img.xbdzmp.com/202001130457169ed1caa91e47.jpg', NULL, '', NULL, '', NULL, 54, 183, 0, NULL, 0, '2020-01-13 16:36:27', '18838214579', '2020-01-13 16:36:27', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for wx_material_comment
-- ----------------------------
DROP TABLE IF EXISTS `wx_material_comment`;
CREATE TABLE `wx_material_comment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `material_id` int(11) NULL DEFAULT NULL COMMENT '素材id',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户(评论者)id',
  `head_img` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评论者头像',
  `nickname` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评论者昵称',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评论内容',
  `prase_count` int(11) NULL DEFAULT NULL COMMENT '点赞数',
  `status` tinyint(3) NULL DEFAULT 0 COMMENT '状态（0待审核，1通过 ，2不通过）',
  `is_deleted` tinyint(3) NULL DEFAULT 0 COMMENT '是否删除（0否，1是）',
  `card_id` int(11) NULL DEFAULT NULL COMMENT '名片id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `flag1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段',
  `flag2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '素材评论' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wx_material_comment_reply
-- ----------------------------
DROP TABLE IF EXISTS `wx_material_comment_reply`;
CREATE TABLE `wx_material_comment_reply`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `comment_id` int(11) NULL DEFAULT NULL COMMENT '评论id',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户(回复者)id',
  `head_img` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '回复者头像',
  `nickname` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '回复者昵称',
  `reply_user_id` int(11) NULL DEFAULT NULL COMMENT '被回复人id（回复张三,此处即为张三id）',
  `reply_nickname` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '被回复人昵称',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '回复内容',
  `prase_count` int(11) NULL DEFAULT NULL COMMENT '点赞数',
  `status` tinyint(3) NULL DEFAULT 0 COMMENT '状态（0待审核，1通过 ，2不通过）',
  `is_deleted` tinyint(3) NULL DEFAULT 0 COMMENT '是否删除（0否，1是）',
  `card_id` int(11) NULL DEFAULT NULL COMMENT '名片id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `flag1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段',
  `flag2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '素材评论回复' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wx_out_flowing
-- ----------------------------
DROP TABLE IF EXISTS `wx_out_flowing`;
CREATE TABLE `wx_out_flowing`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `out_fee` int(11) NULL DEFAULT 0 COMMENT '支出金额（分）',
  `card_id` int(11) NULL DEFAULT NULL COMMENT '名片id',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `openid` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微信用户标识',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支出描述',
  `partner_trade_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支出订单号',
  `spbill_create_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '提现机器Ip地址',
  `pay_type` tinyint(3) NULL DEFAULT 1 COMMENT '支出类型（1微信，2支付宝，3银联）',
  `pay_status` tinyint(3) NULL DEFAULT 1 COMMENT '支付状态（1成功，2失败，3进行中）',
  `is_deleted` tinyint(3) NULL DEFAULT 0 COMMENT '是否删除（0否，1是）',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '平台支出流水' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wx_promotion
-- ----------------------------
DROP TABLE IF EXISTS `wx_promotion`;
CREATE TABLE `wx_promotion`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `card_id` int(11) NULL DEFAULT NULL COMMENT '名片id',
  `is_deleted` tinyint(3) NULL DEFAULT 0 COMMENT '是否删除（0否，1是）',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `update_by` varchar(225) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '宣传文案' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wx_user
-- ----------------------------
DROP TABLE IF EXISTS `wx_user`;
CREATE TABLE `wx_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `emp_id` int(11) NULL DEFAULT NULL COMMENT '导购用户id',
  `flag1` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT 'pids',
  `mobile` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `isvip` tinyint(11) NULL DEFAULT NULL COMMENT '是否是会员（0否，1是）',
  `vip_start_time` datetime(0) NULL DEFAULT NULL COMMENT '成为vip时间',
  `vip_end_time` datetime(0) NULL DEFAULT NULL COMMENT 'vip结束时间',
  `openid` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微信openid',
  `card_id` int(11) NULL DEFAULT NULL COMMENT '当前绑定的名片id',
  `unionid` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微信unionid',
  `qrcode` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微信二维码',
  `nick_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `headimgurl` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户头像',
  `sex` tinyint(3) NULL DEFAULT NULL COMMENT '性别：（1:男性；2:女性；0:未知）',
  `channel` tinyint(4) NULL DEFAULT NULL COMMENT '渠道（0自主 ）',
  `country` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '国家',
  `province` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '省份',
  `city` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '城市',
  `area` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地区',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址详情',
  `language` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户的语言，简体中文为zh_CN',
  `is_deleted` tinyint(3) NULL DEFAULT 0 COMMENT '是否删除（0否，1是）',
  `freeze` tinyint(3) NULL DEFAULT 0 COMMENT '冻结 0未冻结 1已冻结',
  `last_login_time` datetime(0) NULL DEFAULT NULL COMMENT '最后登录时间',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '成为vip时间',
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `flag2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 58 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '微信用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wx_user
-- ----------------------------
INSERT INTO `wx_user` VALUES (54, 0, '[0],', '18838214579', 0, NULL, NULL, 'o1xq25GKRjOKaKPBB4YC5EaguvVI', 183, NULL, NULL, '算了吧！', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJDCzIXQRbicndLxBVXrFq4G9l6OicWRXiagsoFtiakJls0daMs4SBHTnAfkNWF4ucwHPW9icZwGOib2D2g/132', 1, NULL, 'China', 'Henan', 'Shangqiu', NULL, NULL, 'zh_CN', 0, 0, '2020-01-13 16:03:45', '2020-01-13 15:34:25', NULL, NULL, NULL, NULL);
INSERT INTO `wx_user` VALUES (55, 54, '[0],[54],', '18321678888', 1, '2020-01-13 16:09:49', '2021-01-13 16:09:52', 'o1xq25Eleed552v4FQ7FTliQV_ks', 184, NULL, NULL, 'xīn yǒu líng xī', 'https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83erz7jYrZibUKCmRD7CuNLG14Rvdz4ABWcuj57vDgY2609zeUWpR6rjKevQB1Nf4FOcpmXPyDnzTe5A/132', 1, NULL, 'China', 'Jiangsu', 'Xuzhou', NULL, NULL, 'zh_CN', 0, 0, '2020-01-13 15:35:38', '2020-01-13 15:35:38', NULL, NULL, NULL, NULL);
INSERT INTO `wx_user` VALUES (56, 0, '[0],', '18838214579', 0, NULL, NULL, 'o1xq25OruDaAc-8P4huJJit0Etjo', 185, NULL, NULL, '小林', 'https://wx.qlogo.cn/mmopen/vi_32/yK7HiabCicGCtpHGEQ3fNSibBsrA42XiaicrlDhyomkD9IF6lxlibiabUDVIea5BlmNRiaZB4D8Jdvk2OLmQJDLm3K6rnA/132', 1, NULL, 'Andorra', '', '', NULL, NULL, 'zh_CN', 0, 0, '2020-01-13 16:42:51', '2020-01-13 16:42:51', NULL, NULL, NULL, NULL);
INSERT INTO `wx_user` VALUES (57, 0, '[0],', NULL, 0, NULL, NULL, 'o1xq25JpA8YQL8kzHcQMAeXU4Rrk', NULL, NULL, NULL, '王子13116858811', 'https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqYmZyqtXbpSKpTY5QxIG4cvicgcSOvcUzibKQTYar0s9rr4jh3Tq0iaiaq92U2m7GmsIhUaCfBMnicFKA/132', 1, NULL, 'China', 'Zhejiang', 'Shaoxing', NULL, NULL, 'zh_CN', 0, 0, '2020-01-13 17:06:13', '2020-01-13 17:06:13', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for wx_user_achievement
-- ----------------------------
DROP TABLE IF EXISTS `wx_user_achievement`;
CREATE TABLE `wx_user_achievement`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` bigint(11) NULL DEFAULT NULL COMMENT '用户id',
  `customer` int(10) NULL DEFAULT 0 COMMENT '客户数',
  `all_customer` int(10) NULL DEFAULT 0 COMMENT '客户总数',
  `transfer` int(10) NULL DEFAULT 0 COMMENT '转发次数',
  `all_transfer` int(10) NULL DEFAULT 0 COMMENT '转发总次数',
  `enter` int(10) NULL DEFAULT 0 COMMENT '进店次数',
  `all_enter` int(10) NULL DEFAULT 0 COMMENT '进店总次数',
  `amount` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '成交金额',
  `all_amount` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '累计成交金额',
  `quantity` int(10) NULL DEFAULT 0 COMMENT '成交笔数',
  `all_quantity` int(10) NULL DEFAULT 0 COMMENT '累计成交笔数',
  `commission` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '今日提成',
  `all_commission` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '总提成',
  `date_time` date NULL DEFAULT NULL COMMENT '汇总日期',
  `puser_id` bigint(11) NULL DEFAULT NULL COMMENT '父级用户id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uni_user_id_date_time`(`user_id`, `date_time`) USING BTREE,
  INDEX `idx_user_id`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '员工业绩表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
