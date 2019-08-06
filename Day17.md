-- NOTE\_페이지나누기.txt

# 0.

현재 132개의 글이 등록되어 있다고 하자...
한 페이지에 10개의 글을 보여준다고 할때

0.0 테이블에 입력되어진 글의 갯수를 세려면
어떤 SQL 문장을 이용해야 하나?

0.1. page의 갯수는? 14페이지
그리고 글 번호를 이용하여 페이지의 갯수를
구해내는 공식은?

[페이지수] = ( ( [레코드수] - 1 ) / [페이지당글수] ) + 1;
즉 ( ( 132 - 1 ) / 10 ) + 1 은 14.
1페이지 부터 14 페이지 까지 존재하게 된다.

현재 페이지를 [CP]
페이지 수를 [NP]
여기서 시작페이지를 [SP]
끝 베이지를 [EP] 라고 칭하자.

0.2.
....
....
....
1 2 3 4 5 >

와 같은 형태로 글 목록을 보여준다.
여기서 한번에 5개씩 끊어서 보여준다고 정할때
( 이를 blockSize 라고 하자 )

현재 페이지가 3이다
block이 시작되는 페이지는? 1
block이 끝나는 페이지는? 5

이를 이용하여 page의 갯수와 현재 page 번호를 이용하여
block의 시작페이지와 끝 페이지를 구하는 공식을
도출해 내자.

블럭사이즈를 [BS] 라고 하자. ( 여기서는 5다 )

[블럭시작] = ( ( [CP] -1 ) / [BS] ) _ [BS] + 1
즉 ( ( 3 - 1 ) / 5 ) _ 5 + 1 이므로 결과는 1

[블럭끝] = [블럭시작] + [BS] - 1 과 [EP] 중 작은값
5 와 14 중 작은 값이니까 5

[블럭시작] 을 [BB] 라고 하고
[블럭끝] 을 [BE] 라고 하자.

0.3 < 표시를 보일것인지 말것인지를 구하는 논리는?
( [BS] != 1 ) :

    > 표시를 보일것인지 말것인지를 구하는 논리는?
    ( [BE] != [EP] ) :

---

```sql
DROP TABLE Temp10T;

CREATE TABLE Temp10T(
id NUMBER(3) ,
title VARCHAR2(7)
);
CREATE TABLE Temp10T(
id int ,
title VARCHAR(7)
);

INSERT INTO Temp10T VALUES (1,'abcde1');
INSERT INTO Temp10T VALUES (2,'bcdef2');
INSERT INTO Temp10T VALUES (4,'abcde4');
INSERT INTO Temp10T VALUES (5,'defgh5');
INSERT INTO Temp10T VALUES (6,'cdefg6');
INSERT INTO Temp10T VALUES (8,'bcdef8');
INSERT INTO Temp10T VALUES (9,'abcde9');
INSERT INTO Temp10T VALUES (10,'cdefg10');
INSERT INTO Temp10T VALUES (11,'defgh11');
INSERT INTO Temp10T VALUES (14,'bcdef14');
INSERT INTO Temp10T VALUES (15,'defgh15');
INSERT INTO Temp10T VALUES (16,'abcde16');
INSERT INTO Temp10T VALUES (17,'defgh17');
INSERT INTO Temp10T VALUES (18,'cdefg18');
INSERT INTO Temp10T VALUES (20,'efghi20');
INSERT INTO Temp10T VALUES (21,'abcde21');
INSERT INTO Temp10T VALUES (22,'bcdef22');
INSERT INTO Temp10T VALUES (23,'defgh23');
INSERT INTO Temp10T VALUES (25,'cdefg25');
INSERT INTO Temp10T VALUES (26,'abcde26');
INSERT INTO Temp10T VALUES (28,'cdefg28');
INSERT INTO Temp10T VALUES (30,'efghi30');
INSERT INTO Temp10T VALUES (31,'bcdef31');
INSERT INTO Temp10T VALUES (33,'abcde33');
INSERT INTO Temp10T VALUES (34,'efghi34');
INSERT INTO Temp10T VALUES (36,'bcdef36');
INSERT INTO Temp10T VALUES (37,'abcde37');
INSERT INTO Temp10T VALUES (39,'efghi39');
```

# 1. 정렬된 순서 보기

```sql
SELECT id, title FROM Temp10T ORDER BY id DESC;
```

# 2. rownum??

```sql
SELECT id, rownum FROM Temp10T;
SELECT id, rownum FROM Temp10T ORDER BY id DESC;

SELECT id, rownum FROM
( SELECT id FROM Temp10T ORDER BY id DESC );
```

# 3. rownum을 기준으로 페이지 나누기?

```sql
SELECT id, rownum FROM
( SELECT id FROM Temp10T ORDER BY id DESC )
WHERE rownum > 0 AND rownum <= 5;

SELECT id, rownum FROM
( SELECT id FROM Temp10T ORDER BY id DESC )
WHERE rownum > 5 AND rownum <= 10;
```

# 4. rownum을 field로 굳혀놓고 나누기!!

```sql
SELECT id, rownum as sq FROM
( SELECT id FROM Temp10T ORDER BY id DESC );

SELECT id FROM
( SELECT id, rownum as sq FROM
( SELECT id FROM Temp10T ORDER BY id DESC ) )
WHERE sq > 0 AND sq <= 5;
SELECT id FROM
( SELECT id, rownum as sq FROM
( SELECT id FROM Temp10T ORDER BY id DESC ) )
WHERE sq > 5 AND sq <= 10;
```

# 5. 서브쿼리로 정리해서 깔끔하게!!

```sql
SELECT \* FROM Temp10T WHERE id IN
(
SELECT id FROM
( SELECT id, rownum as sq FROM
( SELECT id FROM Temp10T ORDER BY id DESC ) )
WHERE sq > 10 AND sq <= 20
)
ORDER BY id DESC;
```

# 6. 나중의 검색을 위해서 0 = 0은 그냥 끼워 넣어준다.

     ( 검색을 어떻게 할지 생각해 보자 )

```sql
SELECT \* FROM Temp10T WHERE id IN
(
SELECT id FROM
( SELECT id, rownum as sq FROM
( SELECT id FROM Temp10T WHERE 0 = 0 ORDER BY id DESC ) )
WHERE sq > 5 AND sq <= 10
)
ORDER BY id DESC;
```

여기에서 0 = 0 을 응용하여 검색기능에서의 나누기 시도한다.

```java
StringBuffer find = new StringBuffer(" WHERE 0 = 0");
if( title != null ){
find.append(" AND title LIKE '%");
find.append( title );
find.append("%'");
}
if( user != null ){
find.append(" AND user ='");
find.append( title );
find.append("'");
}
if( content != null ){
find.append(" AND content LIKE '%");
find.append( content );
find.append("%'");
}

String subSql = "SELECT id FROM " +
"( SELECT id, rownum as sq FROM " +
"( SELECT id FROM Temp10T "+ find.toString() +
" ORDER BY id DESC ) )" +
"WHERE sq > 5 AND sq <= 10";

String countSql = "SELECT COUNT(_) FROM Temp10T " + find.toString();
String sql = "SELECT _ FROM ("+ subSql +") ORDER BY id DESC";
```

그러면 아래와 같은 형태의 결과를 보게 될 것이다.

```sql
SELECT COUNT(\*) FROM Temp10T WHERE 0 = 0 AND
user = 'root' AND content LIKE '%ab%'

....

SELECT \* FROM Temp10T WHERE id IN
(
SELECT id FROM
( SELECT id, rownum as sq FROM
( SELECT id FROM Temp10T WHERE 0 = 0 AND
user = 'root' AND content LIKE '%ab%' ORDER BY id DESC ) )
WHERE sq > 0 AND sq <= 3
)
ORDER BY id DESC;
```

```sql
select * from temp10t order by id desc limit 0,10;
select * from temp10t order by id desc limit 10,10;
```

- 오라클에 비해 게시판만 보면 mysql이 장점이 많다.
- limit 10, 10은 10개 건너뛰고 10개 보여줘라
