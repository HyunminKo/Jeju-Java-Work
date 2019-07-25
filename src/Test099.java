import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;

public class Test099 {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/study?serverTimezone=UTC", "root",
                args[0]);

        Statement stmt = conn.createStatement();
        String sql = "insert into studentt values('10107','또오치','쌍문동')";
        String sql2 = "delete from studentt where stid='10107'";
        String sql3 = "update studentt set addr = '이도동' where stid='10101'";
        // stmt.executeUpdate(sql);
        // stmt.executeUpdate(sql2);
        stmt.executeUpdate(sql3);
        System.out.println(stmt);
        stmt.close();
        conn.close();
    }
}