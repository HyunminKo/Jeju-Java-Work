package mvc;

import study3.BangMyungDAO;
import study3.BangMyungDAO_OracleImpl;
import study3.BangMyungVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/apple_add2.do")
public class CtrlAdd2 implements Controller{

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");
        String gul = request.getParameter("gul");

        BangMyungVO vo = new BangMyungVO();
        vo.setGul(gul);

        BangMyungDAO dao = new BangMyungDAO_OracleImpl();
        dao.add(vo);

        return "redirect:/apple_list.do";
    }
}
