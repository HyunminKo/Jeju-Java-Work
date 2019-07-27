import java.sql.*;

public class Test106 {
    public static void main(String[] args) throws Exception {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE", "HR", "HR");
            conn.setAutoCommit(false);
            stmt = conn.createStatement();

            stmt.executeUpdate("insert into test_tx values(105)");
            stmt.executeUpdate("insert into test_tx values(106)");
            stmt.executeUpdate("insert into test_tx values(107)");
            stmt.executeUpdate("insert into test_txUUU values(108)");
            conn.commit();

        } catch (Exception e) {
            if (conn != null) {
                conn.rollback();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
    }
}