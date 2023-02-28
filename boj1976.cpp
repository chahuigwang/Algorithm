#include <iostream>

using namespace std;

int n, m; // n : 도시의 수, m : 여행 계획에 속한 도시의 수
int parent[201];

int find(int x){
    if(parent[x] == x)
        return x;
    else
        return parent[x] = find(parent[x]);
}

void merge(int x, int y){
    x = find(x);
    y = find(y);
    if(x == y)
        return;
    if(x > y)
        parent[x] = y;
    else
        parent[y] = x;
}

int main(){
    cin >> n;
    cin >> m;

    for(int i = 0; i <= 200; i++)
        parent[i] = i;

    int conn; // 1이면 연결, 0이면 연결 x
    for(int i = 1; i <= n; i++){
        for(int j = 1; j <= n; j++){
            cin >> conn;
            if(conn)
                merge(i, j);
        }
    }

    int first, next;
    cin >> first;
    int firstParent = find(first);
    int nextParent;
    bool isUnion = true;
    for(int i = 0; i < m-1; i++){
        cin >> next;
        nextParent = find(next);
        if(nextParent != firstParent){
            isUnion = false;
            break;
        }
    }
    if(isUnion)
        cout << "YES";
    else
        cout << "NO";

    return 0;
}