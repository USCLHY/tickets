DROP DATABASE
IF
	EXISTS tickets;
	
	-- 创建数据库
CREATE DATABASE tickets DEFAULT CHARACTER 
SET UTF8 COLLATE utf8_bin;

-- 切换库
USE tickets;



-- 创建用户表
CREATE TABLE
IF
	NOT EXISTS users (
		uid INT PRIMARY KEY AUTO_INCREMENT COMMENT '用户id',
		uname VARCHAR ( 10 ) NOT NULL COMMENT '用户姓名',
		upassword VARCHAR ( 100 ) NOT NULL COMMENT '密码',
		upic VARCHAR ( 100 ) NOT NULL COMMENT '头像',
		utel VARCHAR ( 11 ) NOT NULL UNIQUE COMMENT '用户电话',
		uidentify VARCHAR ( 100 ) NOT NULL UNIQUE COMMENT '用户身份证',
		uemail VARCHAR ( 20 ) NOT NULL UNIQUE COMMENT '用户邮箱',
		STATUS INT ( 2 ) NOT NULL COMMENT '用户状态'-- 0表示冻结，1表示正常,其他 管理员等重新建表
		
	)ENGINE = INNODB AUTO_INCREMENT = 10001 DEFAULT CHARSET = utf8 COLLATE = utf8_bin;
	
	
	
	-- 创建管理员表
CREATE TABLE
IF
	NOT EXISTS admin (
		aid INT PRIMARY KEY AUTO_INCREMENT COMMENT '管理员id',
		aname VARCHAR ( 10 ) NOT NULL COMMENT '管理员姓名',
		apassword VARCHAR ( 100 ) NOT NULL COMMENT '密码',
		aemail VARCHAR ( 20 ) NOT NULL UNIQUE COMMENT '管理员邮箱',
		STATUS INT ( 2 ) NOT NULL COMMENT '管理员状态'-- 0表示冻结，1表示普通管理员,2表示超级管理员
		
	) ENGINE = INNODB AUTO_INCREMENT = 10001 DEFAULT CHARSET = utf8 COLLATE = utf8_bin;
	
	
	
	
	-- 车次类型表
	create table if not exists train_type (
		tid int primary key auto_increment comment '车次类型id' ,
		tname VARCHAR(5) unique not null comment '车次类型名称'
		 
		
	)ENGINE = INNODB AUTO_INCREMENT = 10001 DEFAULT CHARSET = utf8 COLLATE = utf8_bin;
	
	
	
	
	-- 站点表
	create table if not exists site(
		sid int primary key auto_increment comment '站点编号' ,
		sname varchar(10) not null unique comment '站点名称' ,
		status int(2) not null  comment '站点状态'
		
		
	)ENGINE = INNODB AUTO_INCREMENT = 10001 DEFAULT CHARSET = utf8 COLLATE = utf8_bin;
	
	
	
-- 创建列车车次表
CREATE TABLE
IF
	NOT EXISTS train_number (
		tid INT PRIMARY KEY AUTO_INCREMENT COMMENT '列车id',
		tname VARCHAR ( 10 ) NOT NULL COMMENT '列车名称',
		typeid int COMMENT '车次类型id' ,
		
		startsite int not null comment '起始站点编号' ,
		endsite int not null comment '到达站点编号' ,
		duration varchar(10) not null COMMENT '所需时长' ,
		
		rwo int COMMENT '软卧总数量',
		rwoprice DECIMAL(10,2) comment '软卧价格' ,
		rzuo int COMMENT '软座总数量',
		rzuoprice DECIMAL(10,2) comment '软座价格' ,
		ywo int COMMENT '硬卧总数量',
		ywoprice DECIMAL(10,2) comment '硬卧价格' ,
		yzuo int COMMENT '硬座总数量',
		yzuoprice DECIMAL(10,2) comment '硬座价格' ,
		zhan int comment '站票总数量' ,
		zhanprice DECIMAL(10,2) comment '站票价格' ,
		wostudentprice DECIMAL(10,2) comment '卧铺学生票价格' ,
		zuostudentprice DECIMAL(10,2) comment '座铺学生票价格' , 
		STATUS INT ( 2 ) NOT NULL COMMENT '车次状态',
		
		CONSTRAINT FK_train_number_typeid FOREIGN KEY(typeid) REFERENCES train_type(tid) , -- 列车类型的外键
		CONSTRAINT FK_train_number_startsite FOREIGN KEY(startsite) REFERENCES site(sid) , 
		CONSTRAINT FK_train_number_endsite FOREIGN KEY(endsite) REFERENCES site(sid) 
		
	) ENGINE = INNODB AUTO_INCREMENT = 10001 DEFAULT CHARSET = utf8 COLLATE = utf8_bin;
	
	
	
	-- 运营表
	create table if not exists operate(
		oid int primary key auto_increment comment '运营编号' ,
		tid int not null comment '列车车次id' ,
		rworemain int comment '软卧剩余数量' ,
		yworemain int comment '硬卧剩余数量' ,
		rzuoremain int comment '软座剩余数量' ,
		yzuoremain int comment '硬座剩余数量' ,
		zhanremain int comment '站票剩余数量' ,
		date date not null comment '出发日期' ,
		starttime time not null comment '出发时间' ,
		endtime time not null comment '到达时间' ,  
		status int(2) not null  comment '站点状态' , -- 误点，正常 
		
		CONSTRAINT FK_operate_tid FOREIGN KEY(tid) REFERENCES train_number( tid ) 
		
		
	)ENGINE = INNODB AUTO_INCREMENT = 10001 DEFAULT CHARSET = utf8 COLLATE = utf8_bin;
	
	
	
	-- 订单表
	drop table if exists orderitems ;
	create table if not exists orderitems(
		oid int primary key auto_increment comment '订单编号' ,
		operateid int not null  comment '运营编号' ,
		uid int not null COMMENT '用户id',
		identify VARCHAR ( 100 ) NOT NULL COMMENT '乘车人身份证',
		buytime datetime not null comment '购买时间' ,
		name varchar(10) not null comment '乘车人姓名' ,
		money NUMERIC(10,2) not null comment '支付金额' ,
		type varchar(10) not null comment '票的类型' ,
		seattype varchar(10) not null comment '座位类型' ,
		seatnumber int not null comment '座位号',
		status int(2) not null  comment '订单状态', -- 未发车，已发车 
		
		CONSTRAINT FK_orderitems_operateid FOREIGN KEY(operateid) REFERENCES operate( oid ) ,
		CONSTRAINT FK_orderitems_uid FOREIGN KEY(uid) REFERENCES users( uid ) 
		
		
	)ENGINE = INNODB AUTO_INCREMENT = 10001 DEFAULT CHARSET = utf8 COLLATE = utf8_bin;
	
	
	-- 退票表
	drop table if exists refund_ticket ;
	create table if not exists refund_ticket(
		rid int primary key auto_increment comment '退票编号' ,
		operateid int not null comment '运营编号' ,
		uid int not null COMMENT '用户id',
		identify VARCHAR ( 100 ) NOT NULL COMMENT '乘车人身份证',
		name varchar(10) not null comment '乘车人姓名' ,
		refundtime datetime not null comment '退票时间' ,
		money NUMERIC(10,2) not null comment '退还金额' , 
		status int(2) not null  comment '退票状态', -- 待审核，通过，失败 
		
		CONSTRAINT FK_refund_ticket_operateid FOREIGN KEY(operateid) REFERENCES operate( oid ) ,
		CONSTRAINT FK_refund_ticket_uid FOREIGN KEY(uid) REFERENCES users( uid )
		
		
	)ENGINE = INNODB AUTO_INCREMENT = 10001 DEFAULT CHARSET = utf8 COLLATE = utf8_bin;
	
	
	
	
	
	
	
	
	