package hw;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class gugudan extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Random rand = new Random();

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("   <body>");
        out.println("       <table border=\"1\" cellspacing=\"2\" cellpadding=\"12\">");
        for(int i = 1; i <= 9 ; i++){
            out.println("       <tr>");
            for(int j = 1 ; j <= 9 ; j++){
                float r = (float) (rand.nextFloat() / 2f + 0.5);
                float g = (float) (rand.nextFloat() / 2f + 0.5);
                float b = (float) (rand.nextFloat() / 2f + 0.5);
                Color rc = new Color(r, g, b);
                out.println(String.format("<td style=\"background-color:rgba(%d,%d,%d,%d)\">",rc.getRed(),rc.getGreen(),rc.getBlue(),1));
                out.println(String.format("%d * %d = %d",i,j,i*j));
                out.println("       </td>");
            }
            out.println("       </tr>");
        }
        out.println("       </table>");
        out.println("   </body>");
        out.println("</html>");
    }
}
