import java.sql.*;
import java.util.*;

public class Test104 {
    public static List<BangMyungVO> findAll() throws Exception {
        List<BangMyungVO> list = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE", "HR", "HR");
            stmt = conn.createStatement();

            String sql = "select * from bangmyung_t";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int no = rs.getInt("no");
                String gul = rs.getString("gul");
                String theTime = rs.getString("the_time");
                list.add(new BangMyungVO(no, gul, theTime));
            }

        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }

        }
        return list;
    }

    public static void main(String[] args) throws Exception {
        Class.forName("oracle.jdbc.OracleDriver");
        List<BangMyungVO> ls2 = findAll();
        for (BangMyungVO t : ls2) {
            System.out.println(t.getNo() + " " + t.getGul() + " " + t.getTheTime());
        }
    }
}