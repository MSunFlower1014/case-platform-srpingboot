--DROP TABLE TF_MM_CASE
CREATE TABLE `TF_MM_CASE` (
  `CASE_ID` varchar(36) NOT NULL COMMENT '病例id',
  `CASE_NAME` varchar(255) DEFAULT NULL  COMMENT '病例名称',
  `PATIENT_NAME` varchar(255) DEFAULT NULL COMMENT '病人名字',
  `PATIENT_AGE` varchar(4) DEFAULT NULL COMMENT '病人年龄',
  `PATIENT_SEX` varchar(2) DEFAULT NULL COMMENT '病人性别',
  `PATIENT_MOBILE` varchar(20) DEFAULT NULL COMMENT '病人联系方式',
  `CASE_HOSPITAL` varchar(255) DEFAULT NULL COMMENT '所属医院',
  `CASE_MESSAGE` varchar(2550) DEFAULT NULL COMMENT '病例信息',
  `CASE_TYPE` varchar(255) DEFAULT NULL COMMENT '病例类型',
  `CASE_DATE` DATE  COMMENT '创建时间',
  `CASE_STATUS` varchar(4) DEFAULT NULL COMMENT '病例状态  1 正常  2  转派中   3  出院结束',
  PRIMARY KEY (`CASE_ID`)
) COMMENT '病例表',ENGINE=InnoDB DEFAULT CHARSET=utf8;

--DROP TABLE TF_MM_USER
CREATE TABLE `TF_MM_USER` (
  `USER_ID` varchar(36) NOT NULL,
  `USER_NAME` varchar(255) DEFAULT NULL,
  `USER_PASSWORD` varchar(255) DEFAULT NULL,
  `USER_LEVEL` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO TF_MM_USER VALUES('00001','mayantao','123456','10')
--DROP TABLE TF_MM_MENU
CREATE TABLE `TF_MM_MENU` (
  `MENU_ID` varchar(36) NOT NULL COMMENT '菜单id',
  `PARENT_ID` varchar(36)  COMMENT '父菜单id',
  `MENU_NAME` varchar(36) DEFAULT NULL COMMENT '菜单名称',
  `MENU_URL` varchar(255) DEFAULT NULL  COMMENT '菜单地址',
  `MENU_REMARK` varchar(255) DEFAULT NULL COMMENT '菜单备注',
  `MENU_ISSHOW` INT DEFAULT NULL COMMENT '菜单是否显示',
  `MENU_SORT` INT DEFAULT NULL COMMENT '菜单排序',
  `MENU_CREATE_DATE` date COMMENT '菜单地址创建时间',
  `MENU_DEL_FLAG` varchar(255) DEFAULT NULL COMMENT '0 为有效，1为无效',
  PRIMARY KEY (`MENU_ID`)
) COMMENT '菜单表',ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into TF_MM_MENU values('00001',null,'病例','case','case',1,1,SYSDATE(),'0');
insert into TF_MM_MENU values('00002','00001','病例列表','case_list','case_list',1,1,SYSDATE(),'0');
insert into TF_MM_MENU values('00003','00001','病例详情','case_details','case_details',1,1,SYSDATE(),'0');
insert into TF_MM_MENU values('00004','00001','病例编辑','case_add','case_add',1,1,SYSDATE(),'0');

COMMIT;