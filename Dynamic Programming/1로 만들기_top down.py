x = int(input())

d = [0]*(x+1)

def make_one(n):
    if n == 1:
        return 0
    if d[n] != 0:
        return d[n]
    
    d[n] = make_one(n-1) + 1
    if n % 2 == 0:
        d[n] = min(d[n], make_one(n//2) + 1)
    if n % 3 == 0:
        d[n] = min(d[n], make_one(n//3) + 1)
    if n % 5 == 0:
        d[n] = min(d[n], make_one(n//5) + 1)
        
    return d[n]

print(make_one(x))