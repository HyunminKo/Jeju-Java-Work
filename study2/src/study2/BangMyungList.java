package study2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class BangMyungList extends HttpServlet {
    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        BangMyungDAO dao = new BangMyungDAO_OracleImpl();
        List<BangMyungVO> list = null;
        try{
            list = dao.findAll();
        }catch (Exception e){
            e.printStackTrace();
        }

        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("   <body>");
        out.println("       <table border=\"0\" cellspacing'2' cellpadding='8'>");
        out.println("<tr><td width='80'></td><td width='320'></td><td width='160'></td></tr>");
        for(BangMyungVO vo : list){
            out.println("           <tr>");
            out.println("               <td>");
            out.println(vo.getNo());
            out.println("               </td>");
            out.println("               <td>");
            out.println(vo.getGul());
            out.println("               </td>");
            out.println("               <td>");
            out.println(vo.getTheTime());
            out.println("               </td>");
            out.println("           </tr>");
        }
        out.println("       </table>");


        out.println("<br/><br/>");
        out.println("<form method=\"POST\" action=\"bangmyung_add2\">");
        out.println("   <input type=\"text\" size=\"50\" name=\"gul\"/>");
        out.println("   <input type=\"submit\"/>");
        out.println("</form>");
        out.println("   </body>");
        out.println("</html>");
        out.close();
    }
}
