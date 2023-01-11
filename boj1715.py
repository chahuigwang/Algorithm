from queue import PriorityQueue

N = int(input())

pq = PriorityQueue()
for i in range(N):
    pq.put(int(input()))

num1 = num2 = sum = 0
while(pq.qsize() >= 2):
    num1 = pq.get()
    num2 = pq.get()
    sum += (num1+num2)
    if pq.empty():
        break
    pq.put(num1+num2)

print(sum)