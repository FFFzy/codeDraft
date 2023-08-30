#include<iostream>
#include<cmath>
using namespace std;

int main(){
	int n;
	cin>>n;
	
	int a[n];
	for(int i=1;i<=n;i++){
		a[i]=i;
	}
	
	int m;
	cin>>m;
	int temp;
	for(int i=0;i<m;i++){
		int p,q;
		cin>>p>>q;
		temp=0;
		for(int j=1;j<=n;j++){
			if(a[j] == p){
				temp = j;
				break;
			}
		}
		if(q>0){
			for(int j=0;j<q;j++){
				a[temp]=a[temp+1];
				temp++;
			}
			a[temp]=p;
		}
		if(q<0){
			for(int j=0;j<abs(q);j++){
				a[temp]=a[temp-1];
				temp--;
			}
			a[temp]=p;
		}
	}
	
	for(int i=1;i<=n;i++){
		cout<<a[i]<<" ";
	}
	return 0;
}
