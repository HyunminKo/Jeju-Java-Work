public class Test066{
    public static void main(String[] args) throws Exception{
        Class cls = Class.forName("temp.Test066A");

        Object obj = cls.newInstance();

        System.out.println(obj.getClass().getName());
    }
}