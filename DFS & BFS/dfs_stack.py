def dfs_stack(graph, start):
    visited = [False] * (len(graph) + 1)
    stack = [start]  # 1. 시작 노드를 스택에 삽입하고 방문 처리
    visited[start] = True
    print(start, end=' ')

    while stack:
        node = stack[-1]  # 최상단 노드를 확인 (pop하지 않음)
        found = False  # 방문하지 않은 인접 노드가 있는지 체크

        for neighbor in graph[node]:  # 작은 번호부터 탐색
            if not visited[neighbor]:
                stack.append(neighbor)  # **하나만 추가**
                visited[neighbor] = True  # 방문 처리
                print(neighbor, end=' ')
                found = True
                break  # **하나 추가 후 즉시 종료**

        if not found:  # 방문할 노드가 없으면 pop
            stack.pop()

# 그래프 정의 (인접 리스트)
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

# DFS 실행 (스택 방식)
dfs_stack(graph, 1)