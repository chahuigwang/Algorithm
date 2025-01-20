import heapq

n = int(input())

cards = [int(input()) for _ in range(n)]
heapq.heapify(cards)

cnt = 0
while len(cards) > 1:
    num1 = heapq.heappop(cards)
    num2 = heapq.heappop(cards)
    heapq.heappush(cards, num1 + num2)
    cnt += num1 + num2

print(cnt)
