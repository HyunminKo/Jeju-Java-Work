package mvc;

import study3.BangMyungDAO;
import study3.BangMyungDAO_OracleImpl;
import study3.BangMyungVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RequestMapping("/apple_list.do")
public class CtrlList implements Controller{

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        BangMyungDAO dao = new BangMyungDAO_OracleImpl();
        List<BangMyungVO> rl = dao.findAll();
        request.setAttribute("rl",rl);
        return "/apple_list.jsp";
    }
}
