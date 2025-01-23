n = int(input())

p = list(map(int, input().split()))
p.sort()

time = [0]

for i in range(n):
    time.append(time[-1] + p[i]) # 본인 전까지 + 본인 시간
    
print(sum(time))
