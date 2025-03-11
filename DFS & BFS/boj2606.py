def dfs(graph, v, visited):
    visited.append(v)
    
    for neighbor in graph[v]:
        if neighbor not in visited:
            dfs(graph, neighbor, visited)
            
n = int(input())
m = int(input())

graph = [[] for _ in range(n+1)]
for _ in range(m):
    a, b = map(int, input().split())
    graph[a].append(b)
    graph[b].append(a)
    
visited = []

dfs(graph, 1, visited)
print(len(visited)-1)