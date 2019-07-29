package apple;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class HelloWorld2 extends HttpServlet {
    String theTime = null;

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("HelloWorld2");
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "HR", "HR");
            Statement stmt = conn.createStatement();

            String sql = "SELECT SYSDATE FROM DUAL";
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                theTime = rs.getString(1);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("   <body>");
        out.println("       Hello World 2 : ^^* " + theTime);
        out.println("   </body>");
        out.println("</html>");
        out.close();
    }
}
// javac -d ../StudyHome/WEB-INF/classes -classpath servlet-api.jar