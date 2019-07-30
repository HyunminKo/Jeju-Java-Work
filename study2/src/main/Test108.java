package main;

import study2.BangMyungDAO;
import study2.BangMyungDAO_OracleImpl;
import study2.BangMyungVO;

public class Test108 {
    public static void main(String[] args) {
        BangMyungDAO dao = new BangMyungDAO_OracleImpl();
        BangMyungVO pvo = new BangMyungVO();
        for(int i = 0 ; i < 10000; i++){
            pvo.setGul("TestMainTest108"+i);
            try {
                dao.add(pvo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
