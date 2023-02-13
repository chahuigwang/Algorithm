#include <iostream>

using namespace std;

int bag[5001];

int min_bag(int n){
    if(n == 3 || n == 5)
        return 1;
    if(n < 0)
        return 10000;
    if(bag[n] != 0)
        return bag[n];
    return bag[n] = min(min_bag(n-3), min_bag(n-5)) + 1;
}

int main(){
    int N;
    cin >> N;

    if(min_bag(N) >= 10000)
        cout << -1;
    else
        cout << min_bag(N);
}