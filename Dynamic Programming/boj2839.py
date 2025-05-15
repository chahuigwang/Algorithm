n = int(input())

d = [5001]*(n+1)
d[0] = 0

sugar_bags = [3, 5]

for sugar_bag in sugar_bags:
    for j in range(sugar_bag, n+1):
        if d[j-sugar_bag] != 5001:
            d[j] = d[j-sugar_bag] + 1
            
print(d[n] if d[n] != 5001 else -1)