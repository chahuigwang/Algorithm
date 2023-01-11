#include <iostream>
#include <string>
#include <vector>
#include <set>
#include <algorithm>

using namespace std;

int main(){
    int n, k;
    cin >> n;
    cin >> k;

    vector<string> v(n);
    for(int i=0; i<n; i++)
        cin >> v[i];

    sort(v.begin(), v.end());
    
    set<string> s;
    string num;
    do
	{
        num = "";
		for(int i=0; i<k; i++)
            num += v[i];
        s.insert(num);
	}while(next_permutation(v.begin(),v.end()));
    cout << s.size();

    return 0;
}