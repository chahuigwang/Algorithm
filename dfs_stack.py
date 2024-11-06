def dfs_stack(graph, v, visited):
    stack = [v]
    visited.append(v)

    while stack:
        found_unvisited = False

        for i in graph[stack[-1]]:
            if i not in visited:
                stack.append(i)
                visited.append(i)
                found_unvisited = True
                break
        if not found_unvisited:
            stack.pop()


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

dfs_stack(graph, 1, visited)
print(visited)