```java
class CustomThread implements Runnable {
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("HelloWorld " + i);
        }
    }
}

public class Test093 {
    public static void main(String[] args) {
        Runnable rb = new CustomThread();
        new Thread(rb).start();
    }
}
```

- 오버라이딩 할 떄 throws Exception 같은거 붙여서는 못한다.(조상에 선언된 대로만 재 정의해야한다.)
- new Thread() 하면 가상의 CPU를 OS에서 할당받는다. 할당받은 CPU는 생성자에 넘겨진 포인터를 물고간다.
- start() 호출시에 준비과정을 거쳐 새로운 가상 CPU가 rb.run()을 호출한다.

#### 쓰레드 생성방법

    - Runnable 상속받은 클래스 선언.
    - new Thread하면서 1의 인스턴스의 포인터를 넘긴다.
    - Thread::start()를 호출하면 가상CPU(쓰레드)가 run()을 호출

- Program : executable file.
- Process : a running program.
- Thread: a light-weighted process. (독자 행동을 하지만 조금 다르다)
  - 쓰레드는 프로세스 안에서만 존재가 가능하다.
  - 쓰레드간 메모리를 공유할 수 있다.
- 프로세스간은 메모리 전달은 가능해도 공유는 불가능하다.
- 프로세스간의 메모리 전달의 대표적 수단이 소켓이다.(복사&붙이기 전달로 볼 수 있지만 이건 윈도우에서 국한된 개념)

- 프로세스 종료 == 프로세스가 가진 모든 쓰레드의 종료  
  (인간을 생각하면 이해 쉬움: 뇌 위장 척추...)

```java
class A implements Runnable {
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("Apple");

            try {
                int time = (int) (Math.random() * 1000);
                Thread.sleep(time);
            } catch (InterruptedException e) {

            }
        }
    }
}

class B implements Runnable {
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("Banana");

            try {
                int time = (int) (Math.random() * 1000);
                Thread.sleep(time);
            } catch (InterruptedException e) {

            }
        }
    }
}

public class Test094 {
    public static void main(String[] args) {
        new Thread(new A()).start();
        new Thread(new B()).start();
    }
}
```

- 쓰레드는 독자적으로 돌아가는 프로그램이 된다.
- Apple 사이에 Banana 끼어들게 안짰는데 결과는 그러했다.

```java
class Toilet {
    public void bigWork(String by) {
        System.out.println("SETP 1." + by);
        System.out.println("SETP 2." + by);
        System.out.println("SETP 3." + by);
        System.out.println("SETP 4." + by);
        System.out.println("SETP 5." + by);
    }
}

class A implements Runnable {

    private Toilet toilet = null;

    public A(Toilet t) {
        toilet = t;
    }

    public void run() {
        for (int i = 0; i < 100; i++) {
            toilet.bigWork("Apple");
            try {
                int time = (int) (Math.random() * 1000);
                Thread.sleep(time);
            } catch (InterruptedException e) {

            }
        }
    }
}

class B implements Runnable {

    private Toilet toilet = null;

    public B(Toilet t) {
        toilet = t;
    }

    public void run() {
        for (int i = 0; i < 100; i++) {
            toilet.bigWork("Banana");
            try {
                int time = (int) (Math.random() * 1000);
                Thread.sleep(time);
            } catch (InterruptedException e) {

            }
        }
    }
}

public class Test095 {
    public static void main(String[] args) {
        Toilet t = new Toilet();
        new Thread(new A(t)).start();
        new Thread(new B(t)).start();
    }
}
```

- 하나밖에 없는 화장실에서 대 참사가 일어나는 중....
- 화장실 문에 잠금을 해 줘야 한다.
- 쓰레드 프로그램에서는 잠금이 중요한데 그것을 동기화라고 한다.(synchronization)

```java
class Toilet {

    public void bigWork(String by) {
        synchronized (this) {
            System.out.println("SETP 1." + by);
            System.out.println("SETP 2." + by);
            System.out.println("SETP 3." + by);
            System.out.println("SETP 4." + by);
            System.out.println("SETP 5." + by);
        }
    }

    public void smallWork(String by) {
        synchronized (this) {
            System.out.println("SETP 1." + by);
            System.out.println("SETP 2." + by);
            System.out.println("SETP 3." + by);
            System.out.println("SETP 4." + by);
            System.out.println("SETP 5." + by);
        }

    }
}

class A implements Runnable {

    private Toilet toilet = null;

    public A(Toilet t) {
        toilet = t;
    }

    public void run() {
        for (int i = 0; i < 100; i++) {
            toilet.bigWork("Apple");
            try {
                int time = (int) (Math.random() * 100);
                Thread.sleep(time);
            } catch (InterruptedException e) {

            }
        }
    }
}

class B implements Runnable {

    private Toilet toilet = null;

    public B(Toilet t) {
        toilet = t;
    }

    public void run() {
        for (int i = 0; i < 100; i++) {
            toilet.bigWork("Banana");
            try {
                int time = (int) (Math.random() * 100);
                Thread.sleep(time);
            } catch (InterruptedException e) {

            }
        }
    }
}

public class Test096 {
    public static void main(String[] args) {
        Toilet t = new Toilet();
        new Thread(new A(t)).start();
        new Thread(new B(t)).start();
    }
}
```

- 모든 인스턴스에는 lock이라는 개념의 자물쇠? 열쇠? 가 있다. synchronized(this) {...}
- this가 가리키는 인스턴스가 가지고 있는 록을 획득해야 { 에 진입 가능. 획득하지 못하면 쓰레드는 멈추어 기다려야 한다.
- 일을 마쳤으면 } 시점에서 lock을 반납한다.
- 이런 방법으로 공유하는 메모리에서 작업 도중 끊기는 일을 막을 수 있다.

```java
public class Test099 {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
    }
}
```

- jar 파일은 클래스 파일을 압축해서 배포하는 파일
- `java -classpath .:mysql-connector-java-5.0.8-bin.jar Test099`

```java
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;

public class Test099 {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/study?serverTimezone=UTC", "root",
                "password");

        Statement stmt = conn.createStatement();
        String sql = "insert into studentt values('10107','또오치','쌍문동')";
        String sql2 = "delete from studentt where stid='10107'";
        String sql3 = "update studentt set addr = '이도동' where stid='10101'";
        // stmt.executeUpdate(sql);
        // stmt.executeUpdate(sql2);
        stmt.executeUpdate(sql3);
        System.out.println(stmt);
        stmt.close();
        conn.close();
    }
}
```

- Statement 는 conn 줄을 타고 오가는 바구니를 연상하면 된다.
- executeUpdate 함수의 리턴값은 변경된 레코드의 갯수이다.
- select 는 레코드를 변경하지 않는다.
- 해서 executeUpdate는 insert/ delete / update문장에 사용한다.
- conn.close() 신중하게 해야한다.(줄 끊는거다)
- stmt.close() 도 신중하게 (바구니 내리는거)
- conn 형성 -> stmt 형성 -> 작업 -> stmt.close() -> conn.close() 이 순서를 지켜서 작업한다.

- Connection, Statement 모두 인터페이스이다.
- DriverManager.getConnection 안에서는 Connection을 상속받은 모종의 클래스의 인스턴스를 리턴한다.
- 그것은 mysql 에 접속할 수 있는 기능을 구현하고 있다.

- ResultSet 은 CURSOR ( select 결과) 에 접근 가능한 정보.
- CURSOR은 서버에 생긴다.
- Connection이 닫힌 다음에는 ResultSet 은 사용 불가하다.
- Connection은 대단히 비싼 자원이고 제한적이다.
- 접속 후에 빨리 끊어주는게 바람직하다.(콜 센터를 연상하면 된다)

```java
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
class StudentVO {
    private String stId = null;
    private String name = null;
    private String addr = null;

    public String getStId() {return this.stId;};
    public void setStId(String stId) {this.stId = stId;};

    public String getName() {return this.name;};
    public void setName(String name) {this.name = name;};

    public String getAddr() {return this.addr;};
    public void setAddr(String addr) {this.addr = addr;};

    public String toString() {
        return this.getStId() + "\t" + this.getName() + "\t" + this.getAddr();
    }
}
public class Test099_4 {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/study?serverTimezone=UTC", "root",
                args[0]);
        Statement stmt = conn.createStatement();

        List<StudentVO> rl = new ArrayList<>();

        String sql = "select stid,name,addr from studentt";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            StudentVO vo =new StudentVO();
            vo.setStId(rs.getString("stid"));
            vo.setName(rs.getString("name"));
            vo.setAddr(rs.getString("stid"));
            rl.add(vo);
        }

        rs.close();
        stmt.close();
        conn.close();

        System.out.println(rl);
    }
}
```

- ORM(Golden Rule, Rule of Thumb)
- field -> property, table -> class, record -> instance

- Connection은 살아있을때 할 거 다해야한다.
- Connection은 빨리 끊어야 한다.

- Value Object(VO): 값을 담는 객체이다.

  - 테이블 구조와 동일하게, 레코드 하나를 인스턴스에 담을 수 있는 역할

- Data Transfer Object(DTO)
- Entity

```java
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
public class Test100 {
    public static void main(String[] args) throws Exception{
        Class.forName("oracle.jdbc.OracleDriver");
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521/XE","system","oracle");

        Statement stmt = conn.createStatement();
        String sql = "SELECT SYSDATE FROM DUAL";
        ResultSet rs = stmt.executeQuery(sql);

        // while(rs.next()){
        //     String l = rs.getString(1);
        //     System.out.println(l);
        // }
        if(rs.next()){
            String l = rs.getString(1);
            System.out.println(l);
        }
        rs.close();
        stmt.close();
        conn.close();
    }
}
```

- re.getString(1) 필드명 대신에 숫자 가능. 1은 나열된 첫번째 필드를 의미
- 현재시간 - mysql 은 now(), oracle은 sysdate
- 결과 레코드가 1개인 경우에는 while 대신에 if 를 사용한다.

- Connection, Statement 를 상속한 Oracle 과 연동 가능한 클래스를 OracleDriver에서 DriverManager에 세팅해주면 Oracle에 맞추어 개발된, Connection Statement ResultSet 등을 상속받은 클래스가 공급되는 구조이다.
