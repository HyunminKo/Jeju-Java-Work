#include <stdio.h>
float bokri(float money, int year, float interest) {
    for(int i = 0 ; i < year; i++){
        money += (money * interest);
    }
    return money;
}
int main() {
    float r;
    r = bokri(100.0,100,0.066);
    printf("%f\n",r);
    return 0;
}
