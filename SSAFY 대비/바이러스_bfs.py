from collections import deque

def bfs(graph, start, visited):
    queue = deque([start])
    visited[start] = True

    count = 0
    while queue:
        node = queue.popleft()
        for neighbor in graph[node]:
            if not visited[neighbor]:
                queue.append(neighbor)
                visited[neighbor] = True
                count += 1

    return count

n = int(input())
m = int(input())
graph = [[] for _ in range(n+1)]
visited = [False]*(n+1)

for _ in range(m):
    a, b = map(int, input().split())
    graph[a].append(b)
    graph[b].append(a)

print(bfs(graph, 1, visited))