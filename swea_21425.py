t = int(input())

for _ in range(t):
    a, b, n = map(int, input().split())

    count = 0
    while a <= n:
        if a > b:
            a, b = b, a

        a += b
        count += 1

    print(count)
