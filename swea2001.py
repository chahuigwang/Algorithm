T = int(input())

for test_case in range(1, T + 1):
    n, m = map(int, input().split())
    matrix = [[0]*n for _ in range(n)]
    for i in range(n):
        arr = list(map(int, input().split()))
        matrix[i] = arr

    max_dead = 0
    for x in range(n):
        if x+m > n:
            break
        for y in range(n):
            if y+m > n:
                break
            dead_matrix = [matrix[i][y:y+m] for i in range(x, x+m)]
            dead_num = sum(sum(row) for row in dead_matrix)
            if dead_num > max_dead:
                max_dead = dead_num

    print(f'#{test_case} {max_dead}')
