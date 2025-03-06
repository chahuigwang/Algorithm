def d(n):
    return n + sum(map(int, str(n)))

self_nums = set(range(1, 10001))
not_self_nums = set()

for i in range(1, 10001):
    not_self_nums.add(d(i))
    
self_nums = sorted(self_nums - not_self_nums)

for self_num in self_nums:
    print(self_num)