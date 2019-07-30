```html
<body>
  <form method="POST" action="form_test2">
    <input type="text" name="gul" size="50" />
    <input type="checkbox" name="abcd" value="apple" />
    <input type="checkbox" name="xyzz" value="banana" />

    <input type="radio" name="method" value="plus" checked="checked" />
    <input type="radio" name="method" value="minus" />
    <input type="password" name="pwd" size="16" />
    <textarea name="content">HelloWorld</textarea>
    <input type="submit" />
  </form>
</body>
```

- submit을 누르면 action에 지정된 페이지를 요청한다.
- form안에 input 필드에 입력된 값을 보내준다.
- 이 정보는 요청받은 서블릿의 request.getParameter로 가져다 쓸 수 있다.
- 파라미터를 가져올 때 input 필드의 name을 키 값으로 가져올 수 있다.
- 같은 이름을 가진 radio는 하나만 체크 가능하다. checked="checked" 디폴트 설정
- type: password는 text와 같이 보이나 입력 내용이 보여지지 않는다.
- textarea 태그는 여러줄을 입력할 때 사용한다. HelloWorld와 같이 태그안에 들어가는 내용은 디폴트로 보여준다.

```java
public class FormTestServlet extends HttpServlet {
    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("FormTestServlet");

        String gul = req.getParameter("gul");
        System.out.println("글: " + gul);

        resp.sendRedirect("/study2/test_02.html");
    }
}
```

- request
- response
- sendRedirect

```java
package main;

import java.util.StringTokenizer;

public class Test107 {
    public static void main(String[] args) {
        String l = "이것은 글을 쓴 내용에 해당하는 문장입니다.";
        StringTokenizer st = new StringTokenizer(l);
        while(st.hasMoreTokens()){
            String token = st.nextToken();
            System.out.println(token);
        }
    }
}
```

- 문장에서 공백을 기준으로 하나씩 잘라서 볼때 사용한다.
- String의 split() 함수도 비슷한 기능을 하지만 토크나이저가 비용이 적게 든다.

- 데이터를 다루는 클래스는 독립시켜서 만들면 담당을 나누어서 개발을 할 수 있고 나중에 통합하기만 하면 된다.
- 데이터를 다루는 코드를 독립된 클래스로 만들고 그 작업들을 추상화 한 인터페이스를 기반으로 만드는 설계 기법을 DAO Pattern이라고 한다.

```java
public class LifeServlet extends HttpServlet {
    int i = 0;
    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(this);
    }
}
```

- 많은 사람들이 동시에 접속해도 동리한 문자열이 계속 찍힌다.

  - 동일한 인스턴스가 계속 재사용되고 있다. -> 메모리 관리에는 장점이다. 메모리 소비가 적다.

- 서블릿의 인스턴스는 재활용 된다.
- 인스턴스는 재활용을 위해 내부적으로 적재되는데 그 시점에 호출되는 함수가 init함수이다.(최초 요청시에만 호출)

- WEB-INF 폴더의 절대 경로 값을 얻어온다.
- 브라우저가 접근 못하는 폴더가 업로드 파일을 놓기에 가장 적합하다.
- 허락 받고 ( 돈내고) )다운 받을때 FileInputStream으로 내보낸ㄴ다.
- 그 때 FileInputStream은 절대경로로 필요로한다.(그 때 getRealPath가 유용하다)

- 접속한 브라우저와 운영체제 정보를 담은 문자열이 얻어진다. 이것을 이용하여 모바일로 접근한건지 컴으로 접근한건지 알 수 있고 모바일용 화면과 컴용 화면을 구분하여 제공할 수 도 있다.
