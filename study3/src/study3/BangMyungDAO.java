package study3;

import java.util.List;

public interface BangMyungDAO{
    void add(BangMyungVO vo) throws Exception;
    List<BangMyungVO> findAll();
}
