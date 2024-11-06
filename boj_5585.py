n = int(input())

m = 1000 - n

coins = [500, 100, 50, 10, 5, 1]

count = 0
for coin in coins:
    count += m // coin
    m = m % coin

print(count)
