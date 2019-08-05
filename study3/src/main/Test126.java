package main;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Test126 {
    public static void main(String[] args) throws Exception{
        URL rl = new URL("http://localhost:8080/study3/Test126.jsp?pw=2345");
        URLConnection ucon = rl.openConnection();
        InputStream in = ucon.getInputStream();

        BufferedReader bin = new BufferedReader(new InputStreamReader(in));

        String l = null;
        while((l = bin.readLine()) != null) {
            System.out.println(l);
        }
        in.close();
    }
}
