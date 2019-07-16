#include <stdio.h>

int main() {
    int* t;
    int* l;
    int i;
    i=100;
    t = &i;

    printf("%d\n",*t);
    printf("%d\n",i);

    printf("%x\n",t);
    printf("%x\n",&t);
    printf("%x\n",&i);
    return 0;
}
