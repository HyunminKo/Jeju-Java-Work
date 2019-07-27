import java.sql.*;

public class Test103 {
    public static void addGul(String gul) throws Exception {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE", "HR", "HR");
            stmt = conn.createStatement();
            String sql = "insert into bangmyung_t values(seq_bangmyung.nextval,'" + gul + "', sysdateXX)";
            stmt.executeUpdate(sql);
        } catch (Exception e) {
            throw e;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Class.forName("oracle.jdbc.OracleDriver");
        addGul("Hello Apple");
    }
}