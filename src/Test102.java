import java.sql.*;
import java.util.*;

class BangMyungVO {
    private Integer no = null;
    private String gul = null;
    private String theTime = null;

    public BangMyungVO(Integer no, String gul, String theTime) {
        this.no = no;
        this.gul = gul;
        this.theTime = theTime;
    }

    public Integer getNo() {
        return this.no;
    }

    public String getGul() {
        return this.gul;
    }

    public String getTheTime() {
        return this.theTime;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public void setGul(String gul) {
        this.gul = gul;
    }

    public void setTheTiem(String theTime) {
        this.theTime = theTime;
    }
}

public class Test102 {
    public static void main(String[] args) throws Exception {
        Class.forName("oracle.jdbc.OracleDriver");
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE", "HR", "HR");
        Statement stmt = conn.createStatement();
        String sql = "select * from bangmyung_t";
        ResultSet rs = stmt.executeQuery(sql);
        List<BangMyungVO> list = new ArrayList<>();
        while (rs.next()) {
            int no = rs.getInt("no");
            String gul = rs.getString("gul");
            String theTime = rs.getString("the_time");
            list.add(new BangMyungVO(no, gul, theTime));
        }
        for (BangMyungVO t : list) {
            System.out.println(t.getNo() + " " + t.getGul() + " " + t.getTheTime());
        }
        rs.close();
        stmt.close();
        conn.close();

    }
}