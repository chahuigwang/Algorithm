t = int(input())

for i in range(1, t+1):
    n = int(input())
    prices = list(map(int, input().split()))

    revenue = 0
    max_price = 0

    for price in reversed(prices):
        if price > max_price:
            max_price = price
        else:
            revenue += max_price - price

    print(f'#{i} {revenue}')
