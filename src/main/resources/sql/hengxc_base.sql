CREATE TABLE `t_base_dept` (
`DEPT_ID` bigint(20) NOT NULL COMMENT '部门ID',
`PARENT_ID` bigint(20) NOT NULL COMMENT '上级部门ID',
`DEPT_NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '部门名称',
`ORDER_NUM` bigint(20) NULL DEFAULT NULL COMMENT '排序',
`CREATE_TIME` datetime NULL DEFAULT NULL COMMENT '创建时间',
`MODIFY_TIME` datetime NULL DEFAULT NULL COMMENT '修改时间',
PRIMARY KEY (`DEPT_ID`)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = '部门表'
ROW_FORMAT = Dynamic;
CREATE TABLE `t_eximport` (
`FIELD1` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字段1',
`FIELD2` int(11) NOT NULL COMMENT '字段2',
`FIELD3` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字段3',
`CREATE_TIME` datetime NOT NULL
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Excel导入导出测试'
ROW_FORMAT = Dynamic;
CREATE TABLE `t_job_task` (
`JOB_ID` bigint(20) NOT NULL COMMENT '任务id',
`BEAN_NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'spring bean名称',
`METHOD_NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '方法名',
`PARAMS` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数',
`CRON_EXPRESSION` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'cron表达式',
`STATUS` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '任务状态  0：正常  1：暂停',
`REMARK` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
`CREATE_TIME` datetime NULL DEFAULT NULL COMMENT '创建时间',
PRIMARY KEY (`JOB_ID`)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = '定时任务表'
ROW_FORMAT = Dynamic;
CREATE TABLE `t_job_task_log` (
`LOG_ID` bigint(20) NOT NULL COMMENT '任务日志id',
`JOB_ID` bigint(20) NOT NULL COMMENT '任务id',
`BEAN_NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'spring bean名称',
`METHOD_NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '方法名',
`PARAMS` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数',
`STATUS` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '任务状态    0：成功    1：失败',
`ERROR` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '失败信息',
`TIMES` decimal(11,0) NULL DEFAULT NULL COMMENT '耗时(单位：毫秒)',
`CREATE_TIME` datetime NULL DEFAULT NULL COMMENT '创建时间',
PRIMARY KEY (`LOG_ID`)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = '调度日志表'
ROW_FORMAT = Dynamic;
CREATE TABLE `t_base_log` (
`ID` bigint(20) NOT NULL COMMENT '日志ID',
`USERNAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作用户',
`OPERATION` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '操作内容',
`TIME` decimal(11,0) NULL DEFAULT NULL COMMENT '耗时',
`METHOD` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '操作方法',
`PARAMS` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '方法参数',
`IP` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作者IP',
`CREATE_TIME` datetime NULL DEFAULT NULL COMMENT '创建时间',
`location` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作地点',
PRIMARY KEY (`ID`)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = '操作日志表'
ROW_FORMAT = Dynamic;
CREATE TABLE `t_login_log` (
`ID` bigint(11) NOT NULL COMMENT 'id',
`USERNAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
`LOGIN_TIME` datetime NOT NULL COMMENT '登录时间',
`LOCATION` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录地点',
`IP` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'IP地址',
`SYSTEM` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作系统',
`BROWSER` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '浏览器',
PRIMARY KEY (`ID`)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = '登录日志表'
ROW_FORMAT = Dynamic;
CREATE TABLE `t_base_menu` (
`MENU_ID` bigint(20) NOT NULL COMMENT '菜单/按钮ID',
`PARENT_ID` bigint(20) NOT NULL COMMENT '上级菜单ID',
`MENU_NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单/按钮名称',
`URL` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单URL',
`PERMS` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '权限标识',
`ICON` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
`TYPE` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类型 0菜单 1按钮',
`ORDER_NUM` bigint(20) NULL DEFAULT NULL COMMENT '排序',
`CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
`MODIFY_TIME` datetime NULL DEFAULT NULL COMMENT '修改时间',
PRIMARY KEY (`MENU_ID`)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = '菜单表'
ROW_FORMAT = Dynamic;
CREATE TABLE `t_base_role` (
`ROLE_ID` bigint(20) NOT NULL COMMENT '角色ID',
`ROLE_NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
`REMARK` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色描述',
`CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
`MODIFY_TIME` datetime NULL DEFAULT NULL COMMENT '修改时间',
PRIMARY KEY (`ROLE_ID`)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = '角色表'
ROW_FORMAT = Dynamic;
CREATE TABLE `t_base_role_menu` (
`ROLE_ID` bigint(20) NOT NULL COMMENT '角色ID',
`MENU_ID` bigint(20) NOT NULL COMMENT '菜单/按钮ID'
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = '角色菜单关联表'
ROW_FORMAT = Dynamic;
CREATE TABLE `t_base_user` (
`USER_ID` bigint(20) NOT NULL COMMENT '用户ID',
`USERNAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
`PASSWORD` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
`DEPT_ID` bigint(20) NULL DEFAULT NULL COMMENT '部门ID',
`EMAIL` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
`MOBILE` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
`STATUS` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '状态 0锁定 1有效',
`CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
`MODIFY_TIME` datetime NULL DEFAULT NULL COMMENT '修改时间',
`LAST_LOGIN_TIME` datetime NULL DEFAULT NULL COMMENT '最近访问时间',
`SSEX` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别 0男 1女 2保密',
`IS_TAB` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否开启tab，0关闭 1开启',
`THEME` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主题',
`AVATAR` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
`DESCRIPTION` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
PRIMARY KEY (`USER_ID`)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = '用户表'
ROW_FORMAT = Dynamic;
CREATE TABLE `t_base_user_role` (
`USER_ID` bigint(20) NOT NULL COMMENT '用户ID',
`ROLE_ID` bigint(20) NOT NULL COMMENT '角色ID'
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = '用户角色关联表'
ROW_FORMAT = Dynamic;
