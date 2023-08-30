#include<iostream>
using namespace std;
int x[100];
int bestx[100];
int m[100][100];
int f1=0;
int f2=0;
int cf=0;
int time=9999;
int n;

void swap(int &a,int &b){
    int temp=a;
    a=b;
    b=temp;
}

void Scheduling(int t){
    int tempf,j;
    if(t>n){
        if(cf<time){
        for(int i=1; i<=n; i++)
            bestx[i]=x[i];
            time=cf;
        }
    }
    else{
        for(j=t; j<=n; j++){
            f1+=m[x[j]][1];
            tempf=f2;
            f2=(f1>f2?f1:f2)+m[x[j]][2];
            cf+=f2;
            if(cf<time){
                swap(x[t],x[j]);
                Backtrack(t+1);
                swap(x[t],x[j]);
            }
            f1-=m[x[j]][1];
            cf-=f2;
            f2=tempf;
        }
    }
}
 
int main(){
    int i,j;
 
    cout<<"Please enter the number of work£º";
    cin>>n;
    cout<<"Please enter the time on machine 1: ";
        for(j=1; j<=n; j++)
            cin>>m[j][1];
    cout<<"Please enter the time on machine 2:";
        for(j=1; j<=n; j++)
            cin>>m[j][2];
    for(i=1; i<=n; i++)
        x[i]=i;
    
	Scheduling(1);
 
    cout<<"Best scheduling sequence£º";
    for(i=1; i<=n; i++)
        cout<<bestx[i]<<' ';
    cout<<endl;
 
    cout<<"Best scheduling time£º";
    cout<<time;
    return 0;
}
