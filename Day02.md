## Day 2

```c
#include <stdio.h>
int main() {
    int i;
    int j;
    i = 100;
    j = i;
    printf("%d %d\n",i,j);

    return 0;
}
```

```c
#include <stdio.h>

int main() {
    int* t;
    int* l;
    int i;
    i = 100;
    t = &i;

    printf("%d\n",*t);
    printf("%d\n",i);

    printf("%x\n",t);
    printf("%x\n",&t);
    printf("%x\n",&i);
    return 0;
}

```

- 포인터는 저장하는 공간을 가르키기 위한 용도이다.

```c
#include <stdio.h>

int main() {
    int* t;
    int* l;
    int i;

    i = 100;
    t = &i;
    l = t;

    printf("%d\n",*t);
    printf("%d\n",*l);
    printf("%d\n",i);
    return 0;
}
```

- 포인터 변수간의 대입은 왼쪽 포인터 변수가 오른쪽 포인터 변수가 가리키고 있는 대상을 가리킨다.

```c
#include <stdio.h>

int main() {
    int i;
    double j;
    int* h;
    void* t;

    i = 100;
    j = 3.14;

    t = &i;
    h = (int*)t;
    printf("%d\n",*h);
    printf("%d\n",*((int*)t));

    printf("%lu %lu\n",sizeof(i),sizeof(t));

    t = &j;
    printf("%lu %lu\n",sizeof(j),sizeof(t));
    return 0;
}
```

- void\* 변수는 어떤 기억공간이든 다 가리킨다.
- 가리키고 있긴 하지만 (void\*의 크기는 8바이트) 해당 하는 값이 어떤 자료형의 어떤 크기인지 모르므로 형변환이 필요하다

```c
#include <stdio.h>

int add(int i,int j) {
    return i+j;
}
int main() {
    int r;
    r = add(10,20);
    printf("%d\n",r);
    return 0;
}

```

- 함수 선언  
  [유일한 리턴타입][이름] (0..\* 변수선언 - 매개변수){ ... }
- 로컬 변수는 함수 안에서 선언된 변수  
  함수가 호출되는 시점에 메모리 할당, 호출 끝나면 소거

- 대입연산(=)의 오른편이 먼저 동작. 선언된 함수를 호출한다.

- 함수에는 매개변수가 선언된다. 호출시에는 값을 명시한다. 매개변수는 호출시 명시된 값으로 대입 당한다.
  매개변수도 로컬변수여서 함수가 호출 될 때 할당되고 종료할 때 소거된다.

```c
#include <stdio.h>
int add(int i , int j) {
    return i+j;
}
int main() {
    int r;
    int (*fp)(int,int);
    fp = add;

    r = fp(10,20);
    printf("%d\n",r);
    return 0;
}
```

- fp는 함수 포인터이기 때문에 함수를 가리킬 수 있다. 리턴 타입이 int, 매개변수가 int,int 형태로 선언된 함수를

```c
#include <stdio.h>

float danri(float money, int year, float interest) {
    return money + money * year * interest;
}
float bokri(float money, int year, float interest) {
    for(int i = 0; i < year; i++){
        money = money + (money * interest);
    }
    return money;
}

int main() {
    float r;
    float (*fp) (float,int,float);

    fp = danri;
    r = fp(100.0,100,0.066);
    printf("%f\n",r);

    fp = bokri;
    r = fp(100.0,100,0.066);
    printf("%f\n",r);

    return 0;
}
```

```java
class Apple {
    int i;
    int add(int i , int j){
        return 100;
    }
}
public class Test023 {
    public static void main(String[] args){
        Apple a =  new Apple();
        t.i = 100;
        Systemon.out.println(t.add(10,20));
    }
}
```

- 클래스를 선언할 수 있다. class[이름] { ... } : c는 클래스 없다.
- 변수 선언, 함수 선언 가능
- 변수의 대입, 연산, 함수 호출 등등 ... 은 불가능하다.
- 클래스를 선언하고 그 이름으로 변수 선언이 가능한데 이런 변수를 참조형 변수라고 한다.(실은 다 포인터)
- new를 이용해서 인스턴스를 만들 수 있다.
- 클래스 이름으로 선언된 변수는 참조형 변수이고 이것은 해당 클래스를 이용해 만들어진 인스턴스를 가리킬 수 있다.

```c
#include <stdio.h>
#include <stdlib.h>

struct apple {
    int i;
    int add;
};

int main() {
    struct apple* t;
    t = (struct apple*)malloc(sizeof(struct apple));
    free(t);
    return 0;
}

```

- 할당 받은 기억공간은 변수가 아니기 때문에 함수가 종료되어도 사라지지 않는다. 그렇기 때문에 free로 메모리를 해제시켜줘야 한다.

- apple 구조체의 기억공간을 가리킬 수 있는 포인터 변수 t 선언

```c
#include <stdio.h>
#include <stdlib.h>

struct apple {
    int i;
    int add;
};

int main() {

typedef struct apple {
    int i;
    int (*add)(int,int);
}Apple;
int apple_add(int i, int j) {
    return i + j;
}
Apple* new_Apple() {
    Apple* n;
    n = (Apple*) malloc(sizeof(Apple));
    n->add = apple_add;
    return n;
}

int main() {
    Apple* t;
    t = new_Apple();
    t->i = 100;
    printf("%d\n",(t->i + t->add(10,20)));
    free(t);
    return 0;
}
```

- 포인터 t가 가리키는 대상 안에 있는 i 변수에 대입한다.

```java
class Apple {
    int i;
    int add(int i , int j){
        return i + j;
    }
    Apple(int i){
        this.i = i;
    }
}

public class Test025 {
    public static void main(String[] args){
        Apple t = new Apple(6);
        System.out.println(t.i + t.add(10,20));
    }
}
```

- 리턴타입이 없고 클래스명과 함수명이 동일하면 생성자이다.

- 인스턴스 생성 시점에 호출되고 주 용도가 인스턴스 내부의 변수의 초기값을 줄 때 사용된다.

- 생성자는 포인터로 호출 불가능
