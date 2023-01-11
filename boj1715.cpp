#include <iostream>
#include <queue>

using namespace std;

int main(){
    int n;
    cin >> n;

    priority_queue<int, vector<int>, greater<int> > pq;
    int size;
    for(int i=0; i<n; i++){
        cin >> size;
        pq.push(size);
    }

    int num1, num2;
    int sum = 0;
    while(pq.size() >= 2){
        num1 = pq.top();
        pq.pop();
        num2 = pq.top();
        pq.pop();
        sum += (num1 + num2);
        if(pq.empty())
            break;
        pq.push(num1+num2);
    }
    cout << sum;

    return 0;
}