interface IGreet {
    public String greet();
}
class MericGreet implements IGreet{
    @Override
    public String greet() {return "Merci";}
}
class HelloGreet implements IGreet{
    @Override
    public String greet() {return "Hello";}
}
abstract class GreetDeco implements IGreet {
    protected IGreet ig = null;

    GreetDeco(IGreet i){
        ig = i;
    }
}

class SharpDeco extends GreetDeco {
    SharpDeco(IGreet i){
        super(i);
    }
    public String greet() {return "#" + ig.greet() + "#";}
}
class StarDeco extends GreetDeco {
    StarDeco(IGreet i){
        super(i);
    }
    public String greet() {return "*" + ig.greet() + "*";}
}

public class Test039 {
    public static void main(String[] args){
        IGreet ig = new StarDeco(new HelloGreet());
        System.out.println(ig.greet());
    }
}