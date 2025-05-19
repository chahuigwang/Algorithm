T = int(input())
for test_case in range(1, T+1):
    a, b, n = map(int, input().split())
    
    first = min(a, b)
    second = max(a, b)
    
    cnt = 0
    while True:
        first += second
        cnt += 1
        if first > n:
            break
        
        second += first
        cnt += 1
        if second > n:
            break
        
    print(cnt)