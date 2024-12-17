create table if not exists `laboratory`
(
    id         char(19) not null primary key,
    name       varchar(20) not null comment '丹青901',
    config     varchar(100) not null ,
    capacity   tinyint unsigned not null,
    status     varchar(10) not null default '开放',/*增加状态字段*/
    manager    json not null comment '{"name":,"phone":}'/* 改为了单个对象 取消id*/,
    create_time datetime not null default current_timestamp,
    update_time datetime not null default current_timestamp on update current_timestamp,
    index (name,capacity)/*查询哪个楼时按大小排序*/
);
create table if not exists `notice`
(
    id         char(19) not null primary key,
    title      varchar(50) not null,
    content    text not null ,
    publisher  varchar(20) null ,/* 添加了发布者名字 */
    create_time datetime not null default current_timestamp,
    update_time datetime not null default current_timestamp on update current_timestamp
);

create table if not exists `lab_user`
(
    id          char(19) not null primary key,
    name        varchar(10) not null ,
    account     varchar(20) not null unique,
    password    varchar(65) NOT NULL,
    role        char(4),/*随机，防止猜到*/
    phone       char(11) NULL,
    create_time datetime not null default current_timestamp,
    update_time datetime not null default current_timestamp on update current_timestamp,
    unique (account)
);
create table if not exists `course`
(
    id              char(19) not null primary key,
    name            varchar(20) not null ,
    type            char(4) null,/*必修课abc1，选修课def2 先只考虑选修,默认def2*/
    teacher_id      char(19) not null,
    teacher_name    varchar(10) not null ,
    require_config  varchar(50) null ,
    require_number  tinyint unsigned not null ,/*添加课程需要的人数*/
    total           tinyint unsigned not null,/*总共的课时*/
    create_time datetime not null default current_timestamp,
    update_time datetime not null default current_timestamp on update current_timestamp,
    index(type,teacher_id)
);
/**/
create table if not exists `reservation`
(
    id              char(19) not null primary key,
    teacher_id      char(19) not null,
    teacher_name    varchar(10) null,
    laboratory_id   char(19) not null ,
    laboratory_name varchar(20)not null ,
    course_id       char(19)not null,
    course_name     varchar(20) not null,
    `period`        tinyint unsigned not null,/* 1 2 3 4 */
    day             tinyint unsigned not null,/* 1 2 3 4 5 6 7*/
    week            tinyint unsigned not null ,/*同上 1 2 3...18*/
    create_time datetime not null default current_timestamp,
    update_time datetime not null default current_timestamp on update current_timestamp,

    unique (laboratory_id,`period`,day,week),/* 点击实验室准备选课,查看这个实验室所有的预约记录*/
    index(teacher_id,course_id)/*初始老师看自己课表:看自己预约过的记录和记录具体的时间*/
);


