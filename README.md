```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}
```

- HelloWorld.java 로 저장한다. JavaWork
- javac HelloWorld.java -> HelloWorld.class
- dir(Windows), ls(os x)
- public static void main(String[] args) 를 가지고 있는 클래스가 실행 가능하다.

#### Java 자료형

자료형 가지고 변수를 선언할 수 있다  
변수는 값을 저장하는 기억공간  
값은 대입 연산(=)을 사용하여 저장한다

- byte, short, int, long: 정수
- float, double: 실수
- boolean: boolean
- char: 문자

정수형 변수를 사용하기 위해서 4개의 자료형을 사용할 수 있는데, 4개가 있는 이유는 자료형마다 할당되는 공간의 크기가 다르기 때문이다.

- byte(8bit)
- short(16bit)
- int(32bit)
- long(64bit)

```java
public class Test002 {
    public static void main(String[] args){
        int i;
        i = 100;
        i = 200;
        System.out.println( i );
    }
}
```

- 변수의 값을 바뀔 수 있다. 마지막 값만 기억한다.

```java
public class Test003 {
    public static void main(String[] args){
        int i;
        10 = i
        System.out.println( i );
    }
}
```

- 오른쪽의 값을 왼쪽에 변수에 넣어야 한다.

```java
public class Test004 {
    public static void main( String[] args){
        int i,j;
        i = 10;
        j = i;
        i = 20;
        System.out.println(i);
        System.out.println(j);
    }
}
```

- 오른쪽 값이 복사되어 왼쪽으로 대입된다.

```java
public class Test005 {
    public static void main(String[] args){
        int i = 10;
        i = i + 10;
        System.out.println(i);

        j = 20;
    }
}
```

- i + 10 : 변수와 값은 연산가능. 변수가 가진 값과 연산.
- 대입시에는 오른쪽이 먼저 동작하고 다음에 대입 된다.
- 선언되지 않은 변수는 이용할 수 없다.

```java
public class Test007 {
    public static void main(String[] args){
        short t = 4;
        int a = t;
        // t = a;

        double j = 10.0;
        System.out.println(j + 10);
        System.out.println((int)j + 10); //casting
        System.out.println(a);
    }
}
```

- byte < short < int < long < float < double
- 작은 타입에서 큰 타입은 자동 변환 되지만 큰 타입에서 작은 타입은 강제변환 없으면 에러난다.

- 기본적으로 같은 타입형 변수만이 연산 가능하다.

### 문제

```java
public class Test008 {
    public static void main(String[] args){
        int t1 = 8;
        int t2 = 32;
        int t3 = 16;
        int t4 = 30;
        int t5 = 24;
        System.out.println(t1);
        System.out.println(t1+t2);
        System.out.println(t1+t2+t3);
        System.out.println(t1+t2+t3+t4);
        System.out.println(t1+t2+t3+t4+t5);

        int loc = 8;
        System.out.println(loc);
        loc += 32;
        System.out.println(loc);
        loc += 16;
        System.out.println(loc);
        loc += 30;
        System.out.println(loc);
        loc += 24;
        System.out.println(loc);

        int[] t = {8,32,16,30,24};

        for(int i = 0 ; i < t.length; i++){
            int sum = 0;
            for(int j = 0; j <= i; j++){
                sum += t[j];
            }
            System.out.println(sum);
        }
    }
}
```

```java
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
```
