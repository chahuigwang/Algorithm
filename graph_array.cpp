#include <iostream>

using namespace std;

int main(){
    int n, m; // n : 정점의 개수, m : 간선의 개수
    cin >> n >> m;

    int graph[n+1][n+1];
    memset(graph, 0, sizeof(graph));

    int u, v;
    for(int i = 0; i < m; i++){
        cin >> u >> v;
        graph[u][v] = 1;
        graph[v][u] = 1;
    }

    // for(int i = 1; i < n+1; i++){
    //     for(int j = 1; j < n+1; j++)
    //         cout << graph[i][j] << ' ';
    //     cout << endl;
    // }
    return 0;
}