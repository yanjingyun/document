#–1.学生表 
#Student(s_id,s_name,s_birth,s_sex) –学生编号,学生姓名, 出生年月,学生性别
CREATE TABLE `Student` (
    `s_id` VARCHAR(20),
    s_name VARCHAR(20) NOT NULL DEFAULT '',
    s_brith VARCHAR(20) NOT NULL DEFAULT '',
    s_sex VARCHAR(10) NOT NULL DEFAULT '',
    PRIMARY KEY(s_id)
);

#–2.课程表 
#Course(c_id,c_name,t_id) – –课程编号, 课程名称, 教师编号 
create table Course(
    c_id varchar(20),
    c_name VARCHAR(20) not null DEFAULT '',
    t_id VARCHAR(20) NOT NULL,
    PRIMARY KEY(c_id)
);


#3.教师表 
#Teacher(t_id,t_name) –教师编号,教师姓名 

CREATE TABLE Teacher(
    t_id VARCHAR(20),
    t_name VARCHAR(20) NOT NULL DEFAULT '',
    PRIMARY KEY(t_id)
);


#4.成绩表 
#Score(s_id,c_id,s_score) –学生编号,课程编号,分数

Create table Score(
    s_id VARCHAR(20),
    c_id VARCHAR(20) not null default '',
    s_score INT(3),
    primary key(`s_id`,`c_id`)
);

#--插入学生表测试数据
#('01' , '赵雷' , '1990-01-01' , '男')
	insert into Student values('01' , '赵雷' , '1990-01-01' , '男');
	insert into Student values('02' , '钱电' , '1990-12-21' , '男');
	insert into Student values('03' , '孙风' , '1990-05-20' , '男');
	insert into Student values('04' , '李云' , '1990-08-06' , '男');
	insert into Student values('05' , '周梅' , '1991-12-01' , '女');
	insert into Student values('06' , '吴兰' , '1992-03-01' , '女');
	insert into Student values('07' , '郑竹' , '1989-07-01' , '女');
	insert into Student values('08' , '王菊' , '1990-01-20' , '女');

#--课程表测试数据
insert into Course values('01' , '语文' , '02');
insert into Course values('02' , '数学' , '01');
insert into Course values('03' , '英语' , '03');

#--教师表测试数据
insert into Teacher values('01' , '张三');
insert into Teacher values('02' , '李四');
insert into Teacher values('03' , '王五');

#--成绩表测试数据
insert into Score values('01' , '01' , 80);
insert into Score values('01' , '02' , 90);
insert into Score values('01' , '03' , 99);
insert into Score values('02' , '01' , 70);
insert into Score values('02' , '02' , 60);
insert into Score values('02' , '03' , 80);
insert into Score values('03' , '01' , 80);
insert into Score values('03' , '02' , 80);
insert into Score values('03' , '03' , 80);
insert into Score values('04' , '01' , 50);
insert into Score values('04' , '02' , 30);
insert into Score values('04' , '03' , 20);
insert into Score values('05' , '01' , 76);
insert into Score values('05' , '02' , 87);
insert into Score values('06' , '01' , 31);
insert into Score values('06' , '03' , 34);
insert into Score values('07' , '01' , 95);
insert into Score values('07' , '02' , 89);
insert into Score values('07' , '03' , 98);


# 查询各科成绩最高分、最低分和平均分：以如下形式显示：课程ID，课程name，最高分，最低分，平均分，及格率，中等率，优良率，优秀率
select a.c_id,
    b.c_name,
    max(s_score),
    min(s_score),
    round(avg(s_score),2),
    round(100*(sum(case when a.s_score>=60 then 1 else 0 end)/sum(case when a.s_score then 1 else 0 end)),2) as 及格率,
    round(100*(sum(case when a.s_score>=70 and a.s_score<=80 then 1 else 0 end)/sum(case when a.s_score then 1 else 0 end)),2) as 中等率,
    round(100*(sum(case when a.s_score>=80 and a.s_score<=90 then 1 else 0 end)/sum(case when a.s_score then 1 else 0 end)),2) as 优良率,
    round(100*(sum(case when a.s_score>=90 then 1 else 0 end)/sum(case when a.s_score then 1 else 0 end)),2) as 优秀率
    from score a left join course b on a.c_id = b.c_id
        group by a.c_id,b.c_name;

# 按平均成绩从高到低显示所有学生的所有课程的成绩以及平均成绩
select t.s_id,
    (select a.s_score from score a where a.s_id=t.s_id and c_id='01') as '语文',
    (select a.s_score from score a where a.s_id=t.s_id and c_id='02') as '数学',
    (select a.s_score from score a where a.s_id=t.s_id and c_id='03') as '英语',
    round(avg(t.s_score),2) as '平均分'
    from score t group by t.s_id order by '平均分' desc;


# 统计各科成绩分数段人数：课程编号,课程名称,[85-100],[70-85][60-70],[0-60]及所占百分比
select c_id as '课程编号',
    (select c_name from course c where c.c_id = t.c_id) as '课程名称',
    sum(case when s_score > 85 and s_score <= 100 then 1 else 0 end) as '85-100',
    round(sum(case when s_score > 85 and s_score <= 100 then 1 else 0 end) / count(*), 2) as '百分百',

    sum(case when s_score > 70 and s_score <= 85 then 1 else 0 end) as '70-85',
    round(sum(case when s_score > 70 and s_score <= 85 then 1 else 0 end) / count(*), 2) as '百分百',

    sum(case when s_score > 60 and s_score <= 70 then 1 else 0 end) as '60-70',
    round(sum(case when s_score > 60 and s_score <= 70 then 1 else 0 end) / count(*), 2) as '百分百',

    sum(case when s_score > 0 and s_score <= 60 then 1 else 0 end) as '0-60',
    round(sum(case when s_score > 0 and s_score <= 60 then 1 else 0 end) / count(*), 2) as '百分百'
from score t
group by t.c_id;


