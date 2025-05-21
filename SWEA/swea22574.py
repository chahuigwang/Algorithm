T = int(input())
for test_case in range(1, T+1):
    n, p = map(int, input().split())
    
    floor = 0
    stop_p = False
    for i in range(1, n+1):
        floor += i
        if floor == p:
            stop_p = True
            break
        
    total = n*(n+1) // 2
    print(total if not stop_p else total-1)