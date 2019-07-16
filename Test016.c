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
