for test_case in range(1, 11):
    n = int(input())
    region = [[0]*255 for _ in range(n)]
    bldg = list(map(int, input().split()))
    for i in range(n):
        for j in range(bldg[i]):
            region[i][j] = 1

    cnt = 0
    for x in range(n):
        for y in range(bldg[x]):
            if region[x][y] == 1:
                if region[x-1][y] == 0 and region[x-2][y] == 0 and region[x+1][y] == 0 and region[x+2][y] == 0:
                    cnt += 1

    print(f'#{test_case} {cnt}')
