T = int(input())
for test_case in range(1, T+1):
    s = input()
    e = input()
    
    while len(e) > len(s):
        if e[-1] == 'X':
            e = e[:-1]
        elif e[-1] == 'Y':
            e = e[:-1]
            e = e[::-1]
    
    print(f'#{test_case} ', end='')
    print('Yes' if s == e else 'No')