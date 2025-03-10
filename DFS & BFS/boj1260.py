from collections import deque

def dfs(graph, v, visited):
    visited[v] = True
    print(v, end=' ')
    
    for neighbor in graph[v]:
        if not visited[neighbor]:
            dfs(graph, neighbor, visited)
            
def bfs(graph, start, visited):
    queue = deque([start])
    visited[start] = True
    
    while queue:
        v = queue.popleft()
        print(v, end=' ')
        
        for neighbor in graph[v]:
            if not visited[neighbor]:
                queue.append(neighbor)
                visited[neighbor] = True

n, m, v = map(int, input().split())

graph = [[] for _ in range(n+1)]
for _ in range(m):
    a, b = map(int, input().split())
    graph[a].append(b)
    graph[b].append(a)

for g in graph:
    g.sort()

visited = [False] * (n+1)

dfs(graph, v, visited)
print()
visited = [False] * (n+1)
bfs(graph, v, visited)
