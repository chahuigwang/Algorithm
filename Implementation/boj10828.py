import sys

n = int(sys.stdin.readline().strip())

stack = []
for _ in range(n):
    cmd = sys.stdin.readline().strip() # 명령 입력
    if len(cmd.split()) == 2: # push X 인 경우
        cmd = cmd.split()
        stack.append(int(cmd[1]))
    else:
        if cmd == 'pop':
            if len(stack) == 0:
                print(-1)
            else:
                print(stack.pop())
        if cmd == 'size':
            print(len(stack))
        if cmd == 'empty':
            if len(stack) == 0:
                print(1)
            else:
                print(0)
        if cmd == 'top':
            if len(stack) == 0:
                print(-1)
            else:
                print(stack[-1])
