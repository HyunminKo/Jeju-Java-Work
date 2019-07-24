```sql
select stid, name, (kor1 + eng1 + mat1)/3 as avg from score2t;
```

- score2t는 과목이 늘거나 줄 때에 대책이 심각하다.
- 그러나 동작속도는 무지하게 빠르다.
- score2t 와 같이 설계된 경우를 비정규화 라고 한다.

- 정규화
  - Less Column (테이블당 필드의 갯수가 적다 - 5~12개)
  - More Table ( score2t가 한 개로 되는걸 우리는 3개를 만들었다)
  - 필드와 데이터의 중복저장을 허용 안한다. (비디오점의 대여 여부)
- 비정규화
  - Less Table
  - More Column
  - 필드와 데이터의 중복저장을 허용 한다. (속도 때문에)

### 대부분 정규화를 기본으로 해서 적절한 비정규화를 도입한다.

```sql
create table student_xt as
    select stid, name, addr from score2t;

-- 테이블의 껍데기만 만드는것
create table student_xt as
    select stid, name, addr from score2t where 1=1;
-- 테이블에 select 결과를 입력 할 것.
create table student_xt as
    select stid, name, addr from score2t where 1=1;
```

```sql
-- 껍데기 만들자.
create table score_xt as
    select stid, 'KOR1' as subid, kor1 as score from score2t where 0 = 1;

insert into score_xt
    select stid, 'KOR1', kor1 as score from score2t where 1=1;

insert into score_xt
    select stid, 'ENG1', eng1 as score from score2t where 1=1;

insert into score_xt
    select stid, 'MAT1', mat1 as score from score2t where 1=1;
```

- 비정규화 된 설계에서 정규화 된 설계로 옮길 수도 있고 정규화 된 설계에서 비정규화 된 설계로 옮길 수도 있어야 한다.

### JOIN

- 서브쿼리와 유사한데 다르다
- studnett : stid(PK) <- scoret : stid(FK)
- 대부분의 JOIN은 PK - FK 사이에서 일어난다.

#### INNER JOIN

```sql
SELECT * FROM studentt INNER JOIN scoret ON studentt.stid = scoret.stid;

SELECT name, addr FROM studentt INNER JOIN scoret ON studentt.stid = scoret.stid;

SELECT name, addr, score FROM studentt INNER JOIN scoret ON studentt.stid = scoret.stid
WHERE subid='MAT1';
```

- INNER JOIN: PK - FK 데이터가 일치하는 레코드를 짜매준다.

- JOIN을 이용하면 흩어진 데이터를 통합해서 보여지게 할 수 있다.(서브 쿼리도 가능. 성능 차이가 발생한다. 같은 결과를 만들더라도 성능이 향상되는 것으로 만들자. sql 튜닝의 영역)

### Q. subjectt와 scoret를 inner join해서 10101 국어 90 의 형태로 출력할 것.

```sql
SELECT stid, t.name, score from subjectt t, scoret s WHERE t.subid = s.subid;

SELECT stid, subjectt.name, score from subjectt INNER JOIN scoret ON subjectt.subid = scoret.subid;
```

```sql
SELECT stid, avg(score) avg FROM scoret group by stid;

SELECT * FROM (SELECT stid, avg(score) avg FROM scoret group by stid) as x;

SELECT * FROM (SELECT stid, avg(score) avg FROM scoret group by stid) as x INNER JOIN studentt as s ON s.stid = x.stid;

SELECT s.stid, name, avg FROM (SELECT stid, avg(score) avg FROM scoret group by stid) as x INNER JOIN studentt as s ON s.stid = x.stid;
```

- from 절의 서브 쿼리 결과도 INNER JOIN이 가능하다.

#### OUTER JOIN

```sql
INSERT INTO subjectt values('PHY1','물리');

-- INNER JOIN
SELECT * from subjectt INNER JOIN scoret ON subjectt.subid = scoret.subid;

SELECT * from subjectt LEFT OUTER JOIN scoret ON subjectt.subid = scoret.subid;
```

- 물리가 추가되었어도 한건의 성적데이터가 없으니 짜매어줄 대상이 없다.
- 회원가입이 되어도 글이 없으면 조인을 걸어도 나타나지 않으므로 나타나게하려면 OUTER JOIN울 이용한다.
- 부족함(NULL로 채움)이 나타나는 반대편을 명시한다.

```sql
SELECT subjectt.subid ,count(score)
FROM subjctt LEFT OUTER JOIN scoret
ON subjectt,subid = scoret.subid
GROUP BY subjectt.subid;
```

-아래는 과목별로 수강데이터의 갯수를 센다.

- count(\*)은 레코드의 갯수를 세고  
  copunt()는 해당 필드의 null이 아닌 데이터의 갯수를 센다.

- CONSTRAINT - 물리적인 제약조건을 필드에 걸어준다.

```sql
INSERT INTO scoret VALUES ('10101','PHY1',123);

ALTER TABLE scoret ADD CONSTRAINT CHECK_scoret_score CHECK (score >= 0 AND score <= 100);

alter table scoret ADD constraint CHECK_scoret_score CHECK ( score  >= 0 AND score <= 100);

ALTER TABLE scoret DROP CONSTRAINT CHECK_scoret_score;

ALTER TABLE subjectt ADD CONSTRAINT PK_subjectt_subid PRIMARY KEY(subid);
```

- CHECK CONSTRAINT는 WHERE절의 조건을 이용하여 제약을 걸 수 있다.(IN, NOT IN, =, != ...)

- 참조무결성: FK 쪽에서는 PK쪽에 존재하는 데이터만 사용가능

```sql
ALTER TABLE scoret ADD CONSTRAINT FK_scoret_subid FOREIGN KEY(subid) REFERENCES subjectt(subid);

INSERT into scoret VALUES ('10101','XXXX',50);
```

UNIQUE CONSTRAINT : NULL 허용하는데 중복은 안된다.(테이블 분리할 때 절묘하게 쓴다.)

```sql
create table study5t (
    id int not null
);

ALTER TABLE study5t ADD CONSTRAINT UQ_study5t_id UNIQUE (id);
```

- "일대다 상황에서 테이블 분리하는 방법"
- 일단 다대다로 생각하고 테이블을 생성한다.
- PK중 하나에 UNIQUE CONSTRAINT를 건다.
- 다대다가 일대다로 바뀐다.
- PK를 어떻게 할지를 선택하여 결정한다.

```sql
ALTER TABLE study5t DROP CONSTRAINT UNIQUE UQ_study5t_id;
```
