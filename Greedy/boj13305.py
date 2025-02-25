n = int(input())
distances = list(map(int, input().split()))
prices = list(map(int, input().split()))

cost = 0
i = 0  # 현재 도시 인덱스

while i < n - 1:  # 마지막 도시는 주유할 필요 없음
    current_price = prices[i]
    total_dist = distances[i]
    
    j = i + 1
    while j < n - 1 and prices[j] >= current_price:
        total_dist += distances[j]
        j += 1
    
    cost += current_price * total_dist
    i = j  # 더 싼 가격을 만난 위치로 이동

print(cost)
