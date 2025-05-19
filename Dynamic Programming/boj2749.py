n = int(input())

MOD = 1_000_000
PISANO = 1_500_000

n = n % PISANO  # 주기만큼 줄이기

d = [0]*(n+1)
d[1] = 1
if n >= 2:
    d[2] = 1

for i in range(3, n+1):
    d[i] = (d[i-1] + d[i-2]) % MOD
    
print(d[n] % 1000000)