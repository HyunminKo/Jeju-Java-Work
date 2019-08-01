package study3;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class JSPServlet extends HttpServlet {

    private ServletContext application = null;
    private ServletConfig config = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.config = config;
        application = config.getServletContext();
        System.out.println(application.getRealPath("WEB-INF"));
    }
    // -----------------------------------------
    // <%! %>은 여기에 온다.
    int i = 0;
    // -----------------------------------------
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("JSPServlet");

        HttpSession session = req.getSession();
        PrintWriter out = resp.getWriter();
        // -----------------------------------------
        // <% %>은 여기에 온다.
        int j = 0;
        // <%= %>은 out.println(...);으로 감싸준다.
        out.println(i++);
        out.println(j++);
        // -----------------------------------------
        out.flush();
        out.close();
    }
}
