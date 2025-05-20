T = int(input())
for test_case in range(1, T+1):
    nums = list(map(int, input().split()))
    
    max_num = max(nums)
    min_num = min(nums)
    
    while max_num in nums or min_num in nums:
        nums.remove(max_num)
        nums.remove(min_num)
        
    avg = sum(nums) / len(nums)
    print(f'#{test_case} {round(avg)}')