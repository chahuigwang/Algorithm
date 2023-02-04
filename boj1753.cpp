#include <iostream>

using namespace std;

#define INF 100000000

int V, E, K;
int** edge;
int* dist;
int* visited;

int getClosestIndex(){
    int min_dist = INF;
    int idx;
    for(int i = 1; i < V+1; i++){
        if(visited[i] == 1)
            continue;
        if(dist[i] < min_dist){
            min_dist = dist[i];
            idx = i;
        }
    }
    return idx;
}

void dijkstra(){
    for(int i = 1; i < V+1; i++)
        dist[i] = edge[K][i];
    
    visited[K] = 1;
    for(int i = 1; i < V; i++){
        int closest = getClosestIndex();
        visited[closest] = 1;
        for(int j = 1; j < V+1; j++){
            if(visited[j] == 1)
                continue;
            if(dist[closest] + edge[closest][j] < dist[j])
                dist[j] = dist[closest] + edge[closest][j];
        }
    }
}

int main(){
    cin >> V >> E;
    cin >> K;

    edge = (int**)malloc(sizeof(int*) * (V+1));
    for(int i = 0; i < V+1; i++){
        edge[i] = (int*)malloc(sizeof(int) * (E+1));
    }

    for(int i = 0; i < V+1; i++){
        for(int j = 0; j < E+1; j++)
            if(i == j)
                edge[i][j] = 0;
            else
                edge[i][j] = INF;
    }

    int u, v, w;
    for(int i = 0; i < E; i++){
        cin >> u >> v >> w;
        if(w < edge[u][v]){
            edge[u][v] = w;
        }
    }

    dist = (int*)malloc(sizeof(int) * (V+1));
    visited = (int*)malloc(sizeof(int) * (V+1));
    for(int i = 0; i < V+1; i++){
        dist[i] = INF;
        visited[i] = 0;
    }

    dijkstra();

    for(int i = 1; i < V+1; i++){
        if(dist[i] == INF)
            cout << "INF";
        else
            cout << dist[i] << endl;
    }

    for(int i = 0; i < V+1; i++){
        free(edge[i]);
    }
    free(edge);
    free(dist);
    free(visited);

    return 0;
}