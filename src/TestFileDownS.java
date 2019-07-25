import java.net.*;
import java.io.*;

class FileDownThread implements Runnable {
    Socket skt = null;

    FileDownThread(Socket skt) {
        this.skt = skt;
    }

    public void run() {

        String fle = "../sample.pdf";
        try {
            InputStream in = new FileInputStream(fle);
            OutputStream out = skt.getOutputStream();

            int r = 0;
            byte[] buf = new byte[512];

            while ((r = in.read(buf)) != -1) {
                out.write(buf, 0, r);
                out.flush();
            }
            skt.close();
            out.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

public class TestFileDownS {
    public static void main(String[] args) throws Exception {
        ServerSocket svr = new ServerSocket(1123);
        Thread[] threads = new Thread[3];
        for (int i = 0; i < 3; i++) {
            Socket skt = svr.accept();

            threads[i] = new Thread(new FileDownThread(skt));
            threads[i].start();
        }
        try {
            for (int i = 0; i < 3; i++) {
                threads[i].join();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        svr.close();
    }
}