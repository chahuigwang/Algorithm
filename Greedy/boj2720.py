t = int(input())

changes = []

for _ in range(t):
    changes.append(int(input()))
    
coins = []

for change in changes:
    q, d, n, p = 0, 0, 0, 0 # quarter, dime, nickel, penny
    if change // 25 >= 1:
        q = change // 25
        change = change % 25
    if change // 10 >= 1:
        d = change // 10
        change = change % 10
    if change // 5 >= 1:
        n = change // 5
        change = change % 5
    if change // 1 >= 1:
        p = change // 1
        change = change % 1
    
    coins.append((q, d, n, p))
    
for coin in coins:
    print(f'{coin[0]} {coin[1]} {coin[2]} {coin[3]}')
