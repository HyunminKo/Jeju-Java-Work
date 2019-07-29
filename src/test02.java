import java.util.Random;

import java.awt.*;

public class test02 {
    public static void main(String[] args) {
        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        Color randomColor = new Color(r, g, b);
        System.out.println(randomColor.getBlue());
        System.out.println(randomColor.getRed());
        System.out.println(randomColor.getGreen());
    }
}