#include <iostream>
#include <iomanip>
using namespace std;

int main(){
    int graph[5][5] = {{0,10,-1,30,100},
                  {-1,0,50,-1,-1},
                  {-1,-1,0,-1,10},
                  {-1,-1,20,0,60},
                  {-1,-1,-1,-1,0}};
    int D[5][5] = {{0,10,-1,30,100},
                   {0,0,0,0,0},
                   {0,0,0,0,0},
                   {0,0,0,0,0},
                   {0,0,0,0,0}};
    int R[5] = {0,-1,-1,-1,-1};
    int R_N = 0;
    int B[5] = {-1, 1, 2, 3, 4};
    int B_N = 0;

    for(int i=0; i<5-1; i++){
        int B_min;
        for(int j=0; j<5; j++){
            if(B[j]>=0)
                B_min = j;
        }
        for(int j=0; j<5; j++){
            if(B[j]>0 && D[i][j]>=0 && D[i][j]<D[i][B_min]){
            B_min = j;
            }
        }
        R[++R_N] = B_min;
        B[B_min] = -1;

        for(int j=0; j<5; j++){
            if(B[j]>=0){
                if(graph[B_min][j]>0){
                    if(D[i][j]<0){
                        D[i+1][j] = D[i][B_min]+graph[B_min][j];
                    }
                    else if(D[i][B_min] + graph[B_min][j] < D[i][j]){
                        D[i+1][j] = D[i][B_min]+graph[B_min][j];
                    }
                    else{
                        D[i+1][j] = D[i][B_min]+graph[B_min][j];
                    }
                }
                else{
                    D[i+1][j] = D[i][j];
                }
            }
            else{
                D[i+1][j] = D[i][j];
            }
        }
    }

    cout<<"Original:"<<endl;
    cout<<setw(3)<<" ";
    for(int i=0; i<5; i++)
        cout<<setw(3)<<i;
    cout<<endl;
    for(int i=0; i<5; i++){
        cout<<setw(3)<<i;
    for(int j=0; j<5; j++){
        cout<<setw(3)<<graph[i][j];
    }
        cout<<endl;
    }
    cout<<endl;

    cout<<"Changed:"<<endl;
    cout<<setw(3)<<" ";
    for(int i=0; i<5; i++)
    cout<<setw(3)<<i;
    cout<<endl;
    for(int i=0; i<5; i++){
        cout<<setw(3)<<i;
    for(int j=0; j<5; j++){
        cout<<setw(3)<<D[i][j];
        }
        cout<<endl;
    }
    return 0;
}
