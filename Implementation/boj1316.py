n = int(input())
words = [input() for _ in range(n)]

cnt = 0
for word in words:
    chars = []
    is_group = True
    chars.append(word[0])
    for i in range(1, len(word)):
        if word[i] in chars and word[i] != word[i-1]:
            is_group = False
            break
        chars.append(word[i])
    if is_group:
        cnt += 1
    
print(cnt)