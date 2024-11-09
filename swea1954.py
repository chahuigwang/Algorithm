t = int(input())

for i in range(t):
    n = int(input())
    matrix = [[0]*n for _ in range(n)]
    x, y = 0, 0
    dx = [0, 1, 0, -1]
    dy = [1, 0, -1, 0]
    move = 0
    for j in range(1, n*n + 1):
        matrix[x][y] = j
        nx = x + dx[move]
        ny = y + dy[move]
        if nx < 0 or nx >= n or ny < 0 or ny >= n or matrix[nx][ny] != 0:
            if move == 3:
                move = 0
            else:
                move += 1
            nx = x + dx[move]
            ny = y + dy[move]
        x = nx
        y = ny

    print(f'#{i+1}')
    for a in range(n):
        for b in range(n):
            print(matrix[a][b], end=' ')
        print()
