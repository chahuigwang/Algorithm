T = int(input())
for test_case in range(1, T+1):
    n = int(input())
    a = list(map(int, input().split()))
    b = list(map(int, input().split()))
    players = [''] * (n + 1)
    
    i, j = 0, 0
    selected = 0
    
    while selected < n:
        while i < n and players[a[i]] != '':
            i += 1
        if i < n:
            players[a[i]] = 'A'
            selected += 1
            i += 1
        
        while j < n and players[b[j]] != '':
            j += 1
        if j < n:
            players[b[j]] = 'B'
            selected += 1
            j += 1
    
    print(''.join(players[1:]))