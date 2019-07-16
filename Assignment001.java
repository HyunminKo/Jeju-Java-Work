public class Assignment001 {
    public static void main(String[] args){
        final double money = 1000.0;
        final double interest = 6.6 / 100;
        final int year = 100;

        func1(money,interest,year); // 1년만 납입
        func2(money,interest,year); // 매년 납입
        func3(money,interest,year); // 매년 납입
    
    }
    public static void func1(double money, double interest, int year) {
        for(int i = 0 ; i < year ; i++){
            money += (money * interest);
        }
        System.out.println(String.format("%d년 후 : \t%f",year,money));
    }

    public static void func2(double money, double interest, int year) {
        double sum = 0;
        for(int i = 0 ; i < year ; i++){
            double temp = money;
            for(int j = 0; j < year - i; j++){
                temp = temp + (temp * interest);
            }
            sum += temp;
        }
        System.out.println(String.format("%d년 후 : \t%f",year,sum));
    }
    public static void func3(double money, double interest, int year) {
        double sum = 0;
        for(int i = 0 ; i < year ; i++){
            sum += money;
            sum = sum + (sum * interest);
        }
        System.out.println(String.format("%d년 후 : \t%f",year,sum));
    }
}