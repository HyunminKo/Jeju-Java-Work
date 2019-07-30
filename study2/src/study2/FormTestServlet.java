package study2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FormTestServlet extends HttpServlet {
    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("FormTestServlet");

        String gul = req.getParameter("gul");
        System.out.println("글: " + gul);

        System.out.println("abcd: " + req.getParameter("abcd"));
        System.out.println("xyzz: " + req.getParameter("xyzz"));

        System.out.println("method: " + req.getParameter("method"));
        System.out.println("password: " + req.getParameter("pwd"));

        System.out.println("textarea: " + req.getParameter("content"));
        System.out.println("select: " + req.getParameter("fruit"));

        resp.sendRedirect("/study2/test_02.html");
    }
}
