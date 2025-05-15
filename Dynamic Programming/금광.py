t = int(input())

for _ in range(t):
    n, m = map(int, input().split())
    golds = list(map(int, input().split()))

    gold_mine = [golds[i:i+m] for i in range(0, n*m, m)]
    dp = gold_mine
    
    for j in range(1, m):
        for i in range(n):
            if i == 0: left_up = 0
            else: left_up = dp[i-1][j-1]
            left = dp[i][j-1]
            if i == n-1: left_down = 0
            else: left_down = dp[i+1][j-1]
            
            dp[i][j] = gold_mine[i][j] + max(left_up, left, left_down)
            
    print(max(dp[i][m-1] for i in range(n)))