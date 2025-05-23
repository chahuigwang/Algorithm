def dfs(idx, cur_sum):
    global count
    if cur_sum > k:
        return
    if idx == n:
        if cur_sum == k:
            count += 1
        return

    dfs(idx + 1, cur_sum + nums[idx])
    dfs(idx + 1, cur_sum)

T = int(input())
for test_case in range(1, T+1):
    n, k = map(int, input().split())
    nums = list(map(int, input().split()))

    count = 0
    dfs(0, 0)

    print(f'#{test_case} {count}')