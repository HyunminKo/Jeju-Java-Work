//연료 고갈이라는 에러를 클래스로 명시함.
class FuelException extends Exception {}

public class Test075 {
    public void carDrive(int fuel) throws FuelException{
        if(fuel == 0) {
            throw new FuelException();
        }
        System.out.println("GOGO");
    }
    public static void main(String[] args){
        Test075 t = new Test075();
        try {
            t.carDrive(0);
        }catch (FuelException e){
            e.printStackTrace();
            System.out.println("견인차를 불러라");
        }
        
    }
}