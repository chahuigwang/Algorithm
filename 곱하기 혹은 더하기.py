s = '567'

s = list(map(int, s))

while len(s) > 1:
    insert_num = max((s[0]+s[1]), (s[0]*s[1]))
    s.pop(0)
    s.pop(0)
    s.insert(0, insert_num)

print(s[0])
