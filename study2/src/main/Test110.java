package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Test110 {
    public static void main(String[] args) throws Exception{
//        String data = "xyz";
        String data = null;
        Class.forName("oracle.jdbc.OracleDriver");
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe","HR","HR");

        String sql = "insert into temp20t values('"+data+"')";
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(sql);

        stmt.close();
        conn.close();
    }
}
