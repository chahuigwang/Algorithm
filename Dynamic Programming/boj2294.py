n, k = map(int, input().split())

coins = [int(input()) for _ in range(n)]

d = [100001]*(k+1)
d[0] = 0

for coin in coins:
    for i in range(coin, k+1):
        if d[i-coin] != 100001:
            d[i] = min(d[i], d[i-coin] + 1)
            
print(d[k] if d[k] != 100001 else -1)