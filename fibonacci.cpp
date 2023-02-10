#include <iostream>

using namespace std;

long mem[100] = {0, };

long fibonacci(int n){
    if(n == 1 || n == 2)
        return 1;
    return fibonacci(n-1) + fibonacci(n-2);
}

// 동적 프로그래밍
long fibonacci_dp(int n){
    if(n == 1 || n == 2)
        return 1;
    if(mem[n] != 0)
        return mem[n];
    return mem[n] = fibonacci_dp(n-1) + fibonacci_dp(n-2);
}

int main(){
    // cout << fibonacci(50) << endl;
    cout << fibonacci_dp(50) << endl;

    return 0;
}