#include<iostream>
#include<stack>
#include<queue>

using namespace std;

void dfs(int n, int m, int v, int edge[][1001], int visit[]) {
    stack<int> open;
    int closed;
    open.push(v);

    while (!open.empty()) {
        closed = open.top();
        open.pop();
        
        if(!visit[closed])
            cout << closed << ' ';
        visit[closed] = 1;
        for (int i = n; i >= 1; i--) {
            if (edge[closed][i] && !visit[i]) {
                open.push(i);
            }
        }
    }
}

void bfs(int n, int m, int v, int edge[][1001], int visit[]) {
    queue<int> open;
    int closed;
    open.push(v);

    while (!open.empty()) {
        closed = open.front();
        open.pop();

        if(!visit[closed])
            cout << closed << ' ';
        visit[closed] = 1;
        for (int i = 1; i <= n; i++) {
            if (edge[closed][i] && !visit[i]) {
                open.push(i);
            }
        }
    } 
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int n, m, v;
    cin >> n >> m >> v;

    int edge[1001][1001] = {0, };
    int visit[1001] = {0, };
    
    for (int i = 0; i < m; i++) {
        int a, b;
        cin >> a >> b;
        edge[a][b] = 1;
        edge[b][a] = 1;
    }
    dfs(n, m, v, edge, visit);
    fill_n(visit, 1001, 0);
    cout << endl;
    bfs(n, m, v, edge, visit);

    return 0;
}