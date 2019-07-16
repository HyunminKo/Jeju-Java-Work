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
