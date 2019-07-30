package main;

import java.util.StringTokenizer;

public class Test107 {
    public static void main(String[] args) {
        String l = "이것은 글을 쓴 내용에 해당하는 #해쉬태그 문장입니다.";
        StringTokenizer st = new StringTokenizer(l);
        while(st.hasMoreTokens()){
            String token = st.nextToken();
            System.out.println(token.startsWith("해당"));
            if(token.charAt(0) == '#'){
                System.out.println(token);
            }
        }
    }
}
