s = input()

chars = []
nums = []

for char in s:
    if char >= 'A' and char <= 'Z':
        chars.append(char)
    else:
        nums.append(int(char))
        
sum_nums = sum(nums)
chars.sort()

chars.append(str(sum_nums))

print(''.join(chars))