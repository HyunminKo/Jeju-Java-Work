package study3;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class StateServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String use = req.getParameter("use");
        System.out.println(use);

        if("addCookie".equals(use)){
            Cookie ck = new Cookie("name","apple");
            resp.addCookie(ck);
        }else if ("readCookie".equals(use)){
            Cookie[] cks = req.getCookies();
            if(cks != null){
                for (int i = 0; i < cks.length; i++) {
                    System.out.println(cks[i].getName() + ": "+cks[i].getValue());
                }
            }else {
                System.out.println("Cookie is null");
            }
        }
    }
}
