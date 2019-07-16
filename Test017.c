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
