package study3;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileDownServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fileName = request.getParameter("fsn");
        String ofn = request.getParameter("ofn");
        if(ofn == null){
            ofn = fileName;
        }
        String path = request.getServletContext().getRealPath("/WEB-INF/fileup/");

        response.setContentType("application/octet-stream");
        response.setHeader("content-disposition","attchment;filename="+ofn);
        InputStream in = new FileInputStream(path + fileName);
        OutputStream out = response.getOutputStream();

        byte[] buf = new byte[1024*4];
        int len = 0;
        while((len = in.read(buf))!=-1){
            out.write(buf,0,len);
        }
        out.flush();
        out.close();
        in.close();
    }
}
