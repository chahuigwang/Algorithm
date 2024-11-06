n, m = input().split()
n = int(n)

count = 0
for i in range(n+1):
    i = str(i)
    if len(i) == 1:
        i = '0' + i
    for j in range(60):
        j = str(j)
        if len(j) == 1:
            j = '0' + j
        for k in range(60):
            k = str(k)
            if len(k) == 1:
                k = '0' + k
            if m in i + j + k:
                count += 1

print(count)
