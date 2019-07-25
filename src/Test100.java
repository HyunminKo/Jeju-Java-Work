import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
public class Test100 {
    public static void main(String[] args) throws Exception{
        Class.forName("oracle.jdbc.OracleDriver");
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521/XE","system","oracle");
        
        Statement stmt = conn.createStatement();
        String sql = "SELECT SYSDATE FROM DUAL";
        ResultSet rs = stmt.executeQuery(sql);

        while(rs.next()){
            String l = rs.getString(1);
            System.out.println(l);
        }
        rs.close();
        stmt.close();
        conn.close();
    }
}