public class Test008 {
    public static void main(String[] args){
        int t1 = 8;
        int t2 = 32;
        int t3 = 16;
        int t4 = 30;
        int t5 = 24;
        System.out.println(t1);
        System.out.println(t1+t2);
        System.out.println(t1+t2+t3);
        System.out.println(t1+t2+t3+t4);
        System.out.println(t1+t2+t3+t4+t5);

        int loc = 8;
        System.out.println(loc);
        loc += 32;
        System.out.println(loc);
        loc += 16;
        System.out.println(loc);
        loc += 30;
        System.out.println(loc);
        loc += 24;
        System.out.println(loc);

        int[] t = {8,32,16,30,24};

        for(int i = 0 ; i < t.length; i++){
            int sum = 0;
            for(int j = 0; j <= i; j++){
                sum += t[j];
            }
            System.out.println(sum);
        }
    }
}