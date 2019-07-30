package study3;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ConfigServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("init");

        String val = config.getInitParameter("apple");
        System.out.println(val);

        ServletContext application = config.getServletContext();
        System.out.println(application.getRealPath("/WEB-INF/"));
    }

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("service");

        System.out.println("getContextPath: "+ req.getContextPath());
        System.out.println("getRequestURI: "+ req.getRequestURI());
        System.out.println("getRemoteAddr: "+ req.getRemoteAddr());
        System.out.println("getHeader" + req.getHeader("User-Agent"));
    }
}
