n = int(input())

change = 1000-n

cnt = 0

coins = [500, 100, 50, 10, 5, 1]

for coin in coins:
    cnt += change // coin
    change = change % coin

print(cnt)
