public class Test009 {
    public static void main(String[] args){
        double money = 100.0;
        double interest = 6.6 / 100;
        for(int i = 0 ; i < 100 ; i++){
            money = money + (money * interest);
            System.out.println(String.format("%d년 후 : \t%f",i+1,money));
        }
    }
}