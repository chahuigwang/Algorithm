def transpose(arr, x):
    for i in range(x+1):
        for j in range(i+1, x+1):
            arr[i][j], arr[j][i] = arr[j][i], arr[i][j]
            
def is_sorted(arr):
    n = len(arr[0])
    for i in range(n):
        for j in range(n):
            if arr[i][j] != i * n + j + 1:
                return False
    return True

T = int(input())
for test_case in range(1, T + 1):
    n = int(input())
    a = [list(map(int, input().split())) for _ in range(n)]

    count = 0
    for i in range(n - 1, 0, -1):
        if is_sorted(a):
            break
        if a[0][i] != i + 1:
            transpose(a, i)
            count += 1

    print(count)