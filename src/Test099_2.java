import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class Test099_2 {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/study?serverTimezone=UTC", "root",
                args[0]);
        Statement stmt = conn.createStatement();

        String sql = "select stid,name,addr from studentt";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            String stid = rs.getString("stid");
            String name = rs.getString("name");
            String addr = rs.getString("addr");
            System.out.println(stid + "\t" + name + "\t" + addr);
        }
        rs.close();
        stmt.close();
        conn.close();
    }
}