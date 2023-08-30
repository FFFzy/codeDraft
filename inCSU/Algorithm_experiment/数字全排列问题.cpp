#include<iostream>
using namespace std;

#define MAX 9

void numberorder(int account,int a[],int n){
	if(account >= n){
        for(int i=0; i<n; i++)
            cout<<a[i]+1<<" ";
        cout<<endl;
    }
    for(int i=0; i<n; i++){
        bool flag = true;
        for(int j=0; j<account; j++){
            if(a[j] == i)
                flag = false;
        }
        if(flag){
            a[account] = i;
            numberorder(account+1,a,n);
        }
    }
}

int main(){
	int n;
	int a[MAX];
	int account=0;
	cout<<"Please enter the n: ";
	cin>>n;
	
	numberorder(account,a,n);
	
	return 0;
}
