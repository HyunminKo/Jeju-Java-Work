```html
<form method="POST" action="fileup" enctype="multipart/form-data">
  <input type="text" name="title" size="20" />
  <input type="file" name="apple" />
  <input type="submit" />s
</form>
```

- <input type="file"> 은 파일을 서버로 업로드 할 때 사용한다.
- 파일을 업로드 할시에 반드시 enctype="multipart/form-data"를 사용한다.
- fileup에 해당하는 서블릿은 cos.jar 파일의 MultipartRequest를 이용하여 업로드를 처리하는 것이 일반적이다.

```java
private String process(HttpServletRequest request) throws IOException{
    byte[] buf = new byte[1024];
    int len = 0;
    StringBuffer sb = new StringBuffer();
    InputStream in = request.getInputStream();
    while((len = in.read(buf)) != -1) {
        sb.append(new String(buf,0,len));
    }
    in.close();
    return sb.toString();
}
```

- request는 요청, 브라우저에서 서버로 전달하는 개념.
- request.getInputStream()을 이용해 브라우저에서 서버로 전달되는 내용을 볼 수 있다.
- 이 내용을 재구성하는 기능이 cos.jar 파일의 MultipartRequest이다.

```html
<body>
  <a href="WEB-INF/fileup/database_create.sql">fileDown</a>
</body>
```

- WEB-INF는 브라우저가 접근이 불가능하다.

```java
@Override
protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String fileName = request.getParameter("fsn");
    String path = request.getServletContext().getRealPath("/WEB-INF/fileup/");

    response.setContentType("application/octet-stream");
    response.setHeader("content-disposition","attchment;filename="+fileName);
    InputStream in = new FileInputStream(path + fileName);
    OutputStream out = response.getOutputStream();

    byte[] buf = new byte[1024*4];
    int len = 0;
    while((len = in.read(buf))!=-1){
        out.write(buf,0,len);
    }
    out.flush();
    out.close();
    in.close();
}
```

- 전송 이전에 어떤 성격의 정보를 전달할지를 먼저 브라우저에 알려준다.
- application/octet-stream 과 같은 것을 MIME TYPE이라고 한다.
- response를 내보기전에 content type을 지정해야한다.

```java
<%
int i = 0;
if(i != 0) {
    request.setAttribute("메세지","경고!!!");
    RequestDispatcher rd = request.getRequestDispatcher("/Test124_1.jsp");
    rd.forward(request,response);

}else {
    request.setAttribute("메세지","경고!!!");
    String ctxPath = request.getContextPath();
    response.sendRedirect(ctxPath+"/Test124_1.jsp");
}
%>
```

- sendRedirect는 주소가 바뀐다.
- forward는 다음 페이지에 뭔가 전달이 가능하다.
- forward는 같은 Context에서만 가능하고
- request에 심어서 보내면 받는 쪽에서는 꺼내어 쓸 수 있다.

```java
public class Test126 {
    public static void main(String[] args) throws Exception{
        URL rl = new URL("http://localhost:8080/study3/apple_list.do");
        URLConnection ucon = rl.openConnection();
        InputStream in = ucon.getInputStream();

        BufferedReader bin = new BufferedReader(new InputStreamReader(in));

        String l = null;
        while((l = bin.readLine()) != null) {
            System.out.println(l);
        }
        in.close();
    }
}
```

- java.net.URL 은 이것 자체가 작은 웹브라우저의 역할을 한다.
- 요청을 날리고 그에 해당하는 응답을 받아들인다.
- 안드로이드 앱에서 버튼을 누르면 오늘의 배송정보가 넘어오는 ?
- 소켓으로 다 짜는 것이 아니라 http 프로토콜로 서버와 통신할 때는 이클래스를 주로 이용한다.

```java
public static void main(String[] args) throws Exception{
    URL rl = new URL("http://localhost:8080/study3/Test126.jsp?pw=2345");
    URLConnection ucon = rl.openConnection();
    InputStream in = ucon.getInputStream();

    BufferedReader bin = new BufferedReader(new InputStreamReader(in));

    String l = null;
    while((l = bin.readLine()) != null) {
        System.out.println(l);
    }
    in.close();
}
```

- 앱 같은 경우에서 서버로 부터 많은 데이터를 다운받아야할 경우에 URL클래스를 이용하여 JSP파일로부터 정보를 다운 받는다.
- 이게 워낙 많이쓰이다 보니 이를 강화한 오픈소스 라이브러리가 등장. apache http client 프로젝트
