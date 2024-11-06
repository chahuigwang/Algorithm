def min_count(x, y, n):
    sum = 0
    count = 0
    while x <= n:
        if x > y:
            x, y = y, x

        x += y
        count += 1

    return count


t = int(input())

t_list = []
for i in range(t):
    a, b, n = map(int, input().split())
    t_list.append([a, b, n])

for i in range(t):
    print(min_count(t_list[i][0], t_list[i][1], t_list[i][2]))
