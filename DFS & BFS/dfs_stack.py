def dfs_stack(graph, start):
    visited = [False] * (len(graph) + 1)
    stack = [start]  # 1. 시작 노드를 스택에 삽입하고 방문 처리
    visited[start] = True
    print(start, end=' ')

    # 3. 더 이상 2번의 과정을 수행할 수 없을 때까지 반복
    while stack:
        # 2번 과정
        node = stack[-1]  # 최상단 노드 확인
        found = False  # 방문하지 않은 인접 노드가 있는지 체크

        for neighbor in graph[node]:  # 작은 번호부터 탐색
            if not visited[neighbor]:  # 인접 노드를 방문하지 않았다면
                stack.append(neighbor)
                visited[neighbor] = True  # 방문 처리
                print(neighbor, end=' ')
                found = True
                break
            
        if not found:  # 방문하지 않은 인접 노드가 없으면 pop
            stack.pop()

# 그래프 정의 (2차원 리스트)
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

dfs_stack(graph, 1)