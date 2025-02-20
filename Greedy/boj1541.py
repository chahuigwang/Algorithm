expression = input()

expression = expression.split('-')

for i in range(len(expression)):
    if len(expression[i].split('+')) >= 2:
        expression[i] = list(map(int, expression[i].split('+')))
        expression[i] = sum(expression[i])
    else:
        expression[i] = int(expression[i])

result = expression[0]

for i in range(1, len(expression)):
    result -= expression[i]

print(result)
