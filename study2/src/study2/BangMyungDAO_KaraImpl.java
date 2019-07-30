package study2;

import java.util.ArrayList;
import java.util.List;

public class BangMyungDAO_KaraImpl implements BangMyungDAO {
    private static List<BangMyungVO> data = new ArrayList<>();
    @Override
    public void add(BangMyungVO vo) throws Exception {
        data.add(vo);
    }

    @Override
    public List<BangMyungVO> findAll() {
        return data;
    }
}
