package study2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HelloWorld3 extends HttpServlet {
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        System.out.println("HelloWorld3");
        List<BangMyungVO> list = new ArrayList<>();
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            Connection conn= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE","HR","HR");
            Statement stmt = conn.createStatement();
            String sql = "SELECT no,gul,the_time FROM bangmyung_t";
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                list.add(
                        new BangMyungVO(
                                rs.getInt("no"),
                                rs.getString("gul"),
                                rs.getString("the_time")));
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e){
            e.printStackTrace();
        }

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("   <body>");
        out.println("       <table border=\"1\">");
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
        out.println("   </body>");
        out.println("</html>");
        out.close();
    }
}
