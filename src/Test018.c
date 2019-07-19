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
