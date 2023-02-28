#include<iostream>
#include<stack>
#include<queue>

using namespace std;

int n, m, v; // N : 정점의 개수, M : 간선의 개수, V : 탐색 시작 정점 번호
int edge[1001][1001] = {0, };
int visited[1001] = {0, };

void dfs() {
    stack<int> open;
    int closed;
    open.push(v);

    while (!open.empty()) {
        closed = open.top();
        open.pop();
        
        if(!visited[closed])
            cout << closed << ' ';
        visited[closed] = 1;
        for (int i = n; i >= 1; i--) {
            if (edge[closed][i] && !visited[i]) {
                open.push(i);
            }
        }
    }
}

void bfs() {
    queue<int> open;
    int closed;
    open.push(v);

    while (!open.empty()) {
        closed = open.front();
        open.pop();

        if(!visited[closed])
            cout << closed << ' ';
        visited[closed] = 1;
        for (int i = 1; i <= n; i++) {
            if (edge[closed][i] && !visited[i]) {
                open.push(i);
            }
        }
    } 
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> n >> m >> v;
    
    for (int i = 0; i < m; i++) {
        int a, b;
        cin >> a >> b;
        edge[a][b] = 1;
        edge[b][a] = 1;
    }
    dfs();
    fill_n(visited, 1001, 0); // visited 0으로 초기화
    cout << endl;
    bfs();

    return 0;
}