def dfs(node, length):
    global max_length
    visited[node] = True
    max_length = max(max_length, length)
    
    for neighbor in graph[node]:
        if not visited[neighbor]:
            dfs(neighbor, length+1)
            
    visited[node] = False

T = int(input())
for test_case in range(1, T+1):
    n, m = map(int, input().split()) # n: 정점의 개수, m: 간선의 개수
    graph = [[] for _ in range(n+1)]
    visited = [False]*(n+1)
    
    for _ in range(m):
        x, y = map(int, input().split())
        graph[x].append(y)
        graph[y].append(x)
        
    max_length = 0
    for i in range(1, n+1):
        dfs(i, 1)
            
    print(f'#{test_case} {max_length}')