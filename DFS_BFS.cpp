#include <iostream>
#include <vector>
#include <stack>
#include <queue>

using namespace std;

int n, m, v; // n : 정점의 개수, m : 간선의 개수, v : 시작 정점
vector<int> graph[1001];
bool visited[1001] = {0, };

template <typename T>
bool in(T t, int num){
    int cnt = 0;
    T temp = t;
    for(int i = 0; i < t.size(); i++){
        if(t.top() == num)
            cnt++;
        t.pop();
    }
    t = temp;
    if(cnt)
        return true;
    else
        return false;
}

void dfs_recursion(int start){
    visited[start] = true;
    cout << start << ' ';

    for(int i = 0; i < graph[start].size(); i++){
        int next = graph[start][i];
        if(!visited[next]) // visited[next] == false
            dfs_recursion(next);
    }
}

void dfs_stack(){
    stack<int> s;
    s.push(v);
    visited[v] = true;

    while(!s.empty()){
        int cur = s.top();
        s.pop();
        cout << cur << ' ';

        for(int i = 0; i < graph[cur].size(); i++){
            int next = graph[cur][i];
            if(!visited[next]){
                visited[next] = true;
                s.push(next);
                break;
            }
        }
    }
}

void dfs_stack1(){
    stack<int> s;
    s.push(v);

    while(!s.empty()){
        int cur = s.top();
        s.pop();

        if(!visited[cur]){
            cout << cur << ' ';
            visited[cur] = true;
        }
        for(int i = graph[cur].size() - 1; i >= 0; i--){
            if(!in(s, graph[cur][i]) && !visited[graph[cur][i]])
                s.push(graph[cur][i]);
        }
    }
}

void bfs(){
    queue<int> q;
    q.push(v);
    visited[v] = true;

    while(!q.empty()){
        int cur = q.front();
        q.pop();
        cout << cur << ' ';

        for(int i = 0; i < graph[cur].size(); i++){
            if(!visited[graph[cur][i]]){
                visited[graph[cur][i]] = true;
                q.push(graph[cur][i]);
            }
        }
    }
}

int main(){
    cin >> n >> m >> v;

    int a, b;
    for(int i = 0; i < m; i++){
        cin >> a >> b;
        graph[a].push_back(b);
        graph[b].push_back(a);
    }
    for(int i=1; i <= n; i++){
		sort(graph[i].begin(), graph[i].end());
	}
    dfs_recursion(v);
    cout << endl;
    memset(visited, 0, sizeof(visited));
    dfs_stack();
    cout << endl;
    memset(visited, 0, sizeof(visited));
    dfs_stack1();
    cout << endl;
    memset(visited, 0, sizeof(visited));
    bfs();
    
    return 0;
}