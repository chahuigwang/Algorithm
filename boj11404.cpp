#include <iostream>
#include <vector>

#define INF 10000000

using namespace std;

int n, m;
int bus[101][101];

void floydWarshal(){
    for(int k = 1; k < n+1; k++){
        for(int i = 1; i < n+1; i++){
            for(int j = 1; j < n+1; j++){
                if(bus[i][k] + bus[k][j] < bus[i][j])
                    bus[i][j] = bus[i][k] + bus[k][j];
            }
        }
    }
}

int main(){
    cin >> n;
    cin >> m;

    for(int i = 1; i < n+1; i++){
        for(int j = 1; j < n+1; j++){
            if(i == j)
                bus[i][j] = 0;
            else
                bus[i][j] = INF;
        }
    }

    int a, b, c;
    for(int i = 0; i < m; i++){
        cin >> a >> b >> c;
        if(c < bus[a][b])
            bus[a][b] = c;
    }

    floydWarshal();

    for(int i = 1; i < n+1; i++){
        for(int j = 1; j < n+1; j++){
            if(bus[i][j] == INF)
                cout << 0;
            else
                cout << bus[i][j];
            cout << ' ';
        }
        cout << endl;
    }

    return 0;
}