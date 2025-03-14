dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

def dfs(x, y):
    graph[x][y] = 1
    
    for i in range(4):
        nx, ny = x + dx[i], y + dy[i]
        if 0 <= nx < n and 0 <= ny < m and graph[nx][ny] == 0:
            dfs(nx, ny)
    

n, m = list(map(int, input().split()))

graph = [list(map(int, input())) for _ in range(n)]

count = 0
for i in range(n):
    for j in range(m):
        if graph[i][j] == 0:
            dfs(i, j)
            count += 1
            
print(count)