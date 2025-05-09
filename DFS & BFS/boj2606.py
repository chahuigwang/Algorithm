def dfs(graph, v, visited):
    visited[v] = True
    count = 1
    
    for neighbor in graph[v]:
        if not visited[neighbor]:
            count += dfs(graph, neighbor, visited)

    return count

n = int(input())
m = int(input())

graph = [[] for _ in range(n+1)]
visited = [False] * (n+1)

for _ in range(m):
    u, v = map(int, input().split())
    graph[u].append(v)
    graph[v].append(u)
    
print(dfs(graph, 1, visited)-1)