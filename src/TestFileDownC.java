import java.net.Socket;
import java.io.*;

public class TestFileDownC {
	public static void main(String[] args) throws Exception {
		long startTime = System.currentTimeMillis();

		Socket skt = new Socket("192.168.2.100", 1123);

		OutputStream out = new FileOutputStream("sample.pdf");
		InputStream in = skt.getInputStream();

		int r = 0;
		byte[] buf = new byte[512];

		while ((r = in.read(buf)) != -1) {
			out.write(buf, 0, r);
		}

		in.close();
		out.close();

		skt.close();

		long endTime = System.currentTimeMillis();
		System.out.println(endTime - startTime);
	}
}