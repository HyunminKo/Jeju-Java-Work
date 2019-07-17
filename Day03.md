## Day03

```java
class Apple2 {
    int data = 0;
    int add(int i, int j){
        return 100;
    }
}

public class Test026s {
    public static void main(String[] args){
        Apple2 t = new Apple2();
        int i = t.add(10,20);
    }
}
```

- 멤버변수(property), 멤버함수(method)
- 클래스로 뭐하지? 참조형 변수 선언, 인스턴스 생성
- 인스턴스와 클래스 관계, 참조형 변수와 인스턴스 생성

```java
class A {
    int apple = 10;
}
class B extends A{
    int add( int i, int j){ return 100;}
}
public class Test027 {
    public static void main(String[] args){
        B t = new B();
    }
}
// 클래스 3가지: 참조형 변수 선언, 인스턴스 생성, 상속 받아 클래스 선언.
```

- 객체지향 언어의 3대 속성

  - 상속 : 클래스를 상속해서 클래스를 만든다
  - 캡슐화 : 감추고 싶은건 감출 수 있다
  - 다형성 : 하나의 심볼이 여러 실체에 매핑될 수 있다

- 클래스 B는 클래스 A를 상속하여 만들어졌음을 명시함.
  - A에서 선언한 멤버변수 멤버함수를 내려받겠다

```java
class Apple {
    int data = 0;
    void print(){
        System.out.println(this.data);
    }
}
public class Test028 {
    public static void main(String[] args){
        Apple t = new Apple();
        t.print();
    }
}
```

- return 문장이 없는 함수를 서브루틴이라 한다. 리턴타입을 void로 선언한다.
- 멤버함수 안에서 자신이 소속된 인스턴스에 대한 포인터를 사용가능 :this
- 함수 하나의 길이가 크다고 해서 인스턴스를 많이 생성해도 메모리에 부담이 가지 않는다

```java
class Node {
    int data = 0;
    Node next = null;

    Node(int i, Node n){
        this.data = i;
        this.next = n;
    }
}
public class Test029{
    public static void main(String[] args){
        Node head = new Node(0,null);
        Node tail = head;

        tail.next = new Node(10,null);
        tail = tail.next;

        tail.next = new Node(20,null);
        tail = tail.next;

    }
}
```

- 모든 참조형 변수에는 null이라는 값이 대입 가능하다. 가리키는 인스턴스가 없다.
- new Node(0,nullO) 로 클래스 Node의 생성자가 호출되며 실인자로 넘어간 0과 null이 멤버 변수를 초기화 하고 head라는 참조형 변수가 해당 인스턴스를 가리키게 된다. 그리고 tail이라는 참조형 변수가 head가 가리키고 있는 인스턴스를 가리키게 된다.

```java
class A {
    A() {
        System.out.println("A constructor");
    }
    void print() {
        System.out.println("A print");
    }
}
class B extends A {
    B(){
        System.out.println("B constructor");
    }
    void print() {
        System.out.println("A print");
        super.print();
    }
}
public class Test030 {
    public static void main(String[] args){
        B t = new B();
        t.print();

        A t2 = new A();
        t2.print();

    }
}
```

- 상속관계가 존재할 때 (조상클래스, 자손클래스) 자손의 인스턴스를 생성하면 조상의 생성자가 먼저 호출 되고 자손의 생성자가 호출된다.

- 생성자는 상속 되지 않는다. 다만 호출 될 뿐이다. 멤버 함수도 아니다. 참조형 변수로 호출할 수 없다.

- 조상에서 선언한 멤버함수를 자손에서 다시 선언할 수 있다.

- 물려받은 자손쪽에서 물려받은 함수를 호출 하고 싶을 때 super라는 키워드를 이용한다.

```java
class A {
    int i = 100;
    void print() System.out.println("A print");
}
class B extends A {
    void print() {System.out.println("B print")};
    void print2() {System.out.println("B print2")};
}
public class Test031 {
    public static void main(String[] args){
        A t = new B();
        t.print();
    }
}
```

- 조상 타입의 변수로 자손의 인스턴스를 가리킬 수 있다.
- 조상에서 선언된 멤버 함수 멤버 변수만 호출이 가능하다.
- 만일 오버라이딩 된 함수를 호출한다면 이때는 오버라이딩 된 것이 호출된다.

```java
class A {
    int i = 100;
    void print() {System.out.println("A print");}
}
class B extends A {
    int i = 200;
    void print() {System.out.println("B print");}
    void print2() {System.out.println("B print2");}
}
public class Test032 {
    public static void main(String[] args){
        A t = new B();
        t.print();
        System.out.println(t.i);
    }
}
```

- 조상의 참조형 변수가 자식의 인스턴스를 가리킬 때, 오버라이딩 개념은 멤버함수에만 있기 때문에 멤버 변수의 경우는 조상의 변수가 호출된다.

- 상속 X, 외부노출 X, 내가 쓰는거, "마약" : private
- 상속 O, 외부노출 X, 내가 쓰는거, "모나리자" : protected
- 상속 O, 외부노출 O, 내가 쓰는거, "별장" : public
- 클래스 안의 멤버변수, 멤버 함수 앞에 붙여준다. => 감출건 감추자(은닉성)

- java의 protected
  - 같은 패키지에서는 접근 가능
  - 다른 패키지에서는 접근 불가
- friendly:
  - 같은 패키지에서는 public
  - 다른패키지에서는 private

```java
class A {
    private int mayak = 0;
    protected int monarisa = 0;
    public int house2 = 0;
}
class B extedns A {
    void print(){
        System.out.prinln(monarisa);
    }
}
public class Test033 {
    public static void main(String[] args){
        A look = new A();
        System.out.println(look.monarisa)
    }
}
```

- protected를 포수의 사인으로 이해하면 좋다.  
  같은 팀의 다른 클래스에게는 알려지지만, 다른 팀에 소속된 클래스는 접근 불가해야 한다.
  후배에게 물려줄때는 당연하다.

```java
class A {
    private int data = 100;
    public int getData() {return data;}
}
class B extends A{
    private int data = 200;
    public int getData() {return data;}
}
public class Test034 {
    public static void main(String[] args){
        A t = new B();
        System.out.println(t.getData());
    }
}
```

- 멤버 변수는 무조건 private하게 선언한다.
  값을 읽고자 할때는 getSetter를 이용하여 접근한다.

- 조상에서 getXXX가 보이면 그런 변수가 있는 줄 알고 getXXX 변수는 피해간다.

```java
abstract class A {
    abstract public void print();
}
class B extends A {
    public void print(){
        System.out.println("test");
    }
}
public class Test035 {
    public static void main(String[] args){
        A t = new B();
        t.print();
    }
}
```

- 위의 print함수는 선언되었지만 정의되지 않았다. 반드시 abstract 붙여준다. abstract 메소드를 하나라도 가진 클래스는 반드시 abstract클래스라고 정의해야한다.

- abstract class는 인스턴스를 못만든다. 변수 선언, 상속은 가능하다.

- abstract 메소드를 오버라이딩 하면 abstract 성질이 없어지게 된다.

```java
interface ICalc {
    public void print();
}
class Calc implements ICalc{
    public void print() {System.out.println("A");}
}
public class Test038 {
    public static void main (String[] args){
        ICalc ic = new Calc();
        ic.print();
    }
}
```

- 인터페이스에 선언된 method는 모두다 abstract 해야한다.
- 인터페이스를 상속해서 클래스를 선언할 때는 implements 키워드를 사용해야한다.
- 상속 받은 클래스는 인터페이스에 선언된 메소드를 모두 오버라이딩 해야한다.
- 인터페이스는 일종의 abstract 클래스이다. 변수 선언,상속해 줄수 있음, 인스턴스 생성 불가,

```java
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
```

- 자손의 생성자에서 조상의 생성자 중 매개변수 있는 생성자를 호출 원하면 super로 지정.
