T = int(input())
for test_case in range(1, T + 1):
    s = input()
    k = int(input())
    ops = list(map(int, input().split()))

    for x in ops:
        n = len(s)
        if x > 0:
            x = x%n
            s = s[x:] + s[:x]
        elif x < 0:
            x = (-x)%n
            s = s[-x:] + s[:-x]

    print(s)