-- 创建role表
CREATE TABLE role(
  id INT NOT NULL auto_increment comment '角色id',
  login_name VARCHAR (20) NOT NULL UNIQUE comment '登录名',
  login_password VARCHAR (32) NOT NULL comment '登录密码',
  create_id INT NOT NULL comment '创建人角色Id',
  role_type INT NOT NULL comment '角色类型,0=A,1=T,2=S',
  role_status INT NOT NULL DEFAULT '0' comment '记录角色状态，默认为0在职，1为离职',
  create_time TIMESTAMP NOT NULL DEFAULT current_timestamp comment '创建时间',
  PRIMARY KEY (id)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 comment='角色表';

-- 插入超级管理员信息
INSERT INTO
  role(login_name, login_password, create_id, role_type)
VALUES
  ('admin','123456',1000,0)

-- teacher_information表
CREATE TABLE teacher_information(
  id INT NOT NULL AUTO_INCREMENT comment '教师信息id',
  login_name VARCHAR (20) NOT NULL UNIQUE comment '登录名',
  number VARCHAR(5) NOT NULL unique comment '工号',
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
  create_time TIMESTAMP NOT NULL DEFAULT current_timestamp comment '创建时间',
  PRIMARY KEY (id),
  FOREIGN KEY (login_name) REFERENCES role(login_name)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 comment='教师信息表';

-- student_information表
CREATE TABLE student_information(
  id INT NOT NULL AUTO_INCREMENT comment '学生信息id',
  login_name VARCHAR (20) NOT NULL UNIQUE comment '登录名',
  number VARCHAR(8) NOT NULL unique comment '学号',
  name VARCHAR(10) NOT NULL comment '姓名',
  sex VARCHAR(2) NOT NULL comment '性别',
  id_cart VARCHAR(18) NOT NULL comment '身份证号',
  nation VARCHAR (20) NOT NULL comment '民族',
  place VARCHAR(20) NOT NULL comment '籍贯',
  address VARCHAR(128) NOT NULL comment '地址',
  birthday VARCHAR(10) NOT NULL comment '生日',
  phone VARCHAR (11) NOT NULL comment '电话',
  classes VARCHAR (10) NOT NULL comment '班级',
  enrolment_time VARCHAR (10) NOT NULL comment '入学时间',
  create_time TIMESTAMP NOT NULL DEFAULT current_timestamp comment '创建时间',
  PRIMARY KEY (id),
  FOREIGN KEY (login_name) REFERENCES role(login_name)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 comment='学生信息表';

-- classes 表
CREATE TABLE classes(
  id INT NOT NULL auto_increment comment '班级id',
  name VARCHAR (10) NOT NULL comment '班级名称',
  create_time TIMESTAMP NOT NULL DEFAULT current_timestamp comment '创建时间',
  PRIMARY KEY (id)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 comment='班级表';

-- 插入三条班级记录
INSERT INTO
    classes(name)
  VALUES
    ('高一(1)班'),
    ('高二(1)班'),
    ('高三(1)班')

-- achievement表
CREATE TABLE achievement(
  id INT NOT NULL auto_increment comment '成绩id',
  s_information_id INT NOT NULL comment '学生信息id',
  t_information_id INT NOT NULL comment '教师信息id',
  classes VARCHAR (10) NOT NULL comment '班级',
  year VARCHAR (9) NOT NULL comment '成绩记载学年',
  mouth VARCHAR (2) NOT NULL comment '成绩记载月',
  num VARCHAR (2) NOT NULL comment '次数',
  subject VARCHAR (10) NOT NULL comment '科目',
  score INT NOT NULL comment '分数',
  status INT NOT NULL comment '成绩状态,0为有效，1为无效',
  create_time TIMESTAMP NOT NULL DEFAULT current_timestamp comment '创建时间',
  PRIMARY KEY (id)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 comment='成绩表';

-- menu表
CREATE TABLE menu(
  id INT NOT NULL auto_increment comment '菜单表',
  name VARCHAR (20) NOT NULL comment '菜单名称',
  modular VARCHAR (10) NOT NULL comment '菜单模块',
  teacher_display INT NOT NULL comment '教师可见，0不可见，1为可见',
  student_display INT NOT NULL comment '学生可见，0不可见，1为可见',
  url VARCHAR(50) NOT NULL comment '菜单url',
  create_time TIMESTAMP NOT NULL DEFAULT current_timestamp comment '创建时间',
  PRIMARY KEY (id)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 comment='菜单表';

-- record表
CREATE TABLE record(
  id INT NOT NULL auto_increment comment '记录id',
  login_name VARCHAR (20) NOT NULL comment '操作人登录名',
  table_name VARCHAR (20) NOT NULL comment '表名',
  key_id INT NOT NULL comment '主键id',
  update_column VARCHAR (30) NULL comment '修改字段',
  update_value VARCHAR (128) NULL comment '修改值',
  original_value VARCHAR (128) NULL comment '原值',
  type INT NOT NULL DEFAULT 0 comment '类型, 0修改, 1删除, 默认为0',
  create_time TIMESTAMP NOT NULL DEFAULT current_timestamp comment '创建时间',
  PRIMARY KEY (id)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 comment='记录表';