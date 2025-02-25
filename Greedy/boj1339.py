n = int(input())  # 단어 수
words = [input() for _ in range(n)]  # 입력 단어들

# 알파벳 중요도 계산 (각 자릿수에서의 가중치를 합산)
char_weight = {}
for word in words:
    length = len(word)
    for i, char in enumerate(word):
        power = length - i - 1  # 자릿수에 해당하는 가중치
        if char not in char_weight:
            char_weight[char] = 0
        char_weight[char] += 10 ** power

# 중요도가 높은 알파벳부터 내림차순으로 정렬
sorted_chars = sorted(char_weight.items(), key=lambda x: x[1], reverse=True)

# 알파벳에 숫자 할당
char_to_num = {}
num = 9
for char, _ in sorted_chars:
    char_to_num[char] = num
    num -= 1

# 단어를 숫자로 변환하고 합 계산
total_sum = 0
for word in words:
    num_str = ''.join(str(char_to_num[char]) for char in word)
    total_sum += int(num_str)

print(total_sum)