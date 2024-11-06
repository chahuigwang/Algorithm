location = input()
x = int(location[1])
y = int(ord(location[0])-ord('a')) + 1


dx = [-1, -2, -2, -1, 1, 2, 2, 1]
dy = [-2, -1, 1, 2, 2, 1, -1, -2]

count = 8
for i in range(8):
    nx = x + dx[i]
    ny = y + dy[i]
    if nx < 1 or ny < 1 or nx > 8 or ny > 8:
        count -= 1

print(count)
