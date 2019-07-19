class TempException extends RuntimeException {}
public class Test074 {
    public static void main(String[] args){
        int i = 0;
        if(i==0){
            throw new TempException();
        }
        System.out.println(0);
    }
}