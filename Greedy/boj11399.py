n = int(input())

p = list(map(int, input().split()))
p.sort()
    
for i in range(1, n):
    p[i] = p[i-1] + p[i] # 본인 전까지 + 본인 시간

print(sum(p))
