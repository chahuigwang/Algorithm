T = int(input())
for test_case in range(1, T+1):
    n = int(input())
    graph = [[0]*n for _ in range(n)]
    
    dx = [0, 1, 0, -1]
    dy = [1, 0, -1, 0]
    move = 0 
    
    x, y = 0, 0
    for i in range(1, n*n + 1):
        graph[x][y] = i
        
        nx = x + dx[move]
        ny = y + dy[move]
        if nx < 0 or nx > n-1 or ny < 0 or ny > n-1 or graph[nx][ny] != 0:
            move = (move+1) % 4
            nx = x + dx[move]
            ny = y + dy[move]
            
        x, y = nx, ny
        
    print('#'+str(test_case))
    for i in range(n):
        print(*graph[i])