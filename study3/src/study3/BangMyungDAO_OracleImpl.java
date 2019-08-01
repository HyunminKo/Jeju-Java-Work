package study3;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BangMyungDAO_OracleImpl implements BangMyungDAO {


    @Override
    public List<BangMyungVO> findAll() {
        List<BangMyungVO> list = new ArrayList<>();
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            Connection conn= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE","HR","HR");
            Statement stmt = conn.createStatement();
            String sql = "SELECT no,gul,the_time FROM bangmyung_t order by no";
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                list.add(
                        new BangMyungVO(
                                rs.getInt("no"),
                                rs.getString("gul"),
                                rs.getString("the_time")));
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void add(BangMyungVO vo) throws Exception{
        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName("oracle.jdbc.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE","HR1234","HR");
            stmt = conn.createStatement();
            String sql = "INSERT INTO bangmyung_t values (seq_bangmyung.nextval,'"+vo.getGul()+"',sysdate)";
            stmt.executeUpdate(sql);

        }catch (Exception e){
            throw e;
        }finally {
            try{
                if(conn != null) {conn.close();}
                if(stmt!=null){ stmt.close(); }
            }
            catch (SQLException e){
                e.printStackTrace();
            }

        }
    }
}
