package study3;

import org.apache.log4j.Logger;

public class Util {
    static Logger log = null;
    static {
        log = Logger.getLogger("apple");
    }
    public static void l(Object obj){
        if(obj == null){
            log.info("[null]");
        }else {
            log.info(obj.toString());
        }
    }
    public static int parseInt(String l){
        try{
            return Integer.parseInt(l);
        }catch (Exception e){
            return -1;
        }
    }
    public static String toKorean(String l){
        if(l == null || l.equals("")){
            return l;
        }
        try {
            byte[] bs = l.getBytes("8859_1");
            l = new String(bs,"UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return l;
    }
}
