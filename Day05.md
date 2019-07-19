```java
class A {

}
class B extends A {
    public void print() {System.out.println(100);}
}
public class Test061 {
    public static void main (String[] args){
        A t = new B();
        // t.print();

        B t2 = (B) t;
        t2.print();

        A t3 = new A();
        //error, class cast exeception


        if( t3 instanceof B){
            System.out.println("True");
            B t4 = (B) t3;
            t4.print();
        }else {
            System.out.println("False");
        }
    }
}
```

```java
public class Test063 {
    public static void main(String[] args){
        boolean i = true;
        boolean j = false;

        System.out.println(i);
        System.out.println(j);

    }
}
```

- C언어는 정수 값 0은 false, 0 이 아닌 모든 정수는 true
- 비교 연산은 boolean형을 리턴한다
- 비교 연산 : ==, !=, >, <, >=, <= 6가지
- 모든 연산은 같은 자료형끼리만 가능하다

```java
public class Test063 {
    public static void main(String[] args){
        boolean i = true;
        boolean j = false;

        System.out.println(i);
        System.out.println(j);

        System.out.println(10 < 5);
        System.out.println(10 / 5);
        System.out.println(10 % 3);
        System.out.println(10.0 / 3);

        System.out.println(3.33333333 * 3 == 10.0);
    }
}
```

- double 값은 정확하다고 할 수 없기에 == 비교는 해서는 안된다.

```java
package temp;

public class Test064A {
    public int print() {
        return 200;
    }
    public int print2(int i){
        return 300*i;
    }
    public static int print3(){
        return 100;
    }
}
```

- 패키지가 지정된 클래스 컴파일 : javac -d [폴더] Test064A.java  
  `javac -d . Test064A.java`

- 패키지는 클래스의 묶음 . 파일 맨 위에 지정 .  
  이 파일안에 선언한 모든 클래스는 지정된 패키지에 속한다.

- 대상폴더 아래에 패키지 이름을 폴더가 생기고, 그 아래에 class 파일이 들어간다.

- 패키지를 지정 안하면 Unnamed 패키지에 소속된다. 이건 사용시 제약이 많다.

```java
import temp.Test064A;

public class Test064{
    public static void main(String[] args){
        Test064A t = new Test064A();
        System.out.println(t.print());
        System.out.println(t.print2(2));
        System.out.println(t.print3());
    }
}
```

- 다른 패키지의 클래스는 반드시 명시해야 사용할 수 있다.(import)

- 만일 그래도 클래스를 못찾으면, 클래스의 위치를 지정해줘야 한다.

- `javac -classpath [경로(.;/Users/hyunminko/...)]`
  - 경로 구분자
    - `:` 리눅스 계열
    - `;` 윈도우 계열

```java
package temp;

public class Test066A {
    static {

    }
    public int print() { return 200; }
    public int print2(int i) { return 300*i; }
    public static int print3() { return 100; }
}

public class Test066{
    public static void main(String[] args) throws Exception{
        Class.forName("temp.Test066");
        Object obj = cls.newInstance();
        System.out.println(obj.getClass().getName());
    }
}
```

- 클래스를 찾아내서 해당 클래스를 강제로 메모리에 로딩시킨다.

- 로딩시키는 클래스의 static initializer가 동작된다.

- Class: 로딩된 클래스의 관리자 역할을 한다.

- cls.newInstance(): cls가 관리하는 temp.Test066A의 인스턴스를 생성한다.

- obj.getClass().getName(): obj가 가리키는 인스턴스를 생성시킨 클래스명

- import 없이도 다른 패키지의 클래스의 인스턴스를 생성할 수 있다.

```java
import java.lang.reflect.Method;

public class Test066_2{
    public static void main(String[] args) throws Exception{
        Class cls = Class.forName("temp.Test066A");
        Object obj = cls.newInstance();

        Method[] mtds = cls.getMethods();
        for(int i = 0 ; i < mtds.length; i++){
            System.out.println(mtds[i]);
        }
    }
}
```

- 클래스 안에서 선언된 함수에 대한 포인터들을 추출한다.

```java
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
        System.out.println((Integer) r.intValue());
        System.out.println(mtd2.invoke());
    }
}
```

- Method는 C의 함수포인터의 역할을 한다.
- cls.getMethods(): 모든 멤버함수의 포인터를 넘긴다.
- cls.getMethod(...): 단 하나의 멤버함수의 포인터를 넘긴다. ...에는 함수이름, 매개변수의 형태를 명시한다.

```java
import java.util.*;

public class Test068 {
    public static void main(String[]args) throws Exception {
        List<String> l = new ArrayList<>();
        l.add("apple");
        l.add("banana");
        l.add("orange");
        l.add("kiwi");

        System.out.println(l);

        List<String> m = new LinkedList<>();
        m.add("apple");
        m.add("banana");
        m.add("orange");
        m.add("kiwi");

        System.out.println(m);

        Set<String> set = new TreeSet<>();

        set.add("apple");
        set.add("banana");
        set.add("orange");
        set.add("kiwi");

        System.out.println(set);

        Set<String> set2 = new TreeSet<>();

        set2.add("apple");
        set2.add("banana");
        set2.add("orange");
        set2.add("kiwi");

        System.out.println(set2);

        Iterator<String> it = set.iterator();
        while(it.hasNext()){
            String str = it.next();
            if(str.indexOf("an") != -1){
                it.remove();
            }
        }
        System.out.println(set);
    }
}
```

- ArrayList 속도가 빠르다. 내부적으로 배열을 이용한다.  
  배열을 써서 단순히 쌓는 속도는 빠른데, 중간 삭제시에는 비효율적이다.
- LinkedList는 Node를 이용하기에 단순하게 쌓는 속도는 느리다.  
  그렇지만 중간에 추가 삭제가 빈번한 경우에는 용이하다.
- 둘 다 List를 상속한다. List를 상속받은 클래스는 특징이 존재한다.

  - 중복이 허용된다.
  - 검색시에 들어간 순서대로 나온다.
  - 순서대로 보관한다.

- Set 인터페이스를 상속 받은 것: TreeSet, HashSet
- 공통 특징
  - 순서 개념이 없다. 중복보관은 허용하지 않는다.
  - 검색 속도가 List보다 빠름.
- TreeSet
  - 트리를 이용하여 보관
- HashSet
  - 해쉬 알고리즘으로 기억장소를 결정

```java
import java.util.*;

public class Test070 {
    public static void main(String[] args){
        Map<String,String> map = new Hashtable<>();
        map.put("apple","사과");
        map.put("banana","바나나");
        map.put("orange","오렌지");
        map.put("kiwi","키위");
        System.out.println(map.toString());

        String value = map.get("apple");
        System.out.println(value);

        Set<String> keys = map.keySet();
        Iterator<String> it = keys.iterator();
        while(it.hasNext()){
            String k = it.next();
            String v = map.get(k);
            System.out.println(k + ": " + v);
        }
    }
}
```

- Map은 순서 개념 없이 key=value 형태의 마치 사전과 같은 형태로 저장합니다.
- put 함수로 저장, get 함수로 key에 해당하는 value를 뽑아낸다.
- List, Set, Map 형태로 뭔가를 저장하는 형태를 흔히 Collection이라고 한다.

  - List: 중복 O, 순서 O
  - Set: 중복 X, 순서 X
  - Map: 중복 X, 순서 X

- Iterator는 표준 검색 방법이다. keySet()은 key값만의 Set을 생성한다.

```java
import java.util.*;

public class Test071 {
    public static void main(String[] args){
        for(int i = 0 ; i < 20; i++){
            double d = Math.random();
            System.out.println(d);
        }
    }
}
```

- Math.random() : 0에서 1사이의 소수값을 랜덤하게 출력한다.

```java
class TempException extends RuntimeException {}

public class Test074 {
    public static void main(String[] args){
        int i = 0;
        if(i==0){
            throw new TempException();
        }
        System.out.println(0);
    }
}
```

- java에서 각종 에러는 class로 구현된다.  
  extends Exception, extends RuntimeException
- 코드 수행시 에러가 발생되면 해당 예외 클래스의 인스턴스를 throw한다.
- 발생된 예외를 적절하게 처리하지 못하면 프로그램은 종료한다.
- "예외는 함수 수행시에 발생되고, 함수에 그 사실을 명시한다."

```java
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
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("견인차를 불러라");
        }

    }
}
```

- Exception이 깐깐하다:
  - 함수에서 에러가 발생할 수 있다면 그 사실을 함수 선언부에 명시해야 컴파일이 된다.
- throws FuelException으로 선언된 함수를 호출할때는 반드시 에러가 발생할 수 있는 영역을 try {...} 로 감싸준다.
- try에는 반드시 1개 이상의 catch가 있어야 한다.  
  에러가 발생되며 인스턴스를 throw 한다.
- catch(FuelException e)에 있는 e변수가 발생된 에러 인스턴스를 가리킬 수 있으면 catch에 딸린 {...} 영역이 동작한다. 에러를 수습할 수 있는 코드가 들어가는 것이 바람직하다.
- catch(Exception e)을 써도 되는가? OK FuelException이 Exception을 상속받았기 때문이다.
