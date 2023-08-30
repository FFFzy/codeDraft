#include<iostream>
using namespace std;

void Sort(int a[],int n)
{
    for(int i=0;i<n;i++)
    {
        for(int j=i+1;j<n;j++)
        {
            if(a[i]<a[j])
            {
                int t=a[i];
                a[i]=a[j];
                a[j]=t;
            }
        }
    }
}

int set_work(int t[],int m,int n){
	int min;
	int min_M;
	int M[m];
	int time;
	time=t[0];
	for(int i=0;i<m;i++)
	  M[i]=t[i];
	for(int i=m;i<n;i++){
	  min=M[0];
	  min_M=0;
	  for(int j=1;j<m;j++){
	  	if(M[j]<min){
	  		min=M[j];
	  		min_M=j;
		  }
	  }
	  M[min_M]+=t[i];
	  
	  if(time<M[min_M])
	  time=M[min_M];
    }
	return time;
}

int main(){
	int n;
	int Time;
    cout<<"Please enter the number of jobs£º";
    cin>>n;
    int t[n];
    cout<<"Please enter the time for every job£º"<<endl;
    for(int i=0;i<n;i++){
        cin>>t[i];
    }

    Sort(t,n);
    
    int m;
    cout<<"Please enter the number of machines£º";
    cin>>m;
    
    if(m>=n)
    cout<<"The shortest time is "<<t[0]<<endl;
    else
    Time=set_work(t,m,n);
    cout<<"The shortest time is "<<Time<<endl;
    return 0;
}
