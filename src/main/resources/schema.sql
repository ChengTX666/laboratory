create table if not exists `laboratory`(
    id         char(19) not null primary key,
    name       varchar(20) not null ,
    config     varchar(100) not null ,
    capacity   tinyint unsigned not null,
    manager    json not null comment '{["id":,"name":,"phone":]}'
);
create table if not exists `notice`(
    id         char(19) not null primary key,
    title      varchar(50) not null,
    content    text not null ,
    create_time datetime not null default current_timestamp,
    update_time datetime not null default current_timestamp on update current_timestamp
);

create table if not exists `user`(
    id          char(19) not null primary key,
    name        varchar(10) not null ,
    account     varchar(20) not null unique,
    password    varchar(65) NOT NULL,
    role        char(4),/*随机，防止猜到*/
    phone       char(11) NULL,
    create_time datetime not null default current_timestamp,
    update_time datetime not null default current_timestamp on update current_timestamp,
    unique index (role,account)
);
create table if not exists `course`(
    id              char(19) not null primary key,
    name            varchar(20) not null ,
    type            char(4) null,/*理论课abc1，实验课def2*/
    teacher_id      char(19) not null,
    teacher_name    varchar(10) not null ,
    require_config  varchar(50) null ,
    theory          json null comment '{"period","day","weeks","class"}',
    total           tinyint unsigned not null,/*总共的课时*/
    reserved        tinyint unsigned not null,/*已经预约的课时*/
    create_time datetime not null default current_timestamp,
    update_time datetime not null default current_timestamp on update current_timestamp,
    index(type,teacher_id,
        (cast(theory->>'$.period' as unsigned ) collate utf8mb4_bin),
        (cast(theory->>'$.day' as unsigned ) collate utf8mb4_bin))
);
create table if not exists `reservation`(
    id              char(19) not null primary key,
    teacher_id      char(19) not null,
    laboratory_id   char(19) not null ,
    laboratory_name varchar(20)not null ,
    course_id       char(19)not null,
    course_name     varchar(20) not null,
    `period`        tinyint unsigned not null,/* 1 2 3 4 */
    day             tinyint unsigned not null,/* 1 2 3 4 5 6 7*/
    weeks           varchar(100) not null ,/*用逗号分割 "1,2,3"*/
    create_time datetime not null default current_timestamp,
    update_time datetime not null default current_timestamp on update current_timestamp,

    index(laboratory_id,day,`period`),/* 点击实验室准备选课,查看这个实验室所有的预约记录*/
    index(teacher_id,course_id)/*初始老师看自己课表:看自己预约过的记录和记录具体的时间*/
);
explain
select * from reservation where teacher_id='1';

