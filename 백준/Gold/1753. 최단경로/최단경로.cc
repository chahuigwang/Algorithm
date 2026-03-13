#include <iostream>
#include <vector>
#include <queue>

using namespace std;

#define INF 100000000

int V, E, K; // V : 정점의 개수, E : 간선의 개수, K : 시작 정점의 번호
vector<pair<int, int> > edge[20001];
int dist[20001];

void dijkstra(){
    priority_queue<pair<int,int>,vector<pair<int,int> >, greater<pair<int,int> > > pq;

    dist[K] = 0;
    pq.push(make_pair(0, K));

    while(!pq.empty()){
        int cur_dist = pq.top().first;
        int cur = pq.top().second;
        pq.pop();

        for(int i = 0; i < edge[cur].size(); i++){
            int next = edge[cur][i].first;
            int next_dist = cur_dist + edge[cur][i].second;

            if(next_dist < dist[next]){
                dist[next] = next_dist;
                pq.push({next_dist, next});
            }
        }
    }
}

int main(){
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> V >> E;
    cin >> K;

    fill_n(dist+1, V, INF);

    int u, v, w;
    for(int i = 0; i < E; i++){
        cin >> u >> v >> w;
        edge[u].push_back({v, w});
    }

    dijkstra();

    for(int i = 1; i < V+1; i++){
        if(dist[i] == INF)
            cout << "INF";
        else
            cout << dist[i];
        cout << endl;
    }
    
    return 0;
}