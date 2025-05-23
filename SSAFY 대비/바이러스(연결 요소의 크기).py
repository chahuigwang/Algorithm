def dfs(graph, node, visited):
    visited[node] = True
    count = 1

    for neighbor in graph[node]:
        if not visited[neighbor]:
            count += dfs(graph, neighbor, visited)

    return count

n = int(input())
m = int(input())
graph = [[] for _ in range(n+1)]
visited = [False]*(n+1)

for _ in range(m):
    a, b = map(int, input().split())
    graph[a].append(b)
    graph[b].append(a)

print(dfs(graph, 1, visited)-1)
