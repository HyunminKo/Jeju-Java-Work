public class Test004 {
    public static void main( String[] args){
        int i,j;
        int a[] = {1,2,3,4,5};
        int b[] = {10,4,3,2,1};
        i = 10;
        j = i;
        i = 20;
        System.out.println(i);
        System.out.println(j);

        b = a;
        System.out.println(b[0]);
        b[0] = 25;
        System.out.println(a[0]);
    }
}