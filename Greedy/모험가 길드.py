n = int(input())
fear = list(map(int, input().split()))
fear.sort()

group = 0
member = 0

for i in fear:
    member += 1
    if member >= i:
        group += 1
        member = 0

print(group)
