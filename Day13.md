```sql
create table temp20t ( data char(3) null );

insert into temp20t values('abc');
insert into temp20t values(null);

select * from temp20t;
```

#### Q. null 4글자가 입력 가능? null은 빈 값을 의미한다.

- `insert into temp20t values('null');`은 불가능
- 'null'과 null의 차이를 알아야 한다.

#### Q. null 값은 어떻게 데이터베이스에서 찾을까?

- `select * from temp20t where data = null` (x)
- `select * from temp20t where data is null` (o)

```java
String data = null;
String sql = "insert into temp20t values('"+data+"')";
```

- null이라는 문자열로 들어가면서 에러가 난다.

```java
String sql = (data != null) ?
    "insert into temp20t values('"+data+"')" :
    "insert into temp20t values(null)";
```

- 나름 해결책이지만 null 가능 필드가 n개이면 쿼리의 경우의수가 2^n으로 증가한다.
- 이런 문제 때문에 현업에서는 PreparedStatement를 더 선호한다.
- 또는 아예 char는 무조건 4자리 이상을 잡게 하는 경우도 있다.

```java
String data = null;
String sql = "insert into temp20t values(?)";
PreparedStatement stmt = conn.prepareStatement(sql);
stmt.setString(1, null);
stmt.executeUpdate();
```

- Statement가 어떤 SQL 문장이든 실행하는것은 범용적인데 반해서 PreparedStatement는 생성시에 준비한 그 문장만 실행할 수 있다. 대신 ? 영역을 setString, setInteger등을 이용할 수 있다.
- 순서가 1부터 시작하는 것에 주의
- execute시에 매개변수 없음에 주의
- null값을 넣을 때 ''를 붙여야 할지를 결정하기 위해 이런 저런 고민할 필요가 없어진다(Test110참고)

```java
public static void test(String ... args){
    for(String item: args){
        System.out.println(item);
    }
}
public static void main(String[] args) {
    test();
    test("apple");
    test("apple","banana");
}
```

- 매개 변수로 String 을 0..\*개를 넣어도 에러가 안나는 선언 방식
- String...은 String[] 과 동일하다.

```java
public static void test2(Object... args){
    System.out.println(args.length);
}
public static void main(String[] args){
    test2(100,"hello",3.14);
}
```

- Object arg_1 = 100; -> 100을 new Integer(100)으로 auto boxing
- Object arg_3 = 3.14; -> 3.14를 new Double(3.14)로 auto boxing
- Object arg_4 = null; 도 가능

```java
Object i = 100; // (O) autoboxing
int j = i; //(X)

Integer i = 100; //(O) autoboxing
int j = i; //(O) autoboxing
int k = i.intValue(); //(O) unboxing
```

```java
public static int update(String sql, Object ... args) throws Exception{
    int rc = 0;

    Class.forName("oracle.jdbc.OracleDriver");
    Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe","HR","HR");

    PreparedStatement stmt = conn.prepareStatement(sql);

    for (int i = 0; i < args.length; i++) {
        if(args[i] == null){
            stmt.setObject(i+1,null);
        }else if(args[i] instanceof Integer){
            int r = ((Integer)args[i]).intValue();
            stmt.setInt(i+1,r);
        }else if(args[i] instanceof Double){
            double r = ((Double) args[i]).doubleValue();
            stmt.setDouble(i+1,r);
        }else if(args[i] instanceof String ){
            stmt.setString(i+1,(String) args[i]);
        }
    }

    rc = stmt.executeUpdate();
    stmt.close();
    conn.close();
    return rc;
}
public static void main(String[] args) throws Exception{
    String sql = "insert into temp20t values( ? )";
    update(sql,"KIM",100);
}
```

```java
interface ITemp {
    public void print();
}
public class Test114 {
    public static void main(String[] args) {
        final int i = 100;
        int j = 100;
        ITemp t = new ITemp() {
            @Override
           public void print() {
                System.out.println("Overriding"+i+j);
           }
        };
        t.print();
    }
}
```

- Anonymous class : 이름 없는 클래스(조상은 있다)
- ITemp를 상속받고 조상클래스에 있는 abstract 메서드를 오버라이딩 한다.
- 이름이 없어 재사용은 불가능하다.

- final로 선언된 로컬 변수는 anonymous class안에서 사용 가능하다.

```java
public class StateServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String use = req.getParameter("use");
        System.out.println(use);
    }
}
```

- 요청 -> 응답, 요청의 방법이 두가지 GET/POST가 있다.
- ?를 기준으로 주소와 내용이 나눠진다.
- 내용에서는 키-값 구조로 구성되며 request.getParameter를 이용하여 값을 가져올 수 있다.
- 대부분 GET방식이고 ,form 에서 method="POST"로 지정된 경우 POST방식

```java
public class SessionServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        System.out.println(session.isNew());

        session.setAttribute("apple","Object!!!");
        Object value = session.getAttribute("apple");
        System.out.println(value.toString());
    }
}
```

- 최초 getSession() 호출시에는 고유넘버가 없이 요청이 들어오게된다.
- 고유넘버 생성
- JSESSIONID: 키 값으로 고유넘버를 쿠키에 지정
- 그 이후에 getSession() 호출 : 고유 넘버를 가지고 왔으니 그걸로 기억장소 찾는다.

- 쿠키와 세션은 많이 비슷하다.
- 쿠키는 브라우저에, 세션은 서버에
- 쿠키는 파일, 세션은 Map<String,Object>에 저장
- 쿠키는 조작이 가능할수 도있지만 세션은 어렵다.

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%!
    int i = 0;
%>
<%
    int j = 0;
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%=i++%>
    <%=j++%>
</body>
</html>
```

- i는 멤버변수, j는 로컬변수의 느낌이 난다
- jsp 파일을 요청하면 톰캣은 이것을 \*.java 파일로 변환한다.(서블릿코드) 이것을 컴파일하고 인스턴스를 만들어 적재하고 인스턴스 재활용한다.
