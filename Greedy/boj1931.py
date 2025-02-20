n = int(input())

meetings = [list(map(int, input().split())) for _ in range(n)]
meetings.sort(key=lambda x: (x[1], x[0]))

end_time = 0
cnt = 0

for meeting in meetings:
    if meeting[0] >= end_time:
        cnt += 1
        end_time = meeting[1]

print(cnt)
