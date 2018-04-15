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