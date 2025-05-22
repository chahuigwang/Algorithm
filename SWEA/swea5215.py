T = int(input())
for test_case in range(1, T+1):
    n, l = map(int, input().split()) # n: 재료의 수, l: 제한 칼로리
    ingredients = [tuple(map(int, input().split())) for _ in range(n)]
    
    dp = [0]*(l+1)

    for taste, cal in ingredients:
        for j in range(l, cal-1, -1):
            dp[j] = max(dp[j], dp[j - cal] + taste)
    
    print(f'#{test_case} {max(dp)}')