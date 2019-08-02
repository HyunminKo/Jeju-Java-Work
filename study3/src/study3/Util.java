package study3;

public class Util {
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
