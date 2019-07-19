import java.lang.reflect.Method;
import temp.PrintStars;
import java.lang.annotation.Annotation;

public class Test067_2 {
    public static void main(String[] args) throws Exception{
        Class<?> cls = Class.forName("temp.Test067");
        Object obj = cls.newInstance();
        Method mtd = cls.getMethod("print");
        System.out.println(mtd.invoke(obj));

        Annotation anot = mtd.getAnnotation(PrintStars.class);
        Object r = mtd.invoke(obj);
        if(anot != null){
            System.out.print("**");
        }
        System.out.println(((Integer)r).intValue());
    }
}