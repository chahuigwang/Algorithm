#include <iostream>
#include <vector>

using namespace std;

int main(){
    int n, m;
    cin >> n >> m;

    vector<int> graph[n+1];

    int u, v;
    for(int i = 0; i < m; i++){
        graph[u].push_back(v);
        graph[v].push_back(u);
    }
}