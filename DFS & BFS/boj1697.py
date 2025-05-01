from collections import deque

def bfs(start):
    queue = deque([start])
    visited[start] = True
    
    while queue:
        node = queue.popleft()
        
        for next_node in (node-1, node+1, node*2):
            if 0 <= next_node <= 100000 and not visited[next_node]:
                queue.append(next_node)
                visited[next_node] = True
                distance[next_node] = distance[node] + 1
                if next_node == k:
                    return distance[k]
    
    return distance[k]            

n, k = map(int, input().split())

distance = [0] * 100001
visited = [False] * 100001

print(bfs(n))