package main;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Test127 {
    public static void main(String[] args) throws Exception{
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet("http://localhost:8080/study3/Test126.jsp?pw=1234");
        httpGet.addHeader("User-Agent","Mozila/5.0");
        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
        int resCode = httpResponse.getStatusLine().getStatusCode();
        if(resCode == 200){
            InputStream ins = httpResponse.getEntity().getContent();
            BufferedReader bin = new BufferedReader(new InputStreamReader(ins,"UTF-8"));
            String l = null;
            while((l=bin.readLine())!=null){
                System.out.println(l);
            }
            ins.close();
        }
    }
}
