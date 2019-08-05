package example;

import jdbcUtil.RowMapper;
import study3.BangMyungVO;

import java.sql.ResultSet;

public class BangMyungRowMapper implements RowMapper<BangMyungVO> {
    @Override
    public BangMyungVO mapRow(ResultSet rs) throws Exception {
        BangMyungVO vo = new BangMyungVO();
        vo.setNo(rs.getInt("no"));
        vo.setGul(rs.getString("gul"));
        vo.setTheTime(rs.getString("theTime"));
        return vo;
    }
}
