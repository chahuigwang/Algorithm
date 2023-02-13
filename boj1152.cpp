#include <iostream>
#include <cstring>

using namespace std;

int main(){
    char str[1000002];
    fgets(str, 1000002, stdin);
    str[strlen(str)-1] = '\0';

    // 처음에 나오는 공백 제거
    if(str[0] == ' '){
        for(int i = 0; i < strlen(str); i++)
            str[i] = str[i+1];
    }
    // 마지막에 나오는 공백 제거
    if(str[strlen(str)-1] == ' ')
        str[strlen(str)-1] = '\0';
    
    int cnt = 0;
    for(int i = 0; i < strlen(str); i++){
        if(str[i] == ' ')
            cnt++;
    }
    if(strlen(str) == 0)
        cout << 0;
    else
        cout << (cnt+1);

    return 0;
}