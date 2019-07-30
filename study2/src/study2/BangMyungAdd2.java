package study2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BangMyungAdd2 extends HttpServlet {
    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("BangMyungAdd2");
        Connection conn = null;
        Statement stmt = null;

        try {
            String gul = req.getParameter("gul");
            System.out.println(gul);
            Class.forName("oracle.jdbc.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE","HR","HR");
            stmt = conn.createStatement();
            String sql = "INSERT INTO bangmyung_t values (seq_bangmyung.nextval,'"+gul+"',sysdate)";
            stmt.executeUpdate(sql);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(conn != null) {conn.close();}
                if(stmt!=null){ stmt.close(); }
            }
            catch (SQLException e){
                e.printStackTrace();
            }

        }
        resp.sendRedirect("/study2/bangmyung_list");
    }
}
