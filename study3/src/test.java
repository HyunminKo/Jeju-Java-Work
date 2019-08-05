import example.BangMyungRowMapper;
import jdbcUtil.JdbcTemplate;

public class test {
    public static void main(String[] args) {
        JdbcTemplate template = new JdbcTemplate();
        String sql = "select * from ?";
        try {
            template.query(sql,new BangMyungRowMapper(),"bangmyung_t");
            template.query(sql,new UserRowMapper(),"user");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
