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

CREATE TABLE `TF_MM_USER` (
  `USER_ID` varchar(36) NOT NULL COMMENT '用户id',
  `USER_NAME` varchar(255) DEFAULT NULL COMMENT '用户登录名',
  `USER_PASSWORD` varchar(255) DEFAULT NULL COMMENT '用户密码',
  `USER_LEVEL` INT DEFAULT NULL COMMENT '医院级别',
  `USER_HOSPITAL` varchar(100) DEFAULT NULL COMMENT '用户所属医院',
  `CREATE_DATE` DATE  COMMENT '创建时间',
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO TF_MM_USER VALUES('00001','mayantao','123456','10','山西省人民医院-level-10',SYSDATE());
INSERT INTO TF_MM_USER VALUES('00002','wangpeiqiang','123456','1','晋中人民医院-level-1',SYSDATE());
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

insert into TF_MM_MENU values('00000',null,'首页','dashboard','dashboard',1,1,SYSDATE(),'0');
insert into TF_MM_MENU values('000000','00000','首页','','',1,1,SYSDATE(),'0');

insert into TF_MM_MENU values('00001',null,'病例','case','case',1,1,SYSDATE(),'0');
insert into TF_MM_MENU values('00002','00001','病例列表','case_list','case_list',1,1,SYSDATE(),'0');

insert into TF_MM_MENU values('00004','00001','病例新增','case_add','case_add',1,1,SYSDATE(),'0');

insert into TF_MM_MENU values('00010',null,'转诊','referral','referral',1,1,SYSDATE(),'0');
insert into TF_MM_MENU values('00011','00010','转诊申请列表','referral_list','referral_list',1,1,SYSDATE(),'0');
insert into TF_MM_MENU values('00012','00010','转诊结果列表','referral_result','referral_result',1,1,SYSDATE(),'0');
COMMIT;

CREATE TABLE `TF_MM_REFERRAL` (
  `REFERRAL_ID` varchar(36) NOT NULL COMMENT '转诊单id',
  `CASE_ID` varchar(36)  COMMENT '病例id',
  `OLD_OWN_ID` varchar(36)  COMMENT '原医院id',
  `OLD_OWN_NAME` varchar(128)  COMMENT '原医院名字',
  `NEW_OWN_ID` varchar(36)  COMMENT '转派医院id',
  `NEW_OWN_NAME` varchar(36)  COMMENT '转派医院名字',
  `MESSAGE` varchar(255) DEFAULT NULL COMMENT '转诊单备注',
  `TYPE` varchar(2) DEFAULT NULL COMMENT '转诊类型 1 上转转诊 2 下转转诊',
  `CREATE_DATE` date COMMENT '转诊单创建时间',
  `STATUS` varchar(2) DEFAULT NULL COMMENT '1 接受中 2 已接受 3 已拒绝',
  PRIMARY KEY (`REFERRAL_ID`)
) COMMENT '转诊记录表',ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `TF_MM_NOTICE` (
  `NOTICE_ID` varchar(36) NOT NULL COMMENT '转诊结果提醒id',
  `REFERRAL_ID` varchar(36)  COMMENT '转诊单id',
  `NOTICE_STATUS` varchar(2) DEFAULT NULL COMMENT '0 提醒 1 已阅',
  `NOTICE_CREATE_DATE` date COMMENT '处理时间',
  PRIMARY KEY (`NOTICE_ID`)
) COMMENT '转诊结果提醒表',ENGINE=InnoDB DEFAULT CHARSET=utf8;
insert into TF_MM_NOTICE values('0001','4ec76b45524e06f91cddb78e2e08051c','0',1,1,SYSDATE());