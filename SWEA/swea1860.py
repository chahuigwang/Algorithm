T = int(input())
for test_case in range(1, T+1):
    n, m, k = map(int, input().split())
    arrival_time = list(map(int, input().split()))
    arrival_time.sort()
    
    is_possible = True
    for i in range(n):
        time = arrival_time[i]
        bread = (time // m) *  k
        if bread < i+1:
            is_possible = False
            break
    
    print(f'#{test_case} {"Possible" if is_possible else "Impossible"}')