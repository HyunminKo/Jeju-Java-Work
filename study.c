#include <stdio.h>
#include <stdlib.h>

typedef struct Apple {
    int i;
    int (*add)(int,int);
}Apple;

int add_Apple(int i , int j) {
    return i+j;
}
Apple* new_Apple(int num){
    Apple* n = (Apple*) malloc(sizeof(Apple));
    n->i = num;
    n->add = add_Apple;
    return n;    
}
int main() {
    Apple* t;
    t = new_Apple(100);
    t->i = 100;
    printf("%d\n",(t->i + t->add(10,20)));
    printf("%d\n",add_Apple);
    free(t);
    return 0;
}
