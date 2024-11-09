t = int(input())

for i in range(t):
    test_num = int(input())
    scores = list(map(int, input().split()))
    data = []
    cnt = [0]*1000
    for score in scores:
        if score not in data:
            data.append(score)

    for j in range(len(scores)):
        for k in range(len(data)):
            if scores[j] == data[k]:
                cnt[k] += 1

    most_freq = max(cnt)
    most_freq_idx = []
    for l in range(len(cnt)):
        if cnt[l] == most_freq:
            most_freq_idx.append(l)

    most_freq_scores = []
    for m in most_freq_idx:
        most_freq_scores.append(data[m])

    print(f'#{i+1} {max(most_freq_scores)}')
