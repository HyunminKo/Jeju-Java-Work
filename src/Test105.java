import temp.BangMyungDAO;
import temp.BangMyungVO;
import java.util.*;

public class Test105 {
    public static void main(String[] args) throws Exception {
        BangMyungDAO.addGul("끝이 보이냐?");
        List<BangMyungVO> list = BangMyungDAO.findAll();
        for (BangMyungVO item : list) {
            System.out.println(item.getNo() + " " + item.getGul() + " " + item.getTheTime());
        }
    }
}