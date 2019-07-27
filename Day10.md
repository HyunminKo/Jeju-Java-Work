```sql
create table study10t (
    id NUMBER(3),
    data VARCHAR2(10)
);

INSERT INTO study10t VALUES(100,'HelloWorld');

create table study11t (
    id NUMBER(5),
    data CHAR(10)
);

create sequence seq_study11;

insert into study11t values( seq_study11.NEXTVAL, 'apple');
```

- 오라클과 mysql 은 일련 번호를 만드는 법이 틀리다.

- mysql은 auto_increment primary key를 썼다.

```sql
-- mysql의 concat 과 동일한 기능을 수행한다.
-- char는 고정길이를 가지므로 테이블을 만들때 크기를 10 으로 줬다면 apple 뒤 5자리에는 공백으로 채워져있다.('apple     ')
-- mysql은 그냥 'apple' 이 된다.
select id ,data || '*' from study11t;
```

```sql
select id, TRIM(data) || '*' from study11t;
```

- TRIM() - 좌/우의 공백문자를 제거하는 역할을 한다.

```sql
create table study12t (
    the_time date
);

INSERT INTO study12t VALUES (sysdate);

select to_char(the_time,'YYYY-MM-DD') from study12t;

select to_char(the_time,'YYYY-MM-DD HH24:MI:SS') from study12t;
```

- oracle의 날짜시간은 date 자료형을 이용한다.
- 현재 시간은 sysdate를 이용한다.
- 보여지는 형식은 to_char를 이용하여 형식을 지정하면 된다.

#### Q. 역삼동에 사는 학생의 국어성적을 서브쿼리로 구하세요.

```sql
select * from scoret where stid in (select stid from studentt where addr like '역삼동') and subid ='KOR1';
```

#### Q. 학생별 평균 점수를 group by로 구해보세요.

```sql
select stid, avg(score) from scoret group by stid;
```

```sql
select * from studentt inner join scoret on studentt.stid = scoret.stid;
```

```sql
insert into subjectt values('PHY1','물리');

select * from subjectt LEFT OUTER JOIN scoret ON subjectt.subid = scoret.subid;

select * from subjject, scoret WHERE subjectt.subid = scoret.subid(+);
```

- 오라클용 아우터 조인의 문법. null값으로 채워지는 곳에 (+)표시를 붙인다.

- 오라클의 변형 방법을 다른 DB업체들이 따라하기도 한다. 오라클만 쓰는 사람들은 오라클의 방법만을 고집하는 경우가 많다.

```sql
select * from (select stid, avg(score) as avg from scoret group by stid);

select * from( select stid, avg(score) as avg from scoret group by stid ) x, studentt y
where x.stid = y.stid;
```

---

Constraint in Oracle

- Primary key, foreign key, check, unique, not null

```sql
alter table studentt add constraint pk_studentt_stid primary key (stid);
```

- 참조 무결성: FK쪽에는 PK에 없는 데이터는 존재하면 안된다.

```sql
alter table scoret add constraint fk_scoret_stid
foreign key(stid) references studentt(stid);

-- 두 문장이 왜 위배 될까?
delete from studentt where stid = '10101';
insert into scoret values('10109','KOR1',100);
```

```sql
alter table scoret add constraint fk_scoret_subid
foreign key(subid) references subjectt(subid);
```

- FK Constraint는 먼저 참조할 대상 PK Constraint가 존재해야 생성 가능하다

```sql
alter table scoret add constraint ck_scoret_score check(score >= 0 and score <= 100);

insert into scoret values ('10101','PHY1',123);

```

```sql
alter table subjectt add constraint uq_subject_subid unique(subid);
```

- Not null은 보장안함. no duplicate는 보장. null 중복 가능 나머지 불가능

```sql
alter table scoret drop constraint ck_score_score;
alter table ssubjectt drop constraint uq_subject_i;
alter table scoret drop constraint fk_scorket_stid;
alter table studentt drop constraint pk_studentt_stid;
```

```sql
create table bangmyung_t (
    no int,
    gul varchar2(100),
    the_time date
);

create sequence seq_bangmyung;

insert into bangmyung_t values(seq_bangmyung.NEXTVAL, '만나서 반갑습니다',sysdate);

--mysql
create table bangmyung_t (
    no int auto_increment primary key,
    gul varchar(100),
    the_time date
);

insert into bangmyung_t values (default,'만나서 반갑습니다',now());
```

```java
import java.sql.*;

public class Test101 {
    public static void main(String[] args) throws Exception {
        Class.forName("oracle.jdbc.OracleDriver");
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE", "HR", "HR");
        Statement stmt = conn.createStatement();
        String gul = "HelloWorld";
        String sql = "insert into bangmyung_t values(seq_bangmyung.NEXTVAL, '" + gul + "',sysdate)";
        int result = stmt.executeUpdate(sql);
        System.out.println(result);
        stmt.close();
        conn.close();
    }
}
```

```java
public static void addGul(String gul) throws Exception {

        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE", "HR", "HR");
        Statement stmt = conn.createStatement();
        // String sql = "insert into bangmyung_t values(seq_bangmyung.nextval,'" + gul + "', sysdate)";
        String sql = "insert into bangmyung_t values(seq_bangmyung.nextval,'" + gul + "', sysdateXX)";
        stmt.executeUpdate(sql);
        stmt.close();
        conn.close();
}
```

- 함수로 선언해서 재사용성을 높였다.
- sql 문장에서 에러 -> stmt.executeUpdate(sql)에서 예외 발생 -> conn.close() 실행 안된다. -> 비싼 자원을 계속해서 들고 있다.

```java
public static void addGul(String gul) throws Exception {
    Connection conn = null;
        Statement stmt = null;
        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE", "HR", "HR");
            stmt = conn.createStatement();
            String sql = "insert into bangmyung_t values(seq_bangmyung.nextval,'" + gul + "', sysdateXX)";
            stmt.executeUpdate(sql);
        } catch (Exception e) {
            throw e;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
}
```

- finally 영역은 try에서 에러가 나건 안나건 무조건 거친다.

- executeUpdate 상황에서 에러나도 conn.close()는 되어야 한다?

- stmt.close() conn.close() 가 stmt, conn이 null이 아닐 때만 호출하도록 개선.

```java
import java.sql.*;

public class Test106 {
    public static void main(String[] args) throws Exception {
        Class.forName("oracle.jdbc.OracleDriver");
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521", "HR", "HR");

        conn.setAutoCommit(false); //commit이 자동으로 되지 않는다.

        conn.close();
    }
}
```

```sql
CREATE TABLE test_tx {
    data int
};
insert into test_tx values(100);
```

- conn을 통해서 executeUpdate 하면 LOG를 거쳐서 Table에 저장된다.
- jdbc는 auto commit을 지원한다.( 즉 executeUpdate시에 무조건 commit이 자동으로 발생한다.)
