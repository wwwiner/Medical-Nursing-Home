/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.5.56 : Database - his
*********************************************************************
*/
DROP DATABASE IF EXISTS `his`;
CREATE DATABASE `his`;
USE `his`;

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`his` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `his`;

/*Table structure for table `bulletint` */

 DROP TABLE IF EXISTS `bulletin`;
CREATE TABLE `bulletin` (
 `ID` int(255) NOT NULL AUTO_INCREMENT,
  `BullTitle` varchar(255) DEFAULT NULL,
  `UserName` varchar(255) DEFAULT NULL COMMENT '用户名',
  `BullType` varchar(255) DEFAULT NULL,
  `Content` varchar(255) DEFAULT NULL COMMENT '创建内容',
  `CreateDate` datetime DEFAULT NULL COMMENT '创建时间',
 PRIMARY KEY (`ID`)
 ) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8;

insert  into `bulletin`(`ID`,`BullTitle`,`UserName`,`BullType`,`Content`,`CreateDate`) values (1,'急寻！80岁老人走失','王建华','寻人启事','80岁老人，男性于2023年1月12日下午3点左右在新街口南走失,当时身穿黑棉袄和灰色运动裤，头戴帽子，如发现请联系18788345672，重金酬谢！','2023-02-08 23:49:48');

DROP TABLE IF EXISTS `department`;

CREATE TABLE `department` (
  `ID` int(255) NOT NULL AUTO_INCREMENT,
  `DeptCode` varchar(255) DEFAULT NULL,
  `DeptName` varchar(255) DEFAULT NULL,
  `DeptCategoryName` varchar(255) DEFAULT NULL,
  `DeptType` varchar(255) DEFAULT NULL,
  `CreateName` varchar(255) DEFAULT NULL COMMENT '创建人',
  `CreateDate` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8;

/*Data for the table `department` */

insert  into `department`(`ID`,`DeptCode`,`DeptName`,`DeptCategoryName`,`DeptType`,`CreateName`,`CreateDate`) values (1,'XXGNK','心血管内科','内科','医疗科室','root','2023-02-13 16:49:48'),(2,'SJNK','神经内科','内科','医疗科室','root','2023-02-13 11:21:56'),(3,'PTNK','普通内科','内科','医疗科室','root','2023-02-13 11:21:56'),(4,'HXNK','呼吸内科','内科','医疗科室','root','2023-02-13 11:21:56'),(5,'XHNK','消化内科','内科','医疗科室','root','2023-02-13 11:21:56'),(6,'SNK','肾内科','内科','医疗科室','root','2023-02-13 11:21:56'),(7,'XYNK','血液内科','内科','医疗科室','root','2023-02-13 11:21:56'),(8,'MYK','免疫科','内科','医疗科室','root','2023-02-13 11:21:56'),(9,'NFMK','内分泌科','内科','医疗科室','root','2023-02-13 11:21:56'),(10,'NK','脑科','内科','医疗科室','root','2023-02-13 11:21:56'),(11,'PPWK','普通外科','外科','医疗科室','root','2023-02-03 11:21:56'),(12,'JSWK','神经外科','外科','医疗科室','root','2023-02-03 11:21:56'),(13,'XXWK','心胸外科','外科','医疗科室','root','2023-02-03 11:21:56'),(14,'MNWK','泌尿外科','外科','医疗科室','root','2023-02-03 11:21:56'),(15,'XXGWK','心血管外科','外科','医疗科室','root','2023-02-03 11:21:56'),(16,'RXWK','乳腺外科','外科','医疗科室','root','2023-02-03 11:21:56'),(17,'GDWK','肝胆外科','外科','医疗科室','root','2023-02-03 11:21:56'),(18,'QGYZ','器官移植','外科','医疗科室','root','2023-02-03 11:21:56'),(19,'SSK','烧伤科','外科','医疗科室','李卫昊','2023-02-03 13:34:03'),(20,'TJWK','头颈外科','外科','医疗科室','李卫昊','2023-02-03 13:34:03'),(21,'GWK','骨外科','外科','医疗科室','root','2023-02-03 11:21:56'),(22,'QSWK','枪伤外科','外科','医疗科室','root','2023-02-03 11:21:56'),(23,'BJK','保健科','护理科','医疗科室','root','2023-02-03 11:21:56'),(24,'YFK','预防科','护理科','医疗科室','root','2023-02-03 11:21:56'),(25,'TJK','体检科','护理科','医疗科室','root','2023-02-03 11:21:56'),(26,'EBHK','耳鼻喉科','五官科','医疗科室','root','2023-02-03 11:21:56'),(27,'YK','眼科','五官科','医疗科室','root','2023-02-03 11:21:56'),(28,'KQK','口腔科','五官科','医疗科室','root','2023-02-03 11:21:56'),(29,'ZLNK','肿瘤内科','肿瘤科','医疗科室','root','2023-02-03 11:21:56'),(30,'ZLFK','肿瘤妇科','肿瘤科','医疗科室','root','2023-02-03 11:21:56'),(31,'GZLK','骨肿瘤科','肿瘤科','医疗科室','root','2023-02-03 11:21:56'),(32,'FLK','放疗科','肿瘤科','医疗科室','root','2023-02-03 11:21:56'),(33,'ZLKFK','肿瘤康复科','肿瘤科','医疗科室','root','2023-02-03 11:21:56'),(34,'ZLZHK','肿瘤综合科','肿瘤科','医疗科室','root','2023-02-03 11:21:56'),(35,'PFK','皮肤科','皮肤性病科','医疗科室','root','2023-02-03 11:21:56'),(36,'XBK','性病科','皮肤性病科','医疗科室','root','2023-02-03 11:21:56'),(37,'ZYQK','中医全科','中医科','医疗科室','root','2023-02-03 11:21:56'),(38,'ZYNK','中医内科','中医科','医疗科室','root','2023-02-03 11:21:56'),(39,'ZYWK','中医外科','中医科','医疗科室','root','2023-02-03 11:21:56'),(40,'ZYFK','中医妇科','中医科','医疗科室','root','2023-02-03 11:21:56'),(42,'ZYBJK','中医保健科','中医科','医疗科室','root','2023-02-03 11:21:56'),(43,'ZJAMK','针灸按摩科','中医科','医疗科室','root','2023-02-03 11:21:56'),(44,'ZYGSK','中医骨伤科','中医科','医疗科室','root','2023-02-03 11:21:56'),(45,'ZYZLK','中医肿瘤科','中医科','医疗科室','root','2023-02-03 11:21:56'),(46,'GBK','肝病科','传染科','医疗科室','root','2023-02-03 11:21:56'),(47,'AZBK','艾滋病科','传染科','医疗科室','root','2023-02-03 11:21:56'),(48,'JHBK','结核病科','传染科','医疗科室','root','2023-02-03 11:21:56'),(49,'JSCK','寄生虫科','传染科','医疗科室','root','2023-02-03 11:21:56'),(50,'PTJSK','普通精神科','精神科','医疗科室','root','2023-02-03 11:21:56'),(51,'XLK','心理科','精神科','医疗科室','root','2023-02-03 11:21:56'),(52,'YPCWGL','药品财务管理','药品财务管理','财务科室','root','2023-02-03 11:21:56'),(53,'YLQJCWGL','医疗器具财务管理','医疗器具财务管理','财务科室','root','2023-02-03 11:21:56'),(54,'RLZY','人力资源','人力资源','财务科室','root','2023-02-03 11:21:56'),(55,'YBGS','院办公室','院办公室','行政科室','root','2023-02-03 13:20:51'),(56,'YWK','医务科','医务科室','行政科室','root','2023-02-03 13:34:03'),(80,'GCK','肛肠科','肛肠科','医疗科室','苗至恒','2023-02-03 13:34:03');

/*Table structure for table `drug` */

DROP TABLE IF EXISTS `drug`;

CREATE TABLE `drug` (
  `ID` int(255) NOT NULL AUTO_INCREMENT,
  `DrugName` varchar(255) DEFAULT NULL,
  `DrugType` varchar(255) DEFAULT NULL,
  `DrugCount` int(255) DEFAULT NULL,
  `DrugPrice` int(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=133 DEFAULT CHARSET=utf8;

/*Data for the table `drug` */

insert  into `drug`(`ID`,`DrugName`,`DrugType`,`DrugCount`,`DrugPrice`) values (1,'脑白金','保健品',800,100),(2,'阿胶膏','保健品',76,100),(3,'人参茯苓米糊','保健品',621,100),(4,'金装钙片','保健品',652,100),(5,'中老年蛋白粉','保健品',233,100),(6,'乙酰水杨酸','解热镇痛药',653,100),(7,'阿司匹林精氨酸盐','解热镇痛药',100,100),(8,'阿司匹林赖氨酸盐','解热镇痛药',852,100),(9,'对乙酰氨基酚','解热镇痛药',785,100),(10,'双氯灭痛','解热镇痛药',452,100),(11,'吡罗昔康','解热镇痛药',999,100),(12,'脑复康','大脑康复药',100,100),(13,'脑康片','大脑康复药',999,100),(14,'克脑迷','大脑康复药',998,100),(15,'胞二磷胆碱','大脑康复药',500,100),(16,'氯丙嗪','抗精神病药',1000,100),(17,'氟奋乃静','抗精神病药',441,100),(18,'三氟哌啶醇 ','抗精神病药',1000,100),(19,'氟奋乃葵酸酯','抗精神病药',1000,100),(20,'吗啉双苯酮','止咳药',1000,100),(21,'咳散','止咳药',1000,100),(22,'苯哌丙烷','止咳药',1000,100),(23,'双苯丙烷','止咳药',1000,100),(24,'苯哌丁烷','止咳药',1000,100),(25,'胃蛋白酶','消化系统药物',1000,100),(26,'健胃消食片','消化系统药物',1000,100),(27,'乳酶生','消化系统药物',1000,100),(28,'稀盐酸','消化系统药物',1000,100),(29,'碳酸氢钠','消化系统药物',1000,100),(30,'法莫替丁','消化系统药物',1000,100),(31,'冻干人纤维蛋白原','止血药',996,100),(32,'鱼精蛋白','止血药',1000,100),(33,'凝血酶原复合物','止血药',1000,100),(34,'安特诺新','止血药',1000,100),(35,'青霉素','抗生素',1000,100),(36,'头孢噻吩','抗生素',1000,100),(37,'头孢唑啉','抗生素',1000,100),(38,'链霉素','抗生素',1000,100),(39,'四环素','抗生素',1000,100),(40,'罗他霉素','抗生素',1000,100),(41,'头孢甲肟 ','抗生素',1000,100),(42,'酚嘧啶','绦虫药',1000,100),(43,'哌嗪','绦虫药',1000,100),(44,'奥苯哒唑','绦虫药',1000,100),(45,'噻苯咪唑','绦虫药',1000,100),(46,'短棒菌苗','免疫增强剂',1000,100),(47,'胸腺素','免疫增强剂',1000,100),(48,'集落刺激因子','免疫增强剂',1000,100),(49,'转移因子','免疫增强剂',1000,100),(50,'高分化因子','免疫增强剂',1000,100),(51,'苯呋洛尔','眼科用药',1000,100),(52,'安妥碘','眼科用药',1000,100),(53,'羟苄唑','眼科用药',1000,100),(54,'匹马利星','眼科用药',1000,100),(55,'噻吗洛尔','眼科用药',1000,100),(56,'萘唑啉','耳鼻喉科用药',1000,100),(57,'茚咪唑啉','耳鼻喉科用药',1000,100),(58,'维甲酸','皮肤科用药',1000,100),(59,'异维甲酸','皮肤科用药',1000,100),(60,'阿维甲酯','皮肤科用药',1000,100),(61,'补骨脂素','皮肤科用药',1000,100),(62,'肤炎宁','皮肤科用药',1000,100),(63,'二巯丙醇','特殊解毒药',999,100),(64,'依地酸钙钠','特殊解毒药',999,100),(65,'青霉胺','特殊解毒药',999,100),(66,'碘解磷定','特殊解毒药',1000,100),(67,'二巯丙磺钠','特殊解毒药',1000,100),(68,'桑菊感冒片','感冒药',1000,100),(69,'银翘解毒片','感冒药',1000,100),(70,'抗病毒胶囊','感冒药',1000,100),(71,'藿香正气丸','感冒药',1000,100),(72,'清宫丸','感冒药',1000,100),(73,'晕痛定片','头痛药',1000,100),(74,'头风痛丸','头痛药',1000,100),(75,'清眩丸','头痛药',1000,100),(76,'都梁丸','头痛药',1000,100),(77,'立止头痛散','头痛药',1000,100),(78,'小青龙口服液','肺病药',1000,100),(79,'枇杷止咳糖浆','肺病药',1000,100),(80,'猴耳环片','肺病药',1000,100),(81,'保金丸','肺病药',1000,100),(82,'复方四季青片','肺病药',1000,100),(83,'乙肝清热解毒冲剂','肝病药',1000,100),(84,'乙肝益气解郁冲剂','肝病药',1000,100),(85,'茵陈五苓丸','肝病药',1000,100),(86,'乙肝养阴活血冲剂','肝病药',1000,100),(87,'肝炎糖浆','肝病药',1000,100),(88,'枯痔注射液','肛肠药',997,100),(89,'百药煎','肛肠药',997,100),(90,'舒痔丸','肛肠药',997,100),(91,'化痔丸','肛肠药',998,100),(92,'消痔锭','肛肠药',1000,100),(93,'益母草膏','妇产科药',1000,100),(94,'八珍益母丸','妇产科药',1000,100),(95,'乌鸡白凤丸','妇产科药',1000,100),(96,'补血调经丸','妇产科药',1000,100),(97,'八宝坤顺丸','妇产科药',1000,100),(104,'云南白药','骨科药',1000,100),(105,'跌打丸','骨科药',1000,100),(106,'骨友灵搽剂','骨科药',1000,100),(107,'三七伤药片','骨科药',1000,100),(108,'桃仁承气丸','骨科药',1000,100),(109,'九圣散','皮肤科用药',1000,100),(110,'皮炎宁','皮肤科用药',1000,100),(111,'银屑丸','皮肤科用药',1000,100),(112,'白癜风丸','皮肤科用药',1000,100),(113,'六神丸','五官科用药',1000,100),(114,'喉症丸','五官科用药',1000,100),(115,'鼻渊片','五官科用药',1000,100),(116,'明目丸','五官科用药',1000,100),(117,'耳聋左慈丸','五官科用药',1000,100),(118,'平消片','肿瘤科用药',1000,100),(119,'鹤蟾片','肿瘤科用药',1000,100),(120,'复方宣乌片','肿瘤科用药',1000,100),(121,'驱邪净气散','肿瘤科用药',1000,100),(122,'皮癌净','肿瘤科用药',1000,100),(123,'鹤顶红','剧毒药物',994,100),(124,'含笑半步颠','剧毒药物',986,100),(125,'三尸脑神丹','剧毒药物',994,100),(126,'三笑逍遥散','剧毒药物',995,100),(127,'七星海棠','剧毒药物',1000,100),(128,'十香软筋散','剧毒药物',1000,100),(129,'雪上一枝蒿','剧毒药物',1000,100),(130,'金蚕蛊毒','剧毒药物',1000,100),(131,'七步催魂散','剧毒药物',1000,100),(132,'砒霜','剧毒药物',1000,100);

/*Table structure for table `hospitalization` */

DROP TABLE IF EXISTS `hospitalization`;

CREATE TABLE `hospitalization` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `RegisterId` int(11) DEFAULT NULL COMMENT '病历表Id',
  `UserAccount` varchar(255) DEFAULT NULL COMMENT '病人账号',
  `UserName` varchar(255) DEFAULT NULL COMMENT '病人姓名',
  `DocAccount` varchar(255) DEFAULT NULL COMMENT '诊断医生账号',
  `DocName` varchar(255) DEFAULT NULL COMMENT '诊断医生姓名',
  `DocType` varchar(255) DEFAULT NULL COMMENT '诊断医生类型',
  `DeptCategoryName` varchar(255) DEFAULT NULL COMMENT '挂号科室分类',
  `DeptType` varchar(255) DEFAULT NULL COMMENT '挂号科室类型',
  `Disease` varchar(255) DEFAULT NULL COMMENT '疾病',
  `InTime` datetime DEFAULT NULL COMMENT '入住时间',
  `ContinuedTime` int(11) DEFAULT NULL COMMENT '入住时长',
  `Status` varchar(255) DEFAULT NULL COMMENT '状态',
  `PayMoney` int(11) DEFAULT NULL COMMENT '缴费金额',
  `IsIn` int(11) DEFAULT NULL COMMENT '1同意住院0不同意住院',
  `WardName` varchar(255) DEFAULT NULL COMMENT '入住病房名称',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

/*Data for the table `hospitalization` */

insert  into `hospitalization`(`Id`,`RegisterId`,`UserAccount`,`UserName`,`DocAccount`,`DocName`,`DocType`,`DeptCategoryName`,`DeptType`,`Disease`,`InTime`,`ContinuedTime`,`Status`,`PayMoney`,`IsIn`,`WardName`) values (1,16,'wangguifang','王桂芳','zhangqiang','张强','神经内科主任','内科','神经内科','脑震荡','2023-02-19 11:42:19',5,'已出院',300,1,'高级护理房6');
/*Table structure for table `register` */

DROP TABLE IF EXISTS `register`;

CREATE TABLE `register` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `UserAccount` varchar(255) DEFAULT NULL,
  `UserName` varchar(255) DEFAULT NULL,
  `DocAccount` varchar(255) DEFAULT NULL,
  `DocName` varchar(255) DEFAULT NULL,
  `RegisterDepartment` varchar(255) DEFAULT NULL,
  `DocType` varchar(255) DEFAULT NULL,
  `UserDescription` varchar(255) DEFAULT NULL,
  `DocDiagnostic` varchar(255) DEFAULT NULL,
  `Drug` varchar(255) DEFAULT NULL,
  `Status` varchar(255) DEFAULT NULL,
  `DeptName` varchar(255) DEFAULT NULL,
  `DeptCategoryName` varchar(255) DEFAULT NULL,
  `Disease` varchar(255) DEFAULT NULL COMMENT '疾病名称',
  `IsHosAdvice` varchar(255) DEFAULT NULL COMMENT '是否建议住院',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

/*Data for the table `register` */

insert  into `register`(`ID`,`UserAccount`,`UserName`,`DocAccount`,`DocName`,`RegisterDepartment`,`DocType`,`UserDescription`,`DocDiagnostic`,`Drug`,`Status`,`DeptName`,`DeptCategoryName`,`Disease`,`IsHosAdvice`) values (2,'1','root','zhaohanqing','赵翰青','头疼','神经内科主任',NULL,'没毛病','藿香正气丸,清宫丸','已完成','神经内科','内科',NULL,NULL),(3,'1','root','hexianglian','贺湘莲','内科','心血管内科医生',NULL,'对对对','鹤顶红,含笑半步颠,三尸脑神丹,三笑逍遥散','已缴费','心血管内科',NULL,NULL,NULL),(6,'1','root','guojiapeng','国家鹏','疼啊','心血管内科医生',NULL,'asdasd','氯丙嗪','已缴费：100元','心血管内科','内科','心脏病',NULL),(8,'guoyicheng','郭一诚','zhaohanqing','赵翰青','我有病','神经内科主任',NULL,'没病','含笑半步颠','已缴费','神经内科','内科',NULL,NULL),(9,'houjiahao','侯佳昊','miaozhiheng','苗至恒','屁股疼','肛肠科医生',NULL,'想吃啥就吃点啥吧，钱也别存着了','枯痔注射液,百药煎,舒痔丸','已缴费','肛肠科','肛肠科',NULL,NULL),(10,'houjiahao','侯佳昊','guojiapeng','国家鹏','失恋了，心痛','心血管内科医生',NULL,'想吃啥就吃点啥吧，钱也别存着了','氯丙嗪,氟奋乃静,三氟哌啶醇,氨砜噻吨,氟奋乃静癸酸酯','已缴费500元','心血管内科','内科',NULL,NULL),(11,'1','root','hexianglian','贺湘莲','测试挂号验证','心血管内科医生',NULL,'tset','氟奋乃静,三氟哌啶醇,氧化亚氮 ','诊断完成，待缴费','心血管内科','内科',NULL,NULL),(12,'1','root','hexianglian','贺湘莲','测试刷新','心血管内科医生',NULL,'dwrere','氟奋乃静,三氟哌啶醇,氨砜噻吨','已缴费：300元','心血管内科','内科',NULL,NULL),(13,'1','root','guojiapeng','国家鹏','啊实打实的','心血管内科医生',NULL,'wedqwed','三氟哌啶醇','已缴费100元','心血管内科','内科',NULL,NULL),(14,'1','root','guojiapeng','国家鹏','请问全文','心血管内科医生',NULL,'asdasdw','三氟哌啶醇','已缴费100元','心血管内科','内科',NULL,NULL),(15,'guojiapeng','国家鹏','guojiapeng','国家鹏','qweqwe','心血管内科医生',NULL,'asdqwedqw','阿司匹林赖氨酸盐','诊断完成，待缴费','心血管内科','内科',NULL,NULL),(16,'guoyicheng','郭一诚','zhaohanqing','赵翰青','123456','神经内科主任',NULL,'没病','脑复康,脑康片,克脑迷,胞二磷胆碱','诊断完成，待缴费','神经内科','内科','妄想症','否'),(17,'maguangxu','马广旭','zhaohanqing','赵翰青','12345','神经内科主任',NULL,'1234','阿司匹林精氨酸盐,阿司匹林赖氨酸盐,对乙酰氨基酚,双氯灭痛,吡罗昔康','已缴费500元','神经内科','内科',NULL,NULL),(18,'1','root','guojiapeng','国家鹏','测试余额','心血管内科医生',NULL,'return result','冻干人纤维蛋白原','已缴费：100元','心血管内科','内科',NULL,NULL),(19,'liuhuan','刘欢','shixueying','石雪营','我有毛病','肾内科医生',NULL,'肾虚','二巯丙醇,依地酸钙钠,青霉胺','诊断完成，待缴费','肾内科','内科',NULL,NULL),(20,'1','root','zhaohanqing','赵翰青','感觉要死了','神经内科主任',NULL,'放轻松','氟奋乃静癸酸酯,氨砜噻吨,三氟哌啶醇','诊断完成，待缴费','神经内科','内科','抑郁症',NULL),(21,'houjiahao','侯佳昊','zhaohanqing','赵翰青','心碎了','神经内科主任',NULL,'睡一觉就好了','脑复康,脑康片','诊断完成，待缴费','神经内科','内科','失恋',NULL),(23,'zhaohanqing','赵翰青','zhaohanqing','赵翰青','asdasd','神经内科主任',NULL,'dad','法莫替丁,稀盐酸','诊断完成，待缴费','神经内科','内科','dadas','true'),(24,'zhaohanqing','赵翰青','zhaohanqing','赵翰青','qweqwe','神经内科主任',NULL,'werwer','双苯丙烷,苯哌丙烷,咳散','诊断完成，待缴费','神经内科','内科','wer','true'),(25,'guoyicheng','郭一诚','zhaohanqing','赵翰青','我头疼','神经内科主任',NULL,'建议吃点好的','脑复康,脑康片,克脑迷,胞二磷胆碱','已缴费：400元','神经内科','内科','脑震荡','是'),(26,'houjiahao','侯佳昊','mengfandi','孟繁迪','我被苗哥抓走了','艾滋病科医生',NULL,'吃好喝好','含笑半步颠','已缴费：100元','艾滋病科','传染科','艾滋病','是'),(27,'chensupeng','陈苏鹏','mengfandi','孟繁迪','我被苗哥抓走了','艾滋病科医生',NULL,'病危','舒痔丸,枯痔注射液,百药煎,化痔丸','已缴费：400元','艾滋病科','传染科','艾滋病','是'),(28,'dagouzi','大狗子','zhaohanqing','赵翰青','25456','神经内科主任',NULL,'脑膜炎脑膜炎脑膜炎','氯丙嗪,三氟哌啶醇,氟奋乃静,氨砜噻吨','诊断完成，待缴费','神经内科','内科','脑膜炎','是'),(29,'1','root',NULL,'国家鹏','我去额为',NULL,NULL,NULL,NULL,'正在处理','心血管内科','内科',NULL,NULL),(30,'1','root',NULL,'贺湘莲','好多个',NULL,NULL,NULL,NULL,'正在处理','心血管内科','内科',NULL,NULL);

/*Table structure for table `salary` */

DROP TABLE IF EXISTS `salary`;

CREATE TABLE `salary` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `UserAccount` varchar(100) DEFAULT NULL,
  `UserName` varchar(100) DEFAULT NULL,
  `TransferAccount` varchar(100) DEFAULT NULL,
  `TransferName` varchar(100) DEFAULT NULL,
  `TransferTime` datetime DEFAULT NULL,
  `TransferMoney` int(255) DEFAULT NULL,
  `AllTransferMoney` int(255) DEFAULT '0',
  `TransferType` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

/*Data for the table `salary` */

insert  into `salary`(`Id`,`UserAccount`,`UserName`,`TransferAccount`,`TransferName`,`TransferTime`,`TransferMoney`,`AllTransferMoney`,`TransferType`) values (1,'his','医院总部','1','root','2023-02-15 11:36:21',100,100,'工资发放'),(2,'his','医院总部','1','root','2023-02-15 12:20:59',300,300,'医疗缴费'),(3,'zhaohanqing','赵翰青','1','root','2023-02-15 13:00:59',1000,1000,'工资发放'),(4,'1','root','1','root','2023-02-15 13:05:46',100,200,'工资发放'),(5,'lihe','李贺','1','root','2023-02-15 13:07:43',1000,1000,'工资发放'),(6,'lihe','李卫昊','1','root','2023-02-15 13:21:43',200,1200,'工资发放'),(7,'lihe','李贺','1','root','2023-02-15 13:26:41',452,1652,'工资发放'),(8,'lihe','李贺','1','root','2023-02-15 13:27:38',10,1662,'工资发放'),(10,'his','医院总部','guoyicheng','郭一诚','2023-02-19 10:19:01',100,0,'住院缴费'),(11,'his','医院总部','guoyicheng','郭一诚','2023-02-19 10:22:12',100,0,'住院缴费'),(12,'his','医院总部','guoyicheng','郭一诚','2023-02-19 10:28:35',100,0,'住院缴费'),(13,'his','医院总部','guoyicheng','郭一诚','2023-02-19 10:41:14',100,100,'住院缴费'),(14,'his','医院总部','guoyicheng','郭一诚','2023-02-19 11:06:07',300,300,'住院缴费');

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `ID` int(225) NOT NULL AUTO_INCREMENT,
  `UserName` varchar(1000) DEFAULT NULL,
  `Password` varchar(1000) DEFAULT NULL,
  `RealName` varchar(1000) DEFAULT NULL,
  `UserType` varchar(225) DEFAULT NULL COMMENT '用户类型',
  `PrivilegeLevel` varchar(255) DEFAULT NULL COMMENT '权限等级',
  `Money` int(255) DEFAULT NULL,
   `AvatarLink` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`ID`,`UserName`,`Password`,`RealName`,`UserType`,`PrivilegeLevel`,`Money`,`AvatarLink`) values (1,'linya','linya','林雅','用户',NULL,1682,'D2F3DSSOG~04UDC9AAUWL9G.png'),(3,'1','1','root','管理员',NULL,600,'ISRP1BLB4P(H[C9%R)YSVRV.png'),(4,'ningzhiqian','123456','宁至谦','脑科医生',NULL,9500,'KGNNQG75)%Y1N@LVKRWGD3B.png'),(5,'ruanliuzheng','123456','阮流筝','神经内科医生',NULL,9500,'D2F3DSSOG~04UDC9AAUWL9G.png'),(6,'dingyiyuan','123456','丁意媛','保健科医生',NULL,9500,'D2F3DSSOG~04UDC9AAUWL9G.png'),(7,'songhao','123456','宋皓','体检科医生',NULL,9500,'ISRP1BLB4P(H[C9%R)YSVRV.png'),(8,'xiaoshan','123456','萧衫','预防科医生',NULL,9500,'ISRP1BLB4P(H[C9%R)YSVRV.png'),(9,'zhangqiang','123456','张强','神经内科主任',NULL,9500,'ISRP1BLB4P(H[C9%R)YSVRV.png'),(10,'linli','123456','李丽','用户',NULL,1000,'KGNNQG75)%Y1N@LVKRWGD3B.png'),(11,'wangguifang','123456','王桂芳','用户',NULL,1200,'KGNNQG75)%Y1N@LVKRWGD3B.png'),(12,'lihe','123456','李贺','用户',NULL,1682,'KGNNQG75)%Y1N@LVKRWGD3B.png'),(13,'maguangxu','123456','马广旭','脑科医生',NULL,9500,'KGNNQG75)%Y1N@LVKRWGD3B.png'),(14,'guoyicheng','123456','郭一诚','用户',NULL,0,'KGNNQG75)%Y1N@LVKRWGD3B.png'),(15,'zhaohanqing','123456','赵翰青','神经内科主任',NULL,11000,'KGNNQG75)%Y1N@LVKRWGD3B.png'),(16,'wubin','123456','武斌','普通内科医生',NULL,10000,'KGNNQG75)%Y1N@LVKRWGD3B.png'),(17,'miaomiao','123456','苗喵','肛肠科医生',NULL,10000,'ISRP1BLB4P(H[C9%R)YSVRV.png'),(18,'mengfendi','123456','孟芬迪','艾滋病科医生',NULL,10000,'ISRP1BLB4P(H[C9%R)YSVRV.png'),(19,'guojia','123456','郭嘉','心血管内科医生',NULL,10000,'ISRP1BLB4P(H[C9%R)YSVRV.png'),(20,'hexianglian','123456','贺湘莲','心血管内科医生',NULL,10000,'ISRP1BLB4P(H[C9%R)YSVRV.png'),(21,'his','123456','医院总部','医院总部',NULL,1000003518,'KGNNQG75)%Y1N@LVKRWGD3B.png');

/*Table structure for table `ward` */

DROP TABLE IF EXISTS `ward`;

CREATE TABLE `ward` (
  `Id` int(11) NOT NULL AUTO_INCREMENT COMMENT '房间id',
  `WardCode` varchar(255) DEFAULT NULL COMMENT '房间编号',
  `WardName` varchar(255) DEFAULT NULL COMMENT '房间名称',
  `WardType` varchar(255) DEFAULT NULL COMMENT '房间类型',
  `WardAddress` varchar(255) DEFAULT NULL COMMENT '房间地址',
  `UserAccount` varchar(255) DEFAULT NULL COMMENT '老人账号',
  `UserName` varchar(255) DEFAULT NULL COMMENT '老人姓名',
  `IsWardNull` varchar(255) DEFAULT '0' COMMENT '是否为空',
  `Disease` varchar(255) DEFAULT NULL COMMENT '疾病名称',
  `InTime` datetime DEFAULT NULL COMMENT '入住时间',
  `ContinuedTime` varchar(255) DEFAULT NULL COMMENT '入住时长',
  `DocName` varchar(255) DEFAULT NULL COMMENT '诊断医生姓名',
  `HosId` int(11) DEFAULT NULL COMMENT '入住登记表id',
  `WardPrice` int(11) DEFAULT NULL COMMENT '缴费金额',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

/*Data for the table `ward` */

insert  into `ward`(`Id`,`WardCode`,`WardName`,`WardType`,`WardAddress`,`UserAccount`,`UserName`,`IsWardNull`,`Disease`,`InTime`,`ContinuedTime`,`DocName`,`HosId`,`WardPrice`) values (1,'P-1','普通护理房1','普通护理房',NULL,'wangguifang','王桂芳','1','脑震荡','2023-02-19 14:14:43','15','张强',NULL,1500),(2,'P-2','普通护理房2','普通护理房',NULL,'chensupeng','陈苏鹏','1','艾滋病','2023-03-05 18:50:37','4','孟繁迪',NULL,400),(3,'P-3','普通护理房3','普通护理房',NULL,'dagouzi','大狗子','1','脑膜炎','2023-03-05 19:30:54','30','赵翰青',NULL,3000),(4,'P-4','普通护理房4','普通护理房',NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL,NULL,NULL),(5,'P-5','普通护理房5','普通护理房',NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL,NULL,NULL),(6,'P-6','普通护理房6','普通护理房',NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL,NULL,NULL),(7,'P-7','普通护理房7','普通护理房',NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL,NULL,NULL),(8,'P-8','普通护理房8','普通护理房',NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL,NULL,NULL),(9,'P-9','普通护理房9','普通护理房',NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL,NULL,NULL),(10,'P-10','普通护理房10','普通护理房',NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL,NULL,NULL),(11,'GJ-11','高级护理房1','高级护理房',NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL,NULL,NULL),(12,'GJ-12','高级护理房2','高级护理房',NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL,NULL,NULL),(13,'GJ-13','高级护理房3','高级护理房',NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL,NULL,NULL),(14,'GJ-14','高级护理房4','高级护理房',NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL,NULL,NULL),(15,'GJ-15','高级护理房5','高级护理房',NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL,NULL,NULL),(16,'GJ-16','高级护理房6','高级护理房',NULL,'','','0','',NULL,'','',NULL,NULL),(17,'GJ-17','高级护理房7','高级护理房',NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL,NULL,NULL),(18,'GJ-18','高级护理房8','高级护理房',NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL,NULL,NULL),(19,'GJ-19','高级护理房9','高级护理房',NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL,NULL,NULL),(20,'GJ-20','高级护理房10','高级护理房',NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL,NULL,NULL),(21,'lZ-21','临终护理房1','临终护理房',NULL,'wangguifang','王桂芳','1','脑震荡','2023-02-19 14:14:43','15','张强',NULL,1500),(22,'LZ-22','临终护理房2','临终护理房',NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL,NULL,NULL),(23,'LZ-23','临终护理房3','临终护理房',NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL,NULL,NULL),(24,'LZ-24','临终护理房4','临终护理房',NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL,NULL,NULL),(25,'LZ-25','临终护理房5','临终护理房',NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL,NULL,NULL),(26,'LZ-26','临终护理房6','临终护理房',NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL,NULL,NULL),(27,'LZ-27','临终护理房7','临终护理房',NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL,NULL,NULL),(28,'LZ-28','临终护理房8','临终护理房',NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL,NULL,NULL),(29,'LZ-29','临终护理房9','临终护理房',NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL,NULL,NULL),(30,'LZ-30','临终护理房10','临终护理房',NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL,NULL,NULL),(31,'ICU-31','重症监护室1','重症监护室',NULL,'houjiahao','侯佳昊','1','艾滋病','2023-03-03 17:54:07','3','孟繁迪',NULL,3000),(32,'ICU-32','重症监护室2','重症监护室',NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL,NULL,NULL),(33,'ICU-33','重症监护室3','重症监护室',NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL,NULL,NULL),(34,'ICU-34','重症监护室4','重症监护室',NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL,NULL,NULL),(35,'ICU-35','重症监护室5','重症监护室',NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL,NULL,NULL),(36,'ICU-36','重症监护室6','重症监护室',NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL,NULL,NULL),(37,'ICU-37','重症监护室7','重症监护室',NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL,NULL,NULL),(38,'ICU-38','重症监护室8','重症监护室',NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL,NULL,NULL),(39,'ICU-39','重症监护室9','重症监护室',NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL,NULL,NULL),(40,'ICU-40','重症监护室10','重症监护室',NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL,NULL,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
