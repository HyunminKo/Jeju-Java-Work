create database study;

use study;

CREATE TABLE study01t (
    id int not null,
    score tinyint not null
);

desc study01t;

insert into study01t values(10101,100);
insert into study01t values(10102,90);
insert into study01t values(10103,80);
insert into study01t (score, id) values(90,10104);

delete from study01t where id = 10101;
delete from study01t where id < 10104;
delete from study01t;
delete from study01t where 1=1;

update study01t set score = 110 where id = 10101;
update study01t set score = score - 10 where id = 10101;
update study01t set score = id - 10000 where id = 10101;
update study01t set score = id - 10000 where id != 10101;
update study01t set id = 0,score=0 where id = 10101;

select * from study01t where id = 10101;
select * from study01t where id != 10101;
select id from study01t where id = 10102;
select score,id from study01t where id !=10101;
select score+5,id from study01t where id !=10101;
select score+5 as sungjuk, id from study01t where id != 10101;
select score, score as sungjuk from study01t;

create table study02t (
    id char(5) not null,
    name varchar(10) null
);

insert into study02t (id ,name) values ('a0001','abcd');
insert into study02t (id ,name) values ('a0001','HelloWorldxxx');
insert into study02t (id ,name) values ('a0001','HelloWorld');
insert into study02t (id ,name) values ('a01','apple');

select id,name from study02t;
select concat(id,'*') from study02t;
-- oracle: 'a01    *'

select * from studentT where addr = '역삼동';
select * from studentT where addr like '역%';
select * from studentT where addr like '%삼동';

select substr(addr,1,2) from studentt;
select * from studentt where substr(addr,1,2) = '역삼';

select length(addr) from studentt;

select * from scoret where subid = 'KOR1';
select min(score) from scoret where subid='KOR1';
select sum(score) from scoret where subid='KOR1';
select avg(score) from scoret where subid='KOR1';

select avg(score) from scoret where stId=10101;

select stId from scoret where subid = 'KOR1' and score <= 70;
select * from scoret where subid='MAT1' and (score = 60 or score = 80);
select * from scoret where subid='MAT1' and score in (60,80);
select count(*) from scoret where subid='MAT1' and score in (60,80);

select * from scoret where subid = 'KOR1' and stid in(select stid from studentt where addr LIKE '%역삼%');
select * from scoret where subid='KOR1' and score <= (select avg(score) from scoret where subid='KOR1');
select avg(score) from scoret where subid='MAT1' and stid in (select stid from studentt where name LIKE '김%');
update scoret set score = score - 5 where subid='ENG1' and stid in (select * from (select stid from scoret where subid='ENG1' and score <= 70) as temp);
update scoret set score = score + 5 where subid='ENG1' and stid in (select * from (select stid from scoret where subid='ENG1' and score <= 70) as temp);

select avg(score) from scoret group by stid;
select avg(score) from scoret group by stid having avg(score) <= 75;


select stid, round(avg(score),2) as xx from scoret group by stid;
-- --> 이것을 하나의 테이블로 보고
select * from (select stid as id, round(avg(score),2) as xx from scoret group by stid)t where xx <= 75;
create view score2v as select stid, round(avg(score),2) as xx from scoret group by stid;
select * from score2v where xx <= 75;

select stid, name from studentt;
select stid, name, (select avg(score) from scoret) as avg from studnett;
select studentt.stid, studentt.name from studentt;

-- studentt 가 길기 때문에 x로 바꾸어서 쓴다.
select x.stid, x.name from studentt as x;
select x.stid, x.name, (select avg(score) from scoret where stid= x.stid ) as avg from studentt as x;

select stid, avg(score) as avg from scoret group by stid);
select x.stid, x.avg from ( select stid, avg(score) as avg from scoret group by stid) as x;

create table temp01t as
    select stid, avg(score) as avg from
    scoret group by stid;

create view temp01v as
    select stid, avg(score) as avg from
    scoret group by stid;

-- 속도 빠름, 생성 이후의 데이터는 반영 안된다.
select * from temp01t;

-- 오버헤드 있음. 생성 이후의 데이터 변경까지도 반영된다.
select * from temp01v;

-- 등수 구하자.
select x.stid, x.avg from temp01t as x;

select x.stid, x.avg, 
    (select count(*) + 1 from temp01t where avg > x.avg)r
    from temp01t as x;

create table temp02t as
    select x.stid, x.avg,
    (select count(*) + 1 from temp01t where avg > x.avg)r
    from temp01t as x;

select * from temp02t order by r;
select * from temp02t order by r desc;

create table study3t (
    no int not null auto_increment primary key,
    theTime datetime not null
);

insert into study3t values(default,now());

select no, date_add(theTime, INTERVAL 1 MONTH) from study3t;
select no, date_add(theTime, INTERVAL 10 DAY) from study3t;
select no, date_add(theTime, INTERVAL 4 HOUR) from study3t;

create table study4t (
    no int not null auto_increment primary key,
    theTime char(19) not null
);
