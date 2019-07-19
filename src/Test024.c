#include <stdio.h>
#include <stdlib.h>
typedef struct apple {
    int i;
    int (*add)(int,int);
}Apple;
int apple_add(int i, int j) {
    return i + j;
}
Apple* new_Apple(int num) {
    Apple* n;
    n = (Apple*) malloc(sizeof(Apple));
    n->i = num;
    n->add = apple_add;
    return n;
}
int main() {
    Apple* t;
    t = new_Apple(100);
    printf("%d\n",(t->i + t->add(10,20)));
    free(t);
    return 0;
}
