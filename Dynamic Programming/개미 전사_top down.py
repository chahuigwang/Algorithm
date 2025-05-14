n = int(input())
food = list(map(int, input().split()))

d = [-1]*n

def solve(x):
    if x == 0:
        return food[0]
    if x == 1:
        return max(food[0], food[1])
    
    if d[x] != -1:
        return d[x]
    
    d[x] = max(solve(x-1), solve(x-2) + food[x])
    return d[x]

print(solve(n-1))