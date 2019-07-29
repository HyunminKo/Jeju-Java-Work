### Tomcat

- web은 실제로 파일 다운로드 소켓 프로그램과 기본은 같다.
- 클라이언트 : 웹 브라우저
- 서버 : 톰캣(Web Application Server: WAS)

- 브라우저가 톰캣에 접속해서 서버의 파일을 다운로드 받아 보여준다.
- 톰캣 서버 하나에는 여러개의 Web Application이 설치 가능하다. 이것을 Context라고 한다.
  - Context가 가져야 하는 것: 홈 디렉토리, 접근 경로

* edu/JavaWork/StudyHome/ 폴더 생성
* edu/JavaWork/StudyHome/WEB-INF
* edu/JavaWork/StudyHome/WEB-INF/classes
* edu/JavaWork/StudyHome/WEB-INF/lib

* 클라이언트는 홈 디렉토리 아래의 모든 파일을 다운 가능핟. 단 WEB-INF는 접근이 불가능하다.(내부 설정파일 등을 놓는다.)
* classes: java class파일을 놓는 곳
* lib: 동작에 필요한 jar 파일을 놓는 곳

- 응답은 다운 이전에 Code값을 넘긴다.

  - 200: 정상
  - 404: 파일 없음
  - 500: 요청 처리시 에러

- docBase 디렉토리 하위 폴더의 파일은 폴더명을 경로명으로 바꾸어 접속할 수 있다.

- WEB-INF 는 특수한 의미를 갖는다. 브라우저에 공개치 않는다.

```java
package apple;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloWorld2 extends HttpServlet {

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("HelloWorld2");

        Class.forName("oracle.jdbc.OracleDriver");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("   <body>");
        out.println("       Hello World 2 : ^^*");
        out.println("   </body>");
        out.println("</html>");
        out.close();
    }
}

// javac -d ../StudyHome/WEB-INF/classes -classpath servlet-api.jar
```

- 서브릿의 동작 흐름

  1. WAS 쪽에 적절히 설정이 되어 있다고 가정한다.(path에 대한 docBase 경로 설정)
  2. 브라우저에서 /study/study01으로 요청이 들어온다.
  3. /study로 경로를 설정한 Context가 있나?
  4. 2에서 찾은 Context의 docBase 폴더 아래의 /WEB-INF/web.xml 찾음
  5. web.xml로 부터 읽은 설정 중에 url-pattern이 study01에 매칭되는 서블릿을 찾는다.(abcd)
  6. 해당하는 서블릿에서 요청을 처리한다.
     - abcd 서블릿에서 apple/HelloWorld2를 찾음.
     - 이 클래스를 인스턴스화 시키고 오버라이딩 한 service함수를 찾아 실행시킨다.
     - System.out.println()은 서버 쪽 콘솔에 찍힌다.
     - PrintWriter로 내보내는것은 요청으로 들어온 브라우저에 내보내는것이다.

```html
<html>
  <body>
    test_01.html
    <table border="1" cellspacing="0" cellpadding="12">
      <tr bgcolor="#aabbcc">
        <td>apple</td>
        <td>banana</td>
      </tr>
      <tr bgcolor="#ccddee">
        <td>orange</td>
        <td>kiwi</td>
      </tr>
    </table>
  </body>
</html>
```

- cellspacing="0": 셀 간의 간격
- cellpadding="12": 셀의 벽과 내용과의 간격
- border="1": 경계줄의 굵기

- bgColor="#aabbcc" 배경색, 각각 16진수이고 앞의 두 자리는 Red, 그 다음 두 자리는 green, 마지막 두 자리는 blue값을 색으로 사용한다.

```html
<html>
  <body>
    test_01.html
    <table border="0" cellspacing="2" cellpadding="12">
      <tr bgcolor="#aabbcc">
        <td>apple</td>
        <td>banana</td>
      </tr>
      <tr bgcolor="#ccddee">
        <td>orange</td>
        <td>kiwi</td>
      </tr>
    </table>

    <table border="0" cellspacing="2" cellpadding="12">
      <tr bgcolor="#aabbcc">
        <td rowspan="2">apple</td>
        <td>banana</td>
      </tr>
      <tr>
        <td bgcolor="#ccddee">kiwi</td>
      </tr>
      <tr>
        <td colspan="2" bgcolor="#ccddee">kiwi</td>
      </tr>
    </table>
  </body>
</html>
```

- rowspan="3", colspan="2" :엑셀에서의 셀 병합과 같은 개념, rowspan은 상하 , colspan은 좌우 셀과 병합한다.
- align="left | right | center" : 내용물의 좌우 정렬 속성을 정한다.
- width="400px" 셀의 폭을 결정할 수 있다. (%로 주기도 한다)
- 같은 오와 열에 있는 것들은 자동으로 지정된 크기에 맞춰진다.
