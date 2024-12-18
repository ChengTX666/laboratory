
explain
select * from reservation where laboratory_id='1';#ref

explain
select * from reservation where teacher_id='1';#ref
/*ref*/
explain
select * from course where teacher_id='1' and type='abc1' ;

/*基于周 天 节 查可用的实验室*/
explain
select * from laboratory l
left join reservation r
on l.id=r.laboratory_id and week=1 and day=1 and period=1
where laboratory_id is null ;

/*某一天都有空，根据周 天查实验室和节次*/

create table if not exists temp
(
     id      char(19) not null primary key,
    `period` tinyint unsigned not null
);

select * from laboratory,temp;

select * from laboratory l
cross join temp t
left join reservation r
 on l.id = r.laboratory_id and t.period=r.period and week=1 and day=1
where laboratory_id is null;

explain
select  l.id,l.name,group_concat(t.`period`) as free_period from laboratory l
cross join temp t
left join reservation r
on l.id = r.laboratory_id and t.period=r.period and week=1 and day=1
where laboratory_id is null
group by l.id;




#拿老师所有的预约记录生成时间表 ref
    explain
    select * from reservation where teacher_id='1';
#拿老师教的所有课程 ref
    explain
    select * from course where  teacher_id='1';
#点击课程：所有实验室(满足人数) ALL
    explain
    select * from laboratory;
#点击实验室：拿对应实验室的预约记录 ref
    explain
    select * from reservation where laboratory_id='1';
#提交预约

#查每个实验室的预约个数
explain
select laboratory_id,count(*) from reservation group by laboratory_id;

select group_concat(period,day,week separator ',') from reservation group by laboratory_id;

select group_concat(id) from reservation group by laboratory_id;
select count(*) from reservation group by laboratory_id;