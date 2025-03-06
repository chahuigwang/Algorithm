k = int(input())

nums = []

for _ in range(k):
    num = int(input())
    
    if num != 0:
        nums.append(num)
    else:
        nums.pop()
        
print(sum(nums))