```java
public class Test092 {
    public static void main(String[] args){
        String l = "HelloWorld";
        String r = "HelloWorld";
        String t = l.substring(2,5);

        System.out.println( r == l );
        System.out.println( "llo" == t );
        System.out.println( "llo".equals(t));

        String t1 = "10101,100";
        System.out.println(t1.substring(0,t1.indexOf(",")));
        System.out.println(t1.substring(t1.indexOf(",")+1));
    }
}
```

- ""로 생성한 String은 Pool을 쓴다. 포인터 비교가 가능. 그 이외의 경우(substring 등등)은 Pool을 쓴다는 보장이 없기 때문에 equals로 비교한다

```sql
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
```

- tinyint : 1바이트
- int : 4바이트

### 테이블에 있어야 하는 개념

    1. 필드 (Field) - 컬럼
        - 자료형을 지정한다 (int, tinyint)
        - 같은 자료형의 같은 의미의 값이 와야 한다.

    2. 레코드 (Record) - Row
        - 입력의 단위
        - 데이터들이 연관되어진 묶음이어야 한다.

    "이 두가지 개념이 있어야 테이블이라 할 수 있다"

```sql
delete from study01t where id = 10101;
delete from study01t where id < 10104;

-- 조건을 주지 않으면 다 지운다.
delete from study01t;

-- 가끔 잘 써먹는 방법
delete from study01t where 1=1;
```

```sql
update study01t set score = 110 where id = 10101;
update study01t set score = score - 10 where id = 10101;
update study01t set score = id - 10000 where id = 10101;
update study01t set score = id - 10000 where id != 10101;
update study01t set id = 0,score=0 where id = 10101;
```

- 기존의 값을 활용해서 만들 수 있다.
- 2개 이상의 필드를 동시에 수정 가능하다.

```sql
select * from study01t where id = 10101;
select * from study01t where id != 10101;

-- 특정 필드만 가져올 때
select id from study01t where id = 10102;
select score,id from study01t where id !=10101;

-- select 수행시 데이터를 가공해서 보여줄 수 있다.
select score+5,id from study01t where id !=10101;

-- 컬럼명을 바꾸어서 출력이 가능하다.(보여지는 것만 바뀐다.)
select score+5 as sungjuk, id from study01t where id != 10101;

-- 하나의 컬럼을 여러번 출력해도 무방하다.
select score, score as sungjuk from study01t;
```

```sql
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
```

- 문자열: '' 감싼다. char or varchar 자료형.  
  char: 고정길이 문자열( 학번 주민번호)

- char는 무조건 고정길이. 'a01'을 넣어도 무조건 5자리 차지(낭비 각오) 대신에 쿼리속도가 훨씬 빠르다.

- varchar은 가변길이 'apple'을 넣으면 5자리만 공간 사용. 낭비 적다.

- varchar, char는 최대길이 넘겨서 넣을 수 없다.

## Q. 역삼동에 사는 학생은 ?

```sql
select * from studentT where addr = '역삼동';
select * from studentT where addr like '역%';
select * from studentT where addr like '%삼동';
```

'역%': 역으로 시작하는...
'%삼동' : 삼동으로 끝나는...

```sql
-- 부분 문자열 추출
select substr(addr,1,2) from studentt;

select * from studentt where substr(addr,1,2) = '역삼';
```

```sql
select length(addr) from studentt;
```

- 글자 길이. utf-8인 경우는 한글은 3바이트

## aggregate functions

- min
- max
- count
- avg
- sum

```sql
select * from scoret where subid = 'KOR1';
select min(score) from scoret where subid='KOR1';
select sum(score) from scoret where subid='KOR1';
select avg(score) from scoret where subid='KOR1';
```

## Q. 10101 학생의 평균 성적은?

```sql
select avg(score) from scoret where stId=10101;
```

```sql
select stId from scoret where subid = 'KOR1' and score <= 70;
select * from scoret where subid='MAT1' and (score = 60 or score = 80);
select * from scoret where subid='MAT1' and score in (60,80);
select count(*) from scoret where subid='MAT1' and score in (60,80);
```

- 결과 레코드의 개수를 count(\*)로 알 수 있다.

- studentt, subjectt, scoret 는 얽혀 있다. '여러개의 테이블이 연관관계를 가지고 데이터베이스를 구성'

Q. 역삼동 학생들의 국어 성적은 ?

```sql
select * from scoret where subid = 'KOR1' and stid in(select stid from studentt where addr LIKE '%역삼%');
```

- 여러개 나오는 결과를 이용할 때는 IN 또는 NOT IN을 사용한다.

- 하나의 쿼리의 결과를 이용해서 다른 쿼리를 돌릴 수있다. 이것을 '서브 쿼리' 라고 한다.

## Q. 국어에서 평균 이하의 점수를 받은 학생은 누구?

```sql
select * from scoret where subid='KOR1' and score <= (select avg(score) from scoret where subid='KOR1');
```

- 유일한 서브쿼리 결과와 비교는 =, !=, <, <=, >, >= 을 쓴다.
- 서브쿼리는 반드시 괄호( () )로 묶어 주어야 한다.

## Q. 김씨 성 가진 학생들의 수학 평균은?

```sql
select avg(score) from scoret where subid='MAT1' and stid in (select stid from studentt where name LIKE '김%');
```

## Q. 영어에서 70점 이하의 점수를 받은 학생들의 성적을 -5 점 하라.

```sql
update scoret set score = score - 5 where subid='ENG1' and stid in (select * from (select stid from scoret where subid='ENG1' and score <= 70) as temp);
```

## Q. 영어에서 70점 이하의 점수를 받은 학생들의 성적을 +5 점 하라.

```sql
update scoret set score = score + 5
where subid='ENG1' and stid in
    (select * from (select stid from scoret where subid='ENG1' and score <= 70) as temp);
```

## Aggregate function은 GROUP BY, HAVING과 연동된다.

```sql
select avg(score) from scoret group by stid;
```

- stid에 동일한 값을 가진 레코드를 짜메어서 평균 낸 결과.

- group by를 썼을때는 group by에 지정된 칼럼만 select 절에 와야 한다.

```sql
(x) select avg(score) from scoret group by stid where avg(score) <= 75;
```

- 왜 안되나? where는 통계처리에 우선한다. 해서 having은 통계처리 이후에 동작한다.

```sql
select avg(score) from scoret group by stid having avg(score) <= 75;
```

- 서브 쿼리는 크게 3종류로 나뉜다.

  - WHERE 절의 서브쿼리.
  - FROM 절의 서브퀴리 ( inline view ).
  - select 절의 서브쿼리 ( 엄청난 결과/ 엄청난 부담 ).

- FROM 절의 서브퀴리: select 결과를 마치 table처럼 보면 된다.

```sql
select stid, round(avg(score),2) as xx from scoret group by stid;

-- --> 이것을 하나의 테이블로 보고

select * from (select stid as id, round(avg(score),2) as xx from scoret group by stid)t where xx <= 75;

create view score2v as select stid, round(avg(score),2) as xx from scoret group by stid;

select * from score2v where xx <= 75;
```

- view 는 실제로 데이터가 존재하는 테이블이 아니라 테이블 데이터를 이용해서 만들어진 하나의 결과화면일 뿐이다.

- view 를 대상으로 수정 삭제는 무의미하다.

```sql
select stid, name from studentt;
select stid, name, (select avg(score) from scoret where stid='10101' ) as avg from studentt;
```

```sql
-- 원래는 테이블명.컬럼명 으로 동작한다.(우리는 생략)
select studentt.stid, studentt.name from studentt;

-- studentt 가 길기 때문에 x로 바꾸어서 쓴다.
select x.stid, x.name from studentt as x;

select x.stid, x.name, (select avg(score) from scoret where stid= x.stid ) as avg from studentt as x;
```

```sql
-- 속도 빠름, 생성 이후의 데이터는 반영 안된다.
select * from temp01t;

-- 오버헤드 있음. 생성 이후의 데이터 변경까지도 반영된다.
select * from temp01v;
```

```sql
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
```

- 임시테이블과 뷰는 흩어진 데이터에서 자신이 원하는 데이터로 가공할 수 있는 방법을 제공한다.

- 뷰는 오버헤드가 있지만 데이터의 변경을 즉각 반영한다. 임시테이블은 오버헤드 적지만 데이터의 변경을 즉각 반영 못함.

- select 서브쿼리는 오버헤드가 크다. (1000명의 등수를 처리한 결과를 1000명이 동시 열람하면 100만건의 쿼리가 동작하는 셈 + group by 오버헤드 포함)

- 임시테이블은 이러한 부담을 극적으로 줄여준다.

```sql
create table study3t (
    no int not null auto_increment primary key,
    theTime datetime not null
);

insert into study3t values(default,now());
```

- mysql은 일련 번호 auto_increment primary key 사용( 오라클은 sequence을 이용한다.)

- now()는 현재시간. 그것을 입력할 때 datetime 자료형을 쓴다.

```sql
select no, date_add(theTime, INTERVAL 1 MONTH) from study3t;
select no, date_add(theTime, INTERVAL 10 DAY) from study3t;
select no, date_add(theTime, INTERVAL 4 HOUR) from study3t;
```

- datetime 자료형에 들어있는 값은 연산이 가능하다.
- 날짜 데이터를 다룰 때 연산의 필요성이 없으면 char 쓴다.

```sql
create table study4t (
    no int not null auto_increment primary key,
    theTime char(19) not null
);
```

- char타입으로 연산이 불가능 하다.
