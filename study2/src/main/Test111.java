package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class Test111 {
    public static void main(String[] args) throws Exception{
        String data = null;
        Class.forName("oracle.jdbc.OracleDriver");
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe","HR","HR");

        String sql = "insert into temp20t values(?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, data);
        stmt.executeUpdate();

        stmt.close();
        conn.close();
    }
}
