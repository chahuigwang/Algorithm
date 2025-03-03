t = int(input())

a, b, c = 0, 0, 0

if t % 10 == 0:
    if t // 300 >= 1:
        a = t // 300
        t = t % 300
    if t // 60 >= 1:
        b = t // 60
        t = t % 60
    if t // 10 >= 1:
        c = t // 10
        
    print(f'{a} {b} {c}')
else:
    print(-1)