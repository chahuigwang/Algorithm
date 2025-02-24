n = int(input())

ropes = []
for _ in range(n):
    ropes.append(int(input()))

ropes.sort()    
    
max = 0
for _ in range(n):
    cur = len(ropes)*ropes.pop(0)
    if cur > max:
        max = cur
    
print(max)
