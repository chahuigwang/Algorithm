from collections import deque

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

def bfs(x, y):
    if graph[x][y] == 0:
        queue = deque([(x, y)])
        graph[x][y] = 1
        
        while queue:
            x, y = queue.popleft()
            
            for i in range(4):
                nx = x + dx[i]
                ny = y + dy[i]
                
                if 0 <= nx < n and 0 <= ny < m and graph[nx][ny] == 0:
                    queue.append((nx, ny))
                    graph[nx][ny] = 1
        return True
    
    return False

n, m = map(int, input().split())

graph = []
for _ in range(n):
    graph.append(list(map(int, input())))
    
count = 0
for i in range(n):
    for j in range(m):
        if bfs(i, j):
            count += 1
            
print(count)