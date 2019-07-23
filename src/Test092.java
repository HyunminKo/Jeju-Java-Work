public class Test092 {
    public static void main(String[] args){
        String l = "HelloWorld";
        String r = "HelloWorld";
        String t = l.substring(2,5);

        System.out.println( r == l );
        System.out.println( "llo" == t );
        System.out.println( "llo".equals(t));


        String t1 = "10101,100";
        System.out.println(t1.substring(0,t1.indexOf(",")));
        System.out.println(t1.substring(t1.indexOf(",")+1));
    }
}