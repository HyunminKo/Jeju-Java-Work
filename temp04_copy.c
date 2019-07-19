#include <stdio.h>
#include <stdlib.h>

typedef struct Apple {
    int data;
    void (*print)(void*);
}Apple;

typedef struct pine_apple {
    int data;
    void (*print)(void*);

    float pi;
    int (*increase)(void*,int i);
}PineApple;

void apple_constructor(void* self, int data) {
    Apple* this = (Apple*)self;
    this->data = data;
}

void pine_apple_constructor(void* self, int data) {
    PineApple* this = (PineApple*)self;
    this->pi = 3.14;
}

void apple_print(void* self){
    Apple* this = (Apple*)self;
    printf("Apple %d\n", this->data);
}
void pine_apple_print(void* self){
    PineApple* this = (PineApple*)self;
    printf("PineApple %d\n", this->data);
}
int pine_apple_increase(void* self, int i ) {
    PineApple* this = (PineApple*) self;
    printf("%f\n", this->pi);
    return (this->data + i);
}

Apple* new_Apple(int data) {
    Apple* new;
    new = (Apple*)malloc(sizeof(Apple));
    new->print = apple_print;
    apple_constructor(new,data);
    return new;
}
PineApple* new_PineApple(int data) {
    PineApple* new;
    new = (PineApple*) malloc(sizeof(PineApple));
    new->increase = pine_apple_increase;
    new->print = pine_apple_print;

    apple_constructor(new,data);
    pine_apple_constructor(new,data);
    return new;
}
int main() {
    Apple* t;
    t = (Apple*)new_PineApple(100);
    t->print(t);
    free(t);
    return 0;
}
