def get_max_dist(str):
    robot = 0
    max_dist = 0
    for command in str:
        if command == 'L':
            robot -= 1
        else:
            robot += 1
        
        max_dist = max(max_dist, abs(robot))
    return max_dist

T = int(input())
for test_case in range(1, T+1):
    robot = 0
    s = input()
    sl = s.replace('?', 'L')
    sr = s.replace('?', 'R')
    
    max_dist = max(get_max_dist(sl), get_max_dist(sr))
    print(max_dist)