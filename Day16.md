```java
public class Test127 {
    public static void main(String[] args) throws Exception{
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet("http://localhost:8080/study3/Test126.jsp?pw=2345");
        httpGet.addHeader("User-Agent","Mozila/5.0");
        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
        int resCode = httpResponse.getStatusLine().getStatusCode();
        System.out.println(resCode);
    }
}
```

- httpClient는 아파치 재단에서 URL 클래스를 훨씬 좋게 만든 것
- (http 프로토콜로 요청/응답을 수행할 수 있다.)
- 안드로이드 os에도 표준탑재 되어서 서버에서 정보를 다운받는 용도로 사용한다.

- CloseableHttpClient: 실제로 요청/응답을 수행하는 핵심
- 요청 정보에 헤더를 조작할 수 있다.
- httpClient.execute(httpGet) : 실제로 요청/응답을 수행한다. 그리고 httpResponse객체로 응답받는다.

- 많은 정보를 서버에서 다운 받는 것에는 차이가 없다.(GET/POST)
- 클라이언트에서 서버로 대량의 정보를 업로드 할 때는 POST방식을 써야 한다.
- 대부분 GET 방식이지만 POST 방식으로 쓰고자 할때는 위의 코드와 많이 달라지게 되므로 유의한다.

```
log4j.rootLogger=INFO, A2
log4j.appender.A2=org.apache.log4j.FileAppender
# log4j.appender.A2.File=/home/user/.../log.txt
log4j.appender.A2.File=/Users/hyunminko/edu/JavaWork/study3/web/WEB-INF/log.txt
log4j.appender.A2.Append=true
log4j.appender.A2.layout=org.apache.log4j.PatternLayout
#log4j.appender.A2.layout.ConversionPattern=%m%n
log4j.appender.A2.layout.ConversionPattern=[%d] %5p %c : %m%n
```

- ConversionPattern에서 로그파일의 형식을 지정할 수 있는데 매우 다양한 형식을 지정할 수 있다.
- 단순히 콘솔에 찍어보는 걸 넘어서 내부적인 동작을 고스란히 담아 낼 수 있다.
- 로그파일 분석툴을 이용하면 사이트 전반의 분석을 가능하게 한다.

```html
<!DOCTYPE html>
<html>
  <head>
    <style>
      .apple {
        background-color: #aabbcc;
        width: 100px;
        height: 100px;
        position: absolute;
        left: 200px;
        top: 200px;
        z-index: 3;
      }
      .banana {
        background-color: #ccddee;
        width: 100px;
        height: 100px;
        position: absolute;
        left: 150px;
        top: 150px;
        z-index: 2;
      }
    </style>
  </head>
  <body>
    <div class="apple">apple</div>
    <div class="banana">banana</div>
  </body>
</html>
```

- div: 레이어라고 하기도 한다. 위치값을 내가 정할 수 있다. 전체 UI의 배치 담당에 유용하게 쓴다.

- z-index 값을 높게 지정하면 낮은 것 보다 위쪽에 보여지게 된다.

- 보통 class > tagName -> style 순을 저용이 되는 걸 볼 수 았다.

- border-collapse:collapse - 테이블 모양을 만들 때 유용하게 쓰일 수 있다.

```html
<body>
    <div class="container-fluid">
        <div class="row">
            <div class="col-xs-12 bg-success">apple</div>
            <div class="col-xs-12 bg-info">banana</div>
            <div class="col-xs-12 bg-warning">apple</div>
            <div class="col-xs-12 bg-danger">banana</div>
        </div>
    </div>
</body>
</html>
```

- container는 sm 크기, md 크기에서 고정된 폭을 가지게 된다.
- 1000에서 1100까지 폭을 변화해도, 좌우 공백만 커질 뿐 내용물의 크기는 일정하다.
- sm에서 md 영역의 크기로 커지면 그때 확 커지게 된다.
- container-fluid 는 무조건 브라우저의 폭 영역을 꽉채워서 내용을 보여준다.
