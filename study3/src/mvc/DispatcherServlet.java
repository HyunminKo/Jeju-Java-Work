package mvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

public class DispatcherServlet extends HttpServlet {
    private Map<String,Controller> mapp = null;
    @Override
    public void init(ServletConfig config) throws ServletException {
        mapp = new Hashtable<>();

        String cs = config.getInitParameter("controllers");
        for(String clsName : cs.split(",")){
            try {
                Class<?> cls = Class.forName(clsName.trim());
                RequestMapping an = cls.getAnnotation(RequestMapping.class);
                Controller value = (Controller) cls.newInstance();
                String key = an.value();
                mapp.put(key,value);
            }catch (Exception e){

            }
        }
        System.out.println(mapp.toString());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // http://192.168.2.100:8081/study3/apple_list.do
        String ctxPath = request.getContextPath(); // /study3
        String uri = request.getRequestURI(); // /study3/asdf.do
        uri = uri.substring(ctxPath.length()); // asdf.do
        System.out.println(uri);

        Controller ctrl = mapp.get(uri);
        if(ctrl == null){
            System.out.println("해당 요청은 미등록된 요청입니다.");
            return;
        }

        try {
            String l = ctrl.handleRequest(request,response);
            String redirect = "redirect:";
            if(l==null){}
            else if(l.startsWith("redirect:")){
                response.sendRedirect(ctxPath+l.substring(redirect.length()));
            }else {
                RequestDispatcher rd = request.getRequestDispatcher(l);
                rd.forward(request,response);
            }
        } catch (Exception e) {
            request.getSession().setAttribute("err",e);
            response.sendRedirect(ctxPath +"/apple_err.jsp");
        }

    }
}
