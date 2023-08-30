#include<iostream>
using namespace std;

bool bang(int x,int j,int n,int a[]){
	for(int i=0;i<n;i++){
		if(i!=j && a[i] == x) return true;
	}
	return false;
}

int main(){
	int n,L,t;
	cin>>n>>L>>t;
	
	int a[n];
	for(int i=0;i<n;i++){
		cin>>a[i];
	}

	bool flag[n];
	for(int i=0;i<n;i++){
		flag[i]=true;
	}
	for(int i=0;i<t;i++){
		for(int j=0;j<n;j++){
			if(!bang(a[j],j,n,a)){
				if(a[j]==L) flag[j]=false;
				else if(a[j]==0) flag[j]=true;
				else ;
			}
			else{
				if(flag[j]) flag[j]=false;
				else flag[j]=true;
			}
		}
		
		for(int k=0;k<n;k++){
			if(flag[k]) a[k]++;
			else a[k]--;
		}
	}
	
	for(int i=0;i<n;i++){
		cout<<a[i]<<" ";
	}
	return 0;
}
