n = int(input())
soldiers = list(map(int, input().split()))

soldiers.reverse()

d = [1]*n

for i in range(1, n):
    for j in range(i):
        if soldiers[j] < soldiers[i]:
            d[i] = max(d[i], d[j]+1)
            
print(n-max(d))