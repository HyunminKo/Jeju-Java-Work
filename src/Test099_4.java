import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
class StudentVO {
    private String stId = null;
    private String name = null;
    private String addr = null;

    public String getStId() {return this.stId;};
    public void setStId(String stId) {this.stId = stId;};

    public String getName() {return this.name;};
    public void setName(String name) {this.name = name;};

    public String getAddr() {return this.addr;};
    public void setAddr(String addr) {this.addr = addr;};

    public String toString() {
        return this.getStId() + "\t" + this.getName() + "\t" + this.getAddr();
    }
}
public class Test099_4 {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/study?serverTimezone=UTC", "root",
                args[0]);
        Statement stmt = conn.createStatement();

        List<StudentVO> rl = new ArrayList<>();

        String sql = "select stid,name,addr from studentt";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            StudentVO vo =new StudentVO();
            vo.setStId(rs.getString("stid"));
            vo.setName(rs.getString("name"));
            vo.setAddr(rs.getString("stid"));
            rl.add(vo);
        }
        
        rs.close();
        stmt.close();
        conn.close();

        System.out.println(rl);
    }
}