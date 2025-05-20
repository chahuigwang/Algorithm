T = int(input())
for test_case in range(1, T+1):
    n = int(input())
    prices = list(map(int, input().split()))
    
    profit = 0
    max_price = 0
    
    for price in reversed(prices):
        if price > max_price:
            max_price = price
        profit += max_price-price
        
    print(f'#{test_case} {profit}')