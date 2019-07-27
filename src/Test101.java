import java.sql.*;

public class Test101 {
    public static void main(String[] args) throws Exception {
        Class.forName("oracle.jdbc.OracleDriver");
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE", "HR", "HR");
        Statement stmt = conn.createStatement();
        String gul = "HelloWorld";
        String sql = "insert into bangmyung_t values(seq_bangmyung.NEXTVAL, '" + gul + "',sysdate)";
        int result = stmt.executeUpdate(sql);
        System.out.println(result);
        stmt.close();
        conn.close();
    }
}