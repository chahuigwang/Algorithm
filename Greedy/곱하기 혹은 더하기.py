s = input()

s = list(map(int, s))

while len(s) > 1:
    num1 = s.pop(0)
    num2 = s.pop(0)

    if num1 < 2 or num2 < 2:
        result = num1 + num2
    else:
        result = num1 * num2

    s.insert(0, result)

print(s[0])
