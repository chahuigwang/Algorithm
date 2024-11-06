def dfs_recursive(graph, v, visited):
    visited.append(v)

    for i in graph[v]:
        if i not in visited:
            dfs_recursive(graph, i, visited)


graph = [
    [],
    [2, 3, 8],
    [1, 7],
    [1, 4, 5],
    [3, 5],
    [3, 4],
    [7],
    [2, 6, 8],
    [1, 7]
]
visited = []

dfs_recursive(graph, 1, visited)
print(visited)