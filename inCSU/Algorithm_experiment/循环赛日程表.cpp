#include <iostream>
#include<math.h>
#include<iomanip>
using namespace std;

#define a_size 200

void cs_merge(int a[a_size][a_size],int k,int start){
    int m = pow(2,k-1);
    for(int i=start;i<m+start;i++){
        for(int j=0;j<m;j++){
            a[i+m][j+m] = a[i][j];
        }
    }
    for(int i=start+m;i<start+m*2;i++){
        for(int j=0;j<m;j++){
            a[i-m][j+m] = a[i][j];
        }
    }
}

void cycleScheduleSort(int a[a_size][a_size],int k,int start){
    if(k>0){
        int m = pow(2,k-1);
        cycleScheduleSort(a,k-1,start);
        cycleScheduleSort(a,k-1,start+m);

        cs_merge(a,k,start);
    }
}

int main()
{
    int k;
    cout<<"Enter k : ";
    cin>>k;
    int m = pow(2,k);
    cout<<"Number of competitors : "<<m<<endl;
    int a[a_size][a_size];
    for(int i=1;i<m;i++){
        a[0][i]=i;
    }
    for(int i=1;i<m+1;i++){
        a[i][0]=i;
    }

    cycleScheduleSort(a,k,1);

    cout<<"Cycle schedule is:"<<endl;
    for(int i=0;i<m+1;i++){
        for(int j=0;j<m;j++){
            if(a[i][j] != 0)
                cout<<setiosflags(ios::left)<<setw(3)<<a[i][j];
			else
                cout<<setiosflags(ios::left)<<setw(3)<<" ";
        }
        cout<<endl;
    }
    return 0;
}
