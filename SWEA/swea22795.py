T = int(input())
for test_case in range(1, T+1):
    height_list = list(map(int, input().split()))
    height = max(height_list) + 1
    
    while (sum(height_list) + height) % 7 != 0:
        height += 1
        
    print(height)