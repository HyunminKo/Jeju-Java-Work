package study3;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

public class FileUpServlet extends HttpServlet {
    private ServletContext application = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        application = config.getServletContext();
    }

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String l = process2(request);
        System.out.println(l);
    }

    private String process(HttpServletRequest request) throws IOException{
        byte[] buf = new byte[1024];
        int len = 0;
        StringBuffer sb = new StringBuffer();
        InputStream in = request.getInputStream();
        while((len = in.read(buf)) != -1) {
            sb.append(new String(buf,0,len));
        }
        in.close();
        return sb.toString();
    }
    private String process2(HttpServletRequest request) throws IOException{
        String path = application.getRealPath("/WEB-INF/fileup");

        MultipartRequest mpr = new MultipartRequest(
                request,path,1024 * 1024 * 20, "UTF-8",
                new DefaultFileRenamePolicy());

        String ofn = mpr.getOriginalFileName("apple");

        String fsn = mpr.getFilesystemName("apple");
        System.out.println(ofn+","+fsn);

        String title = mpr.getParameter("title");
        System.out.println();
        return path;
    }
}
