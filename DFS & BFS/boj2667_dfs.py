dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

def dfs(x, y):
    visited[x][y] = True
    count = 1
    
    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]
        
        if 0 <= nx < n and 0 <= ny < n and graph[nx][ny] and not visited[nx][ny]:
            count += dfs(nx, ny)
    
    return count

n = int(input())

graph = [list(map(int, input())) for _ in range(n)]
visited = [[False] * n for _ in range(n)]
    
count_list = []

for i in range(n):
    for j in range(n):
        if graph[i][j] and not visited[i][j]:
            count_list.append(dfs(i, j))

count_list.sort()
print(len(count_list))

for count in count_list:
    print(count)