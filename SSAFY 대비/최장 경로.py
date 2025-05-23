def dfs(graph, node, visited, cur_depth):
    visited[node] = True
    depth = cur_depth

    for neighbor in graph[node]:
        if not visited[neighbor]:
            depth = max(depth, dfs(graph, neighbor, visited, cur_depth+1))

    visited[node] = False
    return depth

T = int(input())
for test_case in range(1, T+1):
    n, m = map(int, input().split())
    graph = [[] for _ in range(n+1)]
    visited = [False] * (n+1)

    for _ in range(m):
        x, y = map(int, input().split())
        graph[x].append(y)
        graph[y].append(x)

    longest_path = 1
    for i in range(1, n+1):
        longest_path = max(longest_path, dfs(graph, i, visited, 1))

    print(f'#{test_case} {longest_path}')