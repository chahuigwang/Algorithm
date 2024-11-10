n = int(input())

num_list = [str(i) for i in range(1, n+1)]

for num in num_list:
    count = 0
    for i in range(len(num)):
        if num[i] == '3' or num[i] == '6' or num[i] == '9':
            count += 1

    if count != 0:
        print('-'*count, end=' ')
    else:
        print(num, end=' ')
