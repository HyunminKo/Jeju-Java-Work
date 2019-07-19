import java.lang.reflect.Method;

public class Test066_3{
    public static void main(String[] args) throws Exception{
        Class<?> cls = Class.forName("temp.Test066A");
        Object obj = cls.newInstance();

        //Method mtd = cls.getMethod("print");
        Method mtd = cls.getMethod("print2",int.class);
        Method mtd2 = cls.getMethod("print3");
        System.out.println(mtd);

        Object r = mtd.invoke(obj, 20);
        System.out.println((Integer) r);
        System.out.println(((Integer) r).intValue());
        System.out.println(mtd2.invoke(obj));
    }
}