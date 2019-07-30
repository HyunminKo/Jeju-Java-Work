package study3;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LifeServlet extends HttpServlet {
    int i = 0;
    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(this);

        PrintWriter out = resp.getWriter();
        out.println("<html><body>");
        synchronized (this) {
            i++;
            for(int i = 0 ; i < 200000; i++){
                out.println(this.i);
            }
        }
        out.println("</body></html>");
        out.flush();
        out.close();
    }
}
