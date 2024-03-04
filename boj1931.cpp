#include<iostream>
#include<vector>

using namespace std;

int main(){
    int n; // n : 회의의 수
    cin >> n;

    vector<pair<int, int> > time; // (시작 시간, 종료 시간)이 들어가는 벡터
    int s, e; // s : 시작 시간, e : 종료 시간
    for(int i = 0; i < n; i++){
        cin >> s >> e;
        time.push_back(make_pair(s, e));
    }
    
    pair<int, int> temp;
    for(int i = 0; i < n; i++){
        for(int j = 0; j < n-1-i; j++){
            if((time[j].second - time[j].first) > (time[j+1].second - time[j+1].first)){
                temp = time[j];
                time[j] = time[j+1];
                time[j+1] = temp;
            }
        }
    }

    for(int i = 0; i < n; i++)
        cout << '(' << time[i].first << ", " << time[i].second << ')' << endl;

    return 0;
}