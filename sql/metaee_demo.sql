-- 模块 sql
--
insert into sys_menu
values ('4', '功能演示', '0', '4', 'demo', null, '', 1, 0, 'M', '0', '0', '', 'example', 'admin', sysdate(), '', null,
        '功能演示目录');

drop table if exists demo_student;
create table demo_student
(
    student_id       int(11) auto_increment comment '编号',
    classes_id       int(11)      default null comment '所属班级ID',
    student_name     varchar(30)  default '' comment '学生名称',
    student_age      int(3)       default null comment '年龄',
    student_sex      char(1)      default '0' comment '性别（0男 1女 2未知）',
    student_birthday datetime comment '生日',
    status           char(1)      default '0' comment '状态（0正常 1停用）',
    create_by        varchar(64)  default '' comment '创建者',
    create_time      datetime comment '创建时间',
    update_by        varchar(64)  default '' comment '更新者',
    update_time      datetime comment '更新时间',
    del_flag         tinyint      default 0 comment '删除标志（0逻辑未删除值 1逻辑已删除值）',
    remark           varchar(500) default null comment '备注',
    primary key (student_id)
) engine = innodb
  auto_increment = 1 comment = '单表';



drop table if exists demo_classes;
create table demo_classes
(
    classes_id      int(11) auto_increment comment '编号',
    classes_name    varchar(30)  default '' comment '班级名称',
    classes_grade   int(3)       default null comment '班级年级',
    classes_teacher varchar(30)  default '' comment '班主任',
    status          char(1)      default '0' comment '状态（0正常 1停用）',
    create_by       varchar(64)  default '' comment '创建者',
    create_time     datetime comment '创建时间',
    update_by       varchar(64)  default '' comment '更新者',
    update_time     datetime comment '更新时间',
    del_flag        tinyint      default 0 comment '删除标志（0逻辑未删除值 1逻辑已删除值）',
    remark          varchar(500) default null comment '备注',
    primary key (classes_id)
) engine = innodb
  auto_increment = 1 comment = '主子表';



drop table if exists demo_school;
create table demo_school
(
    school_id   int(11) auto_increment comment '编号',
    parent_id   bigint(20)   default 0 comment '父编号id',
    ancestors   varchar(50)  default '' comment '祖级列表',
    order_num   int(4)       default 0 comment '显示顺序',
    school_name varchar(30)  default '' comment '学生名称',
    status      char(1)      default '0' comment '状态（0正常 1停用）',
    create_by   varchar(64)  default '' comment '创建者',
    create_time datetime comment '创建时间',
    update_by   varchar(64)  default '' comment '更新者',
    update_time datetime comment '更新时间',
    del_flag    tinyint      default 0 comment '删除标志（0逻辑未删除值 1逻辑已删除值）',
    remark      varchar(500) default null comment '备注',
    primary key (school_id)
) engine = innodb
  auto_increment = 1 comment = '树表';









