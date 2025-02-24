def s(arr1, arr2, len):
    result = 0
    for i in range(len):
        result += arr1[i]*arr2[i]
    
    return result

n = int(input())

a = list(map(int, input().split()))
b = list(map(int, input().split()))

a.sort()
b.sort(reverse=True)

print(s(a, b, n))
