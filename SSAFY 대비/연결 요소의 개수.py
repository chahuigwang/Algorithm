import sys
sys.setrecursionlimit(10**6)
def dfs(graph, node, visited):
    visited[node] = True

    for neighbor in graph[node]:
        if not visited[neighbor]:
            dfs(graph, neighbor, visited)

n, m = map(int, input().split())
graph = [[] for _ in range(n+1)]
visited = [False]*(n+1)

for _ in range(m):
    u, v = map(int, input().split())
    graph[u].append(v)
    graph[v].append(u)

count = 0
for i in range(1, n+1):
    if not visited[i]:
        dfs(graph, i, visited)
        count += 1

print(count)