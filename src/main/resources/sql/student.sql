-- 创建role表
CREATE TABLE role(
  id int not null auto_increment comment '角色id',
  login_name varchar(20) not null unique comment '登录名',
  login_password varchar(32) not null comment '登录密码',
  create_id int not null comment '创建人id',
  user_type int not null comment '角色类型,0=A,1=T,2=S',
  create_time timestamp not null default current_timestamp comment '创建时间',
  PRIMARY KEY (id)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- 插入超级管理员信息
INSERT INTO
  role(login_name, login_password, create_id, user_type)
VALUES
  ('admin','123456',1000,0)

-- teacher_information表
CREATE TABLE teacher_information(
  id INT NOT NULL AUTO_INCREMENT COMMENT '信息id',
  login_name VARCHAR(20) NOT NULL UNIQUE comment '登录名',
  number int NOT NULL comment '工号',
  name VARCHAR(10) NOT NULL comment '姓名',
  sex VARCHAR(2) NOT NULL comment '性别',
  id_cart VARCHAR(18) NOT NULL comment '身份证号',
  positional_titles VARCHAR(12) NOT NULL comment '职称',
  nation VARCHAR (20) NOT NULL comment '民族',
  place VARCHAR(20) NOT NULL comment '籍贯',
  address VARCHAR(128) NOT NULL comment '地址',
  birthday VARCHAR(10) NOT NULL comment '生日',
  phone VARCHAR (11) NOT NULL comment '电话',
  department VARCHAR (15) NOT NULL comment '部门',
  classes VARCHAR (50) NOT NULL comment '班级',
  subject VARCHAR (4) NOT NULL comment '学科',
  create_time timestamp not null default current_timestamp comment '创建时间',
  PRIMARY KEY (id),
  FOREIGN KEY (login_name) REFERENCES role(login_name)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='教师信息表';

-- department表
CREATE TABLE department(
  id INT NOT NULL auto_increment comment '部门id',
  name VARCHAR (10) NOT NULL comment '部门名称',
  create_time timestamp not null default current_timestamp comment '创建时间',
  PRIMARY KEY (id)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='部门表';

-- 插入3个部门表数据，学生处，教研组，学生部
INSERT INTO
  department(name)
VALUES
  ('学生处'),
  ('教研组'),
  ('学生部')