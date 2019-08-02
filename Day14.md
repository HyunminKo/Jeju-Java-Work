```html
<form method="GET" action="talk_login2.jsp">
  <input type="text" name="roomNo" size="4" />
  <input type="password" name="pwd" size="8" />
  <input type="submit" />
</form>
```

- GET 방식일때는 주소 옆에 붙어간다. POST는 내부에 숨겨져서 간다.

```java
response.sendRedirect(ctxPath +"/talk_login.jsp");
return ;
```

- sendRedirect 이후의 코드는 의미없다.

```html
<%@ page contentType="text/html;charset=UTF-8" language="java"
isELIgnored="true"%>
<!DOCTYPE html>
<html>
  <head> </head>
  <body>
    ${100+100}
    <br />
    ${50>100}
    <br />
    ${50 gt 100}
    <br />
    ${50 ge 50}
    <br />
    ${50 lt 50}
  </body>
</html>
```

- \${100+100} 은 서버에서 동작했고 200이라는 결과를 만들어 내어서 응답에 포함시켰다.
- \${...} 간단한 연산을 할 수 있고 이것을 EL이라고 한다.(Expression Language)
- isELIgnored="true" 가 설정되면 \${100+100} 은 EL이 아닌 단순 문자열로 인식한다.
- gt : greater than

```java
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setAttribute("apple",100);
    request.setAttribute("ab",new int[]{1,2,3,4});

    TalkRoomVO vo = new TalkRoomVO();
    vo.setRoomNo(10101);
    vo.setApple("청송사과");
    vo.setBanana("제주바나나");

    request.setAttribute("t",vo);
%>
${apple}
${(apple>10)}
${ab[0]}
${ab[1]}
${ab[2]}
${ab[3]}
${t}
```

- request.setAttribute 또는 session.setAttribute를 통해서 저장된 값은 키값을 이용해서 EL에서 사용이 가능하다.

- 정리. session.request,application 3개 객체 모두

  - void setAttribute(String,Object)
  - Object getAttribute(String)
  - void removeAttribute(String)을 지원한다.

- EL이 반응이 좋아서 보다 확장한 기능인 jstl을 지원하게 되었다.
- jstl: 태그로 만든 언어 - <if></if> 와 유사
- SGML에서 태그를 처음 도입

  - 미 국방성에 문서를 전산화 할 수 있는 언어를 개발했다.
  - MathML, MusicML, postscript 등 파생된 서브셋이 엄청 많다.
  - postscript 이 발전해서 pdf로 발전
  - 성공한 것이 XML,html이다.

- 태그의 특징

  1. 최 외각의 태그는 단 하나만 존재해야 한다.
  2. 태그는 중첩해서 선언하지 않는다.
     - <banana><apple></apple></banana> : OK
     - <banana><apple></banana></apple> : NO
  3. 속성은 시작태그에 선언한다.(bgColor="red" 이게 속성)
     - <body bgColor="red"></body>
  4. 속성은 key="value" 또는 key='value' 로 선언되어야 한다.
  5. 하나의 태그에서 속성은 두번 선언 하지 않는다.
  6. 시작 태그가 있으면 반드시 끝 태그가 있다.
     - <apple></apple> 과 같이 내용 없는 경우 <apple />로 쓴다.
     - <br />, <input type="text"/>

```html
<l:forEach var="row" varStatus="vs" items="${rl}">
  <tr bgcolor="${(vs.count)%2==0?'khaki':'lightpink'}">
    <td>${row.no}</td>
    <td>${row.gul}</td>
    <td>${row.theTime}</td>
    <td>${vs.count}</td>
  </tr>
</l:forEach>
```

- java의 class의 static함수를 EL에서 사용할 수 있다.
- 이 개념을 이용하면 복잡한 코드를 EL에서 사용할 수 있다.
