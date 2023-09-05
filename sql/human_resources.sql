/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50635
 Source Host           : localhost:3306
 Source Schema         : human_resources

 Target Server Type    : MySQL
 Target Server Version : 50635
 File Encoding         : 65001

 Date: 04/09/2023 20:30:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for file_academic
-- ----------------------------
DROP TABLE IF EXISTS `file_academic`;
CREATE TABLE `file_academic`  (
  `a_id` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '职称id',
  `academic` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职称姓名',
  PRIMARY KEY (`a_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of file_academic
-- ----------------------------
INSERT INTO `file_academic` VALUES ('6948231856475213927', '博士');
INSERT INTO `file_academic` VALUES ('6948231856475213928', '硕士');
INSERT INTO `file_academic` VALUES ('6948231856475213929', '本科');
INSERT INTO `file_academic` VALUES ('6948231856475213954', '专科');
INSERT INTO `file_academic` VALUES ('6948231856475213955', '高中');
INSERT INTO `file_academic` VALUES ('6948231856475213956', '中专');
INSERT INTO `file_academic` VALUES ('6948231856475213957', '其他');

-- ----------------------------
-- Table structure for file_info
-- ----------------------------
DROP TABLE IF EXISTS `file_info`;
CREATE TABLE `file_info`  (
  `f_id` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '档案id',
  `u_id` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请人id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `sex` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `birth` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出生日期',
  `age` varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '年龄',
  `image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `profession_id` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职称id',
  `organizion_id` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '三级机构id',
  `nation_id` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '民族id',
  `academic_id` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学历id',
  `religion_id` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '宗教id',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `qq` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'QQ',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '居住地址',
  `personal_history` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '个人履历',
  `family_relationship` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '家庭关系信息',
  `remarks` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `is_deleted` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '逻辑删除【1true】',
  `is_check` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '复核情况【1true】',
  `is_reject` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否被驳回【1true】',
  PRIMARY KEY (`f_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of file_info
-- ----------------------------
INSERT INTO `file_info` VALUES ('1600330590672949249', '301069904247783480', 'cocochimp1', '女', '1999', '23', 'https://cocochimp-markdown-img.oss-cn-beijing.aliyuncs.com/secondhandshop/d9bda3f3-4f24-4cde-a978-4f0c9bfef463.jpg', '6948231856475213858', '301076316394881026', '6948231731531091977', '6948231856475213927', '6948211641926422546', '2427886409@qq.com', '13680826172', '242788', '广东省xxx', '个人履历', '家庭关系', '备注', '2022-12-07 11:25:19', '0', '0', '0');
INSERT INTO `file_info` VALUES ('1600386766802567169', '301069904247783481', 'cocochimp2', '男', '2000', '22', 'https://cocochimp-markdown-img.oss-cn-beijing.aliyuncs.com/secondhandshop/d9bda3f3-4f24-4cde-a978-4f0c9bfef463.jpg', '6948231856475213859', '301076316394881027', '6948231731531091978', '6948231856475213928', '6948211641926422547', '2427886409@qq.com', '13680826172', '242788', '广东省xxx', '个人履历', '家庭关系', '备注', '2022-12-07 15:08:32', '0', '0', '0');
INSERT INTO `file_info` VALUES ('1600424492339875842', '301069904247783482', 'cocochimp3', '女', '2001', '21', 'https://cocochimp-markdown-img.oss-cn-beijing.aliyuncs.com/secondhandshop/e8115db8-dac2-407b-a618-32fd339bd168.jpg', '6948231856475213860', '301076316394881028', '6948231731531091979', '6948231856475213929', '6948211641926422548', '2427886409@qq.com', '13680826172', '242788', '广东省xxx', '个人履历', '家庭关系', '备注', '2022-12-07 17:38:20', '0', '0', '0');
INSERT INTO `file_info` VALUES ('1600424492339875849', '301415440381378560', 'cocochimp4', '男', '2002', '20', 'https://cocochimp-markdown-img.oss-cn-beijing.aliyuncs.com/secondhandshop/d9bda3f3-4f24-4cde-a978-4f0c9bfef463.jpg', '6948236297643364352', '301076316394881033', '6948231731531091980', '6948231856475213954', '6948211641926422549', '10086@qq.com', '10086', '242788', '广东省xxx', '个人履历', '家庭关系', '备注', '2022-12-02 17:38:20', '0', '0', '0');
INSERT INTO `file_info` VALUES ('302331257717522432', '301069904247783482', 'cocochimp5', '男', '2002', '20', 'https://cocochimp-markdown-img.oss-cn-beijing.aliyuncs.com/secondhandshop/64cfe05c-af01-422c-8d8b-9bd1f71e80cf.png', '6948231856475213858', '301076316394881027', '6948231731531091978', '6948231856475213928', '6948211641926422547', '2427886409@qq.com', '13680826172', '242788', '广东省xxx', '个人履历', '家庭关系', '备注', '2022-12-10 21:39:33', '0', '0', '0');
INSERT INTO `file_info` VALUES ('302331257717522438', '301069904247783482', 'cocochimp6', '男', '2002', '20', 'https://cocochimp-markdown-img.oss-cn-beijing.aliyuncs.com/secondhandshop/64cfe05c-af01-422c-8d8b-9bd1f71e80cf.png', '6948231856475213859', '301076316394881028', '6948231731531091979', '6948231856475213929', '6948211641926422548', '2427886409@qq.com', '13680826172', '242788', '广东省xxx', '个人履历', '家庭关系', '备注', '2022-12-10 21:39:33', '0', '1', '0');
INSERT INTO `file_info` VALUES ('302331257717522439', '301069904247783481', '小明', '男', '2002', '20', 'https://cocochimp-markdown-img.oss-cn-beijing.aliyuncs.com/secondhandshop/64cfe05c-af01-422c-8d8b-9bd1f71e80cf.png', '6948231856475213859', '301076316394881028', '6948231731531091979', '6948231856475213929', '6948211641926422548', '2427886409@qq.com', '13680826172', '242788', '广东省xxx', '个人履历', '家庭关系', '备注', '2022-12-10 21:39:33', '0', '0', '0');

-- ----------------------------
-- Table structure for file_nation
-- ----------------------------
DROP TABLE IF EXISTS `file_nation`;
CREATE TABLE `file_nation`  (
  `n_id` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '民族id',
  `nation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '民族',
  PRIMARY KEY (`n_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of file_nation
-- ----------------------------
INSERT INTO `file_nation` VALUES ('6948231731531091977', '汉族');
INSERT INTO `file_nation` VALUES ('6948231731531091978', '朝鲜族');
INSERT INTO `file_nation` VALUES ('6948231731531091979', '满族');
INSERT INTO `file_nation` VALUES ('6948231731531091980', '侗族');
INSERT INTO `file_nation` VALUES ('6948231731531091981', '瑶族');
INSERT INTO `file_nation` VALUES ('6948231731531091982', '白族');
INSERT INTO `file_nation` VALUES ('6948231731531091983', '土家族');
INSERT INTO `file_nation` VALUES ('6948231731531091984', '哈尼族');
INSERT INTO `file_nation` VALUES ('6948231731531091985', '哈萨克族');
INSERT INTO `file_nation` VALUES ('6948231731531091986', '傣族');
INSERT INTO `file_nation` VALUES ('6948231731531091987', '黎族');
INSERT INTO `file_nation` VALUES ('6948231731531091988', '蒙古族');
INSERT INTO `file_nation` VALUES ('6948231731531091989', '傈僳族');
INSERT INTO `file_nation` VALUES ('6948231731531091990', '佤族');
INSERT INTO `file_nation` VALUES ('6948231731531091991', '畲族');
INSERT INTO `file_nation` VALUES ('6948231731531091992', '高山族');
INSERT INTO `file_nation` VALUES ('6948231731531091993', '拉祜族');
INSERT INTO `file_nation` VALUES ('6948231731531091994', '水族');
INSERT INTO `file_nation` VALUES ('6948231731531091995', '东乡族');
INSERT INTO `file_nation` VALUES ('6948231731531091996', '纳西族');
INSERT INTO `file_nation` VALUES ('6948231731531091997', '景颇族');
INSERT INTO `file_nation` VALUES ('6948231731531091998', '柯尔克孜族');
INSERT INTO `file_nation` VALUES ('6948231731531091999', '回族');
INSERT INTO `file_nation` VALUES ('6948231731531092000', '土族');
INSERT INTO `file_nation` VALUES ('6948231731531092001', '达斡尔族');
INSERT INTO `file_nation` VALUES ('6948231731531092002', '仫佬族');
INSERT INTO `file_nation` VALUES ('6948231731531092003', '羌族');
INSERT INTO `file_nation` VALUES ('6948231731531092004', '布朗族');
INSERT INTO `file_nation` VALUES ('6948231731531092005', '撒拉族');
INSERT INTO `file_nation` VALUES ('6948231731531092006', '毛难族');
INSERT INTO `file_nation` VALUES ('6948231731531092007', '仡佬族');
INSERT INTO `file_nation` VALUES ('6948231731531092008', '锡伯族');
INSERT INTO `file_nation` VALUES ('6948231731531092009', '阿昌族');
INSERT INTO `file_nation` VALUES ('6948231731531092010', '藏族');
INSERT INTO `file_nation` VALUES ('6948231731531092011', '普米族');
INSERT INTO `file_nation` VALUES ('6948231731531092012', '塔吉克族');
INSERT INTO `file_nation` VALUES ('6948231731531092013', '怒族');
INSERT INTO `file_nation` VALUES ('6948231731531092014', '乌孜别克族');
INSERT INTO `file_nation` VALUES ('6948231731531092015', '俄罗斯族');
INSERT INTO `file_nation` VALUES ('6948231731531092016', '鄂温克族');
INSERT INTO `file_nation` VALUES ('6948231856471019520', '崩龙族');
INSERT INTO `file_nation` VALUES ('6948231856471019521', '保安族');
INSERT INTO `file_nation` VALUES ('6948231856471019522', '裕固族');
INSERT INTO `file_nation` VALUES ('6948231856471019523', '京族');
INSERT INTO `file_nation` VALUES ('6948231856471019524', '维吾尔族');
INSERT INTO `file_nation` VALUES ('6948231856471019525', '塔塔尔族');
INSERT INTO `file_nation` VALUES ('6948231856471019526', '独龙族');
INSERT INTO `file_nation` VALUES ('6948231856471019527', '鄂伦春族');
INSERT INTO `file_nation` VALUES ('6948231856471019528', '赫哲族');
INSERT INTO `file_nation` VALUES ('6948231856471019529', '门巴族');
INSERT INTO `file_nation` VALUES ('6948231856471019530', '珞巴族');
INSERT INTO `file_nation` VALUES ('6948231856471019531', '基诺族');
INSERT INTO `file_nation` VALUES ('6948231856471019532', '苗族');
INSERT INTO `file_nation` VALUES ('6948231856475213824', '彝族');
INSERT INTO `file_nation` VALUES ('6948231856475213825', '壮族');
INSERT INTO `file_nation` VALUES ('6948231856475213826', '布依族');

-- ----------------------------
-- Table structure for file_profession
-- ----------------------------
DROP TABLE IF EXISTS `file_profession`;
CREATE TABLE `file_profession`  (
  `p_id` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '职称id',
  `profession` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职称',
  PRIMARY KEY (`p_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of file_profession
-- ----------------------------
INSERT INTO `file_profession` VALUES ('6948231856475213858', '高等学校教师');
INSERT INTO `file_profession` VALUES ('6948231856475213859', '教授');
INSERT INTO `file_profession` VALUES ('6948231856475213860', '副教授');
INSERT INTO `file_profession` VALUES ('6948236297643364352', '讲师（高校）');
INSERT INTO `file_profession` VALUES ('6948236297647558656', '助教（高校）');
INSERT INTO `file_profession` VALUES ('6948236297647558657', '未评定专业技术职务的高等学校教师');
INSERT INTO `file_profession` VALUES ('6948236297647558658', '科学研究人员');
INSERT INTO `file_profession` VALUES ('6948236297647558659', '研究员');
INSERT INTO `file_profession` VALUES ('6948236297647558660', '副研究员');
INSERT INTO `file_profession` VALUES ('6948236297647558661', '助理研究员');
INSERT INTO `file_profession` VALUES ('6948236297647558662', '研究实习员');
INSERT INTO `file_profession` VALUES ('6948236297647558663', '实验员');
INSERT INTO `file_profession` VALUES ('6948236297647558664', '实验人员');
INSERT INTO `file_profession` VALUES ('6948236297647558665', '高级实验师');
INSERT INTO `file_profession` VALUES ('6948236297647558666', '实验师');
INSERT INTO `file_profession` VALUES ('6948236297647558667', '助理实验师');
INSERT INTO `file_profession` VALUES ('6948236297647558668', '未评定专业技术职务的实验人员');
INSERT INTO `file_profession` VALUES ('6948236297647558669', '工程技术人员');
INSERT INTO `file_profession` VALUES ('6948236297647558670', '教授级高级工程师');
INSERT INTO `file_profession` VALUES ('6948236297647558671', '高级工程师');
INSERT INTO `file_profession` VALUES ('6948236297647558672', '工程师');
INSERT INTO `file_profession` VALUES ('6948236297647558673', '助理工程师');
INSERT INTO `file_profession` VALUES ('6948236297647558674', '技术员');
INSERT INTO `file_profession` VALUES ('6948236297647558675', '未评定专业技术职务的工程技术人员');
INSERT INTO `file_profession` VALUES ('6948236297647558676', '经济专业人员');
INSERT INTO `file_profession` VALUES ('6948236297647558677', '高级经济师');
INSERT INTO `file_profession` VALUES ('6948236297647558678', '经济师');
INSERT INTO `file_profession` VALUES ('6948236297647558679', '助理经济师');
INSERT INTO `file_profession` VALUES ('6948236297647558680', '经济员');
INSERT INTO `file_profession` VALUES ('6948236297647558681', '未评定专业技术职务的经济专业人员');
INSERT INTO `file_profession` VALUES ('6948236297647558682', '会计人员');
INSERT INTO `file_profession` VALUES ('6948236297647558683', '高级会计师');
INSERT INTO `file_profession` VALUES ('6948236297647558684', '会计师');
INSERT INTO `file_profession` VALUES ('6948236297647558685', '助理会计师');
INSERT INTO `file_profession` VALUES ('6948236297647558686', '会计员');
INSERT INTO `file_profession` VALUES ('6948236297647558687', '未评定专业技术职务的会计人员');
INSERT INTO `file_profession` VALUES ('6948236297647558688', '图书、文博、档案、资料人员');
INSERT INTO `file_profession` VALUES ('6948236297647558689', '研究馆员');
INSERT INTO `file_profession` VALUES ('6948236297647558690', '副研究馆员');
INSERT INTO `file_profession` VALUES ('6948236297647558691', '馆员');
INSERT INTO `file_profession` VALUES ('6948236297647558692', '助理馆员');
INSERT INTO `file_profession` VALUES ('6948236297647558693', '管理员');
INSERT INTO `file_profession` VALUES ('6948236297647558694', '卫生技术人员（医疗）');
INSERT INTO `file_profession` VALUES ('6948236297647558695', '主任医师');
INSERT INTO `file_profession` VALUES ('6948236297647558696', '副主任医师');
INSERT INTO `file_profession` VALUES ('6948236297647558697', '主治医师');
INSERT INTO `file_profession` VALUES ('6948236297647558698', '医师');
INSERT INTO `file_profession` VALUES ('6948236297647558699', '医士');
INSERT INTO `file_profession` VALUES ('6948236297647558700', '未评定专业技术职务的卫生技术人员（医疗）');
INSERT INTO `file_profession` VALUES ('6948236297647558701', '行政人员');
INSERT INTO `file_profession` VALUES ('6948236297647558702', '国家二级演员');
INSERT INTO `file_profession` VALUES ('6948236297647558703', '编审');
INSERT INTO `file_profession` VALUES ('6948236297647558704', '其他');

-- ----------------------------
-- Table structure for file_religion
-- ----------------------------
DROP TABLE IF EXISTS `file_religion`;
CREATE TABLE `file_religion`  (
  `r_id` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '宗教id',
  `religion` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '宗教',
  PRIMARY KEY (`r_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of file_religion
-- ----------------------------
INSERT INTO `file_religion` VALUES ('6948211641926422546', '佛教');
INSERT INTO `file_religion` VALUES ('6948211641926422547', '道教');
INSERT INTO `file_religion` VALUES ('6948211641926422548', '伊斯兰教');
INSERT INTO `file_religion` VALUES ('6948211641926422549', '天主教');
INSERT INTO `file_religion` VALUES ('6948211641926422550', '基督教');
INSERT INTO `file_religion` VALUES ('6948211641926422551', '无');

-- ----------------------------
-- Table structure for sal_info
-- ----------------------------
DROP TABLE IF EXISTS `sal_info`;
CREATE TABLE `sal_info`  (
  `s_id` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '薪酬id',
  `u_id` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登记人id',
  `o3_id` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '薪酬标准id',
  `f_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '制定人姓名',
  `allmoney` double(10, 2) NULL DEFAULT NULL COMMENT '总薪酬',
  `basic` double(10, 2) NULL DEFAULT NULL COMMENT '基本工资',
  `transport` double(10, 2) NULL DEFAULT NULL COMMENT '交通补贴',
  `lunch` double(10, 2) NULL DEFAULT NULL COMMENT '午餐补贴',
  `communication` double(10, 2) NULL DEFAULT NULL COMMENT '通信补贴',
  `pension` double(10, 2) NOT NULL COMMENT '养老保险',
  `unemployment` double(10, 2) NULL DEFAULT NULL COMMENT '失业保险',
  `medical` double(10, 2) NULL DEFAULT NULL COMMENT '医疗保险',
  `homemoney` double(10, 2) NULL DEFAULT NULL COMMENT '住房公积金',
  `review` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '复核意见',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `is_check` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '复核情况【1true】',
  `is_reject` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '是否被驳回【1true】',
  `is_give` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '发放情况【1true】',
  `is_deleted` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '逻辑删除【1true】',
  PRIMARY KEY (`s_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sal_info
-- ----------------------------
INSERT INTO `sal_info` VALUES ('302225751384522753', '301069904247783480', '301076316394881027', '小王1', 666.00, 666.00, 666.00, 666.00, 666.00, 666.00, 666.00, 666.00, 666.00, 'review1', '2022-12-10 15:58:32', '0', '1', '0', '0');
INSERT INTO `sal_info` VALUES ('302225751384522754', '301069904247783481', '301076316394881029', '小王2', 666.00, 666.00, 666.00, 666.00, 666.00, 666.00, 666.00, 666.00, 666.00, 'review2', '2022-12-10 15:29:33', '1', '0', '0', '0');
INSERT INTO `sal_info` VALUES ('302225751384522755', '301069904247783482', '301076316394881029', '小王3', 666.00, 666.00, 666.00, 666.00, 666.00, 666.00, 666.00, 666.00, 666.00, 'review3', '2022-12-10 15:29:36', '1', '0', '0', '0');
INSERT INTO `sal_info` VALUES ('302225751384522756', '301069904247783482', '301076316394881034', '小王4', 666.00, 666.00, 666.00, 666.00, 666.00, 666.00, 666.00, 666.00, 666.00, 'review4', '2022-12-10 15:29:38', '0', '0', '1', '0');
INSERT INTO `sal_info` VALUES ('302323905572372480', '301069904247783481', '301076316394881034', '小王5', 6.00, 6.00, 6.00, 6.00, 6.00, 6.00, 6.00, 6.00, 6.00, '6', '2022-12-10 21:10:21', '0', '0', '0', '1');
INSERT INTO `sal_info` VALUES ('304931105423753216', '301069904247783481', '301076316394881030', '小王6', 666.00, 666.00, 666.00, 66.60, 66.60, 53.28, 3.33, 16.32, 53.28, '工资', '2022-12-18 01:50:26', '1', '0', '0', '0');
INSERT INTO `sal_info` VALUES ('304956201601335296', '301069904247783480', '301076316394881031', '小王7', 186.64, 77.00, 77.00, 7.70, 7.70, 6.16, 0.38, 4.54, 6.16, '', '2022-12-18 03:30:09', '1', '0', '0', '0');
INSERT INTO `sal_info` VALUES ('305038947275243520', '301069904247783480', '301076316394881030', '小王8', 161.98, 66.66, 66.66, 6.67, 6.67, 5.33, 0.33, 4.33, 5.33, '', '2022-12-18 08:58:57', '1', '0', '0', '0');

-- ----------------------------
-- Table structure for sys_organ1
-- ----------------------------
DROP TABLE IF EXISTS `sys_organ1`;
CREATE TABLE `sys_organ1`  (
  `o1_id` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '一级机构id',
  `o1_name` varchar(19) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '一级机构名称',
  PRIMARY KEY (`o1_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_organ1
-- ----------------------------
INSERT INTO `sys_organ1` VALUES ('301074459173847110', '广东海洋大学');
INSERT INTO `sys_organ1` VALUES ('301074459173847111', '华为公司');
INSERT INTO `sys_organ1` VALUES ('301074459173847112', '中国数学会');
INSERT INTO `sys_organ1` VALUES ('305158422775463936', '666');

-- ----------------------------
-- Table structure for sys_organ2
-- ----------------------------
DROP TABLE IF EXISTS `sys_organ2`;
CREATE TABLE `sys_organ2`  (
  `o2_id` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '二级机构id',
  `o1_id` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上级机构id',
  `o2_name` varchar(19) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '二级机构名称',
  PRIMARY KEY (`o2_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_organ2
-- ----------------------------
INSERT INTO `sys_organ2` VALUES ('301074752624132100', '301074459173847110', '技术部1');
INSERT INTO `sys_organ2` VALUES ('301074752624132101', '301074459173847110', '科创部1');
INSERT INTO `sys_organ2` VALUES ('301074752624132102', '301074459173847110', '学习部1');
INSERT INTO `sys_organ2` VALUES ('301074752624132103', '301074459173847111', '宣传部2');
INSERT INTO `sys_organ2` VALUES ('301074752624132104', '301074459173847111', '组织部2');
INSERT INTO `sys_organ2` VALUES ('301074752624132105', '301074459173847111', '技能部2');
INSERT INTO `sys_organ2` VALUES ('301074752624132106', '301074459173847112', '财管部3');
INSERT INTO `sys_organ2` VALUES ('301074752624132107', '301074459173847112', '行政部3');
INSERT INTO `sys_organ2` VALUES ('301074752624132108', '301074459173847112', '活动部3');
INSERT INTO `sys_organ2` VALUES ('305158446225817600', '305158422775463936', '666');

-- ----------------------------
-- Table structure for sys_organ3
-- ----------------------------
DROP TABLE IF EXISTS `sys_organ3`;
CREATE TABLE `sys_organ3`  (
  `o3_id` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '三级机构id',
  `o2_id` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上级机构id',
  `o3_name` varchar(19) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '三级机构名称',
  PRIMARY KEY (`o3_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_organ3
-- ----------------------------
INSERT INTO `sys_organ3` VALUES ('301076316394881025', '301074752624132100', '技术组1');
INSERT INTO `sys_organ3` VALUES ('301076316394881026', '301074752624132101', '科创组1');
INSERT INTO `sys_organ3` VALUES ('301076316394881027', '301074752624132102', '学习组1');
INSERT INTO `sys_organ3` VALUES ('301076316394881028', '301074752624132103', '宣传组2');
INSERT INTO `sys_organ3` VALUES ('301076316394881030', '301074752624132105', '技能组2');
INSERT INTO `sys_organ3` VALUES ('301076316394881031', '301074752624132106', '财管组3');
INSERT INTO `sys_organ3` VALUES ('301076316394881032', '301074752624132107', '行政组3');
INSERT INTO `sys_organ3` VALUES ('301076316394881033', '301074752624132108', '财能组3');

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `p_id` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限id',
  `p_name` varchar(19) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限名称',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限路径',
  `higher_pid` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '上级权限id',
  `is_deleted` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '逻辑删除【1true】',
  PRIMARY KEY (`p_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('301069904243589117', '系统管理', '/sys', '0', '0');
INSERT INTO `sys_permission` VALUES ('301069904243589118', '档案管理', '/file', '0', '0');
INSERT INTO `sys_permission` VALUES ('301069904243589119', '薪酬管理', '/sal', '0', '0');
INSERT INTO `sys_permission` VALUES ('301069904243589120', '角色管理', '/sys/user', '301069904243589117', '0');
INSERT INTO `sys_permission` VALUES ('301069904243589122', '机构管理', '/sys/organization', '301069904243589117', '0');
INSERT INTO `sys_permission` VALUES ('301069904243589123', '职位管理', '/sys/position', '301069904243589117', '0');
INSERT INTO `sys_permission` VALUES ('301069904243589124', '档案登记', '/file/add', '301069904243589118', '0');
INSERT INTO `sys_permission` VALUES ('301069904243589125', '档案复核', '/file/check', '301069904243589118', '0');
INSERT INTO `sys_permission` VALUES ('301069904243589126', '档案查询', '/file/query', '301069904243589118', '0');
INSERT INTO `sys_permission` VALUES ('301069904243589127', '薪酬登记', '/sal/add', '301069904243589119', '0');
INSERT INTO `sys_permission` VALUES ('301069904243589128', '薪酬复核', '/sal/check', '301069904243589119', '0');
INSERT INTO `sys_permission` VALUES ('301069904243589129', '薪酬查询', '/sal/query', '301069904243589119', '0');
INSERT INTO `sys_permission` VALUES ('301069904243589130', '薪资发放', '/sal/give', '301069904243589119', '0');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `r_id` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色id',
  `role_name` varchar(19) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `is_deleted` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '逻辑删除【1true】',
  PRIMARY KEY (`r_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('0000000000000000000', '超级管理员', '0');
INSERT INTO `sys_role` VALUES ('0000000000000000001', '系统管理员', '0');
INSERT INTO `sys_role` VALUES ('0000000000000000002', '档案管理员', '0');
INSERT INTO `sys_role` VALUES ('0000000000000000003', '薪酬管理员', '0');
INSERT INTO `sys_role` VALUES ('0000000000000000004', '普通管理员', '0');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`  (
  `r_id` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色id',
  `p_id` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限id',
  `is_deleted` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '逻辑删除【1true】',
  PRIMARY KEY (`r_id`, `p_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('0000000000000000000', '301069904243589120', '0');
INSERT INTO `sys_role_permission` VALUES ('0000000000000000000', '301069904243589121', '0');
INSERT INTO `sys_role_permission` VALUES ('0000000000000000000', '301069904243589122', '0');
INSERT INTO `sys_role_permission` VALUES ('0000000000000000000', '301069904243589123', '0');
INSERT INTO `sys_role_permission` VALUES ('0000000000000000000', '301069904243589124', '0');
INSERT INTO `sys_role_permission` VALUES ('0000000000000000000', '301069904243589125', '0');
INSERT INTO `sys_role_permission` VALUES ('0000000000000000000', '301069904243589126', '0');
INSERT INTO `sys_role_permission` VALUES ('0000000000000000000', '301069904243589127', '0');
INSERT INTO `sys_role_permission` VALUES ('0000000000000000000', '301069904243589128', '0');
INSERT INTO `sys_role_permission` VALUES ('0000000000000000000', '301069904243589129', '0');
INSERT INTO `sys_role_permission` VALUES ('0000000000000000000', '301069904243589130', '0');
INSERT INTO `sys_role_permission` VALUES ('0000000000000000001', '301069904243589120', '0');
INSERT INTO `sys_role_permission` VALUES ('0000000000000000001', '301069904243589121', '0');
INSERT INTO `sys_role_permission` VALUES ('0000000000000000001', '301069904243589122', '0');
INSERT INTO `sys_role_permission` VALUES ('0000000000000000001', '301069904243589123', '0');
INSERT INTO `sys_role_permission` VALUES ('0000000000000000002', '301069904243589124', '0');
INSERT INTO `sys_role_permission` VALUES ('0000000000000000002', '301069904243589125', '0');
INSERT INTO `sys_role_permission` VALUES ('0000000000000000002', '301069904243589126', '0');
INSERT INTO `sys_role_permission` VALUES ('0000000000000000003', '301069904243589127', '0');
INSERT INTO `sys_role_permission` VALUES ('0000000000000000003', '301069904243589128', '0');
INSERT INTO `sys_role_permission` VALUES ('0000000000000000003', '301069904243589129', '0');
INSERT INTO `sys_role_permission` VALUES ('0000000000000000003', '301069904243589130', '0');
INSERT INTO `sys_role_permission` VALUES ('0000000000000000004', '301069904243589124', '0');
INSERT INTO `sys_role_permission` VALUES ('0000000000000000004', '301069904243589126', '0');
INSERT INTO `sys_role_permission` VALUES ('0000000000000000004', '301069904243589127', '0');
INSERT INTO `sys_role_permission` VALUES ('0000000000000000004', '301069904243589129', '0');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `u_id` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
  `username` varchar(19) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(19) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `r_id` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色id',
  `is_deleted` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '逻辑删除【1true】',
  PRIMARY KEY (`u_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('301069904247783480', 'admin', '123456', '0000000000000000000', '0');
INSERT INTO `sys_user` VALUES ('301069904247783481', 'xitong', '123456', '0000000000000000001', '0');
INSERT INTO `sys_user` VALUES ('301069904247783482', 'dangan', '123456', '0000000000000000002', '0');
INSERT INTO `sys_user` VALUES ('301069904247783483', 'xinchou', '123456', '0000000000000000003', '0');
INSERT INTO `sys_user` VALUES ('301415440381378560', 'putong', '123456', '0000000000000000004', '0');
INSERT INTO `sys_user` VALUES ('302295364541612032', 'admin04', '123456', '0000000000000000004', '0');
INSERT INTO `sys_user` VALUES ('305158191270854656', 'admin666', '123456', '0000000000000000002', '1');

SET FOREIGN_KEY_CHECKS = 1;
