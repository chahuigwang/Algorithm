dp = [0]*11

def solve(n):
    if n == 0:
        return 1
    if n < 0:
        return 0
    
    if dp[n] != 0:
        return dp[n]
    dp[n] = solve(n-1) + solve(n-2) + solve(n-3)
    
    return dp[n]

for _ in range(int(input())):
    n = int(input())
    print(solve(n))