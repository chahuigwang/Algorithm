#include <iostream>
#include <string>
#include <vector>
#include <stack>

using namespace std;

int main(){
    vector<string> v;
    stack<char> s;
    string str;
    while(1){
        getline(cin, str);
        v.push_back(str);
        if(str == ".")
            break;
    }

    for(int i = 0; i < v.size(); i++){
        if(v[i] == ".")
            break;
        str = v[i];
        for(int j = 0; j < str.length(); j++){
            if(str[j] == '(' || str[j] == '[')
                s.push(str[j]);
            if(str[j] == ')'){
                if(s.empty()){
                    s.push(' ');
                    break;
                }
                else if(s.top() == '('){
                    s.pop();
                }
                else{
                    s.push(' ');
                    break;
                }
            }
            if(str[j] == ']'){
                if(s.empty()){
                    s.push(' ');
                    break;
                }
                else if(s.top() == '['){
                    s.pop();
                }
                else{
                    s.push(' ');
                    break;
                }
            }
        }
        if(s.empty())
            cout << "yes" << endl;
        else{
            cout << "no" << endl;
            while(!s.empty())
                s.pop();
        }
    }

    return 0;
}